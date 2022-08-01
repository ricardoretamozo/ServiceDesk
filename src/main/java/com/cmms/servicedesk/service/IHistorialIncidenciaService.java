package com.cmms.servicedesk.service;

import com.cmms.servicedesk.model.HistorialIncidencia;
import com.cmms.servicedesk.model.Incidencia;
import com.cmms.servicedesk.model.Persona;

import java.util.List;
import java.util.Optional;

public interface IHistorialIncidenciaService {

    List<HistorialIncidencia> findAll();

    Optional<HistorialIncidencia> findById(Integer id);

    HistorialIncidencia create(HistorialIncidencia historialIncidencia);

    HistorialIncidencia update(HistorialIncidencia historialIncidencia);

    void delete(Integer id);

    List<HistorialIncidencia> findByPersonaAsignado(Persona persona);

    List<HistorialIncidencia> findByPersona_asignadoIsNull();

    List<HistorialIncidencia> findByPersona_asignadoIsNotNull();

    HistorialIncidencia findByIncidencia(Incidencia incidencia);

}
