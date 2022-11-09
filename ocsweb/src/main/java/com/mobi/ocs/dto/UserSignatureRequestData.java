package com.mobi.ocs.dto;

public class UserSignatureRequestData {
	private String username;

	public UserSignatureRequestData(String username) {
		super();
		this.username = username;
	}

	public UserSignatureRequestData() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "UserSignatureRequestData [username=" + username + "]";
	}
	
	
}
