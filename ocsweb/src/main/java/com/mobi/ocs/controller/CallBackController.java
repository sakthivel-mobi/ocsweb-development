package com.mobi.ocs.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.text.DocumentException;
import com.mobi.ocs.dto.CommonResponseData;
import com.mobi.ocs.entity.Callback;
import com.mobi.ocs.service.QuotationService;

@Controller
@RequestMapping("/CallBack")
public class CallBackController {

	@Autowired
	private QuotationService quotationService;
	protected static Logger logger = Logger.getLogger(CallBackController.class);

	@RequestMapping("/list")
	public String CallBackList(Model model) {

		try {

			logger.info("Inside CallBack List Controller");
			List<Callback> callbackRequests = new ArrayList<Callback>();

			callbackRequests = quotationService.GetCallBackRequests();
			model.addAttribute("callbackRequests", callbackRequests);

		} catch (Exception e) {
			logger.error("Failed to Get CallBackList - ", e);
		}

		return "CallBackRequestList";
	}

	@PostMapping(path = "/saveCallBackRequest", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object SaveCallBackRequest(@RequestBody Callback callbackRequest)
			throws DocumentException, MalformedURLException, IOException {

		logger.info("Callback Request Received : " + callbackRequest.toString());
		Date now = new Date();
		callbackRequest.setDate(now);
		quotationService.SaveCallbackRequest(callbackRequest);
		return new CommonResponseData("0000", "CallBack Request Saved", null);

	}

	@PostMapping(path = "/markCallbackContacted", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object MarkCallbackContacted(int callbackId)
			throws DocumentException, MalformedURLException, IOException {

		logger.info("To Mark Contacted For the CallBack Request ID : " + callbackId);
		quotationService.MarkCallbackContacted(callbackId);
		return new CommonResponseData("0000", "CallBack Request Marked Contacted", null);

	}

}
