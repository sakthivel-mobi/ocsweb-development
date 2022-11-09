package com.mobi.ocs.modal;

import java.util.ArrayList;
import java.util.List;

import com.mobi.ocs.dto.OrderLineIds;
import com.mobi.ocs.entity.OrderLines;
import com.mobi.ocs.entity.Quotation;
import com.mobi.ocs.entity.QuotationEzySplitMDRRate;
import com.mobi.ocs.entity.QuotationMDRRate;

public class MerchantQuotationDetailsResponseModel {

	private List<OrderLines> orderLines;
	private List<QuotationMDRRate> quotationMDRRate;
	private List<QuotationEzySplitMDRRate> quotationEzySplitMDRRate;
	private int quotationAccepted;

	public MerchantQuotationDetailsResponseModel() {

	}

	public List<OrderLines> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLines> orderLines) {
		this.orderLines = orderLines;
	}

	public List<QuotationMDRRate> getQuotationMDRRate() {
		return quotationMDRRate;
	}

	public void setQuotationMDRRate(List<QuotationMDRRate> quotationMDRRate) {
		this.quotationMDRRate = quotationMDRRate;
	}

	public List<QuotationEzySplitMDRRate> getQuotationEzySplitMDRRate() {
		return quotationEzySplitMDRRate;
	}

	public void setQuotationEzySplitMDRRate(List<QuotationEzySplitMDRRate> quotationEzySplitMDRRate) {
		this.quotationEzySplitMDRRate = quotationEzySplitMDRRate;
	}

	public int getQuotationAccepted() {
		return quotationAccepted;
	}

	public void setQuotationAccepted(int quotationAccepted) {
		this.quotationAccepted = quotationAccepted;
	}


}
