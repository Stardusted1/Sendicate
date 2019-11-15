package com.stardusted1.Sendicate.app.core.cargo;

import com.stardusted1.Sendicate.app.core.cargo.condition.Condition;
import org.springframework.data.annotation.Transient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.EventListener;

@Entity
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    protected String payload;
    protected long supplyId;
    @Transient
    protected ArrayList<Frame> history;
    @Transient
    protected ArrayList<Transmitter> transmitters;
    @Transient
    protected ArrayList<Condition> conditions;


    public Package() {
    }

    public long getId() {
        return id;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public long getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(long supplyId) {
        this.supplyId = supplyId;
    }

    public ArrayList<Frame> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<Frame> history) {
        this.history = history;
    }

    public ArrayList<Transmitter> getTransmitters() {
        return transmitters;
    }

    public void setTransmitters(ArrayList<Transmitter> transmitters) {
        this.transmitters = transmitters;
    }

    public ArrayList<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(ArrayList<Condition> conditions) {
        this.conditions = conditions;
    }


}
