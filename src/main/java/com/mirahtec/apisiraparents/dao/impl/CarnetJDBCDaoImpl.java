package com.mirahtec.apisiraparents.dao.impl;

import com.mirahtec.apisiraparents.dao.ICarnetDao;
import com.mirahtec.apisiraparents.model.Carnet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CarnetJDBCDaoImpl implements ICarnetDao {
    @Autowired
    private JdbcTemplate beanJDBCTemplate;

    @Override
    public List<Carnet> getCarnetsByStudentMatricule(String matricule) {
        try{
        String sql = "SELECT carnet.matricule, carnet.date, carnet.notes, users.prenom, users.nom, users.role FROM carnet INNER join users on carnet.auteur=users.email WHERE carnet.matricule=?";
        return beanJDBCTemplate.query(sql, new Object[]{matricule}, new BeanPropertyRowMapper<>(Carnet.class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
