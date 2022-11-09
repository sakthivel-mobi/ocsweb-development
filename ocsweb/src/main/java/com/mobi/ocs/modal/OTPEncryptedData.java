package com.mobi.ocs.modal;

public class OTPEncryptedData {

	private String encryptedText;

	public OTPEncryptedData() {

	}


	public String getEncryptedText() {
		return encryptedText;
	}

	public void setEncryptedText(String encryptedText) {
		this.encryptedText = encryptedText;
	}

	@Override
	public String toString() {
		return "OTPEncryptedData [encryptedText=" + encryptedText + "]";
	}

}
