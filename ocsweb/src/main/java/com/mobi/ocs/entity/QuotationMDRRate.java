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
@Table(name = "QuotationMDRRate")
public class QuotationMDRRate {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonIgnore()
	@Column(name = "createdOn")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime createdOn;

	@JsonIgnore()
	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
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

	// @ManyToOne(cascade=
	// {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
	// @JoinColumn(name = "ProductID")
	// private Product product;

	@Column(name = "unitPrice")
	private double unitPrice;

	@Column(name = "productType")
	private String productType;

	@Column(name = "hostType")
	private String hostType;

	@Column(name = "hostRateIdRef")
	private String hostRateIdRef;

	// @Column(name = "UnitPrice")
	// private double productUnitPrice;

	// Debit Merchant
	@Column(name = "loc_Debit_Merch_Visa")
	private double loc_Debit_Merch_Visa;

	@Column(name = "loc_Debit_Merch_Master")
	private double loc_Debit_Merch_Master;

	@Column(name = "loc_Debit_Merch_Union")
	private double loc_Debit_Merch_Union;

	@Column(name = "for_Debit_Merch_Visa")
	private double for_Debit_Merch_Visa;

	@Column(name = "for_Debit_Merch_Master")
	private double for_Debit_Merch_Master;

	@Column(name = "for_Debit_Merch_Union")
	private double for_Debit_Merch_Union;

	// Credit Merchant
	@Column(name = "loc_Credit_Merch_Visa")
	private double loc_Credit_Merch_Visa;

	@Column(name = "loc_Credit_Merch_Master")
	private double loc_Credit_Merch_Master;

	@Column(name = "loc_Credit_Merch_Union")
	private double loc_Credit_Merch_Union;

	@Column(name = "for_Credit_Merch_Visa")
	private double for_Credit_Merch_Visa;

	@Column(name = "for_Credit_Merch_Master")
	private double for_Credit_Merch_Master;

	@Column(name = "for_Credit_Merch_Union")
	private double for_Credit_Merch_Union;

	// Debit Host
	@Column(name = "loc_Debit_Host_Visa")
	private double loc_Debit_Host_Visa;

	@Column(name = "loc_Debit_Host_Master")
	private double loc_Debit_Host_Master;

	@Column(name = "loc_Debit_Host_Union")
	private double loc_Debit_Host_Union;

	@Column(name = "for_Debit_Host_Visa")
	private double for_Debit_Host_Visa;

	@Column(name = "for_Debit_Host_Master")
	private double for_Debit_Host_Master;

	@Column(name = "for_Debit_Hosth_Union")
	private double for_Debit_Host_Union;

	// Credit Host
	@Column(name = "loc_Credit_Host_Visa")
	private double loc_Credit_Host_Visa;

	@Column(name = "loc_Credit_Host_Master")
	private double loc_Credit_Host_Master;

	@Column(name = "loc_Credit_Host_Union")
	private double loc_Credit_Host_Union;

	@Column(name = "for_Credit_Host_Visa")
	private double for_Credit_Host_Visa;

	@Column(name = "for_Credit_Host_Master")
	private double for_Credit_Host_Master;

	@Column(name = "for_Credit_Host_Union")
	private double for_Credit_Host_Union;

	// Debit Mobi
	@Column(name = "loc_Debit_Mobi_Visa")
	private double loc_Debit_Mobi_Visa;

	@Column(name = "loc_Debit_Mobi_Master")
	private double loc_Debit_Mobi_Master;

	@Column(name = "loc_Debit_Mobi_Union")
	private double loc_Debit_Mobi_Union;

	@Column(name = "for_Debit_Mobi_Visa")
	private double for_Debit_Mobi_Visa;

	@Column(name = "for_Debit_Mobi_Master")
	private double for_Debit_Mobi_Master;

	@Column(name = "for_Debit_Mobih_Union")
	private double for_Debit_Mobi_Union;

	// Credit Mobi
	@Column(name = "loc_Credit_Mobi_Visa")
	private double loc_Credit_Mobi_Visa;

	@Column(name = "loc_Credit_Mobi_Master")
	private double loc_Credit_Mobi_Master;

	@Column(name = "loc_Credit_Mobi_Union")
	private double loc_Credit_Mobi_Union;

	@Column(name = "for_Credit_Mobi_Visa")
	private double for_Credit_Mobi_Visa;

	@Column(name = "for_Credit_Mobi_Master")
	private double for_Credit_Mobi_Master;

	@Column(name = "for_Credit_Mobi_Union")
	private double for_Credit_Mobi_Union;

	// local Credit Customer
	@Column(name = "loc_Credit_Cus_Visa")
	private double loc_Credit_Cus_Visa;

	@Column(name = "loc_Credit_Cus_Master")
	private double loc_Credit_Cus_Master;

	@Column(name = "loc_Credit_Cus_Union")
	private double loc_Credit_Cus_Union;

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

	// Settlement Periods

	@Column(name = "productSettlement")
	private int productSettlement;

	@Column(name = "BoostSettlement")
	private int boostSettlement;

	@Column(name = "GrabSettlement")
	private int grabSettlement;

	@Column(name = "fpxSettlement")
	private int fpxSettlement;
	
	
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

	/*
	 * public double getProductUnitPrice() { return productUnitPrice; }
	 * 
	 * public void setProductUnitPrice(double productUnitPrice) {
	 * this.productUnitPrice = productUnitPrice; }
	 */
	public double getLoc_Debit_Merch_Visa() {
		return loc_Debit_Merch_Visa;
	}

	public void setLoc_Debit_Merch_Visa(double loc_Debit_Merch_Visa) {
		this.loc_Debit_Merch_Visa = loc_Debit_Merch_Visa;
	}

	public double getLoc_Debit_Merch_Master() {
		return loc_Debit_Merch_Master;
	}

	public void setLoc_Debit_Merch_Master(double loc_Debit_Merch_Master) {
		this.loc_Debit_Merch_Master = loc_Debit_Merch_Master;
	}

	public double getLoc_Debit_Merch_Union() {
		return loc_Debit_Merch_Union;
	}

	public void setLoc_Debit_Merch_Union(double loc_Debit_Merch_Union) {
		this.loc_Debit_Merch_Union = loc_Debit_Merch_Union;
	}

	public double getFor_Debit_Merch_Visa() {
		return for_Debit_Merch_Visa;
	}

	public void setFor_Debit_Merch_Visa(double for_Debit_Merch_Visa) {
		this.for_Debit_Merch_Visa = for_Debit_Merch_Visa;
	}

	public double getFor_Debit_Merch_Master() {
		return for_Debit_Merch_Master;
	}

	public void setFor_Debit_Merch_Master(double for_Debit_Merch_Master) {
		this.for_Debit_Merch_Master = for_Debit_Merch_Master;
	}

	public double getFor_Debit_Merch_Union() {
		return for_Debit_Merch_Union;
	}

	public void setFor_Debit_Merch_Union(double for_Debit_Merch_Union) {
		this.for_Debit_Merch_Union = for_Debit_Merch_Union;
	}

	public double getLoc_Credit_Merch_Visa() {
		return loc_Credit_Merch_Visa;
	}

	public void setLoc_Credit_Merch_Visa(double loc_Credit_Merch_Visa) {
		this.loc_Credit_Merch_Visa = loc_Credit_Merch_Visa;
	}

	public double getLoc_Credit_Merch_Master() {
		return loc_Credit_Merch_Master;
	}

	public void setLoc_Credit_Merch_Master(double loc_Credit_Merch_Master) {
		this.loc_Credit_Merch_Master = loc_Credit_Merch_Master;
	}

	public double getLoc_Credit_Merch_Union() {
		return loc_Credit_Merch_Union;
	}

	public void setLoc_Credit_Merch_Union(double loc_Credit_Merch_Union) {
		this.loc_Credit_Merch_Union = loc_Credit_Merch_Union;
	}

	public double getFor_Credit_Merch_Visa() {
		return for_Credit_Merch_Visa;
	}

	public void setFor_Credit_Merch_Visa(double for_Credit_Merch_Visa) {
		this.for_Credit_Merch_Visa = for_Credit_Merch_Visa;
	}

	public double getFor_Credit_Merch_Master() {
		return for_Credit_Merch_Master;
	}

	public void setFor_Credit_Merch_Master(double for_Credit_Merch_Master) {
		this.for_Credit_Merch_Master = for_Credit_Merch_Master;
	}

	public double getFor_Credit_Merch_Union() {
		return for_Credit_Merch_Union;
	}

	public void setFor_Credit_Merch_Union(double for_Credit_Merch_Union) {
		this.for_Credit_Merch_Union = for_Credit_Merch_Union;
	}

	public double getLoc_Debit_Host_Visa() {
		return loc_Debit_Host_Visa;
	}

	public void setLoc_Debit_Host_Visa(double loc_Debit_Host_Visa) {
		this.loc_Debit_Host_Visa = loc_Debit_Host_Visa;
	}

	public double getLoc_Debit_Host_Master() {
		return loc_Debit_Host_Master;
	}

	public void setLoc_Debit_Host_Master(double loc_Debit_Host_Master) {
		this.loc_Debit_Host_Master = loc_Debit_Host_Master;
	}

	public double getLoc_Debit_Host_Union() {
		return loc_Debit_Host_Union;
	}

	public void setLoc_Debit_Host_Union(double loc_Debit_Host_Union) {
		this.loc_Debit_Host_Union = loc_Debit_Host_Union;
	}

	public double getFor_Debit_Host_Visa() {
		return for_Debit_Host_Visa;
	}

	public void setFor_Debit_Host_Visa(double for_Debit_Host_Visa) {
		this.for_Debit_Host_Visa = for_Debit_Host_Visa;
	}

	public double getFor_Debit_Host_Master() {
		return for_Debit_Host_Master;
	}

	public void setFor_Debit_Host_Master(double for_Debit_Host_Master) {
		this.for_Debit_Host_Master = for_Debit_Host_Master;
	}

	public double getFor_Debit_Host_Union() {
		return for_Debit_Host_Union;
	}

	public void setFor_Debit_Host_Union(double for_Debit_Host_Union) {
		this.for_Debit_Host_Union = for_Debit_Host_Union;
	}

	public double getLoc_Credit_Host_Visa() {
		return loc_Credit_Host_Visa;
	}

	public void setLoc_Credit_Host_Visa(double loc_Credit_Host_Visa) {
		this.loc_Credit_Host_Visa = loc_Credit_Host_Visa;
	}

	public double getLoc_Credit_Host_Master() {
		return loc_Credit_Host_Master;
	}

	public void setLoc_Credit_Host_Master(double loc_Credit_Host_Master) {
		this.loc_Credit_Host_Master = loc_Credit_Host_Master;
	}

	public double getLoc_Credit_Host_Union() {
		return loc_Credit_Host_Union;
	}

	public void setLoc_Credit_Host_Union(double loc_Credit_Host_Union) {
		this.loc_Credit_Host_Union = loc_Credit_Host_Union;
	}

	public double getFor_Credit_Host_Visa() {
		return for_Credit_Host_Visa;
	}

	public void setFor_Credit_Host_Visa(double for_Credit_Host_Visa) {
		this.for_Credit_Host_Visa = for_Credit_Host_Visa;
	}

	public double getFor_Credit_Host_Master() {
		return for_Credit_Host_Master;
	}

	public void setFor_Credit_Host_Master(double for_Credit_Host_Master) {
		this.for_Credit_Host_Master = for_Credit_Host_Master;
	}

	public double getFor_Credit_Host_Union() {
		return for_Credit_Host_Union;
	}

	public void setFor_Credit_Host_Union(double for_Credit_Host_Union) {
		this.for_Credit_Host_Union = for_Credit_Host_Union;
	}

	public double getLoc_Debit_Mobi_Visa() {
		return loc_Debit_Mobi_Visa;
	}

	public void setLoc_Debit_Mobi_Visa(double loc_Debit_Mobi_Visa) {
		this.loc_Debit_Mobi_Visa = loc_Debit_Mobi_Visa;
	}

	public double getLoc_Debit_Mobi_Master() {
		return loc_Debit_Mobi_Master;
	}

	public void setLoc_Debit_Mobi_Master(double loc_Debit_Mobi_Master) {
		this.loc_Debit_Mobi_Master = loc_Debit_Mobi_Master;
	}

	public double getLoc_Debit_Mobi_Union() {
		return loc_Debit_Mobi_Union;
	}

	public void setLoc_Debit_Mobi_Union(double loc_Debit_Mobi_Union) {
		this.loc_Debit_Mobi_Union = loc_Debit_Mobi_Union;
	}

	public double getFor_Debit_Mobi_Visa() {
		return for_Debit_Mobi_Visa;
	}

	public void setFor_Debit_Mobi_Visa(double for_Debit_Mobi_Visa) {
		this.for_Debit_Mobi_Visa = for_Debit_Mobi_Visa;
	}

	public double getFor_Debit_Mobi_Master() {
		return for_Debit_Mobi_Master;
	}

	public void setFor_Debit_Mobi_Master(double for_Debit_Mobi_Master) {
		this.for_Debit_Mobi_Master = for_Debit_Mobi_Master;
	}

	public double getFor_Debit_Mobi_Union() {
		return for_Debit_Mobi_Union;
	}

	public void setFor_Debit_Mobi_Union(double for_Debit_Mobi_Union) {
		this.for_Debit_Mobi_Union = for_Debit_Mobi_Union;
	}

	public double getLoc_Credit_Mobi_Visa() {
		return loc_Credit_Mobi_Visa;
	}

	public void setLoc_Credit_Mobi_Visa(double loc_Credit_Mobi_Visa) {
		this.loc_Credit_Mobi_Visa = loc_Credit_Mobi_Visa;
	}

	public double getLoc_Credit_Mobi_Master() {
		return loc_Credit_Mobi_Master;
	}

	public void setLoc_Credit_Mobi_Master(double loc_Credit_Mobi_Master) {
		this.loc_Credit_Mobi_Master = loc_Credit_Mobi_Master;
	}

	public double getLoc_Credit_Mobi_Union() {
		return loc_Credit_Mobi_Union;
	}

	public void setLoc_Credit_Mobi_Union(double loc_Credit_Mobi_Union) {
		this.loc_Credit_Mobi_Union = loc_Credit_Mobi_Union;
	}

	public double getFor_Credit_Mobi_Visa() {
		return for_Credit_Mobi_Visa;
	}

	public void setFor_Credit_Mobi_Visa(double for_Credit_Mobi_Visa) {
		this.for_Credit_Mobi_Visa = for_Credit_Mobi_Visa;
	}

	public double getFor_Credit_Mobi_Master() {
		return for_Credit_Mobi_Master;
	}

	public void setFor_Credit_Mobi_Master(double for_Credit_Mobi_Master) {
		this.for_Credit_Mobi_Master = for_Credit_Mobi_Master;
	}

	public double getFor_Credit_Mobi_Union() {
		return for_Credit_Mobi_Union;
	}

	public void setFor_Credit_Mobi_Union(double for_Credit_Mobi_Union) {
		this.for_Credit_Mobi_Union = for_Credit_Mobi_Union;
	}

	public double getLoc_Credit_Cus_Visa() {
		return loc_Credit_Cus_Visa;
	}

	public void setLoc_Credit_Cus_Visa(double loc_Credit_Cus_Visa) {
		this.loc_Credit_Cus_Visa = loc_Credit_Cus_Visa;
	}

	public double getLoc_Credit_Cus_Master() {
		return loc_Credit_Cus_Master;
	}

	public void setLoc_Credit_Cus_Master(double loc_Credit_Cus_Master) {
		this.loc_Credit_Cus_Master = loc_Credit_Cus_Master;
	}

	public double getLoc_Credit_Cus_Union() {
		return loc_Credit_Cus_Union;
	}

	public void setLoc_Credit_Cus_Union(double loc_Credit_Cus_Union) {
		this.loc_Credit_Cus_Union = loc_Credit_Cus_Union;
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

	public Quotation getQuotationId() {
		return quotation;
	}

	public void setQuotationId(Quotation quotationId) {
		this.quotation = quotationId;
	}
	
	

	/*
	 * public Product getProduct() { return product; }
	 * 
	 * public void setProduct(Product product) { this.product = product; }
	 */

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

	@Override
	public String toString() {
		return "QuotationMDRRate [id=" + id + ", createdOn=" + createdOn + ", quotation=" + quotation + ", productName="
				+ productName + ", includeWallet=" + includeWallet + ", showInQuote=" + showInQuote + ", payLater="
				+ payLater + ", subscription=" + subscription + ", unitPrice=" + unitPrice + ", productType="
				+ productType + ", hostType=" + hostType + ", hostRateIdRef=" + hostRateIdRef
				+ ", loc_Debit_Merch_Visa=" + loc_Debit_Merch_Visa + ", loc_Debit_Merch_Master="
				+ loc_Debit_Merch_Master + ", loc_Debit_Merch_Union=" + loc_Debit_Merch_Union
				+ ", for_Debit_Merch_Visa=" + for_Debit_Merch_Visa + ", for_Debit_Merch_Master="
				+ for_Debit_Merch_Master + ", for_Debit_Merch_Union=" + for_Debit_Merch_Union
				+ ", loc_Credit_Merch_Visa=" + loc_Credit_Merch_Visa + ", loc_Credit_Merch_Master="
				+ loc_Credit_Merch_Master + ", loc_Credit_Merch_Union=" + loc_Credit_Merch_Union
				+ ", for_Credit_Merch_Visa=" + for_Credit_Merch_Visa + ", for_Credit_Merch_Master="
				+ for_Credit_Merch_Master + ", for_Credit_Merch_Union=" + for_Credit_Merch_Union
				+ ", loc_Debit_Host_Visa=" + loc_Debit_Host_Visa + ", loc_Debit_Host_Master=" + loc_Debit_Host_Master
				+ ", loc_Debit_Host_Union=" + loc_Debit_Host_Union + ", for_Debit_Host_Visa=" + for_Debit_Host_Visa
				+ ", for_Debit_Host_Master=" + for_Debit_Host_Master + ", for_Debit_Host_Union=" + for_Debit_Host_Union
				+ ", loc_Credit_Host_Visa=" + loc_Credit_Host_Visa + ", loc_Credit_Host_Master="
				+ loc_Credit_Host_Master + ", loc_Credit_Host_Union=" + loc_Credit_Host_Union
				+ ", for_Credit_Host_Visa=" + for_Credit_Host_Visa + ", for_Credit_Host_Master="
				+ for_Credit_Host_Master + ", for_Credit_Host_Union=" + for_Credit_Host_Union + ", loc_Debit_Mobi_Visa="
				+ loc_Debit_Mobi_Visa + ", loc_Debit_Mobi_Master=" + loc_Debit_Mobi_Master + ", loc_Debit_Mobi_Union="
				+ loc_Debit_Mobi_Union + ", for_Debit_Mobi_Visa=" + for_Debit_Mobi_Visa + ", for_Debit_Mobi_Master="
				+ for_Debit_Mobi_Master + ", for_Debit_Mobi_Union=" + for_Debit_Mobi_Union + ", loc_Credit_Mobi_Visa="
				+ loc_Credit_Mobi_Visa + ", loc_Credit_Mobi_Master=" + loc_Credit_Mobi_Master
				+ ", loc_Credit_Mobi_Union=" + loc_Credit_Mobi_Union + ", for_Credit_Mobi_Visa=" + for_Credit_Mobi_Visa
				+ ", for_Credit_Mobi_Master=" + for_Credit_Mobi_Master + ", for_Credit_Mobi_Union="
				+ for_Credit_Mobi_Union + ", loc_Credit_Cus_Visa=" + loc_Credit_Cus_Visa + ", loc_Credit_Cus_Master="
				+ loc_Credit_Cus_Master + ", loc_Credit_Cus_Union=" + loc_Credit_Cus_Union + ", boostMDREcomm="
				+ boostMDREcomm + ", boostMDRQR=" + boostMDRQR + ", grabMDREcomm=" + grabMDREcomm + ", grabMDRQR="
				+ grabMDRQR + ", fPXMDR_RM=" + fPXMDR_RM + ", fPXMDR_Percent=" + fPXMDR_Percent + ", productSettlement="
				+ productSettlement + ", boostSettlement=" + boostSettlement + ", grabSettlement=" + grabSettlement
				+ ", fpxSettlement=" + fpxSettlement + ", tngMDREcomm=" + tngMDREcomm + ", tngMDRQR=" + tngMDRQR
				+ ", shopeepayMDREcomm=" + shopeepayMDREcomm + ", shopeepayMDRQR=" + shopeepayMDRQR + ", tngSettlement="
				+ tngSettlement + ", shopeepaySettlement=" + shopeepaySettlement + "]";
	}

	

}
