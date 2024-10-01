package com.mirahtec.apisiraparents.dao.document;

import com.mirahtec.apisiraparents.model.Document;

import java.util.List;

public interface IDocumentDao {

    List<Document> getQuittancesInscriptionByMatricule(String matricule);

    List<Document> getCertificatScolariteByMatricule(String matricule);

    List<Document> getEcheancierByMatricule(String matricule);

    List<Document> getDocUploadedElevesByMatricule(String matricule);

    List<Document> getDocUploadedClassesByIdClasse(String idClasse);

    List<Document> getDocUploadedEcole();

    List<Document> getDocumentsByMatricule(String matricule);

    List<Document> getDocumentsByClasse(String idClasse);
}
