package com.hele.utils;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    CLIENT, EMPLOYEE, OWNER;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
