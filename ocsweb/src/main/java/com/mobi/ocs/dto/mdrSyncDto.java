package com.mobi.ocs.dto;

import java.time.LocalDateTime;


public class mdrSyncDto {

	private int orderLineId;
	private int quotationMdrRateId;
	private int quotationEzysplitMdrRateId;
	private int productId;
	private int quotationId;
	private int quantity;
	private String mid;
	private LocalDateTime createdOn;
	private String tid;
	private String dtl;
	private String hashkey;
	private String callbackURL;
	private String ezywireDeviceId;
	private String ezywireRefNo;

	private String productName;
	private String includeWallet;
	private String showInQuote;
	private String payLater;
	private String subscription;
	private double unitPrice;
	private String productType;
	private String productSettlement;
	private String hostType;
	
	private String ezywayTid;
	private String ezylinkTid;

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
	
	//Ezysplit
	private double loc_Credit_Merch_Master_Ezysplit_Insta3;
	private double loc_Credit_Merch_Master_Ezysplit_Insta6;
	private double loc_Credit_Merch_Master_Ezysplit_Insta9;
	private double loc_Credit_Merch_Master_Ezysplit_Insta12;
	
	private double loc_Credit_Cus_Master_Ezysplit_Insta3;
	private double loc_Credit_Cus_Master_Ezysplit_Insta6;
	private double loc_Credit_Cus_Master_Ezysplit_Insta9;
	private double loc_Credit_Cus_Master_Ezysplit_Insta12;
	
	
	public int getOrderLineId() {
		return orderLineId;
	}
	public void setOrderLineId(int orderLineId) {
		this.orderLineId = orderLineId;
	}
	
	public int getQuotationMdrRateId() {
		return quotationMdrRateId;
	}
	public void setQuotationMdrRateId(int quotationMdrRateId) {
		this.quotationMdrRateId = quotationMdrRateId;
	}
	public int getQuotationEzysplitMdrRateId() {
		return quotationEzysplitMdrRateId;
	}
	public void setQuotationEzysplitMdrRateId(int quotationEzysplitMdrRateId) {
		this.quotationEzysplitMdrRateId = quotationEzysplitMdrRateId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public String getEzywayTid() {
		return ezywayTid;
	}
	public void setEzywayTid(String ezywayTid) {
		this.ezywayTid = ezywayTid;
	}
	public String getEzylinkTid() {
		return ezylinkTid;
	}
	public void setEzylinkTid(String ezylinkTid) {
		this.ezylinkTid = ezylinkTid;
	}
	public int getQuotationId() {
		return quotationId;
	}
	public String getProductSettlement() {
		return productSettlement;
	}
	public void setProductSettlement(String productSettlement) {
		this.productSettlement = productSettlement;
	}
	public void setQuotationId(int quotationId) {
		this.quotationId = quotationId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getDtl() {
		return dtl;
	}
	public void setDtl(String dtl) {
		this.dtl = dtl;
	}
	public String getHashkey() {
		return hashkey;
	}
	public void setHashkey(String hashkey) {
		this.hashkey = hashkey;
	}
	public String getCallbackURL() {
		return callbackURL;
	}
	public void setCallbackURL(String callbackURL) {
		this.callbackURL = callbackURL;
	}
	public String getEzywireDeviceId() {
		return ezywireDeviceId;
	}
	public void setEzywireDeviceId(String ezywireDeviceId) {
		this.ezywireDeviceId = ezywireDeviceId;
	}
	public String getEzywireRefNo() {
		return ezywireRefNo;
	}
	public void setEzywireRefNo(String ezywireRefNo) {
		this.ezywireRefNo = ezywireRefNo;
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
	public double getLoc_Credit_Cus_Master_Ezysplit_Insta3() {
		return loc_Credit_Cus_Master_Ezysplit_Insta3;
	}
	public void setLoc_Credit_Cus_Master_Ezysplit_Insta3(double loc_Credit_Cus_Master_Ezysplit_Insta3) {
		this.loc_Credit_Cus_Master_Ezysplit_Insta3 = loc_Credit_Cus_Master_Ezysplit_Insta3;
	}
	public double getLoc_Credit_Cus_Master_Ezysplit_Insta6() {
		return loc_Credit_Cus_Master_Ezysplit_Insta6;
	}
	public void setLoc_Credit_Cus_Master_Ezysplit_Insta6(double loc_Credit_Cus_Master_Ezysplit_Insta6) {
		this.loc_Credit_Cus_Master_Ezysplit_Insta6 = loc_Credit_Cus_Master_Ezysplit_Insta6;
	}
	public double getLoc_Credit_Cus_Master_Ezysplit_Insta9() {
		return loc_Credit_Cus_Master_Ezysplit_Insta9;
	}
	public void setLoc_Credit_Cus_Master_Ezysplit_Insta9(double loc_Credit_Cus_Master_Ezysplit_Insta9) {
		this.loc_Credit_Cus_Master_Ezysplit_Insta9 = loc_Credit_Cus_Master_Ezysplit_Insta9;
	}
	public double getLoc_Credit_Cus_Master_Ezysplit_Insta12() {
		return loc_Credit_Cus_Master_Ezysplit_Insta12;
	}
	public void setLoc_Credit_Cus_Master_Ezysplit_Insta12(double loc_Credit_Cus_Master_Ezysplit_Insta12) {
		this.loc_Credit_Cus_Master_Ezysplit_Insta12 = loc_Credit_Cus_Master_Ezysplit_Insta12;
	}
	public String getHostType() {
		return hostType;
	}
	public void setHostType(String hostType) {
		this.hostType = hostType;
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
	
	
	
	public double getLoc_Credit_Merch_Master_Ezysplit_Insta3() {
		return loc_Credit_Merch_Master_Ezysplit_Insta3;
	}
	public void setLoc_Credit_Merch_Master_Ezysplit_Insta3(double loc_Credit_Merch_Master_Ezysplit_Insta3) {
		this.loc_Credit_Merch_Master_Ezysplit_Insta3 = loc_Credit_Merch_Master_Ezysplit_Insta3;
	}
	public double getLoc_Credit_Merch_Master_Ezysplit_Insta6() {
		return loc_Credit_Merch_Master_Ezysplit_Insta6;
	}
	public void setLoc_Credit_Merch_Master_Ezysplit_Insta6(double loc_Credit_Merch_Master_Ezysplit_Insta6) {
		this.loc_Credit_Merch_Master_Ezysplit_Insta6 = loc_Credit_Merch_Master_Ezysplit_Insta6;
	}
	public double getLoc_Credit_Merch_Master_Ezysplit_Insta9() {
		return loc_Credit_Merch_Master_Ezysplit_Insta9;
	}
	public void setLoc_Credit_Merch_Master_Ezysplit_Insta9(double loc_Credit_Merch_Master_Ezysplit_Insta9) {
		this.loc_Credit_Merch_Master_Ezysplit_Insta9 = loc_Credit_Merch_Master_Ezysplit_Insta9;
	}
	public double getLoc_Credit_Merch_Master_Ezysplit_Insta12() {
		return loc_Credit_Merch_Master_Ezysplit_Insta12;
	}
	public void setLoc_Credit_Merch_Master_Ezysplit_Insta12(double loc_Credit_Merch_Master_Ezysplit_Insta12) {
		this.loc_Credit_Merch_Master_Ezysplit_Insta12 = loc_Credit_Merch_Master_Ezysplit_Insta12;
	}


	
	
	
	
	
}
