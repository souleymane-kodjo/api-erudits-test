package com.mirahtec.apisiraparents.dao;

import com.mirahtec.apisiraparents.model.Document;

import java.util.List;

public interface IDocumentDao {

    public List<Document> getDocumentsByStudentMatricule(String matricule);
}
