package com.stardusted1.Sendicate.app.core.cargo;

import com.stardusted1.Sendicate.app.core.cargo.condition.Condition;
import com.stardusted1.Sendicate.app.core.repositories.SupplyRepository;
import com.stardusted1.Sendicate.app.service.TokenGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Optional;

@Entity
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    protected String payload;
    protected long supplyId;
    protected ArrayList<Frame> history;
    @Transient
    protected ArrayList<Transmitter> transmitters;
    protected ArrayList<Condition> conditions;
    protected PackageStatus status;
    @Transient
    SupplyRepository supplyRepository;

    public Package() {
    }

    public PackageStatus getStatus() {
        return status;
    }

    public void setStatusSpoiled() {
        this.status = PackageStatus.SPOILED;
        Optional<Supply> Osupply = supplyRepository.findById(supplyId);
        Supply supply;
        if (Osupply.isPresent()) {
            supply = Osupply.get();
            supply.setConditionPartiallySpoiled();
        }

    }

    public void setStatusNormal() {
        this.status = PackageStatus.NORMAL;
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

    public boolean addTransmitter(Transmitter transmitter) {
        for (Transmitter t : transmitters) {
            if (t.equals(transmitter)) {
                return false;
            }
        }
        transmitters.add(transmitter);
        transmitter.setCurrentPackageId(this.id);
        transmitter.setCurrentSupplyId(this.supplyId);
        transmitter.setToken(TokenGenerator.generateToken(transmitter.getClass().getTypeName().toLowerCase()));
        return true;
    }

    public boolean removeTransmitter(Transmitter transmitter) {
        transmitters.remove(transmitter);
        return true;
    }

    public boolean addCondition(Condition condition) {
        if (conditions.size() == 0) {
            conditions.add(condition);
            return true;
        } else {
            for (Condition c : conditions) {
                if (c.getClass().equals(condition.getClass())) {
                    return false;
                }
            }
            conditions.add(condition);
            return true;
        }
    }

    public boolean deleteCondition(Condition condition) {
        conditions.remove(condition);
        return true;
    }

    public ArrayList<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(ArrayList<Condition> conditions) {
        this.conditions = conditions;
    }


}
