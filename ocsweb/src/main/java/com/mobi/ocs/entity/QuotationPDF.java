package com.mobi.ocs.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "quotationpdf")
public class QuotationPDF {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateAt;

	private String documentPath;
	private String documentName;
	private int quotationId;

	public QuotationPDF() {

	}

	public QuotationPDF(String documentPath, String documentName, int quotationId) {
		this.documentPath = documentPath;
		this.documentName = documentName;
		this.quotationId = quotationId;
	}

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public int getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(int quotationId) {
		this.quotationId = quotationId;
	}

	@Override
	public String toString() {
		return "QuotationPDF [id=" + id + ", createdAt=" + createdAt + ", updateAt=" + updateAt + ", documentPath="
				+ documentPath + ", documentName=" + documentName + ", quotationId=" + quotationId + "]";
	}

}
