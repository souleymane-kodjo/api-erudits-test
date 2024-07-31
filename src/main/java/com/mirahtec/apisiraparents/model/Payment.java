package com.mirahtec.apisiraparents.model;

import lombok.Data;

import java.io.Serializable;
@Data
public class Payment implements Serializable {
    private long id ;
    private String matricule ;
    private String mois ;
    private String scolarite ;
    private String reliquat ;
    private String date_paiement;
    private String mode_paiement ;
    private String justificatif ;
    private Double total ;

}
