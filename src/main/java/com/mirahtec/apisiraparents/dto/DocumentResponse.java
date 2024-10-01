package com.mirahtec.apisiraparents.dto;

import lombok.Data;

@Data
public class DocumentResponse {
    private Long id;
    private String nom;
    private String description;
    private String link;
    private String matricule;
    private String dateSaisie;
    private int idClasse;
    private String nomClasse;
    private String type;
    private String anneScolaire;
}
