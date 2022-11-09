package com.mobi.ocs.dao;

import org.springframework.stereotype.Repository;

import com.mobi.ocs.entity.OTPLog;


public interface MessagingDAO {

	public int AddOTPLog(OTPLog otpLog);

	public OTPLog GetLastOTP(String mobile);

}
