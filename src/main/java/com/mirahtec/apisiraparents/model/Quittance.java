package com.mirahtec.apisiraparents.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mirahtec.apisiraparents.constant.EnumQuittanceType;
import com.mirahtec.apisiraparents.utils.ParserString;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serializable;
@Data
public class Quittance implements Serializable {
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private String matricule;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String anne_scolaire;
    @JsonIgnore
    private String date_saisie;
    private String link;
    @JsonAlias("type")
    private String type;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mois;
    public String getNom() {
        return ParserString.parserFileName(link);
    }


    public String link() {
        return "https://docs.google.com/viewerng/viewer?url=https://www.learningcontainer.com/download/sample-pdf-file-for-testing/?ind%3D0%26filename%3Dsample-pdf-file.pdf%26wpdmdl%3D1566%26refresh%3D66fa9c3f678931727700031%26open%3D1";
    }
    public void setLink(String link) {
        this.link = "https://docs.google.com/viewerng/viewer?url=https://www.learningcontainer.com/download/sample-pdf-file-for-testing/?ind%3D0%26filename%3Dsample-pdf-file.pdf%26wpdmdl%3D1566%26refresh%3D66fa9c3f678931727700031%26open%3D1";
    }
}
