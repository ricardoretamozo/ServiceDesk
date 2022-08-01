package com.cmms.servicedesk.controller;

import com.cmms.servicedesk.model.*;
import com.cmms.servicedesk.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/incidencias")
public class IncidenciaController {
    @Autowired
    private IncidenciaService incidenciaService;
    @Autowired
    private HistorialPersonaService historialPersonaService;
    @Autowired
    private PersonaService personaService;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PersonaOrganoService personaOrganoService;

    @Autowired
    private EstadoTecnicoService estadoTecnicoService;

    @Autowired
    private HistorialIncidenciaService historialIncidenciaService;

    @GetMapping
    public ResponseEntity<List<Incidencia>> findAll() {
        return ResponseEntity.ok(incidenciaService.findAll());
    }

    @GetMapping("/persona/detalles/{id}")
    public ResponseEntity<Incidencia> findById(@PathVariable("id") Integer idIncidencia){
        return incidenciaService.findById(idIncidencia)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/persona/{id}")
    public ResponseEntity<List<Incidencia>> findByPersona(@PathVariable("id") Integer idPersona){
        Persona persona = personaService.findById(idPersona).get();
        List<Incidencia> incidencia = incidenciaService.findByPersona(persona);
        return ResponseEntity.ok(incidencia);
    }

    @GetMapping("/persona/asignado/{id}")
    public ResponseEntity<List<Incidencia>> findByPersonaAsignado(@PathVariable("id") Integer idPersona){
        Persona persona = personaService.findById(idPersona).get();
        List<HistorialIncidencia> historialIncidencia = historialIncidenciaService.findByPersonaAsignado(persona);
        List<Incidencia> incidencia = historialIncidencia.stream().map(HistorialIncidencia::getIncidencia).collect(java.util.stream.Collectors.toList());
        incidencia.stream().forEach(i -> i.setHistorialIncidencia(historialIncidenciaService.findByIncidencia(i)));
        return ResponseEntity.ok(incidencia);
    }

    @GetMapping("/persona/asignados")
    public ResponseEntity<List<Incidencia>> findByPersonaAsignados(){

        List<HistorialIncidencia> historialIncidencia = historialIncidenciaService.findByPersona_asignadoIsNotNull();
        System.out.println(historialIncidencia.size());
        List<Incidencia> incidencia = historialIncidencia.stream().map(HistorialIncidencia::getIncidencia).collect(java.util.stream.Collectors.toList());
        incidencia.stream().forEach(i -> {
            HistorialIncidencia historialIncidencia1 = historialIncidenciaService.findByIncidencia(i);
            historialIncidencia1.setIncidencia(null);
            i.setHistorialIncidencia(historialIncidencia1);
        });
        return ResponseEntity.ok(incidencia);
    }

    @GetMapping("/persona/noasignados")
    public ResponseEntity<List<Incidencia>> findByPersonaNoAsignados(){
        List<HistorialIncidencia> historialIncidencia = historialIncidenciaService.findByPersona_asignadoIsNull();
        System.out.println(historialIncidencia.size());
        if (historialIncidencia.isEmpty()) {
            return ResponseEntity.noContent().build();
        }else {
            List<Incidencia> incidencia = historialIncidencia.stream().map(HistorialIncidencia::getIncidencia).collect(java.util.stream.Collectors.toList());
            System.out.println(incidencia.size());
            System.out.println("----------------------------------------------------");
            incidencia.forEach(i -> {
                HistorialIncidencia historialIncidencia1 = historialIncidenciaService.findByIncidencia(i);
                historialIncidencia1.setIncidencia(null);
                i.setHistorialIncidencia(historialIncidencia1);
            });
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(incidencia.size());
            return ResponseEntity.ok(incidencia);
        }
    }


    @PostMapping("/usuariocomun")
    public ResponseEntity<Incidencia> createusuario(@RequestBody Incidencia incidencia){
        System.out.println("Creando incidencia");
        Persona persona = incidencia.getPersona();
        System.out.println("Persona: " + persona.getIdpersona());
        HistorialPersona historialPersona = historialPersonaService.findByPersonaAndActivo(persona,'S').get();
        System.out.println("HistorialPersona: " + historialPersona.getIdHistorialPersona());
        HistorialIncidencia historialIncidencia = incidencia.getHistorialIncidencia();
        System.out.println("HistorialIncidencia: " + historialIncidencia);
        incidencia.setOficina(historialPersona.getOficina());
        historialIncidencia.setIp(incidenciaService.getClientIp(request));
        historialIncidencia.setFecha(ZonedDateTime.now());
        //incidencia.setIp(incidenciaService.getClientIp(request));
        List<PersonaOrgano> personaOrgano = personaOrganoService.findByOrgano(historialPersona.getOficina().getOrgano());
        if (personaOrgano.size() == 0){
//            incidencia.setPersona_asignado(null);
            historialIncidencia.setPersona_asignado(null);
        }else {
            for (PersonaOrgano personaOrgano1:personaOrgano ) {
                if (estadoTecnicoService.findByPersonaAndActivo(personaOrgano1.getPersona(),'A').size() == 1){
                    historialIncidencia.setPersona_asignado(personaOrgano1.getPersona());
//                    incidencia.setPersona_asignado(personaOrgano1.getPersona());
                    estadoTecnicoService.findByPersonaAndActivo(personaOrgano1.getPersona(),'A').get(0).setActivo('N');
                    break;
                }
            }
            if (historialIncidencia.getPersona_asignado() == null){
                for (EstadoTecnico estadoTecnico:estadoTecnicoService.findByActivo('N')) {
                    for (PersonaOrgano personaOrgano1:personaOrgano ) {
                     if (estadoTecnicoService.findByPersona(personaOrgano1.getPersona()).size() == 1){
                         estadoTecnico.setActivo('A');
                     }
                    }
                }
                for (PersonaOrgano personaOrgano1:personaOrgano ) {
                    if (estadoTecnicoService.findByPersona(personaOrgano1.getPersona()).size() == 1){
                        historialIncidencia.setPersona_asignado(personaOrgano1.getPersona());
//                        incidencia.setPersona_asignado(personaOrgano1.getPersona());
                        estadoTecnicoService.findByPersona(personaOrgano1.getPersona()).get(0).setActivo('N');
                        break;
                    }
                }
            }
        }
        historialIncidencia.setEstadoIncidencia('A');
        historialIncidencia.setEstado('P');
        incidencia.setHistorialIncidencia(historialIncidencia);
        Incidencia incidencia1 = incidenciaService.create(incidencia);
        historialIncidencia.setIncidencia(incidencia1);
        historialIncidenciaService.create(historialIncidencia);
        historialIncidencia.setIncidencia(null);
        //incidencia.setFecha(ZonedDateTime.now());
        return new ResponseEntity<>(incidencia1, HttpStatus.CREATED);
    }

//    @PostMapping(path = "/cordinador")
//    public ResponseEntity<Incidencia> create(@Valid @RequestBody Incidencia incidencia){
//        Persona persona = incidencia.getPersona_registro();
//        HistorialPersona historialPersona = historialPersonaService.findByPersonaAndActivo(persona,'S').get();
//        incidencia.setOficina(historialPersona.getOficina());
//        return new ResponseEntity<>(incidenciaService.create(incidencia), HttpStatus.CREATED);
//    }
//
//    @PutMapping("asignacion/{id}")
//    public ResponseEntity<Incidencia> updateAsignacion(@PathVariable("id") Integer idIncidencia,@RequestBody Persona persona){
//        Incidencia incidencia = incidenciaService.findById(idIncidencia).get();
//        Persona persona1 = personaService.findById(persona.getIdpersona()).get();
//        incidencia.setPersona_asignado(persona1);
//        return new ResponseEntity<>(incidenciaService.update(incidencia), HttpStatus.OK);
//    }

//    @PutMapping("tramite/{id}")
//    public ResponseEntity<Incidencia> updateStatusIncidencia(@PathVariable("id") Integer idIncidencia){
//        Incidencia incidencia = incidenciaService.findById(idIncidencia).get();
//        incidencia.setEstado('T');
//        incidencia.setFecha(ZonedDateTime.now());
//        return new ResponseEntity<>(incidenciaService.update(incidencia), HttpStatus.OK);
//    }

}
