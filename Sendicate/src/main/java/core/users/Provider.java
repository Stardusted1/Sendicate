package core.users;

import core.cargo.Package;
import core.cargo.Supply;
import core.reporting.Report;

import java.util.List;

public class Provider extends BusinessCustomer{

    public Provider(String name, String login, String password) {
        super(name, login, password);
    }

    public Supply InitiateSupply(Deliveryman deliveryman, Receiver receiver, List<Package> packages){
        return new Supply();
    }

}
