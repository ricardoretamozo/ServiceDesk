package com.cmms.servicedesk.service;

import com.cmms.servicedesk.model.Persona;
import com.cmms.servicedesk.repository.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PersonaService implements IPersonaService {

    @Autowired
    private IPersonaRepository personaRepository;

    @Override
    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

    @Override
    public Optional<Persona> findById(Integer id) {
        return personaRepository.findById(id);
    }

    @Override
    public Persona create(Persona cliente) {
        return personaRepository.save(cliente);
    }

    @Override
    public Persona update(Persona cliente) {
        return personaRepository.save(cliente);
    }

    @Override
    public void delete(Integer id) {
        personaRepository.deleteById(id);
    }
}
