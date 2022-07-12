package com.cmms.servicedesk.repository;


import com.cmms.servicedesk.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPersonaRepository extends JpaRepository<Persona, Integer> {

    Optional<Persona> findByDni(String dni);

}
