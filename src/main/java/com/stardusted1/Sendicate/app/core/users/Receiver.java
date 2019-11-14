package com.stardusted1.Sendicate.app.core.users;


import com.stardusted1.Sendicate.app.core.cargo.Supply;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.LinkedList;

@Entity
@Table(name = "Receivers")
public class Receiver extends Customer {
    @Column(name = "email")
    protected String email;
    @Column(name = "phone")
    protected String phone;
    @Column(name = "surname")
    protected String surname;
    public Receiver(){
        super();
    }

    public Receiver(String name, String login, String password) {
        super(name, login, password);
        this.email = "";
        this.phone = "";
        this.surname = "";
    }

    public Receiver(String name, String login, String password, String email, String phone, String surname) {
        super(name, login, password);
        this.email = email;
        this.phone = phone;
        this.surname = surname;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean ReceiveSupply(Supply supply) {
        return false;
//        TODO
    }

    public boolean AcceptSupply(Supply supply) {
        return false;
//        TODO
    }

    public boolean ChangeSupplyStatus(Supply supply) {
        return false;
//        TODO
    }


}
