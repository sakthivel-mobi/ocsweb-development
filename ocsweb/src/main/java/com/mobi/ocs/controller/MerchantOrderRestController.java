package com.mobi.ocs.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.VoiceStatus;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.mobi.ocs.dao.MerchantDAO;
import com.mobi.ocs.dao.SignatureDAO;
import com.mobi.ocs.dto.CommonResponseData;
import com.mobi.ocs.entity.MerchantRegistration;
import com.mobi.ocs.entity.Order;
import com.mobi.ocs.entity.OrderLines;
import com.mobi.ocs.entity.Quotation;
import com.mobi.ocs.modal.AcceptQuotationRequestData;
import com.mobi.ocs.modal.MerchantCredentialsResponseData;
import com.mobi.ocs.modal.MerchantOrderListItemResponseData;
import com.mobi.ocs.modal.MerchantOrderMobileResponseData;
import com.mobi.ocs.modal.QuotationRequestData;
import com.mobi.ocs.service.GeneratePDFService;
import com.mobi.ocs.service.MerchantRegistrationService;
import com.mobi.ocs.service.QuotationService;

@RestController
@RequestMapping(path = "/merchant")
public class MerchantOrderRestController {
	Logger logger = Logger.getLogger(MerchantOrderController.class.getName());

	@Autowired
	MerchantDAO merchantDAO;

	@Autowired
	private SignatureDAO signatureDAO;

	@Autowired
	QuotationService quotationService;

	@Autowired
	GeneratePDFService generatePDFService;

	@Autowired
	MerchantRegistrationService merchantRegistrationService;

	@RequestMapping(path = "/quotation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getMerchantQuotationList() {

		try {
			logger.info("Inside MerchantOrder Controller");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			String sUserName = auth.getName().toString();
			ArrayList<MerchantOrderMobileResponseData> response = merchantDAO.getQuotationMobile(sUserName);

			return new CommonResponseData("0000", "Success", response);
		} catch (Exception e) {
			logger.info(e);
			return new CommonResponseData("0001", "Unable to get merchant quotation list", null);
		}
	}

	@RequestMapping(path = "/order", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getMerchantOrderList() {

		try {
			logger.info("Inside MerchantOrder Controller");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			String sUserName = auth.getName().toString();
			ArrayList<Order> order = (ArrayList<Order>) merchantDAO.getMerchantOrders(sUserName);

			ArrayList<MerchantOrderListItemResponseData> orderList = new ArrayList<MerchantOrderListItemResponseData>();

			for (Order item : order) {
				MerchantOrderListItemResponseData response = new MerchantOrderListItemResponseData();
				response.setId(String.valueOf(item.getId()));
				response.setCreatedAt(
						String.valueOf(item.getCreatedOn().format(DateTimeFormatter.ofPattern("dd MMM, yyyy"))));

				response.setQuotationId(String.valueOf(item.getQuotation().getId()));

				if (item.getQuotation().getOrderLines().size() > 0) {
					response.setProductName(item.getQuotation().getOrderLines().get(0).getProduct().getProductName());
				} else {
					response.setProductName("");
				}

				if (item.getMerchantRegistration() != null) {
					logger.info("getMerchantRegistration >> " + item.getMerchantRegistration().getIsSuccess());
				}

				if (item.getMerchantRegistration() != null)
					response.setEnableWelcomeLetter(item.getMerchantRegistration().getIsSuccess());
				else
					response.setEnableWelcomeLetter(false);

				orderList.add(response);
			}

			return new CommonResponseData("0000", "Success", orderList);

		} catch (

		Exception e) {
			logger.error(e);
			return new CommonResponseData("0001", "Unable to get merchant order list", null);
		}
	}

	@RequestMapping(path = "/credentials", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getMerchantCredentialsList() {
		try {
			logger.info("Inside MerchantOrder Controller");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			String sUserName = auth.getName().toString();
			ArrayList<Order> items = merchantDAO.getMerchantOrders(sUserName);

			logger.info(items.toString());

			ArrayList<MerchantCredentialsResponseData> response = new ArrayList<MerchantCredentialsResponseData>();

			for (Order order : items) {

				logger.info(String.valueOf(order.getId()));
				logger.info(order.getBusinessName());
				logger.info(order.getQuotation().getOrderType());
				logger.info(order.getCreatedOn());

				MerchantCredentialsResponseData data = new MerchantCredentialsResponseData();

				data.setOrderId(String.valueOf(order.getId()));
				data.setCompanyName(order.getBusinessName());
				data.setType(order.getQuotation().getOrderType());
				data.setQuotationId(String.valueOf(order.getQuotation().getId()));
				data.setDate(order.getCreatedOn().format(DateTimeFormatter.ISO_DATE));

				MerchantRegistration merchantRegistration = (MerchantRegistration) merchantRegistrationService
						.getMerchantRegistrationByOrderId(String.valueOf(order.getId()));

				if (merchantRegistration == null) {
					data.setShowCredentials(false);
				} else {
					logger.info(merchantRegistration.getIsSuccess());
					data.setShowCredentials(
							merchantRegistration.getIsSuccess() == null ? false : merchantRegistration.getIsSuccess());
				}

				response.add(data);
			}

			return new CommonResponseData("0000", "Success", response);

		} catch (Exception e) {
			e.printStackTrace();
			return new CommonResponseData("0001", "Unable to get the credentials", null);
		}

	}

	@RequestMapping(path = "/quotation/{quotationId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getOrderLineFromQuotationByQuotationId(@PathVariable(name = "quotationId") String quotationId) {
//		Object response = merchantDAO.getOrderLineFromQuotationByQuotationId(quotationId);	
		Object response = quotationService.getMerchantQuotationDetailsByQuotationId(quotationId);
		return response;
	}

	@RequestMapping(path = "/quotation/{quotationId}/accept", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object acceptQuotationById(@PathVariable(name = "quotationId") String quotationId,
			@RequestBody AcceptQuotationRequestData acceptQuotationRequestData, HttpServletRequest request)
			throws DocumentException, MalformedURLException, IOException {

		Object response = merchantDAO.acceptQuotation(quotationId, acceptQuotationRequestData);

		CommonResponseData commonResponse = (CommonResponseData) response;
		if (commonResponse.getResponseStatus() == "0000") {
			Quotation quotation = quotationService.getQuotationByID(Integer.parseInt(quotationId));
			generatePDFService.GenerateQuotationMobi(quotation, request);
			
//			Generate accepted quotation
			generatePDFService.generatedAcceptedQuotation(new QuotationRequestData(quotationId), quotation, request);
			
			
			quotationService.SendQuotationAcceptedEmail(quotationId);
			

		} else {
			logger.info("Failed to Send Quotation Acceptance Email for Quotation : " + quotationId
					+ ". Response Code : " + commonResponse.getResponseStatus());
		}

		return response;
	}

	@RequestMapping(path = "/wl/{quotationId}/download", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object downloadWelcomeLetter(@PathVariable(name = "quotationId") String quotationId) {
		System.out.println("downloadWelcomeLetter --> ");
//		Object responseData = generatePDFService.GenerateWL(quotationId);
		return "";
	}

	@RequestMapping(path = "/wl/{quotationId}/accept", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object acceptWelcomeLetter(@PathVariable(name = "quotationId") String quotationId,
			@RequestBody AcceptQuotationRequestData acceptQuotationRequestData) {
		

		Object responseObject = quotationService.AcceptWelcomeLetter(quotationId, acceptQuotationRequestData);
		
		merchantRegistrationService.UpdateSuccessStatusForRegistration(acceptQuotationRequestData.getOrderId());


		CommonResponseData response = (CommonResponseData) responseObject;
		if (response.getResponseStatus() == "0000") {
			quotationService.SendWelcomeLetterAcceptedEmail(quotationId);
		} else {
			logger.info("Failed to Send WelcomeLetter Acceptance Email for Quotation : " + quotationId
					+ ". Response Code : " + response.getResponseStatus());
		}

		return responseObject;
	}

	@RequestMapping(path = "/signature/{quotationId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getMerchantSignature(@PathVariable(name = "quotationId") String quotationId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object response = signatureDAO.getUserSignature(auth.getName());
		return response;
	}

}
