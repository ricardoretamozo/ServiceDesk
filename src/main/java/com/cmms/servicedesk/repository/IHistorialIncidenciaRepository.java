package com.cmms.servicedesk.repository;

import com.cmms.servicedesk.model.HistorialIncidencia;
import com.cmms.servicedesk.model.Incidencia;
import com.cmms.servicedesk.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IHistorialIncidenciaRepository extends JpaRepository<HistorialIncidencia, Integer> {

    HistorialIncidencia findByIncidenciaAndEstadoIncidencia(Incidencia incidencia, char estado);

    @Query(value = "SELECT * FROM historial_incidencia WHERE n_id_persona_asignado = ?1", nativeQuery = true)
    List<HistorialIncidencia> findByPersonaAsignado(Persona persona);

    @Query(value = "SELECT * FROM historial_incidencia WHERE n_id_persona_asignado is null", nativeQuery = true)
    List<HistorialIncidencia> findByPersona_asignadoIsNull();

    @Query(value = "SELECT * FROM historial_incidencia WHERE n_id_persona_asignado is not null", nativeQuery = true)
    List<HistorialIncidencia> findByPersona_asignadoIsNotNull();

}

