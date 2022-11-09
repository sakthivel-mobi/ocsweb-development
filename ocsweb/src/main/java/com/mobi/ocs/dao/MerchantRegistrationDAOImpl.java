package com.mobi.ocs.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.sql.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.mobi.ocs.dto.CommonResponseData;
import com.mobi.ocs.entity.MerchantRegistration;
import com.mobi.ocs.entity.Order;
import com.mobi.ocs.entity.OrderLines;
import com.mobi.ocs.entity.Quotation;
import com.mobi.ocs.entity.UmobileMCC;
import com.mobi.ocs.modal.MerchantOrderMobileResponseData;
import com.mobi.ocs.modal.MerchantRegistrationResponseData.MerchantRegistrationResponse;

@Repository
public class MerchantRegistrationDAOImpl implements MerchantRegistrationDAO {

	@Autowired
	private SessionFactory sessionFactory;

	protected static Logger logger = Logger.getLogger(MerchantRegistrationDAOImpl.class);

	@Override
	public List<MerchantRegistration> GetRegistrationOrders() {
		Session session = sessionFactory.getCurrentSession();
		Query<MerchantRegistration> query = session.createQuery("from MerchantRegistration",
				MerchantRegistration.class);
		List<MerchantRegistration> registrationOrders = query.getResultList();

		return registrationOrders;
	}

	@Override
	public List<Order> getPendingDeploymentOrderIDs() {

		Session session = sessionFactory.getCurrentSession();
		Query<Order> query = session.createNativeQuery(
				"select * from orders o inner join quotation q on q.id = o.quotation_id where q.stage = :pendingdeployment or q.stage = :umobile",
				Order.class);
		query.setParameter("pendingdeployment", "pending-deployment");
		query.setParameter("umobile", "umobile");
		List<Order> pendingDeploymentOrders = query.getResultList();

		return pendingDeploymentOrders;
	}

	@Override
	public MerchantRegistration GetRegistrationOrderByID(String id) {
		Session session = sessionFactory.getCurrentSession();
		Query<MerchantRegistration> query = session.createQuery("from MerchantRegistration where id = :id",
				MerchantRegistration.class);
		query.setParameter("id", Integer.parseInt(id));
		MerchantRegistration reg = query.getSingleResult();

		return reg;
	}

	@Override
	@Transactional
	public Object CreateMerchantRegistrationForOrder(String orderId) {

		try {

			LocalDateTime now = LocalDateTime.now();

			Session session = sessionFactory.getCurrentSession();
			Order order = session.load(Order.class, Integer.parseInt(orderId));

			MerchantRegistration existingRegistration = (MerchantRegistration) session
					.createQuery("FROM MerchantRegistration WHERE orderId = :orderId")
					.setParameter("orderId", Integer.parseInt(orderId)).uniqueResult();

			if (existingRegistration == null) {
				MerchantRegistration reg = new MerchantRegistration();

				reg.setCreatedOn(now);
				reg.setOrder(order);
				reg.setAutoSettled("No");
				reg.setBankOTP("No");
				reg.setPreAuth("No");
				reg.setDocuments("");

				reg.setEzywireMID("");
				reg.setEzylinkMID("");
				reg.setEzysplitMID("");
				reg.setEzyrecplusMID("");
				reg.setEzywayMID("");

				reg.setiSwitchDiscount(order.getQuotation().getiSwitchDiscount());

				reg.setUmEzywireMID("");
				reg.setUmEzylinkMID("");
				reg.setUmEzysplitMID("");
				reg.setUmEzyrecplusMID("");
				reg.setUmEzywayMID("");

				reg.setWaiverMonth("");
				reg.setSignedPackage("");
				reg.setNoOfReader("");
				reg.setStatusRemarks("");
				reg.setMdr("");
				reg.setOwnerCount("0");
				reg.setiSwitchEnable("No");
				session.save(reg);

				return new CommonResponseData("0000", "Merchant details added", null);
			} else {
				return new CommonResponseData("0001", "Dupicate entries not allowed, Merchant details already exist",
						null);
			}

		} catch (Exception e) {
			logger.error("Error in Creating Merchant Registration Row for Order : ", e);
			return new CommonResponseData("0001", "Something went wrong unable to added merchant details", null);
		}
	}

	@Override
	public void UpdateMerchantRegistration(MerchantRegistration reg) {

		try {

			logger.info("UpdateMerchantRegistration >> " + reg.toString());

			Session session = sessionFactory.getCurrentSession();
			Order order = session.load(Order.class, reg.getOrder().getId());

			reg.setOrder(order);
			reg.setiSwitchDiscount(reg.getiSwitchDiscount());

			if (reg.getAutoSettled() == null) {
				reg.setAutoSettled("No");
			}
			if (reg.getPreAuth() == null) {
				reg.setPreAuth("No");
			}
			if (reg.getBankOTP() == null) {
				reg.setBankOTP("No");
			}
			if (reg.getiSwitchEnable() == null) {
				reg.setiSwitchEnable("No");
			}
			if (reg.getiSwitchDiscount() == null) {
				reg.setiSwitchDiscount("No");
			}

			session.update(reg);

		} catch (Exception e) {
			logger.error("Error in Updating Merchant Registration : ", e);
		}
	}

	@Override
	public void UpdateSuccessStatusForRegistration(String orderId) {

		logger.info("Updating Success Status for Registation of OrderID : " + orderId);
		Session session = sessionFactory.getCurrentSession();
		Query<MerchantRegistration> query = session.createNativeQuery(
				"update merchantRegistration set isSuccess =:isSuccess where orderId = :orderId",
				MerchantRegistration.class);
		query.setParameter("orderId", Integer.parseInt(orderId));
		query.setParameter("isSuccess", true);
		query.executeUpdate();

	}

	@Override
	public void updateAPIKeyAndActivationCode(String mobiApiKey, String activationCode, int quotationId,
			String password, String userName) {
		try {

			Session session = sessionFactory.getCurrentSession();
			int count = session.createQuery(
					"UPDATE OrderLines SET apiKey =:apiKey, activationCode =:activationCode, userName =:userName, password =:password WHERE quotationId =:quotationId")
					.setParameter("apiKey", mobiApiKey).setParameter("activationCode", activationCode)
					.setParameter("userName", userName).setParameter("password", password)
					.setParameter("quotationId", quotationId).executeUpdate();

			logger.info("updateAPIKeyAndActivationCode >> " + count);

		} catch (Exception e) {
			logger.error("updateAPIKeyAndActivationCode >> ", e);
		}
	}

	@Override
	@Transactional
	public Object GetMerchantRegistrationListMobile(String username) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Quotation> query = session.createQuery("FROM Quotation WHERE userId =:username")
					.setParameter("username", username);
			ArrayList<Quotation> registrationOrders = (ArrayList<Quotation>) query.getResultList();

			logger.info("registrationOrders size >> " + registrationOrders.size());
			return new CommonResponseData("0000", "Success", registrationOrders);
		} catch (Exception e) {
			logger.error("GetMerchantRegistrationListMobile >> ", e);
			return new CommonResponseData("0001", "Unable to get Quotation List", null);
		}

	}

	@Override
	public List<MerchantRegistration> getGrapPayReport() {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<MerchantRegistration> query = session.createQuery("FROM MerchantRegistration WHERE isSuccess ='1'",
					MerchantRegistration.class);
			List<MerchantRegistration> merchantRegistration = query.getResultList();

			logger.info("merchantRegistration >> " + merchantRegistration.toString());
			return merchantRegistration;
		} catch (Exception e) {
			logger.error("getGrapPayReport >> ", e);
			return null;
		}
	}

	@Override
	public UmobileMCC getUmobileMccList(int visaMCC) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<UmobileMCC> query = session.createQuery("FROM UmobileMCC WHERE value =:visaMCC", UmobileMCC.class)
					.setParameter("visaMCC", String.valueOf(visaMCC));
			UmobileMCC umobileMCC = query.uniqueResult();

			logger.info("getUmobileMccList >> " + umobileMCC.toString());
			return umobileMCC;
		} catch (Exception e) {
			logger.error("getUmobileMccList >> ", e);
			return null;
		}
	}

	@Transactional
	@Override
	public void updateMidForMerchantRegistration(String merchantRegistrationId,
			MerchantRegistrationResponse responseData) {
		try {
			Session session = sessionFactory.getCurrentSession();

			Query<MerchantRegistration> query = session
					.createNativeQuery("UPDATE merchantRegistration SET \r\n" + "grabMid = :grabMid, \r\n"
							+ "grabTid = :grabTid, \r\n" + "boostMid = :boostMid, \r\n" + "boostTid = :boostTid, \r\n"
							+ "fpxMid = :fpxMid, \r\n" + "tngMid = :tngMid, \r\n" + "shopeepayMid = :shopeepayMid, \r\n" +"tngTid = :tngTid, \r\n" + "shopeepayTid = :shopeepayTid, \r\n" + "fpxTid = :fpxTid\r\n" + "WHERE id = :merchantRegistrationId",
							MerchantRegistration.class)
					.setParameter("grabMid", String.valueOf(responseData.getGrabMid()))
					.setParameter("grabTid", String.valueOf(responseData.getGrabTid()))
					.setParameter("boostMid", String.valueOf(responseData.getBoostMid()))
					.setParameter("boostTid", String.valueOf(responseData.getBoostTid()))
					.setParameter("fpxMid", String.valueOf(responseData.getFpxMid()))
					.setParameter("fpxTid", String.valueOf(responseData.getFpxTid()))
					.setParameter("tngTid", String.valueOf(responseData.getTngTid()))
					.setParameter("shopeepayTid", String.valueOf(responseData.getShopeepayTid()))
					.setParameter("tngMid", String.valueOf(responseData.getTngMid()))
					.setParameter("shopeepayMid", String.valueOf(responseData.getShopeepayMid()))
					.setParameter("merchantRegistrationId", Integer.valueOf(merchantRegistrationId));

			query.executeUpdate();

		} catch (Exception e) {
			logger.error("getUmobileMccList >> ", e);
		}

	}

	@Override
	public MerchantRegistration getMerchantRegistrationDetailsByRegistrationId(String registrationID) {
		try {
			Session session = sessionFactory.getCurrentSession();

			Query<MerchantRegistration> query = session
					.createQuery("FROM MerchantRegistration WHERE id =:registrationID", MerchantRegistration.class)
					.setParameter("registrationID", Integer.valueOf(registrationID));
			MerchantRegistration merchantRegistration = query.uniqueResult();

			logger.info("getMerchantRegistrationDetailsByRegistrationId >> " + merchantRegistration.toString());

			return merchantRegistration;
		} catch (Exception e) {
			logger.error("getMerchantRegistrationDetailsByRegistrationId >> ", e);
			return null;
		}
	}

	@Override
	public MerchantRegistration getMerchantRegistrationByRegistrationId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public void saveWebPortalCredentials(String merchantRegistrationId, MerchantRegistrationResponse responseData) {
		logger.info("saveWebPortalCredentials >> " + responseData.toString());
		try {

			Session session = sessionFactory.getCurrentSession();

			Query<MerchantRegistration> query = null;

			if (responseData.getPassword() != null) {
				query = session
						.createNativeQuery(
								"UPDATE merchantRegistration SET " + "webPortalUsername =:webPortalUsername,"
										+ "webPortalPassword =:webPortalPassword" + "  WHERE id =:registrationID",
								MerchantRegistration.class)
						.setParameter("registrationID", Integer.valueOf(merchantRegistrationId))
						.setParameter("webPortalUsername", responseData.getUsername());
				query.setParameter("webPortalPassword", responseData.getPassword());
			} else {
				query = session
						.createNativeQuery("UPDATE merchantRegistration SET " + "webPortalUsername =:webPortalUsername "
								+ "  WHERE id =:registrationID", MerchantRegistration.class)
						.setParameter("registrationID", Integer.valueOf(merchantRegistrationId))
						.setParameter("webPortalUsername", responseData.getUsername());

			}
			query.executeUpdate();

		} catch (Exception e) {
			logger.error("saveWebPortalCredentials >> ", e);
		}

	}

	@Transactional
	@Override
	public void updateMerchantAppCredentials(int merchantRegistrationId, String username, String password,
			String activationCode, String mobiApiKey) {

		logger.error("updateMerchantAppCredentials  Started");
		try {
			logger.error("updateMerchantAppCredentials  Started");
			Session session = sessionFactory.getCurrentSession();
			int updatedCount = session
					.createNativeQuery("UPDATE merchantRegistration SET " + "appUsername =:appUsername,"
							+ "appPassword =:appPassword," + "apiKey =:apiKey," + "activationCode =:activationCode,"
							+ "eWalletSyncSuccess = :eWalletSyncSuccess" + "  WHERE id =:merchantRegistrationId",
							MerchantRegistration.class)
					.setParameter("appUsername", username).setParameter("appPassword", password)
					.setParameter("eWalletSyncSuccess", 1).setParameter("apiKey", mobiApiKey)
					.setParameter("activationCode", activationCode)
					.setParameter("merchantRegistrationId", merchantRegistrationId).executeUpdate();
			logger.error("updateMerchantAppCredentials  Ended Updated >> " + updatedCount);
		} catch (Exception e) {
			logger.error("updateMerchantAppCredentials >> ", e);
		}
	}

	@Transactional
	@Override
	public void updateEwalletSyncCompleted(int merchantRegistrationId) {

		logger.error("updateMerchantAppCredentials  Started");
		try {
			logger.error("updateMerchantAppCredentials  Started");
			Session session = sessionFactory.getCurrentSession();
			int updatedCount = session
					.createNativeQuery("UPDATE merchantRegistration SET " + "eWalletSyncSuccess = :eWalletSyncSuccess"
							+ "  WHERE id =:merchantRegistrationId", MerchantRegistration.class)
					.setParameter("eWalletSyncSuccess", 1)
					.setParameter("merchantRegistrationId", merchantRegistrationId).executeUpdate();
			logger.error("updateMerchantAppCredentials  Ended Updated >> " + updatedCount);
		} catch (Exception e) {
			logger.error("updateMerchantAppCredentials >> ", e);
		}
	}

	@Transactional
	@Override
	public Object getMerchantRegistrationByOrderId(String orderId) {
		try {
			logger.info("getMerchantRegistrationByOrderId >> " + orderId);
			Session session = sessionFactory.getCurrentSession();

			Query<MerchantRegistration> query = session
					.createQuery("FROM MerchantRegistration WHERE orderId =:orderId", MerchantRegistration.class)
					.setParameter("orderId", orderId);

			MerchantRegistration merchantRegistration = query.uniqueResult();

			logger.info("getMerchantRegistrationDetailsByRegistrationId >> " + merchantRegistration.toString());

			return merchantRegistration;
		} catch (Exception e) {
			logger.error("getMerchantRegistrationDetailsByRegistrationId >> ", e);
			return null;
		}
	}

}
