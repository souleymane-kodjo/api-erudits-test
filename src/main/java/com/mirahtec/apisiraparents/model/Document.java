package com.mirahtec.apisiraparents.model;

import lombok.Data;

import java.io.Serializable;
@Data
public class Document implements Serializable {
    private Long id;
    private String name;
    private String description;
    private String link;
    private String matricule;
    private String dateSaisie;
    private int idClasse;
    private String nomClasse;
}
