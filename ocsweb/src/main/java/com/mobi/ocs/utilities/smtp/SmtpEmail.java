package com.mobi.ocs.utilities.smtp;


public class SmtpEmail {

	private String subject;
	private String msgto;
	private String cc;
	private String bcc;
	private String attachment;
	private String textbody;
	private String bodyHtml;

	public SmtpEmail(String subject, String msgto, String cc, String bcc, String attachment, String textbody,
			String bodyHtml) {
		super();
		this.subject = subject;
		this.msgto = msgto;
		this.cc = cc;
		this.bcc = bcc;
		this.attachment = attachment;
		this.textbody = textbody;
		this.bodyHtml = bodyHtml;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMsgto() {
		return msgto;
	}

	public void setMsgto(String msgto) {
		this.msgto = msgto;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getTextbody() {
		return textbody;
	}

	public void setTextbody(String textbody) {
		this.textbody = textbody;
	}

	public String getBodyHtml() {
		return bodyHtml;
	}

	public void setBodyHtml(String bodyHtml) {
		this.bodyHtml = bodyHtml;
	}

}
