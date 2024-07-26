package com.mirahtec.apisiraparents.dao.impl;

import com.mirahtec.apisiraparents.dao.IStudentDao;
import com.mirahtec.apisiraparents.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class StudentJDBCDaoImpl implements IStudentDao {
    @Autowired
    private JdbcTemplate beanJDBCTemplate  ;

    @Override
    public List<Student> findAll() {
        String sql = "SELECT * FROM eleves24";
        return beanJDBCTemplate.query(sql, (rs, rowNum) -> {
            Student student = new Student();
            student.setId(rs.getLong("id")); // Assuming there's an 'id' colum
            student.setMatricule(rs.getInt("matricule")); // Adjust field names as per your table
            student.setPrenomEleve(rs.getString("prenomEleve"));
            student.setNomEleve(rs.getString("nomEleve"));
            student.setIdClasse(rs.getInt("idClasse"));
            student.setSexe(rs.getString("sexe"));
            student.setDateNaissanceEleve(rs.getDate("dateNaissanceEleve"));
            student.setLieuN(rs.getString("lieuN"));
            student.setAnneeScolaire(rs.getString("anneeScolaire"));
            student.setEps(rs.getInt("eps"));
            student.setCodeLocalite(rs.getInt("code_localite"));
            return student;
        });
    }

    @Override
    public Student getStudentByMatricule(String matricule) {
        String sql = "SELECT * FROM eleves24 WHERE matricule = ?";
        try {
            return beanJDBCTemplate.queryForObject(sql, new Object[]{matricule}, (rs, rowNum) -> {
                Student student = new Student();
                student.setId(rs.getLong("id")); // Assuming there's an 'id' colum
                student.setMatricule(rs.getInt("matricule")); // Adjust field names as per your table
                student.setPrenomEleve(rs.getString("prenomEleve"));
                student.setNomEleve(rs.getString("nomEleve"));
                student.setIdClasse(rs.getInt("idClasse"));
                student.setSexe(rs.getString("sexe"));
                student.setDateNaissanceEleve(rs.getDate("dateNaissanceEleve"));
                student.setLieuN(rs.getString("lieuN"));
                student.setAnneeScolaire(rs.getString("anneeScolaire"));
                student.setEps(rs.getInt("eps"));
                student.setCodeLocalite(rs.getInt("code_localite"));
                return student;
            });
        } catch (EmptyResultDataAccessException e) {
            return null; // or throw a custom exception
        }
    }



}
