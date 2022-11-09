package com.mobi.ocs.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;

import com.fasterxml.jackson.databind.ser.std.StdArraySerializers.DoubleArraySerializer;

public class MDRData {

	private String productName;
	private double amount;
	private String subscription;
	private String showInQuote;
	private String productType;
	private String includeWallet;
	private String payLater;
	private String hostType;
	private LocalDateTime createdOn;

	private String hostRateId;
	private int quotationMdrRateId;
	private int productId;
	private int quotationId;
	private int orderLineId;

	// Debit Merchant

	private double loc_Debit_Merch_Visa;
	private double loc_Debit_Merch_Master;
	private double loc_Debit_Merch_Union;
	private double for_Debit_Merch_Visa;
	private double for_Debit_Merch_Master;
	private double for_Debit_Merch_Union;

	// Credit Merchant
	private double loc_Credit_Merch_Visa;
	private double loc_Credit_Merch_Master;
	private double loc_Credit_Merch_Union;
	private double for_Credit_Merch_Visa;
	private double for_Credit_Merch_Master;
	private double for_Credit_Merch_Union;

	// Debit Host

	private double loc_Debit_Host_Visa;
	private double loc_Debit_Host_Master;
	private double loc_Debit_Host_Union;
	private double for_Debit_Host_Visa;
	private double for_Debit_Host_Master;
	private double for_Debit_Host_Union;

	// Credit Host
	private double loc_Credit_Host_Visa;
	private double loc_Credit_Host_Master;
	private double loc_Credit_Host_Union;
	private double for_Credit_Host_Visa;
	private double for_Credit_Host_Master;
	private double for_Credit_Host_Union;

	// local Credit Customer
	private double loc_Credit_Cus_Visa;
	private double loc_Credit_Cus_Master;
	private double loc_Credit_Cus_Union;

	// Boost Grab FPX
	private double boostMDREcomm;
	private double grabMDREcomm;
	private double boostMDRQR;
	private double grabMDRQR;
	private double fPXMDR_RM;
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

	// Settlement Periods

	private int productSettlement;
	private int boostSettlement;
	private int grabSettlement;
	private int fpxSettlement;

	// Ezysplit MDR

	private int quotationEzysplitMdrRateId;

	private double loc_Credit_Merch_Master_Insta3;
	private double loc_Credit_Cus_Master_Insta3;
	private double loc_Credit_Host_Master_Insta3;

	private double loc_Credit_Merch_Master_Insta6;
	private double loc_Credit_Cus_Master_Insta6;
	private double loc_Credit_Host_Master_Insta6;

	private double loc_Credit_Merch_Master_Insta9;
	private double loc_Credit_Cus_Master_Insta9;
	private double loc_Credit_Host_Master_Insta9;

	private double loc_Credit_Merch_Master_Insta12;
	private double loc_Credit_Cus_Master_Insta12;
	private double loc_Credit_Host_Master_Insta12;

	public MDRData() {
		this.createdOn = LocalDateTime.now();
	}

	public int getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(int quotationId) {
		this.quotationId = quotationId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuotationMdrRateId() {
		return quotationMdrRateId;
	}

	public void setQuotationMdrRateId(int quotationMdrRateId) {
		this.quotationMdrRateId = quotationMdrRateId;
	}

	public int getOrderLineId() {
		return orderLineId;
	}

	public void setOrderLineId(int orderLineId) {
		this.orderLineId = orderLineId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getShowInQuote() {
		return showInQuote;
	}

	public void setShowInQuote(String showInQuote) {
		this.showInQuote = showInQuote;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getIncludeWallet() {
		return includeWallet;
	}

	public void setIncludeWallet(String includeWallet) {
		this.includeWallet = includeWallet;
	}

	public String getPayLater() {
		return payLater;
	}

	public void setPayLater(String payLater) {
		this.payLater = payLater;
	}

	public String getHostType() {
		return hostType;
	}

	public void setHostType(String hostType) {
		this.hostType = hostType;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public String getSubscription() {
		return subscription;
	}

	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getHostRateId() {
		return hostRateId;
	}

	public void setHostRateId(String hostRateId) {
		this.hostRateId = hostRateId;
	}

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

	public double getGrabMDREcomm() {
		return grabMDREcomm;
	}

	public void setGrabMDREcomm(double grabMDREcomm) {
		this.grabMDREcomm = grabMDREcomm;
	}

	public double getBoostMDRQR() {
		return boostMDRQR;
	}

	public void setBoostMDRQR(double boostMDRQR) {
		this.boostMDRQR = boostMDRQR;
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

	public int getQuotationEzysplitMdrRateId() {
		return quotationEzysplitMdrRateId;
	}

	public void setQuotationEzysplitMdrRateId(int quotationEzysplitMdrRateId) {
		this.quotationEzysplitMdrRateId = quotationEzysplitMdrRateId;
	}

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

	public double getLoc_Credit_Host_Master_Insta3() {
		return loc_Credit_Host_Master_Insta3;
	}

	public void setLoc_Credit_Host_Master_Insta3(double loc_Credit_Host_Master_Insta3) {
		this.loc_Credit_Host_Master_Insta3 = loc_Credit_Host_Master_Insta3;
	}

	public double getLoc_Credit_Host_Master_Insta6() {
		return loc_Credit_Host_Master_Insta6;
	}

	public void setLoc_Credit_Host_Master_Insta6(double loc_Credit_Host_Master_Insta6) {
		this.loc_Credit_Host_Master_Insta6 = loc_Credit_Host_Master_Insta6;
	}

	public double getLoc_Credit_Host_Master_Insta9() {
		return loc_Credit_Host_Master_Insta9;
	}

	public void setLoc_Credit_Host_Master_Insta9(double loc_Credit_Host_Master_Insta9) {
		this.loc_Credit_Host_Master_Insta9 = loc_Credit_Host_Master_Insta9;
	}

	public double getLoc_Credit_Host_Master_Insta12() {
		return loc_Credit_Host_Master_Insta12;
	}

	public void setLoc_Credit_Host_Master_Insta12(double loc_Credit_Host_Master_Insta12) {
		this.loc_Credit_Host_Master_Insta12 = loc_Credit_Host_Master_Insta12;
	}

	@Override
	public String toString() {
		return "MDRData [productName=" + productName + ", amount=" + amount + ", subscription=" + subscription
				+ ", showInQuote=" + showInQuote + ", productType=" + productType + ", includeWallet=" + includeWallet
				+ ", payLater=" + payLater + ", hostType=" + hostType + ", createdOn=" + createdOn + ", hostRateId="
				+ hostRateId + ", quotationMdrRateId=" + quotationMdrRateId + ", productId=" + productId
				+ ", quotationId=" + quotationId + ", orderLineId=" + orderLineId + ", loc_Debit_Merch_Visa="
				+ loc_Debit_Merch_Visa + ", loc_Debit_Merch_Master=" + loc_Debit_Merch_Master
				+ ", loc_Debit_Merch_Union=" + loc_Debit_Merch_Union + ", for_Debit_Merch_Visa=" + for_Debit_Merch_Visa
				+ ", for_Debit_Merch_Master=" + for_Debit_Merch_Master + ", for_Debit_Merch_Union="
				+ for_Debit_Merch_Union + ", loc_Credit_Merch_Visa=" + loc_Credit_Merch_Visa
				+ ", loc_Credit_Merch_Master=" + loc_Credit_Merch_Master + ", loc_Credit_Merch_Union="
				+ loc_Credit_Merch_Union + ", for_Credit_Merch_Visa=" + for_Credit_Merch_Visa
				+ ", for_Credit_Merch_Master=" + for_Credit_Merch_Master + ", for_Credit_Merch_Union="
				+ for_Credit_Merch_Union + ", loc_Debit_Host_Visa=" + loc_Debit_Host_Visa + ", loc_Debit_Host_Master="
				+ loc_Debit_Host_Master + ", loc_Debit_Host_Union=" + loc_Debit_Host_Union + ", for_Debit_Host_Visa="
				+ for_Debit_Host_Visa + ", for_Debit_Host_Master=" + for_Debit_Host_Master + ", for_Debit_Host_Union="
				+ for_Debit_Host_Union + ", loc_Credit_Host_Visa=" + loc_Credit_Host_Visa + ", loc_Credit_Host_Master="
				+ loc_Credit_Host_Master + ", loc_Credit_Host_Union=" + loc_Credit_Host_Union
				+ ", for_Credit_Host_Visa=" + for_Credit_Host_Visa + ", for_Credit_Host_Master="
				+ for_Credit_Host_Master + ", for_Credit_Host_Union=" + for_Credit_Host_Union + ", loc_Credit_Cus_Visa="
				+ loc_Credit_Cus_Visa + ", loc_Credit_Cus_Master=" + loc_Credit_Cus_Master + ", loc_Credit_Cus_Union="
				+ loc_Credit_Cus_Union + ", boostMDREcomm=" + boostMDREcomm + ", grabMDREcomm=" + grabMDREcomm
				+ ", boostMDRQR=" + boostMDRQR + ", grabMDRQR=" + grabMDRQR + ", fPXMDR_RM=" + fPXMDR_RM
				+ ", fPXMDR_Percent=" + fPXMDR_Percent + ", tngMDREcomm=" + tngMDREcomm + ", tngMDRQR=" + tngMDRQR
				+ ", shopeepayMDREcomm=" + shopeepayMDREcomm + ", shopeepayMDRQR=" + shopeepayMDRQR + ", tngSettlement="
				+ tngSettlement + ", shopeepaySettlement=" + shopeepaySettlement + ", productSettlement="
				+ productSettlement + ", boostSettlement=" + boostSettlement + ", grabSettlement=" + grabSettlement
				+ ", fpxSettlement=" + fpxSettlement + ", quotationEzysplitMdrRateId=" + quotationEzysplitMdrRateId
				+ ", loc_Credit_Merch_Master_Insta3=" + loc_Credit_Merch_Master_Insta3
				+ ", loc_Credit_Cus_Master_Insta3=" + loc_Credit_Cus_Master_Insta3 + ", loc_Credit_Host_Master_Insta3="
				+ loc_Credit_Host_Master_Insta3 + ", loc_Credit_Merch_Master_Insta6=" + loc_Credit_Merch_Master_Insta6
				+ ", loc_Credit_Cus_Master_Insta6=" + loc_Credit_Cus_Master_Insta6 + ", loc_Credit_Host_Master_Insta6="
				+ loc_Credit_Host_Master_Insta6 + ", loc_Credit_Merch_Master_Insta9=" + loc_Credit_Merch_Master_Insta9
				+ ", loc_Credit_Cus_Master_Insta9=" + loc_Credit_Cus_Master_Insta9 + ", loc_Credit_Host_Master_Insta9="
				+ loc_Credit_Host_Master_Insta9 + ", loc_Credit_Merch_Master_Insta12=" + loc_Credit_Merch_Master_Insta12
				+ ", loc_Credit_Cus_Master_Insta12=" + loc_Credit_Cus_Master_Insta12
				+ ", loc_Credit_Host_Master_Insta12=" + loc_Credit_Host_Master_Insta12 + ", getQuotationId()="
				+ getQuotationId() + ", getProductId()=" + getProductId() + ", getQuotationMdrRateId()="
				+ getQuotationMdrRateId() + ", getOrderLineId()=" + getOrderLineId() + ", getProductName()="
				+ getProductName() + ", getShowInQuote()=" + getShowInQuote() + ", getProductType()=" + getProductType()
				+ ", getIncludeWallet()=" + getIncludeWallet() + ", getPayLater()=" + getPayLater() + ", getHostType()="
				+ getHostType() + ", getCreatedOn()=" + getCreatedOn() + ", getSubscription()=" + getSubscription()
				+ ", getAmount()=" + getAmount() + ", getHostRateId()=" + getHostRateId()
				+ ", getLoc_Debit_Merch_Visa()=" + getLoc_Debit_Merch_Visa() + ", getLoc_Debit_Merch_Master()="
				+ getLoc_Debit_Merch_Master() + ", getLoc_Debit_Merch_Union()=" + getLoc_Debit_Merch_Union()
				+ ", getFor_Debit_Merch_Visa()=" + getFor_Debit_Merch_Visa() + ", getFor_Debit_Merch_Master()="
				+ getFor_Debit_Merch_Master() + ", getFor_Debit_Merch_Union()=" + getFor_Debit_Merch_Union()
				+ ", getLoc_Credit_Merch_Visa()=" + getLoc_Credit_Merch_Visa() + ", getLoc_Credit_Merch_Master()="
				+ getLoc_Credit_Merch_Master() + ", getLoc_Credit_Merch_Union()=" + getLoc_Credit_Merch_Union()
				+ ", getFor_Credit_Merch_Visa()=" + getFor_Credit_Merch_Visa() + ", getFor_Credit_Merch_Master()="
				+ getFor_Credit_Merch_Master() + ", getFor_Credit_Merch_Union()=" + getFor_Credit_Merch_Union()
				+ ", getLoc_Debit_Host_Visa()=" + getLoc_Debit_Host_Visa() + ", getLoc_Debit_Host_Master()="
				+ getLoc_Debit_Host_Master() + ", getLoc_Debit_Host_Union()=" + getLoc_Debit_Host_Union()
				+ ", getFor_Debit_Host_Visa()=" + getFor_Debit_Host_Visa() + ", getFor_Debit_Host_Master()="
				+ getFor_Debit_Host_Master() + ", getFor_Debit_Host_Union()=" + getFor_Debit_Host_Union()
				+ ", getLoc_Credit_Host_Visa()=" + getLoc_Credit_Host_Visa() + ", getLoc_Credit_Host_Master()="
				+ getLoc_Credit_Host_Master() + ", getLoc_Credit_Host_Union()=" + getLoc_Credit_Host_Union()
				+ ", getFor_Credit_Host_Visa()=" + getFor_Credit_Host_Visa() + ", getFor_Credit_Host_Master()="
				+ getFor_Credit_Host_Master() + ", getFor_Credit_Host_Union()=" + getFor_Credit_Host_Union()
				+ ", getLoc_Credit_Cus_Visa()=" + getLoc_Credit_Cus_Visa() + ", getLoc_Credit_Cus_Master()="
				+ getLoc_Credit_Cus_Master() + ", getLoc_Credit_Cus_Union()=" + getLoc_Credit_Cus_Union()
				+ ", getBoostMDREcomm()=" + getBoostMDREcomm() + ", getGrabMDREcomm()=" + getGrabMDREcomm()
				+ ", getBoostMDRQR()=" + getBoostMDRQR() + ", getGrabMDRQR()=" + getGrabMDRQR() + ", getfPXMDR_RM()="
				+ getfPXMDR_RM() + ", getfPXMDR_Percent()=" + getfPXMDR_Percent() + ", getProductSettlement()="
				+ getProductSettlement() + ", getBoostSettlement()=" + getBoostSettlement() + ", getGrabSettlement()="
				+ getGrabSettlement() + ", getFpxSettlement()=" + getFpxSettlement()
				+ ", getLoc_Credit_Merch_Master_Insta3()=" + getLoc_Credit_Merch_Master_Insta3()
				+ ", getLoc_Credit_Cus_Master_Insta3()=" + getLoc_Credit_Cus_Master_Insta3()
				+ ", getLoc_Credit_Merch_Master_Insta6()=" + getLoc_Credit_Merch_Master_Insta6()
				+ ", getLoc_Credit_Cus_Master_Insta6()=" + getLoc_Credit_Cus_Master_Insta6()
				+ ", getLoc_Credit_Merch_Master_Insta9()=" + getLoc_Credit_Merch_Master_Insta9()
				+ ", getLoc_Credit_Cus_Master_Insta9()=" + getLoc_Credit_Cus_Master_Insta9()
				+ ", getLoc_Credit_Merch_Master_Insta12()=" + getLoc_Credit_Merch_Master_Insta12()
				+ ", getLoc_Credit_Cus_Master_Insta12()=" + getLoc_Credit_Cus_Master_Insta12()
				+ ", getQuotationEzysplitMdrRateId()=" + getQuotationEzysplitMdrRateId() + ", getTngMDREcomm()="
				+ getTngMDREcomm() + ", getTngMDRQR()=" + getTngMDRQR() + ", getShopeepayMDREcomm()="
				+ getShopeepayMDREcomm() + ", getShopeepayMDRQR()=" + getShopeepayMDRQR() + ", getTngSettlement()="
				+ getTngSettlement() + ", getShopeepaySettlement()=" + getShopeepaySettlement()
				+ ", getLoc_Credit_Host_Master_Insta3()=" + getLoc_Credit_Host_Master_Insta3()
				+ ", getLoc_Credit_Host_Master_Insta6()=" + getLoc_Credit_Host_Master_Insta6()
				+ ", getLoc_Credit_Host_Master_Insta9()=" + getLoc_Credit_Host_Master_Insta9()
				+ ", getLoc_Credit_Host_Master_Insta12()=" + getLoc_Credit_Host_Master_Insta12() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
