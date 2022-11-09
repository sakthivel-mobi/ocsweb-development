package com.mobi.ocs.modal;

public class MerchantOrderListItemResponseData {
	private String id, createdAt, productName, quotationId;
	private Boolean enableWelcomeLetter;

	public MerchantOrderListItemResponseData() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(String quotationId) {
		this.quotationId = quotationId;
	}

	public boolean isEnableWelcomeLetter() {
		return enableWelcomeLetter;
	}

	public void setEnableWelcomeLetter(boolean enableWelcomeLetter) {
		this.enableWelcomeLetter = enableWelcomeLetter;
	}

	@Override
	public String toString() {
		return "MerchantOrderListItemResponseData [id=" + id + ", createdAt=" + createdAt + ", productName="
				+ productName + ", enableWelcomeLetter=" + enableWelcomeLetter + "]";
	}

}
