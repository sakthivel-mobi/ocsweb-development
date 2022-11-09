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
import javax.persistence.Transient;

@Entity
@Table(name = "Document_dotnetData")
public class Document_dotnet {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;

	@Column(name = "UPLOAD_DATE")
	private String UPLOAD_DATE;

	@Column(name = "UPLOADED_BY")
	private String UPLOADED_BY;

	@Column(name = "IP_ADDRESS")
	private String IP_ADDRESS;

	@Column(name = "USERNAME")
	private String USERNAME;

	@Column(name = "ORDER_ID")
	private String ORDER_ID;

	@Column(name = "FILE_TYPE")
	private String FILE_TYPE;

	@Column(name = "FILE_NAME")
	private String FILE_NAME;

	@Column(name = "STORE_FILENAME")
	private String STORE_FILENAME;

	@Column(name = "MIME_TYPE")
	private String MIME_TYPE;

	@Column(name = "FILE_SIZE")
	private String FILE_SIZE;

	@Column(name = "FILE_CATEGORY")
	private String FILE_CATEGORY;

	@Column(name = "ACTIVE")
	private String ACTIVE;
	
	private String userName;

	@Transient
	private String fileType;
	
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getUPLOAD_DATE() {
		return UPLOAD_DATE;
	}

	public void setUPLOAD_DATE(String uPLOAD_DATE) {
		UPLOAD_DATE = uPLOAD_DATE;
	}

	public String getUPLOADED_BY() {
		return UPLOADED_BY;
	}

	public void setUPLOADED_BY(String uPLOADED_BY) {
		UPLOADED_BY = uPLOADED_BY;
	}

	public String getIP_ADDRESS() {
		return IP_ADDRESS;
	}

	public void setIP_ADDRESS(String iP_ADDRESS) {
		IP_ADDRESS = iP_ADDRESS;
	}

	public String getUSERNAME() {
		return USERNAME;
	}

	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}

	public String getORDER_ID() {
		return ORDER_ID;
	}

	public void setORDER_ID(String oRDER_ID) {
		ORDER_ID = oRDER_ID;
	}

	public String getFILE_TYPE() {
		return FILE_TYPE;
	}

	public void setFILE_TYPE(String fILE_TYPE) {
		FILE_TYPE = fILE_TYPE;
	}

	public String getFILE_NAME() {
		return FILE_NAME;
	}

	public void setFILE_NAME(String fILE_NAME) {
		FILE_NAME = fILE_NAME;
	}

	public String getSTORE_FILENAME() {
		return STORE_FILENAME;
	}

	public void setSTORE_FILENAME(String sTORE_FILENAME) {
		STORE_FILENAME = sTORE_FILENAME;
	}

	public String getMIME_TYPE() {
		return MIME_TYPE;
	}

	public void setMIME_TYPE(String mIME_TYPE) {
		MIME_TYPE = mIME_TYPE;
	}

	public String getFILE_SIZE() {
		return FILE_SIZE;
	}

	public void setFILE_SIZE(String fILE_SIZE) {
		FILE_SIZE = fILE_SIZE;
	}

	public String getFILE_CATEGORY() {
		return FILE_CATEGORY;
	}

	public void setFILE_CATEGORY(String fILE_CATEGORY) {
		FILE_CATEGORY = fILE_CATEGORY;
	}

	public String getACTIVE() {
		return ACTIVE;
	}

	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}

	@Override
	public String toString() {
		return "Document_dotnet [ID=" + ID + ", UPLOAD_DATE=" + UPLOAD_DATE + ", UPLOADED_BY=" + UPLOADED_BY
				+ ", IP_ADDRESS=" + IP_ADDRESS + ", USERNAME=" + USERNAME + ", ORDER_ID=" + ORDER_ID + ", FILE_TYPE="
				+ FILE_TYPE + ", FILE_NAME=" + FILE_NAME + ", STORE_FILENAME=" + STORE_FILENAME + ", MIME_TYPE="
				+ MIME_TYPE + ", FILE_SIZE=" + FILE_SIZE + ", FILE_CATEGORY=" + FILE_CATEGORY + ", ACTIVE=" + ACTIVE
				+ "]";
	}

}
