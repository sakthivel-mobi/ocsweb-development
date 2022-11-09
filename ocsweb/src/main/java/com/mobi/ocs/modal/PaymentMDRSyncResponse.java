package com.mobi.ocs.modal;

public class PaymentMDRSyncResponse {

	private String responseCode;
	private String responseMessage;
	private String responseDescription;
	private PaymentMDRSyncResponseData responseData;

	public PaymentMDRSyncResponse() {

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

	public PaymentMDRSyncResponseData getResponseData() {
		return responseData;
	}

	public void setResponseData(PaymentMDRSyncResponseData responseData) {
		this.responseData = responseData;
	}

	public class PaymentMDRSyncResponseData {

		private String tid;
		private String mid;
		private String username;
		private String cardBrand;
		private String activationCode;
		private String productType;
		private String mobiApiKey;
		private String forceReferral;

		public PaymentMDRSyncResponseData() {

		}

		public String getTid() {
			return tid;
		}

		public void setTid(String tid) {
			this.tid = tid;
		}

		public String getMid() {
			return mid;
		}

		public void setMid(String mid) {
			this.mid = mid;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getCardBrand() {
			return cardBrand;
		}

		public void setCardBrand(String cardBrand) {
			this.cardBrand = cardBrand;
		}

		public String getActivationCode() {
			return activationCode;
		}

		public void setActivationCode(String activationCode) {
			this.activationCode = activationCode;
		}

		public String getProductType() {
			return productType;
		}

		public void setProductType(String productType) {
			this.productType = productType;
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

		public void setForceReferral(String forceReferral) {
			this.forceReferral = forceReferral;
		}

		@Override
		public String toString() {
			return "PaymentMDRSyncResponseData [tid=" + tid + ", mid=" + mid + ", username=" + username + ", cardBrand="
					+ cardBrand + ", activationCode=" + activationCode + ", productType=" + productType
					+ ", mobiApiKey=" + mobiApiKey + ", forceReferral=" + forceReferral + "]";
		}

	}

	@Override
	public String toString() {
		return "PaymentMDRSyncResponse [responseCode=" + responseCode + ", responseMessage=" + responseMessage
				+ ", responseDescription=" + responseDescription + ", responseData=" + responseData + "]";
	}

}
