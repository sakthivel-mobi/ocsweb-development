package com.mobi.ocs.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.mobi.ocs.dao.MessagingDAO;
import com.mobi.ocs.dto.CommonResponseData;
import com.mobi.ocs.entity.OTPLog;
import com.mobi.ocs.entity.Quotation;
import com.mobi.ocs.modal.OTPEncryptedData;
import com.mobi.ocs.modal.ValidateOTPRequestDataModal;
import com.mobi.ocs.utilities.Constants;
import com.mobi.ocs.utilities.Encryptor;
import com.mobi.ocs.utilities.smtp.SmtpEmail;
import com.mobi.ocs.utilities.smtp.SmtpEmailClient;

@Service
public class SMSServiceImpl implements SMSService {

	private final String smsServiceLink = "http://ic1.silverstreet.com/send.php";

	private static final Logger logger = Logger.getLogger(SMSServiceImpl.class);

	/**
	 * Generic sendSMS service for sending general purpose SMS
	 * 
	 * @param MSISDN  Mobile phone number, starts with 01
	 * @param content Max size of an SMS content is 153 characters.
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */

	@Autowired
	private MessagingDAO messagingDAO;

	@Autowired
	private QuotationService quotationService;

	@Autowired
	private UserDetailsManager userDetailsManager;

	@Override
	public Object sendSMS(final String MSISDN, final String content, String otpRandomNumber, String type) {

		logger.info("MSISDN --> " + parsePhoneNumber(MSISDN));
		logger.info(content);

		try {
			URL url = new URL(smsServiceLink);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			Map<String, String> parameters = new HashMap<>();
			parameters.put("body", content);
			parameters.put("destination", parsePhoneNumber(MSISDN));
			parameters.put("password", "mobivers");
			parameters.put("username", "mobiversa");
			parameters.put("sender", "Mobiversa");
			parameters.put("validity", "5");
			parameters.put("reference", String.valueOf(new Date().getTime()));

			con.setDoOutput(true);
			DataOutputStream out = new DataOutputStream(con.getOutputStream());
			out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
			out.flush();
			out.close();

			int responseCode = con.getResponseCode();

			logger.info("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			logger.info("Response Data : " + response.toString());

			OTPEncryptedData encryptorResponse = Encryptor.encrypt(otpRandomNumber, MSISDN);
			String node1 = encryptorResponse.getEncryptedText();

			String fromAddress = "Info@gomobi.io";
			Quotation quotation = quotationService.getQuotationByUserName(MSISDN);

			logger.info("Params that need to pass into api");

			String toAddress = quotation.getContact().getEmail();
			String emailBody = content;
			String subject = "Mobi Login OTP";
			String ccAddress = null;
			String bccAddress = null;
			String filename = null;
			String html = null;

			if (quotation != null) {
				SmtpEmail send = new SmtpEmail(subject, toAddress, ccAddress, bccAddress, filename, emailBody, html);
				SmtpEmailClient smtp = new SmtpEmailClient();
				smtp.sendMessage(send);
//				quotationService.SendEmail(fromAddress, quotation.getContact().getEmail(), "", "Mobi Login OTP", "", "",
//						content, null);
			}

			if (responseCode == 200) {
				OTPLog otpLog = new OTPLog(MSISDN, "01", node1);
				int result = messagingDAO.AddOTPLog(otpLog);

				if (result == 1) {
					return new CommonResponseData("0000", Constants.getMessageContent(MSISDN), null);
				} else {
					return new CommonResponseData("0001", "Something went wrong please try again.", null);
				}
			} else {
				logger.warn("Unable to send OTP, please check your mobile number.");
				return new CommonResponseData("0001", "Unable to send OTP, please check your mobile number", null);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Stacktrace : " + e);
			return new CommonResponseData("0001", "Something went wrong please try again.", null);
		}

	}

	private String parsePhoneNumber(String recipientMobile) {
		recipientMobile = recipientMobile.replaceAll("[+ -]", "");
		if (recipientMobile.startsWith("0")) {
			// prefix with malaysia country code
			recipientMobile = "6" + recipientMobile;
		} else {
			recipientMobile = "60" + recipientMobile;
		}
		return recipientMobile.trim();
	}

	@Override
	public Object validateOTP(ValidateOTPRequestDataModal requestData) {

		OTPLog otpLog = messagingDAO.GetLastOTP(requestData.getMobile());
		OTPEncryptedData encrypString = Encryptor.encrypt(requestData.getOtp(), requestData.getMobile());

		if (otpLog.getNode1().equals(encrypString.getEncryptedText())) {
			logger.info("Password Matched");
			return new CommonResponseData("0000", "OTP Verified", null);
		} else {
			logger.info("Password Not Matched");
			return new CommonResponseData("0001", "Something went wrong, please try again", null);
		}

	}

}
