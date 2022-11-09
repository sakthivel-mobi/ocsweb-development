package com.mobi.ocs.dto;

public class WelcomeLetterPathDataModel {

	private String welcomeLetterPath;

	public WelcomeLetterPathDataModel(String welcomeLetterPath) {
		this.welcomeLetterPath = welcomeLetterPath;
	};
	
	public WelcomeLetterPathDataModel() {

	}

	public String getWelcomeLetterPath() {
		return welcomeLetterPath;
	}

	public void setWelcomeLetterPath(String welcomeLetterPath) {
		this.welcomeLetterPath = welcomeLetterPath;
	}

	@Override
	public String toString() {
		return "WelcomeLetterPathDataModel [welcomeLetterPath=" + welcomeLetterPath + "]";
	};
	
	
	
	
	
}
