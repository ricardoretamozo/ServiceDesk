package com.cmms.servicedesk.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
@Entity
public class HistorialPersona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "n_id_historial_persona")
    private Integer idHistorialPersona;

    @ManyToOne
    @JoinColumn(name = "n_id_persona", nullable = false)
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "n_id_cargo", nullable = false, columnDefinition = "smallint")
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "n_id_oficina", nullable = false, columnDefinition = "smallint")
    private Oficina oficina;

    //@NotNull(message = "El campo iniciaCargo no puede ser null")
    @Column(name = "f_desde")
    private LocalDate iniciaCargo;

    //@NotNull(message = "El campo terminaCargo no puede ser null")
    @Column(name = "f_hasta", nullable = false)
    private LocalDate terminaCargo;

    @Column(name = "s_activo", nullable = false, columnDefinition = "char(1)", length = 1)
    private String activo;
    @ManyToOne
    @JoinColumn(name = "id_persona_logueado", nullable = false)
    private Persona idPersonaLogueado;

    @CreatedDate
    @Column(name = "fh_fecha", updatable = false)
    private Date fecha;

    @Column(name = "s_ip_pc", nullable = false)
    private String IP;

    public HistorialPersona() {
    }

    public Integer getIdHistorialPersona() {
        return idHistorialPersona;
    }

    public void setIdHistorialPersona(Integer idHistorialPersona) {
        this.idHistorialPersona = idHistorialPersona;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    public LocalDate getIniciaCargo() {
        return iniciaCargo;
    }

    public void setIniciaCargo(LocalDate iniciaCargo) {
        this.iniciaCargo = iniciaCargo;
    }

    public LocalDate getTerminaCargo() {
        return terminaCargo;
    }

    public void setTerminaCargo(LocalDate terminaCargo) {
        this.terminaCargo = terminaCargo;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Persona getIdPersonaLogueado() {
        return idPersonaLogueado;
    }

    public void setIdPersonaLogueado(Persona idPersonaLogueado) {
        this.idPersonaLogueado = idPersonaLogueado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }
}
