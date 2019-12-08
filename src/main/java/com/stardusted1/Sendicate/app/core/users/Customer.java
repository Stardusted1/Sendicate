package com.stardusted1.Sendicate.app.core.users;

import com.stardusted1.Sendicate.app.core.cargo.Supply;
import com.stardusted1.Sendicate.app.core.cargo.SupplyStatus;
import com.stardusted1.Sendicate.app.core.repositories.SupplyRepository;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@MappedSuperclass
public abstract class Customer extends User implements Serializable {
	@Transient
	protected LinkedList<Supply> supplyHistory;
	@Transient
	protected LinkedList<Supply> currentSupplies;
	@Column(name = "emails")
	protected LinkedList<String> emails;
	@Column(name = "phones")
	protected LinkedList<String> phones;
	@Transient
	SupplyRepository supplyRepository;
	public Customer() {
		super();
	}

	public LinkedList<String> getPhones() {
		if(phones!=null){
			return phones;
		}else{
			phones = new LinkedList<>();
			return phones;
		}


	}

	public String getPhone() {
		if(phones!=null){
			return phones.getFirst();
		}else{
			return null;
		}

	}

	public abstract void addPhone(String phone);

	public abstract void deletePhone(String phone);

	public LinkedList<String> getEmails() {
		if(emails!=null){
			return emails;
		}else{
			emails = new LinkedList<>();
			return emails;
		}
	}

	public String getEmail() {
		if(emails!=null){
			return emails.getFirst();
		}else{
			return null;
		}

	}

	public abstract void addEmail(String email);

	public abstract void deleteEmail(String email);


	public LinkedList<Supply> getSupplyHistory() {
		return supplyHistory;
	}

	public LinkedList<Supply> getCurrentSupplies() {
		return currentSupplies;
	}

	public void updateCurrentSupplies() {
		for (Supply supply : supplyHistory) {
			if (supply.getStatus().equals(SupplyStatus.DELIVERING)) {
				currentSupplies.add(supply);
			}

		}
		for (Supply supply : currentSupplies) {
			if (supply.getStatus().equals(SupplyStatus.DELIVERED) || supply.getStatus().equals(SupplyStatus.UNDELIVERED)) {
				currentSupplies.remove(supply);
			}
		}
	}


}
