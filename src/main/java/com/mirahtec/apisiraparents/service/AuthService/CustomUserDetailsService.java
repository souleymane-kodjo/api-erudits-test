package com.mirahtec.apisiraparents.service;
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
    //private ParentJDBCDaoImpl parentJDBCDao ;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    SHA256PasswordEncoder passwordEncoderSHA256;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Parent user = parentJDBCDao.findByUsername(username);
        AuthUser user = userParentJDBCDaoImpl.findByUsername(username);
        user.setRole("ADMIN");

        if (user != null) {
            return new User(user.getTelephone(), user.getPassword(), getGrantedAuthorities(user.getRole()));
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    @PostConstruct
    public void createUserIfNeeded() {
        String defaultAdminUsername = "admin";
        AuthUser existingUser = null  ;
        if (existingUser == null) {
            AuthUser adminUser = new AuthUser();
            adminUser.setEmail("admin@gmail.com");
            adminUser.setNom("admin");
            adminUser.setPrenom("admin");
            adminUser.setTelephone(defaultAdminUsername);
            adminUser.setPassword(passwordEncoderSHA256.encode(defaultAdminUsername)); // replace "adminPassword" with the actual password
            adminUser.setRole("ADMIN");

            userParentJDBCDaoImpl.save(adminUser);
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }



//    @PostConstruct
//    public void createUserIfNeeded() {
//        String defaultAdminUsername = "Mohamed";
//        Parent existingUser = parentJDBCDao.findByUsername(defaultAdminUsername);
//        if (existingUser == null) {
//            Parent adminUser = new Parent();
//            adminUser.setEmail("Mohamed@gmail.com");
//            adminUser.setFirstName("Mohamed");
//            adminUser.setLastName("Ben");
//            adminUser.setUsername(defaultAdminUsername);
//            adminUser.setPassword(passwordEncoder.encode("Mohamed")); // replace "adminPassword" with the actual password
//            adminUser.setRole("ADMIN");
//            parentJDBCDao.save(adminUser);
//        }
//    }


    public String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}
