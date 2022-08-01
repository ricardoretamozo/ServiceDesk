package com.cmms.servicedesk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Incidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "n_id_incidencia")
    private Integer idIncidencia;

    @NotBlank(message = "La incidencia debe tener una descripcion")
    @Column(name = "s_descripcion", length = 400)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "n_id_origen", nullable = false)
    private OrigenIncidencia origen;

    @ManyToOne
    @JoinColumn(name = "n_id_persona", nullable = false)
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "n_id_oficina", nullable = true, columnDefinition = "smallint")
    private Oficina oficina;

    @ManyToOne
    @JoinColumn(name = "n_id_motivo", nullable = false)
    private Motivo motivo;

    @Transient
    private HistorialIncidencia historialIncidencia;

}
