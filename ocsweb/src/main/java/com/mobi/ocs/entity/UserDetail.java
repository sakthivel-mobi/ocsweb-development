package com.mobi.ocs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UserDetail")
public class UserDetail {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "signature")
	private String signature;
	
	@Column(name = "active")
	private boolean active;
	
	@Column(name = "NRIC")
	private String nRIC;
	
	@Column(name = "aliasName")
	private String aliasName;
	
	@Column(name = "hubspotOwnerId")
	private String hubspotOwnerId;
	
	@Column(name = "isSales" , nullable = false, columnDefinition = "int default 0")
	private int isSales;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getnRIC() {
		return nRIC;
	}

	public void setnRIC(String nRIC) {
		this.nRIC = nRIC;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	

	public String getHubspotOwnerId() {
		return hubspotOwnerId;
	}

	public void setHubspotOwnerId(String hubspotOwnerId) {
		this.hubspotOwnerId = hubspotOwnerId;
	}

	public int getIsSales() {
		return isSales;
	}

	public void setIsSales(int isSales) {
		this.isSales = isSales;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "EmployeeDetail [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", signature="
				+ signature + ", active=" + active + ", nRIC=" + nRIC + ", aliasName=" + aliasName + ", hubspotOwnerId="
				+ hubspotOwnerId + ", isSales=" + isSales + "]";
	}


}
