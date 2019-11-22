package com.stardusted1.Sendicate.app.core.users;


import com.stardusted1.Sendicate.app.core.cargo.Supply;
import com.stardusted1.Sendicate.app.core.cargo.SupplyStatus;
import com.stardusted1.Sendicate.app.core.repositories.DeliverymanRepository;
import com.stardusted1.Sendicate.app.core.repositories.ReceiverRepository;
import com.stardusted1.Sendicate.app.core.repositories.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Transient;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="Providers")
public class Provider extends BusinessCustomer{
    @Transient
    @Autowired
    private SupplyRepository supplyrepo;
    @Transient
    private ReceiverRepository receiverRepository;
    @Transient
    private DeliverymanRepository deliverymanRepository;


    public Supply InitiateSupply(Deliveryman deliveryman, Receiver receiver, List<Package> packages,String name){
        Supply nSupply = new Supply();
        deliveryman.getSupplyHistory().add(nSupply);
        deliveryman.updateCurrentSupplies();
        receiver.getSupplyHistory().add(nSupply);
        receiver.updateCurrentSupplies();
        this.getSupplyHistory().add(nSupply);
        this.updateCurrentSupplies();
        nSupply.setProviderId(receiver.id);
        nSupply.setReceiverId(receiver.id);
        nSupply.setDeliverymanId(deliveryman.id);
        supplyrepo.save(nSupply);
        return nSupply;
        // TODO: 15.11.2019 supply
    }

}