package com.cmms.servicedesk.repository;

import com.cmms.servicedesk.model.Incidencia;
import com.cmms.servicedesk.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IIncidenciaRepository extends JpaRepository<Incidencia, Integer> {
    List<Incidencia> findByPersona(Persona persona);
}
