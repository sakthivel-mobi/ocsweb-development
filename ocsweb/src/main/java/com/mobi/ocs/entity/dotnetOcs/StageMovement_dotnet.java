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
@Table(name = "stageMovement_dotnetData")
public class StageMovement_dotnet {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	
	@Column(name = "MerchantID")
	private String MerchantID;
	
	@Column(name = "UserId")
	private String UserId;
	
	@Column(name = "RoleId")
	private String RoleId;
	
	@Column(name = "Status")
	private String Status;
	
	@Column(name = "Date")
	private String Date;
	
	@Column(name = "orderid")
	private String orderid;
	
	@Column(name = "PendingWith")
	private String PendingWith;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getMerchantID() {
		return MerchantID;
	}

	public void setMerchantID(String merchantID) {
		MerchantID = merchantID;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getRoleId() {
		return RoleId;
	}

	public void setRoleId(String roleId) {
		RoleId = roleId;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getPendingWith() {
		return PendingWith;
	}

	public void setPendingWith(String pendingWith) {
		PendingWith = pendingWith;
	}

	@Override
	public String toString() {
		return "StageMovement_dotnet [ID=" + ID + ", MerchantID=" + MerchantID + ", UserId=" + UserId + ", RoleId="
				+ RoleId + ", Status=" + Status + ", Date=" + Date + ", orderid=" + orderid + ", PendingWith="
				+ PendingWith + "]";
	}
	

	
	
	
}
