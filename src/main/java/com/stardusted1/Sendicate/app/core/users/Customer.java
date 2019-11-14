package com.stardusted1.Sendicate.app.core.users;

import com.stardusted1.Sendicate.app.core.cargo.Supply;
import com.stardusted1.Sendicate.app.core.reporting.Report;
import com.stardusted1.Sendicate.app.core.reporting.Reportable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import java.util.LinkedList;

@MappedSuperclass
public class Customer extends User {

    @Column(name = "supplyHistory")
    protected LinkedList<Supply> supplyHistory;
    protected LinkedList<Supply> currentSullies;


    public Customer(String name, String login, String password) {
        super(name, login, password);
        this.supplyHistory = new LinkedList<>();
        this.currentSullies = new LinkedList<>();
    }

    public Customer(String name, String login, String password, LinkedList<Supply> supplyHistory, LinkedList<Supply> currentSullies) {
        super(name, login, password);
        this.supplyHistory = supplyHistory;
        this.currentSullies = currentSullies;
    }

    public Customer() {
        super();
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
