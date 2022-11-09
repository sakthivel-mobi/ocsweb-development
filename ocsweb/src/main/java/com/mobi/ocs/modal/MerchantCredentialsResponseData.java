package com.mobi.ocs.modal;

public class MerchantCredentialsResponseData {

	private String quotationId;
	private String orderId;
	private String date;
	private String companyName;
	private String type;
	private Boolean showCredentials;

	public MerchantCredentialsResponseData() {

	}

	public String getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(String quotationId) {
		this.quotationId = quotationId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getShowCredentials() {
		return showCredentials;
	}

	public void setShowCredentials(Boolean showCredentials) {
		this.showCredentials = showCredentials;
	}

	@Override
	public String toString() {
		return "MerchantCredentialsResponseData [quotationId=" + quotationId + ", orderId=" + orderId + ", date=" + date
				+ ", companyName=" + companyName + ", type=" + type + ", showCredentials=" + showCredentials + "]";
	}

}
