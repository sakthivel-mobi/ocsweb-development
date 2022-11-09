package com.mobi.ocs.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.text.pdf.AcroFields.Item;
import com.mobi.ocs.dao.MerchantDAO;
import com.mobi.ocs.entity.MerchantRegistration;
import com.mobi.ocs.entity.Order;
import com.mobi.ocs.entity.OrderLines;
import com.mobi.ocs.entity.Quotation;
import com.mobi.ocs.modal.MerchantOrderListItemResponseData;
import com.mobi.ocs.service.MerchantRegistrationService;
import com.mobi.ocs.service.QuotationService;

@Controller
public class MerchantOrderController {

	Logger logger = Logger.getLogger(MerchantOrderController.class.getName());

	@Autowired
	MerchantDAO merchantDAO;

	@Autowired
	QuotationService quotationService;

	@Autowired
	MerchantRegistrationService merchantRegistrationService;

	@GetMapping("/MerchantOrder")
	public String gotoMerchantView(Model model) {

		try {
			logger.info("Inside MerchantOrder Controller");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			String sUserName = auth.getName().toString();
			ArrayList<Quotation> quotations = (ArrayList<Quotation>) merchantDAO.getQuotations(sUserName);

			model.addAttribute("quotations", quotations);

		} catch (Exception e) {
			logger.info(e);
		}

		return "MerchantOrder";
	}

	@GetMapping("/MerchantOrder/Order")
	public String getMerchantOrders(Model model) {

		try {
			logger.info("Inside MerchantOrder Controller");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			String sUserName = auth.getName().toString();
			ArrayList<Order> items = merchantDAO.getMerchantOrders(sUserName);
			Collections.sort(items, (s1, s2) ->
		    Integer.max(s2.getId(), s1.getId()));		
			
			model.addAttribute("orders", items);

		} catch (Exception e) {
			logger.info(e);
		}

		return "MerchantOrderList";
	}

	@GetMapping("/MerchantOrder/Credential")
	public String gotoMerchantCredentials(Model model) {

		try {
			logger.info("Inside MerchantOrder Controller");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			String sUserName = auth.getName().toString();
			ArrayList<Order> items = merchantDAO.getMerchantOrders(sUserName);

			model.addAttribute("orders", items);

		} catch (Exception e) {
			logger.info(e);
		}

		return "CredentialsList";
	}

	@GetMapping("/MerchantOrder/Credential/{orderId}")
	public String gotoCredentials(@PathVariable(name = "orderId") String orderId, Model model,
			@RequestParam(name = "quotationId") String quotationId) {

		MerchantRegistration merchantRegistration = (MerchantRegistration) merchantRegistrationService
				.getMerchantRegistrationByOrderId(orderId);

		model.addAttribute("quotationId", quotationId);
		model.addAttribute("orderId", orderId);
		model.addAttribute("merchantRegistration", merchantRegistration);

		return "CredentialsView";
	}

	@GetMapping("/MerchantOrder/Quotation/{quotationId}")
	public String viewMerchantQuotation(Model model, @PathVariable(name = "quotationId") String quotationId) {

		try {
			logger.info("Inside MerchantOrder Controller");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			String sUserName = auth.getName().toString();
			Quotation quotations = merchantDAO.getOrderLineByQuotationId(quotationId);

			model.addAttribute("quotations", quotations);
			model.addAttribute("quotationId", quotationId);

			double subTotal = 0;

			for (OrderLines orderLines : quotations.getOrderLines()) {
				subTotal += orderLines.getProduct().getUnitPrice() * orderLines.getQuantity();
			}

			model.addAttribute("subTotal", String.format("%.02f", subTotal));
			model.addAttribute("discount", String.format("%.02f", quotations.getDiscountPrice()));

			double totalAmount = (subTotal - quotations.getDiscountPrice());
			model.addAttribute("totalAmount", String.format("%.02f", totalAmount));

		} catch (Exception e) {
			logger.info(e);
		}

		return "MerchantQuotationView";
	}

	@GetMapping("/WelcomeLetter")
	public String gotoWelcomeLetterView(@RequestParam(name = "quotationId") String quotationId, Model model) {

		try {

			logger.info("In WelcomeLetter Controller");

			Quotation quotationObject = quotationService.getQuotationByID(Integer.parseInt(quotationId));
			Order order = quotationService.GetOrderByQuotationId(Integer.parseInt(quotationId));

			String midString = "";
			String tidString = "";
			String dtlString = "";
			String apiKeyString = "";
			String activationCodeString = "";

			for (OrderLines line : quotationObject.getOrderLines()) {

				midString = midString + (line.getMid() == null ? "" : line.getMid()) + " ";
				tidString = tidString + (line.getTid() == null ? "" : line.getTid()) + " ";
				dtlString = dtlString + (line.getDtl() == null ? "" : line.getDtl()) + " ";
				apiKeyString = apiKeyString + (line.getApiKey() == null ? "" : line.getApiKey()) + " ";
				activationCodeString = activationCodeString
						+ (line.getActivationCode() == null ? "" : line.getActivationCode()) + " ";

				logger.info("MID Ternary - " + line.getMid() == null ? "" : line.getMid());
			}

			logger.info("MID String - " + midString.trim() == "null" ? "" : midString);

			model.addAttribute("quotationId", quotationId);
			model.addAttribute("orderId", order.getId());
			model.addAttribute("quotationDataSet", quotationObject);
			model.addAttribute("mid", midString.trim());
			model.addAttribute("tid", tidString.trim());
			model.addAttribute("dtl", dtlString.trim());
			model.addAttribute("apiKey", apiKeyString.trim());
			model.addAttribute("activationCode", activationCodeString.trim());

			model.addAttribute("bankName", order.getBankName());
			model.addAttribute("accountNo", order.getAccountNo());
			model.addAttribute("businessName", order.getBusinessName());

			if (order.getQuotation().getWelcomeLetterAccepted() == 1) {
				model.addAttribute("welcomeLetterAccepted", true);
			} else {
				model.addAttribute("welcomeLetterAccepted", false);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "WelcomeLetter";
	}

	@RequestMapping(path = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getOrderByOrderId(@PathVariable(name = "orderId") String orderId) {
		Object response = quotationService.GetOrderByID(orderId);
		return response;
	}

}
