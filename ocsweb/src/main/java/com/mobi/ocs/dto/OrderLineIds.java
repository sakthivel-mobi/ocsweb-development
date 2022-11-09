package com.mobi.ocs.dto;

import java.util.List;

public class OrderLineIds {
	 private List<String> orderLineIds;

	public List<String> getOrderLineIds() {
		return orderLineIds;
	}

	public void setOrderLineIds(List<String> orderLineIds) {
		this.orderLineIds = orderLineIds;
	}

	@Override
	public String toString() {
		return "OrderLineIds [orderLineIds=" + orderLineIds + "]";
	}
	 
	 

}
