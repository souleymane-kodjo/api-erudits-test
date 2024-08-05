package com.mirahtec.apisiraparents.service;

import com.mirahtec.apisiraparents.dao.impl.DocumentJDBCDaoImpl;
import com.mirahtec.apisiraparents.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {
    @Autowired
    DocumentJDBCDaoImpl documentJDBCDao;
//    getQuittancesInscriptionByMatricule
    public ResponseEntity<?> getQuittancesInscriptionByMatricule(String matricule) {
        try {
            if (matricule == null || matricule.isEmpty()) {
                return ResponseEntity.badRequest().body("Matricule cannot be empty");
            }
            return ResponseEntity.ok(documentJDBCDao.getQuittancesInscriptionByMatricule(matricule));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("An error occurred while fetching documents");
        }
    }
    public ResponseEntity<?> getCertificatScolariteByMatricule(String matricule) {
        try {
            if (matricule == null || matricule.isEmpty()) {
                return ResponseEntity.badRequest().body("Matricule cannot be empty");
            }
            return ResponseEntity.ok(documentJDBCDao.getCertificatScolariteByMatricule(matricule));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("An error occurred while fetching documents");
        }
    }
//getEcheancierByMatricule
    public ResponseEntity<?> getEcheancierByMatricule(String matricule) {
        try {
            if (matricule == null || matricule.isEmpty()) {
                return ResponseEntity.badRequest().body("Matricule cannot be empty");
            }
            return ResponseEntity.ok(documentJDBCDao.getEcheancierByMatricule(matricule));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("An error occurred while fetching documents");
        }
    }

    public ResponseEntity<?> getDocUploadedElevesByMatricule(String matricule) {
        try {
            if (matricule == null || matricule.isEmpty()) {
                return ResponseEntity.badRequest().body("Matricule cannot be empty");
            }
            return ResponseEntity.ok(documentJDBCDao.getDocUploadedElevesByMatricule(matricule));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("An error occurred while fetching documents");
        }
    }
//getDocUploadedClassesByIdClasse
    public List<Document> getDocUploadedClassesByIdClasse(String idClasse) {
        return documentJDBCDao.getDocUploadedClassesByIdClasse(idClasse);
    }
//getDocUploadedEcole
    public ResponseEntity<?> getDocUploadedEcole() {
        try {
            return ResponseEntity.ok(documentJDBCDao.getDocUploadedEcole());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("An error occurred while fetching documents");
        }
    }

    public ResponseEntity<?> getDocumentsByMatricule(String matricule) {
        try {
            if (matricule == null || matricule.isEmpty()) {
                return ResponseEntity.badRequest().body("Matricule cannot be empty");
            }
            return ResponseEntity.ok(documentJDBCDao.getDocumentsByMatricule(matricule));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("An error occurred while fetching documents");
        }
    }
}
