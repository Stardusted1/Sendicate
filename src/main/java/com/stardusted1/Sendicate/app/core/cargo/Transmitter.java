package com.stardusted1.Sendicate.app.core.cargo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Transmitter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;
	protected Date dateOfRegistration;
	protected long currentSupplyId;
	protected long currentPackageId;

	public Transmitter() {
	}

	public long getId() {
		return id;
	}

	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}

	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}

	public long getCurrentSupplyId() {
		return currentSupplyId;
	}

	public void setCurrentSupplyId(long currentSupplyId) {
		this.currentSupplyId = currentSupplyId;
	}

	public long getCurrentPackageId() {
		return currentPackageId;
	}

	public void setCurrentPackageId(long currentPackageId) {
		this.currentPackageId = currentPackageId;
	}


}
