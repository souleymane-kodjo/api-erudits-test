package com.mirahtec.apisiraparents.service;

import com.mirahtec.apisiraparents.dao.journalConnexion.JournalConnexionJDBCDaoImpl;
import com.mirahtec.apisiraparents.model.JournalConnexion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JournalConnexionService {
    @Autowired
    private JournalConnexionJDBCDaoImpl journalConnexionJDBCDao;
    public void addJournalConnexion(JournalConnexion journalConnexion){
        journalConnexionJDBCDao.addJournalConnexion(journalConnexion);
    }
}
