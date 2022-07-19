package com.cmms.servicedesk.controller;

import com.cmms.servicedesk.model.*;
import com.cmms.servicedesk.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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

    @PostMapping("/usuariocomun")
    public ResponseEntity<Incidencia> createusuario(@Valid @RequestBody Incidencia incidencia){
        Persona persona = incidencia.getPersona();
        HistorialPersona historialPersona = historialPersonaService.findByPersonaAndActivo(persona,'S').get();
        incidencia.setOficina(historialPersona.getOficina());
        incidencia.setIp(incidenciaService.getClientIp(request));
        List<PersonaOrgano> personaOrgano = personaOrganoService.findByOrgano(historialPersona.getOficina().getOrgano());
        if (personaOrgano.size() == 0){
            incidencia.setPersona_asignado(null);
        }else {
            for (PersonaOrgano personaOrgano1:personaOrgano ) {
                if (estadoTecnicoService.findByPersonaAndActivo(personaOrgano1.getPersona(),'A').size() == 1){
                    incidencia.setPersona_asignado(personaOrgano1.getPersona());
                    estadoTecnicoService.findByPersonaAndActivo(personaOrgano1.getPersona(),'A').get(0).setActivo('N');
                    break;
                }
            }
            if (incidencia.getPersona_asignado() == null){
                for (EstadoTecnico estadoTecnico:estadoTecnicoService.findByActivo('N')) {
                    for (PersonaOrgano personaOrgano1:personaOrgano ) {
                     if (estadoTecnicoService.findByPersona(personaOrgano1.getPersona()).size() == 1){
                         estadoTecnico.setActivo('A');
                     }
                    }
                }
                for (PersonaOrgano personaOrgano1:personaOrgano ) {
                    if (estadoTecnicoService.findByPersona(personaOrgano1.getPersona()).size() == 1){
                        incidencia.setPersona_asignado(personaOrgano1.getPersona());
                        estadoTecnicoService.findByPersona(personaOrgano1.getPersona()).get(0).setActivo('N');
                        break;
                    }
                }
            }
        }
        return new ResponseEntity<>(incidenciaService.create(incidencia), HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Incidencia> create(@Valid @RequestBody Incidencia incidencia){
        Persona persona = incidencia.getPersona_registro();
        HistorialPersona historialPersona = historialPersonaService.findByPersonaAndActivo(persona,'S').get();
        incidencia.setOficina(historialPersona.getOficina());
        return new ResponseEntity<>(incidenciaService.create(incidencia), HttpStatus.CREATED);
    }

}
