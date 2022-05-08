package com.cmms.servicedesk.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class PerfilPersona {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    @Column(name = "n_id_perfil",columnDefinition = "serial2")
    private Integer idPerfilPersona;

    @NotNull
    @Column(name = "s_perfil", length = 15)
    private String perfil;

    @NotBlank(message = "Debe indicar la descripcion")
    @Column(name = "s_descripcion" , length = 120)
    private String descripcion;

    public Integer getIdPerfilPersona() {
        return idPerfilPersona;
    }

    public void setIdPerfilPersona(Integer idPerfilPersona) {
        this.idPerfilPersona = idPerfilPersona;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
