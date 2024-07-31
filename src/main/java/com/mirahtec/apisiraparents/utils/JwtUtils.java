package com.mirahtec.apisiraparents.utils;

import com.mirahtec.apisiraparents.dao.impl.TokenBlacklistJDBCDaoImpl;
import com.mirahtec.apisiraparents.model.TokenBlacklist;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    @Value("${spring.app.jwtSecret}")
    private String jwtSecret;

    @Value("${spring.app.jwtExpirationMs}")
    private String jwtExpirationMs;


    @Autowired
    private TokenBlacklistJDBCDaoImpl tokenBlacklistJDBCDao;

    public boolean isTokenBlacklisted(String token) {
        return tokenBlacklistJDBCDao.existsByToken(token);
    }

    public String getJwtFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        logger.debug("Authorization Header: {}", bearerToken);
        if (bearerToken != null&&bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        else return null;

    }
    public String generateTokenFromUsername(UserDetails userDetails){
        String username = userDetails.getUsername();
        Calendar calendar =Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MILLISECOND, Integer.valueOf(jwtExpirationMs));
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .signWith(key())
                .expiration(calendar.getTime())
                .compact();
    }
    public String getUserNameFromJwtToken(String jwtToken){
        return Jwts.parser().verifyWith((SecretKey)key()).build().parseSignedClaims(jwtToken)
                .getPayload().getSubject();
    }
    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public boolean validateJwtToken(String authToken) {
        if (isTokenBlacklisted(authToken)) {
            logger.error("Token is blacklisted: {}", authToken); // Ensure logging the token is safe and compliant with your security policies
            return false;
        }

        try {
            Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(authToken);
            return true;
        } catch (MalformedJwtException | IllegalArgumentException | UnsupportedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        }
        return false;
    }

    //    feature
    @Scheduled(fixedRate = 86400000) // Example: once a day
    public void cleanupExpiredTokens() {
        List<TokenBlacklist> allTokens = tokenBlacklistJDBCDao.findAll();
        allTokens.forEach(token -> {
            if (tokenIsExpired(token.getToken())) {
                tokenBlacklistJDBCDao.delete(token);
            }
        });
    }

    private boolean tokenIsExpired(String token) {
        try {
            Date expiration = Jwts.parser().verifyWith((SecretKey)key()).build().parseSignedClaims(token).getPayload().getExpiration();
            return expiration.before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        } catch (Exception e) {
            logger.error("Error checking token expiration: {}", e.getMessage());
            return false;
        }
    }
}
