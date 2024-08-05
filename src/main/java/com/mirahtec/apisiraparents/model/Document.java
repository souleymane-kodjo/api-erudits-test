package com.mirahtec.apisiraparents.model;

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

    public String getLink() {
        return "https://demo-bucket-29072024.s3.us-east-1.amazonaws.com/documents/test-demo.pdf?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIA4LE5YEUAPBNU6SOC%2F20240729%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240729T143117Z&X-Amz-Expires=8600&X-Amz-SignedHeaders=host&X-Amz-Signature=c781285ed6b8fda03555f41baffdda21fffe0b2dedd3048b0520bddeb0500f4a";
    }
    public String getNom() {
        return "Fichier Demo";
    }
    public String getAnneScolaire() {
        return "2022-2023";
    }
}
