package com.cmms.servicedesk.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Organo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "n_id_organo",columnDefinition = "serial2")
    private Integer idOrgano;

    @ManyToOne
    @JoinColumn(name = "n_id_sede", nullable = false, columnDefinition = "smallint")
    private Sede sede;

    @NotNull
    @NotBlank(message = "El organo debe tener un nombre.")
    @Column(name = "s_organo", length = 100)
    private String organo;

    public Integer getIdOrgano() {
        return idOrgano;
    }

    public void setIdOrgano(Integer idOrgano) {
        this.idOrgano = idOrgano;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public String getOrgano() {
        return organo;
    }

    public void setOrgano(String organo) {
        this.organo = organo;
    }
}
