package com.mobi.ocs.modal;

public class OldProductListData {

	private String lineId;
	private String description;
	private String productName;
	private String quantity;
	private String amount;

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public OldProductListData() {
		super();
	}

	@Override
	public String toString() {
		return "OldProductListData [lineId=" + lineId + ", description=" + description + ", productName=" + productName
				+ ", quantity=" + quantity + ", amount=" + amount + "]";
	}

}
