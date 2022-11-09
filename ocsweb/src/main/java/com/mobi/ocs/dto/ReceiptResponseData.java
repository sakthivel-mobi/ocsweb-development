package com.mobi.ocs.dto;

public class ReceiptResponseData {
	
	private String receiptPath;

	public ReceiptResponseData(String receiptPath) {
		this.receiptPath = receiptPath;
	}
	
	public ReceiptResponseData() {
	}

	public String getReceiptPath() {
		return receiptPath;
	}

	public void setReceiptPath(String receiptPath) {
		this.receiptPath = receiptPath;
	}

	@Override
	public String toString() {
		return "ReceiptResponseData [receiptPath=" + receiptPath + "]";
	}
	
	
	

}
