package com.mirahtec.apisiraparents.service.AuthService;
import com.mirahtec.apisiraparents.dao.auth.UserParentJDBCDaoImpl;
import com.mirahtec.apisiraparents.model.AuthUser;
import com.mirahtec.apisiraparents.utils.SHA256PasswordEncoder;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Primary
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserParentJDBCDaoImpl userParentJDBCDaoImpl;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    SHA256PasswordEncoder passwordEncoderSHA256;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = userParentJDBCDaoImpl.findByUsername(username);
        user.setRole("ADMIN");
        if (user != null) {
            return new User(user.getTelephone(), user.getPassword(), getGrantedAuthorities(user.getRole()));
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }
    public String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}
