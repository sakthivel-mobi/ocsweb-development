package com.mobi.ocs.modal;

public class QuotationBasicInfoResponseData {

	private String quotationID;
	private String discountPrice;
	private String subTotalPrice;
	private String discountReason;

	public QuotationBasicInfoResponseData() {

	}

	public String getSubTotalPrice() {
		return subTotalPrice;
	}

	public void setSubTotalPrice(String subTotalPrice) {
		this.subTotalPrice = subTotalPrice;
	}

	public String getQuotationID() {
		return quotationID;
	}

	public void setQuotationID(String quotationID) {
		this.quotationID = quotationID;
	}

	public String getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getDiscountReason() {
		return discountReason;
	}

	public void setDiscountReason(String discountReason) {
		this.discountReason = discountReason;
	}

	@Override
	public String toString() {
		return "QuotationBasicInfoResponseData [quotationID=" + quotationID + ", discountPrice=" + discountPrice
				+ ", subTotalPrice=" + subTotalPrice + ", discountReason=" + discountReason + "]";
	}

}
