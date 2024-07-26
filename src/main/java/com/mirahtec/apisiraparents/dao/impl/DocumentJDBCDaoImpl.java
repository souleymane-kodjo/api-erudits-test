package com.mirahtec.apisiraparents.dao.impl;

import com.mirahtec.apisiraparents.dao.IDocumentDao;
import com.mirahtec.apisiraparents.model.Document;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocumentJDBCDaoImpl implements IDocumentDao {
    @Override
    public List<Document> getDocumentsByStudentMatricule(String matricule) {
        return List.of();
    }
}
