package com.mobi.ocs.dao;

import java.util.ArrayList;
import java.util.List;

import com.mobi.ocs.entity.Order;
import com.mobi.ocs.entity.Quotation;
import com.mobi.ocs.entity.QuotationEzySplitMDRRate;
import com.mobi.ocs.entity.QuotationMDRRate;
import com.mobi.ocs.modal.AcceptQuotationRequestData;
import com.mobi.ocs.modal.MerchantOrderMobileResponseData;

public interface MerchantDAO {

	public List<Quotation> getQuotations(String username);

	public Object getOrderLineFromQuotationByQuotationId(String quotationId);

	public Object acceptQuotation(String quotationId, AcceptQuotationRequestData acceptQuotationRequestData);

	Object acceptWelcomeLetter(String quotationId, AcceptQuotationRequestData acceptQuotationRequestData);

	ArrayList<MerchantOrderMobileResponseData> getQuotationMobile(String username);

	public Object getQuotationByQuotationId(String quotationId);

	Quotation getOrderLineByQuotationId(String quotationId);

	public List<QuotationMDRRate> getMDRRates(String quotationId);

	public List<QuotationEzySplitMDRRate> getEzySplitMDRRates(String quotationId);

	public ArrayList<Order> getMerchantOrders(String sUserName);

}
