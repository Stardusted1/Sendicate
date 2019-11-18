package com.stardusted1.Sendicate.app.core.users;

import com.stardusted1.Sendicate.app.core.cargo.Supply;
import com.stardusted1.Sendicate.app.core.cargo.Transmitter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.LinkedList;
//
@Entity
@Table(name="Administrators")
public class Administrator extends User {
    public Administrator(String name, String login, String password) {
        super(name, login, password);
    }

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





}
