package com.mirahtec.apisiraparents.model;

import lombok.Data;

import java.io.Serializable;
@Data
public class Note implements Serializable {
    String nom_cours;
    String nom_evaluation;
    String date_evaluation;
    String note;
}
