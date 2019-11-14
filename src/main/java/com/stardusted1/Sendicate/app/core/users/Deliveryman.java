package com.stardusted1.Sendicate.app.core.users;


import com.stardusted1.Sendicate.app.core.cargo.Supply;

import javax.persistence.Entity;
import javax.persistence.Table;
//
@Entity
@Table(name="Deliverymans")
public class Deliveryman extends BusinessCustomer{

    public Deliveryman(String name, String login, String password) {
        super(name, login, password);
    }

    public boolean AcceptSupply(Supply supply){
        return false;
//        TODO
    }

    public boolean ChangeSupplyStatus(Supply supply){
        return false;
//        TODO
    }
}
