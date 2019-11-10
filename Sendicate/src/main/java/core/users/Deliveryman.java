package core.users;

import core.cargo.Supply;

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
