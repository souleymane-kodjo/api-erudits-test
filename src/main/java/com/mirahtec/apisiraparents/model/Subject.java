package com.mirahtec.apisiraparents.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Subject implements Serializable {
    private Long id;
    private String horaire;
    private String matiere;
    private String icone;
    private String prof;
    private String textColor;
    private String bgColor;

    // Constructor
    public Subject() {
    }
    public Subject(Long id, String horaire, String matiere, String icone, String prof, String couleur, String textColor) {
        this.id = id;
        this.horaire = horaire;
        this.matiere = matiere;
        this.icone = icone;
        this.prof = prof;
        this.bgColor = couleur;
        this.textColor = textColor;
    }

}