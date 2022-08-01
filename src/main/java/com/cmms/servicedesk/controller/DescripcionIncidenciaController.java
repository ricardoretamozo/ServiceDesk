package com.cmms.servicedesk.controller;

import com.cmms.servicedesk.model.DescripcionIncidencia;
import com.cmms.servicedesk.model.Incidencia;
import com.cmms.servicedesk.service.DescripcionIncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidencia/descripcion")
public class DescripcionIncidenciaController {

    @Autowired
    private DescripcionIncidenciaService descripcionIncidenciaService;

    @GetMapping("/all")
    public ResponseEntity<List<DescripcionIncidencia>> findAll() {
        return ResponseEntity.ok(descripcionIncidenciaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DescripcionIncidencia> findById(@PathVariable("id") Integer id) {
        return descripcionIncidenciaService.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<DescripcionIncidencia> save(@RequestBody DescripcionIncidencia descripcionIncidencia) {
        System.out.println(descripcionIncidencia);
        return ResponseEntity.ok(descripcionIncidenciaService.create(descripcionIncidencia));
    }

    @PutMapping("/update")
    public ResponseEntity<DescripcionIncidencia> update(DescripcionIncidencia descripcionIncidencia) {
        return ResponseEntity.ok(descripcionIncidenciaService.update(descripcionIncidencia));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        descripcionIncidenciaService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/incidencia")
    public ResponseEntity<DescripcionIncidencia> findByIncidencia(@RequestBody Incidencia incidencia) {
        return descripcionIncidenciaService.findByIncidencia(incidencia).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
