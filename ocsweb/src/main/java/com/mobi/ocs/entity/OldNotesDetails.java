package com.mobi.ocs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notesdetails")
public class OldNotesDetails {
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name = "UserAppl")
	private String UserAppl;
	@Column(name = "ToMail")
	private String ToMail;
	@Column(name = "From")
	private String From;
	@Column(name = "Subject")
	private String Subject;
	@Column(name = "senddate")
	private String senddate;
	@Column(name = "Attachment")
	private String Attachment;
	@Column(name = "MailBody")
	private String MailBody;
	@Column(name = "OrderId")
	private String OrderId;

	public OldNotesDetails() {
	}

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

	public String getToMail() {
		return ToMail;
	}

	public void setToMail(String toMail) {
		ToMail = toMail;
	}

	public String getFrom() {
		return From;
	}

	public void setFrom(String from) {
		From = from;
	}

	public String getSubject() {
		return Subject;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}

	public String getSenddate() {
		return senddate;
	}

	public void setSenddate(String senddate) {
		this.senddate = senddate;
	}

	public String getAttachment() {
		return Attachment;
	}

	public void setAttachment(String attachment) {
		Attachment = attachment;
	}

	public String getMailBody() {
		return MailBody;
	}

	public void setMailBody(String mailBody) {
		MailBody = mailBody;
	}

	public String getOrderId() {
		return OrderId;
	}

	public void setOrderId(String orderId) {
		OrderId = orderId;
	}

	@Override
	public String toString() {
		return "OldNotesDetails [Attachment=" + Attachment + ", From=" + From + ", Id=" + Id + ", MailBody=" + MailBody
				+ ", OrderId=" + OrderId + ", Subject=" + Subject + ", ToMail=" + ToMail + ", UserAppl=" + UserAppl
				+ ", senddate=" + senddate + "]";
	}

}
