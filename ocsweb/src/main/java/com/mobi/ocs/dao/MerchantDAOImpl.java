package com.mobi.ocs.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mobi.ocs.controller.QuotationController;
import com.mobi.ocs.dto.CommonResponseData;
import com.mobi.ocs.entity.Order;
import com.mobi.ocs.entity.Quotation;
import com.mobi.ocs.entity.QuotationAcceptance;
import com.mobi.ocs.entity.QuotationEzySplitMDRRate;
import com.mobi.ocs.entity.QuotationMDRRate;
import com.mobi.ocs.entity.Signature;
import com.mobi.ocs.entity.WelcomeLetterAcceptance;
import com.mobi.ocs.modal.AcceptQuotationRequestData;
import com.mobi.ocs.modal.MerchantOrderMobileResponseData;
import com.mobi.ocs.service.QuotationService;

@Repository
public class MerchantDAOImpl implements MerchantDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private QuotationService quotationService;

	protected static Logger logger = Logger.getLogger(MerchantDAOImpl.class);

	@Transactional
	public ArrayList<Quotation> getQuotations(String username) {
		try {

			Session session = sessionFactory.getCurrentSession();
			Query<Quotation> query = session.createQuery("FROM Quotation WHERE userId = :userName", Quotation.class)
					.setParameter("userName", username);
			ArrayList<Quotation> orders = (ArrayList<Quotation>) query.getResultList();

			logger.info("getOrders ->  " + orders.size());

			return orders;
		} catch (Exception e) {
			logger.error("Failed to get Quotations - ", e);
			return new ArrayList<Quotation>();
		}
	}

	@Transactional
	@Override
	public Object getOrderLineFromQuotationByQuotationId(String quotationId) {

		Quotation result = (Quotation) getQuotationByQuotationId(Integer.parseInt(quotationId));
		if (result != null) {
			return new CommonResponseData("0000", "Success", result.getOrderLines());
		} else {
			return new CommonResponseData("0001", "No records found", null);
		}
	}

	@Transactional
	public Quotation getQuotationByQuotationId(int quotationId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Quotation> query = session.createQuery("FROM Quotation WHERE id = :quotationId", Quotation.class)
					.setParameter("quotationId", quotationId);
			Quotation result = query.uniqueResult();
			return result;
		} catch (Exception e) {
			logger.error("Failed to get Quotation By QuotationID - ", e);
			return null;
		}
	}

	@Transactional
	public Signature getSignatureByQuotationId(String quotationId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Signature> query = session
					.createQuery("FROM Signature WHERE quotationId = :quotationId", Signature.class)
					.setParameter("quotationId", Integer.parseInt(quotationId));
			Signature result = query.uniqueResult();

			return result;
		} catch (Exception e) {
			logger.error("Failed to get Signature By QuotationID - ", e);
			return null;
		}
	}

	@Override
	@Transactional
	public Object acceptQuotation(String quotationId, AcceptQuotationRequestData acceptQuotationRequestData) {

		try {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Session session = sessionFactory.getCurrentSession();

			Signature signature = getSignatureByUserID(auth.getName());

			if (signature != null) {

				QuotationAcceptance quotationAcceptance = new QuotationAcceptance();

				quotationAcceptance.setIpAddress(acceptQuotationRequestData.getIpAddress());
				quotationAcceptance.setIcNumber(acceptQuotationRequestData.getIcNumber());
				quotationAcceptance.setNameAsPerIC(acceptQuotationRequestData.getNamePerIc());
				quotationAcceptance.setCreatedOn(LocalDateTime.now());
				quotationAcceptance.setSignature(signature);

				/*
				 * QuotationAcceptance quotationAcceptance = new
				 * QuotationAcceptance(LocalDateTime.now(),
				 * acceptQuotationRequestData.getIpAddress(),
				 * acceptQuotationRequestData.getNamePerIc(),
				 * acceptQuotationRequestData.getIcNumber(), String.valueOf(signature.getId()));
				 */
//				quotationAcceptance.setIpAddress(acceptQuotationRequestData.getIpAddress());
//				quotationAcceptance.setIcNumber(acceptQuotationRequestData.getIcNumber());
//				quotationAcceptance.setNameAsPerIC(acceptQuotationRequestData.getNamePerIc());
//				quotationAcceptance.setCreatedOn(LocalDateTime.now());
//				quotationAcceptance.setSignatureData(String.valueOf(signature.getId()));

				// Quotation quotation =
				// getQuotationByQuotationId(Integer.parseInt(quotationId));

				// quotation.setQuotationAcceptance(quotationAcceptanceID);

				int quotationAcceptanceID = (int) session.save(quotationAcceptance);
				logger.info("quotationAcceptanceID >> " + quotationAcceptanceID);
				String response = quotationService.UpdateQuotationAcceptanceIDInQuotation(quotationAcceptanceID,
						quotationId);

				if (response.equals("Success")) {
					return new CommonResponseData("0000", "Success", quotationAcceptanceID);
				} else {
					return new CommonResponseData("0001", "Something went wrong, please try again", null);
				}

			} else {
				return new CommonResponseData("0001", "No Signature avaibale, please add signature to this quotation",
						null);
			}

		} catch (Exception e) {
			logger.error("Failed to Accept Quotation - ", e);
			return new CommonResponseData("0001", "Something went wrong, please try again", null);
		}

	}

	@Override
	@Transactional
	public Object acceptWelcomeLetter(String quotationId, AcceptQuotationRequestData acceptQuotationRequestData) {

		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			Session session = sessionFactory.getCurrentSession();
			Signature signature = getSignatureByUserID(auth.getName());

			if (signature != null) {

				WelcomeLetterAcceptance welcomeLetterAcceptance = new WelcomeLetterAcceptance();

				welcomeLetterAcceptance.setIpAddress(acceptQuotationRequestData.getIpAddress());
				welcomeLetterAcceptance.setIcNumber(acceptQuotationRequestData.getIcNumber());
				welcomeLetterAcceptance.setNameAsPerIC(acceptQuotationRequestData.getNamePerIc());
				welcomeLetterAcceptance.setCreatedOn(LocalDateTime.now());
				welcomeLetterAcceptance.setSignature(signature);

				int welcomeLetterAcceptanceId = (int) session.save(welcomeLetterAcceptance);

				String response = quotationService.UpdateWelcomeLetterAcceptanceIDInQuotation(welcomeLetterAcceptanceId,
						quotationId);

				if (response.equals("Success")) {
					logger.info("Merchant Successfully Accepted the Welcome Letter. Quotation ID - " + quotationId);
					return new CommonResponseData("0000", "Welcome Acceptance Completed", welcomeLetterAcceptanceId);
				} else {
					logger.info("Merchant unable to Accept the Welcome Letter. Quotation ID - " + quotationId);
					return new CommonResponseData("0001", "Something went wrong, please try again", null);
				}

			} else {
				logger.info("No Signature available for Merchant to Accept the Welcome Letter. Quotation ID - "
						+ quotationId);
				return new CommonResponseData("0001", "No Signature available, please add signature to this quotation",
						null);
			}

		} catch (Exception e) {
			logger.error("Merchant unable to Accept the Welcome Letter. Quotation ID - " + quotationId + " - " + e);
			return new CommonResponseData("0001", "Something went wrong, please try again", null);
		}

	}

	public Signature getSignatureByUserID(String userId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Signature> query = session.createQuery("FROM Signature WHERE username = :userId", Signature.class)
					.setParameter("userId", userId);
			Signature result = query.uniqueResult();

			return result;
		} catch (Exception e) {
			logger.error("Failed to get Signature By userid - ", e);
			return null;
		}
	}

	@Transactional
	@Override
	public ArrayList<MerchantOrderMobileResponseData> getQuotationMobile(String username) {
		try {

			Session session = sessionFactory.getCurrentSession();
			Query<Quotation> query = session.createQuery("FROM Quotation WHERE userId = :userName", Quotation.class)
					.setParameter("userName", username);
			ArrayList<Quotation> orders = (ArrayList<Quotation>) query.getResultList();

			ArrayList<MerchantOrderMobileResponseData> arrayList = new ArrayList<MerchantOrderMobileResponseData>();
			for (Quotation quotation : orders) {
				MerchantOrderMobileResponseData item = new MerchantOrderMobileResponseData();
				item.setId(String.valueOf(quotation.getId()));

				item.setCreatedOn(quotation.getCreatedOn().format(DateTimeFormatter.ofPattern("dd MMM, yyyy")));

				if (quotation.getOrderLines().size() > 0) {
					String prodcutName = quotation.getOrderLines().get(0).getProduct().getProductName();
					item.setProductName(prodcutName);
				} else {
					item.setProductName("");
				}
				arrayList.add(item);
			}

			logger.info("getOrders ->  " + orders.size());

			return arrayList;
		} catch (Exception e) {
			logger.error("Failed to get Quotations - ", e);
			return new ArrayList<MerchantOrderMobileResponseData>();
		}
	}

	@Transactional
	@Override
	public Object getQuotationByQuotationId(String quotationId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Quotation> query = session.createQuery("FROM Quotation WHERE id = :quotationId", Quotation.class)
					.setParameter("quotationId", quotationId);
			Quotation result = query.uniqueResult();

			return new CommonResponseData("0000", "Success", result);
		} catch (Exception e) {
			logger.error("Failed to get Signature By userid - ", e);
			return new CommonResponseData("0001", "Unable to get quotation", null);
		}
	}

	@Transactional
	@Override
	public Quotation getOrderLineByQuotationId(String quotationId) {

		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Quotation> query = session.createQuery("FROM Quotation WHERE id = :quotationId", Quotation.class)
					.setParameter("quotationId", Integer.parseInt(quotationId));
			Quotation result = query.uniqueResult();

			return result;
		} catch (Exception e) {
			logger.error("Failed to get Signature By userid - ", e);
			return null;
		}
	}

	@Transactional
	@Override
	public List<QuotationMDRRate> getMDRRates(String quotationId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<QuotationMDRRate> query = session
					.createQuery("FROM QuotationMDRRate WHERE QuotationID = :quotationId", QuotationMDRRate.class)
					.setParameter("quotationId", Integer.parseInt(quotationId));
			List<QuotationMDRRate> result = query.getResultList();

			return result;
		} catch (Exception e) {
			logger.error("Failed to get Signature By userid - ", e);
			return null;
		}
	}

	@Transactional
	@Override
	public List<QuotationEzySplitMDRRate> getEzySplitMDRRates(String quotationId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<QuotationEzySplitMDRRate> query = session
					.createQuery("FROM QuotationEzySplitMDRRate WHERE quotationId = :quotationId",
							QuotationEzySplitMDRRate.class)
					.setParameter("quotationId", Integer.parseInt(quotationId));
			List<QuotationEzySplitMDRRate> result = query.getResultList();

			return result;
		} catch (Exception e) {
			logger.error("Failed to get Signature By userid - ", e);
			return null;
		}
	}

	@Transactional
	@Override
	public ArrayList<Order> getMerchantOrders(String sUserName) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Order> query = session
					.createQuery("FROM Order WHERE userId = :sUserName",
							Order.class)
					.setParameter("sUserName", sUserName);
			List<Order> result = query.getResultList();

			return (ArrayList<Order>) result;
		} catch (Exception e) {
			logger.error("Failed to get getMerchantOrders - ", e);
			return null;
		}
	}

}
