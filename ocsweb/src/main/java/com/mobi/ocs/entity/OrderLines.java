package com.mobi.ocs.entity;

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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "orderLines")
@JsonIgnoreProperties(value = { "quotation", "quotationMDRRate", "quotationEzysplitMDRRate" })
public class OrderLines {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "createdOn", updatable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime createdOn;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "mid")
	private String mid;

	@Column(name = "tid")
	private String tid;

	@Column(name = "dtl")
	private String dtl;

	@Column(name = "hashkey")
	private String hashkey;

	@Column(name = "callbackURL")
	private String callbackURL;

	@Column(name = "ezywireDeviceId")
	private String ezywireDeviceId;

	@Column(name = "ezylinkTid")
	private String ezylinkTid;

	@Column(name = "ezywayTid")
	private String ezywayTid;

	@Column(name = "ezywireRefNo")
	private String ezywireRefNo;

	@Column(name = "apiKey")
	private String apiKey;

	@Column(name = "userName")
	private String userName;

	@Column(name = "isCompleted", columnDefinition = "int(1) default 0")
	private boolean isCompleted;

	@Column(name = "password")
	private String password;

	@Column(name = "activationCode")
	private String activationCode;

	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "quotationId")
	private Quotation quotation;

	@ManyToOne()
	@JoinColumn(name = "productId")
	private Product product;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "quotationMDRRateId")
	private QuotationMDRRate quotationMDRRate;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "quotationEzysplitMDRRateId")
	private QuotationEzySplitMDRRate quotationEzysplitMDRRate;

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public QuotationMDRRate getQuotationMDRRate() {
		return quotationMDRRate;
	}

	public void setQuotationMDRRate(QuotationMDRRate quotationMDRRate) {
		this.quotationMDRRate = quotationMDRRate;
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

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public QuotationEzySplitMDRRate getQuotationEzysplitMDRRate() {
		return quotationEzysplitMDRRate;
	}

	public void setQuotationEzysplitMDRRate(QuotationEzySplitMDRRate quotationEzysplitMDRRate) {
		this.quotationEzysplitMDRRate = quotationEzysplitMDRRate;
	}

	@Override
	public String toString() {
		return "OrderLines [id=" + id + ", createdOn=" + createdOn + ", quantity=" + quantity + ", mid=" + mid
				+ ", tid=" + tid + ", dtl=" + dtl + ", hashkey=" + hashkey + ", callbackURL=" + callbackURL
				+ ", ezywireDeviceId=" + ezywireDeviceId + ", ezylinkTid=" + ezylinkTid + ", ezywayTid=" + ezywayTid
				+ ", ezywireRefNo=" + ezywireRefNo + ", apiKey=" + apiKey + ", userName=" + userName + ", isCompleted="
				+ isCompleted + ", password=" + password + ", activationCode=" + activationCode + ", quotation="
				+ quotation + ", product=" + product + ", quotationMDRRate=" + quotationMDRRate
				+ ", quotationEzysplitMDRRate=" + quotationEzysplitMDRRate + "]";
	}

}
