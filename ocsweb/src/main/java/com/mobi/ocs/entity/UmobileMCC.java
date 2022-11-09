package com.mobi.ocs.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="umobileMCC")
public class UmobileMCC {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "createdOn")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime createdOn;
		
	@Column(name = "tcc")
	private int tcc;
	
	@Column(name = "merchantGroupIndicator")
	private int merchantGroupIndicator;
	
	@Column(name = "corpReportCategory")
	private int corpReportCategory;
	
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "value")
	private String value;
	
	@Column(name = "active")
	private int active;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}



	public int getTcc() {
		return tcc;
	}

	public void setTcc(int tcc) {
		this.tcc = tcc;
	}

	public int getMerchantGroupIndicator() {
		return merchantGroupIndicator;
	}

	public void setMerchantGroupIndicator(int merchantGroupIndicator) {
		this.merchantGroupIndicator = merchantGroupIndicator;
	}

	public int getCorpReportCategory() {
		return corpReportCategory;
	}

	public void setCorpReportCategory(int corpReportCategory) {
		this.corpReportCategory = corpReportCategory;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "umobileMCC [id=" + id + ", name=" + name + ", value=" + value + ", active=" + active + "]";
	}
	
	
}