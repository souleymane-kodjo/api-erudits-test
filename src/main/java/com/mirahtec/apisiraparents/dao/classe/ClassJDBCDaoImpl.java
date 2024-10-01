package com.mirahtec.apisiraparents.dao.impl;

import com.mirahtec.apisiraparents.dao.IClassDao;
import com.mirahtec.apisiraparents.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.mirahtec.apisiraparents.model.Class;

import java.sql.SQLException;
import java.util.List;

@Component
@Slf4j
public class ClassJDBCDaoImpl implements IClassDao {
    @Autowired
    private JdbcTemplate beanJDBCTemplate  ;

    @Override
    public List<Class> getAllClasses()   {
        String sql = "SELECT * FROM classes";
        try {
            List<Class> classes = beanJDBCTemplate.query(sql, new BeanPropertyRowMapper<>(Class.class));
            return classes;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Class getClassById(int id) {
        String sql = "SELECT * FROM classes WHERE idClasse = ?";
        Class classe = beanJDBCTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Class.class));
        return classe;
    }
}
