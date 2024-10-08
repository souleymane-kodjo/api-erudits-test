package com.mirahtec.apisiraparents.service.AuthService.impl;

import com.mirahtec.apisiraparents.dao.auth.IUserParentDao;
import com.mirahtec.apisiraparents.dto.*;
import com.mirahtec.apisiraparents.model.AuthUser;
import com.mirahtec.apisiraparents.model.JournalConnexion;
import com.mirahtec.apisiraparents.model.TokenBlacklist;
import com.mirahtec.apisiraparents.service.AuthService.IAuthService;
import com.mirahtec.apisiraparents.service.AuthService.TokenBlacklistService;
import com.mirahtec.apisiraparents.service.AuthService.UserParentService;
import com.mirahtec.apisiraparents.service.journalService.JournalConnexionService;
import com.mirahtec.apisiraparents.service.messageService.MessageSenderService;
import com.mirahtec.apisiraparents.utils.ParserString;
import com.mirahtec.apisiraparents.utils.SHA256PasswordEncoder;
import com.mirahtec.apisiraparents.utils.JwtUtils;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AuthService implements IAuthService {
    @Autowired
    private SHA256PasswordEncoder passwordEncoderSHA256;
    @Autowired
    private JwtUtils utils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IUserParentDao userParentJDBCDaoImpl;
    @Autowired
    private UserParentService userParentService;
    @Autowired
    private TokenBlacklistService tokenBlacklistService;
    @Autowired
    private JournalConnexionService journalConnexionService;
    @Override
    public ResponseEntity<?> logout(HttpServletRequest request) {
        try {
            String jwtToken = utils.getJwtFromHeader(request);
            if (jwtToken != null && utils.validateJwtToken(jwtToken)) {
                TokenBlacklist tokenBlacklist = new TokenBlacklist();
                tokenBlacklist.setToken(jwtToken);
                Instant now = Instant.now();
                tokenBlacklist.setTimestamp(now);
                tokenBlacklistService.addTokenToBlacklist(tokenBlacklist);
                SecurityContextHolder.clearContext();

                return ResponseEntity.ok(Map.of("message", "Successfully logged out"));
            } else {
                // Invalid token
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid token"));
            }
        } catch (Exception e) {
            //e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Internal server error"));
        }
    }
    @Override
    public ResponseEntity<?> changePassword(ChangePasswordRequest changePasswordRequest) {
        try{
        String username = changePasswordRequest.getUsername();
        if (username == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Username is required"));
        }
        if (changePasswordRequest == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Request body is required"));
        }
        if (changePasswordRequest.getOldPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Old password is required"));
        }
        if (changePasswordRequest.getNewPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "New password is required"));
        }
        if (changePasswordRequest.getOldPassword().equals(changePasswordRequest.getNewPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Old password and new password cannot be the same"));
        }
        String oldPassword = changePasswordRequest.getOldPassword();
        String newPassword = changePasswordRequest.getNewPassword();

        AuthUser user = userParentJDBCDaoImpl.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "User not found"));
        }
        boolean isPasswordMatch = passwordEncoderSHA256.matches(oldPassword, user.getPassword());
        if (!isPasswordMatch) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Old password is incorrect"));
        }

        String hashedNewPassword = passwordEncoderSHA256.encode(newPassword);
        user.setPassword(hashedNewPassword);
        user.setIsActived(true);
        userParentJDBCDaoImpl.updateUser(user);
        return ResponseEntity.ok(Map.of("message", "Password changed successfully"));
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Database error"));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Service unavailable"));
        }
    }

    public ResponseEntity<?> fallback(Throwable t) {
        return ResponseEntity.status(500).body("Service is temporarily unavailable. Please try again later.");
    }

    @Override
    @CircuitBreaker(name = "backendA", fallbackMethod = "fallback")
    @Retry(name = "backendA")
    @RateLimiter(name = "backendA")
    public ResponseEntity<?> login(LoginRequest loginRequest, HttpServletRequest requestClient) {
        try {
            //String username = ParserString.parseTelephone(loginRequest.getUsername());
            String username = loginRequest.getUsername();

            log.info("username: " + username);
            String password = loginRequest.getPassword();
            Authentication authentication;
            authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetail= (UserDetails) authentication.getPrincipal();
            String jwtToken = utils.generateTokenFromUsername(userDetail);
            List<String> roles = userDetail.getAuthorities().stream().map(
                    GrantedAuthority::getAuthority
            ).toList();
            //journal_connexion
            JournalConnexion journalConnexion = new JournalConnexion();
            journalConnexion.setLogin(username);
            journalConnexion.setDateConnexion(LocalDateTime.now());
            String clientIP = requestClient.getRemoteAddr();
            journalConnexion.setIpAddress(clientIP);
            journalConnexion.setTypeDevice("Mobile");
            journalConnexionService.addJournalConnexion(journalConnexion);
            LoginResponse response = new LoginResponse(jwtToken,roles,userDetail.getUsername());
            AuthUser userParent1 = userParentJDBCDaoImpl.findByUsername(username);
            response.setUser(userParent1);
            return ResponseEntity.ok(response);
        }
        catch (AuthenticationException e){
            //e.printStackTrace();
            Map<String, Object> map = new HashMap<>();
            map.put("status", false);
            map.put("message", "Mot de passe ou nom d'utilisateur incorrect");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            e.printStackTrace();
            Map<String, Object> map = new HashMap<>();
            map.put("status", false);
            map.put("message", "Service indisponible");
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public ResponseEntity<?> resetPassword(ResetPasswordRequest resetPasswordRequest) {
        if (resetPasswordRequest == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Erreur", "Erreur de requête"));
        }
        if (resetPasswordRequest.getUsername() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Erreur", "Nom d'utilisateur requis"));
        }
        log.info("resetPasswordRequest: " + resetPasswordRequest);
        try {
            log.info("userTelephone: " + resetPasswordRequest.getUsername());
            AuthUser user = userParentJDBCDaoImpl.findByUsername(resetPasswordRequest.getUsername());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Erreur", "Utilisateur non trouvé"));
            }
            log.info("user selectionner : " + user);
            String DefaultPassword = "mirahtec@2024";
            String DefaultPasswordHashed = passwordEncoderSHA256.encode(DefaultPassword);
            log.info("DefaultPasswordHashed: " + DefaultPasswordHashed);
            user.setPassword(DefaultPasswordHashed);
            user.setIsActived(false);
            var isUpdated = false;
            isUpdated  = userParentJDBCDaoImpl.updateUserPassWord(user);
            log.info("isUpdated: " + isUpdated);
            if (isUpdated) {
                MessageSenderService messageSenderService = new MessageSenderService();
                messageSenderService.sendSMS(user.getTelephone(), "\n Votre mot de passe a été réinitialisé avec succès.\n Votre nouveau mot de passe est: " + DefaultPassword);
                ChangePasswordResponse changePasswordResponse = new ChangePasswordResponse();
                changePasswordResponse.setMessage("Mot de passe réinitialisé avec succès");
                changePasswordResponse.setStatus(true);
                return ResponseEntity.ok(changePasswordResponse);
            } else {
                ChangePasswordResponse changePasswordResponse = new ChangePasswordResponse();
                changePasswordResponse.setMessage("Non utilisation ou mot de passe incorrect : ");
                changePasswordResponse.setStatus(true);
                return ResponseEntity.ok(changePasswordResponse);
            }
        }
        catch (DataAccessException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("erreur", "Erreur de base de données"));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("erreur", "Service indisponible"));
        }
    }
}
