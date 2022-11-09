package com.mobi.ocs.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="QuotationEzySplitMDRRate")
public class QuotationEzySplitMDRRate {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonIgnore()
	@Column(name = "createdOn")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime createdOn;
	
	@JsonIgnore
	@OneToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
	@JoinColumn(name = "QuotationID")
	private Quotation quotation;
	
	@Column(name = "productName")
	private String productName;
	
	@Column(name = "IncludeWallet")
	private String includeWallet;
	
	@Column(name = "showInQuote")
	private String showInQuote;
	
	@Column(name = "payLater")
	private String payLater;
	
	@Column(name = "subscription")
	private String subscription;
	
	@Column(name = "unitPrice")
	private double unitPrice;
	
	@Column(name = "productType")
	private String productType;
	
	@Column(name = "hostType")
	private String hostType;
	
	@Column(name = "hostRateIdRef")
	private String hostRateIdRef;
	
	
	// Instalment 3 __ Local Credit - Merchant,Customer,Mobi

		@Column(name = "loc_Credit_Merch_Master_Insta3")
		private double loc_Credit_Merch_Master_Insta3;

		@Column(name = "loc_Credit_Cus_Master_Insta3")
		private double loc_Credit_Cus_Master_Insta3;

		@Column(name = "loc_Credit_Host_Master_Insta3")
		private double loc_Credit_Host_Master_Insta3;

		@Column(name = "loc_Credit_Mobi_Master_Insta3")
		private double loc_Credit_Mobi_Master_Insta3;

		// Instalment 6 __ Local Credit - Merchant,Customer,Mobi

		@Column(name = "loc_Credit_Merch_Master_Insta6")
		private double loc_Credit_Merch_Master_Insta6;

		@Column(name = "loc_Credit_Cus_Master_Insta6")
		private double loc_Credit_Cus_Master_Insta6;

		@Column(name = "loc_Credit_Host_Master_Insta6")
		private double loc_Credit_Host_Master_Insta6;

		@Column(name = "loc_Credit_Mobi_Master_Insta6")
		private double loc_Credit_Mobi_Master_Insta6;

		// Instalment 9 __ Local Credit - Merchant,Customer,Mobi

		@Column(name = "loc_Credit_Merch_Master_Insta9")
		private double loc_Credit_Merch_Master_Insta9;

		@Column(name = "loc_Credit_Cus_Master_Insta9")
		private double loc_Credit_Cus_Master_Insta9;

		@Column(name = "loc_Credit_Host_Master_Insta9")
		private double loc_Credit_Host_Master_Insta9;

		@Column(name = "loc_Credit_Mobi_Master_Insta9")
		private double loc_Credit_Mobi_Master_Insta9;

		// Instalment 12 __ Local Credit - Merchant,Customer,Mobi

		@Column(name = "loc_Credit_Merch_Master_Insta12")
		private double loc_Credit_Merch_Master_Insta12;

		@Column(name = "loc_Credit_Cus_Master_Insta12")
		private double loc_Credit_Cus_Master_Insta12;

		@Column(name = "loc_Credit_Host_Master_Insta12")
		private double loc_Credit_Host_Master_Insta12;

		@Column(name = "loc_Credit_Mobi_Master_Insta12")
		private double loc_Credit_Mobi_Master_Insta12;

		// Boost Grab FPX
		@Column(name = "boostMDREcomm", columnDefinition = "double default 0")
		private double boostMDREcomm;

		@Column(name = "boostMDRQR", columnDefinition = "double default 0")
		private double boostMDRQR;

		@Column(name = "grabMDREcomm", columnDefinition = "double default 0")
		private double grabMDREcomm;

		@Column(name = "grabMDRQR", columnDefinition = "double default 0")
		private double grabMDRQR;

		@Column(name = "FPXMDR_RM", columnDefinition = "double default 0")
		private double fPXMDR_RM;

		@Column(name = "FPXMDR_Percent", columnDefinition = "double default 0")
		private double fPXMDR_Percent;
		
		// tng and shopeepay

		@Column(name = "tngMDREcomm", columnDefinition = "double default 0")
		private double tngMDREcomm;

		@Column(name = "tngMDRQR", columnDefinition = "double default 0")
		private double tngMDRQR;

		@Column(name = "shopeepayMDREcomm", columnDefinition = "double default 0")
		private double shopeepayMDREcomm;

		@Column(name = "shopeepayMDRQR", columnDefinition = "double default 0")
		private double shopeepayMDRQR;

		@Column(name = "tngSettlement", columnDefinition = "int default 0")
		private int tngSettlement;

		@Column(name = "shopeepaySettlement", columnDefinition = "int default 0")
		private int shopeepaySettlement;

	
	//Settlement Periods
	
	@Column(name = "productSettlement")
	private int productSettlement;
	
	@Column(name = "BoostSettlement")
	private int boostSettlement;
	
	@Column(name = "GrabSettlement")
	private int grabSettlement;
	
	@Column(name = "fpxSettlement")
	private int fpxSettlement;

	
	public double getTngMDREcomm() {
		return tngMDREcomm;
	}

	public void setTngMDREcomm(double tngMDREcomm) {
		this.tngMDREcomm = tngMDREcomm;
	}

	public double getTngMDRQR() {
		return tngMDRQR;
	}

	public void setTngMDRQR(double tngMDRQR) {
		this.tngMDRQR = tngMDRQR;
	}

	public double getShopeepayMDREcomm() {
		return shopeepayMDREcomm;
	}

	public void setShopeepayMDREcomm(double shopeepayMDREcomm) {
		this.shopeepayMDREcomm = shopeepayMDREcomm;
	}

	public double getShopeepayMDRQR() {
		return shopeepayMDRQR;
	}

	public void setShopeepayMDRQR(double shopeepayMDRQR) {
		this.shopeepayMDRQR = shopeepayMDRQR;
	}

	public int getTngSettlement() {
		return tngSettlement;
	}

	public void setTngSettlement(int tngSettlement) {
		this.tngSettlement = tngSettlement;
	}

	public int getShopeepaySettlement() {
		return shopeepaySettlement;
	}

	public void setShopeepaySettlement(int shopeepaySettlement) {
		this.shopeepaySettlement = shopeepaySettlement;
	}
	
	
	
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

	
	public Quotation getQuotation() {
		return quotation;
	}

	public void setQuotation(Quotation quotation) {
		this.quotation = quotation;
	}

	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getIncludeWallet() {
		return includeWallet;
	}

	public void setIncludeWallet(String includeWallet) {
		this.includeWallet = includeWallet;
	}

	public String getShowInQuote() {
		return showInQuote;
	}

	public void setShowInQuote(String showInQuote) {
		this.showInQuote = showInQuote;
	}

	
	public String getHostRateIdRef() {
		return hostRateIdRef;
	}

	public void setHostRateIdRef(String hostRateIdRef) {
		this.hostRateIdRef = hostRateIdRef;
	}

	public String getPayLater() {
		return payLater;
	}

	public void setPayLater(String payLater) {
		this.payLater = payLater;
	}

	public String getSubscription() {
		return subscription;
	}

	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getHostType() {
		return hostType;
	}

	public void setHostType(String hostType) {
		this.hostType = hostType;
	}

	public double getLoc_Credit_Merch_Master_Insta3() {
		return loc_Credit_Merch_Master_Insta3;
	}

	public void setLoc_Credit_Merch_Master_Insta3(double loc_Credit_Merch_Master_Insta3) {
		this.loc_Credit_Merch_Master_Insta3 = loc_Credit_Merch_Master_Insta3;
	}

	public double getLoc_Credit_Cus_Master_Insta3() {
		return loc_Credit_Cus_Master_Insta3;
	}

	public void setLoc_Credit_Cus_Master_Insta3(double loc_Credit_Cus_Master_Insta3) {
		this.loc_Credit_Cus_Master_Insta3 = loc_Credit_Cus_Master_Insta3;
	}

	public double getLoc_Credit_Host_Master_Insta3() {
		return loc_Credit_Host_Master_Insta3;
	}

	public void setLoc_Credit_Host_Master_Insta3(double loc_Credit_Host_Master_Insta3) {
		this.loc_Credit_Host_Master_Insta3 = loc_Credit_Host_Master_Insta3;
	}

	public double getLoc_Credit_Mobi_Master_Insta3() {
		return loc_Credit_Mobi_Master_Insta3;
	}

	public void setLoc_Credit_Mobi_Master_Insta3(double loc_Credit_Mobi_Master_Insta3) {
		this.loc_Credit_Mobi_Master_Insta3 = loc_Credit_Mobi_Master_Insta3;
	}

	public double getLoc_Credit_Merch_Master_Insta6() {
		return loc_Credit_Merch_Master_Insta6;
	}

	public void setLoc_Credit_Merch_Master_Insta6(double loc_Credit_Merch_Master_Insta6) {
		this.loc_Credit_Merch_Master_Insta6 = loc_Credit_Merch_Master_Insta6;
	}

	public double getLoc_Credit_Cus_Master_Insta6() {
		return loc_Credit_Cus_Master_Insta6;
	}

	public void setLoc_Credit_Cus_Master_Insta6(double loc_Credit_Cus_Master_Insta6) {
		this.loc_Credit_Cus_Master_Insta6 = loc_Credit_Cus_Master_Insta6;
	}

	public double getLoc_Credit_Host_Master_Insta6() {
		return loc_Credit_Host_Master_Insta6;
	}

	public void setLoc_Credit_Host_Master_Insta6(double loc_Credit_Host_Master_Insta6) {
		this.loc_Credit_Host_Master_Insta6 = loc_Credit_Host_Master_Insta6;
	}

	public double getLoc_Credit_Mobi_Master_Insta6() {
		return loc_Credit_Mobi_Master_Insta6;
	}

	public void setLoc_Credit_Mobi_Master_Insta6(double loc_Credit_Mobi_Master_Insta6) {
		this.loc_Credit_Mobi_Master_Insta6 = loc_Credit_Mobi_Master_Insta6;
	}

	public double getLoc_Credit_Merch_Master_Insta9() {
		return loc_Credit_Merch_Master_Insta9;
	}

	public void setLoc_Credit_Merch_Master_Insta9(double loc_Credit_Merch_Master_Insta9) {
		this.loc_Credit_Merch_Master_Insta9 = loc_Credit_Merch_Master_Insta9;
	}

	public double getLoc_Credit_Cus_Master_Insta9() {
		return loc_Credit_Cus_Master_Insta9;
	}

	public void setLoc_Credit_Cus_Master_Insta9(double loc_Credit_Cus_Master_Insta9) {
		this.loc_Credit_Cus_Master_Insta9 = loc_Credit_Cus_Master_Insta9;
	}

	public double getLoc_Credit_Host_Master_Insta9() {
		return loc_Credit_Host_Master_Insta9;
	}

	public void setLoc_Credit_Host_Master_Insta9(double loc_Credit_Host_Master_Insta9) {
		this.loc_Credit_Host_Master_Insta9 = loc_Credit_Host_Master_Insta9;
	}

	public double getLoc_Credit_Mobi_Master_Insta9() {
		return loc_Credit_Mobi_Master_Insta9;
	}

	public void setLoc_Credit_Mobi_Master_Insta9(double loc_Credit_Mobi_Master_Insta9) {
		this.loc_Credit_Mobi_Master_Insta9 = loc_Credit_Mobi_Master_Insta9;
	}

	public double getLoc_Credit_Merch_Master_Insta12() {
		return loc_Credit_Merch_Master_Insta12;
	}

	public void setLoc_Credit_Merch_Master_Insta12(double loc_Credit_Merch_Master_Insta12) {
		this.loc_Credit_Merch_Master_Insta12 = loc_Credit_Merch_Master_Insta12;
	}

	public double getLoc_Credit_Cus_Master_Insta12() {
		return loc_Credit_Cus_Master_Insta12;
	}

	public void setLoc_Credit_Cus_Master_Insta12(double loc_Credit_Cus_Master_Insta12) {
		this.loc_Credit_Cus_Master_Insta12 = loc_Credit_Cus_Master_Insta12;
	}

	public double getLoc_Credit_Host_Master_Insta12() {
		return loc_Credit_Host_Master_Insta12;
	}

	public void setLoc_Credit_Host_Master_Insta12(double loc_Credit_Host_Master_Insta12) {
		this.loc_Credit_Host_Master_Insta12 = loc_Credit_Host_Master_Insta12;
	}

	public double getLoc_Credit_Mobi_Master_Insta12() {
		return loc_Credit_Mobi_Master_Insta12;
	}

	public void setLoc_Credit_Mobi_Master_Insta12(double loc_Credit_Mobi_Master_Insta12) {
		this.loc_Credit_Mobi_Master_Insta12 = loc_Credit_Mobi_Master_Insta12;
	}

	
	public double getfPXMDR_RM() {
		return fPXMDR_RM;
	}

	public void setfPXMDR_RM(double fPXMDR_RM) {
		this.fPXMDR_RM = fPXMDR_RM;
	}

	public double getfPXMDR_Percent() {
		return fPXMDR_Percent;
	}

	public void setfPXMDR_Percent(double fPXMDR_Percent) {
		this.fPXMDR_Percent = fPXMDR_Percent;
	}

	public double getBoostMDREcomm() {
		return boostMDREcomm;
	}

	public void setBoostMDREcomm(double boostMDREcomm) {
		this.boostMDREcomm = boostMDREcomm;
	}

	public double getBoostMDRQR() {
		return boostMDRQR;
	}

	public void setBoostMDRQR(double boostMDRQR) {
		this.boostMDRQR = boostMDRQR;
	}

	public double getGrabMDREcomm() {
		return grabMDREcomm;
	}

	public void setGrabMDREcomm(double grabMDREcomm) {
		this.grabMDREcomm = grabMDREcomm;
	}

	public double getGrabMDRQR() {
		return grabMDRQR;
	}

	public void setGrabMDRQR(double grabMDRQR) {
		this.grabMDRQR = grabMDRQR;
	}

	public int getProductSettlement() {
		return productSettlement;
	}

	public void setProductSettlement(int productSettlement) {
		this.productSettlement = productSettlement;
	}

	public int getBoostSettlement() {
		return boostSettlement;
	}

	public void setBoostSettlement(int boostSettlement) {
		this.boostSettlement = boostSettlement;
	}

	public int getGrabSettlement() {
		return grabSettlement;
	}

	public void setGrabSettlement(int grabSettlement) {
		this.grabSettlement = grabSettlement;
	}

	public int getFpxSettlement() {
		return fpxSettlement;
	}

	public void setFpxSettlement(int fpxSettlement) {
		this.fpxSettlement = fpxSettlement;
	}

	@Override
	public String toString() {
		return "QuotationEzySplitMDRRate [id=" + id + ", createdOn=" + createdOn + ", quotation=" + quotation
				+ ", productName=" + productName + ", includeWallet=" + includeWallet + ", showInQuote=" + showInQuote
				+ ", payLater=" + payLater + ", subscription=" + subscription + ", unitPrice=" + unitPrice
				+ ", productType=" + productType + ", hostType=" + hostType + ", hostRateIdRef=" + hostRateIdRef
				+ ", loc_Credit_Merch_Master_Insta3=" + loc_Credit_Merch_Master_Insta3
				+ ", loc_Credit_Cus_Master_Insta3=" + loc_Credit_Cus_Master_Insta3 + ", loc_Credit_Host_Master_Insta3="
				+ loc_Credit_Host_Master_Insta3 + ", loc_Credit_Mobi_Master_Insta3=" + loc_Credit_Mobi_Master_Insta3
				+ ", loc_Credit_Merch_Master_Insta6=" + loc_Credit_Merch_Master_Insta6
				+ ", loc_Credit_Cus_Master_Insta6=" + loc_Credit_Cus_Master_Insta6 + ", loc_Credit_Host_Master_Insta6="
				+ loc_Credit_Host_Master_Insta6 + ", loc_Credit_Mobi_Master_Insta6=" + loc_Credit_Mobi_Master_Insta6
				+ ", loc_Credit_Merch_Master_Insta9=" + loc_Credit_Merch_Master_Insta9
				+ ", loc_Credit_Cus_Master_Insta9=" + loc_Credit_Cus_Master_Insta9 + ", loc_Credit_Host_Master_Insta9="
				+ loc_Credit_Host_Master_Insta9 + ", loc_Credit_Mobi_Master_Insta9=" + loc_Credit_Mobi_Master_Insta9
				+ ", loc_Credit_Merch_Master_Insta12=" + loc_Credit_Merch_Master_Insta12
				+ ", loc_Credit_Cus_Master_Insta12=" + loc_Credit_Cus_Master_Insta12
				+ ", loc_Credit_Host_Master_Insta12=" + loc_Credit_Host_Master_Insta12
				+ ", loc_Credit_Mobi_Master_Insta12=" + loc_Credit_Mobi_Master_Insta12 + ", boostMDREcomm="
				+ boostMDREcomm + ", boostMDRQR=" + boostMDRQR + ", grabMDREcomm=" + grabMDREcomm + ", grabMDRQR="
				+ grabMDRQR + ", fPXMDR_RM=" + fPXMDR_RM + ", fPXMDR_Percent=" + fPXMDR_Percent + ", tngMDREcomm="
				+ tngMDREcomm + ", tngMDRQR=" + tngMDRQR + ", shopeepayMDREcomm=" + shopeepayMDREcomm
				+ ", shopeepayMDRQR=" + shopeepayMDRQR + ", tngSettlement=" + tngSettlement + ", shopeepaySettlement="
				+ shopeepaySettlement + ", productSettlement=" + productSettlement + ", boostSettlement="
				+ boostSettlement + ", grabSettlement=" + grabSettlement + ", fpxSettlement=" + fpxSettlement
				+ ", getTngMDREcomm()=" + getTngMDREcomm() + ", getTngMDRQR()=" + getTngMDRQR()
				+ ", getShopeepayMDREcomm()=" + getShopeepayMDREcomm() + ", getShopeepayMDRQR()=" + getShopeepayMDRQR()
				+ ", getTngSettlement()=" + getTngSettlement() + ", getShopeepaySettlement()="
				+ getShopeepaySettlement() + ", getId()=" + getId() + ", getCreatedOn()=" + getCreatedOn()
				+ ", getQuotation()=" + getQuotation() + ", getProductName()=" + getProductName()
				+ ", getIncludeWallet()=" + getIncludeWallet() + ", getShowInQuote()=" + getShowInQuote()
				+ ", getHostRateIdRef()=" + getHostRateIdRef() + ", getPayLater()=" + getPayLater()
				+ ", getSubscription()=" + getSubscription() + ", getUnitPrice()=" + getUnitPrice()
				+ ", getProductType()=" + getProductType() + ", getHostType()=" + getHostType()
				+ ", getLoc_Credit_Merch_Master_Insta3()=" + getLoc_Credit_Merch_Master_Insta3()
				+ ", getLoc_Credit_Cus_Master_Insta3()=" + getLoc_Credit_Cus_Master_Insta3()
				+ ", getLoc_Credit_Host_Master_Insta3()=" + getLoc_Credit_Host_Master_Insta3()
				+ ", getLoc_Credit_Mobi_Master_Insta3()=" + getLoc_Credit_Mobi_Master_Insta3()
				+ ", getLoc_Credit_Merch_Master_Insta6()=" + getLoc_Credit_Merch_Master_Insta6()
				+ ", getLoc_Credit_Cus_Master_Insta6()=" + getLoc_Credit_Cus_Master_Insta6()
				+ ", getLoc_Credit_Host_Master_Insta6()=" + getLoc_Credit_Host_Master_Insta6()
				+ ", getLoc_Credit_Mobi_Master_Insta6()=" + getLoc_Credit_Mobi_Master_Insta6()
				+ ", getLoc_Credit_Merch_Master_Insta9()=" + getLoc_Credit_Merch_Master_Insta9()
				+ ", getLoc_Credit_Cus_Master_Insta9()=" + getLoc_Credit_Cus_Master_Insta9()
				+ ", getLoc_Credit_Host_Master_Insta9()=" + getLoc_Credit_Host_Master_Insta9()
				+ ", getLoc_Credit_Mobi_Master_Insta9()=" + getLoc_Credit_Mobi_Master_Insta9()
				+ ", getLoc_Credit_Merch_Master_Insta12()=" + getLoc_Credit_Merch_Master_Insta12()
				+ ", getLoc_Credit_Cus_Master_Insta12()=" + getLoc_Credit_Cus_Master_Insta12()
				+ ", getLoc_Credit_Host_Master_Insta12()=" + getLoc_Credit_Host_Master_Insta12()
				+ ", getLoc_Credit_Mobi_Master_Insta12()=" + getLoc_Credit_Mobi_Master_Insta12() + ", getfPXMDR_RM()="
				+ getfPXMDR_RM() + ", getfPXMDR_Percent()=" + getfPXMDR_Percent() + ", getBoostMDREcomm()="
				+ getBoostMDREcomm() + ", getBoostMDRQR()=" + getBoostMDRQR() + ", getGrabMDREcomm()="
				+ getGrabMDREcomm() + ", getGrabMDRQR()=" + getGrabMDRQR() + ", getProductSettlement()="
				+ getProductSettlement() + ", getBoostSettlement()=" + getBoostSettlement() + ", getGrabSettlement()="
				+ getGrabSettlement() + ", getFpxSettlement()=" + getFpxSettlement() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	

	
}
