package com.mobi.ocs.modal;

public class UserApprovalRequestData {

	private String name;
	private String alias;
	private String email;
	private String phone;
	private String nric;
	private String accessRequired;

	public UserApprovalRequestData() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}

	public String getAccessRequired() {
		return accessRequired;
	}

	public void setAccessRequired(String accessRequired) {
		this.accessRequired = accessRequired;
	}

	@Override
	public String toString() {
		return "UserApprovalRequestData [name=" + name + ", alias=" + alias + ", email=" + email + ", phone=" + phone
				+ ", nric=" + nric + ", accessRequired=" + accessRequired + "]";
	}

}
