package com.mirahtec.apisiraparents.dao.tokenBlackList;

import com.mirahtec.apisiraparents.model.TokenBlacklist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class TokenBlacklistJDBCDaoImpl implements ITokenBlacklistDao {
    @Autowired
    private JdbcTemplate beanJDBCTemplate  ;

    @Override
    public boolean existsByToken(String token) {
        String sql = "SELECT count(*) FROM token_blacklist WHERE token = ?";
        return beanJDBCTemplate.queryForObject(sql, new Object[]{token}, Integer.class) > 0;
    }

    @Override
    public void delete(TokenBlacklist token) {
        String sql = "DELETE FROM token_blacklist WHERE token = ?";
        beanJDBCTemplate.update(sql, token.getToken());
    }

    @Override
    public void addTokenToBlacklist(TokenBlacklist tokenBlacklist) {
        String token = tokenBlacklist.getToken();
        Timestamp timestamp = Timestamp.from(tokenBlacklist.getTimestamp());
        String sql = "INSERT INTO token_blacklist (token, timestamp) VALUES (?, ?)";
        beanJDBCTemplate.update(sql, token, timestamp);
    }

    public List<TokenBlacklist> findAll() {
        String sql = "SELECT * FROM token_blacklist";
        return beanJDBCTemplate.query(sql, (rs, rowNum) -> {
            TokenBlacklist tokenBlacklist = new TokenBlacklist();
            tokenBlacklist.setId(rs.getLong("id"));
            tokenBlacklist.setToken(rs.getString("token"));
            return tokenBlacklist;
        });
    }
}
