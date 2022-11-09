package com.mobi.ocs.service;

import com.mobi.ocs.modal.ValidateOTPRequestDataModal;

public interface SMSService {

	Object sendSMS(final String MSISDN, final String content, String otpRandomNumber, String type);

	Object validateOTP(ValidateOTPRequestDataModal requestData);
}
