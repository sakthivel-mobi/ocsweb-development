package com.mobi.ocs.dao;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mobi.ocs.dto.CommonResponseData;
import com.mobi.ocs.dto.IssueQuotationResponseDataModel;
import com.mobi.ocs.entity.AccountType;
import com.mobi.ocs.entity.Acquirer;
import com.mobi.ocs.entity.Bank;
import com.mobi.ocs.entity.BusinessType;
import com.mobi.ocs.entity.CompanyType;
import com.mobi.ocs.entity.Contact;
import com.mobi.ocs.entity.Director;
import com.mobi.ocs.entity.HostRate;
import com.mobi.ocs.entity.HostType;
import com.mobi.ocs.entity.IssuedQuotation;
import com.mobi.ocs.entity.MasterMerchant;
import com.mobi.ocs.entity.MerchantIdType;
import com.mobi.ocs.entity.NatureOfBusiness;
import com.mobi.ocs.entity.NotificationList;
import com.mobi.ocs.entity.OldNotesDetails;
import com.mobi.ocs.entity.OldOrderDetails;
import com.mobi.ocs.entity.Order;
import com.mobi.ocs.entity.OrderLines;
import com.mobi.ocs.entity.OrderNotes;
import com.mobi.ocs.entity.OrderType;
import com.mobi.ocs.entity.Payment;
import com.mobi.ocs.entity.PaymentType;
import com.mobi.ocs.entity.PendingQuotationMDRRate;
import com.mobi.ocs.entity.PendingQuotationEzySplitMDRRate;
import com.mobi.ocs.entity.Product;
import com.mobi.ocs.entity.ProductType;
import com.mobi.ocs.entity.Quotation;
import com.mobi.ocs.entity.QuotationAcceptance;
import com.mobi.ocs.entity.QuotationEzySplitMDRRate;
import com.mobi.ocs.entity.QuotationMDRRate;
import com.mobi.ocs.entity.QuotationPDF;
import com.mobi.ocs.entity.SalesPerson;
import com.mobi.ocs.entity.Salutation;
import com.mobi.ocs.entity.StageMovement;
import com.mobi.ocs.entity.StandardMDRRate;
import com.mobi.ocs.entity.UmobileCountry;
import com.mobi.ocs.entity.UmobileECommIndustry;
import com.mobi.ocs.entity.UmobileMCC;
import com.mobi.ocs.entity.UmobileState;
import com.mobi.ocs.entity.UmobileTown;
import com.mobi.ocs.entity.UserDetail;
import com.mobi.ocs.entity.WalletRate;
import com.mobi.ocs.entity.dotnetOcs.Director_dotnet;
import com.mobi.ocs.entity.dotnetOcs.Document_dotnet;
import com.mobi.ocs.entity.dotnetOcs.MerchantDetails;
import com.mobi.ocs.entity.dotnetOcs.StageMovement_dotnet;
import com.mobi.ocs.modal.OldProductListData;
import com.mobi.ocs.modal.OrderDirectorResponseData;
import com.mobi.ocs.modal.OrderNotesResponseData;
import com.mobi.ocs.modal.PendingQuotationMDRRatesRequestData;

@Repository
public class DotnetDataDAOImpl implements DotnetDataDAO {

	@Autowired
	private SessionFactory sessionFactory;

	protected static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DotnetDataDAOImpl.class);

	@Override
	public List<MerchantDetails> GetDotnetOCSMerchantData() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<MerchantDetails> theQuery = currentSession.createQuery("from MerchantDetails", MerchantDetails.class);
		List<MerchantDetails> merchantDetails = theQuery.getResultList();

		return merchantDetails;
	}

	@Override
	public MerchantDetails GetDotnetOCSMerchantDataById(String orderID) {
		Session session = sessionFactory.getCurrentSession();
		Query<MerchantDetails> query = session.createQuery("from MerchantDetails where OrderId = :orderID",
				MerchantDetails.class);
		query.setParameter("orderID", orderID);

		MerchantDetails merchantDetail = query.getSingleResult();
		return merchantDetail;
	}

	@Override
	public List<Director_dotnet> GetDotnetOCSDirectorsByOrderID(String OrderId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Director_dotnet> query = currentSession.createQuery("from Director_dotnet where OrderId = :OrderId",
				Director_dotnet.class);
		query.setParameter("OrderId", OrderId);

		List<Director_dotnet> directors = query.getResultList();

		return directors;
	}

	@Override
	public List<StageMovement_dotnet> GetDotnetOCSStageMovementByOrderId(String orderId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<StageMovement_dotnet> query = currentSession
				.createQuery("from StageMovement_dotnet where orderid = :orderId", StageMovement_dotnet.class);
		query.setParameter("orderId", orderId);

		List<StageMovement_dotnet> stageMovement = query.getResultList();

		return stageMovement;
	}

	@Override
	public List<Document_dotnet> GetDotnetOCSDocumentsByUserId(String userId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Document_dotnet> query = currentSession.createQuery("from Document_dotnet where USERNAME = :userId",
				Document_dotnet.class);
		query.setParameter("userId", userId);

		List<Document_dotnet> documents = query.getResultList();

		return documents;
	}

	@Override
	public List<OldOrderDetails> getOldOrderDetailsById(String orderID) {
		try {
			Session currentSession = sessionFactory.getCurrentSession();

			List<OldOrderDetails> oldOrderDetails = currentSession
					.createQuery("FROM OldOrderDetails WHERE OrderId = :OrderId", OldOrderDetails.class)
					.setParameter("OrderId", orderID).getResultList();

			return oldOrderDetails;
		} catch (Exception e) {
			logger.error("getOldOrderDetailsById", e);
			return null;
		}

	}

	@Override
	public List<OldNotesDetails> getOldOrderNotesByOrderId(String orderID) {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Query<OldNotesDetails> query = currentSession
					.createQuery("FROM OldNotesDetails WHERE OrderId =:OrderId", OldNotesDetails.class)
					.setParameter("OrderId", orderID);

			return query.getResultList();
		} catch (Exception e) {
			logger.error("getOldOrderNotesByOrderId", e);
			return null;
		}

	}

}