package com.mirahtec.apisiraparents.dao.impl;

import com.mirahtec.apisiraparents.dao.INiveauDao;
import com.mirahtec.apisiraparents.model.Niveau;
import jakarta.persistence.Access;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NiveauJDBCDaoImpl implements INiveauDao {
    @Autowired
    private JdbcTemplate beanJDBCTemplate;
    @Override
    public Niveau getNiveauByIdNiveau(int idNiveau) {
        try {
            String sql = "SELECT * FROM niveau WHERE idNiveau = ?";
            return beanJDBCTemplate.queryForObject(sql, new Object[]{idNiveau}, new BeanPropertyRowMapper<>(Niveau.class));
        } catch (EmptyResultDataAccessException e) {
            return null; // or throw a custom exception
        }
    }
}
