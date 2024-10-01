package com.mirahtec.apisiraparents.dao.impl;

import com.mirahtec.apisiraparents.dao.IParentDao;
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
        String sql = "SELECT eleves23.matricule,eleves23.idClasse ,eleves23.nomEleve, eleves23.prenomEleve, eleves23.dateNaissanceEleve as ddn, eleves23.lieuN as ldn, classes.nom_classe FROM  parents  INNER JOIN eleves23 ON eleves23.matricule=parents.matriculeEleve INNER JOIN classes ON eleves23.idClasse=classes.idClasse where parents.telephone = ?";
        List<Student> students = beanJDBCTemplate.query(sql, new Object[]{username}, new BeanPropertyRowMapper<>(Student.class));
        return students;
    }

    @Override
    public Parent findByUsername(String username) {
        String sql = "SELECT * FROM parents WHERE telephone = ? LIMIT 1";
        Parent parent = beanJDBCTemplate.queryForObject(sql, new Object[]{username}, new BeanPropertyRowMapper<>(Parent.class));
        return parent;
    }

    public List<Parent> findAll() {
        String sql = "SELECT * FROM parents";
        List<Parent> parents = beanJDBCTemplate.query(sql, new BeanPropertyRowMapper<>(Parent.class));
        return parents;
    }

    public List<Parent> findAll2(int page, int size) {
        String sql = "SELECT * FROM parents LIMIT ? OFFSET ?";
        List<Parent> parents = beanJDBCTemplate.query(sql, new Object[]{size, (page - 1) * size}, new BeanPropertyRowMapper<>(Parent.class));
        return parents;
    }
}
