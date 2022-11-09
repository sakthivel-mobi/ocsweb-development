package com.mobi.ocs.dto;

public class MDRSyncToPaymentServer {

	private String service;
	private String payLater;
	private String amount;
	private String settlePeriod;

	private String mid;
	private String tid;
	private String boostTid;
	private String dtl;
	private String hashKey;
	private String callBackUrl;
	private String subscription;
	private String productType;
	private String refereeMID;
	private String forceReferral;
	private String deviceId;
	private String refNo;
	private String hostType;

	private double fpxHostAmt;
	private double fpxMercAmt;
	private double fpxMobiAmt;
	private double fpxTxnMdr;

	private double boostEcomHostMdr;
	private double boostEcomMercMdr;
	private double boostEcomMobiMdr;

	private double grabEcomHostMdr;
	private double grabEcomMercMdr;
	private double grabEcomMobiMdr;

	private double boostQrHostMdr;
	private double boostQrMercMdr;
	private double boostQrMobiMdr;

	private double grabQrHostMdr;
	private double grabQrMercMdr;
	private double grabQrMobiMdr;
	
	private double tngQrHostMdr;
	private double tngQrMercMdr;
	private double tngQrMobiMdr;
	
	private double shopeepayQrHostMdr;
	private double shopeepayQrMercMdr;
	private double shopeepayQrMobiMdr;
	
	public double getTngQrHostMdr() {
		return tngQrHostMdr;
	}

	public void setTngQrHostMdr(double tngQrHostMdr) {
		this.tngQrHostMdr = tngQrHostMdr;
	}

	public double getTngQrMercMdr() {
		return tngQrMercMdr;
	}

	public void setTngQrMercMdr(double tngQrMercMdr) {
		this.tngQrMercMdr = tngQrMercMdr;
	}

	public double getTngQrMobiMdr() {
		return tngQrMobiMdr;
	}

	public void setTngQrMobiMdr(double tngQrMobiMdr) {
		this.tngQrMobiMdr = tngQrMobiMdr;
	}

	public double getShopeepayQrHostMdr() {
		return shopeepayQrHostMdr;
	}

	public void setShopeepayQrHostMdr(double shopeepayQrHostMdr) {
		this.shopeepayQrHostMdr = shopeepayQrHostMdr;
	}

	public double getShopeepayQrMercMdr() {
		return shopeepayQrMercMdr;
	}

	public void setShopeepayQrMercMdr(double shopeepayQrMercMdr) {
		this.shopeepayQrMercMdr = shopeepayQrMercMdr;
	}

	public double getShopeepayQrMobiMdr() {
		return shopeepayQrMobiMdr;
	}

	public void setShopeepayQrMobiMdr(double shopeepayQrMobiMdr) {
		this.shopeepayQrMobiMdr = shopeepayQrMobiMdr;
	}

	public double getTngEcomHostMdr() {
		return tngEcomHostMdr;
	}

	public void setTngEcomHostMdr(double tngEcomHostMdr) {
		this.tngEcomHostMdr = tngEcomHostMdr;
	}

	public double getTngEcomMercMdr() {
		return tngEcomMercMdr;
	}

	public void setTngEcomMercMdr(double tngEcomMercMdr) {
		this.tngEcomMercMdr = tngEcomMercMdr;
	}

	public double getTngEcomMobiMdr() {
		return tngEcomMobiMdr;
	}

	public void setTngEcomMobiMdr(double tngEcomMobiMdr) {
		this.tngEcomMobiMdr = tngEcomMobiMdr;
	}

	public double getShopeepayEcomHostMdr() {
		return shopeepayEcomHostMdr;
	}

	public void setShopeepayEcomHostMdr(double shopeepayEcomHostMdr) {
		this.shopeepayEcomHostMdr = shopeepayEcomHostMdr;
	}

	public double getShopeepayEcomMercMdr() {
		return shopeepayEcomMercMdr;
	}

	public void setShopeepayEcomMercMdr(double shopeepayEcomMercMdr) {
		this.shopeepayEcomMercMdr = shopeepayEcomMercMdr;
	}

	public double getShopeepayEcomMobiMdr() {
		return shopeepayEcomMobiMdr;
	}

	public void setShopeepayEcomMobiMdr(double shopeepayEcomMobiMdr) {
		this.shopeepayEcomMobiMdr = shopeepayEcomMobiMdr;
	}

	private double tngEcomHostMdr;
	private double tngEcomMercMdr;
	private double tngEcomMobiMdr;
	
	
	private double shopeepayEcomHostMdr;
	private double shopeepayEcomMercMdr;
	private double shopeepayEcomMobiMdr;
	
	
	
	
	

	private String businessRegNo;

	private String cardBrand;
	private double creditLocalMerchantMDR;
	private double creditLocalHostMDR;
	private double debitForeignMerchantMDR;
	private double debitLocalMerchantMDR;
	private double creditForeignMerchantMDR;
	private double debitForeignHostMDR;
	private double debitLocalHostMDR;
	private double creditForeignHostMDR;

	// EZYSPLIT
	private String rateId03;
	private String rateId06;
	private String rateId09;
	private String rateId12;

	private String ezylinkTid;
	private String ezywayTid;
	private String ezysplitMid;
	private String ezysplitTid;

	private String preAuth;

	public String getPreAuth() {
		return preAuth;
	}

	public void setPreAuth(String preAuth) {
		this.preAuth = preAuth;
	}

	public String getBoostTid() {
		return boostTid;
	}

	public void setBoostTid(String boostTid) {
		this.boostTid = boostTid;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getPayLater() {
		return payLater;
	}

	public void setPayLater(String payLater) {
		this.payLater = payLater;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String formattedAmount) {
		this.amount = formattedAmount;
	}

	public String getSettlePeriod() {
		return settlePeriod;
	}

	public void setSettlePeriod(String settlePeriod) {
		this.settlePeriod = settlePeriod;
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

	public String getHashKey() {
		return hashKey;
	}

	public void setHashKey(String hashKey) {
		this.hashKey = hashKey;
	}

	public String getCallBackUrl() {
		return callBackUrl;
	}

	public void setCallBackUrl(String callBackUrl) {
		this.callBackUrl = callBackUrl;
	}

	public String getSubscription() {
		return subscription;
	}

	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getRefereeMID() {
		return refereeMID;
	}

	public void setRefereeMID(String refreeMid) {
		this.refereeMID = refreeMid;
	}

	public String getForceReferral() {
		return forceReferral;
	}

	public void setForceReferral(String forceReferral) {
		this.forceReferral = forceReferral;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getHostType() {
		return hostType;
	}

	public void setHostType(String hostType) {
		this.hostType = hostType;
	}

	public double getFpxHostAmt() {
		return fpxHostAmt;
	}

	public void setFpxHostAmt(double fpxHostAmt) {
		this.fpxHostAmt = fpxHostAmt;
	}

	public double getFpxMercAmt() {
		return fpxMercAmt;
	}

	public void setFpxMercAmt(double fpxMercAmt) {
		this.fpxMercAmt = fpxMercAmt;
	}

	public double getFpxMobiAmt() {
		return fpxMobiAmt;
	}

	public void setFpxMobiAmt(double fpxMobiAmt) {
		this.fpxMobiAmt = fpxMobiAmt;
	}

	public double getFpxTxnMdr() {
		return fpxTxnMdr;
	}

	public void setFpxTxnMdr(double fpxTxnMdr) {
		this.fpxTxnMdr = fpxTxnMdr;
	}

	public double getBoostEcomHostMdr() {
		return boostEcomHostMdr;
	}

	public void setBoostEcomHostMdr(double boostEcomHostMdr) {
		this.boostEcomHostMdr = boostEcomHostMdr;
	}

	public double getBoostEcomMercMdr() {
		return boostEcomMercMdr;
	}

	public void setBoostEcomMercMdr(double boostEcomMercMdr) {
		this.boostEcomMercMdr = boostEcomMercMdr;
	}

	public double getBoostEcomMobiMdr() {
		return boostEcomMobiMdr;
	}

	public void setBoostEcomMobiMdr(double boostEcomMobiMdr) {
		this.boostEcomMobiMdr = boostEcomMobiMdr;
	}

	public double getGrabEcomHostMdr() {
		return grabEcomHostMdr;
	}

	public void setGrabEcomHostMdr(double grabEcomHostMdr) {
		this.grabEcomHostMdr = grabEcomHostMdr;
	}

	public double getGrabEcomMercMdr() {
		return grabEcomMercMdr;
	}

	public void setGrabEcomMercMdr(double grabEcomMercMdr) {
		this.grabEcomMercMdr = grabEcomMercMdr;
	}

	public double getGrabEcomMobiMdr() {
		return grabEcomMobiMdr;
	}

	public void setGrabEcomMobiMdr(double grabEcomMobiMdr) {
		this.grabEcomMobiMdr = grabEcomMobiMdr;
	}

	public double getBoostQrHostMdr() {
		return boostQrHostMdr;
	}

	public void setBoostQrHostMdr(double boostQrHostMdr) {
		this.boostQrHostMdr = boostQrHostMdr;
	}

	public double getBoostQrMercMdr() {
		return boostQrMercMdr;
	}

	public void setBoostQrMercMdr(double boostQrMercMdr) {
		this.boostQrMercMdr = boostQrMercMdr;
	}

	public double getBoostQrMobiMdr() {
		return boostQrMobiMdr;
	}

	public void setBoostQrMobiMdr(double boostQrMobiMdr) {
		this.boostQrMobiMdr = boostQrMobiMdr;
	}

	public double getGrabQrHostMdr() {
		return grabQrHostMdr;
	}

	public void setGrabQrHostMdr(double grabQrHostMdr) {
		this.grabQrHostMdr = grabQrHostMdr;
	}

	public double getGrabQrMercMdr() {
		return grabQrMercMdr;
	}

	public void setGrabQrMercMdr(double grabQrMercMdr) {
		this.grabQrMercMdr = grabQrMercMdr;
	}

	public double getGrabQrMobiMdr() {
		return grabQrMobiMdr;
	}

	public void setGrabQrMobiMdr(double grabQrMobiMdr) {
		this.grabQrMobiMdr = grabQrMobiMdr;
	}

	public String getBusinessRegNo() {
		return businessRegNo;
	}

	public void setBusinessRegNo(String businessRegNo) {
		this.businessRegNo = businessRegNo;
	}

	public String getCardBrand() {
		return cardBrand;
	}

	public void setCardBrand(String cardBrand) {
		this.cardBrand = cardBrand;
	}

	public double getCreditLocalMerchantMDR() {
		return creditLocalMerchantMDR;
	}

	public void setCreditLocalMerchantMDR(double creditLocalMerchantMDR) {
		this.creditLocalMerchantMDR = creditLocalMerchantMDR;
	}

	public double getCreditLocalHostMDR() {
		return creditLocalHostMDR;
	}

	public void setCreditLocalHostMDR(double creditLocalHostMDR) {
		this.creditLocalHostMDR = creditLocalHostMDR;
	}

	public double getDebitForeignMerchantMDR() {
		return debitForeignMerchantMDR;
	}

	public void setDebitForeignMerchantMDR(double debitForeignMerchantMDR) {
		this.debitForeignMerchantMDR = debitForeignMerchantMDR;
	}

	public double getDebitLocalMerchantMDR() {
		return debitLocalMerchantMDR;
	}

	public void setDebitLocalMerchantMDR(double debitLocalMerchantMDR) {
		this.debitLocalMerchantMDR = debitLocalMerchantMDR;
	}

	public double getCreditForeignMerchantMDR() {
		return creditForeignMerchantMDR;
	}

	public void setCreditForeignMerchantMDR(double creditForeignMerchantMDR) {
		this.creditForeignMerchantMDR = creditForeignMerchantMDR;
	}

	public double getDebitForeignHostMDR() {
		return debitForeignHostMDR;
	}

	public void setDebitForeignHostMDR(double debitForeignHostMDR) {
		this.debitForeignHostMDR = debitForeignHostMDR;
	}

	public double getDebitLocalHostMDR() {
		return debitLocalHostMDR;
	}

	public void setDebitLocalHostMDR(double debitLocalHostMDR) {
		this.debitLocalHostMDR = debitLocalHostMDR;
	}

	public double getCreditForeignHostMDR() {
		return creditForeignHostMDR;
	}

	public void setCreditForeignHostMDR(double creditForeignHostMDR) {
		this.creditForeignHostMDR = creditForeignHostMDR;
	}

	public String getRateId03() {
		return rateId03;
	}

	public void setRateId03(String rateId03) {
		this.rateId03 = rateId03;
	}

	public String getRateId06() {
		return rateId06;
	}

	public void setRateId06(String rateId06) {
		this.rateId06 = rateId06;
	}

	public String getRateId09() {
		return rateId09;
	}

	public void setRateId09(String rateId09) {
		this.rateId09 = rateId09;
	}

	public String getRateId12() {
		return rateId12;
	}

	public void setRateId12(String rateId12) {
		this.rateId12 = rateId12;
	}

	public String getEzylinkTid() {
		return ezylinkTid;
	}

	public void setEzylinkTid(String ezylinkTid) {
		this.ezylinkTid = ezylinkTid;
	}

	public String getEzywayTid() {
		return ezywayTid;
	}

	public void setEzywayTid(String ezywayTid) {
		this.ezywayTid = ezywayTid;
	}

	public String getEzysplitMid() {
		return ezysplitMid;
	}

	public void setEzysplitMid(String ezysplitMid) {
		this.ezysplitMid = ezysplitMid;
	}

	public String getEzysplitTid() {
		return ezysplitTid;
	}

	public void setEzysplitTid(String ezysplitTid) {
		this.ezysplitTid = ezysplitTid;
	}

	@Override
	public String toString() {
		return "MDRSyncToPaymentServer [service=" + service + ", payLater=" + payLater + ", amount=" + amount
				+ ", settlePeriod=" + settlePeriod + ", mid=" + mid + ", tid=" + tid + ", boostTid=" + boostTid
				+ ", dtl=" + dtl + ", hashKey=" + hashKey + ", callBackUrl=" + callBackUrl + ", subscription="
				+ subscription + ", productType=" + productType + ", refereeMID=" + refereeMID + ", forceReferral="
				+ forceReferral + ", deviceId=" + deviceId + ", refNo=" + refNo + ", hostType=" + hostType
				+ ", fpxHostAmt=" + fpxHostAmt + ", fpxMercAmt=" + fpxMercAmt + ", fpxMobiAmt=" + fpxMobiAmt
				+ ", fpxTxnMdr=" + fpxTxnMdr + ", boostEcomHostMdr=" + boostEcomHostMdr + ", boostEcomMercMdr="
				+ boostEcomMercMdr + ", boostEcomMobiMdr=" + boostEcomMobiMdr + ", grabEcomHostMdr=" + grabEcomHostMdr
				+ ", grabEcomMercMdr=" + grabEcomMercMdr + ", grabEcomMobiMdr=" + grabEcomMobiMdr + ", boostQrHostMdr="
				+ boostQrHostMdr + ", boostQrMercMdr=" + boostQrMercMdr + ", boostQrMobiMdr=" + boostQrMobiMdr
				+ ", grabQrHostMdr=" + grabQrHostMdr + ", grabQrMercMdr=" + grabQrMercMdr + ", grabQrMobiMdr="
				+ grabQrMobiMdr + ", businessRegNo=" + businessRegNo + ", cardBrand=" + cardBrand
				+ ", creditLocalMerchantMDR=" + creditLocalMerchantMDR + ", creditLocalHostMDR=" + creditLocalHostMDR
				+ ", debitForeignMerchantMDR=" + debitForeignMerchantMDR + ", debitLocalMerchantMDR="
				+ debitLocalMerchantMDR + ", creditForeignMerchantMDR=" + creditForeignMerchantMDR
				+ ", debitForeignHostMDR=" + debitForeignHostMDR + ", debitLocalHostMDR=" + debitLocalHostMDR
				+ ", creditForeignHostMDR=" + creditForeignHostMDR + ", rateId03=" + rateId03 + ", rateId06=" + rateId06
				+ ", rateId09=" + rateId09 + ", rateId12=" + rateId12 + ", ezylinkTid=" + ezylinkTid + ", ezywayTid="
				+ ezywayTid + ", ezysplitMid=" + ezysplitMid + ", ezysplitTid=" + ezysplitTid + ", preAuth=" + preAuth
				+ "]";
	}

}
