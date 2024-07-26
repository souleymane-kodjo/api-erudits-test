package com.mirahtec.apisiraparents.dao.impl;

import com.mirahtec.apisiraparents.dao.IJournalConnexion;
import com.mirahtec.apisiraparents.model.JournalConnexion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JournalConnexionJDBCDaoImpl implements IJournalConnexion {
    @Autowired
    private JdbcTemplate beanJDBCTemplate  ;
    @Override
    public void addJournalConnexion(JournalConnexion journalConnexion){
        String sql = "INSERT INTO journal_connexion ( login, dateConnexion, ip) VALUES (?, ?, ?)";
        beanJDBCTemplate.update(sql,journalConnexion.getLogin(), journalConnexion.getDateConnexion(), journalConnexion.getIpAddress());
    }
}
