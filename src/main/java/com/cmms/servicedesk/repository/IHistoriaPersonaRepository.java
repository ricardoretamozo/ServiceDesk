package com.cmms.servicedesk.repository;

import com.cmms.servicedesk.model.HistorialPersona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHistoriaPersonaRepository extends JpaRepository<HistorialPersona, Integer> {
    HistorialPersona findByActivoAAndPersona(char activo, int idPersona);
}
