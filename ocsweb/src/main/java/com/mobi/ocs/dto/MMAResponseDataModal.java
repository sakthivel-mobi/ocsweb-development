package com.mobi.ocs.dto;

public class MMAResponseDataModal {

	private String mmaPath;

	public MMAResponseDataModal(String mmaPath) {
		this.mmaPath = mmaPath;
	}

	
	public MMAResponseDataModal() {

	}


	public String getMmaPath() {
		return mmaPath;
	}


	public void setMmaPath(String mmaPath) {
		this.mmaPath = mmaPath;
	}


	@Override
	public String toString() {
		return "MMAResponseDataModal [mmaPath=" + mmaPath + "]";
	}
	
	
}
