package com.mobi.ocs.controller;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.NullCipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;
import org.bouncycastle.crypto.util.CipherFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.acls.model.ObjectIdentityRetrievalStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.mobi.ocs.dto.CommonResponseData;
import com.mobi.ocs.modal.ForgotPasswordRequestData;
import com.mobi.ocs.modal.OTPDataSetData;
import com.mobi.ocs.modal.ValidateOTPRequestData;
import com.mobi.ocs.service.SMSService;
import com.mobi.ocs.service.UserServices;
import com.mobi.ocs.utilities.Constants;

import lombok.val;

@RestController
@RequestMapping("/resetPassword")
public class ForgotPasswordRestController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsManager userdetailsManager;

	@Autowired
	SMSService smsService;

	@Autowired
	private UserServices userServices;

	protected static Logger logger = Logger.getLogger(SignatureRestController.class);

	@RequestMapping(path = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object resetPassword(@RequestBody ForgotPasswordRequestData forgotPasswordRequestData) {

		try {
			forgotPasswordRequestData
					.setNewPassword(passwordEncoder.encode(forgotPasswordRequestData.getNewPassword()));
			int responseData = userServices.ResetPassword(forgotPasswordRequestData.getUserName(),
					forgotPasswordRequestData.getNewPassword());

			if (responseData != -1) {
				return new CommonResponseData("0000", "Password changed successfully", null);
			} else {
				return new CommonResponseData("0001", "Unable to change password, please check the details", null);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new CommonResponseData("0001", "Something went wrong, please try again", null);
		}
	}

	@RequestMapping(path = "/request/otp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object requestResetOTP(@RequestBody ValidateOTPRequestData validateOTPRequestData) {

		try {
			logger.info("requestResetOTP >> "+validateOTPRequestData.getMobile());
			Boolean isExist = userdetailsManager.userExists(validateOTPRequestData.getMobile());
			logger.info("requestResetOTP >> " + isExist);

			if (isExist) {
				String otpRandomNumber = Constants.GenerateNumber();
				String messageContent = Constants.getOTPMessageContent(validateOTPRequestData.getMobile(),
						otpRandomNumber);
				Object respone = smsService.sendSMS(validateOTPRequestData.getMobile(), messageContent, otpRandomNumber,
						"0");
				return respone;
			} else {
				return new CommonResponseData("0001", "No details available for this mobile number", null);
			}

		} catch (Exception e) {
			logger.error("requestResetOTP >> ", e);
			return new CommonResponseData("0001", "unable to send OTP please try again", null);
		}
	}

}
