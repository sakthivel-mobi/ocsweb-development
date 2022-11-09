package com.mobi.ocs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "profoma")
public class Profoma {

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "ProfomaPath")
	private String profomapath;

	@Column(name = "QuotationId")
	private int quotationid;

	@Column(name = "ProfomaName")
	private String ProfomaName;

	public String getProfomaName() {
		return ProfomaName;
	}

	public void setProfomaName(String profomaName) {
		ProfomaName = profomaName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProfomapath() {
		return profomapath;
	}

	public void setProfomapath(String profomapath) {
		this.profomapath = profomapath;
	}

	public int getQuotationid() {
		return quotationid;
	}

	public void setQuotationid(int quotationid) {
		this.quotationid = quotationid;
	}

}
