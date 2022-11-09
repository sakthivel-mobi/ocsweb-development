package com.mobi.ocs.modal;

public class OldMerchantDataUpdate {

	private String service;
	private String officeEmail;
	private String reActivateDate;
	private String isCombo;

	public OldMerchantDataUpdate() {
		this.service = "OLD_MERCHANT_UPDATE";

	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getOfficeEmail() {
		return officeEmail;
	}

	public void setOfficeEmail(String officeEmail) {
		this.officeEmail = officeEmail;
	}

	public String getReActivateDate() {
		return reActivateDate;
	}

	public void setReActivateDate(String reActivateDate) {
		this.reActivateDate = reActivateDate;
	}

	public String getIsCombo() {
		return isCombo;
	}

	public void setIsCombo(String isCombo) {
		this.isCombo = isCombo;
	}

	@Override
	public String toString() {
		return "OldMerchantDataUpdate [service=" + service + ", officeEmail=" + officeEmail + ", reActivateDate="
				+ reActivateDate + ", isCombo=" + isCombo + "]";
	}

}
