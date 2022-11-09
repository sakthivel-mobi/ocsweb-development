package com.mobi.ocs.modal;

public class PaymentCollectionModel {

	private String quotationId;
	private String acquirer;

	public PaymentCollectionModel() {

	}

	public String getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(String quotationId) {
		this.quotationId = quotationId;
	}

	public String getAcquirer() {
		return acquirer;
	}

	public void setAcquirer(String acquirer) {
		this.acquirer = acquirer;
	}

	@Override
	public String toString() {
		return "PaymentCollectionModel [quotationId=" + quotationId + ", acquirer=" + acquirer + "]";
	}

}
