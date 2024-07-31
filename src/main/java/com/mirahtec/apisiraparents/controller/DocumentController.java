package com.mirahtec.apisiraparents.controller;

import com.mirahtec.apisiraparents.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/documents")
public class DocumentController {
    @Autowired
    private DocumentService documentService;
    //getQuittancesInscriptionByMatricule
    @GetMapping("/quittances/student/{matricule}")
    public ResponseEntity<?> getQuittancesInscriptionByMatricule(@PathVariable String matricule) {
        if (matricule == null || matricule.isEmpty()) {
            return ResponseEntity.badRequest().body("Matricule cannot be empty");
        }
        return documentService.getQuittancesInscriptionByMatricule(matricule);
    }
    //getCertificatScolariteByMatricule
    @GetMapping("/certificat/student/{matricule}")
    public ResponseEntity<?> getCertificatScolariteByMatricule(@PathVariable String matricule) {
        return documentService.getCertificatScolariteByMatricule(matricule);
    }
    //getEcheancierByMatricule
    @GetMapping("/echeancier/student/{matricule}")
    public ResponseEntity<?> getEcheancierByMatricule(@PathVariable String matricule) {
        return documentService.getEcheancierByMatricule(matricule);
    }
    //getDocUploadedElevesByMatricule
    @GetMapping("/eleves/student/{matricule}")
    public ResponseEntity<?> getDocUploadedElevesByMatricule(@PathVariable String matricule) {
        return documentService.getDocUploadedElevesByMatricule(matricule);
    }
    //getDocUploadedClassesByIdClasse
    @GetMapping("/classes/{idClasse}")
    public ResponseEntity<?> getDocUploadedClassesByIdClasse(@PathVariable String idClasse) {
        return documentService.getDocUploadedClassesByIdClasse(idClasse);
    }
    //getDocUploadedEcole
    @GetMapping("/ecole")
    public ResponseEntity<?> getDocUploadedEcole() {
        return documentService.getDocUploadedEcole();
    }
}
