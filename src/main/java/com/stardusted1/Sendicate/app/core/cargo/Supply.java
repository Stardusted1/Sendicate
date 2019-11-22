package com.stardusted1.Sendicate.app.core.cargo;

import com.stardusted1.Sendicate.app.core.repositories.DeliverymanRepository;
import com.stardusted1.Sendicate.app.core.repositories.ProviderRepository;
import com.stardusted1.Sendicate.app.core.repositories.ReceiverRepository;
import com.stardusted1.Sendicate.app.core.users.Deliveryman;
import com.stardusted1.Sendicate.app.core.users.Provider;
import com.stardusted1.Sendicate.app.core.users.Receiver;
import com.stardusted1.Sendicate.app.service.EmailNotifier;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Transient;
import java.util.LinkedList;
import java.util.Optional;

@Entity
@Table(name = "Administrators")
public class Supply{
	@Transient
	DeliverymanRepository deliverymanRepository;
	@Transient
	ProviderRepository providerRepository;
	@Transient
	ReceiverRepository receiverRepository;
	@Transient
	@Autowired
	EmailNotifier emailNotifier;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    protected String name;
	protected SupplyStatus status;
    protected Date dateBegins;
    protected Date dateEnds;
    protected boolean receiverApproved;
    protected boolean deliverymanApproved;

	protected long receiverId;

	protected long deliverymanId;
	protected long providerId;
	protected ArrayList<Package> packages;
	protected SupplyCondition condition;

	public Supply(String name,
				  Date dateBegins,
				  Date dateEnds,
				  long receiverId,
				  long deliverymanId,
				  long providerId,
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
        this.condition = SupplyCondition.PARTIALLY_SPOILED;
        Provider provider = providerRepository.findById(providerId).get();
		Receiver receiver = receiverRepository.findById(receiverId).get();
		Deliveryman deliveryman = deliverymanRepository.findById(deliverymanId).get();
		emailNotifier.NotifyUsers(deliveryman,provider,receiver,this);
    }

	public void setConditionNormal() {
		this.condition = SupplyCondition.NORMAL;
	}

    public Supply() {
    }
	public SupplyStatus getStatus() {
		return status;
	}

	public void setStatus(SupplyStatus status) {
		this.status = status;
	}


	public Supply(Date dateBegins, Date dateEnds, long providerId) {
        this.dateBegins = dateBegins;
        this.dateEnds = dateEnds;
        this.providerId = providerId;
    }

    public int getId() {
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

    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }

    public long getDeliverymanId() {
        return deliverymanId;
    }

    public void setDeliverymanId(long deliverymanId) {
        this.deliverymanId = deliverymanId;
    }

    public long getProviderId() {
        return providerId;
    }

    public void setProviderId(long providerId) {
        this.providerId = providerId;
    }

    public ArrayList<Package> getPackages() {
        return packages;
    }

    public void setPackages(ArrayList<Package> packages) {
        this.packages = packages;
    }

    public void addPackage(Package aPackage){
        packages.add(aPackage);
    }
}
