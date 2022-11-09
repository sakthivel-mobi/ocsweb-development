package com.mobi.ocs.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SalesPerson")
public class SalesPerson {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinColumn(name = "signature")
	private Signature signature;

	@Column(name = "active")
	private boolean active;

	@Column(name = "NRIC")
	private String nRIC;

	@Column(name = "aliasName")
	private String aliasName;

	@Column(name = "hubspotOwnerId")
	private String hubspotOwnerId;

	@Column(name = "salesHead", nullable = false, columnDefinition = "int default 0")
	private int salesHead;

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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getnRIC() {
		return nRIC;
	}

	public Signature getSignature() {
		return signature;
	}

	public void setSignature(Signature signature) {
		this.signature = signature;
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

	public int getSalesHead() {
		return salesHead;
	}

	public void setSalesHead(int salesHead) {
		this.salesHead = salesHead;
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

//	@Override
//	public String toString() {
//		return "SalesPerson [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", signature="
//				+ signature + ", active=" + active + ", nRIC=" + nRIC + ", aliasName=" + aliasName + ", hubspotOwnerId="
//				+ hubspotOwnerId + ", salesHead=" + salesHead + "]";
//	}

}
