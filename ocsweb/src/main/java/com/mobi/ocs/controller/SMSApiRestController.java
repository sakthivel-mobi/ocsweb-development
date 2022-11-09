package com.mobi.ocs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mobi.ocs.modal.SendOTPRequestDataModal;
import com.mobi.ocs.modal.ValidateOTPRequestDataModal;
import com.mobi.ocs.service.SMSService;
import com.mobi.ocs.utilities.Constants;

@RestController
@RequestMapping("sms")
public class SMSApiRestController {

	@Autowired
	SMSService smsService;

	@RequestMapping(path = "/send/otp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object sendOTP(@RequestBody SendOTPRequestDataModal sendOTPRequestDataModal) {
		String otpRandomNumber = Constants.GenerateNumber();
		String messageContent = Constants.getOTPMessageContent(sendOTPRequestDataModal.getMobile(), otpRandomNumber);
		Object respone = smsService.sendSMS(sendOTPRequestDataModal.getMobile(), messageContent, otpRandomNumber, sendOTPRequestDataModal.getType() );
		return respone;
	}

	@RequestMapping(path = "/validate/otp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object validateOTP(@RequestBody ValidateOTPRequestDataModal requestData) {
		Object response = smsService.validateOTP(requestData);
		return response;
	}

}
