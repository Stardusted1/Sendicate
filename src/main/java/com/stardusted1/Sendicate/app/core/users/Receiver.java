package com.stardusted1.Sendicate.app.core.users;


import com.stardusted1.Sendicate.app.core.cargo.Supply;
import com.stardusted1.Sendicate.app.core.cargo.SupplyStatus;
import com.stardusted1.Sendicate.app.service.Authority;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

@Entity
@Table(name = "Receivers")
public class Receiver extends Customer implements Serializable {
    @Column(name = "email")
    protected String email;
    @Column(name = "phone")
    protected String phone;

    @Override
    public String getRole() {
        return "CUSTOMER";
    }

    public Receiver(){
        super();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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


}
