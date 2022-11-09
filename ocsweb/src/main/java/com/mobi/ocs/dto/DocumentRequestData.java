package com.mobi.ocs.dto;

import java.util.ArrayList;

import com.mobi.ocs.entity.Document;

public class DocumentRequestData {

	private String username;
	private ArrayList<Document> document;

	public DocumentRequestData(String username, ArrayList<Document> document) {
		this.username = username;
		this.document = document;
	}

	public DocumentRequestData() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ArrayList<Document> getDocument() {
		return document;
	}

	public void setDocument(ArrayList<Document> document) {
		this.document = document;
	}

	@Override
	public String toString() {
		return "DocumentRequestData [username=" + username + ", document=" + document + "]";
	}

}
