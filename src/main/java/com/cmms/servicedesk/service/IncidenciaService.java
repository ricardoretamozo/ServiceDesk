package com.cmms.servicedesk.service;

import com.cmms.servicedesk.model.Incidencia;
import com.cmms.servicedesk.repository.IIncidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidenciaService implements IIncidenciaService{

    @Autowired
    IIncidenciaRepository incidenciaRepository;

    @Override
    public List<Incidencia> findAll() {
        return incidenciaRepository.findAll();
    }

    @Override
    public Optional<Incidencia> findById(Integer id) {
        return incidenciaRepository.findById(id);
    }

    @Override
    public Incidencia create(Incidencia incidencia) {
        return incidenciaRepository.save(incidencia);
    }

    @Override
    public Incidencia update(Incidencia incidencia) {
        return incidenciaRepository.save(incidencia);
    }

    @Override
    public void delete(Integer id) {
        incidenciaRepository.deleteById(id);
    }
}
