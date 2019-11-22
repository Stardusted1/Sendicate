package com.stardusted1.Sendicate.app.core.users;


import com.stardusted1.Sendicate.app.core.cargo.Supply;
import com.stardusted1.Sendicate.app.core.cargo.SupplyStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Receivers")
public class Receiver extends Customer {
    @Column(name = "email",unique = true)
    protected String email;
    @Column(name = "phone",unique = true)
    protected String phone;
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
        return false;
//        TODO
    }

    public boolean AcceptSupply(Supply supply) {
    	supply.setStatus(SupplyStatus.DELIVERED);
        return false;
    }

    public boolean ChangeSupplyStatus(Supply supply) {
        return false;
//        TODO
    }


}
