package com.mirahtec.apisiraparents.service.AuthService;

import com.mirahtec.apisiraparents.dto.ChangePasswordRequest;
import com.mirahtec.apisiraparents.dto.LoginRequest;
import com.mirahtec.apisiraparents.dto.ResetPasswordRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    ResponseEntity<?> logout(HttpServletRequest request);

    ResponseEntity<?> changePassword(ChangePasswordRequest changePasswordRequest);

    ResponseEntity<?> login(LoginRequest loginRequest, HttpServletRequest requestClient);

    ResponseEntity<?> resetPassword(ResetPasswordRequest resetPasswordRequest);
}
