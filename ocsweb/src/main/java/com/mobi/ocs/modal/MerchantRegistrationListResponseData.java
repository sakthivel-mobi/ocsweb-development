package com.mobi.ocs.modal;

import java.util.ArrayList;
import java.util.List;

import com.mobi.ocs.entity.MerchantRegistration;
import com.mobi.ocs.entity.Order;

public class MerchantRegistrationListResponseData {

	String responseStatus;
	String responseMessage;

	MerchantRegistrationResponse responseData;

	public MerchantRegistrationListResponseData() {
		this.responseData = new MerchantRegistrationResponse();
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public MerchantRegistrationResponse getResponseData() {
		return responseData;
	}

	public void setResponseData(MerchantRegistrationResponse responseData) {
		this.responseData = responseData;
	}

	@Override
	public String toString() {
		return "MerchantRegistrationListResponseData [responseStatus=" + responseStatus + ", responseMessage="
				+ responseMessage + ", responseData=" + responseData + "]";
	}

}
