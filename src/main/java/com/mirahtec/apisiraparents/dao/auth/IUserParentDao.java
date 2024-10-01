package com.mirahtec.apisiraparents.dao.auth;

import com.mirahtec.apisiraparents.model.AuthUser;

public interface IUserParentDao {
    // liste des étudiants dun parent by username
    AuthUser
    findByUsername(String username);

    AuthUser create(AuthUser authUser);

    void save(AuthUser adminUser);

    boolean updateUser(AuthUser user) throws Exception;

    boolean updateUserPassWord(AuthUser user);
}
