package com.mobi.ocs.modal;

public class QuotationRequestData {

	private String quotationId;

	public QuotationRequestData() {

	}

	public QuotationRequestData(String quotationId) {
		this.quotationId = quotationId;
	}

	public String getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(String quotationId) {
		this.quotationId = quotationId;
	}

	@Override
	public String toString() {
		return "IssueQuotationRequestData [quotationId=" + quotationId + "]";
	}

}
