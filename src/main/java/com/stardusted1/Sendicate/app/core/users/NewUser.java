package com.stardusted1.Sendicate.app.core.users;

import com.stardusted1.Sendicate.app.service.Authority;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Collection;
import java.util.LinkedList;

@Entity
public class NewUser extends Customer {
    @Column(name = "email",unique = true)
    protected String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities.isEmpty()){
            authorities.add(new Authority("CUSTOMER"));
        }
        return authorities;
    }
}
