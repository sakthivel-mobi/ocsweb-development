package com.mobi.ocs.controller;

import com.mobi.ocs.dto.CommonResponseData;
import com.mobi.ocs.dto.EmailNotes;
import com.mobi.ocs.entity.Director;
import com.mobi.ocs.entity.Order;
import com.mobi.ocs.entity.OrderNotes;
import com.mobi.ocs.entity.UmobileCountry;
import com.mobi.ocs.modal.OrderDirectorResponseData;
import com.mobi.ocs.service.QuotationService;
import com.mobi.ocs.utilities.EmailTemplateNotes;
import com.mobi.ocs.utilities.smtp.SmtpEmail;
import com.mobi.ocs.utilities.smtp.SmtpEmailClient;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {

	@Autowired
	private QuotationService quotationService;

	protected static Logger logger = Logger.getLogger(QuotationController.class);

	@RequestMapping(path = "/order/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getOrderList() {
		List<Order> orders = quotationService.getOrders();
		if (orders == null) {
			return new CommonResponseData("0001", "Unable to get orders", null);

		} else {
			return new CommonResponseData("0000", "Success", orders);
		}
	}

	@RequestMapping(path = "order/{orderId}/stage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getStageMovement(@PathVariable(name = "orderId") String orderId) {
		Object response = quotationService.getStageMovement(orderId);
		return response;
	}

	@RequestMapping(path = "order/{orderId}/notes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getOrderNotes(@PathVariable(name = "orderId") String orderId) {
		Object response = quotationService.getOrderNotes(orderId);
		return response;
	}

	@RequestMapping(path = "order/{orderId}/director", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getOrderDirector(@PathVariable(name = "orderId") String orderId) {
		Object response = quotationService.getOrderDirector(orderId);
		return response;
	}

	@RequestMapping(path = "order/{orderId}/director", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object updateOrderDirector(@PathVariable(name = "orderId") String orderId,
			@RequestBody OrderDirectorResponseData requestData) {
		Object response = quotationService.updateOrderDirector(orderId, requestData);
		return response;
	}

	@RequestMapping(path = "order/{orderId}/director/{directorId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object deleteDirector(@PathVariable(name = "orderId") String orderId,
			@PathVariable(name = "directorId") String directorId) {
		Object response = quotationService.deleteDirector(orderId, directorId);
		return response;
	}

	@RequestMapping(path = "order/{orderId}/notes", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object SaveNotes(@RequestBody OrderNotes notes, @PathVariable(name = "orderId") String orderId) {

		try {

			logger.info("Saving Notes for Order ID : " + orderId);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			EmailNotes params = new EmailNotes();
			params.setOrderId(orderId);
			params.setSender(auth.getName());
			params.setRegarding(notes.getSubject());
			params.setNotes(notes.getNotes());
			params.setCompanyName(notes.getCompanyName());



//			String fromAddress = "Info@gomobi.io";
//			// String fromAddress = notes.getFromUser();
//			String toAddress = notes.getNotifyTo();
//			String ccAddress = "";
//			String subject = "Message for Order ID : " + orderId;

			String emailBody = EmailTemplateNotes.Template(params);
			String toAddress = notes.getNotifyTo();
			String ccAddress = null;
			String bccAddress = null;
			String textBody = "";
			String subject = "Message for Order ID : " + orderId;

			quotationService.SaveNotes(notes, orderId, auth.getName());
			SmtpEmail send = new SmtpEmail(subject, toAddress, ccAddress, bccAddress, null, textBody, emailBody);

			logger.info("This is the object we passed into the api" + send);
			SmtpEmailClient smtp = new SmtpEmailClient();

			logger.info("Sending to Api");
			smtp.sendMessage(send);
//			quotationService.SendEmail(fromAddress, toAddress, ccAddress, subject, "", "", emailBody, null);
			return new CommonResponseData("0000", "Email has been sent", null);

		} catch (Exception e) {
			logger.error("Error In Saving Notes : ", e);
			return new CommonResponseData("0001", "Unable to add notes", null);
		}

	}

}
