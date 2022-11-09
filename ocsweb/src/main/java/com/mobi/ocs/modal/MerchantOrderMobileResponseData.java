package com.mobi.ocs.modal;

public class MerchantOrderMobileResponseData {

	private String id;
	private String createdOn;
	private String productName;

	public MerchantOrderMobileResponseData() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "MerchantOrderMobileResponseData [id=" + id + ", createdOn=" + createdOn + ", productName=" + productName
				+ "]";
	}

}
