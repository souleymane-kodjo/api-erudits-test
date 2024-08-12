package com.mirahtec.apisiraparents.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirahtec.apisiraparents.constant.EnumQuittanceType;
import com.mirahtec.apisiraparents.utils.ParserString;
import lombok.Data;

import java.io.Serializable;
@Data
public class Quittance implements Serializable {
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private String matricule;
    private String anne_scolaire;
    @JsonIgnore
    private String date_saisie;

    private String link;
    @JsonAlias("type")
    private String type;
    private String mois;

    public String getAnne_scolaire() {
        return "2023-2024";
    }
    public String getNom() {
        return ParserString.parserFileName(link);
    }




}
