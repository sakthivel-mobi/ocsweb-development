package com.mobi.ocs.modal;

public class MerchantRegistrationResponseData {

	private String responseCode;
	private String responseMessage;
	private String responseDescription;
	private MerchantRegistrationResponse responseData;

	public MerchantRegistrationResponseData() {

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

	public MerchantRegistrationResponse getResponseData() {
		return responseData;
	}

	public void setResponseData(MerchantRegistrationResponse responseData) {
		this.responseData = responseData;
	}

	@Override
	public String toString() {
		return "MerchantRegistrationResponseData [responseCode=" + responseCode + ", responseMessage=" + responseMessage
				+ ", responseDescription=" + responseDescription + ", responseData=" + responseData + "]";
	}

	public class MerchantRegistrationResponse {

		private String officeNo;
		private String businessName;
		private String username;
		private String password;
		private String grabMid;
		private String fpxMid;
		private String boostMid;
		public String grabTid;
		public String fpxTid;
		public String boostTid;
		
		public String tngMid;
		public String shopeepayMid;
		public String tngTid;
		public String shopeepayTid;

		public String getTngMid() {
			return tngMid;
		}

		public void setTngMid(String tngMid) {
			this.tngMid = tngMid;
		}

		public String getShopeepayMid() {
			return shopeepayMid;
		}

		public void setShopeepayMid(String shopeepayMid) {
			this.shopeepayMid = shopeepayMid;
		}

		public String getTngTid() {
			return tngTid;
		}

		public void setTngTid(String tngTid) {
			this.tngTid = tngTid;
		}

		public String getShopeepayTid() {
			return shopeepayTid;
		}

		public void setShopeepayTid(String shopeepayTid) {
			this.shopeepayTid = shopeepayTid;
		}

		public MerchantRegistrationResponse() {
			this.password = null;
			this.username = null;
		}

		public String getOfficeNo() {
			return officeNo;
		}

		public void setOfficeNo(String officeNo) {
			this.officeNo = officeNo;
		}

		public String getBusinessName() {
			return businessName;
		}

		public void setBusinessName(String businessName) {
			this.businessName = businessName;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getGrabMid() {
			return grabMid;
		}

		public void setGrabMid(String grabMid) {
			this.grabMid = grabMid;
		}

		public String getFpxMid() {
			return fpxMid;
		}

		public void setFpxMid(String fpxMid) {
			this.fpxMid = fpxMid;
		}

		public String getBoostMid() {
			return boostMid;
		}

		public void setBoostMid(String boostMid) {
			this.boostMid = boostMid;
		}

		public String getGrabTid() {
			return grabTid;
		}

		public void setGrabTid(String grabTid) {
			this.grabTid = grabTid;
		}

		public String getFpxTid() {
			return fpxTid;
		}

		public void setFpxTid(String fpxTid) {
			this.fpxTid = fpxTid;
		}

		public String getBoostTid() {
			return boostTid;
		}

		public void setBoostTid(String boostTid) {
			this.boostTid = boostTid;
		}

		@Override
		public String toString() {
			return "MerchantRegistrationResponse [officeNo=" + officeNo + ", businessName=" + businessName
					+ ", username=" + username + ", password=" + password + ", grabMid=" + grabMid + ", fpxMid="
					+ fpxMid + ", boostMid=" + boostMid + ", grabTid=" + grabTid + ", fpxTid=" + fpxTid + ", boostTid="
					+ boostTid + ", tngMid=" + tngMid + ", shopeepayMid=" + shopeepayMid + ", tngTid=" + tngTid
					+ ", shopeepayTid=" + shopeepayTid + "]";
		}

		
	}
}
