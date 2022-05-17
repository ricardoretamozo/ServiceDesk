package com.cmms.servicedesk.service;

import com.cmms.servicedesk.model.Persona;

import java.util.List;
import java.util.Optional;

public interface IPersonaService {
    List<Persona> findAll();

    Optional<Persona> findById(Integer id);

    Persona create(Persona cliente);

    Persona update(Persona cliente);

    void delete(Integer id);

    Persona findByDni(String dni);
}
