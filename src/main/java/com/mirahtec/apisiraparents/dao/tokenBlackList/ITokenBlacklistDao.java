package com.mirahtec.apisiraparents.dao;

import com.mirahtec.apisiraparents.model.TokenBlacklist;

public interface ITokenBlacklistDao {
    boolean existsByToken(String token);

    void delete(TokenBlacklist token);

    void addTokenToBlacklist(TokenBlacklist token);
}
