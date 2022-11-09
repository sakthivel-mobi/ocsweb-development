package com.mobi.ocs.modal;

public class SendOTPRequestDataModal {
	
	private String mobile;
	
	private String type;
	
	
	public SendOTPRequestDataModal() {
		
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public SendOTPRequestDataModal(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "SendOTPRequestDataModal [mobile=" + mobile + "]";
	}
	
	
	

}
