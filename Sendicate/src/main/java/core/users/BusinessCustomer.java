package core.users;

import java.util.LinkedList;

public class BusinessCustomer extends Customer {

    protected String description;
    protected LinkedList<String> emails;
    protected LinkedList<String> phones;
    protected LinkedList<String> address;
    protected String siteAddress;

    public BusinessCustomer(String name, String login, String password) {
        super(name, login, password);
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
