package com.mirahtec.apisiraparents.service.AuthService;

import com.mirahtec.apisiraparents.dao.auth.UserParentJDBCDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserParentService {
    @Autowired
    private UserParentJDBCDaoImpl userParentJDBCDao;
}
