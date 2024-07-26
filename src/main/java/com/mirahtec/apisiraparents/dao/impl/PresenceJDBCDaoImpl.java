package com.mirahtec.apisiraparents.dao.impl;

import com.mirahtec.apisiraparents.model.Presence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class PresenceJDBCDaoImpl {
    @Autowired
    private JdbcTemplate beanJDBCTemplate;

    public List<Presence> getPresencesByMatricule(String matricule) {
        String sql2 = "SELECT DISTINCT presences.id ,presences.matricule,presences.id_classe, presences.id_cours, presences.date,presences.presence,presences.date_saisie,classes.nom_classe,cours.nom_cours FROM presences INNER JOIN classes ON presences.id_classe = classes.idClasse INNER JOIN Cours ON presences.id_cours = cours.id_cours WHERE presences.matricule = ?";
        return beanJDBCTemplate.query(sql2, new Object[]{matricule}, new RowMapper<Presence>() {
            @Override
            public Presence mapRow(ResultSet rs, int rowNum) throws SQLException {
                Presence presence = new Presence();
                presence.setId(rs.getLong("id"));
                presence.setMatricule(rs.getString("matricule"));
                presence.setIdClasse(rs.getLong("id_classe"));
                presence.setIdCours(rs.getLong("id_cours"));
                //presence.setCours(rs.getString("nom_cours"));
                //presence.setClasse(rs.getString("nom_classe") ) ;
                presence.setDate(rs.getDate("date").toLocalDate());
                presence.setPresence(rs.getString("presence"));
                presence.setDateSaisie(rs.getTimestamp("date_saisie").toLocalDateTime());
                return presence;
            }
        });
    }
    // Presences niveau PreScolaire , table = presences_el
    public List<Presence> getPresencesPreScolaireByMatricule(String matricule) {
        String sql = "SELECT * FROM presences_p_e WHERE matricule = ?";
        return beanJDBCTemplate.query(sql, new Object[]{matricule}, new RowMapper<Presence>() {
            @Override
            public Presence mapRow(ResultSet rs, int rowNum) throws SQLException {
                Presence presence = new Presence();
                presence.setId(rs.getLong("id"));
                presence.setMatricule(rs.getString("matricule"));
                presence.setIdClasse(rs.getLong("id_classe"));
                presence.setIdCours(rs.getLong("id_cours"));
                presence.setCours(rs.getString("nom_cours"));
                presence.setClasse(rs.getString("nom_classe") ) ;
                presence.setDate(rs.getDate("date").toLocalDate());
                presence.setPresence(rs.getString("presence"));
                presence.setDateSaisie(rs.getTimestamp("date_saisie").toLocalDateTime());
                return presence;
            }
        });
    }

    //Prenseces niveau Elementaire , table = presences_el
    public List<Presence> getPresencesElementaireByMatricule(String matricule) {
        String sql = "SELECT * FROM presences_p_e WHERE matricule = ?";
        return beanJDBCTemplate.query(sql, new Object[]{matricule}, new RowMapper<Presence>() {
            @Override
            public Presence mapRow(ResultSet rs, int rowNum) throws SQLException {
                Presence presence = new Presence();
                presence.setId(rs.getLong("id"));
                presence.setMatricule(rs.getString("matricule"));
                presence.setIdClasse(rs.getLong("id_classe"));
                presence.setCours(rs.getString("nom_cours"));
                presence.setClasse(rs.getString("nom_classe") ) ;
                presence.setIdCours(rs.getLong("id_cours"));
                presence.setDate(rs.getDate("date").toLocalDate());
                presence.setPresence(rs.getString("presence"));
                presence.setDateSaisie(rs.getTimestamp("date_saisie").toLocalDateTime());
                return presence;
            }
        });
    }
    // Prenseces niveau Moyen, table = presences
    public List<Presence> getPresencesMoyenByMatricule(String matricule) {
        String sql = "SELECT * FROM presences WHERE matricule = ?";
        String sql2 = "SELECT DISTINCT presences.id ,presences.matricule,presences.id_classe, presences.id_cours, presences.date,presences.presence,presences.date_saisie,classes.nom_classe,cours.nom_cours FROM presences INNER JOIN classes ON presences.id_classe = classes.idClasse INNER JOIN Cours ON presences.id_cours = cours.id_cours WHERE presences.matricule = ?";
        return beanJDBCTemplate.query(sql2, new Object[]{matricule}, new RowMapper<Presence>() {
            @Override
            public Presence mapRow(ResultSet rs, int rowNum) throws SQLException {
                Presence presence = new Presence();
                presence.setId(rs.getLong("id"));
                presence.setMatricule(rs.getString("matricule"));
                presence.setIdClasse(rs.getLong("id_classe"));
                presence.setCours(rs.getString("nom_cours"));
                presence.setClasse(rs.getString("nom_classe") ) ;
                presence.setIdCours(rs.getLong("id_cours"));
                presence.setDate(rs.getDate("date").toLocalDate());
                presence.setPresence(rs.getString("presence"));
                presence.setDateSaisie(rs.getTimestamp("date_saisie").toLocalDateTime());
                return presence;
            }
        });
    }
    // Presences niveau Secondaire , table = presences
    public List<Presence> getPresencesSecondaireByMatricule(String matricule) {
        String sql = "SELECT * FROM presences WHERE matricule = ?";
        String sql2 = "SELECT DISTINCT presences.id ,presences.matricule,presences.id_classe, presences.id_cours, presences.date,presences.presence,presences.date_saisie,classes.nom_classe,cours.nom_cours FROM presences INNER JOIN classes ON presences.id_classe = classes.idClasse INNER JOIN Cours ON presences.id_cours = cours.id_cours WHERE presences.matricule = ?";
        return beanJDBCTemplate.query(sql2, new Object[]{matricule}, new RowMapper<Presence>() {
            @Override
            public Presence mapRow(ResultSet rs, int rowNum) throws SQLException {
                Presence presence = new Presence();
                presence.setId(rs.getLong("id"));
                presence.setMatricule(rs.getString("matricule"));
                presence.setIdClasse(rs.getLong("id_classe"));
                presence.setIdCours(rs.getLong("id_cours"));
                presence.setCours(rs.getString("nom_cours"));
                presence.setClasse(rs.getString("nom_classe") ) ;
                presence.setDate(rs.getDate("date").toLocalDate());
                presence.setPresence(rs.getString("presence"));
                presence.setDateSaisie(rs.getTimestamp("date_saisie").toLocalDateTime());
                return presence;
            }
        });
    }

}