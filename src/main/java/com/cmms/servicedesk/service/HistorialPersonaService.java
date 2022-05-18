package com.cmms.servicedesk.service;

import com.cmms.servicedesk.model.HistorialPersona;
import com.cmms.servicedesk.repository.IHistoriaPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistorialPersonaService implements ICRUDService<HistorialPersona> {
    @Autowired
    private IHistoriaPersonaRepository historiaPersonaRepository;

    @Override
    public List<HistorialPersona> findAll() {
        return historiaPersonaRepository.findAll();
    }

    @Override
    public Optional<HistorialPersona> findById(Integer id) {
        return historiaPersonaRepository.findById(id);
    }

    @Override
    public HistorialPersona create(HistorialPersona model) {
        return historiaPersonaRepository.save(model);
    }

    @Override
    public HistorialPersona update(HistorialPersona model) {
        return historiaPersonaRepository.save(model);
    }

    @Override
    public void delete(Integer id) {
        historiaPersonaRepository.deleteById(id);
    }
}
