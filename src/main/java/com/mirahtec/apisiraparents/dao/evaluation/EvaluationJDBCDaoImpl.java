package com.mirahtec.apisiraparents.dao.evaluation;

import com.mirahtec.apisiraparents.model.Evaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EvaluationJDBCDaoImpl implements IEvaluationDao {
    @Autowired
    private JdbcTemplate beanJDBCTemplate;

    @Override
    public List<Evaluation> getEvaluationsByStudentclassID(String classID) {
        String sql = "SELECT * FROM evaluations INNER JOIN cours ON evaluations.id_cours = cours.id_cours where evaluations.id_classe=? ORDER BY evaluations.id DESC";
        return beanJDBCTemplate.query(sql, new Object[]{classID}, new EvaluationRowMapper());
    }

    private static class EvaluationRowMapper implements RowMapper<Evaluation> {
        @Override
        public Evaluation mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Evaluation evaluation = new Evaluation();
                    evaluation.setId(rs.getLong("id"));
                    evaluation.setIdEvaluation(rs.getLong("id_evaluation"));
                    evaluation.setNomEvaluation(rs.getString("nom_evaluation"));
                    evaluation.setIdClasse(rs.getInt("id_classe"));
                    evaluation.setIdCours(rs.getInt("id_cours"));
                    evaluation.setDebut(rs.getString("debut"));
                    evaluation.setDateCreation(rs.getString("date_creation"));
                    evaluation.setDateEvaluation(rs.getString("date_evaluation"));
                    evaluation.setFin(rs.getString("fin"));
                    evaluation.setAnneeScolaire(rs.getString("annee_scolaire"));
                    evaluation.setSemestre(rs.getString("semestre"));
                    evaluation.setTypeEvaluation(rs.getString("type_evaluation"));
                    evaluation.setNomCours(rs.getString("nom_cours"));
                    evaluation.setBgColor(rs.getString("bgColor"));
                    evaluation.setTextColor(rs.getString("textColor"));


                    return evaluation;
        }
    }
}
