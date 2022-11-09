package com.mobi.ocs.modal;


public class OrderNotesResponseData {
    
    private int id;
    private String sendDate, from , notifyTo, subject, notes;
    public OrderNotesResponseData() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSendDate() {
        return sendDate;
    }
    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getNotifyTo() {
        return notifyTo;
    }
    public void setNotifyTo(String notifyTo) {
        this.notifyTo = notifyTo;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    @Override
    public String toString() {
        return "OrderNotesResponseData [from=" + from + ", id=" + id + ", notes=" + notes + ", notifyTo=" + notifyTo
                + ", sendDate=" + sendDate + ", subject=" + subject + "]";
    }
    
}
