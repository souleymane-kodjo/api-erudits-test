package com.mirahtec.apisiraparents.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table("eleves24")
public class Student implements Serializable {
    @Id
    @JsonIgnore
    private Long id;

    @Column("matricule")
    private int matricule;

    @Column("prenomEleve")
    private String prenomEleve;

    @Column("nomEleve")
    private String nomEleve;

    @Column("matriculeParent")
    private String matriculeParent ;


    @Column("idClasse")
    private Integer idClasse;

    @Column("sexe")
    @JsonIgnore
    private String sexe;

    @Column("dateNaissanceEleve")
    @JsonIgnore
    private Date dateNaissanceEleve;

    @Column("lieuN")
    @JsonIgnore
    private String lieuN;

    @Column("anneeScolaire")
    private String anneeScolaire;

    @Column("eps")
    @JsonIgnore
    private int eps;

//    @Column("code_localite")
//    private int codeLocalite;

    @Column("nom_classe")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nomClasse;
    public void setCodeLocalite(int codeLocalite) {

    }

    public String getMatriculeParent() {
        return matriculeParent;
    }
}