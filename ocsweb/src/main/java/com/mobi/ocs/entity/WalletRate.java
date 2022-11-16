package com.mobi.ocs.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "WalletRate")
public class WalletRate {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "createdOn")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date createdOn;

	@Column(name = "name")
	private String name;

	@Column(name = "productType")
	private String productType;

	// Boost Grab FPX
	@Column(name = "boostMDREcomm")
	private double boostMDREcomm;

	@Column(name = "boostMDRQR")
	private double boostMDRQR;

	@Column(name = "grabMDREcomm")
	private double grabMDREcomm;

	@Column(name = "grabMDRQR")
	private double grabMDRQR;

	// rk added

	@Column(name = "tngMDREcomm", columnDefinition = "double default 0")
	private double tngMDREcomm;

	@Column(name = "tngMDRQR", columnDefinition = "double default 0")
	private double tngMDRQR;

	@Column(name = "shopeepayMDREcomm", columnDefinition = "double default 0")
	private double shopeepayMDREcomm;

	@Column(name = "shopeepayMDRQR", columnDefinition = "double default 0")
	private double shopeepayMDRQR;

	@Column(name = "tngSettlement", columnDefinition = "int default 0")
	private int tngSettlement;

	@Column(name = "shopeepaySettlement", columnDefinition = "int default 0")
	private int shopeepaySettlement;

	@Column(name = "FPXMDR_RM")
	private double fPXMDR_RM;

	@Column(name = "FPXMDR_Percent")
	private double fPXMDR_Percent;

	// Settlement Periods

	@Column(name = "BoostSettlement")
	private int boostSettlement;

	@Column(name = "GrabSettlement")
	private int grabSettlement;

	@Column(name = "fpxSettlement")
	private int fpxSettlement;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public double getBoostMDREcomm() {
		return boostMDREcomm;
	}

	public void setBoostMDREcomm(double boostMDREcomm) {
		this.boostMDREcomm = boostMDREcomm;
	}

	public double getBoostMDRQR() {
		return boostMDRQR;
	}

	public void setBoostMDRQR(double boostMDRQR) {
		this.boostMDRQR = boostMDRQR;
	}

	public double getGrabMDREcomm() {
		return grabMDREcomm;
	}

	public void setGrabMDREcomm(double grabMDREcomm) {
		this.grabMDREcomm = grabMDREcomm;
	}

	public double getGrabMDRQR() {
		return grabMDRQR;
	}

	public void setGrabMDRQR(double grabMDRQR) {
		this.grabMDRQR = grabMDRQR;
	}

	public double getTngMDREcomm() {
		return tngMDREcomm;
	}

	public void setTngMDREcomm(double tngMDREcomm) {
		this.tngMDREcomm = tngMDREcomm;
	}

	public double getTngMDRQR() {
		return tngMDRQR;
	}

	public void setTngMDRQR(double tngMDRQR) {
		this.tngMDRQR = tngMDRQR;
	}

	public double getShopeepayMDREcomm() {
		return shopeepayMDREcomm;
	}

	public void setShopeepayMDREcomm(double shopeepayMDREcomm) {
		this.shopeepayMDREcomm = shopeepayMDREcomm;
	}

	public double getShopeepayMDRQR() {
		return shopeepayMDRQR;
	}

	public void setShopeepayMDRQR(double shopeepayMDRQR) {
		this.shopeepayMDRQR = shopeepayMDRQR;
	}

	public int getTngSettlement() {
		return tngSettlement;
	}

	public void setTngSettlement(int tngSettlement) {
		this.tngSettlement = tngSettlement;
	}

	public int getShopeepaySettlement() {
		return shopeepaySettlement;
	}

	public void setShopeepaySettlement(int shopeepaySettlement) {
		this.shopeepaySettlement = shopeepaySettlement;
	}

	public double getfPXMDR_RM() {
		return fPXMDR_RM;
	}

	public void setfPXMDR_RM(double fPXMDR_RM) {
		this.fPXMDR_RM = fPXMDR_RM;
	}

	public double getfPXMDR_Percent() {
		return fPXMDR_Percent;
	}

	public void setfPXMDR_Percent(double fPXMDR_Percent) {
		this.fPXMDR_Percent = fPXMDR_Percent;
	}

	public int getBoostSettlement() {
		return boostSettlement;
	}

	public void setBoostSettlement(int boostSettlement) {
		this.boostSettlement = boostSettlement;
	}

	public int getGrabSettlement() {
		return grabSettlement;
	}

	public void setGrabSettlement(int grabSettlement) {
		this.grabSettlement = grabSettlement;
	}

	public int getFpxSettlement() {
		return fpxSettlement;
	}

	public void setFpxSettlement(int fpxSettlement) {
		this.fpxSettlement = fpxSettlement;
	}

	@Override
	public String toString() {
		return "WalletRate [id=" + id + ", createdOn=" + createdOn + ", name=" + name + ", productType=" + productType
				+ ", boostMDREcomm=" + boostMDREcomm + ", boostMDRQR=" + boostMDRQR + ", grabMDREcomm=" + grabMDREcomm
				+ ", grabMDRQR=" + grabMDRQR + ", tngMDREcomm=" + tngMDREcomm + ", tngMDRQR=" + tngMDRQR
				+ ", shopeepayMDREcomm=" + shopeepayMDREcomm + ", shopeepayMDRQR=" + shopeepayMDRQR + ", tngSettlement="
				+ tngSettlement + ", shopeepaySettlement=" + shopeepaySettlement + ", fPXMDR_RM=" + fPXMDR_RM
				+ ", fPXMDR_Percent=" + fPXMDR_Percent + ", boostSettlement=" + boostSettlement + ", grabSettlement="
				+ grabSettlement + ", fpxSettlement=" + fpxSettlement + "]";
	}

}
