package com.mobi.ocs.modal;

import java.util.Arrays;

public class TPADocumentDownloadRequestData {
	
	private String[] orderIds;

	public TPADocumentDownloadRequestData(String[] orderIds) {
		this.orderIds = orderIds;
	}

	public TPADocumentDownloadRequestData() {

	}

	public String[] getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(String[] orderIds) {
		this.orderIds = orderIds;
	}

	@Override
	public String toString() {
		return "TPADocumentDownloadRequestData [orderIds=" + Arrays.toString(orderIds) + "]";
	}
	
	
	

}
