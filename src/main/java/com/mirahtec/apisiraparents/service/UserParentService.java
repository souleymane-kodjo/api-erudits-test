package com.mirahtec.apisiraparents.service;

import com.mirahtec.apisiraparents.dao.impl.UserParentJDBCDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserParentService {
    @Autowired
    private UserParentJDBCDaoImpl userParentJDBCDao;
}
