package com.cmms.servicedesk.repository;

import com.cmms.servicedesk.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonaRepository extends JpaRepository<Persona, Integer> {

    Persona findByDni(String dni);
}
