package com.cmms.servicedesk.service;

import com.cmms.servicedesk.model.PerfilPersona;
import com.cmms.servicedesk.repository.IPerfilPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilPersonaService implements IPerfilPersonaService{
    @Autowired
    private IPerfilPersonaRepository perfilPersonaRepository;

    @Override
    public List<PerfilPersona> findAll() {
        return perfilPersonaRepository.findAll();
    }
    @Override
    public Optional<PerfilPersona> findById(Integer id) {
        return perfilPersonaRepository.findById(id);
    }

    @Override
    public PerfilPersona create(PerfilPersona cliente) {
        return perfilPersonaRepository.save(cliente);
    }

    @Override
    public PerfilPersona update(PerfilPersona cliente) {
        return perfilPersonaRepository.save(cliente);
    }

    public void delete(Integer id) {
        perfilPersonaRepository.deleteById(id);
    }
}
