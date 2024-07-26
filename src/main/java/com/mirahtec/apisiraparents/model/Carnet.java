package com.mirahtec.apisiraparents.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Carnet implements Serializable {
    String matricule;
    String date;
    String notes ;
    String prenom ;
    String nom ;
    String role ;
}
