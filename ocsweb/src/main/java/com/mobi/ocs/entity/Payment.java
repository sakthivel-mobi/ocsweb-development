package com.mobi.ocs.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Payment")
public class Payment {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "collectedOn")
	private String collectedOn;
	
	@Column(name = "verifiedOn")
	private String verifiedOn;
	
	@Column(name = "verifiedBy")
	private String verifiedBy;
	
	@Column(name = "verifiedByUserName")
	private String verifiedByUserName;
	
	@Column(name = "invoiceNo")
	private String invoiceNo;
	
	@Column(name = "receipt")
	private String receipt;;
	
	@Column(name = "modeOfPayment")
	private String modeOfPayment;
	
	@JsonIgnore
	@OneToOne(mappedBy = "payment", cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
	private Quotation quotation;
	
	private String quotationId;
	
	

	public Payment(String collectedOn, String verifiedOn, String verifiedBy, String invoiceNo, String receipt,
			String modeOfPayment, Quotation quotation, String quotationId) {
		this.collectedOn = collectedOn;
		this.verifiedOn = verifiedOn;
		this.verifiedBy = verifiedBy;
		this.invoiceNo = invoiceNo;
		this.receipt = receipt;
		this.modeOfPayment = modeOfPayment;
		this.quotation = quotation;
		this.quotationId = quotationId;
	}

	public String getVerifiedByUserName() {
		return verifiedByUserName;
	}

	public void setVerifiedByUserName(String verifiedByUserName) {
		this.verifiedByUserName = verifiedByUserName;
	}

	public Payment() {
	}

	public String getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(String quotationId) {
		this.quotationId = quotationId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCollectedOn() {
		return collectedOn;
	}

	public void setCollectedOn(String collectedOn) {
		this.collectedOn = collectedOn;
	}

	public String getVerifiedOn() {
		return verifiedOn;
	}

	public void setVerifiedOn(String verifiedOn) {
		this.verifiedOn = verifiedOn;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public String getVerifiedBy() {
		return verifiedBy;
	}

	public void setVerifiedBy(String verifiedBy) {
		this.verifiedBy = verifiedBy;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public Quotation getQuotation() {
		return quotation;
	}

	public void setQuotation(Quotation quotation) {
		this.quotation = quotation;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", collectedOn=" + collectedOn + ", verifiedOn=" + verifiedOn + ", invoiceNo="
				+ invoiceNo + ", receipt=" + receipt + ", modeOfPayment=" + modeOfPayment + ", quotation=" + quotation
				+ "]";
	}
	
	

}
