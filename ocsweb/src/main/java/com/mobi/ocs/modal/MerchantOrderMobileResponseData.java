package com.mobi.ocs.modal;

import java.util.Date;

public class MerchantOrderMobileResponseData {

	private String id;
	private Date createdOn;
	private String productName;

	public MerchantOrderMobileResponseData() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
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
