package com.stardusted1.Sendicate.app.core.users;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;

@MappedSuperclass
public abstract class BusinessCustomer extends Customer implements Serializable {

	@Column(name = "description")
	protected String description;
	@Column(name = "address")
	protected ArrayList<String> address;
	@Column(name = "siteAddress")
	protected String siteAddress;


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void addPhone(String phone) {
		if (phones != null) {
			if (!phones.contains(phone)) {
				this.phones.add(phone);
			}
		}else {
			phones = new ArrayList<>();
			phones.add(phone);
		}
	}

	@Override
	public void deletePhone(String phone) {
		this.phones.remove(phone);
	}

	@Override
	public void addEmail(String email) {
		if (emails != null) {
			if (!emails.contains(email)) {
				emails.add(email);
			}
		}else {
			emails = new ArrayList<>();
			emails.add(email);
		}
	}

	@Override
	public void deleteEmail(String email) {
		emails.remove(email);
	}


	public ArrayList<String> getAddress() {
		if(address!=null){
			return address;
		}else {
			address = new ArrayList<>();
			address.add("");
			return address;
		}

	}

	public String getAddres() {
		if(address!=null){
			return address.get(0);
		}else {
			return null;
		}

	}

	public void addAddress(String address) {
		if(this.address!=null){
			if(!this.address.contains(address)){
				this.address.add(address);
			}
		}else{
			this.address = new ArrayList<>();
			this.address.add(address);
		}

	}

	public void deleteAddress(String address) {
		this.address.remove(address);
	}

	public String getSiteAddress() {
		return siteAddress;
	}

	public void setSiteAddress(String siteAddress) {
		this.siteAddress = siteAddress;
	}


}
