package com.mobi.ocs.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HubSpotInfo {

	
	private int id;
	private Property properties;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public Property getProperties() {
		return properties;
	}

	public void setProperties(Property properties) {
		this.properties = properties;
	}
	
	
	
	@Override
	public String toString() {
		return "ContactInfo [id=" + id + ", properties=" + properties + "]";
	}



	
	
	
	
}
