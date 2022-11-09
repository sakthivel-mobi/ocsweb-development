package com.mobi.ocs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mobi.ocs.dto.CommonResponseData;
import com.mobi.ocs.entity.MerchantRegistration;
import com.mobi.ocs.entity.Order;
import com.mobi.ocs.modal.MerchantRegistrationListResponseData;
import com.mobi.ocs.service.MerchantRegistrationService;
import com.mobi.ocs.service.QuotationService;

@RestController
@RequestMapping(path = "/merchant/registration")
public class MerchantRegistrationRestController {

	@Autowired
	private MerchantRegistrationService regService;

	protected static Logger logger = Logger.getLogger(MerchantRegistrationRestController.class);

	@RequestMapping(path = "/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object GetMerchantRegistrationList() {
		try {

			System.out.println("Inside MerchantRegistration Controller");

			List<Order> pendingDeployementOrders = regService.GetPendingDeploymentOrders();
			List<MerchantRegistration> registrationOrders = regService.getRegistrationOrders();

			for (Order item : pendingDeployementOrders) {
				logger.info(item.toString());

			}
			for (MerchantRegistration reg : registrationOrders) {
				logger.info(reg.toString());
			}

			return "OK";

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("GetMerchantRegistrationList", e);
			return new CommonResponseData("0001", "Unable to get merchant order list", null);
		}
	}

	@RequestMapping(path = "/mobile/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object GetMerchantRegistrationListMobile() throws JsonMappingException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object response = regService.GetMerchantRegistrationListMobile(auth.getName());
		return response;
	}

	@RequestMapping(path = "/grabpay/report", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object GetGrabPayReport() {
		return regService.getGrapPayReport();
	}

	@RequestMapping(path = "/ewallet/mdr/sync", method = RequestMethod.POST)
	public Object submitEWalletMDR(@RequestParam("registrationID") String registrationID) {
		Object response = regService.submitEWalletMDR(registrationID);
		return response;
	}
	
	@RequestMapping(path = "/grabPay/mdr/sync", method = RequestMethod.POST)
	public Object submitGrabPayEWalletMDR(@RequestParam("registrationID") String registrationID, @RequestParam("grabPayAccquired") String grabPayAccquired) {
		MerchantRegistration merchantRegiistration = regService.getMerchantRegistrationByRegistrationId(Integer.valueOf(registrationID));
		merchantRegiistration.setGrabMidAcquired(grabPayAccquired);
		
		regService.UpdateMerchantRegistration(merchantRegiistration);
		
		Object response = regService.submitGrabPayEWalletMDR(registrationID);
		return response;
	}
}
