package com.mobi.ocs.entity.dotnetOcs;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "director_dotnetData")
public class Director_dotnet {

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name = "UserAppl")
	private String UserAppl;
	
	@Column(name = "DirName")
	private String DirName;
	
	@Column(name = "DirContact")
	private String DirContact;
	
	@Column(name = "DirNationality")
	private String DirNationality;
	
	@Column(name = "DirDesign")
	private String DirDesign;
	
	@Column(name = "DirAddress")
	private String DirAddress;
	
	@Column(name = "DirNric")
	private String DirNric;
	
	@Column(name = "DirIDType")
	private String DirIDType;
	
	@Column(name = "OrderId")
	private String OrderId;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getUserAppl() {
		return UserAppl;
	}

	public void setUserAppl(String userAppl) {
		UserAppl = userAppl;
	}

	public String getDirName() {
		return DirName;
	}

	public void setDirName(String dirName) {
		DirName = dirName;
	}

	public String getDirContact() {
		return DirContact;
	}

	public void setDirContact(String dirContact) {
		DirContact = dirContact;
	}

	public String getDirNationality() {
		return DirNationality;
	}

	public void setDirNationality(String dirNationality) {
		DirNationality = dirNationality;
	}

	public String getDirDesign() {
		return DirDesign;
	}

	public void setDirDesign(String dirDesign) {
		DirDesign = dirDesign;
	}

	public String getDirAddress() {
		return DirAddress;
	}

	public void setDirAddress(String dirAddress) {
		DirAddress = dirAddress;
	}

	public String getDirNric() {
		return DirNric;
	}

	public void setDirNric(String dirNric) {
		DirNric = dirNric;
	}

	public String getDirIDType() {
		return DirIDType;
	}

	public void setDirIDType(String dirIDType) {
		DirIDType = dirIDType;
	}

	public String getOrderId() {
		return OrderId;
	}

	public void setOrderId(String orderId) {
		OrderId = orderId;
	}

	@Override
	public String toString() {
		return "Director_dotnet [Id=" + Id + ", UserAppl=" + UserAppl + ", DirName=" + DirName + ", DirContact="
				+ DirContact + ", DirNationality=" + DirNationality + ", DirDesign=" + DirDesign + ", DirAddress="
				+ DirAddress + ", DirNric=" + DirNric + ", DirIDType=" + DirIDType + ", OrderId=" + OrderId + "]";
	}
	
	
	
	
	
}
