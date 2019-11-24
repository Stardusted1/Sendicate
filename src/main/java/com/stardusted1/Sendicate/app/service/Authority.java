package com.stardusted1.Sendicate.app.service;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

    private String value;

    public Authority(String value) {
        this.value = value;
    }

    @Override
    public String getAuthority() {
        return  value;
    }
    public void setValue(String value1){
        this.value = value1;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
