package com.cmms.servicedesk.model;

import javax.persistence.*;

@Entity
@Table(name = "oficina_union_organo")
public class OficinaUnionOrgano {

    @Id
    @Column(name = "n_oficina_union_organo")
    private Integer idOUO;

    @ManyToOne
    @JoinColumn(name = "n_id_oficina", nullable = false)
    private Oficina oficina;

    @Column(name = "n_id_organo", nullable = false,columnDefinition = "serial2")
    private Integer idOrgano;

    public Integer getIdOUO() {
        return idOUO;
    }

    public void setIdOUO(Integer idOUO) {
        this.idOUO = idOUO;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    public Integer getIdOrgano() {
        return idOrgano;
    }

    public void setIdOrgano(Integer idOrgano) {
        this.idOrgano = idOrgano;
    }
}
