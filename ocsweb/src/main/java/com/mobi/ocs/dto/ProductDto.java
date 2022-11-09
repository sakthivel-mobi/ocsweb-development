package com.mobi.ocs.dto;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.mobi.ocs.entity.OrderType;
import com.mobi.ocs.entity.ProductType;
import com.mobi.ocs.entity.QuotationMDRRate;
import com.mobi.ocs.entity.StandardMDRRate;

public class ProductDto {

     private int id;
	private Timestamp createdOn;
	private String productName;
	private boolean includeWallet;
	private boolean payLater;
	private int subscription;
	private double unitPrice;
	private String productType;
	private String hostType;
	private int standardMdrRate;
	private int quotationMdr;
	private int orderTypeID;
	
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

		// Debit Mobi
		private double loc_Debit_Mobi_Visa;
		private double loc_Debit_Mobi_Master;
		private double loc_Debit_Mobi_Union;
		private double for_Debit_Mobi_Visa;
		private double for_Debit_Mobi_Master;
		private double for_Debit_Mobi_Union;

		// Credit Mobi
		private double loc_Credit_Mobi_Visa;
		private double loc_Credit_Mobi_Master;
		private double loc_Credit_Mobi_Union;
		private double for_Credit_Mobi_Visa;
		private double for_Credit_Mobi_Master;
		private double for_Credit_Mobi_Union;

		// local Credit Customer
		private double loc_Credit_Cus_Visa;
		private double loc_Credit_Cus_Master;
		private double loc_Credit_Cus_Union;
		
		//Boost Grab FPX
		private double boostMDR;
		private double grabMDR;
		private double fPXMDR_RM;
		private double fPXMDR_Percent;
		
		//Settlement Periods
		
		private int productSettlement;
		private int boostSettlement;
		private int grabSettlement;
		private int fpxSettlement;
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public boolean isIncludeWallet() {
		return includeWallet;
	}
	public void setIncludeWallet(boolean includeWallet) {
		this.includeWallet = includeWallet;
	}
	public boolean isPayLater() {
		return payLater;
	}
	public void setPayLater(boolean payLater) {
		this.payLater = payLater;
	}
	public int getSubscription() {
		return subscription;
	}
	public void setSubscription(int subscription) {
		this.subscription = subscription;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getHostType() {
		return hostType;
	}
	public void setHostType(String hostType) {
		this.hostType = hostType;
	}
	
	
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public int getStandardMdrRate() {
		return standardMdrRate;
	}
	public void setStandardMdrRate(int standardMdrRate) {
		this.standardMdrRate = standardMdrRate;
	}
	public int getQuotationMdr() {
		return quotationMdr;
	}
	public void setQuotationMdr(int quotationMdr) {
		this.quotationMdr = quotationMdr;
	}
	public int getOrderTypeID() {
		return orderTypeID;
	}
	public void setOrderTypeID(int orderTypeID) {
		this.orderTypeID = orderTypeID;
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
	public double getBoostMDR() {
		return boostMDR;
	}
	public void setBoostMDR(double boostMDR) {
		this.boostMDR = boostMDR;
	}
	public double getGrabMDR() {
		return grabMDR;
	}
	public void setGrabMDR(double grabMDR) {
		this.grabMDR = grabMDR;
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
	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", createdOn=" + createdOn + ", productName=" + productName + ", includeWallet="
				+ includeWallet + ", payLater=" + payLater + ", subscription=" + subscription + ", unitPrice="
				+ unitPrice + ", productType=" + productType + ", hostType=" + hostType + ", standardMdrRate="
				+ standardMdrRate + ", quotationMdr=" + quotationMdr + ", orderTypeID=" + orderTypeID
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
				+ loc_Credit_Cus_Master + ", loc_Credit_Cus_Union=" + loc_Credit_Cus_Union + ", boostMDR=" + boostMDR
				+ ", grabMDR=" + grabMDR + ", fPXMDR_RM=" + fPXMDR_RM + ", fPXMDR_Percent=" + fPXMDR_Percent
				+ ", productSettlement=" + productSettlement + ", boostSettlement=" + boostSettlement
				+ ", grabSettlement=" + grabSettlement + ", fpxSettlement=" + fpxSettlement + "]";
	}
	
	
}
