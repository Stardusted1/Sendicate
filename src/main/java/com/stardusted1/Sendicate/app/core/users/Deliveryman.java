package com.stardusted1.Sendicate.app.core.users;


import com.stardusted1.Sendicate.app.core.cargo.Supply;
import com.stardusted1.Sendicate.app.core.cargo.SupplyStatus;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.LinkedList;

//
@Entity
@Table(name="Deliverymans")
public class Deliveryman extends BusinessCustomer{

    public boolean AcceptSupply(Supply supply){
        return false;
//        TODO
    }

    public boolean ChangeSupplyStatus(Supply supply){

        if(supply.getStatus() == SupplyStatus.DELIVERING){
            supply.setStatus(SupplyStatus.DELIVERED);
            return true;
        }else{
            return false;
        }
//        TODO
    }

    protected void updateSupplyHistory(){
        this.supplyHistory = (LinkedList<Supply>) supplyRepository.findAllByDeliverymanIdEquals(this.id);
    }
}
