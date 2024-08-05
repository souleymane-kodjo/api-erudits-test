package com.mirahtec.apisiraparents.model;

import lombok.Data;

import java.io.Serializable;
@Data
public class ReportCard implements Serializable {
    private Long id;
    private String matricule;
    private String type;
    private String date ;
    private String pj;
    private String publie;
    private String nom ;

    public String getNom() {
        return "Fichier Demo";
    }
}
