package com.stardusted1.Sendicate.app.core.users;


import com.stardusted1.Sendicate.app.core.cargo.Supply;
import com.stardusted1.Sendicate.app.core.cargo.SupplyStatus;
import com.stardusted1.Sendicate.app.service.Authority;
import com.stardusted1.Sendicate.app.service.System;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

//
@Entity
@Table(name="Deliverymans")
public class Deliveryman extends BusinessCustomer implements Serializable {

    public boolean AcceptSupply(Supply supply){
        supply.deliverymanApprove();
        if(supply.isReceiverApproved()){
            supply.setStatus(SupplyStatus.DELIVERING);
        }
        return true;
    }

    public boolean ChangeSupplyStatus(Supply supply){

        if(supply.getStatus() == SupplyStatus.DELIVERING){
            supply.setStatus(SupplyStatus.DELIVERED);
            return true;
        }else{
            return false;
        }
//        TODO
    }

    protected void updateSupplyHistory(){
        this.supplyHistory = (LinkedList<Supply>) supplyRepository.findAllByDeliverymanIdEquals(this.id);
    }

    @Override
    public String getRole() {
        return "BCUSTOMER";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities.isEmpty()){
            authorities.add(new Authority("CUSTOMER"));
        }
        return authorities;
    }

    @Override
    public void newToken() {
    	this.token = System.generateToken(this.getClass().getName());
    }
}
