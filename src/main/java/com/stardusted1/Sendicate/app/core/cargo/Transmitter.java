package com.stardusted1.Sendicate.app.core.cargo;

import com.stardusted1.Sendicate.app.core.cargo.condition.Condition;
import com.stardusted1.Sendicate.app.core.repositories.PackageRepository;
import org.springframework.data.annotation.Transient;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Entity
public class Transmitter {
	@Transient
	PackageRepository packageRepository;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;
	protected Date dateOfRegistration;
	protected long currentSupplyId;
	protected long currentPackageId;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	protected String token;

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

	public void ReceiveDataFrame(Frame frame) throws Exception {
		Optional<Package> OPackage = packageRepository.findById(currentPackageId);
		Package aPackage;
		if(OPackage.isPresent()){
			aPackage = OPackage.get();
		}else{
			throw new Exception();
		}
		aPackage.getHistory().add(frame);
		ArrayList<Condition> packageConditions = aPackage.getConditions();
		for(Condition condition:packageConditions){
			if(!condition.CheckCondition(frame)){
				aPackage.setStatusSpoiled();
			}
		}
	}


}
