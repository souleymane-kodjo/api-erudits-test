package com.mirahtec.apisiraparents.service;

import com.mirahtec.apisiraparents.dao.impl.UserParentJDBCDaoImpl;
import com.mirahtec.apisiraparents.dto.*;
import com.mirahtec.apisiraparents.model.AuthUser;
import com.mirahtec.apisiraparents.model.JournalConnexion;
import com.mirahtec.apisiraparents.model.TokenBlacklist;
import com.mirahtec.apisiraparents.utils.SHA256PasswordEncoder;
import com.mirahtec.apisiraparents.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AuthService {
    @Autowired
    private SHA256PasswordEncoder passwordEncoderSHA256;
    @Autowired
    private JwtUtils utils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserParentJDBCDaoImpl userParentJDBCDaoImpl;
    @Autowired
    private UserParentService userParentService;
    @Autowired
    private TokenBlacklistService tokenBlacklistService;
    @Autowired
    private JournalConnexionService journalConnexionService;

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
//    public ResponseEntity<?> logout(HttpServletRequest request) {
//        String jwtToken = utils.getJwtFromHeader(request);
//        if (jwtToken != null && utils.validateJwtToken(jwtToken)) {
//            tokenBlacklistService.addTokenToBlacklist(jwtToken);
//            SecurityContextHolder.clearContext();
//            return ResponseEntity.ok(Map.of("message", "Successfully logged out"));
//        } else {
//            // Invalid token
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid token"));
//        }
//    }
    }

    public ResponseEntity<?> changePassword(ChangePasswordRequest changePasswordRequest) {
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
        log.info("***********************************************");
        log.info("User: {}", user);
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
    }

    public ResponseEntity<?> login(LoginRequest loginRequest, HttpServletRequest requestClient) {
        log.info("***********************************************");


        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        //String hashedPassword = HashUtil.hashSha256(password);
        String hashedPassword = password;

        Authentication authentication;
        try {
            //authentication = authenticationManager
            //       .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, hashedPassword));

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


            //Type device requestClient
            String typeDevice = requestClient.getHeader("User-Agent");
            if (typeDevice == null){
                typeDevice = "Unknown";
            }else{
                log.info("***********************************************");
                log.info("deviceType: "+typeDevice);
            }
            journalConnexion.setTypeDevice(typeDevice);

            journalConnexionService.addJournalConnexion(journalConnexion);
            LoginResponse response = new LoginResponse(jwtToken,roles,userDetail.getUsername());
            AuthUser userParent1 = userParentJDBCDaoImpl.findByUsername(username);
            response.setUser(userParent1);
            log.info("***********************************************");
            log.info("User authentified successfully");
            return ResponseEntity.ok(response);
        }
        catch (AuthenticationException e){
            //e.printStackTrace();
            Map<String, Object> map = new HashMap<>();
            map.put("status", false);
            map.put("message", "Username or password incorrect");
            log.info("***********************************************");
            log.info("Username or password incorrect");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
        //invalid token
        catch (Exception e){
            //e.printStackTrace();
            Map<String, Object> map = new HashMap<>();
            map.put("status", false);
            map.put("message", "Internal server error");
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> resetPassword(ResetPasswordRequest resetPasswordRequest) {
        if (resetPasswordRequest == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Request body is required"));
        }
        if (resetPasswordRequest.getUsername() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Username is required"));
        }
        try {
            String DefaultPassword = "Mirahtec@2024";
            String DefaultPasswordHashed = passwordEncoderSHA256.encode(DefaultPassword);
            AuthUser user = userParentJDBCDaoImpl.findByUsername(resetPasswordRequest.getUsername());
            user.setPassword(DefaultPasswordHashed);
            user.setIsActived(false);
            userParentJDBCDaoImpl.updateUser(user);
            ChangePasswordResponse changePasswordResponse = new ChangePasswordResponse();
            changePasswordResponse.setMessage("Password reset successfully  - User : " + resetPasswordRequest.getUsername());
            changePasswordResponse.setStatus(true);
            return ResponseEntity.ok(changePasswordResponse);
        }
        //
        //Sql exceptio DataAccessException
        catch (DataAccessException e){
            //e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Database error"));
        }
        catch (Exception e){
            //e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Internal server error"));
        }
    }
}
