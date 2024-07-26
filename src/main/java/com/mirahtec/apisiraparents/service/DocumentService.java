package com.mirahtec.apisiraparents.service;

import com.mirahtec.apisiraparents.dao.impl.DocumentJDBCDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    @Autowired
    DocumentJDBCDaoImpl documentJDBCDao;
    public ResponseEntity<?> getDocumentsByStudentMatricule(String matricule) {
        return null;
    }
}
