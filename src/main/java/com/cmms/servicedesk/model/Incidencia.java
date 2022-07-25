package com.cmms.servicedesk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

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

    @Column(name = "s_estado", nullable = false, columnDefinition = "char(1)", length = 1)
    private char estado;

    @Column(name = "f_sistema_registro", nullable = false)
    private LocalDate fecha;

    @Column(name = "s_ip_pc_registro", nullable = false)
    private String ip;

    @Column(name = "s_origen", nullable = false)
    private String origen;

    @ManyToOne
    @JoinColumn(name = "n_id_persona", nullable = false)
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "n_id_oficina", nullable = true, columnDefinition = "smallint")
    private Oficina oficina;

    @ManyToOne
    @JoinColumn(name = "n_id_persona_registro", nullable = false)
    private Persona persona_registro;

    @ManyToOne
    @JoinColumn(name = "n_id_persona_asignado", nullable = true)
    private Persona persona_asignado;

    @ManyToOne
    @JoinColumn(name = "n_id_motivo", nullable = false)
    private Motivo motivo;
}
