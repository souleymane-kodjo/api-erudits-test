package com.mirahtec.apisiraparents.dao.impl;

import com.mirahtec.apisiraparents.model.Quittance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class QuittanceJDBCDaoImpl {
    @Autowired
    private JdbcTemplate beanJDBCTemplate;

    public List<Quittance> getQuittancesByStudentMatricule(String matricule) {
        String sqlQuittanceInscription = "SELECT * FROM d_quittances_inscription WHERE matricule=?";
        List<Quittance> quittanceInscription = beanJDBCTemplate.query(sqlQuittanceInscription, new Object[]{matricule}, new RowMapper<Quittance>() {
            @Override
            public Quittance mapRow(ResultSet rs, int rowNum) throws SQLException {
                Quittance quittance = new Quittance();
                quittance.setMatricule(rs.getString("matricule"));
                quittance.setDate_saisie(rs.getString("date_saisie"));
                quittance.setLink(rs.getString("link"));
                quittance.setType("Inscription");
                return quittance;
            }
        });

        String sqlQuittancePaiement = "SELECT * FROM d_quittances_paiement WHERE matricule=?";
        List<Quittance> quittancePaiements = beanJDBCTemplate.query(sqlQuittancePaiement, new Object[]{matricule}, new RowMapper<Quittance>() {
            @Override
            public Quittance mapRow(ResultSet rs, int rowNum) throws SQLException {
                Quittance quittance = new Quittance();
                quittance.setMatricule(rs.getString("matricule"));
                quittance.setDate_saisie(rs.getString("date_saisie"));
                quittance.setLink(rs.getString("link"));
                quittance.setType("Paiement");
                quittance.setMois(rs.getString("mois"));
                return quittance;
            }
        });

        List<Quittance> quittances = new ArrayList<>();
        quittances.addAll(quittanceInscription);
        quittances.addAll(quittancePaiements);
        return quittances;
    }
}