package com.stardusted1.Sendicate.app.core.users;


import com.stardusted1.Sendicate.app.core.cargo.Package;
import com.stardusted1.Sendicate.app.core.cargo.Supply;
import com.stardusted1.Sendicate.app.core.cargo.SupplyStatus;
import com.stardusted1.Sendicate.app.service.Authority;
import com.stardusted1.Sendicate.app.service.System;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Transient;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

@Entity
@Table(name="Providers")
public class Provider extends BusinessCustomer implements Serializable{

    public Supply InitiateSupply(Deliveryman deliveryman, Receiver receiver, ArrayList<Package> packages, String name){
        System system = new System();
        Supply nSupply = new Supply();
        nSupply.setName(name);
        deliveryman.getSupplyHistory().add(nSupply);
        deliveryman.updateCurrentSupplies();
        receiver.getSupplyHistory().add(nSupply);
        receiver.updateCurrentSupplies();
        this.getSupplyHistory().add(nSupply);
        this.updateCurrentSupplies();
        nSupply.setProviderId(receiver.id);
        nSupply.setReceiverId(receiver.id);
        nSupply.setDeliverymanId(deliveryman.id);
        nSupply.setStatus(SupplyStatus.PENDING);
        nSupply.setPackages(packages);
        system.supplyRepository.save(nSupply);
        system.emailNotifier.notifyUsersAboutNewSupply(deliveryman,receiver);
        return nSupply;
        // TODO: 15.11.2019 supply
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
        this.token = System.generateToken(this.getClass().getSimpleName());
    }
}
