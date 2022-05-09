package com.cmms.servicedesk.repository;

import com.cmms.servicedesk.model.Sede;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISedeRepository extends JpaRepository<Sede, Integer> {
}
