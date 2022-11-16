package com.mobi.ocs.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "quotation")
@JsonIgnoreProperties(value = { "order" })
public class Quotation {

	public Quotation() {

	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@Column(name = "createdOn", updatable = false)
	private Date createdOn;

	@Column(name = "lastModified")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date lastModified;

	@Column(name = "expiryDate")
	private String expiryDate;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "quotationAcceptanceId")
	private QuotationAcceptance quotationAcceptance;

	@Column(name = "quotationAccepted", columnDefinition = "int(1) default 0")
	private int quotationAccepted;

	@Column(name = "welcomeLetterAccepted", columnDefinition = "int(1) default 0")
	private int welcomeLetterAccepted;

	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinColumn(name = "orderId")
	private Order order;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "welcomeLetterAcceptanceId")
	private WelcomeLetterAcceptance welcomeLetterAcceptance;

	@Column(name = "userId")
	private String userId;

	// Hubspot
	@Column(name = "dealID")
	private String dealID;

	// Kanban
	@Column(name = "boardID")
	private String boardID;
	// Kanban
	@Column(name = "cardID")
	private String cardID;

	@Column(name = "companyName")
	private String companyName;

	@Column(name = "stage")
	private String stage;

	@Column(name = "orderType")
	private String orderType;

	@Column(name = "registrationNumber")
	private String registrationNumber;

	@Column(name = "totalAmount")
	private String totalAmount;

	@Column(name = "acquirer")
	private String acquirer;

	@Column(name = "notes")
	private String notes;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "refreeMID")
	private String refreeMID;

	@Column(name = "refreePhone")
	private String refreePhone;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "postalCode")
	private String postalCode;

	@Column(name = "country")
	private String country;

	@Column(name = "acceptedOn")
	private Date acceptedOn;

	@Column(name = "paymentType")
	private String paymentType;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "contactID")
	private Contact contact;

	@Column(name = "welcomeLetterPath")
	private String welcomeLetterPath;

	@Column(name = "mmaPath")
	private String mmaPath;

	@Column(name = "pendingApproval", columnDefinition = "int(1) default 0")
	private int pendingApproval;

	@Column(name = "discountPrice", columnDefinition = "double default 0.00")
	private double discountPrice;

	@Column(name = "discountReason")
	private String discountReason;

	@Column(name = "quotationReceipt")
	private String quotationReceipt;

	@Column(name = "iSwitchDiscount")
	private String iSwitchDiscount;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinColumn(name = "salesPersonID")
	private SalesPerson salesPerson;

	@OneToMany(mappedBy = "quotation", fetch = FetchType.EAGER)
	private List<OrderLines> orderLines;

	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinColumn(name = "paymentID")
	private Payment payment;

	@Column(name = "rollbackReason")
	private String rollbackReason;

	public String getRollbackReason() {
		return rollbackReason;
	}

	public void setRollbackReason(String rollbackReason) {
		this.rollbackReason = rollbackReason;
	}

	public String getiSwitchDiscount() {
		return iSwitchDiscount;
	}

	public void setiSwitchDiscount(String iSwitchDiscount) {
		this.iSwitchDiscount = iSwitchDiscount;
	}

	public int getPendingApproval() {
		return pendingApproval;
	}

	public void setPendingApproval(int pendingApproval) {
		this.pendingApproval = pendingApproval;
	}

	public String getQuotationReceipt() {
		return quotationReceipt;
	}

	public void setQuotationReceipt(String quotationReceipt) {
		this.quotationReceipt = quotationReceipt;
	}

	public int getWelcomeLetterAccepted() {
		return welcomeLetterAccepted;
	}

	public void setWelcomeLetterAccepted(int welcomeLetterAccepted) {
		this.welcomeLetterAccepted = welcomeLetterAccepted;
	}

	public int getQuotationAccepted() {
		return quotationAccepted;
	}

	public void setQuotationAccepted(int quotationAccepted) {
		this.quotationAccepted = quotationAccepted;
	}

	public String getWelcomeLetterPath() {
		return welcomeLetterPath;
	}

	public void setWelcomeLetterPath(String welcomeLetterPath) {
		this.welcomeLetterPath = welcomeLetterPath;
	}

	public String getMmaPath() {
		return mmaPath;
	}

	public void setMmaPath(String mmaPath) {
		this.mmaPath = mmaPath;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public SalesPerson getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(SalesPerson salesPerson) {
		this.salesPerson = salesPerson;
	}

	public Date getAcceptedOn() {
		return acceptedOn;
	}

	public void setAcceptedOn(Date acceptedOn) {
		this.acceptedOn = acceptedOn;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public int getId() {
		return id;
	}

	public String getAddress() {
		return address;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDealID() {
		return dealID;
	}

	public void setDealID(String dealID) {
		this.dealID = dealID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public List<OrderLines> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLines> orderLines) {
		this.orderLines = orderLines;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getAcquirer() {
		return acquirer;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public void setAcquirer(String acquirer) {
		this.acquirer = acquirer;
	}

	public String getRefreeMID() {
		return refreeMID;
	}

	public void setRefreeMID(String refreeMID) {
		this.refreeMID = refreeMID;
	}

	public String getBoardID() {
		return boardID;
	}

	public void setBoardID(String boardID) {
		this.boardID = boardID;
	}

	public String getCardID() {
		return cardID;
	}

	public void setCardID(String cardID) {
		this.cardID = cardID;
	}

	public String getRefreePhone() {
		return refreePhone;
	}

	public void setRefreePhone(String refreePhone) {
		this.refreePhone = refreePhone;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public QuotationAcceptance getQuotationAcceptance() {
		return quotationAcceptance;
	}

	public void setQuotationAcceptance(QuotationAcceptance quotationAcceptance) {
		this.quotationAcceptance = quotationAcceptance;
	}

	public WelcomeLetterAcceptance getWelcomeLetterAcceptance() {
		return welcomeLetterAcceptance;
	}

	public void setWelcomeLetterAcceptance(WelcomeLetterAcceptance welcomeLetterAcceptance) {
		this.welcomeLetterAcceptance = welcomeLetterAcceptance;
	}

	public double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getDiscountReason() {
		return discountReason;
	}

	public void setDiscountReason(String discountReason) {
		this.discountReason = discountReason;
	}

}
