package com.mobi.ocs.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;

@Entity
@Table(name = "merchantRegistration")
public class MerchantRegistration {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "createdOn")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date createdOn;

	@JoinColumn(name = "orderId")
	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.PERSIST })
	private Order order;

	@Column(name = "ezywireMID", nullable = false, columnDefinition = "varchar(255) default ''")
	private String ezywireMID;

	@Column(name = "ezylinkMID", nullable = false, columnDefinition = "varchar(255) default ''")
	private String ezylinkMID;

	@Column(name = "ezysplitMID", nullable = false, columnDefinition = "varchar(255) default ''")
	private String ezysplitMID;

	@Column(name = "ezyrecplusMID", nullable = false, columnDefinition = "varchar(255) default ''")
	private String ezyrecplusMID;

	@Column(name = "ezywayMID", nullable = false, columnDefinition = "varchar(255) default ''")
	private String ezywayMID;

	// UM MIDs

	@Column(name = "umEzywireMID", nullable = false, columnDefinition = "varchar(255) default ''")
	private String umEzywireMID;

	@Column(name = "umEzylinkMID", nullable = false, columnDefinition = "varchar(255) default ''")
	private String umEzylinkMID;

	@Column(name = "umEzysplitMID", nullable = false, columnDefinition = "varchar(255) default ''")
	private String umEzysplitMID;

	@Column(name = "umEzyrecplusMID", nullable = false, columnDefinition = "varchar(255) default ''")
	private String umEzyrecplusMID;

	@Column(name = "umEzywayMID", nullable = false, columnDefinition = "varchar(255) default ''")
	private String umEzywayMID;

	// Other Information

	@Column(name = "waiverMonth", nullable = false, columnDefinition = "varchar(255) default ''")
	private String waiverMonth;

	@Column(name = "signedPackage", nullable = false, columnDefinition = "varchar(255) default ''")
	private String signedPackage;

	@Column(name = "noOfReader", nullable = false, columnDefinition = "varchar(255) default ''")
	private String noOfReader;

	@Column(name = "documents", nullable = false, columnDefinition = "varchar(255) default ''")
	private String documents;

	@Column(name = "statusRemarks", nullable = false, columnDefinition = "varchar(255) default ''")
	private String statusRemarks;

	@Column(name = "mdr", nullable = false, columnDefinition = "varchar(255) default ''")
	private String mdr;

	@Column(name = "preAuth", nullable = false, columnDefinition = "varchar(255) default ''")
	private String preAuth;

	@Column(name = "iSwitchEnable", nullable = false, columnDefinition = "varchar(255) default ''")
	private String iSwitchEnable;

	@Column(name = "bankOTP", nullable = false, columnDefinition = "varchar(255) default ''")
	private String bankOTP;

	@Column(name = "autoSettled", nullable = false, columnDefinition = "varchar(255) default ''")
	private String autoSettled;

	@Column(name = "ownerCount", nullable = false, columnDefinition = "varchar(255) default ''")
	private String ownerCount;

	@Column(name = "isSuccess", columnDefinition = "int(1) default '0'")
	private Boolean isSuccess;

	@Column(name = "wlSentDate")
	private Date wlSentDate;

	@Column(name = "iSwitchDiscount", columnDefinition = "varchar(5) default 'No'")
	private String iSwitchDiscount;

	@Column(name = "enblBoth", columnDefinition = "varchar(5) default 'No'")
	private String enblBoth;

	@Column(name = "grabMid")
	private String grabMid;
	
	@Column(name = "tngMid")
	private String tngMid;
	
	@Column(name = "shopeepayMid")
	private String shopeepayMid;
	
	@Column(name = "shopeepayTid")
	private String shopeepayTid;
	
	
	public String getTngMid() {
		return tngMid;
	}

	public void setTngMid(String tngMid) {
		this.tngMid = tngMid;
	}

	public String getShopeepayMid() {
		return shopeepayMid;
	}

	public void setShopeepayMid(String shopeepayMid) {
		this.shopeepayMid = shopeepayMid;
	}

	public String getShopeepayTid() {
		return shopeepayTid;
	}

	public void setShopeepayTid(String shopeepayTid) {
		this.shopeepayTid = shopeepayTid;
	}

	public String getTngTid() {
		return tngTid;
	}

	public void setTngTid(String tngTid) {
		this.tngTid = tngTid;
	}

	@Column(name = "tngTid")
	private String tngTid;

	@Column(name = "fpxMid")
	private String fpxMid;

	@Column(name = "boostMid")
	private String boostMid;

	@Column(name = "grabTid")
	private String grabTid;

	@Column(name = "grabMidAcquired")
	private String grabMidAcquired;

	@Column(name = "fpxTid")
	private String fpxTid;

	@Column(name = "boostTid")
	private String boostTid;

	@Column(name = "webPortalUsername")
	private String webPortalUsername;

	@Column(name = "webPortalPassword")
	private String webPortalPassword;

	@Column(name = "appUsername")
	private String appUsername;

	@Column(name = "appPassword")
	private String appPassword;

	@Column(name = "apiKey")
	private String apiKey;

	@Column(name = "activationCode")
	private String activationCode;

	@Column(name = "card", columnDefinition = "varchar(5) default 'No'")
	private String Card;

	@Column(name = "eWallet", columnDefinition = "varchar(5) default 'No'")
	private String EWallet;

	@Column(name = "fpx", columnDefinition = "varchar(5) default 'No'")
	private String FPX;

	@Column(name = "ezyComboEnable", columnDefinition = "varchar(5) default 'No'")
	private String ezyComboEnable;

	@Column(name = "ezyMotoVcc", columnDefinition = "varchar(5) default 'No'")
	private String ezyMotoVcc;

	@Column(name = "reRegister", columnDefinition = "varchar(50) default ''")
	private String reRegister;

	@Column(name = "isEzywirePlus", columnDefinition = "varchar(10) default 'Normal'")
	private String isEzywirePlus;

	@Column(name = "eWalletSyncSuccess", columnDefinition = "int(1) default '0'")
	private Boolean eWalletSyncSuccess;

	public String getEzyMotoVcc() {
		return ezyMotoVcc;
	}
	
	

	public void setEzyMotoVcc(String ezyMotoVcc) {
		this.ezyMotoVcc = ezyMotoVcc;
	}

	public String getGrabMidAcquired() {
		return grabMidAcquired;
	}

	public void setGrabMidAcquired(String grabMidAcquired) {
		this.grabMidAcquired = grabMidAcquired;
	}

	public Boolean geteWalletSyncSuccess() {
		return eWalletSyncSuccess;
	}

	public void seteWalletSyncSuccess(Boolean eWalletSyncSuccess) {
		this.eWalletSyncSuccess = eWalletSyncSuccess;
	}

	public String getIsEzywirePlus() {
		return isEzywirePlus;
	}

	public void setIsEzywirePlus(String isEzywireEnable) {
		this.isEzywirePlus = isEzywireEnable;
	}

	public String getEzyComboEnable() {
		return ezyComboEnable;
	}

	public void setEzyComboEnable(String ezyComboEnable) {
		this.ezyComboEnable = ezyComboEnable;
	}

	public String getReRegister() {
		return reRegister;
	}

	public void setReRegister(String reRegister) {
		this.reRegister = reRegister;
	}

	public String getCard() {
		return Card;
	}

	public void setCard(String card) {
		Card = card;
	}

	public String getEWallet() {
		return EWallet;
	}

	public void setEWallet(String eWallet) {
		EWallet = eWallet;
	}

	public String getFPX() {
		return FPX;
	}

	public void setFPX(String fPX) {
		FPX = fPX;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public String getWebPortalUsername() {
		return webPortalUsername;
	}

	public void setWebPortalUsername(String webPortalUsername) {
		this.webPortalUsername = webPortalUsername;
	}

	public String getWebPortalPassword() {
		return webPortalPassword;
	}

	public void setWebPortalPassword(String webPortalPassword) {
		this.webPortalPassword = webPortalPassword;
	}

	public String getAppUsername() {
		return appUsername;
	}

	public void setAppUsername(String appUsername) {
		this.appUsername = appUsername;
	}

	public String getAppPassword() {
		return appPassword;
	}

	public void setAppPassword(String appPassword) {
		this.appPassword = appPassword;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getGrabMid() {
		return grabMid;
	}

	public void setGrabMid(String grabMid) {
		this.grabMid = grabMid;
	}

	public String getFpxMid() {
		return fpxMid;
	}

	public void setFpxMid(String fpxMid) {
		this.fpxMid = fpxMid;
	}

	public String getBoostMid() {
		return boostMid;
	}

	public void setBoostMid(String boostMid) {
		this.boostMid = boostMid;
	}

	public String getGrabTid() {
		return grabTid;
	}

	public void setGrabTid(String grabTid) {
		this.grabTid = grabTid;
	}

	public String getFpxTid() {
		return fpxTid;
	}

	public void setFpxTid(String fpxTid) {
		this.fpxTid = fpxTid;
	}

	public String getBoostTid() {
		return boostTid;
	}

	public void setBoostTid(String boostTid) {
		this.boostTid = boostTid;
	}

	public String getEnblBoth() {
		return enblBoth;
	}

	public void setEnblBoth(String enblBoth) {
		this.enblBoth = enblBoth;
	}

	public String getiSwitchDiscount() {
		return iSwitchDiscount;
	}

	public void setiSwitchDiscount(String iSwitchDiscount) {
		this.iSwitchDiscount = iSwitchDiscount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getEzywireMID() {
		return ezywireMID;
	}

	public void setEzywireMID(String ezywireMID) {
		this.ezywireMID = ezywireMID;
	}

	public String getEzylinkMID() {
		return ezylinkMID;
	}

	public void setEzylinkMID(String ezylinkMID) {
		this.ezylinkMID = ezylinkMID;
	}

	public String getEzysplitMID() {
		return ezysplitMID;
	}

	public void setEzysplitMID(String ezysplitMID) {
		this.ezysplitMID = ezysplitMID;
	}

	public String getEzyrecplusMID() {
		return ezyrecplusMID;
	}

	public void setEzyrecplusMID(String ezyrecplusMID) {
		this.ezyrecplusMID = ezyrecplusMID;
	}

	public String getEzywayMID() {
		return ezywayMID;
	}

	public void setEzywayMID(String ezywayMID) {
		this.ezywayMID = ezywayMID;
	}

	public String getUmEzywireMID() {
		return umEzywireMID;
	}

	public void setUmEzywireMID(String umEzywireMID) {
		this.umEzywireMID = umEzywireMID;
	}

	public String getUmEzylinkMID() {
		return umEzylinkMID;
	}

	public void setUmEzylinkMID(String umEzylinkMID) {
		this.umEzylinkMID = umEzylinkMID;
	}

	public String getUmEzysplitMID() {
		return umEzysplitMID;
	}

	public void setUmEzysplitMID(String umEzysplitMID) {
		this.umEzysplitMID = umEzysplitMID;
	}

	public String getUmEzyrecplusMID() {
		return umEzyrecplusMID;
	}

	public void setUmEzyrecplusMID(String umEzyrecplusMID) {
		this.umEzyrecplusMID = umEzyrecplusMID;
	}

	public String getUmEzywayMID() {
		return umEzywayMID;
	}

	public void setUmEzywayMID(String umEzywayMID) {
		this.umEzywayMID = umEzywayMID;
	}

	public String getSignedPackage() {
		return signedPackage;
	}

	public void setSignedPackage(String signedPackage) {
		this.signedPackage = signedPackage;
	}

	public String getNoOfReader() {
		return noOfReader;
	}

	public void setNoOfReader(String noOfReader) {
		this.noOfReader = noOfReader;
	}

	public String getDocuments() {
		return documents;
	}

	public void setDocuments(String documents) {
		this.documents = documents;
	}

	public String getStatusRemarks() {
		return statusRemarks;
	}

	public void setStatusRemarks(String statusRemarks) {
		this.statusRemarks = statusRemarks;
	}

	public String getMdr() {
		return mdr;
	}

	public void setMdr(String mdr) {
		this.mdr = mdr;
	}

	public String getPreAuth() {
		return preAuth;
	}

	public void setPreAuth(String preAuth) {
		this.preAuth = preAuth;
	}

	public String getBankOTP() {
		return bankOTP;
	}

	public void setBankOTP(String bankOTP) {
		this.bankOTP = bankOTP;
	}

	public String getAutoSettled() {
		return autoSettled;
	}

	public void setAutoSettled(String autoSettled) {
		this.autoSettled = autoSettled;
	}

	public String getOwnerCount() {
		return ownerCount;
	}

	public void setOwnerCount(String ownerCount) {
		this.ownerCount = ownerCount;
	}

	public Date getWlSentDate() {
		return wlSentDate;
	}

	public void setWlSentDate(Date wlSentDate) {
		this.wlSentDate = wlSentDate;
	}

	public String getWaiverMonth() {
		return waiverMonth;
	}

	public String getiSwitchEnable() {
		return iSwitchEnable;
	}

	public void setiSwitchEnable(String iSwitchEnable) {
		this.iSwitchEnable = iSwitchEnable;
	}

	public void setWaiverMonth(String waiverMonth) {
		this.waiverMonth = waiverMonth;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	@Override
	public String toString() {
		return "MerchantRegistration [id=" + id + ", createdOn=" + createdOn + ", order=" + order + ", ezywireMID="
				+ ezywireMID + ", ezylinkMID=" + ezylinkMID + ", ezysplitMID=" + ezysplitMID + ", ezyrecplusMID="
				+ ezyrecplusMID + ", ezywayMID=" + ezywayMID + ", umEzywireMID=" + umEzywireMID + ", umEzylinkMID="
				+ umEzylinkMID + ", umEzysplitMID=" + umEzysplitMID + ", umEzyrecplusMID=" + umEzyrecplusMID
				+ ", umEzywayMID=" + umEzywayMID + ", waiverMonth=" + waiverMonth + ", signedPackage=" + signedPackage
				+ ", noOfReader=" + noOfReader + ", documents=" + documents + ", statusRemarks=" + statusRemarks
				+ ", mdr=" + mdr + ", preAuth=" + preAuth + ", iSwitchEnable=" + iSwitchEnable + ", bankOTP=" + bankOTP
				+ ", autoSettled=" + autoSettled + ", ownerCount=" + ownerCount + ", isSuccess=" + isSuccess
				+ ", wlSentDate=" + wlSentDate + ", iSwitchDiscount=" + iSwitchDiscount + ", enblBoth=" + enblBoth
				+ ", grabMid=" + grabMid + ", tngMid=" + tngMid + ", shopeepayMid=" + shopeepayMid + ", shopeepayTid="
				+ shopeepayTid + ", tngTid=" + tngTid + ", fpxMid=" + fpxMid + ", boostMid=" + boostMid + ", grabTid="
				+ grabTid + ", grabMidAcquired=" + grabMidAcquired + ", fpxTid=" + fpxTid + ", boostTid=" + boostTid
				+ ", webPortalUsername=" + webPortalUsername + ", webPortalPassword=" + webPortalPassword
				+ ", appUsername=" + appUsername + ", appPassword=" + appPassword + ", apiKey=" + apiKey
				+ ", activationCode=" + activationCode + ", Card=" + Card + ", EWallet=" + EWallet + ", FPX=" + FPX
				+ ", ezyComboEnable=" + ezyComboEnable + ", ezyMotoVcc=" + ezyMotoVcc + ", reRegister=" + reRegister
				+ ", isEzywirePlus=" + isEzywirePlus + ", eWalletSyncSuccess=" + eWalletSyncSuccess + "]";
	}

	
}
