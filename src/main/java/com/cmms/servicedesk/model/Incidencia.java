package com.cmms.servicedesk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

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

    @Column(name = "s_origen", nullable = false)
    private String origen;

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
