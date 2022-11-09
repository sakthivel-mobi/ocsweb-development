package com.mobi.ocs.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "welcomeLetterAcceptance")
public class WelcomeLetterAcceptance {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "createdOn")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime createdOn;
	
	@OneToOne(mappedBy = "welcomeLetterAcceptance", fetch = FetchType.EAGER)
	private Quotation quotation;
	
	@Column(name = "ipAddress")
	private String ipAddress;
	
	@Column(name = "nameAsPerIC")
	private String nameAsPerIC;
	
	@Column(name = "icNumber")
	private String icNumber;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "signatureId")
	private Signature signature;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getNameAsPerIC() {
		return nameAsPerIC;
	}

	public void setNameAsPerIC(String nameAsPerIC) {
		this.nameAsPerIC = nameAsPerIC;
	}

	public String getIcNumber() {
		return icNumber;
	}

	public void setIcNumber(String icNumber) {
		this.icNumber = icNumber;
	}

	public Signature getSignature() {
		return signature;
	}

	public void setSignature(Signature signature) {
		this.signature = signature;
	}

	
	

	public Quotation getQuotation() {
		return quotation;
	}

	public void setQuotation(Quotation quotation) {
		this.quotation = quotation;
	}

//	@Override
//	public String toString() {
//		return "WelcomeLetterAcceptance [id=" + id + ", createdOn=" + createdOn + ", ipAddress="
//				+ ipAddress + ", nameAsPerIC=" + nameAsPerIC + ", icNumber=" + icNumber + "]";
//	}

	

}
	

