package com.cmms.servicedesk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoTecnico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "n_id_Estado_Tecnico")
    private Integer idHistorialPersona;

    @ManyToOne
    @JoinColumn(name = "n_id_persona", nullable = false , unique = true)
    private Persona persona;

    @Column(name = "s_activo", length = 1, columnDefinition = "char(1)")
    private char activo;
}
