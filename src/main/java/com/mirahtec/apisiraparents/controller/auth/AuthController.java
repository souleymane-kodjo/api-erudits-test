package com.mirahtec.apisiraparents.controller.auth;

import com.mirahtec.apisiraparents.dto.ChangePasswordRequest;
import com.mirahtec.apisiraparents.dto.LoginRequest;
import com.mirahtec.apisiraparents.dto.ResetPasswordRequest;
import com.mirahtec.apisiraparents.service.AuthService.IAuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private IAuthService authService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletRequest requestClient) {
        return authService.login(loginRequest, requestClient);
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
       return  authService.logout(request);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        return authService.changePassword(changePasswordRequest);
    }
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        return authService.resetPassword(resetPasswordRequest);
    }
}
