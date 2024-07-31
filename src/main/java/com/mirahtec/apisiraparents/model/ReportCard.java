package com.mirahtec.apisiraparents.model;

import lombok.Data;

import java.io.Serializable;
@Data
public class ReportCard implements Serializable {
    private Long id;
    private String matricule;
    private String type;
    private String date ;
    private String pj;
    private String publie;
    private String nom ;
    public String getPj() {
        return "https://demo-bucket-29072024.s3.us-east-1.amazonaws.com/documents/test-demo.pdf?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIA4LE5YEUAPBNU6SOC%2F20240729%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240729T101039Z&X-Amz-Expires=3600&X-Amz-SignedHeaders=host&X-Amz-Signature=e02ddae6bdf9a2fa01c67e29f1e9a36b652297752ee1681ba25c7326eb80362b";
    }
    public String getNom() {
        return "Fichier Demo";
    }
}
