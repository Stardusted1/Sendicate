package com.stardusted1.Sendicate.app.core.users;


import com.stardusted1.Sendicate.app.core.cargo.Supply;
import com.stardusted1.Sendicate.app.core.cargo.SupplyStatus;
import com.stardusted1.Sendicate.app.service.Authority;
import com.stardusted1.Sendicate.app.service.System;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "Receivers")
public class Receiver extends Customer implements Serializable {
    @Override
    public String getRole() {
        return "CUSTOMER";
    }

    public Receiver(){
        super();
    }

	@Override
	public void addPhone(String phone) {
		if(phones!=null){
			phones.clear();
			phones.add(phone);
		}else {
			phones = new LinkedList<>();
			phones.add(phone);
		}
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
		}else {
    		emails = new LinkedList<>();
    		emails.add(email);
		}

	}

	@Override
	public void deleteEmail(String email) {
		emails.clear();
	}


	public boolean ReceiveSupply(Supply supply) {
    	supply.setStatus(SupplyStatus.DELIVERED);
        return true;
    }

    public boolean AcceptSupply(Supply supply) {
    	supply.receiverApprove();
    	if(supply.isDeliverymanApproved()){
    	    supply.setStatus(SupplyStatus.DELIVERING);
        }
        return true;
    }

    public boolean ChangeSupplyStatus(Supply supply) {
        supply.setStatus(SupplyStatus.DELIVERED);
        return false;
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
		this.token = System.generateToken(this.getClass().getSimpleName());
	}


}
