package com.mobi.ocs.modal;

import java.util.ArrayList;

public class DocumentUploadRequestData {

	private String username;
	private int orderId;
	private ArrayList<String> documentData;
	private ArrayList<String> documentName;
	private ArrayList<String> documentFileType;
	private ArrayList<String> documentFileCategory;

	public DocumentUploadRequestData() {

	}

	public DocumentUploadRequestData(String username, int orderId, ArrayList<String> documentData,
			ArrayList<String> documentName, ArrayList<String> documentFileType,
			ArrayList<String> documentFileCategory) {
		this.username = username;
		this.orderId = orderId;
		this.documentData = documentData;
		this.documentName = documentName;
		this.documentFileType = documentFileType;
		this.documentFileCategory = documentFileCategory;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ArrayList<String> getDocumentData() {
		return documentData;
	}

	public void setDocumentData(ArrayList<String> documentData) {
		this.documentData = documentData;
	}

	public ArrayList<String> getDocumentName() {
		return documentName;
	}

	public void setDocumentName(ArrayList<String> documentName) {
		this.documentName = documentName;
	}

	public ArrayList<String> getDocumentFileType() {
		return documentFileType;
	}

	public void setDocumentFileType(ArrayList<String> documentFileType) {
		this.documentFileType = documentFileType;
	}

	public ArrayList<String> getDocumentFileCategory() {
		return documentFileCategory;
	}

	public void setDocumentFileCategory(ArrayList<String> documentFileCategory) {
		this.documentFileCategory = documentFileCategory;
	}

	@Override
	public String toString() {
		return "DocumentUploadRequestData [username=" + username + ", orderId=" + orderId + ", documentData="
				+ documentData + ", documentName=" + documentName + "]";
	}

}
