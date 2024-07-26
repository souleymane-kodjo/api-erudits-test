package com.mirahtec.apisiraparents.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TimeTable implements Serializable {
    private int idClasse;
    private int codeClasse;
    private String nomClasse;
    private int idNiveau;
    private int id;
    private int idClasseTable; // Pour différencier de idClasse
    private String jour;
    private int idCours;
    private String debutCours;
    private String finCours;
    private int matriculeProf;
    private int idCoursTable; // Pour différencier de idCours
    private String nomCours;
    private int idProf; // Pour différencier de l'autre id
    private int matriculeProfesseur; // Pour différencier de matriculeProf
    private String prenom;
    private String nom;
    private String matiere;
    private String telephone;
    private String email;
    private String adresse;
}


