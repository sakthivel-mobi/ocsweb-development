package com.mobi.ocs.dto;

public class EmailNotes {
	
	private String sender;
	private String orderId;
	private String companyName;
	private String regarding;
	private String notes;
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getRegarding() {
		return regarding;
	}
	public void setRegarding(String regarding) {
		this.regarding = regarding;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	@Override
	public String toString() {
		return "EmailNotes [sender=" + sender + ", orderId=" + orderId + ", companyName=" + companyName + ", regarding="
				+ regarding + ", notes=" + notes + "]";
	}
	
	

}
