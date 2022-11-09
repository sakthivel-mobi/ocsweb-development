package com.mobi.ocs.modal;

public class ForgotPasswordRequestData {

	private String userName;
	private String newPassword;
	
	
	public ForgotPasswordRequestData() {
		
	}
	
	public ForgotPasswordRequestData(String userName, String newPassword) {
		this.userName = userName;
		this.newPassword = newPassword;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getNewPassword() {
		return newPassword;
	}


	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}


	@Override
	public String toString() {
		return "ForgotPasswordRequestData [userName=" + userName + ", newPassword=" + newPassword + "]";
	}
	
	

}
