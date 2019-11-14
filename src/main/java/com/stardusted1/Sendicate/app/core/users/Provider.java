package com.stardusted1.Sendicate.app.core.users;


import com.stardusted1.Sendicate.app.core.cargo.Supply;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="Providers")
public class Provider extends BusinessCustomer{

    public Provider(String name, String login, String password) {
        super(name, login, password);
    }

    public Supply InitiateSupply(Deliveryman deliveryman, Receiver receiver, List<Package> packages){
        return new Supply();
    }

}
