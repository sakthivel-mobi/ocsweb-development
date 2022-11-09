package com.mobi.ocs.modal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GrabPayReportResponseData {

	@JsonProperty(value = "ORDER ID")
	private String OrderId;

	@JsonProperty(value = "Merchant Registered Name")
	private String MerchantRegistrationName;

	@JsonProperty(value = "Merchant Trading Name")
	private String MerchantTradingName;

	@JsonProperty(value = "Business Registration Number")
	private String BusinessReistrationNumber;

	@JsonProperty(value = "Business Category (MCC)")
	private String BusinessCategory;

	@JsonProperty(value = "MCC")
	private String MCC;

	@JsonProperty(value = "Trading Address Line 1")
	private String TradingAddressLine1;

	@JsonProperty(value = "Province")
	private String Province;

	@JsonProperty(value = "City")
	private String City;

	@JsonProperty(value = "Postal Code")
	private String PostalCode;

	@JsonProperty(value = "Geolocation Latitude")
	private String Latitude;

	@JsonProperty(value = "Geolocation Longitude")
	private String Longitude;

	@JsonProperty(value = "Terminal id required")
	private String TerminalIdRegistrated;

	@JsonProperty(value = "TID")
	private String TID;

	public GrabPayReportResponseData(String orderId, String merchantRegistrationName, String merchantTradingName,
			String businessReistrationNumber, String businessCategory, String mCC, String tradingAddressLine1,
			String province, String city, String postalCode, String latitude, String longitude,
			String terminalIdRegistrated, String tID) {
		OrderId = orderId;
		MerchantRegistrationName = merchantRegistrationName;
		MerchantTradingName = merchantTradingName;
		BusinessReistrationNumber = businessReistrationNumber;
		BusinessCategory = businessCategory;
		MCC = mCC;
		TradingAddressLine1 = tradingAddressLine1;
		Province = province;
		City = city;
		PostalCode = postalCode;
		Latitude = latitude;
		Longitude = longitude;
		TerminalIdRegistrated = terminalIdRegistrated;
		TID = tID;
	}

	@Override
	public String toString() {
		return "GrabPayReportResponseData [OrderId=" + OrderId + ", MerchantRegistrationName="
				+ MerchantRegistrationName + ", MerchantTradingName=" + MerchantTradingName
				+ ", BusinessReistrationNumber=" + BusinessReistrationNumber + ", BusinessCategory=" + BusinessCategory
				+ ", MCC=" + MCC + ", TradingAddressLine1=" + TradingAddressLine1 + ", Province=" + Province + ", City="
				+ City + ", PostalCode=" + PostalCode + ", Latitude=" + Latitude + ", Longitude=" + Longitude
				+ ", TerminalIdRegistrated=" + TerminalIdRegistrated + ", TID=" + TID + "]";
	}

}
