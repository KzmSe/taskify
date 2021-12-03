package com.taskify.account.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum AccountRole implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
