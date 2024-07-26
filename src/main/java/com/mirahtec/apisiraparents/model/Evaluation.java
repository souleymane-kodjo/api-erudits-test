// src/main/java/com/mirahtec/apisiraparents/model/Evaluation.java
package com.mirahtec.apisiraparents.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Evaluation {
    private long id;
    private long idEvaluation;
    private String nomEvaluation;
    private int idClasse;
    private String dateEvaluation;
    private String debut;
    private String fin;
    private String anneeScolaire;
    private String semestre;
    private String typeEvaluation;
    private String dateCreation;
    private long idCours;
    private String nomCours;
    private String bgColor;
    private String textColor;

    // Constructor
    public Evaluation() {
    }
    public Evaluation(long id, long idEvaluation, String nomEvaluation, int idClasse, String dateEvaluation, String debut, String fin, String anneeScolaire, String semestre, String typeEvaluation, String dateCreation, long idCours, String nomCours, String bgColor, String textColor) {
        this.id = id;
        this.idEvaluation = idEvaluation;
        this.nomEvaluation = nomEvaluation;
        this.idClasse = idClasse;

        this.dateEvaluation = dateEvaluation;
        this.debut = debut;
        this.fin = fin;
        this.anneeScolaire = anneeScolaire;
        this.semestre = semestre;
        this.typeEvaluation = typeEvaluation;
        this.dateCreation = dateCreation;
        this.idCours = idCours;
        this.nomCours = nomCours;
        this.bgColor = bgColor;
        this.textColor = textColor;
    }

}