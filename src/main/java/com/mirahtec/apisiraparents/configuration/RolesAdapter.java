package com.mirahtec.apisiraparents.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter

@AllArgsConstructor
public class RolesAdapter implements GrantedAuthority {
    private final String role;

    @Override
    public String getAuthority() {
        return this.role;
    }
}