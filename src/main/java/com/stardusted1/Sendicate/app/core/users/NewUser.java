package com.stardusted1.Sendicate.app.core.users;

import com.stardusted1.Sendicate.app.service.Authority;
import com.stardusted1.Sendicate.app.service.System;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Entity
public class NewUser extends Customer implements Serializable {

    @Override
    public String getRole() {
        return "USER";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities.isEmpty()){
            authorities.add(new Authority("USER"));
        }
        return authorities;
    }

	@Override
	public void newToken() {
		System.generateToken(this.getClass().getName());
	}

	@Override
	public void addPhone(String phone) {
		phones.clear();
		phones.add(phone);
	}

	@Override
	public void deletePhone(String phone) {
		phones.clear();
	}

	@Override
	public void addEmail(String email) {
    	if(emails!=null){
			emails.clear();
			emails.add(email);
		}else{
    		emails = new LinkedList<>();
    		emails.add(email);
		}

	}

	@Override
	public void deleteEmail(String email) {
		emails.clear();
	}
}
