package com.mobi.ocs.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tpafiletoumobile")
public class TPAFileToUmobile {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "createdOn")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime createdOn;

	private String passCode;
	private String HQSUB;
	private String verificationURL;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime validTill;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime runDate;
	private String batchNo;
	private String orderId;
	private int isProcessed;
	private String status;

	private String referenceName;

	private int responseCode;
	private String responseDescription;
	private String operationCode;
	private String tpaReferenceNo;
	private String productCode;
	private String mid;
	private String chainNo;
	private String rootMerchantNo;
	private String merchantIdType;
	private String businessRegistrationNo;
	private String businessName;
	private String merchantTradingName;
	private String businessStartTime;
	private String businessEndTime;
	private String natureOfBusiness;

	private String businessAddressLine1;
	private String businessAddressLine2;
	private String businessAddressLine3;
	private String businessAddressLine4;
	private int country;
	private int state;
	private String town;
	private String postCode;

	private String director1Name;
	private String director1IdType;
	private String director1Country;
	private String director1IdNumber;
	private String director1MobileNo;

	private String director2Name;
	private String director2IdType;
	private String director2Country;
	private String director2IdNumber;
	private String director2MobileNo;

	private String director3Name;
	private String director3IdType;
	private String director3Country;
	private String director3IdNumber;
	private String director3MobileNo;

	private String director4Name;
	private String director4IdType;
	private String director4Country;
	private String director4IdNumber;
	private String director4MobileNo;

	private String director5Name;
	private String director5IdType;
	private String director5Country;
	private String director5IdNumber;
	private String director5MobileNo;

	private String eCommerceIndicator;
	private String eCommerceTerminalFlag;
	private String eCommerceIndustry;
	private String websiteUrl;
	private String numberOfEdc;
	private String edcModel;
	private String numberOfMpos;
	private String mposModel;
	private String merchantSize;
	private String masterCardMCC;
	private String visaMCC;
	private String unionPayMCC;
	private String previousAcquirerIndicator;
	private String previousAcquirer;

	private String ceasedDateWithPreviousAcquirer;
	private String email;
	private String companyType;

	public String getReferenceName() {
		return referenceName;
	}

	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
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

	public String getPassCode() {
		return passCode;
	}

	public void setPassCode(String passCode) {
		this.passCode = passCode;
	}

	public String getHQSUB() {
		return HQSUB;
	}

	public void setHQSUB(String hQSUB) {
		HQSUB = hQSUB;
	}

	public String getVerificationURL() {
		return verificationURL;
	}

	public void setVerificationURL(String verificationURL) {
		this.verificationURL = verificationURL;
	}

	public LocalDateTime getValidTill() {
		return validTill;
	}

	public void setValidTill(LocalDateTime localDateTime) {
		this.validTill = localDateTime;
	}

	public LocalDateTime getRunDate() {
		return runDate;
	}

	public void setRunDate(LocalDateTime localDateTime) {
		this.runDate = localDateTime;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(int isProcessed) {
		this.isProcessed = isProcessed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public String getTpaReferenceNo() {
		return tpaReferenceNo;
	}

	public void setTpaReferenceNo(String tpaReferenceNo) {
		this.tpaReferenceNo = tpaReferenceNo;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getChainNo() {
		return chainNo;
	}

	public void setChainNo(String chainNo) {
		this.chainNo = chainNo;
	}

	public String getRootMerchantNo() {
		return rootMerchantNo;
	}

	public void setRootMerchantNo(String rootMerchantNo) {
		this.rootMerchantNo = rootMerchantNo;
	}

	public String getMerchantIdType() {
		return merchantIdType;
	}

	public void setMerchantIdType(String merchantIdType) {
		this.merchantIdType = merchantIdType;
	}

	public String getBusinessRegistrationNo() {
		return businessRegistrationNo;
	}

	public void setBusinessRegistrationNo(String businessRegistrationNo) {
		this.businessRegistrationNo = businessRegistrationNo;
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

	public String getNatureOfBusiness() {
		return natureOfBusiness;
	}

	public void setNatureOfBusiness(String natureOfBusiness) {
		this.natureOfBusiness = natureOfBusiness;
	}

	public String getBusinessAddressLine1() {
		return businessAddressLine1;
	}

	public void setBusinessAddressLine1(String businessAddressLine1) {
		this.businessAddressLine1 = businessAddressLine1;
	}

	public String getBusinessAddressLine2() {
		return businessAddressLine2;
	}

	public void setBusinessAddressLine2(String businessAddressLine2) {
		this.businessAddressLine2 = businessAddressLine2;
	}

	public String getBusinessAddressLine3() {
		return businessAddressLine3;
	}

	public void setBusinessAddressLine3(String businessAddressLine3) {
		this.businessAddressLine3 = businessAddressLine3;
	}

	public String getBusinessAddressLine4() {
		return businessAddressLine4;
	}

	public void setBusinessAddressLine4(String businessAddressLine4) {
		this.businessAddressLine4 = businessAddressLine4;
	}

	public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
		this.country = country;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getDirector1Name() {
		return director1Name;
	}

	public void setDirector1Name(String director1Name) {
		this.director1Name = director1Name;
	}

	public String getDirector1IdType() {
		return director1IdType;
	}

	public void setDirector1IdType(String director1IdType) {
		this.director1IdType = director1IdType;
	}

	public String getDirector1Country() {
		return director1Country;
	}

	public void setDirector1Country(String director1Country) {
		this.director1Country = director1Country;
	}

	public String getDirector1IdNumber() {
		return director1IdNumber;
	}

	public void setDirector1IdNumber(String director1IdNumber) {
		this.director1IdNumber = director1IdNumber;
	}

	public String getDirector1MobileNo() {
		return director1MobileNo;
	}

	public void setDirector1MobileNo(String director1MobileNo) {
		this.director1MobileNo = director1MobileNo;
	}

	public String getDirector2Name() {
		return director2Name;
	}

	public void setDirector2Name(String director2Name) {
		this.director2Name = director2Name;
	}

	public String getDirector2IdType() {
		return director2IdType;
	}

	public void setDirector2IdType(String director2IdType) {
		this.director2IdType = director2IdType;
	}

	public String getDirector2Country() {
		return director2Country;
	}

	public void setDirector2Country(String director2Country) {
		this.director2Country = director2Country;
	}

	public String getDirector2IdNumber() {
		return director2IdNumber;
	}

	public void setDirector2IdNumber(String director2IdNumber) {
		this.director2IdNumber = director2IdNumber;
	}

	public String getDirector2MobileNo() {
		return director2MobileNo;
	}

	public void setDirector2MobileNo(String director2MobileNo) {
		this.director2MobileNo = director2MobileNo;
	}

	public String getDirector3Name() {
		return director3Name;
	}

	public void setDirector3Name(String director3Name) {
		this.director3Name = director3Name;
	}

	public String getDirector3IdType() {
		return director3IdType;
	}

	public void setDirector3IdType(String director3IdType) {
		this.director3IdType = director3IdType;
	}

	public String getDirector3Country() {
		return director3Country;
	}

	public void setDirector3Country(String director3Country) {
		this.director3Country = director3Country;
	}

	public String getDirector3IdNumber() {
		return director3IdNumber;
	}

	public void setDirector3IdNumber(String director3IdNumber) {
		this.director3IdNumber = director3IdNumber;
	}

	public String getDirector3MobileNo() {
		return director3MobileNo;
	}

	public void setDirector3MobileNo(String director3MobileNo) {
		this.director3MobileNo = director3MobileNo;
	}

	public String getDirector4Name() {
		return director4Name;
	}

	public void setDirector4Name(String director4Name) {
		this.director4Name = director4Name;
	}

	public String getDirector4IdType() {
		return director4IdType;
	}

	public void setDirector4IdType(String director4IdType) {
		this.director4IdType = director4IdType;
	}

	public String getDirector4Country() {
		return director4Country;
	}

	public void setDirector4Country(String director4Country) {
		this.director4Country = director4Country;
	}

	public String getDirector4IdNumber() {
		return director4IdNumber;
	}

	public void setDirector4IdNumber(String director4IdNumber) {
		this.director4IdNumber = director4IdNumber;
	}

	public String getDirector4MobileNo() {
		return director4MobileNo;
	}

	public void setDirector4MobileNo(String director4MobileNo) {
		this.director4MobileNo = director4MobileNo;
	}

	public String getDirector5Name() {
		return director5Name;
	}

	public void setDirector5Name(String director5Name) {
		this.director5Name = director5Name;
	}

	public String getDirector5IdType() {
		return director5IdType;
	}

	public void setDirector5IdType(String director5IdType) {
		this.director5IdType = director5IdType;
	}

	public String getDirector5Country() {
		return director5Country;
	}

	public void setDirector5Country(String director5Country) {
		this.director5Country = director5Country;
	}

	public String getDirector5IdNumber() {
		return director5IdNumber;
	}

	public void setDirector5IdNumber(String director5IdNumber) {
		this.director5IdNumber = director5IdNumber;
	}

	public String getDirector5MobileNo() {
		return director5MobileNo;
	}

	public void setDirector5MobileNo(String director5MobileNo) {
		this.director5MobileNo = director5MobileNo;
	}

	public String geteCommerceIndicator() {
		return eCommerceIndicator;
	}

	public void seteCommerceIndicator(String eCommerceIndicator) {
		this.eCommerceIndicator = eCommerceIndicator;
	}

	public String geteCommerceTerminalFlag() {
		return eCommerceTerminalFlag;
	}

	public void seteCommerceTerminalFlag(String eCommerceTerminalFlag) {
		this.eCommerceTerminalFlag = eCommerceTerminalFlag;
	}

	public String geteCommerceIndustry() {
		return eCommerceIndustry;
	}

	public void seteCommerceIndustry(String eCommerceIndustry) {
		this.eCommerceIndustry = eCommerceIndustry;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getNumberOfEdc() {
		return numberOfEdc;
	}

	public void setNumberOfEdc(String numberOfEdc) {
		this.numberOfEdc = numberOfEdc;
	}

	public String getEdcModel() {
		return edcModel;
	}

	public void setEdcModel(String edcModel) {
		this.edcModel = edcModel;
	}

	public String getNumberOfMpos() {
		return numberOfMpos;
	}

	public void setNumberOfMpos(String numberOfMpos) {
		this.numberOfMpos = numberOfMpos;
	}

	public String getMposModel() {
		return mposModel;
	}

	public void setMposModel(String mposModel) {
		this.mposModel = mposModel;
	}

	public String getMerchantSize() {
		return merchantSize;
	}

	public void setMerchantSize(String merchantSize) {
		this.merchantSize = merchantSize;
	}

	public String getMasterCardMCC() {
		return masterCardMCC;
	}

	public void setMasterCardMCC(String masterCardMCC) {
		this.masterCardMCC = masterCardMCC;
	}

	public String getVisaMCC() {
		return visaMCC;
	}

	public void setVisaMCC(String visaMCC) {
		this.visaMCC = visaMCC;
	}

	public String getUnionPayMCC() {
		return unionPayMCC;
	}

	public void setUnionPayMCC(String unionPayMCC) {
		this.unionPayMCC = unionPayMCC;
	}

	public String getPreviousAcquirerIndicator() {
		return previousAcquirerIndicator;
	}

	public void setPreviousAcquirerIndicator(String previousAcquirerIndicator) {
		this.previousAcquirerIndicator = previousAcquirerIndicator;
	}

	public String getPreviousAcquirer() {
		return previousAcquirer;
	}

	public void setPreviousAcquirer(String previousAcquirer) {
		this.previousAcquirer = previousAcquirer;
	}

	public String getCeasedDateWithPreviousAcquirer() {
		return ceasedDateWithPreviousAcquirer;
	}

	public void setCeasedDateWithPreviousAcquirer(String ceasedDateWithPreviousAcquirer) {
		this.ceasedDateWithPreviousAcquirer = ceasedDateWithPreviousAcquirer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

}
