package com.mobi.ocs.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "HostRate")
public class HostRate {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "createdOn")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime createdOn;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "hostType")
	private String hostType;
	
	@Column(name = "productType")
	private String productType;
	
	@Column(name = "refId")
	private String refId;
	
	// Debit Host
		@Column(name = "loc_Debit_Host_Visa")
		private double loc_Debit_Host_Visa;

		@Column(name = "loc_Debit_Host_Master")
		private double loc_Debit_Host_Master;

		@Column(name = "loc_Debit_Host_Union")
		private double loc_Debit_Host_Union;

		@Column(name = "for_Debit_Host_Visa")
		private double for_Debit_Host_Visa;

		@Column(name = "for_Debit_Host_Master")
		private double for_Debit_Host_Master;

		@Column(name = "for_Debit_Hosth_Union")
		private double for_Debit_Host_Union;

		// Credit Host
		@Column(name = "loc_Credit_Host_Visa")
		private double loc_Credit_Host_Visa;

		@Column(name = "loc_Credit_Host_Master")
		private double loc_Credit_Host_Master;

		@Column(name = "loc_Credit_Host_Union")
		private double loc_Credit_Host_Union;

		@Column(name = "for_Credit_Host_Visa")
		private double for_Credit_Host_Visa;

		@Column(name = "for_Credit_Host_Master")
		private double for_Credit_Host_Master;

		@Column(name = "for_Credit_Host_Union")
		private double for_Credit_Host_Union;
		
		//EZYSPLIT Host Rate 
		//Only Local Credit MasterCard  for EZYSPLIT , Rest 0%
		
		@Column(name = "loc_Credit_Host_Master_EZYSPLIT",columnDefinition = "double default 0.00")
		private double loc_Credit_Host_Master_EZYSPLIT;

		
		public double getLoc_Debit_Host_Visa() {
			return loc_Debit_Host_Visa;
		}

		public void setLoc_Debit_Host_Visa(double loc_Debit_Host_Visa) {
			this.loc_Debit_Host_Visa = loc_Debit_Host_Visa;
		}

		public double getLoc_Debit_Host_Master() {
			return loc_Debit_Host_Master;
		}

		public void setLoc_Debit_Host_Master(double loc_Debit_Host_Master) {
			this.loc_Debit_Host_Master = loc_Debit_Host_Master;
		}

		public double getLoc_Debit_Host_Union() {
			return loc_Debit_Host_Union;
		}

		public void setLoc_Debit_Host_Union(double loc_Debit_Host_Union) {
			this.loc_Debit_Host_Union = loc_Debit_Host_Union;
		}

		public double getFor_Debit_Host_Visa() {
			return for_Debit_Host_Visa;
		}

		public void setFor_Debit_Host_Visa(double for_Debit_Host_Visa) {
			this.for_Debit_Host_Visa = for_Debit_Host_Visa;
		}

		public double getFor_Debit_Host_Master() {
			return for_Debit_Host_Master;
		}

		public void setFor_Debit_Host_Master(double for_Debit_Host_Master) {
			this.for_Debit_Host_Master = for_Debit_Host_Master;
		}

		public double getFor_Debit_Host_Union() {
			return for_Debit_Host_Union;
		}

		public void setFor_Debit_Host_Union(double for_Debit_Host_Union) {
			this.for_Debit_Host_Union = for_Debit_Host_Union;
		}

		public double getLoc_Credit_Host_Visa() {
			return loc_Credit_Host_Visa;
		}

		public void setLoc_Credit_Host_Visa(double loc_Credit_Host_Visa) {
			this.loc_Credit_Host_Visa = loc_Credit_Host_Visa;
		}

		public double getLoc_Credit_Host_Master() {
			return loc_Credit_Host_Master;
		}

		public void setLoc_Credit_Host_Master(double loc_Credit_Host_Master) {
			this.loc_Credit_Host_Master = loc_Credit_Host_Master;
		}

		public double getLoc_Credit_Host_Union() {
			return loc_Credit_Host_Union;
		}

		public void setLoc_Credit_Host_Union(double loc_Credit_Host_Union) {
			this.loc_Credit_Host_Union = loc_Credit_Host_Union;
		}

		public double getFor_Credit_Host_Visa() {
			return for_Credit_Host_Visa;
		}

		public void setFor_Credit_Host_Visa(double for_Credit_Host_Visa) {
			this.for_Credit_Host_Visa = for_Credit_Host_Visa;
		}

		public double getFor_Credit_Host_Master() {
			return for_Credit_Host_Master;
		}

		public void setFor_Credit_Host_Master(double for_Credit_Host_Master) {
			this.for_Credit_Host_Master = for_Credit_Host_Master;
		}

		public double getFor_Credit_Host_Union() {
			return for_Credit_Host_Union;
		}

		public void setFor_Credit_Host_Union(double for_Credit_Host_Union) {
			this.for_Credit_Host_Union = for_Credit_Host_Union;
		}

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

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getHostType() {
			return hostType;
		}

		public void setHostType(String hostType) {
			this.hostType = hostType;
		}

		public String getProductType() {
			return productType;
		}

		public void setProductType(String productType) {
			this.productType = productType;
		}
		

		public double getLoc_Credit_Host_Master_EZYSPLIT() {
			return loc_Credit_Host_Master_EZYSPLIT;
		}

		public void setLoc_Credit_Host_Master_EZYSPLIT(double loc_Credit_Host_Master_EZYSPLIT) {
			this.loc_Credit_Host_Master_EZYSPLIT = loc_Credit_Host_Master_EZYSPLIT;
		}
		
		

		public String getRefId() {
			return refId;
		}

		public void setRefId(String refId) {
			this.refId = refId;
		}


		@Override
		public String toString() {
			return "HostRate [id=" + id + ", createdOn=" + createdOn + ", name=" + name + ", hostType=" + hostType
					+ ", productType=" + productType + ", loc_Debit_Host_Visa=" + loc_Debit_Host_Visa
					+ ", loc_Debit_Host_Master=" + loc_Debit_Host_Master + ", loc_Debit_Host_Union="
					+ loc_Debit_Host_Union + ", for_Debit_Host_Visa=" + for_Debit_Host_Visa + ", for_Debit_Host_Master="
					+ for_Debit_Host_Master + ", for_Debit_Host_Union=" + for_Debit_Host_Union
					+ ", loc_Credit_Host_Visa=" + loc_Credit_Host_Visa + ", loc_Credit_Host_Master="
					+ loc_Credit_Host_Master + ", loc_Credit_Host_Union=" + loc_Credit_Host_Union
					+ ", for_Credit_Host_Visa=" + for_Credit_Host_Visa + ", for_Credit_Host_Master="
					+ for_Credit_Host_Master + ", for_Credit_Host_Union=" + for_Credit_Host_Union
					+ ", loc_Credit_Host_Master_EZYSPLIT=" + loc_Credit_Host_Master_EZYSPLIT + "]";
		}


}
