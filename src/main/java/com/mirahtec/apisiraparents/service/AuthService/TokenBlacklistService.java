package com.mirahtec.apisiraparents.service;

import com.mirahtec.apisiraparents.dao.tokenBlackList.TokenBlacklistJDBCDaoImpl;
import com.mirahtec.apisiraparents.model.TokenBlacklist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenBlacklistService {
    @Autowired
    private TokenBlacklistJDBCDaoImpl tokenBlacklistJDBCDao;
    public void addTokenToBlacklist(TokenBlacklist token) {
        tokenBlacklistJDBCDao.addTokenToBlacklist(token);
    }

    public boolean isTokenBlacklisted(String token) {
        return false;
    }


}
