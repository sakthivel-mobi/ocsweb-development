package com.mobi.ocs.modal;

public class SignatureRequestModal {

	private String imageBaseString;
	private String username;

	public SignatureRequestModal(String imageBaseString, String username) {
		this.imageBaseString = imageBaseString;
		this.username = username;
	}

	public SignatureRequestModal() {
		super();
	}

	public String getImageBaseString() {
		return imageBaseString;
	}

	public void setImageBaseString(String imageBaseString) {
		this.imageBaseString = imageBaseString;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "SignatureRequestModal [imageBaseString=" + imageBaseString + ", username=" + username + "]";
	}

}
