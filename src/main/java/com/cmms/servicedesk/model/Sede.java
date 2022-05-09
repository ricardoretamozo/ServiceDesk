package com.cmms.servicedesk.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Sede {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    @Column(name = "n_id_sede",columnDefinition = "serial2")
    private Integer idSede;

    @NotNull
    @NotBlank(message = "La sede debe tener nombre.")
    @Column(name = "s_sede", length = 80)
    private String sede;

    @Column(name = "s_direccion", length = 80)
    private String direccion;

    public Integer getIdSede() {
        return idSede;
    }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
