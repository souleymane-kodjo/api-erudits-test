package com.mirahtec.apisiraparents.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data

public class Presence implements Serializable {
    @Id
    private Long id;

    @Column("matricule")
    private String matricule;

    @Column("id_classe")
    private Long idClasse;

    @Column("id_cours")
    private Long idCours;
    @Column("classe")
    private String classe;
    @Column("cours")
    private String cours;

    @Column("date")
    private LocalDate date;

    @Column("presence")
    private String presence;

    @Column("date_saisie")
    private LocalDateTime dateSaisie;
}