package com.cmms.servicedesk.repository;

import com.cmms.servicedesk.model.Organo;
import com.cmms.servicedesk.model.Persona;
import com.cmms.servicedesk.model.PersonaOrgano;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IPersonaOrganoRepository extends JpaRepository<PersonaOrgano, Integer> {
    List<PersonaOrgano> findByPersona(Persona persona);

    Optional<PersonaOrgano> findByPersonaAndOrgano(Persona persona, Organo organo);
}