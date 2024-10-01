package com.mirahtec.apisiraparents.service.documentService;

import com.mirahtec.apisiraparents.dao.document.DocumentJDBCDaoImpl;
import com.mirahtec.apisiraparents.dto.DocumentResponse;
import com.mirahtec.apisiraparents.model.Document;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class DocumentService {
    @Autowired
    DocumentJDBCDaoImpl documentJDBCDao;

    @Value("${document.storage.location}")
    private String storageLocation;

    private Path rootLocation;

    @PostConstruct
    public void init() {
        this.rootLocation = Paths.get(storageLocation);
    }

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
            return ResponseEntity.badRequest().body("An error occurred while fetching documents");
        }
    }
    public List<Document> getDocUploadedClassesByIdClasse(String idClasse) {
        return documentJDBCDao.getDocUploadedClassesByIdClasse(idClasse);
    }

    public ResponseEntity<?> getDocUploadedEcole() {
        try {
            return ResponseEntity.ok(documentJDBCDao.getDocUploadedEcole());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("An error occurred while fetching documents");
        }
    }

    public List<Document> getDocumentsByMatricule(String matricule) {
        try {
             return   documentJDBCDao.getDocumentsByMatricule(matricule) ;
        }catch (Exception e){
            return null ;
        }
    }



    public byte[] downloadImage(String fileName) throws IOException {
    Path file = rootLocation.resolve(fileName);
    return Files.readAllBytes(file);
}

public ResponseEntity<?> getDocumentUrlByMatricule(String matricule, String documentName) {
    String baseDirectory = "src/main/resources/documents/";
    List<Document> documents = documentJDBCDao.getDocumentsByMatricule(matricule);
    for (Document document : documents) {
        if (document.getNom().equals(documentName)) {
            try {
                Path file = rootLocation.resolve(document.getLink());
                byte[] imageBytes = Files.readAllBytes(file);
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while reading the file");
            }
        }
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Document not found");
}
}
