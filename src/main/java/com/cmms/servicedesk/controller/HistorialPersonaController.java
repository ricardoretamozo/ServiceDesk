package com.cmms.servicedesk.controller;

import com.cmms.servicedesk.model.HistorialPersona;
import com.cmms.servicedesk.service.HistorialPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/historialpersonas")
public class HistorialPersonaController {
    @Autowired
    private HistorialPersonaService historialPersonaService;

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

    @PostMapping
    public ResponseEntity<HistorialPersona> create(@Valid @RequestBody HistorialPersona historialPersona){
        return new ResponseEntity<>(historialPersonaService.create(historialPersona), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<HistorialPersona> update(@Valid @RequestBody HistorialPersona historialPersona){
        return historialPersonaService.findById(historialPersona.getIdHistorialPersona())
                .map(o -> ResponseEntity.ok(historialPersonaService.update(historialPersona)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HistorialPersona> delete(@PathVariable("id") Integer idHistorialPersona){
        return historialPersonaService.findById(idHistorialPersona)
                .map(o -> {
                    historialPersonaService.delete(idHistorialPersona);
                    return ResponseEntity.ok(o);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}