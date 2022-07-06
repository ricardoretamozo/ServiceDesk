package com.cmms.servicedesk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Motivo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "n_id_motivo")
    private Integer idMotivo;

    @Column(name = "s_motivo")
    private String motivo;
}
