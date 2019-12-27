package com.stardusted1.Sendicate.app.core.cargo;

import com.stardusted1.Sendicate.app.core.repositories.DeliverymanRepository;
import com.stardusted1.Sendicate.app.core.repositories.PackageRepository;
import com.stardusted1.Sendicate.app.core.repositories.ProviderRepository;
import com.stardusted1.Sendicate.app.core.repositories.ReceiverRepository;
import com.stardusted1.Sendicate.app.core.users.Deliveryman;
import com.stardusted1.Sendicate.app.core.users.Provider;
import com.stardusted1.Sendicate.app.core.users.Receiver;
import com.stardusted1.Sendicate.app.service.System;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "supplies")
public class Supply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    protected String name;
    protected SupplyStatus status;
    protected Date dateBegins;
    protected Date dateEnds;
    protected boolean receiverApproved;
    protected boolean deliverymanApproved;

    protected String receiverId;
    protected String deliverymanId;
    protected String providerId;
    protected ArrayList<Package> packages;
    protected SupplyCondition condition;


    public Supply() {
    }

    public Supply(String name,
                  Date dateBegins,
                  Date dateEnds,
                  String receiverId,
                  String deliverymanId,
                  String providerId,
                  ArrayList<Package> packages) {

        this.name = name;
        this.dateBegins = dateBegins;
        this.dateEnds = dateEnds;
        this.receiverId = receiverId;
        this.deliverymanId = deliverymanId;
        this.providerId = providerId;
        this.packages = packages;
        this.receiverApproved = false;
        this.deliverymanApproved = false;
    }

    public Supply(Date dateBegins, Date dateEnds, String providerId) {
        this.dateBegins = dateBegins;
        this.dateEnds = dateEnds;
        this.providerId = providerId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SupplyCondition getCondition() {
        return condition;
    }

    public boolean isReceiverApproved() {
        return receiverApproved;
    }

    public void receiverApprove() {
        this.receiverApproved = true;
    }

    public boolean isDeliverymanApproved() {
        return deliverymanApproved;
    }

    public void deliverymanApprove() {
        this.deliverymanApproved = true;
    }

    public void setConditionPartiallySpoiled() {
        System system = new System();
        this.condition = SupplyCondition.PARTIALLY_SPOILED;
        Provider provider = system.providerRepository.findById(providerId).get();
        Receiver receiver = system.receiverRepository.findById(receiverId).get();
        Deliveryman deliveryman = system.deliverymanRepository.findById(deliverymanId).get();
        system.emailNotifier.notifyUsersSupplyBadCondition(deliveryman, provider, receiver, this);
    }

    public void setConditionNormal() {
        this.condition = SupplyCondition.NORMAL;
    }

    public SupplyStatus getStatus() {
        return status;
    }

    public void setStatus(SupplyStatus status, System system) {
        Deliveryman deliveryman = system.deliverymanRepository.findById(deliverymanId).get();
        Provider provider =  system.providerRepository.findById(providerId).get();
        Receiver receiver =  system.receiverRepository.findById(receiverId).get();
        if (status.equals(SupplyStatus.UNDELIVERED)) {

            system.emailNotifier.sendMail(deliveryman.getEmail(),
                    "Supply is out of date",
                    "Supply " + name + " might be delivered at " + dateEnds + "but it wasn't");
            system.emailNotifier.sendMail(provider.getEmail(),
                    "Supply is out of date",
                    "Supply " + name + " might be delivered at " + dateEnds + "but it wasn't");
            system.emailNotifier.sendMail(receiver.getEmail(),
                    "Supply is out of date",
                    "Supply " + name + " might be delivered at " + dateEnds + "but it wasn't");
        }
        if(status.equals(SupplyStatus.DELIVERING)){
            system.emailNotifier.sendMail(deliveryman.getEmail(),
                    "Supply " +name +" is now delivering",
                    "Supply " + name + " might be delivered at " + dateEnds);
            system.emailNotifier.sendMail(provider.getEmail(),
                    "Supply " +name +" is now delivering",
                    "Supply " + name + " might be delivered at " + dateEnds);
            system.emailNotifier.sendMail(receiver.getEmail(),
                    "Supply " +name +" is now delivering",
                    "Supply " + name + " might be delivered at " + dateEnds);
        }
        if(status.equals(SupplyStatus.DELIVERED)){
            try {
                system.emailNotifier.sendMail(deliveryman.getEmail(),
                        "Supply " +name +" is now delivered",
                        "Supply " + name + " might be delivered at" + new Date());
            }catch (Exception e){

            }
            try {
                system.emailNotifier.sendMail(provider.getEmail(),
                        "Supply " +name +" is now delivered",
                        "Supply " + name + " might be delivered at" + new Date());
            }catch (Exception e){

            }
            try {
                system.emailNotifier.sendMail(receiver.getEmail(),
                        "Supply " +name +" is now delivered",
                        "Supply " + name + " might be delivered at" + new Date());
            } catch (Exception e){

            }

        }
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateBegins() {
        return dateBegins;
    }

    public void setDateBegins(Date dateBegins) {
        this.dateBegins = dateBegins;
    }

    public Date getDateEnds() {
        return dateEnds;
    }

    public void setDateEnds(Date dateEnds) {
        this.dateEnds = dateEnds;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getDeliverymanId() {
        return deliverymanId;
    }

    public void setDeliverymanId(String deliverymanId) {
        this.deliverymanId = deliverymanId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public ArrayList<Package> getPackages() {
        return packages;
    }

    public void setPackages(ArrayList<Package> packages) {
        this.packages = packages;
    }

    public void addPackage(Package aPackage) {
        if(packages!=null){
//            packageRepository;
            aPackage.setSupplyId(this.id);
            packages.add(aPackage);

        }else{
            packages = new ArrayList<>();
            aPackage.setSupplyId(this.id);
            packages.add(aPackage);
        }
    }
}
