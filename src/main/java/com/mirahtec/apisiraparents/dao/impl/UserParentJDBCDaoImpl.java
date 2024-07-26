package com.mirahtec.apisiraparents.dao.impl;

import com.mirahtec.apisiraparents.dao.IUserParentDao;
import com.mirahtec.apisiraparents.model.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserParentJDBCDaoImpl implements IUserParentDao {
    @Autowired
    private JdbcTemplate beanJDBCTemplate  ;
    @Override
    public AuthUser findByUsername(String username) {
        String sql = "SELECT * FROM usersParents WHERE telephone = ? LIMIT 1";
        AuthUser authUser = beanJDBCTemplate.queryForObject(sql, new Object[]{username}, new BeanPropertyRowMapper<>(AuthUser.class));
        return authUser;
    }
    public AuthUser create(AuthUser authUser) {
        String sql = "INSERT INTO usersParents (prenom, nom, telephone, email, password, role) VALUES (?, ?, ?, ?, ?, ?)";
        int rowsAffected = beanJDBCTemplate.update(sql, authUser.getPrenom(), authUser.getNom(), authUser.getTelephone(), authUser.getEmail(), authUser.getPassword(), authUser.getRole());
        if(rowsAffected > 0) {
            return authUser;
        } else {
            return null; // Or handle this case as per your application's requirements
        }
    }

    public void save(AuthUser adminUser) {
        String sql = "INSERT INTO usersParents (prenom, nom, telephone, email, password) VALUES (?, ?, ?, ?, ?)";
        int rowsAffected = beanJDBCTemplate.update(sql, adminUser.getPrenom(), adminUser.getNom(), adminUser.getTelephone(), adminUser.getEmail(), adminUser.getPassword());
        if(rowsAffected > 0) {
            log.info("Admin user created successfully");
        } else {
            log.error("Failed to create admin user");
        }
    }
    @Override
    public void updateUser(AuthUser user) {
        String sql = "UPDATE usersParents SET prenom = ?, nom = ?, telephone = ?, email = ?, password = ?, isActived = ? WHERE id = ?";
        int rowsAffected = beanJDBCTemplate.update(sql, user.getPrenom(), user.getNom(), user.getTelephone(), user.getEmail(), user.getPassword(),user.getIsActived(), user.getId());
        if(rowsAffected > 0) {
            log.info("User updated successfully");
        } else {
            log.error("Failed to update user");
        }
    }
}
