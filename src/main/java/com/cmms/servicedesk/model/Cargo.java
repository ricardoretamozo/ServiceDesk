package com.cmms.servicedesk.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
@Entity
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "n_id_cargo",columnDefinition = "serial2")
    private Integer idCargo;

    @NotBlank(message = "El cargo debe tener un nombre")
    @Column(name = "s_cargo", length = 80)
    private String cargo;

    public Cargo() {
    }

    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
