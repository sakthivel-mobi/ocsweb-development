package com.mobi.ocs.modal;

public class OTPDataSetData {
	
	private String trace;
	private String date;
	private String otp;
	public OTPDataSetData() {

	}
	public OTPDataSetData(String trace, String date, String otp) {
		this.trace = trace;
		this.date = date;
		this.otp = otp;
	}
	public String getTrace() {
		return trace;
	}
	public void setTrace(String trace) {
		this.trace = trace;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	@Override
	public String toString() {
		return "OTPDataSetData [trace=" + trace + ", date=" + date + ", otp=" + otp + "]";
	}

}
