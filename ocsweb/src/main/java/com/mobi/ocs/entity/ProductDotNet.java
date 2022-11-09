package com.mobi.ocs.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products_dotnet")
public class ProductDotNet {
	@Id
	@Column(name = "ProductID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ProductID;
	
	@Column(name = "CreatedBy")
	private String CreatedBy;
	
	@Column(name = "CreatedOn")
	private LocalDateTime CreatedOn;
	
	@Column(name = "IPAddress")
	private String IPAddress;
	
	@Column(name = "ProductName")
	private String ProductName;
	
	@Column(name = "Description")
	private String Description;
	
	@Column(name = "ImagePath")
	private String ImagePath;
	
	@Column(name = "UnitPrice")
	private Double UnitPrice;
	
	@Column(name = "CategoryID")
	private int CategoryID;
	
	@Column(name = "Quantity")
	private int Quantity;
	
	@Column(name = "Active")
	private boolean Active;
	
	@Column(name = "OldProductName")
	private String OldProductName;
	
	@Column(name = "OldImagePath")
	private String OldImagePath;
	
	@Column(name = "OldDescription")
	private String OldDescription;
	
	@Column(name = "OneTimePrice")
	private String OneTimePrice;
	
	@Column(name = "MonthlyPrice")
	private String MonthlyPrice;
	
	@Column(name = "YearlyPrice")
	private String YearlyPrice;
	
	@Column(name = "LifetimePrice")
	private String LifetimePrice;
	
	@Column(name = "IncludeInUM")
	private boolean IncludeInUM;
	
	@Column(name = "IncludeInWallet")
	private boolean IncludeInWallet;
	
	@Column(name = "SettlementPeriod")
	private int SettlementPeriod;
	
	@Column(name = "PayLater")
	private int PayLater;
	
	@Column(name = "Amount")
	private Double Amount;
	
	@Column(name = "Instalment")
	private int Instalment;
	
	@Column(name = "ProductType")
	private String ProductType;
	
	@Column(name = "Subscription")
	private int Subscription;
	
	@Column(name = "ShowInQuote")
	private boolean ShowInQuote;
	
	@Column(name = "RateID")
	private int RateID;
	
	@Column(name = "TNC")
	private int TNC;

	public ProductDotNet() {
	}

	public int getProductID() {
		return ProductID;
	}

	public void setProductID(int productID) {
		ProductID = productID;
	}

	public String getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}

	public LocalDateTime getCreatedOn() {
		return CreatedOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		CreatedOn = createdOn;
	}

	public String getIPAddress() {
		return IPAddress;
	}

	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getImagePath() {
		return ImagePath;
	}

	public void setImagePath(String imagePath) {
		ImagePath = imagePath;
	}

	public Double getUnitPrice() {
		return UnitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		UnitPrice = unitPrice;
	}

	public int getCategoryID() {
		return CategoryID;
	}

	public void setCategoryID(int categoryID) {
		CategoryID = categoryID;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public boolean isActive() {
		return Active;
	}

	public void setActive(boolean active) {
		Active = active;
	}

	public String getOldProductName() {
		return OldProductName;
	}

	public void setOldProductName(String oldProductName) {
		OldProductName = oldProductName;
	}

	public String getOldImagePath() {
		return OldImagePath;
	}

	public void setOldImagePath(String oldImagePath) {
		OldImagePath = oldImagePath;
	}

	public String getOldDescription() {
		return OldDescription;
	}

	public void setOldDescription(String oldDescription) {
		OldDescription = oldDescription;
	}

	public String getOneTimePrice() {
		return OneTimePrice;
	}

	public void setOneTimePrice(String oneTimePrice) {
		OneTimePrice = oneTimePrice;
	}

	public String getMonthlyPrice() {
		return MonthlyPrice;
	}

	public void setMonthlyPrice(String monthlyPrice) {
		MonthlyPrice = monthlyPrice;
	}

	public String getYearlyPrice() {
		return YearlyPrice;
	}

	public void setYearlyPrice(String yearlyPrice) {
		YearlyPrice = yearlyPrice;
	}

	public String getLifetimePrice() {
		return LifetimePrice;
	}

	public void setLifetimePrice(String lifetimePrice) {
		LifetimePrice = lifetimePrice;
	}

	public boolean isIncludeInUM() {
		return IncludeInUM;
	}

	public void setIncludeInUM(boolean includeInUM) {
		IncludeInUM = includeInUM;
	}

	public boolean isIncludeInWallet() {
		return IncludeInWallet;
	}

	public void setIncludeInWallet(boolean includeInWallet) {
		IncludeInWallet = includeInWallet;
	}

	public int getSettlementPeriod() {
		return SettlementPeriod;
	}

	public void setSettlementPeriod(int settlementPeriod) {
		SettlementPeriod = settlementPeriod;
	}

	public int getPayLater() {
		return PayLater;
	}

	public void setPayLater(int payLater) {
		PayLater = payLater;
	}

	public Double getAmount() {
		return Amount;
	}

	public void setAmount(Double amount) {
		Amount = amount;
	}

	public int getInstalment() {
		return Instalment;
	}

	public void setInstalment(int instalment) {
		Instalment = instalment;
	}

	public String getProductType() {
		return ProductType;
	}

	public void setProductType(String productType) {
		ProductType = productType;
	}

	public int getSubscription() {
		return Subscription;
	}

	public void setSubscription(int subscription) {
		Subscription = subscription;
	}

	public boolean isShowInQuote() {
		return ShowInQuote;
	}

	public void setShowInQuote(boolean showInQuote) {
		ShowInQuote = showInQuote;
	}

	public int getRateID() {
		return RateID;
	}

	public void setRateID(int rateID) {
		RateID = rateID;
	}

	public int getTNC() {
		return TNC;
	}

	public void setTNC(int tNC) {
		TNC = tNC;
	}

	@Override
	public String toString() {
		return "ProductDotNet [Active=" + Active + ", Amount=" + Amount + ", CategoryID=" + CategoryID + ", CreatedBy="
				+ CreatedBy + ", CreatedOn=" + CreatedOn + ", Description=" + Description + ", IPAddress=" + IPAddress
				+ ", ImagePath=" + ImagePath + ", IncludeInUM=" + IncludeInUM + ", IncludeInWallet=" + IncludeInWallet
				+ ", Instalment=" + Instalment + ", LifetimePrice=" + LifetimePrice + ", MonthlyPrice=" + MonthlyPrice
				+ ", OldDescription=" + OldDescription + ", OldImagePath=" + OldImagePath + ", OldProductName="
				+ OldProductName + ", OneTimePrice=" + OneTimePrice + ", PayLater=" + PayLater + ", ProductID="
				+ ProductID + ", ProductName=" + ProductName + ", ProductType=" + ProductType + ", Quantity=" + Quantity
				+ ", RateID=" + RateID + ", SettlementPeriod=" + SettlementPeriod + ", ShowInQuote=" + ShowInQuote
				+ ", Subscription=" + Subscription + ", TNC=" + TNC + ", UnitPrice=" + UnitPrice + ", YearlyPrice="
				+ YearlyPrice + "]";
	}

	
}
