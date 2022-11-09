package com.mobi.ocs.modal;

public class UpdateAuthoritiesRequestData {

	private String userName, userRole;

	public UpdateAuthoritiesRequestData() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "UpdateAuthoritiesRequestData [userName=" + userName + ", userRole=" + userRole + "]";
	}

}
