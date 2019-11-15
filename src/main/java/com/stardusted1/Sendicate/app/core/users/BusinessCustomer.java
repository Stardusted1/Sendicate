package com.stardusted1.Sendicate.app.core.users;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import java.util.LinkedList;
@MappedSuperclass
public class BusinessCustomer extends Customer {

    @Column(name = "description")
    protected String description;
    @Column(name = "email")
    protected LinkedList<String> emails;
    @Column(name = "phones")
    protected LinkedList<String> phones;
    @Column(name = "address")
    protected LinkedList<String> address;
    @Column(name = "siteAddress")
    protected String siteAddress;

// TODO: 14.11.2019 сделать все через ид

    public BusinessCustomer(String name, String login, String password) {
        super(name, login, password);
        this.description = "";
        this.emails =  new LinkedList<>();
        this.phones = new LinkedList<>();
        this.address = new LinkedList<>();
        this.siteAddress = "";

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LinkedList<String> getEmails() {
        return emails;
    }
    public void AddEmail(String email) {
        this.emails.add(email);
    }
    public void DeleteEmail(String email) {
        this.emails.remove(email);
    }

    public LinkedList<String> getPhones() {
        return phones;
    }

    public void AddPhone(String phone) {
        this.emails.add(phone);
    }
    public void DeletePhone(String phone) {
        this.emails.remove(phone);
    }


    public LinkedList<String> getAddress() {
        return address;
    }

    public void AddAddress(String address) {
        this.emails.add(address);
    }
    public void DeleteAddress(String address) {
        this.emails.remove(address);
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }






}
