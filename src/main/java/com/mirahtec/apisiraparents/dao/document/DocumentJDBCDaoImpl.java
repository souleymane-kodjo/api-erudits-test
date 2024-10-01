package com.mirahtec.apisiraparents.dao.document;

import com.mirahtec.apisiraparents.model.Document;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class DocumentJDBCDaoImpl implements IDocumentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate  ;

    @Override
    public List<Document> getQuittancesInscriptionByMatricule(String matricule) {
        String sql = "SELECT * FROM d_quittances_inscription WHERE matricule = ?";
        return jdbcTemplate.query(sql, new Object[]{matricule}, new BeanPropertyRowMapper<>(Document.class));
    }
    @Override
    public List<Document> getCertificatScolariteByMatricule(String matricule) {
        String sql = "SELECT * FROM d_certificat_scolarite WHERE matricule = ? ";
        return jdbcTemplate.query(sql, new Object[]{matricule}, new BeanPropertyRowMapper<>(Document.class));
    }
    @Override
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
    @Override
    public List<Document> getDocUploadedElevesByMatricule(String matricule) {
        String sql = "SELECT * FROM doc_uploaded_eleves WHERE matricule = ?";
        return jdbcTemplate.query(sql, new Object[]{matricule}, new BeanPropertyRowMapper<>(Document.class));
    }
    @Override
    public List<Document> getDocUploadedClassesByIdClasse(String idClasse) {
        try {
            String sql = "SELECT * FROM doc_uploaded_classes WHERE id_classe = ?";
            return jdbcTemplate.query(sql, new Object[]{idClasse}, (rs, rowNum) -> {
                Document documentClasse = new Document();
                documentClasse.setNomClasse(rs.getString("nom_classe"));
                documentClasse.setNom(rs.getString("doc_name"));
                documentClasse.setLink(rs.getString("link"));
                documentClasse.setDateSaisie(rs.getString("date_saisie"));
                documentClasse.setType("Documents publiés pour la classe");
                documentClasse.setIdClasse(rs.getInt("id"));
                return documentClasse;
            });

        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    @Override
    public List<Document> getDocUploadedEcole() {
        String sql = "SELECT * FROM doc_uploaded_ecole";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Document.class));
    }
    @Override
    public List<Document> getDocumentsByMatricule(String matricule) {
        //certificat de scolarite
        String sqlCertificatScolarite = "SELECT * FROM d_certificat_scolarite WHERE matricule = ? ";
        List<Document> certificatScolarite = jdbcTemplate.query(sqlCertificatScolarite, new Object[]{matricule},(rs, rowNum) -> {
            Document document = new Document();
            document.setMatricule(rs.getString("matricule"));
            document.setLink(rs.getString("link"));
            document.setDateSaisie(rs.getString("date_saisie"));
            document.setType("Certificat de scolarité");
            return document;
        });
        //quittance inscription
        String sqlQuittanceInscription = "SELECT * FROM d_quittances_inscription WHERE matricule = ?";
        List<Document> quittanceInscription = jdbcTemplate.query(sqlQuittanceInscription, new Object[]{matricule}, (rs, rowNum) -> {
            Document document = new Document();
            document.setMatricule(rs.getString("matricule"));
            document.setLink(rs.getString("link"));
            document.setDateSaisie(rs.getString("date_saisie"));
            document.setType("Quittance d'inscription");
            return document;
        });
        //echeancier
        String sqlEcheancier = "SELECT * FROM d_echeancier WHERE matricule = ?";
        List<Document> echeancier = jdbcTemplate.query(sqlEcheancier, new Object[]{matricule}, (rs, rowNum) -> {
            Document document = new Document();
            document.setMatricule(rs.getString("matricule"));
            document.setLink(rs.getString("link"));
            document.setDateSaisie(rs.getString("date_saisie"));
            document.setType("Echéancier");
            return document;
        });
        //Documents publiés
        String sqlDocUploadedEleves = "SELECT * FROM doc_uploaded_eleves WHERE matricule = ?";
        List<Document> docUploadedEleves = jdbcTemplate.query(sqlDocUploadedEleves, new Object[]{matricule}, (rs, rowNum) -> {
            Document document = new Document();
            document.setMatricule(rs.getString("matricule"));
            document.setLink(rs.getString("link"));
            document.setDateSaisie(rs.getString("date_saisie"));
            document.setType("Documents publiés");
            return document;
        });
        List<Document> documents = new ArrayList<>();
        documents.addAll(certificatScolarite);
        documents.addAll(quittanceInscription);
        documents.addAll(echeancier);
        documents.addAll(docUploadedEleves);
        return documents;
    }
    @Override
    public List<Document> getDocumentsByClasse(String idClasse)
    {
        String sql = "SELECT * FROM doc_uploaded_classes WHERE id_classe = ?";
       return jdbcTemplate.query(sql, new Object[]{idClasse}, (rs, rowNum) -> {
            Document documentClasse = new Document();
           documentClasse.setNomClasse(rs.getString("nom_classe"));
           log.info("nom_classe: "+rs.getString("nom_classe"));
            return documentClasse;
        });
    }
}