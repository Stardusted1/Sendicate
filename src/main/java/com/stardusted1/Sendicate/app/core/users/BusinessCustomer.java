package com.stardusted1.Sendicate.app.core.users;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.LinkedList;

@MappedSuperclass
public abstract class BusinessCustomer extends Customer {

	@Column(name = "description")
	protected String description;
	@Column(name = "email")
	protected LinkedList<String> emails;
	@Column(name = "phones")
	protected LinkedList<String> phones;
	@Column(name = "address")
	protected LinkedList<String> address;
	@Column(name = "siteAddress")
	protected String siteAddress;


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LinkedList<String> getEmails() {
		return emails;
	}

	public void AddEmail(String email) {
		for(String e:emails){
			if(e.equals(email)){
				return;
			}
		}
		this.emails.add(email);
	}

	public void DeleteEmail(String email) {
		this.emails.remove(email);
	}

	public LinkedList<String> getPhones() {
		return phones;
	}

	public void AddPhone(String phone) {
		for(String p:phones){
			if(p.equals(phone)){
				return;
			}
		}
		this.emails.add(phone);
	}

	public void DeletePhone(String phone) {
		this.emails.remove(phone);
	}


	public LinkedList<String> getAddress() {
		return address;
	}

	public void AddAddress(String addres) {
		for(String p:address){
			if(p.equals(addres)){
				return;
			}
		}
		this.address.add(addres);
	}

	public void DeleteAddress(String address) {
		this.emails.remove(address);
	}

	public String getSiteAddress() {
		return siteAddress;
	}

	public void setSiteAddress(String siteAddress) {
		this.siteAddress = siteAddress;
	}


}
