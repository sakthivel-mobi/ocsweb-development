package com.mobi.ocs.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "CALLBACKREQUEST")
public class Callback {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "date")
	private Date date;

	@Column(name = "businessName")
	private String businessName;

	@Column(name = "email")
	private String email;

	@Column(name = "mobileNumber")
	private String mobileNumber;

	@Column(name = "poc")
	private String poc;

	@Column(name = "isContacted", columnDefinition = "tinyint(1) default 0")
	private boolean contactedFlag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public boolean isContactedFlag() {
		return contactedFlag;
	}

	public void setContactedFlag(boolean contactedFlag) {
		this.contactedFlag = contactedFlag;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPoc() {
		return poc;
	}

	public void setPoc(String poc) {
		this.poc = poc;
	}

	@Override
	public String toString() {
		return "Callback [id=" + id + ", date=" + date + ", businessName=" + businessName + ", email=" + email
				+ ", mobileNumber=" + mobileNumber + ", poc=" + poc + ", isContacted=" + contactedFlag + "]";
	}

}
