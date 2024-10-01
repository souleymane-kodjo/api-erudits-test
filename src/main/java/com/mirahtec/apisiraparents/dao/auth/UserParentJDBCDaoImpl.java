package com.mirahtec.apisiraparents.dao.auth;

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
//    @Override
//    public AuthUser findByUsername(String telephone) {
//        String sql = "SELECT * FROM usersParents WHERE telephone = ?  ORDER BY id DESC LIMIT 1";
//        AuthUser authUser = beanJDBCTemplate.queryForObject(sql, new Object[]{telephone}, new BeanPropertyRowMapper<>(AuthUser.class));
//        log.info("User found: " + authUser);
//        return authUser;
//    }
    @Override
    public AuthUser findByUsername(String telephone) {
        String sql = "SELECT * FROM usersParents WHERE telephone = ?  ORDER BY id DESC LIMIT 1";
        AuthUser authUser = beanJDBCTemplate.queryForObject(sql, new Object[]{telephone}, new BeanPropertyRowMapper<>(AuthUser.class));
        log.info("User found: " + authUser);
        return authUser;
    }
    @Override
    public AuthUser create(AuthUser authUser) {
        String sql = "INSERT INTO usersParents (prenom, nom, telephone, email, password, role) VALUES (?, ?, ?, ?, ?, ?)";
        int rowsAffected = beanJDBCTemplate.update(sql, authUser.getPrenom(), authUser.getNom(), authUser.getTelephone(), authUser.getEmail(), authUser.getPassword(), authUser.getRole());
        if(rowsAffected > 0) {
            return authUser;
        } else {
            return null; // Or handle this case as per your application's requirements
        }
    }
    @Override
    public void save(AuthUser adminUser) {
        String sql = "INSERT INTO usersParents (prenom, nom, telephone, email, password) VALUES (?, ?, ?, ?, ?)";
        int rowsAffected = beanJDBCTemplate.update(sql, adminUser.getPrenom(), adminUser.getNom(), adminUser.getTelephone(), adminUser.getEmail(), adminUser.getPassword());
    }
    @Override
    public boolean updateUser(AuthUser user) throws Exception {

        if (user.getId() == null) {
            log.error("User id is null");
            return false;
        }else {
            log.info("User from Dao : " + user);
        }
        String sql = "UPDATE usersParents SET prenom = ?, nom = ?, telephone = ?, email = ?, password = ?, isActived = ? WHERE id = ?";
        int rowsAffected = beanJDBCTemplate.update(sql, user.getPrenom(), user.getNom(), user.getTelephone(), user.getEmail(), user.getPassword(),user.getIsActived(), user.getId());
        if (rowsAffected > 0) {
            log.info("User updated successfully");
            return true;
        } else {
            log.error("Error updating user");
            return false;
        }
    }

    @Override
    public boolean updateUserPassWord(AuthUser user) {
        if (user.getId() == null) {
            log.error("User id is null");
            return false;
        }else {
            log.info("User from Dao : " + user);
        }
        String sql = "UPDATE usersParents SET password = ? WHERE id = ?";
        int rowsAffected = beanJDBCTemplate.update(sql, user.getPassword(), user.getId());
        if (rowsAffected > 0) {
            log.info("User password updated successfully");
            return true;
        } else {
            log.error("Error updating user password");
            return false;
            }
    }
}
