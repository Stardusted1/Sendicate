package com.stardusted1.Sendicate.app.core.cargo;

import com.stardusted1.Sendicate.app.core.cargo.condition.Condition;
import com.stardusted1.Sendicate.app.core.repositories.SupplyRepository;
import javax.persistence.Transient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Optional;

@Entity
public class Package {
	@Transient
	SupplyRepository supplyRepository;
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

    public PackageStatus getStatus() {
        return status;
    }

    public void setStatusSpoiled() {
        this.status = PackageStatus.SPOILED;
		Optional<Supply> Osupply = supplyRepository.findById(supplyId);
		Supply supply;
		if(Osupply.isPresent()){
			supply = Osupply.get();
			supply.setConditionPartiallySpoiled();
			return;
		}
        // TODO: 17.11.2019 set Supply status = spoiled
    }
    public void setStatusNormal() {
        this.status = PackageStatus.NORMAL;
    }


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

    public boolean addTransmitter(Transmitter transmitter) {
        return false;
        // TODO: 17.11.2019
    }

    public boolean removeTransmitter(Transmitter transmitter) {
        return false;
        // TODO: 17.11.2019
    }

    public void setTransmitters(ArrayList<Transmitter> transmitters) {
        this.transmitters = transmitters;
    }

	public boolean addCondition(Condition condition) {
		if(conditions.size() == 0){
			conditions.add(condition);
			return true;
		}else{
			for(Condition c: conditions){
				if(c.getClass().equals(condition.getClass())){
					return false;
				}
			}
			conditions.add(condition);
			return true;
		}
	}



	public ArrayList<Condition> getConditions() {
		return conditions;
	}

    // TODO: 17.11.2019 add, delete condition

    public void setConditions(ArrayList<Condition> conditions) {
        this.conditions = conditions;
    }


}
