package com.mirahtec.apisiraparents.dao;

import com.mirahtec.apisiraparents.model.AuthUser;

public interface IUserParentDao {
    // liste des étudiants dun parent by username

    AuthUser
    findByUsername(String username);

    void updateUser(AuthUser user);
}
