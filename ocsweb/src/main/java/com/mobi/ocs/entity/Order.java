package com.mobi.ocs.entity;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "createdOn", updatable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime createdOn;

	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.PERSIST })
	@JsonBackReference
	private Quotation quotation;

	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
	private Set<Director> directors = new HashSet<Director>();

	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
	private Set<OrderNotes> notes = new HashSet<OrderNotes>();

	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.PERSIST })
	private MerchantRegistration merchantRegistration;

	@Column(name = "userId")
	private String userId;

	@Column(name = "userName")
	private String userName;

	@Column(name = "gstNo")
	private String gstNo;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "merchantIdType")
	private String merchantIdType;

	@Column(name = "registrationId")
	private String registrationId;

	@Column(name = "businessName")
	private String businessName;

	@Column(name = "merchantTradingName")
	private String merchantTradingName;

	@Column(name = "businessStartTime", columnDefinition = "varchar(255) default '10:00'")
	private String businessStartTime;

	@Column(name = "businessEndTime", columnDefinition = "varchar(255) default '19:00'")
	private String businessEndTime;

	@Column(name = "noOfMPOS", nullable = false, columnDefinition = "int default 0")
	private int noOfMPOS;

	@Column(name = "mPOSModel")
	private String mPOSModel;

	@Column(name = "natureOfBusiness")
	private int natureOfBusiness;

	@Column(name = "eCommIndustry")
	private int eCommIndustry;

	@Column(name = "masterCardMCC")
	private int masterCardMCC;

	@Column(name = "visaMCC")
	private int visaMCC;

	@Column(name = "unionPayMCC")
	private int unionPayMCC;

	@Column(name = "websiteUrl")
	private String websiteUrl;

	@Column(name = "currentAcquirer")
	private String currentAcquirer;

	@Column(name = "previousAcquirer")
	private String previousAcquirer;

	@Column(name = "previousAcquirerCeasedDate")
	private String previousAcquirerCeasedDate;

	@Column(name = "stage")
	private String stage;

	@Column(name = "officeNo")
	private String officeNo;

	@Column(name = "officeEmail")
	private String officeEmail;

	@Column(name = "bankName")
	private String bankName;

	@Column(name = "accountNo")
	private String accountNo;

	@Column(name = "accountType", nullable = false, columnDefinition = "int default 0")
	private int accountType;

	@Column(name = "businessType")
	private String businessType;

	@Column(name = "dateIncorporated")
	private String dateIncorporated;

	@Column(name = "authContactPersonName")
	private String authContactPersonName;

	@Column(name = "authContactPersonId")
	private String authContactPersonId;

	@Column(name = "authContactPersonPhone")
	private String authContactPersonPhone;

	@Column(name = "authContactPersonNationality")
	private String authContactPersonNationality;

	@Column(name = "salutation")
	private String salutation;

	@Column(name = "companyType")
	private String companyType;

	@Column(name = "sizeOfPremise")
	private String sizeOfPremise;

	@Column(name = "premiseType")
	private String premiseType;

	@Column(name = "propertyType")
	private String propertyType;

	@Column(name = "yearsOccupied")
	private String yearsOccupied;

	@Column(name = "estimatedStockValue")
	private String estimatedStockValue;

	@Column(name = "noOfEmployee")
	private String noOfEmployee;

	@Column(name = "noOfDailyTransaction")
	private String noOfDailyTransaction;

	@Column(name = "averageTransactionSize")
	private String averageTransactionSize;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "whoSigned")
	private String whoSigned;

	@Column(name = "whoSignedNric")
	private String whoSignedNric;

	@Column(name = "whoSignedMobile")
	private String whoSignedMobile;

	@Column(name = "masterMerchant")
	private String masterMerchant;

	@Column(name = "grabPayLatitude")
	private String grabPayLatitude;

	@Column(name = "grabPayLongitude")
	private String grabPayLongitude;

	@Column(name = "isUSResident", nullable = false, columnDefinition = "varchar(255) default ''")
	private String isUSResident;

	@Column(name = "isUSCitizen", nullable = false, columnDefinition = "varchar(255) default ''")
	private String isUSCitizen;

	@Column(name = "isGreenCardHolder", nullable = false, columnDefinition = "varchar(255) default ''")
	private String isGreenCardHolder;

	@Column(name = "taxIdUS", nullable = false, columnDefinition = "varchar(255) default ''")
	private String taxIdUS;

	@Column(name = "countryOfBirth", nullable = false, columnDefinition = "varchar(255) default ''")
	private String countryOfBirth;

	@Column(name = "addressLine")
	private String addressLine;

	@Column(name = "requstedDTL", nullable = false, columnDefinition = "varchar(10) default '0'")
	private String requstedDTL;

	@Column(name = "riskApprovedDTL", nullable = false, columnDefinition = "varchar(10) default '0'")
	private String riskApprovedDTL;

	@Column(name = "riskReason")
	private String riskReason;

	@Column(name = "highRiskMark")
	private String highRiskMark;

	@Column(name = "highRiskStatus")
	private String highRiskStatus;

	@Column(name = "highRiskMarkedOn")
	private String highRiskMarkedOn;

	@Column(name = "postCode")
	private String postCode;

	@Column(name = "ecommIndicator")
	private String ecommIndicator;

	@Column(name = "paydeeMCC")
	private int paydeeMCC;

	@Column(name = "paydeeDTL")
	private String paydeeDTL;

	@Column(name = "paydeeMID")
	private String paydeeMID;

	@Column(name = "paydeeTID")
	private String paydeeTID;

	@Column(name = "state", nullable = false, columnDefinition = "int default 0")
	private int state;

	@Column(name = "city", nullable = false, columnDefinition = "int default 0")
	private int city;

	@Column(name = "country", nullable = false, columnDefinition = "int default 0")
	private int country;

	@Column(name = "requestType")
	private String requestType;

	@Column(name = "mdrCredit")
	private String mdrCredit;

	@Column(name = "mdrDebit")
	private String mdrDebit;

	@Column(name = "deploymentDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime deploymentDate;

	@Column(name = "merchantCategory")
	private String merchantCategory;

	@Column(name = "merchantSector")
	private String merchantSector;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMerchantCategory() {
		return merchantCategory;
	}

	public void setMerchantCategory(String merchantCategory) {
		this.merchantCategory = merchantCategory;
	}

	public String getMerchantSector() {
		return merchantSector;
	}

	public void setMerchantSector(String merchantSector) {
		this.merchantSector = merchantSector;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Quotation getQuotation() {
		return quotation;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getMdrCredit() {
		return mdrCredit;
	}

	public void setMdrCredit(String mdrCredit) {
		this.mdrCredit = mdrCredit;
	}

	public String getMdrDebit() {
		return mdrDebit;
	}

	public void setMdrDebit(String mdrDebit) {
		this.mdrDebit = mdrDebit;
	}

	public LocalDateTime getDeploymentDate() {
		return deploymentDate;
	}

	public void setDeploymentDate(LocalDateTime deploymentDate) {
		this.deploymentDate = deploymentDate;
	}

	public MerchantRegistration getMerchantRegistration() {
		return merchantRegistration;
	}

	public void setMerchantRegistration(MerchantRegistration merchantRegistration) {
		this.merchantRegistration = merchantRegistration;
	}

	public void setQuotation(Quotation quotation) {
		this.quotation = quotation;
	}

	public String getPreviousAcquirer() {
		return previousAcquirer;
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	public void setPreviousAcquirer(String previousAcquirer) {
		this.previousAcquirer = previousAcquirer;
	}

	public String getPreviousAcquirerCeasedDate() {
		return previousAcquirerCeasedDate;
	}

	public void setPreviousAcquirerCeasedDate(String previousAcquirerCeasedDate) {
		this.previousAcquirerCeasedDate = previousAcquirerCeasedDate;
	}

	public String getMerchantIdType() {
		return merchantIdType;
	}

	public void setMerchantIdType(String merchantIdType) {
		this.merchantIdType = merchantIdType;
	}

	public String getEcommIndicator() {
		return ecommIndicator;
	}

	public void setEcommIndicator(String ecommIndicator) {
		this.ecommIndicator = ecommIndicator;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getMerchantTradingName() {
		return merchantTradingName;
	}

	public void setMerchantTradingName(String merchantTradingName) {
		this.merchantTradingName = merchantTradingName;
	}

	public String getBusinessStartTime() {
		return businessStartTime;
	}

	public void setBusinessStartTime(String businessStartTime) {
		this.businessStartTime = businessStartTime;
	}

	public String getBusinessEndTime() {
		return businessEndTime;
	}

	public void setBusinessEndTime(String businessEndTime) {
		this.businessEndTime = businessEndTime;
	}

	public String getmPOSModel() {
		return mPOSModel;
	}

	public void setmPOSModel(String mPOSModel) {
		this.mPOSModel = mPOSModel;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getCurrentAcquirer() {
		return currentAcquirer;
	}

	public void setCurrentAcquirer(String currentAcquirer) {
		this.currentAcquirer = currentAcquirer;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getOfficeNo() {
		return officeNo;
	}

	public void setOfficeNo(String officeNo) {
		this.officeNo = officeNo;
	}

	public String getPremiseType() {
		return premiseType;
	}

	public void setPremiseType(String premiseType) {
		this.premiseType = premiseType;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getYearsOccupied() {
		return yearsOccupied;
	}

	public void setYearsOccupied(String yearsOccupied) {
		this.yearsOccupied = yearsOccupied;
	}

	public String getOfficeEmail() {
		return officeEmail;
	}

	public void setOfficeEmail(String officeEmail) {
		this.officeEmail = officeEmail;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getDateIncorporated() {
		return dateIncorporated;
	}

	public void setDateIncorporated(String dateIncorporated) {
		this.dateIncorporated = dateIncorporated;
	}

	public String getAuthContactPersonName() {
		return authContactPersonName;
	}

	public void setAuthContactPersonName(String authContactPersonName) {
		this.authContactPersonName = authContactPersonName;
	}

	public String getAuthContactPersonId() {
		return authContactPersonId;
	}

	public void setAuthContactPersonId(String authContactPersonId) {
		this.authContactPersonId = authContactPersonId;
	}

	public String getAuthContactPersonPhone() {
		return authContactPersonPhone;
	}

	public void setAuthContactPersonPhone(String authContactPersonPhone) {
		this.authContactPersonPhone = authContactPersonPhone;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getSizeOfPremise() {
		return sizeOfPremise;
	}

	public void setSizeOfPremise(String sizeOfPremise) {
		this.sizeOfPremise = sizeOfPremise;
	}

	public String getEstimatedStockValue() {
		return estimatedStockValue;
	}

	public void setEstimatedStockValue(String estimatedStockValue) {
		this.estimatedStockValue = estimatedStockValue;
	}

	public String getNoOfEmployee() {
		return noOfEmployee;
	}

	public void setNoOfEmployee(String noOfEmployee) {
		this.noOfEmployee = noOfEmployee;
	}

	public String getNoOfDailyTransaction() {
		return noOfDailyTransaction;
	}

	public void setNoOfDailyTransaction(String noOfDailyTransaction) {
		this.noOfDailyTransaction = noOfDailyTransaction;
	}

	public String getAuthContactPersonNationality() {
		return authContactPersonNationality;
	}

	public void setAuthContactPersonNationality(String authContactPersonNationality) {
		this.authContactPersonNationality = authContactPersonNationality;
	}

	public String getAverageTransactionSize() {
		return averageTransactionSize;
	}

	public void setAverageTransactionSize(String averageTransactionSize) {
		this.averageTransactionSize = averageTransactionSize;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getWhoSigned() {
		return whoSigned;
	}

	public void setWhoSigned(String whoSigned) {
		this.whoSigned = whoSigned;
	}

	public String getWhoSignedNric() {
		return whoSignedNric;
	}

	public void setWhoSignedNric(String whoSignedNric) {
		this.whoSignedNric = whoSignedNric;
	}

	public String getWhoSignedMobile() {
		return whoSignedMobile;
	}

	public int getPaydeeMCC() {
		return paydeeMCC;
	}

	public void setPaydeeMCC(int paydeeMCC) {
		this.paydeeMCC = paydeeMCC;
	}

	public void setWhoSignedMobile(String whoSignedMobile) {
		this.whoSignedMobile = whoSignedMobile;
	}

	public String getMasterMerchant() {
		return masterMerchant;
	}

	public void setMasterMerchant(String masterMerchant) {
		this.masterMerchant = masterMerchant;
	}

	public String getGrabPayLatitude() {
		return grabPayLatitude;
	}

	public void setGrabPayLatitude(String grabPayLatitude) {
		this.grabPayLatitude = grabPayLatitude;
	}

	public String getGrabPayLongitude() {
		return grabPayLongitude;
	}

	public void setGrabPayLongitude(String grabPayLongitude) {
		this.grabPayLongitude = grabPayLongitude;
	}

	public String getRequstedDTL() {
		return requstedDTL;
	}

	public void setRequstedDTL(String requstedDTL) {
		this.requstedDTL = requstedDTL;
	}

	public String getRiskApprovedDTL() {
		return riskApprovedDTL;
	}

	public void setRiskApprovedDTL(String riskApprovedDTL) {
		this.riskApprovedDTL = riskApprovedDTL;
	}

	public String getRiskReason() {
		return riskReason;
	}

	public void setRiskReason(String riskReason) {
		this.riskReason = riskReason;
	}

	public String getHighRiskMark() {
		return highRiskMark;
	}

	public void setHighRiskMark(String highRiskMark) {
		this.highRiskMark = highRiskMark;
	}

	public String getHighRiskStatus() {
		return highRiskStatus;
	}

	public void setHighRiskStatus(String highRiskStatus) {
		this.highRiskStatus = highRiskStatus;
	}

	public String getHighRiskMarkedOn() {
		return highRiskMarkedOn;
	}

	public void setHighRiskMarkedOn(String highRiskMarkedOn) {
		this.highRiskMarkedOn = highRiskMarkedOn;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public String getPaydeeDTL() {
		return paydeeDTL;
	}

	public void setPaydeeDTL(String paydeeDTL) {
		this.paydeeDTL = paydeeDTL;
	}

	public String getPaydeeMID() {
		return paydeeMID;
	}

	public void setPaydeeMID(String paydeeMID) {
		this.paydeeMID = paydeeMID;
	}

	public String getPaydeeTID() {
		return paydeeTID;
	}

	public void setPaydeeTID(String paydeeTID) {
		this.paydeeTID = paydeeTID;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getIsUSResident() {
		return isUSResident;
	}

	public void setIsUSResident(String isUSResident) {
		this.isUSResident = isUSResident;
	}

	public String getIsUSCitizen() {
		return isUSCitizen;
	}

	public void setIsUSCitizen(String isUSCitizen) {
		this.isUSCitizen = isUSCitizen;
	}

	public String getIsGreenCardHolder() {
		return isGreenCardHolder;
	}

	public void setIsGreenCardHolder(String isGreenCardHolder) {
		this.isGreenCardHolder = isGreenCardHolder;
	}

	public String getTaxIdUS() {
		return taxIdUS;
	}

	public void setTaxIdUS(String taxIdUS) {
		this.taxIdUS = taxIdUS;
	}

	public String getCountryOfBirth() {
		return countryOfBirth;
	}

	public void setCountryOfBirth(String countryOfBirth) {
		this.countryOfBirth = countryOfBirth;
	}

	public int getNoOfMPOS() {
		return noOfMPOS;
	}

	public void setNoOfMPOS(int noOfMPOS) {
		this.noOfMPOS = noOfMPOS;
	}

	public int getNatureOfBusiness() {
		return natureOfBusiness;
	}

	public void setNatureOfBusiness(int natureOfBusiness) {
		this.natureOfBusiness = natureOfBusiness;
	}

	public int geteCommIndustry() {
		return eCommIndustry;
	}

	public void seteCommIndustry(int eCommIndustry) {
		this.eCommIndustry = eCommIndustry;
	}

	public int getMasterCardMCC() {
		return masterCardMCC;
	}

	public void setMasterCardMCC(int masterCardMCC) {
		this.masterCardMCC = masterCardMCC;
	}

	public int getVisaMCC() {
		return visaMCC;
	}

	public void setVisaMCC(int visaMCC) {
		this.visaMCC = visaMCC;
	}

	public int getUnionPayMCC() {
		return unionPayMCC;
	}

	public void setUnionPayMCC(int unionPayMCC) {
		this.unionPayMCC = unionPayMCC;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
		this.country = country;
	}

	public Set<OrderNotes> getNotes() {
		return notes;
	}

	public void setNotes(Set<OrderNotes> notes) {
		this.notes = notes;
	}

	public Set<Director> getDirectors() {
		return directors;
	}

	public void setDirectors(Set<Director> directors) {
		this.directors = directors;
	}

//	@Override
//	public String toString() {
//		return "Order [id=" + id + ", createdOn=" + createdOn + ", quotation=" + quotation + ", userId=" + userId
//				+ ", merchantIdType=" + merchantIdType + ", registrationId=" + registrationId + ", businessName="
//				+ businessName + ", merchantTradingName=" + merchantTradingName + ", businessStartTime="
//				+ businessStartTime + ", businessEndTime=" + businessEndTime + ", noOfMPOS=" + noOfMPOS + ", mPOSModel="
//				+ mPOSModel + ", natureOfBusiness=" + natureOfBusiness + ", eCommIndustry=" + eCommIndustry
//				+ ", masterCardMCC=" + masterCardMCC + ", visaMCC=" + visaMCC + ", unionPayMCC=" + unionPayMCC
//				+ ", websiteUrl=" + websiteUrl + ", currentAcquirer=" + currentAcquirer + ", stage=" + stage
//				+ ", officeNo=" + officeNo + ", accountNo=" + accountNo + ", accountType=" + accountType
//				+ ", dateIncorporated=" + dateIncorporated + ", authContactPersonName=" + authContactPersonName
//				+ ", authContactPersonId=" + authContactPersonId + ", authContactPersonPhone=" + authContactPersonPhone
//				+ ", companyType=" + companyType + ", sizeOfPremise=" + sizeOfPremise + ", estimatedStockValue="
//				+ estimatedStockValue + ", noOfEmployee=" + noOfEmployee + ", noOfDailyTransaction="
//				+ noOfDailyTransaction + ", averageTransactionSize=" + averageTransactionSize + ", remarks=" + remarks
//				+ ", whoSigned=" + whoSigned + ", whoSignedNric=" + whoSignedNric + ", whoSignedMobile="
//				+ whoSignedMobile + ", masterMerchant=" + masterMerchant + ", grabPayLatitude=" + grabPayLatitude
//				+ ", grabPayLongitude=" + grabPayLongitude + ", addressLine=" + addressLine + ", requstedDTL="
//				+ requstedDTL + ", riskApprovedDTL=" + riskApprovedDTL + ", riskReason=" + riskReason
//				+ ", highRiskMark=" + highRiskMark + ", highRiskStatus=" + highRiskStatus + ", highRiskMarkedOn="
//				+ highRiskMarkedOn + ", postCode=" + postCode + ", state=" + state + ", city=" + city + ", country="
//				+ country + "]";
//	}

}
