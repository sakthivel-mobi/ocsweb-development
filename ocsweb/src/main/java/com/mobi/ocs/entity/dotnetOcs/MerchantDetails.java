package com.mobi.ocs.entity.dotnetOcs;

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

@Entity
@Table(name = "merchantdetails_dotnetData")
public class MerchantDetails {

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "Encrypted")
	private String Encrypted;
	
	@Column(name = "IPAddress")
	private String IPAddress;

	@Column(name = "CreatedOn")
	private LocalDateTime createdOn;
	
	@Column(name = "UpdatedOn")
	private LocalDateTime updatedOn;
	
	@Column(name = "UserId")
	private String UserId;
	
	@Column(name = "UserName")
	private String UserName;
	
	@Column(name = "RegisteredName")
	private String RegisteredName;

	@Column(name = "RegistrationNumber")
	private String RegistrationNumber;
	
	@Column(name = "OtherRegistrationNumber")
	private String OtherRegistrationNumber;

	
	@Column(name = "GstNo")
	private String GstNo;

	@Column(name = "BusinessName")
	private String BusinessName;

	

	@Column(name = "OfficeNo")
	private String OfficeNo;

	@Column(name = "MobileNo")
	private String MobileNo;

	@Column(name = "Email")
	private String Email;

	@Column(name = "BankName")
	private String BankName;

	@Column(name = "AccountNumber")
	private String AccountNumber;

	@Column(name = "DateIncorporated")
	private String DateIncorporated;

	@Column(name = "AuthContactPersonName")
	private String AuthContactPersonName;

	@Column(name = "AuthContactPersonID")
	private String AuthContactPersonID;

	@Column(name = "AuthContactPersonNumber")
	private String AuthContactPersonNumber;

	@Column(name = "AuthContactPersonNationality")
	private String AuthContactPersonNationality;
	

	@Column(name = "CompanyType")
	private String CompanyType;
	
	@Column(name = "Remarks")
	private String Remarks;


	@Column(name = "OwnerName")
	private String OwnerName;

	@Column(name = "Designation")
	private String Designation;
	
	@Column(name = "IdNo")
	private String IdNo;
	
	@Column(name = "ContactNo")
	private String ContactNo;

	@Column(name = "Nationality")
	private String Nationality;

	@Column(name = "ResidentialAddress")
	private String ResidentialAddress;
	
	@Column(name = "YearsOccupied")
	private String YearsOccupied;

	 @Column(name = "SizeOfPremise")
	 private String SizeOfPremise;

	@Column(name = "EstimatedStockValue")
	private String EstimatedStockValue;

	@Column(name = "NoOfEmployee" )
	private String NoOfEmployee;
	
	@Column(name = "BusinessHours")
	private String BusinessHours;

	@Column(name = "ExistingAcquirer")
	private String ExistingAcquirer;

	@Column(name = "ApproxMonthlyTurnoverCard")
	private String ApproxMonthlyTurnoverCard;

	@Column(name = "ApproxMonthlyTurnoverCash")
	private String ApproxMonthlyTurnoverCash;

	@Column(name = "ApproxMonthlyTurnoverOther")
	private String ApproxMonthlyTurnoverOther;
	
	@Column(name = "NoOfDailyTxn")
	private String NoOfDailyTxn;
	
	@Column(name = "AvgTxnSize")
	private String AvgTxnSize;

	@Column(name = "AdvanceOrPartial")
	private String AdvanceOrPartial;

	@Column(name = "SaveOrSubmit")
	private String SaveOrSubmit;
	
	@Column(name = "RegisteredAddress")
	private String RegisteredAddress;
	
	@Column(name = "BusinessAddress")
	private String BusinessAddress;
	
	@Column(name = "MailingAddress")
	private String MailingAddress;

	@Column(name = "Website")
	private String Website;

	@Column(name = "NOB")
	private String NOB;

	@Column(name = "TOB")
	private String TOB;

	@Column(name = "PremiseType")
	private String PremiseType;

	@Column(name = "PropertyType")
	private String PropertyType;

	@Column(name = "ExistingMDR")
	private String ExistingMDR;

	@Column(name = "requestType")
	private String requestType;

	@Column(name = "MDRCredit")
	private String MDRCredit;

	@Column(name = "MDRDebit")
	private String MDRDebit;

	@Column(name = "DTL")
	private String DTL;

	@Column(name = "MID")
	private String MID;
	
	

	@Column(name = "liteMid")
	private String liteMid;

	@Column(name = "TID")
	private String TID;

	@Column(name = "MCC")
	private String MCC;

	@Column(name = "MailingAddrState")
	private String MailingAddrState;

	@Column(name = "MailingAddrCity")
	private String MailingAddrCity;

	@Column(name = "MailingAddrZip")
	private String MailingAddrZip;
	
	@Column(name = "BusinessAddrState")
	private String BusinessAddrState;
	
	@Column(name = "BusinessAddrCity")
	private String BusinessAddrCity;
	
	@Column(name = "BusinessAddrZip")
	private String BusinessAddrZip;
	
	@Column(name = "RegisteredAddrState")
	private String RegisteredAddrState;
	
	@Column(name = "RegisteredAddrCity")
	private String RegisteredAddrCity;

   @Column(name = "RegisteredAddrZip")
	private String RegisteredAddrZip;
	
	@Column(name = "SalesPersonId")
	private String SalesPersonId;
	
	@Column(name = "WhoSigned")
	private String WhoSigned;
	
	@Column(name = "WhoSignedNRIC")
	private String WhoSignedNRIC;
	
	
	
	@Column(name = "OrderId")
	private String OrderId;

   @Column(name = "USResident")
	private String USResident;
	
	@Column(name = "USCitizen")
	private String USCitizen;
	
	@Column(name = "USPR")
	private String USPR;
	
	@Column(name = "USTaxID")
	private String USTaxID;
	
	@Column(name = "CountryOfBirth")
	private String CountryOfBirth;

   @Column(name = "DeploymentDate")
	private String DeploymentDate;
	
	@Column(name = "PaymentReceivedDate")
	private String PaymentReceivedDate;
	
	@Column(name = "KatID")
	private String KatID;
	
	@Column(name = "office_addr_file")
	private String office_addr_file;
	
	
	@Column(name = "passport_file")
	private String passport_file;

   @Column(name = "UMBusinessStartTime")
	private String UMBusinessStartTime;
	
	@Column(name = "UMBusinessEndTime")
	private String UMBusinessEndTime;
	
	@Column(name = "UMobileNOB")
	private String UMobileNOB;
	
	@Column(name = "UMMerchantSize")
	private String UMMerchantSize;
	
	@Column(name = "UMobileState")
	private String UMobileState;

   @Column(name = "UMobileTown")
	private String UMobileTown;
	
	@Column(name = "UMobileEcommerceIndustry")
	private String UMobileEcommerceIndustry;
	
	@Column(name = "UMPreviousAcquirer")
	private String UMPreviousAcquirer;
	
	@Column(name = "UMPreviousAcquirerCeasedDate")
	private String UMPreviousAcquirerCeasedDate;
	
	
	
	@Column(name = "UMMastercardMCC")
	private String UMMastercardMCC;

   @Column(name = "UMVisaMCC")
	private String UMVisaMCC;
	
	@Column(name = "UMUnionPayMCC")
	private String UMUnionPayMCC;
	
	@Column(name = "UMIDType")
	private String UMIDType;
	
	@Column(name = "UMMPOSModel")
	private String UMMPOSModel;
	
	@Column(name = "UMobileCountry")
	private String UMobileCountry;

   @Column(name = "UMNumberOfMPOS")
	private String UMNumberOfMPOS;
	
	@Column(name = "WhoSignedMobile")
	private String WhoSignedMobile;
	
	@Column(name = "RequestedDTL")
	private String RequestedDTL;
	
	@Column(name = "RiskApprovedDTL")
	private String RiskApprovedDTL;
	
	
	@Column(name = "RiskReason")
	private String RiskReason;

   @Column(name = "HighRiskMark")
	private String HighRiskMark;
	
	@Column(name = "HighRiskStatus")
	private String HighRiskStatus;
	
	@Column(name = "HighRiskMarkedOn")
	private String HighRiskMarkedOn;
	
	@Column(name = "GrabPayLatitude")
	private String GrabPayLatitude;
	
	@Column(name = "GrabPayLongitude")
	private String GrabPayLongitude;

   @Column(name = "AccountType")
	private String AccountType;
	
	@Column(name = "MasterMerchant")
	private String MasterMerchant;
	
	@Column(name = "MigratedOrder")
	private String MigratedOrder;

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

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getRegisteredName() {
		return RegisteredName;
	}

	public void setRegisteredName(String registeredName) {
		RegisteredName = registeredName;
	}

	public String getRegistrationNumber() {
		return RegistrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		RegistrationNumber = registrationNumber;
	}

	public String getOtherRegistrationNumber() {
		return OtherRegistrationNumber;
	}

	public void setOtherRegistrationNumber(String otherRegistrationNumber) {
		OtherRegistrationNumber = otherRegistrationNumber;
	}

	public String getGstNo() {
		return GstNo;
	}

	public void setGstNo(String gstNo) {
		GstNo = gstNo;
	}

	public String getBusinessName() {
		return BusinessName;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

	public void setBusinessName(String businessName) {
		BusinessName = businessName;
	}

	public String getOfficeNo() {
		return OfficeNo;
	}

	public void setOfficeNo(String officeNo) {
		OfficeNo = officeNo;
	}

	public String getMobileNo() {
		return MobileNo;
	}

	public void setMobileNo(String mobileNo) {
		MobileNo = mobileNo;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getBankName() {
		return BankName;
	}

	public void setBankName(String bankName) {
		BankName = bankName;
	}

	public String getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}

	public String getDateIncorporated() {
		return DateIncorporated;
	}

	public void setDateIncorporated(String dateIncorporated) {
		DateIncorporated = dateIncorporated;
	}

	public String getAuthContactPersonName() {
		return AuthContactPersonName;
	}

	public void setAuthContactPersonName(String authContactPersonName) {
		AuthContactPersonName = authContactPersonName;
	}

	public String getAuthContactPersonID() {
		return AuthContactPersonID;
	}

	public void setAuthContactPersonID(String authContactPersonID) {
		AuthContactPersonID = authContactPersonID;
	}

	public String getAuthContactPersonNumber() {
		return AuthContactPersonNumber;
	}

	public void setAuthContactPersonNumber(String authContactPersonNumber) {
		AuthContactPersonNumber = authContactPersonNumber;
	}

	public String getAuthContactPersonNationality() {
		return AuthContactPersonNationality;
	}

	public void setAuthContactPersonNationality(String authContactPersonNationality) {
		AuthContactPersonNationality = authContactPersonNationality;
	}

	public String getCompanyType() {
		return CompanyType;
	}

	public void setCompanyType(String companyType) {
		CompanyType = companyType;
	}

	public String getOwnerName() {
		return OwnerName;
	}

	public void setOwnerName(String ownerName) {
		OwnerName = ownerName;
	}

	public String getDesignation() {
		return Designation;
	}

	public void setDesignation(String designation) {
		Designation = designation;
	}

	public String getIdNo() {
		return IdNo;
	}

	public void setIdNo(String idNo) {
		IdNo = idNo;
	}

	public String getContactNo() {
		return ContactNo;
	}

	public void setContactNo(String contactNo) {
		ContactNo = contactNo;
	}

	public String getNationality() {
		return Nationality;
	}

	public void setNationality(String nationality) {
		Nationality = nationality;
	}

	public String getResidentialAddress() {
		return ResidentialAddress;
	}

	public void setResidentialAddress(String residentialAddress) {
		ResidentialAddress = residentialAddress;
	}

	public String getYearsOccupied() {
		return YearsOccupied;
	}

	public void setYearsOccupied(String yearsOccupied) {
		YearsOccupied = yearsOccupied;
	}

	public String getSizeOfPremise() {
		return SizeOfPremise;
	}

	public void setSizeOfPremise(String sizeOfPremise) {
		SizeOfPremise = sizeOfPremise;
	}

	public String getEstimatedStockValue() {
		return EstimatedStockValue;
	}

	public void setEstimatedStockValue(String estimatedStockValue) {
		EstimatedStockValue = estimatedStockValue;
	}

	public String getNoOfEmployee() {
		return NoOfEmployee;
	}

	public void setNoOfEmployee(String noOfEmployee) {
		NoOfEmployee = noOfEmployee;
	}

	public String getBusinessHours() {
		return BusinessHours;
	}

	public void setBusinessHours(String businessHours) {
		BusinessHours = businessHours;
	}

	public String getExistingAcquirer() {
		return ExistingAcquirer;
	}

	public void setExistingAcquirer(String existingAcquirer) {
		ExistingAcquirer = existingAcquirer;
	}

	public String getApproxMonthlyTurnoverCard() {
		return ApproxMonthlyTurnoverCard;
	}

	public void setApproxMonthlyTurnoverCard(String approxMonthlyTurnoverCard) {
		ApproxMonthlyTurnoverCard = approxMonthlyTurnoverCard;
	}

	public String getApproxMonthlyTurnoverCash() {
		return ApproxMonthlyTurnoverCash;
	}

	public void setApproxMonthlyTurnoverCash(String approxMonthlyTurnoverCash) {
		ApproxMonthlyTurnoverCash = approxMonthlyTurnoverCash;
	}

	public String getApproxMonthlyTurnoverOther() {
		return ApproxMonthlyTurnoverOther;
	}

	public void setApproxMonthlyTurnoverOther(String approxMonthlyTurnoverOther) {
		ApproxMonthlyTurnoverOther = approxMonthlyTurnoverOther;
	}

	public String getNoOfDailyTxn() {
		return NoOfDailyTxn;
	}

	public void setNoOfDailyTxn(String noOfDailyTxn) {
		NoOfDailyTxn = noOfDailyTxn;
	}

	public String getAvgTxnSize() {
		return AvgTxnSize;
	}

	public void setAvgTxnSize(String avgTxnSize) {
		AvgTxnSize = avgTxnSize;
	}

	public String getAdvanceOrPartial() {
		return AdvanceOrPartial;
	}

	public void setAdvanceOrPartial(String advanceOrPartial) {
		AdvanceOrPartial = advanceOrPartial;
	}

	public String getSaveOrSubmit() {
		return SaveOrSubmit;
	}

	public void setSaveOrSubmit(String saveOrSubmit) {
		SaveOrSubmit = saveOrSubmit;
	}

	public String getRegisteredAddress() {
		return RegisteredAddress;
	}

	public void setRegisteredAddress(String registeredAddress) {
		RegisteredAddress = registeredAddress;
	}

	public String getBusinessAddress() {
		return BusinessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		BusinessAddress = businessAddress;
	}

	public String getMailingAddress() {
		return MailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		MailingAddress = mailingAddress;
	}

	public String getWebsite() {
		return Website;
	}

	public void setWebsite(String website) {
		Website = website;
	}

	public String getNOB() {
		return NOB;
	}

	public void setNOB(String nOB) {
		NOB = nOB;
	}

	public String getTOB() {
		return TOB;
	}

	public void setTOB(String tOB) {
		TOB = tOB;
	}

	public String getPremiseType() {
		return PremiseType;
	}

	public void setPremiseType(String premiseType) {
		PremiseType = premiseType;
	}

	public String getPropertyType() {
		return PropertyType;
	}

	public void setPropertyType(String propertyType) {
		PropertyType = propertyType;
	}

	public String getExistingMDR() {
		return ExistingMDR;
	}

	public void setExistingMDR(String existingMDR) {
		ExistingMDR = existingMDR;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getMDRCredit() {
		return MDRCredit;
	}

	public void setMDRCredit(String mDRCredit) {
		MDRCredit = mDRCredit;
	}

	public String getMDRDebit() {
		return MDRDebit;
	}

	public void setMDRDebit(String mDRDebit) {
		MDRDebit = mDRDebit;
	}

	public String getDTL() {
		return DTL;
	}

	public void setDTL(String dTL) {
		DTL = dTL;
	}

	public String getMID() {
		return MID;
	}

	public void setMID(String mID) {
		MID = mID;
	}

	public String getLiteMid() {
		return liteMid;
	}

	public void setLiteMid(String liteMid) {
		this.liteMid = liteMid;
	}

	public String getTID() {
		return TID;
	}

	public void setTID(String tID) {
		TID = tID;
	}

	public String getMCC() {
		return MCC;
	}

	public void setMCC(String mCC) {
		MCC = mCC;
	}

	public String getMailingAddrState() {
		return MailingAddrState;
	}

	public void setMailingAddrState(String mailingAddrState) {
		MailingAddrState = mailingAddrState;
	}

	public String getMailingAddrCity() {
		return MailingAddrCity;
	}

	public void setMailingAddrCity(String mailingAddrCity) {
		MailingAddrCity = mailingAddrCity;
	}

	public String getMailingAddrZip() {
		return MailingAddrZip;
	}

	public void setMailingAddrZip(String mailingAddrZip) {
		MailingAddrZip = mailingAddrZip;
	}

	public String getBusinessAddrState() {
		return BusinessAddrState;
	}

	public void setBusinessAddrState(String businessAddrState) {
		BusinessAddrState = businessAddrState;
	}

	public String getBusinessAddrCity() {
		return BusinessAddrCity;
	}

	public void setBusinessAddrCity(String businessAddrCity) {
		BusinessAddrCity = businessAddrCity;
	}

	public String getBusinessAddrZip() {
		return BusinessAddrZip;
	}

	public void setBusinessAddrZip(String businessAddrZip) {
		BusinessAddrZip = businessAddrZip;
	}

	public String getRegisteredAddrState() {
		return RegisteredAddrState;
	}

	public void setRegisteredAddrState(String registeredAddrState) {
		RegisteredAddrState = registeredAddrState;
	}

	public String getRegisteredAddrCity() {
		return RegisteredAddrCity;
	}

	public void setRegisteredAddrCity(String registeredAddrCity) {
		RegisteredAddrCity = registeredAddrCity;
	}

	public String getRegisteredAddrZip() {
		return RegisteredAddrZip;
	}

	public void setRegisteredAddrZip(String registeredAddrZip) {
		RegisteredAddrZip = registeredAddrZip;
	}

	public String getSalesPersonId() {
		return SalesPersonId;
	}

	public void setSalesPersonId(String salesPersonId) {
		SalesPersonId = salesPersonId;
	}

	public String getWhoSigned() {
		return WhoSigned;
	}

	public void setWhoSigned(String whoSigned) {
		WhoSigned = whoSigned;
	}

	public String getWhoSignedNRIC() {
		return WhoSignedNRIC;
	}

	public void setWhoSignedNRIC(String whoSignedNRIC) {
		WhoSignedNRIC = whoSignedNRIC;
	}

	public String getOrderId() {
		return OrderId;
	}

	public void setOrderId(String orderId) {
		OrderId = orderId;
	}

	public String getUSResident() {
		return USResident;
	}

	public void setUSResident(String uSResident) {
		USResident = uSResident;
	}

	public String getUSCitizen() {
		return USCitizen;
	}

	public void setUSCitizen(String uSCitizen) {
		USCitizen = uSCitizen;
	}

	public String getUSPR() {
		return USPR;
	}

	public void setUSPR(String uSPR) {
		USPR = uSPR;
	}

	public String getUSTaxID() {
		return USTaxID;
	}

	public void setUSTaxID(String uSTaxID) {
		USTaxID = uSTaxID;
	}

	public String getCountryOfBirth() {
		return CountryOfBirth;
	}

	public void setCountryOfBirth(String countryOfBirth) {
		CountryOfBirth = countryOfBirth;
	}

	public String getDeploymentDate() {
		return DeploymentDate;
	}

	public void setDeploymentDate(String deploymentDate) {
		DeploymentDate = deploymentDate;
	}

	public String getPaymentReceivedDate() {
		return PaymentReceivedDate;
	}

	public void setPaymentReceivedDate(String paymentReceivedDate) {
		PaymentReceivedDate = paymentReceivedDate;
	}

	public String getKatID() {
		return KatID;
	}

	public void setKatID(String katID) {
		KatID = katID;
	}

	public String getOffice_addr_file() {
		return office_addr_file;
	}

	public void setOffice_addr_file(String office_addr_file) {
		this.office_addr_file = office_addr_file;
	}

	public String getPassport_file() {
		return passport_file;
	}

	public void setPassport_file(String passport_file) {
		this.passport_file = passport_file;
	}

	public String getUMBusinessStartTime() {
		return UMBusinessStartTime;
	}

	public void setUMBusinessStartTime(String uMBusinessStartTime) {
		UMBusinessStartTime = uMBusinessStartTime;
	}

	public String getUMBusinessEndTime() {
		return UMBusinessEndTime;
	}

	public void setUMBusinessEndTime(String uMBusinessEndTime) {
		UMBusinessEndTime = uMBusinessEndTime;
	}

	public String getEncrypted() {
		return Encrypted;
	}

	public void setEncrypted(String encrypted) {
		Encrypted = encrypted;
	}

	public String getIPAddress() {
		return IPAddress;
	}

	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}

	public String getUMobileNOB() {
		return UMobileNOB;
	}

	public void setUMobileNOB(String uMobileNOB) {
		UMobileNOB = uMobileNOB;
	}

	public String getUMMerchantSize() {
		return UMMerchantSize;
	}

	public void setUMMerchantSize(String uMMerchantSize) {
		UMMerchantSize = uMMerchantSize;
	}

	public String getUMobileState() {
		return UMobileState;
	}

	public void setUMobileState(String uMobileState) {
		UMobileState = uMobileState;
	}

	public String getUMobileTown() {
		return UMobileTown;
	}

	public void setUMobileTown(String uMobileTown) {
		UMobileTown = uMobileTown;
	}

	public String getUMobileEcommerceIndustry() {
		return UMobileEcommerceIndustry;
	}

	public void setUMobileEcommerceIndustry(String uMobileEcommerceIndustry) {
		UMobileEcommerceIndustry = uMobileEcommerceIndustry;
	}

	public String getUMPreviousAcquirer() {
		return UMPreviousAcquirer;
	}

	public void setUMPreviousAcquirer(String uMPreviousAcquirer) {
		UMPreviousAcquirer = uMPreviousAcquirer;
	}

	public String getUMPreviousAcquirerCeasedDate() {
		return UMPreviousAcquirerCeasedDate;
	}

	public void setUMPreviousAcquirerCeasedDate(String uMPreviousAcquirerCeasedDate) {
		UMPreviousAcquirerCeasedDate = uMPreviousAcquirerCeasedDate;
	}

	public String getUMMastercardMCC() {
		return UMMastercardMCC;
	}

	public void setUMMastercardMCC(String uMMastercardMCC) {
		UMMastercardMCC = uMMastercardMCC;
	}

	public String getUMVisaMCC() {
		return UMVisaMCC;
	}

	public void setUMVisaMCC(String uMVisaMCC) {
		UMVisaMCC = uMVisaMCC;
	}

	public String getUMUnionPayMCC() {
		return UMUnionPayMCC;
	}

	public void setUMUnionPayMCC(String uMUnionPayMCC) {
		UMUnionPayMCC = uMUnionPayMCC;
	}

	public String getUMIDType() {
		return UMIDType;
	}

	public void setUMIDType(String uMIDType) {
		UMIDType = uMIDType;
	}

	public String getUMMPOSModel() {
		return UMMPOSModel;
	}

	public void setUMMPOSModel(String uMMPOSModel) {
		UMMPOSModel = uMMPOSModel;
	}

	public String getUMobileCountry() {
		return UMobileCountry;
	}

	public void setUMobileCountry(String uMobileCountry) {
		UMobileCountry = uMobileCountry;
	}

	public String getUMNumberOfMPOS() {
		return UMNumberOfMPOS;
	}

	public void setUMNumberOfMPOS(String uMNumberOfMPOS) {
		UMNumberOfMPOS = uMNumberOfMPOS;
	}

	public String getWhoSignedMobile() {
		return WhoSignedMobile;
	}

	public void setWhoSignedMobile(String whoSignedMobile) {
		WhoSignedMobile = whoSignedMobile;
	}

	public String getRequestedDTL() {
		return RequestedDTL;
	}

	public void setRequestedDTL(String requestedDTL) {
		RequestedDTL = requestedDTL;
	}

	public String getRiskApprovedDTL() {
		return RiskApprovedDTL;
	}

	public void setRiskApprovedDTL(String riskApprovedDTL) {
		RiskApprovedDTL = riskApprovedDTL;
	}

	public String getRiskReason() {
		return RiskReason;
	}

	public void setRiskReason(String riskReason) {
		RiskReason = riskReason;
	}

	public String getHighRiskMark() {
		return HighRiskMark;
	}

	public void setHighRiskMark(String highRiskMark) {
		HighRiskMark = highRiskMark;
	}

	public String getHighRiskStatus() {
		return HighRiskStatus;
	}

	public void setHighRiskStatus(String highRiskStatus) {
		HighRiskStatus = highRiskStatus;
	}

	public String getHighRiskMarkedOn() {
		return HighRiskMarkedOn;
	}

	public void setHighRiskMarkedOn(String highRiskMarkedOn) {
		HighRiskMarkedOn = highRiskMarkedOn;
	}

	public String getGrabPayLatitude() {
		return GrabPayLatitude;
	}

	public void setGrabPayLatitude(String grabPayLatitude) {
		GrabPayLatitude = grabPayLatitude;
	}

	public String getGrabPayLongitude() {
		return GrabPayLongitude;
	}

	public void setGrabPayLongitude(String grabPayLongitude) {
		GrabPayLongitude = grabPayLongitude;
	}

	public String getAccountType() {
		return AccountType;
	}

	public void setAccountType(String accountType) {
		AccountType = accountType;
	}

	public String getMasterMerchant() {
		return MasterMerchant;
	}

	public void setMasterMerchant(String masterMerchant) {
		MasterMerchant = masterMerchant;
	}

	public String getMigratedOrder() {
		return MigratedOrder;
	}

	public void setMigratedOrder(String migratedOrder) {
		MigratedOrder = migratedOrder;
	}

	@Override
	public String toString() {
		return "MerchantDetails [id=" + id + ", CreatedOn=" + createdOn + ", UpdatedOn=" + updatedOn + ", UserId="
				+ UserId + ", UserName=" + UserName + ", RegisteredName=" + RegisteredName + ", RegistrationNumber="
				+ RegistrationNumber + ", OtherRegistrationNumber=" + OtherRegistrationNumber + ", GstNo=" + GstNo
				+ ", BusinessName=" + BusinessName + ", OfficeNo=" + OfficeNo + ", MobileNo=" + MobileNo + ", Email="
				+ Email + ", BankName=" + BankName + ", AccountNumber=" + AccountNumber + ", DateIncorporated="
				+ DateIncorporated + ", AuthContactPersonName=" + AuthContactPersonName + ", AuthContactPersonID="
				+ AuthContactPersonID + ", AuthContactPersonNumber=" + AuthContactPersonNumber
				+ ", AuthContactPersonNationality=" + AuthContactPersonNationality + ", CompanyType=" + CompanyType
				+ ", OwnerName=" + OwnerName + ", Designation=" + Designation + ", IdNo=" + IdNo + ", ContactNo="
				+ ContactNo + ", Nationality=" + Nationality + ", ResidentialAddress=" + ResidentialAddress
				+ ", YearsOccupied=" + YearsOccupied + ", SizeOfPremise=" + SizeOfPremise + ", EstimatedStockValue="
				+ EstimatedStockValue + ", NoOfEmployee=" + NoOfEmployee + ", BusinessHours=" + BusinessHours
				+ ", ExistingAcquirer=" + ExistingAcquirer + ", ApproxMonthlyTurnoverCard=" + ApproxMonthlyTurnoverCard
				+ ", ApproxMonthlyTurnoverCash=" + ApproxMonthlyTurnoverCash + ", ApproxMonthlyTurnoverOther="
				+ ApproxMonthlyTurnoverOther + ", NoOfDailyTxn=" + NoOfDailyTxn + ", AvgTxnSize=" + AvgTxnSize
				+ ", AdvanceOrPartial=" + AdvanceOrPartial + ", SaveOrSubmit=" + SaveOrSubmit + ", RegisteredAddress="
				+ RegisteredAddress + ", BusinessAddress=" + BusinessAddress + ", MailingAddress=" + MailingAddress
				+ ", Website=" + Website + ", NOB=" + NOB + ", TOB=" + TOB + ", PremiseType=" + PremiseType
				+ ", PropertyType=" + PropertyType + ", ExistingMDR=" + ExistingMDR + ", requestType=" + requestType
				+ ", MDRCredit=" + MDRCredit + ", MDRDebit=" + MDRDebit + ", DTL=" + DTL + ", MID=" + MID + ", liteMid="
				+ liteMid + ", TID=" + TID + ", MCC=" + MCC + ", MailingAddrState=" + MailingAddrState
				+ ", MailingAddrCity=" + MailingAddrCity + ", MailingAddrZip=" + MailingAddrZip + ", BusinessAddrState="
				+ BusinessAddrState + ", BusinessAddrCity=" + BusinessAddrCity + ", BusinessAddrZip=" + BusinessAddrZip
				+ ", RegisteredAddrState=" + RegisteredAddrState + ", RegisteredAddrCity=" + RegisteredAddrCity
				+ ", RegisteredAddrZip=" + RegisteredAddrZip + ", SalesPersonId=" + SalesPersonId + ", WhoSigned="
				+ WhoSigned + ", WhoSignedNRIC=" + WhoSignedNRIC + ", OrderId=" + OrderId + ", USResident=" + USResident
				+ ", USCitizen=" + USCitizen + ", USPR=" + USPR + ", USTaxID=" + USTaxID + ", CountryOfBirth="
				+ CountryOfBirth + ", DeploymentDate=" + DeploymentDate + ", PaymentReceivedDate=" + PaymentReceivedDate
				+ ", KatID=" + KatID + ", office_addr_file=" + office_addr_file + ", passport_file=" + passport_file
				+ ", UMBusinessStartTime=" + UMBusinessStartTime + ", UMBusinessEndTime=" + UMBusinessEndTime
				+ ", UMobileNOB=" + UMobileNOB + ", UMMerchantSize=" + UMMerchantSize + ", UMobileState=" + UMobileState
				+ ", UMobileTown=" + UMobileTown + ", UMobileEcommerceIndustry=" + UMobileEcommerceIndustry
				+ ", UMPreviousAcquirer=" + UMPreviousAcquirer + ", UMPreviousAcquirerCeasedDate="
				+ UMPreviousAcquirerCeasedDate + ", UMMastercardMCC=" + UMMastercardMCC + ", UMVisaMCC=" + UMVisaMCC
				+ ", UMUnionPayMCC=" + UMUnionPayMCC + ", UMIDType=" + UMIDType + ", UMMPOSModel=" + UMMPOSModel
				+ ", UMobileCountry=" + UMobileCountry + ", UMNumberOfMPOS=" + UMNumberOfMPOS + ", WhoSignedMobile="
				+ WhoSignedMobile + ", RequestedDTL=" + RequestedDTL + ", RiskApprovedDTL=" + RiskApprovedDTL
				+ ", RiskReason=" + RiskReason + ", HighRiskMark=" + HighRiskMark + ", HighRiskStatus=" + HighRiskStatus
				+ ", HighRiskMarkedOn=" + HighRiskMarkedOn + ", GrabPayLatitude=" + GrabPayLatitude
				+ ", GrabPayLongitude=" + GrabPayLongitude + ", AccountType=" + AccountType + ", MasterMerchant="
				+ MasterMerchant + ", MigratedOrder=" + MigratedOrder + "]";
	}
	
	
	
	

}
