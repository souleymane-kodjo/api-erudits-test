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
    //Get documents by student matricule
    @GetMapping("/student/{matricule}")
    public ResponseEntity<?> getDocumentsByStudentMatricule(@PathVariable String matricule) {
        if (matricule == null || matricule.isEmpty()) {
            return ResponseEntity.badRequest().body("Matricule cannot be empty");
        }
        return documentService.getDocumentsByStudentMatricule(matricule);
    }
}
