package com.cmms.servicedesk.controller;

import com.cmms.servicedesk.model.Incidencia;
import com.cmms.servicedesk.service.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/incidencias")
public class IncidenciaController {
    @Autowired
    private IncidenciaService incidenciaService;

    @GetMapping
    public ResponseEntity<List<Incidencia>> findAll() {
        return ResponseEntity.ok(incidenciaService.findAll());
    }

    @GetMapping("/incidencias/{id}")
    public ResponseEntity<Incidencia> findById(@PathVariable("id") Integer idIncidencia){
        return incidenciaService.findById(idIncidencia)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Incidencia> create(@Valid @RequestBody Incidencia incidencia){
        return new ResponseEntity<>(incidenciaService.create(incidencia), HttpStatus.CREATED);
    }

}
