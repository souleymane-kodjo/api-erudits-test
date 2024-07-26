package com.mirahtec.apisiraparents.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class LoginResponse<T> {
    private int statusCode;
    private String message;
    private T user;
    @JsonIgnore
    private boolean isAuth;
    private String jwtToken;
//    private String role;
    private LocalDateTime timestamp = LocalDateTime.now();

    public LoginResponse(String jwtToken, List<String> roles, String username) {
        this.jwtToken = jwtToken;
//        this.role = role;
        this.user = null ;
        this.isAuth = true;
        this.statusCode = 200 ;
        this.message = "Login successful";
    }
}