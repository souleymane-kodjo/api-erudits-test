package com.mirahtec.apisiraparents.dao.student;

import com.mirahtec.apisiraparents.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Slf4j
public class StudentJDBCDaoImpl implements IStudentDao {
    @Autowired
    private JdbcTemplate beanJDBCTemplate  ;
    @Value("${eleves.table.name}")
    private String elevesTable;

    @Override
    public List<Student> findAll() {
        String sql = "SELECT * FROM " + elevesTable ;
        return beanJDBCTemplate.query(sql, (rs, rowNum) -> {
            Student student = new Student();
//            student.setId(rs.getLong("id")); // Assuming there's an 'id' colum
            student.setMatricule(rs.getInt("matricule")); // Adjust field names as per your table
            student.setPrenomEleve(rs.getString("prenomEleve"));
            student.setNomEleve(rs.getString("nomEleve"));
            student.setIdClasse(rs.getInt("idClasse"));
            student.setSexe(rs.getString("sexe"));
            student.setDateNaissanceEleve(rs.getDate("dateNaissanceEleve"));
            student.setLieuN(rs.getString("lieuN"));
            student.setAnneeScolaire(rs.getString("anneeScolaire"));
            student.setEps(rs.getInt("eps"));
            //student.setCodeLocalite(rs.getInt("code_localite"));
            //student.setMatriculeParent(rs.getString("matriculeParent"));
            log.info(student.toString());
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

    public List<Student> findStudentsByParentUsername(String username) {
        String sql = "SELECT eleves23.matricule,eleves23.idClasse ,eleves23.nomEleve, eleves23.prenomEleve, eleves23.dateNaissanceEleve as ddn, eleves23.lieuN as ldn, classes.nom_classe FROM  parents  INNER JOIN eleves23 ON eleves23.matricule=parents.matriculeEleve INNER JOIN classes ON eleves23.idClasse=classes.idClasse where parents.telephone = ?";
        List<Student> students = beanJDBCTemplate.query(sql, new Object[]{username}, new BeanPropertyRowMapper<>(Student.class));
        return students;
    }
    public List<Student> findStudentsByMatriculeParent(String matriculeParent) {
        String sql = "SELECT * FROM eleves24 WHERE matriculeParent = ?";
        return beanJDBCTemplate.query(sql, new Object[]{matriculeParent}, (rs, rowNum) -> {
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
            student.setMatriculeParent(rs.getString("matriculeParent"));
//            student.setCodeLocalite(rs.getInt("code_localite"));
            return student;
        });
    }
}
