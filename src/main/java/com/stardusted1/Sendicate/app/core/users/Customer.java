package com.stardusted1.Sendicate.app.core.users;

import com.stardusted1.Sendicate.app.core.cargo.Supply;
import com.stardusted1.Sendicate.app.core.cargo.SupplyStatus;
import com.stardusted1.Sendicate.app.core.repositories.SupplyRepository;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.LinkedList;

@MappedSuperclass
public abstract class  Customer extends User {
	@Transient
	protected LinkedList<Supply> supplyHistory;
	@Transient
	protected LinkedList<Supply> currentSupplies;
	@Transient
	SupplyRepository supplyRepository;

	public Customer() {
		super();
	}


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
