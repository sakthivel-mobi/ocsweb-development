package com.mobi.ocs.modal;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

import com.mobi.ocs.entity.MerchantRegistration;
import com.mobi.ocs.entity.Order;

public class MerchantRegistrationResponse {
	private List<Order> order;
	private List<MerchantRegistration> merchantRegistration;

	public MerchantRegistrationResponse() {
		super();
		order = new ArrayList<Order>();
		merchantRegistration = new ArrayList<MerchantRegistration>();
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public List<MerchantRegistration> getMerchantRegistration() {
		return merchantRegistration;
	}

	public void setMerchantRegistration(List<MerchantRegistration> merchantRegistration) {
		this.merchantRegistration = merchantRegistration;
	}

	@Override
	public String toString() {
		return "MerchantRegistrationListResponseData [order=" + order + ", merchantRegistration=" + merchantRegistration
				+ "]";
	}
}
