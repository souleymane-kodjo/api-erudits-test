package com.mirahtec.apisiraparents.service;

import com.mirahtec.apisiraparents.dao.impl.UserParentJDBCDaoImpl;
import com.mirahtec.apisiraparents.model.AuthUser;
import com.mirahtec.apisiraparents.configuration.UserDetailsAdapter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserParentJDBCDaoImpl userParentJDBCDao ;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetailsAdapter getUserDetails(String username){
        AuthUser user = userParentJDBCDao.findByUsername(username) ;
        log.info ("User service called: ");
        log.info ("User found: " + user);
         return new UserDetailsAdapter(user);
    }




    public AuthUser findByUsername(String username) {
        return userParentJDBCDao.findByUsername(username) ;
    }
}
