package com.mirahtec.apisiraparents.model;

import com.mirahtec.apisiraparents.utils.ParserString;
import lombok.Data;

import java.io.Serializable;
@Data
public class Document implements Serializable {
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

    public Document(long l, String link, String dateSaisie, String type) {
        this.id = l;
        this.link = link;
        this.dateSaisie = dateSaisie;
        this.type = type;
    }

    public Document() {
    }

    public String getNom() {
        return ParserString.parserFileName(link);
    }
    public String getAnneScolaire() {
        return "2022-2023";
    }
}
