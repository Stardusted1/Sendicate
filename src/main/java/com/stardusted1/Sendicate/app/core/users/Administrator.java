package com.stardusted1.Sendicate.app.core.users;

import com.stardusted1.Sendicate.app.core.cargo.Supply;
import com.stardusted1.Sendicate.app.core.cargo.Transmitter;
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
@Table(name="Administrators")
public class Administrator extends User implements Serializable {

    protected String email;
    protected String surname;
    protected String address;
    protected String phoneNumber;


    public String getEmail() {
        return email;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LinkedList<Deliveryman> getDeliverymans(){
        return null;
//        TODO
    }

    public boolean DeleteDeliveryman(Deliveryman deliveryman){
        return false;
//        TODO
    }

    public LinkedList<Receiver> GetReceivers(){
        return null;
//        TODO
    }

    public boolean DeleteReceiver(Receiver receiver){
        return false;
//        TODO
    }

    public LinkedList<Provider> GetProviders(){
        return null;
//        TODO
    }

    public boolean DeleteProvider(Provider provider){
        return false;
//        TODO
    }
    public LinkedList<Supply> GetActiveSupplies(){
        return null;
//        TODO
    }
    public LinkedList<Supply> GetAllSupplies(){
        return null;
//        TODO
    }

    public boolean DeleteSupply(){
        return false;
//        TODO
    }

    public boolean RegisterNewTransmitter(Transmitter transmitter){
        return false;
    }


    @Override
    public String getRole() {
        return "ADMIN";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities.isEmpty()){
            authorities.add(new Authority("ADMIN"));
        }
        return authorities;
    }

	@Override
	public void newToken() {
		 this.token = System.generateToken(this.getClass().getName());
	}
}
