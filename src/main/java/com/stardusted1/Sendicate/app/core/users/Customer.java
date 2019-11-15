package com.stardusted1.Sendicate.app.core.users;

import com.stardusted1.Sendicate.app.core.cargo.Supply;
import com.stardusted1.Sendicate.app.core.cargo.SupplyStatus;
import com.stardusted1.Sendicate.app.core.reporting.Report;
import com.stardusted1.Sendicate.app.core.reporting.Reportable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.LinkedList;

@MappedSuperclass
public class Customer extends User {

	@Transient
	protected LinkedList<Supply> supplyHistory;
	@Transient
	protected LinkedList<Supply> currentSupllies;

	protected long reportReposId;

	public Customer(String name, String login, String password) {
		super(name, login, password);
		this.supplyHistory = new LinkedList<>();
		this.currentSupllies = new LinkedList<>();
	}

	public Customer(String name, String login, String password, LinkedList<Supply> supplyHistory, LinkedList<Supply> currentSupllies) {
		super(name, login, password);
		this.supplyHistory = supplyHistory;
		this.currentSupllies = currentSupllies;
	}


	public Customer() {
		super();
	}

	public long getReportReposId() {
		return reportReposId;
	}

	public void setReportReposId(long reportReposId) {
		this.reportReposId = reportReposId;
	}

	public LinkedList<Supply> getSupplyHistory() {
		return supplyHistory;
	}

	public LinkedList<Supply> getCurrentSupllies() {
		return currentSupllies;
	}

	public boolean CreateReport(Reportable object, String topic, String body) {
		try {
			Report report = new Report(this.id,body,topic);
			object.AddReport(report);
			return true;
		} catch (Exception E) {
			return false;
		}
	}


	protected void updateCurrentSupplies() {
		for (Supply supply : supplyHistory) {
            if(supply.getStatus().equals(SupplyStatus.DELIVERING)){
                currentSupllies.add(supply);
            }

		}
		for(Supply supply : currentSupllies){
		    if(supply.getStatus().equals(SupplyStatus.DELIVERED)||supply.getStatus().equals(SupplyStatus.UNDELIVERED)){
		        currentSupllies.remove(supply);
            }
        }
	}
}
