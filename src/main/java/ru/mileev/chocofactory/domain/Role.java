package ru.mileev.chocofactory.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, ТЕХНОЛОГ, ВЗБИВАТЕЛЬ, ИССЛЕДОВАТЕЛЬ, БЕЛКА, УПАКОВЩИК;

    @Override
    public String getAuthority() {
        return name();
    }
}
