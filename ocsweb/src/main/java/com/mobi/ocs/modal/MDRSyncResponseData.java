package com.mobi.ocs.modal;

import javax.annotation.processing.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class MDRSyncResponseData {
	@SerializedName("responseCode")
	@Expose
	private String responseCode;
	@SerializedName("responseMessage")
	@Expose
	private String responseMessage;
	@SerializedName("responseDescription")
	@Expose
	private String responseDescription;
	@SerializedName("responseData")
	@Expose
	private BrandResponseData responseData;

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

	public BrandResponseData getResponseData() {
		return responseData;
	}

	public void setResponseData(BrandResponseData responseData) {
		this.responseData = responseData;
	}

	@Override
	public String toString() {
		return "MDRSyncResponseData [responseCode=" + responseCode + ", responseMessage=" + responseMessage
				+ ", responseDescription=" + responseDescription + ", responseData=" + responseData + "]";
	}

	@Generated("jsonschema2pojo")
	public class BrandResponseData {

		@SerializedName("tid")
		@Expose
		private String tid;
		
		@SerializedName("mid")
		@Expose
		private String mid;
		
		@SerializedName("username")
		@Expose
		private String username;
		
		@SerializedName("cardBrand")
		@Expose
		private String cardBrand;

		@SerializedName("activationCode")
		@Expose
		private String activationCode;
		@SerializedName("productType")
		@Expose
		private String productType;

		@SerializedName("mobiApiKey")
		@Expose
		private String mobiApiKey;

		@SerializedName("forceReferral")
		@Expose
		private String forceReferral;
		
		@SerializedName("password")
		@Expose
		private String password;

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

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		@Override
		public String toString() {
			return "ResponseData [tid=" + tid + ", mid=" + mid + ", username=" + username + ", cardBrand=" + cardBrand
					+ ", activationCode=" + activationCode + ", productType=" + productType + ", mobiApiKey="
					+ mobiApiKey + ", forceReferral=" + forceReferral + "]";
		}

	}
}
