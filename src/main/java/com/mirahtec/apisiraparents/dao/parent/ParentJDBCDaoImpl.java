package com.mirahtec.apisiraparents.dao.parent;

import com.mirahtec.apisiraparents.model.Parent;
import com.mirahtec.apisiraparents.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Slf4j
public class ParentJDBCDaoImpl implements IParentDao {
    @Autowired
    private JdbcTemplate beanJDBCTemplate  ;

    @Override
    public List<Student> findStudentsByParentUsername(String username) {
        String sql = "SELECT eleves25.matricule,eleves25.idClasse ,eleves25.nomEleve, eleves25.prenomEleve, eleves25.dateNaissanceEleve as ddn, eleves25.lieuN as ldn, classes.nom_classe FROM  parents  INNER JOIN eleves25 ON eleves25.matricule=parents.matriculeEleve INNER JOIN classes ON eleves25.idClasse=classes.idClasse where parents.telephone = ?";
        List<Student> students = beanJDBCTemplate.query(sql, new Object[]{username}, new BeanPropertyRowMapper<>(Student.class));
        return students;
    }

    @Override
    public Parent findByUsername(String username) {
        String sql = "SELECT * FROM parents WHERE telephone = ?  ORDER BY id DESC LIMIT 1";
        Parent parent =  beanJDBCTemplate.queryForObject(sql, new Object[]{username}, new BeanPropertyRowMapper<>(Parent.class));
        log.info("Parent found: " + parent);
        return parent;
    }

    public List<Parent> findAll() {
        String sql = "SELECT * FROM parents";
        List<Parent> parents = beanJDBCTemplate.query(sql, new BeanPropertyRowMapper<>(Parent.class));
        return parents;
    }
    @Override
    public List<Parent> findAll2(int page, int size) {
        String sql = "SELECT * FROM parents LIMIT ? OFFSET ?";
        List<Parent> parents = beanJDBCTemplate.query(sql, new Object[]{size, (page - 1) * size}, new BeanPropertyRowMapper<>(Parent.class));
        return parents;
    }

    public String findMatriculeParentByUsername(String parentUsername) {
        String sql = "SELECT matriculeParent FROM parents WHERE telephone = ? limit 1";
        return beanJDBCTemplate.queryForObject(sql, new Object[]{parentUsername}, String.class);
    }
}
