package com.mirahtec.apisiraparents.dao;

public interface IAuthServiceDao {
    boolean authenticate(String username, String password);
    boolean logout(String username);
    boolean register(String username, String password);
    boolean changePassword(String username, String password);
    boolean resetPassword(String username, String password);
    boolean isAuth(String username);
}