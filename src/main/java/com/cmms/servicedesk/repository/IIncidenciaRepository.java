package com.cmms.servicedesk.repository;

import com.cmms.servicedesk.model.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IIncidenciaRepository extends JpaRepository<Incidencia, Integer> {
}
