package com.cmms.servicedesk.controller;

import com.cmms.servicedesk.model.HistorialPersona;
import com.cmms.servicedesk.model.Persona;
import com.cmms.servicedesk.service.HistorialPersonaService;
import com.cmms.servicedesk.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/historialpersonas")
public class HistorialPersonaController {
    @Autowired
    private HistorialPersonaService historialPersonaService;
    @Autowired
    private PersonaService personaService;

    @GetMapping
    public ResponseEntity<List<HistorialPersona>> findAll(){
        return ResponseEntity.ok(historialPersonaService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<HistorialPersona> findById(@PathVariable("id") Integer idHistorialPersona){
        return historialPersonaService.findById(idHistorialPersona)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<HistorialPersona> create(@Valid @RequestBody HistorialPersona historialPersona){
        historialPersonaService.findByPersonaAndActivo(historialPersona.getPersona(),'S')
                .map(h -> {
                    h.setActivo('N');
                    h.setTerminaCargo(historialPersona.getIniciaCargo());
                    historialPersonaService.update(h);
                    historialPersonaService.create(historialPersona);
                    return ResponseEntity.ok(h);
                })
                .orElseGet(() -> {
                    return new ResponseEntity<>(historialPersonaService.create(historialPersona), HttpStatus.CREATED);
                });
        return new ResponseEntity<>(historialPersonaService.create(historialPersona), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<HistorialPersona> update(@Valid @RequestBody HistorialPersona historialPersona){
        return historialPersonaService.findById(historialPersona.getIdHistorialPersona())
                .map(o -> ResponseEntity.ok(historialPersonaService.update(historialPersona)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HistorialPersona> updateActivo(@PathVariable("id") Integer idHistorialPersona){

        return historialPersonaService.findById(idHistorialPersona)
                .map(h -> {
                    char activo = h.getActivo();
                    if(activo == 'S'){
                        h.setActivo('N');
                    }else {h.setActivo('S');}
                    historialPersonaService.update(h);
                    return ResponseEntity.ok(h);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping(path = "/persona/{id}")
    public ResponseEntity<HistorialPersona> findByPersona(@PathVariable("id") Integer idHistorialPersona){
        Persona persona = personaService.findById(idHistorialPersona).get();
        Optional<HistorialPersona> historialPersona = historialPersonaService.findByPersonaAndActivo(persona,'S');
        // System.out.println(historialPersona);
        return historialPersona.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
