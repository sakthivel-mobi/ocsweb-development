package com.mobi.ocs.dto;

public class IssueQuotationResponseDataModel {
	
	private String issuedQuotationPath;
	private String fileName;
	public String getIssuedQuotationPath() {
		return issuedQuotationPath;
	}
	public void setIssuedQuotationPath(String issuedQuotationPath) {
		this.issuedQuotationPath = issuedQuotationPath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	public IssueQuotationResponseDataModel() {
		
	}
	@Override
	public String toString() {
		return "IssueQuotationResponseDataModel [issuedQuotationPath=" + issuedQuotationPath + ", fileName=" + fileName
				+ "]";
	}
	
	

}
