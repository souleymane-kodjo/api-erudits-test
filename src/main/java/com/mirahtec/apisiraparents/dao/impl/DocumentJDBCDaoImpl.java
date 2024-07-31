package com.mirahtec.apisiraparents.dao.impl;

import com.mirahtec.apisiraparents.model.Document;
import com.mirahtec.apisiraparents.model.ReportCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DocumentJDBCDaoImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Document> getQuittancesInscriptionByMatricule(String matricule) {
        String sql = "SELECT * FROM d_quittances_inscription WHERE matricule = ?";
        return jdbcTemplate.query(sql, new Object[]{matricule}, new BeanPropertyRowMapper<>(Document.class));
    }

    public List<Document> getCertificatScolariteByMatricule(String matricule) {
        String sql = "SELECT * FROM d_certificat_scolarite WHERE matricule = ? ";
        return jdbcTemplate.query(sql, new Object[]{matricule}, new BeanPropertyRowMapper<>(Document.class));
    }

    public List<Document> getEcheancierByMatricule(String matricule) {
        String sql = "SELECT * FROM d_echeancier WHERE matricule = ?";
        return jdbcTemplate.query(sql, new Object[]{matricule}, new RowMapper<Document>() {
            @Override
            public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
                Document document = new Document();
                document.setMatricule(rs.getString("matricule"));
                document.setLink(rs.getString("link"));
                document.setDateSaisie(rs.getString("date_saisie"));
                document.setType("Calendrier des paiements");
                return document;
            }
        });

    }

    public List<Document> getDocUploadedElevesByMatricule(String matricule) {
        String sql = "SELECT * FROM doc_uploaded_eleves WHERE matricule = ?";
        return jdbcTemplate.query(sql, new Object[]{matricule}, new BeanPropertyRowMapper<>(Document.class));
    }

    public List<Document> getDocUploadedClassesByIdClasse(String idClasse) {
        String sql = "SELECT * FROM doc_uploaded_classes WHERE id_classe = ?";
        return jdbcTemplate.query(sql, new Object[]{idClasse}, new BeanPropertyRowMapper<>(Document.class));
    }

    public List<Document> getDocUploadedEcole() {
        String sql = "SELECT * FROM doc_uploaded_ecole";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Document.class));
    }
}