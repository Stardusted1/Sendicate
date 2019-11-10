package core.users;

import core.cargo.Supply;
import core.reporting.Report;
import core.reporting.Reportable;

import java.util.LinkedList;

public class Customer extends User {
    protected LinkedList<Supply> supplyHistory;
    protected LinkedList<Supply> currentSullies;
    public Customer(String name, String login, String password) {
        super(name, login, password);
    }

    public LinkedList<Supply> getSupplyHistory() {
        return supplyHistory;
    }

    public LinkedList<Supply> getCurrentSullies() {
        return currentSullies;
    }

    public boolean CreateReport(Reportable object, String topic, String body) {
        try{
        Report report = new Report(this, topic, body);
        object.AddReport(report);
        return true;
        } catch (Exception E){
            return false;
        }
    }


}
