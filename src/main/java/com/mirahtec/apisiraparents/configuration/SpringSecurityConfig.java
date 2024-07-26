package com.mirahtec.apisiraparents.configuration;

import com.mirahtec.apisiraparents.utils.SHA256PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;
    @Autowired
    SHA256PasswordEncoder passwordEncoderSHA256;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(a->a
                        .requestMatchers("/api/v1/home").permitAll()
                        .requestMatchers("/api/v1/public/**").permitAll()
                        .requestMatchers("/api/v1/auth/change-password").permitAll()
                        .requestMatchers("/api/v1/auth/login").permitAll()
                        .requestMatchers("/api/v1/auth/logout").permitAll()
                        .requestMatchers("/api/v1/students/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/presences/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/notes/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .formLogin().disable()
                .httpBasic().disable()
                .csrf().disable()
                .cors(cors -> cors.disable())
                .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(f->f.authenticationEntryPoint(
                        unauthorizedHandler
                ))
                .headers(h->h.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return    httpSecurity    .build();

    }
    @Bean
    public AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration b) throws Exception {
        return b.getAuthenticationManager();
    }
}