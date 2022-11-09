package com.mobi.ocs.dto;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class RateIDInsertEzysplit {

	private String service;
	
	
	private String locCreditMcCust;
	private String locCreditMcMobi;
	private String locCreditMcMer;
	private String locCreditMcHost;
	
	
	private String rateId;
	private String createdBy;
	private String createdDate;
	private String updatedBy;
	private String updatedDate;
	private String userReference;
	private String productId;
	
	private String validFrom;
	private String ipAddress;
	private String validTo;
	
	private String boostMdr;
	private String grabpayMdr;
	private String hostType;
	
	//Not User As of Now
	
	private String locDebitVisaCust;
	private String foreignDebitVisaCust;
	private String foreignCreditVisaCust;
	private String locDebitMcCust;
	private String foreignDebitMcCust;
	private String foreignCreditMcCust;
	private String locDebitUpCust;
	private String locCreditUpCust;
	private String foreignDebitUpCust;
	private String foreignCreditUpCust;
	private String locDebitVisaMobi;
	private String locCreditVisaMobi;
	private String foreignDebitVisaMobi;
	private String foreignCreditVisaMobi;
	private String locDebitMcMobi;	
	private String foreignDebitMcMobi;
	private String foreignCreditMcMobi;
	private String locDebitUpMobi;
	private String locCreditUpMobi;
	private String foreignDebitUpMobi;
	private String foreignCreditUpMobi;
	
	
	private String locDebitVisaMer;
	private String locCreditVisaMer;
	private String foreignDebitVisaMer;
	
	private String foreignCreditVisaMer;
	private String locDebitMcMer;
	private String foreignDebitMcMer;
	private String foreignCreditMcMer;
	private String locDebitUpMer;
  
	
	private String locCreditUpMer;
	private String foreignDebitUpMer;
	private String foreignCreditUpMer;
	private String locDebitVisaHost;
	private String locCreditVisaHost;
	private String foreignDebitVisaHost;
	
	private String foreignCreditVisaHost;
	private String locDebitMcHost;
	private String foreignDebitMcHost;
	private String foreignCreditMcHost;
	private String locDebitUpHost;
	
	private String locCreditUpHost;
	private String foreignDebitUpHost;
	private String foreignCreditUpHost;
	
	public RateIDInsertEzysplit() {
		
		service ="";
		rateId= "";
		createdBy= "";
		createdDate= "";
		updatedBy= "";
		updatedDate= "";
		userReference= "";
		productId= "";
		
		validFrom= "";
		ipAddress= "";
		validTo= "";
		
		boostMdr= "";
		grabpayMdr= "";
		hostType= "";
		
		locCreditMcCust= "0.00";
		locCreditMcMobi= "0.00";
		locCreditMcMer= "0.00";
		locCreditMcHost= "0.00";
		
		
		
		
		//Not User As of Now
		
		locDebitVisaCust= "0.00";
		foreignDebitVisaCust= "0.00";
		foreignCreditVisaCust= "0.00";
		locDebitMcCust= "0.00";
		foreignDebitMcCust= "0.00";
		foreignCreditMcCust= "0.00";
		locDebitUpCust= "0.00";
		locCreditUpCust= "0.00";
		foreignDebitUpCust= "0.00";
		foreignCreditUpCust= "0.00";
		locDebitVisaMobi= "0.00";
		locCreditVisaMobi= "0.00";
		foreignDebitVisaMobi= "0.00";
		foreignCreditVisaMobi= "0.00";
		locDebitMcMobi= "0.00";	
		foreignDebitMcMobi= "0.00";
		foreignCreditMcMobi= "0.00";
		locDebitUpMobi= "0.00";
		locCreditUpMobi= "0.00";
		foreignDebitUpMobi= "0.00";
		foreignCreditUpMobi= "0.00";
		
		
		locDebitVisaMer= "0.00";
		locCreditVisaMer= "0.00";
		foreignDebitVisaMer= "0.00";
		
		foreignCreditVisaMer= "0.00";
		locDebitMcMer= "0.00";
		foreignDebitMcMer= "0.00";
		foreignCreditMcMer= "0.00";
		locDebitUpMer= "0.00";
	  
		
		locCreditUpMer= "0.00";
		foreignDebitUpMer= "0.00";
		foreignCreditUpMer= "0.00";
		locDebitVisaHost= "0.00";
		locCreditVisaHost= "0.00";
		foreignDebitVisaHost= "0.00";
		
		foreignCreditVisaHost= "0.00";
		locDebitMcHost= "0.00";
		foreignDebitMcHost= "0.00";
		foreignCreditMcHost= "0.00";
		locDebitUpHost= "0.00";
		
		locCreditUpHost= "0.00";
		foreignDebitUpHost= "0.00";
		foreignCreditUpHost= "0.00";
		
	}
	
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getLocCreditMcCust() {
		return locCreditMcCust;
	}
	public void setLocCreditMcCust(String locCreditMcCust) {
		this.locCreditMcCust = locCreditMcCust;
	}
	public String getLocCreditMcMobi() {
		return locCreditMcMobi;
	}
	public void setLocCreditMcMobi(String locCreditMcMobi) {
		this.locCreditMcMobi = locCreditMcMobi;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getLocCreditMcMer() {
		return locCreditMcMer;
	}
	public void setLocCreditMcMer(String locCreditMcMer) {
		this.locCreditMcMer = locCreditMcMer;
	}
	public String getLocCreditMcHost() {
		return locCreditMcHost;
	}
	public void setLocCreditMcHost(String locCreditMcHost) {
		this.locCreditMcHost = locCreditMcHost;
	}
	public String getLocDebitVisaCust() {
		return locDebitVisaCust;
	}
	public void setLocDebitVisaCust(String locDebitVisaCust) {
		this.locDebitVisaCust = locDebitVisaCust;
	}
	public String getForeignDebitVisaCust() {
		return foreignDebitVisaCust;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public void setForeignDebitVisaCust(String foreignDebitVisaCust) {
		this.foreignDebitVisaCust = foreignDebitVisaCust;
	}
	public String getForeignCreditVisaCust() {
		return foreignCreditVisaCust;
	}
	public void setForeignCreditVisaCust(String foreignCreditVisaCust) {
		this.foreignCreditVisaCust = foreignCreditVisaCust;
	}
	public String getLocDebitMcCust() {
		return locDebitMcCust;
	}
	public void setLocDebitMcCust(String locDebitMcCust) {
		this.locDebitMcCust = locDebitMcCust;
	}
	public String getForeignDebitMcCust() {
		return foreignDebitMcCust;
	}
	public void setForeignDebitMcCust(String foreignDebitMcCust) {
		this.foreignDebitMcCust = foreignDebitMcCust;
	}
	public String getForeignCreditMcCust() {
		return foreignCreditMcCust;
	}
	public void setForeignCreditMcCust(String foreignCreditMcCust) {
		this.foreignCreditMcCust = foreignCreditMcCust;
	}
	public String getLocDebitUpCust() {
		return locDebitUpCust;
	}
	public void setLocDebitUpCust(String locDebitUpCust) {
		this.locDebitUpCust = locDebitUpCust;
	}
	public String getLocCreditUpCust() {
		return locCreditUpCust;
	}
	public void setLocCreditUpCust(String locCreditUpCust) {
		this.locCreditUpCust = locCreditUpCust;
	}
	public String getForeignDebitUpCust() {
		return foreignDebitUpCust;
	}
	public void setForeignDebitUpCust(String foreignDebitUpCust) {
		this.foreignDebitUpCust = foreignDebitUpCust;
	}
	public String getForeignCreditUpCust() {
		return foreignCreditUpCust;
	}
	public void setForeignCreditUpCust(String foreignCreditUpCust) {
		this.foreignCreditUpCust = foreignCreditUpCust;
	}
	public String getLocDebitVisaMobi() {
		return locDebitVisaMobi;
	}
	public void setLocDebitVisaMobi(String locDebitVisaMobi) {
		this.locDebitVisaMobi = locDebitVisaMobi;
	}
	public String getLocCreditVisaMobi() {
		return locCreditVisaMobi;
	}
	public void setLocCreditVisaMobi(String locCreditVisaMobi) {
		this.locCreditVisaMobi = locCreditVisaMobi;
	}
	public String getForeignDebitVisaMobi() {
		return foreignDebitVisaMobi;
	}
	public void setForeignDebitVisaMobi(String foreignDebitVisaMobi) {
		this.foreignDebitVisaMobi = foreignDebitVisaMobi;
	}
	public String getForeignCreditVisaMobi() {
		return foreignCreditVisaMobi;
	}
	public void setForeignCreditVisaMobi(String foreignCreditVisaMobi) {
		this.foreignCreditVisaMobi = foreignCreditVisaMobi;
	}
	public String getLocDebitMcMobi() {
		return locDebitMcMobi;
	}
	public void setLocDebitMcMobi(String locDebitMcMobi) {
		this.locDebitMcMobi = locDebitMcMobi;
	}
	public String getForeignDebitMcMobi() {
		return foreignDebitMcMobi;
	}
	public void setForeignDebitMcMobi(String foreignDebitMcMobi) {
		this.foreignDebitMcMobi = foreignDebitMcMobi;
	}
	public String getForeignCreditMcMobi() {
		return foreignCreditMcMobi;
	}
	public void setForeignCreditMcMobi(String foreignCreditMcMobi) {
		this.foreignCreditMcMobi = foreignCreditMcMobi;
	}
	public String getLocDebitUpMobi() {
		return locDebitUpMobi;
	}
	public void setLocDebitUpMobi(String locDebitUpMobi) {
		this.locDebitUpMobi = locDebitUpMobi;
	}
	public String getLocCreditUpMobi() {
		return locCreditUpMobi;
	}
	public void setLocCreditUpMobi(String locCreditUpMobi) {
		this.locCreditUpMobi = locCreditUpMobi;
	}
	public String getForeignDebitUpMobi() {
		return foreignDebitUpMobi;
	}
	public void setForeignDebitUpMobi(String foreignDebitUpMobi) {
		this.foreignDebitUpMobi = foreignDebitUpMobi;
	}
	public String getForeignCreditUpMobi() {
		return foreignCreditUpMobi;
	}
	public void setForeignCreditUpMobi(String foreignCreditUpMobi) {
		this.foreignCreditUpMobi = foreignCreditUpMobi;
	}
	public String getRateId() {
		return rateId;
	}
	public void setRateId(String rateId) {
		this.rateId = rateId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUserReference() {
		return userReference;
	}
	public void setUserReference(String userReference) {
		this.userReference = userReference;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getValidTo() {
		return validTo;
	}
	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}
	public String getLocDebitVisaMer() {
		return locDebitVisaMer;
	}
	public void setLocDebitVisaMer(String locDebitVisaMer) {
		this.locDebitVisaMer = locDebitVisaMer;
	}
	public String getLocCreditVisaMer() {
		return locCreditVisaMer;
	}
	public void setLocCreditVisaMer(String locCreditVisaMer) {
		this.locCreditVisaMer = locCreditVisaMer;
	}
	public String getForeignDebitVisaMer() {
		return foreignDebitVisaMer;
	}
	public void setForeignDebitVisaMer(String foreignDebitVisaMer) {
		this.foreignDebitVisaMer = foreignDebitVisaMer;
	}
	public String getForeignCreditVisaMer() {
		return foreignCreditVisaMer;
	}
	public void setForeignCreditVisaMer(String foreignCreditVisaMer) {
		this.foreignCreditVisaMer = foreignCreditVisaMer;
	}
	public String getLocDebitMcMer() {
		return locDebitMcMer;
	}
	public void setLocDebitMcMer(String locDebitMcMer) {
		this.locDebitMcMer = locDebitMcMer;
	}
	public String getForeignDebitMcMer() {
		return foreignDebitMcMer;
	}
	public void setForeignDebitMcMer(String foreignDebitMcMer) {
		this.foreignDebitMcMer = foreignDebitMcMer;
	}
	public String getForeignCreditMcMer() {
		return foreignCreditMcMer;
	}
	public void setForeignCreditMcMer(String foreignCreditMcMer) {
		this.foreignCreditMcMer = foreignCreditMcMer;
	}
	public String getLocDebitUpMer() {
		return locDebitUpMer;
	}
	public void setLocDebitUpMer(String locDebitUpMer) {
		this.locDebitUpMer = locDebitUpMer;
	}
	public String getLocCreditUpMer() {
		return locCreditUpMer;
	}
	public void setLocCreditUpMer(String locCreditUpMer) {
		this.locCreditUpMer = locCreditUpMer;
	}
	public String getForeignDebitUpMer() {
		return foreignDebitUpMer;
	}
	public void setForeignDebitUpMer(String foreignDebitUpMer) {
		this.foreignDebitUpMer = foreignDebitUpMer;
	}
	public String getForeignCreditUpMer() {
		return foreignCreditUpMer;
	}
	public void setForeignCreditUpMer(String foreignCreditUpMer) {
		this.foreignCreditUpMer = foreignCreditUpMer;
	}
	public String getLocDebitVisaHost() {
		return locDebitVisaHost;
	}
	public void setLocDebitVisaHost(String locDebitVisaHost) {
		this.locDebitVisaHost = locDebitVisaHost;
	}
	public String getLocCreditVisaHost() {
		return locCreditVisaHost;
	}
	public void setLocCreditVisaHost(String locCreditVisaHost) {
		this.locCreditVisaHost = locCreditVisaHost;
	}
	public String getForeignDebitVisaHost() {
		return foreignDebitVisaHost;
	}
	public void setForeignDebitVisaHost(String foreignDebitVisaHost) {
		this.foreignDebitVisaHost = foreignDebitVisaHost;
	}
	public String getForeignCreditVisaHost() {
		return foreignCreditVisaHost;
	}
	public void setForeignCreditVisaHost(String foreignCreditVisaHost) {
		this.foreignCreditVisaHost = foreignCreditVisaHost;
	}
	public String getLocDebitMcHost() {
		return locDebitMcHost;
	}
	public void setLocDebitMcHost(String locDebitMcHost) {
		this.locDebitMcHost = locDebitMcHost;
	}
	public String getForeignDebitMcHost() {
		return foreignDebitMcHost;
	}
	public void setForeignDebitMcHost(String foreignDebitMcHost) {
		this.foreignDebitMcHost = foreignDebitMcHost;
	}
	public String getForeignCreditMcHost() {
		return foreignCreditMcHost;
	}
	public void setForeignCreditMcHost(String foreignCreditMcHost) {
		this.foreignCreditMcHost = foreignCreditMcHost;
	}
	public String getLocDebitUpHost() {
		return locDebitUpHost;
	}
	public void setLocDebitUpHost(String locDebitUpHost) {
		this.locDebitUpHost = locDebitUpHost;
	}
	public String getLocCreditUpHost() {
		return locCreditUpHost;
	}
	public void setLocCreditUpHost(String locCreditUpHost) {
		this.locCreditUpHost = locCreditUpHost;
	}
	public String getForeignDebitUpHost() {
		return foreignDebitUpHost;
	}
	public void setForeignDebitUpHost(String foreignDebitUpHost) {
		this.foreignDebitUpHost = foreignDebitUpHost;
	}
	public String getForeignCreditUpHost() {
		return foreignCreditUpHost;
	}
	public void setForeignCreditUpHost(String foreignCreditUpHost) {
		this.foreignCreditUpHost = foreignCreditUpHost;
	}
	public String getBoostMdr() {
		return boostMdr;
	}
	public void setBoostMdr(String boostMdr) {
		this.boostMdr = boostMdr;
	}
	public String getGrabpayMdr() {
		return grabpayMdr;
	}
	public void setGrabpayMdr(String grabpayMdr) {
		this.grabpayMdr = grabpayMdr;
	}
	public String getHostType() {
		return hostType;
	}
	public void setHostType(String hostType) {
		this.hostType = hostType;
	}
	@Override
	public String toString() {
		return "RateIDInsertEzysplit [service=" + service + ", locCreditMcCust=" + locCreditMcCust
				+ ", locCreditMcMobi=" + locCreditMcMobi + ", locCreditMcMer=" + locCreditMcMer + ", locCreditMcHost="
				+ locCreditMcHost + ", rateId=" + rateId + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", userReference=" + userReference
				+ ", productId=" + productId + ", validFrom=" + validFrom + ", ipAddress=" + ipAddress + ", validTo="
				+ validTo + ", boostMdr=" + boostMdr + ", grabpayMdr=" + grabpayMdr + ", hostType=" + hostType
				+ ", locDebitVisaCust=" + locDebitVisaCust + ", foreignDebitVisaCust=" + foreignDebitVisaCust
				+ ", foreignCreditVisaCust=" + foreignCreditVisaCust + ", locDebitMcCust=" + locDebitMcCust
				+ ", foreignDebitMcCust=" + foreignDebitMcCust + ", foreignCreditMcCust=" + foreignCreditMcCust
				+ ", locDebitUpCust=" + locDebitUpCust + ", locCreditUpCust=" + locCreditUpCust
				+ ", foreignDebitUpCust=" + foreignDebitUpCust + ", foreignCreditUpCust=" + foreignCreditUpCust
				+ ", locDebitVisaMobi=" + locDebitVisaMobi + ", locCreditVisaMobi=" + locCreditVisaMobi
				+ ", foreignDebitVisaMobi=" + foreignDebitVisaMobi + ", foreignCreditVisaMobi=" + foreignCreditVisaMobi
				+ ", locDebitMcMobi=" + locDebitMcMobi + ", foreignDebitMcMobi=" + foreignDebitMcMobi
				+ ", foreignCreditMcMobi=" + foreignCreditMcMobi + ", locDebitUpMobi=" + locDebitUpMobi
				+ ", locCreditUpMobi=" + locCreditUpMobi + ", foreignDebitUpMobi=" + foreignDebitUpMobi
				+ ", foreignCreditUpMobi=" + foreignCreditUpMobi + ", locDebitVisaMer=" + locDebitVisaMer
				+ ", locCreditVisaMer=" + locCreditVisaMer + ", foreignDebitVisaMer=" + foreignDebitVisaMer
				+ ", foreignCreditVisaMer=" + foreignCreditVisaMer + ", locDebitMcMer=" + locDebitMcMer
				+ ", foreignDebitMcMer=" + foreignDebitMcMer + ", foreignCreditMcMer=" + foreignCreditMcMer
				+ ", locDebitUpMer=" + locDebitUpMer + ", locCreditUpMer=" + locCreditUpMer + ", foreignDebitUpMer="
				+ foreignDebitUpMer + ", foreignCreditUpMer=" + foreignCreditUpMer + ", locDebitVisaHost="
				+ locDebitVisaHost + ", locCreditVisaHost=" + locCreditVisaHost + ", foreignDebitVisaHost="
				+ foreignDebitVisaHost + ", foreignCreditVisaHost=" + foreignCreditVisaHost + ", locDebitMcHost="
				+ locDebitMcHost + ", foreignDebitMcHost=" + foreignDebitMcHost + ", foreignCreditMcHost="
				+ foreignCreditMcHost + ", locDebitUpHost=" + locDebitUpHost + ", locCreditUpHost=" + locCreditUpHost
				+ ", foreignDebitUpHost=" + foreignDebitUpHost + ", foreignCreditUpHost=" + foreignCreditUpHost + "]";
	}
	
	
	
	
	
}
