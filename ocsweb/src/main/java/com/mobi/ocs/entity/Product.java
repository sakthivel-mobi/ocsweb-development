package com.mobi.ocs.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="Product")
public class Product {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "createdOn")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime createdOn;
	
	@Column(name = "updatedOn")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime updatedOn;
	
	@Column(name = "productName")
	private String productName;
	
	@Column(name = "IncludeWallet")
	private String includeWallet;
	
	@Column(name = "showInQuote")
	private String showInQuote;
	
	@Column(name = "payLater")
	private String payLater;
	
	@Column(name = "subscription")
	private int subscription;
	
	@Column(name = "productSettlement")
	private int productSettlement;
	
	@Column(name = "unitPrice")
	private double unitPrice;
	
	@Column(name = "productType")
	private String productType;
	
	@Column(name = "hostType")
	private String hostType;
	
	@OneToOne(fetch=FetchType.EAGER,cascade = {CascadeType.ALL})
	@JoinColumn(name = "standardMDRRateID")
	private StandardMDRRate standardmdrRate;
	
	@OneToOne(fetch=FetchType.EAGER,cascade = {CascadeType.ALL})
	@JoinColumn(name = "standardEzySplitMDRRateID")
	private StandardEzySplitMDRRate standardEzySplitMDRRate;
	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
	@JoinColumn(name = "orderTypeID")
	private OrderType orderTypeID;
	
	@Column(name = "refId")
	private String refId;

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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String isIncludeWallet() {
		return includeWallet;
	}

	public void setIncludeWallet(String includeWallet) {
		this.includeWallet = includeWallet;
	}

	public String getIncludeWallet() {
		return includeWallet;
	}
	
	public String getShowInQuote() {
		return showInQuote;
	}

	public void setShowInQuote(String showInQuote) {
		this.showInQuote = showInQuote;
	}

	public int getProductSettlement() {
		return productSettlement;
	}

	public void setProductSettlement(int productSettlement) {
		this.productSettlement = productSettlement;
	}

	public String getPayLater() {
		return payLater;
	}

	public String isPayLater() {
		return payLater;
	}

	public void setPayLater(String payLater) {
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

	public StandardMDRRate getStandardmdrRate() {
		return standardmdrRate;
	}

	public void setStandardmdrRate(StandardMDRRate standardmdrRate) {
		this.standardmdrRate = standardmdrRate;
	}
	
	

	
	public StandardEzySplitMDRRate getStandardEzySplitMDRRate() {
		return standardEzySplitMDRRate;
	}

	public void setStandardEzySplitMDRRate(StandardEzySplitMDRRate standardEzySplitMDRRate) {
		this.standardEzySplitMDRRate = standardEzySplitMDRRate;
	}

	public OrderType getOrderTypeID() {
		return orderTypeID;
	}

	public void setOrderTypeID(OrderType orderTypeID) {
		this.orderTypeID = orderTypeID;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	
	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}
	

	@Override
	public String toString() {
		return "Product [id=" + id + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + ", productName="
				+ productName + ", includeWallet=" + includeWallet + ", showInQuote=" + showInQuote + ", payLater="
				+ payLater + ", subscription=" + subscription + ", productSettlement=" + productSettlement
				+ ", unitPrice=" + unitPrice + ", productType=" + productType + ", hostType=" + hostType
				+ ", standardmdrRate=" + standardmdrRate + ", standardEzySplitMDRRate=" + standardEzySplitMDRRate
				+ ", orderTypeID=" + orderTypeID + ", refId=" + refId + "]";
	}
	
	

}
