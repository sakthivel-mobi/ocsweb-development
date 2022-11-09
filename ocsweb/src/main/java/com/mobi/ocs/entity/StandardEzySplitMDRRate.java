package com.mobi.ocs.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "StandardEzySplitMDRRate")
public class StandardEzySplitMDRRate {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "createdOn")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime createdOn;

	// Instalment 3 __ Local Credit - Merchant,Customer,Mobi

	@Column(name = "loc_Credit_Merch_Master_Insta3")
	private double loc_Credit_Merch_Master_Insta3;

	@Column(name = "loc_Credit_Cus_Master_Insta3")
	private double loc_Credit_Cus_Master_Insta3;

	@Column(name = "loc_Credit_Host_Master_Insta3")
	private double loc_Credit_Host_Master_Insta3;

	@Column(name = "loc_Credit_Mobi_Master_Insta3")
	private double loc_Credit_Mobi_Master_Insta3;

	// Instalment 6 __ Local Credit - Merchant,Customer,Mobi

	@Column(name = "loc_Credit_Merch_Master_Insta6")
	private double loc_Credit_Merch_Master_Insta6;

	@Column(name = "loc_Credit_Cus_Master_Insta6")
	private double loc_Credit_Cus_Master_Insta6;

	@Column(name = "loc_Credit_Host_Master_Insta6")
	private double loc_Credit_Host_Master_Insta6;

	@Column(name = "loc_Credit_Mobi_Master_Insta6")
	private double loc_Credit_Mobi_Master_Insta6;

	// Instalment 9 __ Local Credit - Merchant,Customer,Mobi

	@Column(name = "loc_Credit_Merch_Master_Insta9")
	private double loc_Credit_Merch_Master_Insta9;

	@Column(name = "loc_Credit_Cus_Master_Insta9")
	private double loc_Credit_Cus_Master_Insta9;

	@Column(name = "loc_Credit_Host_Master_Insta9")
	private double loc_Credit_Host_Master_Insta9;

	@Column(name = "loc_Credit_Mobi_Master_Insta9")
	private double loc_Credit_Mobi_Master_Insta9;

	// Instalment 12 __ Local Credit - Merchant,Customer,Mobi

	@Column(name = "loc_Credit_Merch_Master_Insta12")
	private double loc_Credit_Merch_Master_Insta12;

	@Column(name = "loc_Credit_Cus_Master_Insta12")
	private double loc_Credit_Cus_Master_Insta12;

	@Column(name = "loc_Credit_Host_Master_Insta12")
	private double loc_Credit_Host_Master_Insta12;

	@Column(name = "loc_Credit_Mobi_Master_Insta12")
	private double loc_Credit_Mobi_Master_Insta12;

	// Boost Grab FPX
	@Column(name = "boostMDREcomm", columnDefinition = "double default 0")
	private double boostMDREcomm;

	@Column(name = "boostMDRQR", columnDefinition = "double default 0")
	private double boostMDRQR;

	@Column(name = "grabMDREcomm", columnDefinition = "double default 0")
	private double grabMDREcomm;

	@Column(name = "grabMDRQR", columnDefinition = "double default 0")
	private double grabMDRQR;

	@Column(name = "FPXMDR_RM", columnDefinition = "double default 0")
	private double fPXMDR_RM;

		@Column(name = "FPXMDR_Percent", columnDefinition = "double default 0")
		private double fPXMDR_Percent;
		
		
		//rk added
		
		@Column(name = "tngMDREcomm",columnDefinition = "double default 0")
		private double tngMDREcomm;
		
		
		@Column(name = "tngMDRQR",columnDefinition = "double default 0")
		private double tngMDRQR;
		
		
		@Column(name = "shopeepayMDREcomm",columnDefinition = "double default 0")
		private double shopeepayMDREcomm;
		
		
		@Column(name = "shopeepayMDRQR",columnDefinition = "double default 0")
		private double shopeepayMDRQR;
		
		
		@Column(name = "tngSettlement",columnDefinition = "int default 0")
		private int tngSettlement;
		
		
		@Column(name = "shopeepaySettlement",columnDefinition = "int default 0")
		private int shopeepaySettlement;
		
		//rk added
	// Settlement Periods

	@Column(name = "productSettlement")
	private int productSettlement;

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
		shopeepayMDREcomm = shopeepayMDREcomm;
	}

	public double getShopeepayMDRQR() {
		return shopeepayMDRQR;
	}

	public void setShopeepayMDRQR(double shopeepayMDRQR) {
		shopeepayMDRQR = shopeepayMDRQR;
	}

	public int getTngSettlement() {
		return tngSettlement;
	}

	public void setTngSettlement(int tngSettlement) {
		tngSettlement = tngSettlement;
	}

	public int getShopeepaySettlement() {
		return shopeepaySettlement;
	}

	public void setShopeepaySettlement(int shopeepaySettlement) {
		shopeepaySettlement = shopeepaySettlement;
	}

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

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public double getLoc_Credit_Merch_Master_Insta3() {
		return loc_Credit_Merch_Master_Insta3;
	}

	public void setLoc_Credit_Merch_Master_Insta3(double loc_Credit_Merch_Master_Insta3) {
		this.loc_Credit_Merch_Master_Insta3 = loc_Credit_Merch_Master_Insta3;
	}

	public double getLoc_Credit_Cus_Master_Insta3() {
		return loc_Credit_Cus_Master_Insta3;
	}

	public void setLoc_Credit_Cus_Master_Insta3(double loc_Credit_Cus_Master_Insta3) {
		this.loc_Credit_Cus_Master_Insta3 = loc_Credit_Cus_Master_Insta3;
	}

	public double getLoc_Credit_Host_Master_Insta3() {
		return loc_Credit_Host_Master_Insta3;
	}

	public void setLoc_Credit_Host_Master_Insta3(double loc_Credit_Host_Master_Insta3) {
		this.loc_Credit_Host_Master_Insta3 = loc_Credit_Host_Master_Insta3;
	}

	public double getLoc_Credit_Mobi_Master_Insta3() {
		return loc_Credit_Mobi_Master_Insta3;
	}

	public void setLoc_Credit_Mobi_Master_Insta3(double loc_Credit_Mobi_Master_Insta3) {
		this.loc_Credit_Mobi_Master_Insta3 = loc_Credit_Mobi_Master_Insta3;
	}

	public double getLoc_Credit_Merch_Master_Insta6() {
		return loc_Credit_Merch_Master_Insta6;
	}

	public void setLoc_Credit_Merch_Master_Insta6(double loc_Credit_Merch_Master_Insta6) {
		this.loc_Credit_Merch_Master_Insta6 = loc_Credit_Merch_Master_Insta6;
	}

	public double getLoc_Credit_Cus_Master_Insta6() {
		return loc_Credit_Cus_Master_Insta6;
	}

	public void setLoc_Credit_Cus_Master_Insta6(double loc_Credit_Cus_Master_Insta6) {
		this.loc_Credit_Cus_Master_Insta6 = loc_Credit_Cus_Master_Insta6;
	}

	public double getLoc_Credit_Host_Master_Insta6() {
		return loc_Credit_Host_Master_Insta6;
	}

	public void setLoc_Credit_Host_Master_Insta6(double loc_Credit_Host_Master_Insta6) {
		this.loc_Credit_Host_Master_Insta6 = loc_Credit_Host_Master_Insta6;
	}

	public double getLoc_Credit_Mobi_Master_Insta6() {
		return loc_Credit_Mobi_Master_Insta6;
	}

	public void setLoc_Credit_Mobi_Master_Insta6(double loc_Credit_Mobi_Master_Insta6) {
		this.loc_Credit_Mobi_Master_Insta6 = loc_Credit_Mobi_Master_Insta6;
	}

	public double getLoc_Credit_Merch_Master_Insta9() {
		return loc_Credit_Merch_Master_Insta9;
	}

	public void setLoc_Credit_Merch_Master_Insta9(double loc_Credit_Merch_Master_Insta9) {
		this.loc_Credit_Merch_Master_Insta9 = loc_Credit_Merch_Master_Insta9;
	}

	public double getLoc_Credit_Cus_Master_Insta9() {
		return loc_Credit_Cus_Master_Insta9;
	}

	public void setLoc_Credit_Cus_Master_Insta9(double loc_Credit_Cus_Master_Insta9) {
		this.loc_Credit_Cus_Master_Insta9 = loc_Credit_Cus_Master_Insta9;
	}

	public double getLoc_Credit_Host_Master_Insta9() {
		return loc_Credit_Host_Master_Insta9;
	}

	public void setLoc_Credit_Host_Master_Insta9(double loc_Credit_Host_Master_Insta9) {
		this.loc_Credit_Host_Master_Insta9 = loc_Credit_Host_Master_Insta9;
	}

	public double getLoc_Credit_Mobi_Master_Insta9() {
		return loc_Credit_Mobi_Master_Insta9;
	}

	public void setLoc_Credit_Mobi_Master_Insta9(double loc_Credit_Mobi_Master_Insta9) {
		this.loc_Credit_Mobi_Master_Insta9 = loc_Credit_Mobi_Master_Insta9;
	}

	public double getLoc_Credit_Merch_Master_Insta12() {
		return loc_Credit_Merch_Master_Insta12;
	}

	public void setLoc_Credit_Merch_Master_Insta12(double loc_Credit_Merch_Master_Insta12) {
		this.loc_Credit_Merch_Master_Insta12 = loc_Credit_Merch_Master_Insta12;
	}

	public double getLoc_Credit_Cus_Master_Insta12() {
		return loc_Credit_Cus_Master_Insta12;
	}

	public void setLoc_Credit_Cus_Master_Insta12(double loc_Credit_Cus_Master_Insta12) {
		this.loc_Credit_Cus_Master_Insta12 = loc_Credit_Cus_Master_Insta12;
	}

	public double getLoc_Credit_Host_Master_Insta12() {
		return loc_Credit_Host_Master_Insta12;
	}

	public void setLoc_Credit_Host_Master_Insta12(double loc_Credit_Host_Master_Insta12) {
		this.loc_Credit_Host_Master_Insta12 = loc_Credit_Host_Master_Insta12;
	}

	public double getLoc_Credit_Mobi_Master_Insta12() {
		return loc_Credit_Mobi_Master_Insta12;
	}

	public void setLoc_Credit_Mobi_Master_Insta12(double loc_Credit_Mobi_Master_Insta12) {
		this.loc_Credit_Mobi_Master_Insta12 = loc_Credit_Mobi_Master_Insta12;
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

	public int getProductSettlement() {
		return productSettlement;
	}

	public void setProductSettlement(int productSettlement) {
		this.productSettlement = productSettlement;
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
		return "StandardEzySplitMDRRate [id=" + id + ", createdOn=" + createdOn + ", loc_Credit_Merch_Master_Insta3="
				+ loc_Credit_Merch_Master_Insta3 + ", loc_Credit_Cus_Master_Insta3=" + loc_Credit_Cus_Master_Insta3
				+ ", loc_Credit_Host_Master_Insta3=" + loc_Credit_Host_Master_Insta3
				+ ", loc_Credit_Mobi_Master_Insta3=" + loc_Credit_Mobi_Master_Insta3
				+ ", loc_Credit_Merch_Master_Insta6=" + loc_Credit_Merch_Master_Insta6
				+ ", loc_Credit_Cus_Master_Insta6=" + loc_Credit_Cus_Master_Insta6 + ", loc_Credit_Host_Master_Insta6="
				+ loc_Credit_Host_Master_Insta6 + ", loc_Credit_Mobi_Master_Insta6=" + loc_Credit_Mobi_Master_Insta6
				+ ", loc_Credit_Merch_Master_Insta9=" + loc_Credit_Merch_Master_Insta9
				+ ", loc_Credit_Cus_Master_Insta9=" + loc_Credit_Cus_Master_Insta9 + ", loc_Credit_Host_Master_Insta9="
				+ loc_Credit_Host_Master_Insta9 + ", loc_Credit_Mobi_Master_Insta9=" + loc_Credit_Mobi_Master_Insta9
				+ ", loc_Credit_Merch_Master_Insta12=" + loc_Credit_Merch_Master_Insta12
				+ ", loc_Credit_Cus_Master_Insta12=" + loc_Credit_Cus_Master_Insta12
				+ ", loc_Credit_Host_Master_Insta12=" + loc_Credit_Host_Master_Insta12
				+ ", loc_Credit_Mobi_Master_Insta12=" + loc_Credit_Mobi_Master_Insta12 + "]";
	}

}
