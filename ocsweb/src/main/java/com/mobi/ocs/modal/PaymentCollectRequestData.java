package com.mobi.ocs.modal;

public class PaymentCollectRequestData {

	private String collectedOn;
	private String quotationId;
	private String documentData;
	private String documentName;
	private String documentType;
	private int paymentType;

//	1 for normal payment collect
//	2 for iswitchToMobi

	public PaymentCollectRequestData() {
		this.paymentType = 1;
	}

	public String getCollectedOn() {
		return collectedOn;
	}

	public void setCollectedOn(String collectedOn) {
		this.collectedOn = collectedOn;
	}

	public String getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(String quotationId) {
		this.quotationId = quotationId;
	}

	public String getDocumentData() {
		return documentData;
	}

	public void setDocumentData(String documentData) {
		this.documentData = documentData;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public int getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}

	@Override
	public String toString() {
		return "PaymentCollectRequestData [collectedOn=" + collectedOn + ", quotationId=" + quotationId
				+ ", documentData=" + documentData + ", documentName=" + documentName + ", documentType=" + documentType
				+ ", paymentType=" + paymentType + "]";
	}

}
