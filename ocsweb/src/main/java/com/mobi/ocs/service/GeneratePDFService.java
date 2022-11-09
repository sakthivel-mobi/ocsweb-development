package com.mobi.ocs.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.text.DocumentException;
import com.mobi.ocs.entity.Quotation;
import com.mobi.ocs.modal.QuotationRequestData;

public interface GeneratePDFService {
	Object GenerateQuotationMobi(Quotation quotation, HttpServletRequest req)
			throws FileNotFoundException, DocumentException, MalformedURLException, IOException;

	Object IssueQuotationByQuotationId(QuotationRequestData quotationRequestData, Quotation quotation,
			HttpServletRequest req);
	
	void generatedAcceptedQuotation(QuotationRequestData quotationRequestData, Quotation quotation,
			HttpServletRequest req);

	Object getAvailableQuotationById(String quotationId);

	Object acceptQuotation(String quotationId);

	String GenerateWL(String orderId) throws DocumentException, IOException;

	Object GenerateMMA(String orderId) throws DocumentException, IOException;

	Object GenerateProfoma(Quotation quote, HttpServletRequest req)
			throws DocumentException, MalformedURLException, IOException;

	public Object IssueProfomaByQuotationId(QuotationRequestData quotationRequestData, Quotation quotation,
			HttpServletRequest req);

}
