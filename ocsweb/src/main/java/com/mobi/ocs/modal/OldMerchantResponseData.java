package com.mobi.ocs.modal;

public class OldMerchantResponseData {
	
	private String responseCode;
	private String responseMessage;
	private String responseDescription;
	private ResponseData responseData;

	public OldMerchantResponseData() {

	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String value) {
		this.responseCode = value;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String value) {
		this.responseMessage = value;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String value) {
		this.responseDescription = value;
	}

	public ResponseData getResponseData() {
		return responseData;
	}

	public void setResponseData(ResponseData value) {
		this.responseData = value;
	}

	public class ResponseData {
		private String officeNo;
		private String businessName;
		private String username;

		public ResponseData() {

		}

		public String getOfficeNo() {
			return officeNo;
		}

		public void setOfficeNo(String value) {
			this.officeNo = value;
		}

		public String getBusinessName() {
			return businessName;
		}

		public void setBusinessName(String value) {
			this.businessName = value;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String value) {
			this.username = value;
		}
	}
}