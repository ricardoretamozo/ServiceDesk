package com.cmms.servicedesk.controller;

import com.cmms.servicedesk.model.Persona;
import com.cmms.servicedesk.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @GetMapping
    public ResponseEntity<List<Persona>> findAll(){
        return ResponseEntity.ok(personaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> findById(@PathVariable("id") Integer idPersona){
        return personaService.findById(idPersona)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Persona> create(@Valid @RequestBody Persona persona){
        return new ResponseEntity<>(personaService.create(persona), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Persona> update(@Valid @RequestBody Persona persona){
        return personaService.findById(persona.getIdpersona())
                .map(c->ResponseEntity.ok(personaService.update(persona)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Persona> delete(@PathVariable("id") Integer idPersona){
        return personaService.findById(idPersona)
                .map(c -> {
                    personaService.delete(idPersona);
                    return ResponseEntity.ok(c);
                })
                .orElseGet(()-> ResponseEntity.notFound().build());
    }
}
