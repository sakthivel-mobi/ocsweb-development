package com.mobi.ocs.dto;

public class CommonResponseData {
    String responseStatus;
    String responseMessage;
    Object responseData;

    public CommonResponseData(String responseStatus, String responseMessage, Object responseData) {
        this.responseStatus = String.valueOf(responseStatus);
        this.responseMessage = responseMessage;
        this.responseData = responseData;
    }

    @Override
    public String toString() {
        return "CommonResponseData{" +
                "responseStatus='" + responseStatus + '\'' +
                ", responseMessage='" + responseMessage + '\'' +
                ", responseData=" + responseData +
                '}';
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }
}
