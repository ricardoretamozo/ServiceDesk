package com.cmms.servicedesk.controller;

import com.cmms.servicedesk.model.Sede;
import com.cmms.servicedesk.service.SedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/sedes")
public class SedeController {
    @Autowired
    private SedeService sedeService;

    @GetMapping
    public ResponseEntity<List<Sede>> findAll(){
        return ResponseEntity.ok(sedeService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Sede> findById(@PathVariable("id") Integer idSede){
        return sedeService.findById(idSede)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Sede> create(@Valid @RequestBody Sede sede){
        return new ResponseEntity<>(sedeService.create(sede), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Sede> update(@Valid @RequestBody Sede sede){
        return sedeService.findById(sede.getIdSede())
                .map(s -> ResponseEntity.ok(sedeService.update(sede)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Sede> delete(@PathVariable("id") Integer idSede){
        return sedeService.findById(idSede)
                .map(s -> {
                    sedeService.delete(idSede);
                    return ResponseEntity.ok(s);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
