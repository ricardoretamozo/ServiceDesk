package com.cmms.servicedesk.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class SoporteTecnicoSede {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "n_id_soporte_tecnico_sede")
    private Integer idSoporteTecnicoSede;

    @ManyToOne
    @JoinColumn(name = "n_id_sede", nullable = false, columnDefinition = "smallint")
    private Sede sede;

    @ManyToOne
    @JoinColumn(name = "n_id_organo", nullable = false, columnDefinition = "smallint")
    private Organo organo;

    @ManyToOne
    @JoinColumn(name = "n_id_oficina", nullable = true, columnDefinition = "smallint")
    private Oficina oficina;

    @ManyToOne
    @JoinColumn(name = "n_id_persona", nullable = false)
    private Persona persona;

    @Column(name = "fh_fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "s_ip_pc",nullable = false)
    private String IP;

    public Integer getIdSoporteTecnicoSede() {
        return idSoporteTecnicoSede;
    }

    public void setIdSoporteTecnicoSede(Integer idSoporteTecnicoSede) {
        this.idSoporteTecnicoSede = idSoporteTecnicoSede;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public Organo getOrgano() {
        return organo;
    }

    public void setOrgano(Organo organo) {
        this.organo = organo;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SoporteTecnicoSede that = (SoporteTecnicoSede) o;
        return Objects.equals(idSoporteTecnicoSede, that.idSoporteTecnicoSede);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSoporteTecnicoSede);
    }
}
