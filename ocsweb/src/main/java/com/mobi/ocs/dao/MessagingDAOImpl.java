package com.mobi.ocs.dao;

import java.util.Date;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mobi.ocs.entity.OTPLog;

@Repository
public class MessagingDAOImpl implements MessagingDAO {

	@Autowired
	private SessionFactory sessionFactory;
	protected static Logger logger = Logger.getLogger(MessagingDAOImpl.class);
	@Override
	@Transactional
	public int AddOTPLog(OTPLog otpLog) {
		try {
			Session session = sessionFactory.getCurrentSession();
			OTPLog response = (OTPLog) session.createQuery("FROM OTPLog WHERE mobile = :mobile")
					.setParameter("mobile", otpLog.getMobile())
					.uniqueResult();
			
			if (response == null) {
				response = new OTPLog();
				response.setMobile(otpLog.getMobile());
				response.setResponseData(otpLog.getResponseData());
			}
			
			response.setNode1(otpLog.getNode1());
			response.setCreatedAt(new Date());
			session.saveOrUpdate(response);
			
			return 1;
		} catch (Exception e) {
			logger.error("Error In AddOTPLog : ", e);
			return -1;
		}
	}

	@Override
	@Transactional
	public OTPLog GetLastOTP(String mobile) {
		try {
			Session session = sessionFactory.getCurrentSession();
			OTPLog response = (OTPLog) session.createQuery("FROM OTPLog WHERE mobile = :mobile")
					.setParameter("mobile", mobile)
					.uniqueResult();
			
			return response;
		} catch (Exception e) {
			logger.error("Error In GetLastOTP : ", e);
			return null;
		}
	}

}
