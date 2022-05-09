package com.cmms.servicedesk.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Oficina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "n_id_oficina")
    private Integer idOficina;

    @ManyToOne
    @JoinColumn(name = "n_id_organo", nullable = false,columnDefinition = "smallint")
    private OficinaUnionOrgano oficinaUnionOrgano;

    @NotBlank(message = "La oficina debe tener un nombre")
    @Column(name = "s_oficina", length = 120)
    private String oficina;

    public Integer getIdOficina() {
        return idOficina;
    }

    public void setIdOficina(Integer idOficina) {
        this.idOficina = idOficina;
    }

    public Organo getOrgano() {
        return organo;
    }

    public void setOrgano(Organo organo) {
        this.organo = organo;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }
}
