package com.cmms.servicedesk.service;

import com.cmms.servicedesk.model.Incidencia;

import java.util.List;
import java.util.Optional;

public interface IIncidenciaService {

    List<Incidencia> findAll();

    Optional<Incidencia> findById(Integer id);

    Incidencia create(Incidencia incidencia);

    Incidencia update(Incidencia incidencia);

    void delete(Integer id);

}
