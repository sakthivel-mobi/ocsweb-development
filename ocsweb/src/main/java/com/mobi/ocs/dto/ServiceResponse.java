package com.mobi.ocs.dto;

import java.util.HashMap;

public class ServiceResponse {

	private String responseCode;

	private String responseMessage;

	private String responseDescription;

	private String responseData;

	public static HashMap<String, String> splitData(String resMsg) {
		resMsg = resMsg.replaceAll("\"", "");
		String[] data1 = resMsg.split(",");
		HashMap<String, String> hm = new HashMap<String, String>();

		for (String data : data1) {
			String[] data2 = data.split(":");
			if (data2.length == 2) {
				hm.put(data2[0], data2[1]);
			}
		}

		if (hm.containsKey("responseMessage")) {
			System.out.println(hm.get("responseMessage"));
		}

		return hm;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public String getResponseData() {
		return responseData;
	}

	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}

	@Override
	public String toString() {
		return "ServiceResponse [responseCode=" + responseCode + ", responseMessage=" + responseMessage
				+ ", responseDescription=" + responseDescription + ", responseData=" + responseData + "]";
	}

	
}
