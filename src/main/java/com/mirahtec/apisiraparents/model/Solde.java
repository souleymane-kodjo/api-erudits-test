package com.mirahtec.apisiraparents.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Solde implements Serializable {
    private long id ;
    private String matricule ;
    private String mois ;
    private String scolarite ;
    private String reliquat ;
    private Double total ;

}
