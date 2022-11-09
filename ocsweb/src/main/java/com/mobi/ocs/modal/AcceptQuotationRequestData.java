package com.mobi.ocs.modal;

public class AcceptQuotationRequestData {

	private String namePerIc;
	private String icNumber;
	private String ipAddress;
	private String orderId;

	public AcceptQuotationRequestData() {

	}

	public AcceptQuotationRequestData(String namePerIc, String icNumber, String ipAddress) {
		this.namePerIc = namePerIc;
		this.icNumber = icNumber;
		this.ipAddress = ipAddress;
		this.orderId = orderId;

	}

	public String getNamePerIc() {
		return namePerIc;
	}

	public void setNamePerIc(String namePerIc) {
		this.namePerIc = namePerIc;
	}

	public String getIcNumber() {
		return icNumber;
	}

	public void setIcNumber(String icNumber) {
		this.icNumber = icNumber;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "AcceptQuotationRequestData [namePerIc=" + namePerIc + ", icNumber=" + icNumber + ", ipAddress="
				+ ipAddress + ", orderId=" + orderId + "]";
	}

}
