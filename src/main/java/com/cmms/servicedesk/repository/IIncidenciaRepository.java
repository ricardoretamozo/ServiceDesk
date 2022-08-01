package com.cmms.servicedesk.repository;

import com.cmms.servicedesk.model.Incidencia;
import com.cmms.servicedesk.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IIncidenciaRepository extends JpaRepository<Incidencia, Integer> {
    List<Incidencia> findByPersona(Persona persona);

//    @Query(value = "SELECT * FROM incidencia WHERE n_id_persona_asignado = ?1", nativeQuery = true)
//    List<Incidencia> findByPersonaAsignado(Persona persona);
//
//    @Query(value = "SELECT * FROM incidencia WHERE n_id_persona_asignado is null ", nativeQuery = true)
//    List<Incidencia> findByPersona_asignadoIsNull();
//    @Query(value = "SELECT * FROM incidencia WHERE n_id_persona_asignado is not null ", nativeQuery = true)
//    List<Incidencia> findByPersona_asignadoIsNotNull();

}
