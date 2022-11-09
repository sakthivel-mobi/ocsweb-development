package com.mobi.ocs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "filetypes")
public class DocumentType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "CompanyType")
	private String companyType;

	@Column(name = "Display")
	private String display;

	@Column(name = "Value")
	private String value;

	@Column(name = "Active")
	private String active;

	@Column(name = "CreatedOn")
	private String createdOn;

	@Column(name = "ShortDisplay")
	private String shortDisplay;

	@Column(name = "ValueAPI")
	private String valueApi;

	public DocumentType() {
	}

	public DocumentType(String companyType, String display, String value, String active, String createdOn,
			String shortDisplay, String valueApi) {
		this.companyType = companyType;
		this.display = display;
		this.value = value;
		this.active = active;
		this.createdOn = createdOn;
		this.shortDisplay = shortDisplay;
		this.valueApi = valueApi;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getShortDisplay() {
		return shortDisplay;
	}

	public void setShortDisplay(String shortDisplay) {
		this.shortDisplay = shortDisplay;
	}

	public String getValueApi() {
		return valueApi;
	}

	public void setValueApi(String valueApi) {
		this.valueApi = valueApi;
	}

	@Override
	public String toString() {
		return "DocumentType [companyType=" + companyType + ", display=" + display + ", value=" + value + ", active="
				+ active + ", createdOn=" + createdOn + ", shortDisplay=" + shortDisplay + ", valueApi=" + valueApi
				+ "]";
	}

}
