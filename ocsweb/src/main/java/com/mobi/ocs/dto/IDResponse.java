package com.mobi.ocs.dto;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IDResponse {

	private String[] results;

	public String[] getResults() {
		return results;
	}

	public void setResults(String[] results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "ContactIdResponse [results=" + Arrays.toString(results) + "]";
	}
	
}
