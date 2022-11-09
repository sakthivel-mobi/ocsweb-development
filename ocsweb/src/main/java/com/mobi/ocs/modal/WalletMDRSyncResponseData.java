package com.mobi.ocs.modal;

public class WalletMDRSyncResponseData {
	private String responseCode;
	private String responseMessage;
	private String responseDescription;
	private ResponseData responseData;

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

		private String tid;
		private String mid;
		private String username;
		private String cardBrand;
		private String activationCode;
		private String productType;
		private String password;
		private String mobiApiKey;
		private String forceReferral;

		public String getTid() {
			return tid;
		}

		public void setTid(String value) {
			this.tid = value;
		}

		public String getMid() {
			return mid;
		}

		public void setMid(String value) {
			this.mid = value;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String value) {
			this.username = value;
		}

		public String getCardBrand() {
			return cardBrand;
		}

		public void setCardBrand(String value) {
			this.cardBrand = value;
		}

		public String getActivationCode() {
			return activationCode;
		}

		public void setActivationCode(String value) {
			this.activationCode = value;
		}

		public String getProductType() {
			return productType;
		}

		public void setProductType(String value) {
			this.productType = value;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String value) {
			this.password = value;
		}

		public String getMobiApiKey() {
			return mobiApiKey;
		}

		public void setMobiApiKey(String mobiApiKey) {
			this.mobiApiKey = mobiApiKey;
		}

		public String getForceReferral() {
			return forceReferral;
		}

		public void setForceReferral(String value) {
			this.forceReferral = value;
		}

		@Override
		public String toString() {
			return "ResponseData [tid=" + tid + ", mid=" + mid + ", username=" + username + ", cardBrand=" + cardBrand
					+ ", activationCode=" + activationCode + ", productType=" + productType + ", password=" + password
					+ ", mobiApiKey=" + mobiApiKey + ", forceReferral=" + forceReferral + "]";
		}

	}

	@Override
	public String toString() {
		return "WalletMDRSyncResponseData [responseCode=" + responseCode + ", responseMessage=" + responseMessage
				+ ", responseDescription=" + responseDescription + ", responseData=" + responseData + "]";
	}

}
