package com.mobi.ocs.modal;

public class DiscountPriceRequestData {

	private double discount;
	private String discountReason;

	public DiscountPriceRequestData() {

	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getDiscountReason() {
		return discountReason;
	}

	public void setDiscountReason(String discountReason) {
		this.discountReason = discountReason;
	}

	@Override
	public String toString() {
		return "DiscountPriceRequestData [discount=" + discount + ", discountReason=" + discountReason + "]";
	}

}
