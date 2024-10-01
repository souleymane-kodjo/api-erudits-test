package com.mirahtec.apisiraparents.dao.note;

import com.mirahtec.apisiraparents.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class NoteJDBCDaoImpl implements INoteDao {
    @Autowired
    private JdbcTemplate beanJDBCTemplate  ;
    @Override
    public List<Note> getNotesByStudentMatricule(String matricule) {
        try {
            String sql = "SELECT cours.nom_cours, evaluations.nom_evaluation, notes.note, evaluations.date_evaluation FROM notes INNER JOIN evaluations ON notes.id_evaluation = evaluations.id_evaluation INNER JOIN cours ON cours.id_cours=notes.id_cours WHERE notes.matricule_eleve=?";
            return beanJDBCTemplate.query(sql, new Object[]{matricule}, new NoteJDBCDaoImpl.NoteRowMapper());
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private static class NoteRowMapper implements RowMapper<Note> {
        @Override
        public Note mapRow(ResultSet rs, int rowNum) throws SQLException {
            Note note = new Note();
            note.setNom_cours(rs.getString("nom_cours"));
            note.setNom_evaluation(rs.getString("nom_evaluation"));
            note.setNote(rs.getString("note"));
            note.setDate_evaluation(rs.getString("date_evaluation"));
            return note;
        }
    }
}
