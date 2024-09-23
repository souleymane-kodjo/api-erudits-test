package com.mirahtec.apisiraparents.model;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("class")
@Data
public class Class {
    //idClasse , code_classe, nom_classe idNiveau
    @Column("idClasse")
    private int idClasse;
    @Column("code_classe")
    private String codeClasse;
    @Column("nom_classe")
    private String nomClasse;
    @Column("idNiveau")
    private int idNiveau;
    @Column("idSchool")
    private int idSchool;
}
