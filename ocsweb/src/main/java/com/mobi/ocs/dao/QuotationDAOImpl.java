package com.mobi.ocs.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.mobi.ocs.entity.Callback;
import com.mobi.ocs.entity.CompanyType;
import com.mobi.ocs.entity.Contact;
import com.mobi.ocs.entity.Director;
import com.mobi.ocs.entity.HostRate;
import com.mobi.ocs.entity.HostType;
import com.mobi.ocs.entity.IssuedProfoma;
import com.mobi.ocs.entity.IssuedQuotation;
import com.mobi.ocs.entity.MasterMerchant;
import com.mobi.ocs.entity.MerchantIdType;
import com.mobi.ocs.entity.NatureOfBusiness;
import com.mobi.ocs.entity.NotificationList;
import com.mobi.ocs.entity.Order;
import com.mobi.ocs.entity.OrderLines;
import com.mobi.ocs.entity.OrderNotes;
import com.mobi.ocs.entity.OrderType;
import com.mobi.ocs.entity.Payment;
import com.mobi.ocs.entity.PaymentType;
import com.mobi.ocs.entity.PendingQuotationEzySplitMDRRate;
import com.mobi.ocs.entity.PendingQuotationMDRRate;
import com.mobi.ocs.entity.Product;
import com.mobi.ocs.entity.ProductType;
import com.mobi.ocs.entity.Profoma;
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
import com.mobi.ocs.modal.DiscountPriceRequestData;
import com.mobi.ocs.modal.OrderDirectorResponseData;
import com.mobi.ocs.modal.OrderNotesResponseData;
import com.mobi.ocs.modal.RollbackRequestData;

@Repository
public class QuotationDAOImpl implements QuotationDAO {

	@Autowired
	private SessionFactory sessionFactory;

	protected static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(QuotationDAOImpl.class);

	@Transactional
	public List<Quotation> getQuotations() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Quotation> theQuery = currentSession.createQuery("from Quotation q ORDER BY q.id DESC", Quotation.class);
		List<Quotation> quotations = theQuery.getResultList();

		return quotations;
	}

	@Transactional
	public void saveQuotation(Quotation quotation, int salespersonId) {

		try {

			Date currentDateTime = new Date();
			if (quotation.getCreatedOn() == null) {
				quotation.setCreatedOn(currentDateTime);
			}
			quotation.setLastModified(currentDateTime);

			Session session = sessionFactory.getCurrentSession();
			SalesPerson salesPerson = session.load(SalesPerson.class, salespersonId);
			quotation.setSalesPerson(salesPerson);

			session.saveOrUpdate(quotation);

		} catch (Exception e) {
			logger.error("Stacktrace : ", e);
		}

	}

	@Transactional
	public void deleteQuotation(String quotationID) {
		Session currentSession = sessionFactory.getCurrentSession();

		Quotation quote = currentSession.get(Quotation.class, Integer.parseInt(quotationID));

		currentSession.delete(quote);
	}

	@Transactional
	public List<OrderType> getOrderTypes() {

		Session currentSession = sessionFactory.getCurrentSession();
		Query<OrderType> theQuery = currentSession.createQuery("from OrderType", OrderType.class);
		List<OrderType> orderTypes = theQuery.getResultList();

		return orderTypes;
	}

	@Transactional
	public Quotation getQuotationByID(int quotationID) {
		Session currentSession = sessionFactory.getCurrentSession();
		Quotation quote = currentSession.get(Quotation.class, quotationID);
		return quote;
	}

	@Transactional
	public void saveContact(Contact contact) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(contact);

	}

	@Transactional
	public Boolean IsDealExist(String dealID) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Quotation> query = currentSession.createQuery("from Quotation where dealID = :dealID", Quotation.class);
		query.setParameter("dealID", dealID);
		Quotation quotation = new Quotation();
		try {
			quotation = query.getSingleResult();
		} catch (NoResultException nre) {
			logger.info("No Deals Found in DB!");
			return false;
		}

		logger.info("Deal Already Imported!");
		return true;
	}

	@Transactional
	public int SaveStandardMDRRate(StandardMDRRate rate) {
		Session session = sessionFactory.getCurrentSession();
		return (Integer) session.save(rate);
	}

	@Transactional
	public void SaveProduct(Product product) {

		if (getProductName(product.getProductName()).isEmpty()) {
			LocalDateTime now = LocalDateTime.now();
			Session session = sessionFactory.getCurrentSession();
			product.setCreatedOn(now);
			session.save(product);
		}

	}

	@Transactional
	@Override
	public List<Product> getProductName(String productName) {

		try {

			Session session = sessionFactory.getCurrentSession();

			List<Product> product = session.createQuery("FROM Product WHERE productName = :productName", Product.class)
					.setParameter("productName", productName).getResultList();

			logger.info("product >> " + product.toString());

			return product;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Transactional
	@Override
	public List<String> getAllProductList() {

		try {
			Session session = sessionFactory.getCurrentSession();
			List<Product> product = session.createQuery("FROM Product", Product.class).getResultList();
			logger.info("product >> " + product.toString());

			List<String> list = new ArrayList<String>();
			for (Product item : product) {
				list.add(item.getProductName());
			}

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	public List<Product> GetProducts() {
		Session session = sessionFactory.getCurrentSession();
		Query<Product> query = session.createQuery("from Product where refId = null", Product.class);
		List<Product> products = query.getResultList();

		return products;
	}

	@Override
	@Transactional
	public int SavePayment(Payment payment) {
		try {
			Session session = sessionFactory.getCurrentSession();

			Payment existing = (Payment) session.createQuery("FROM Payment WHERE quotationId = :quotationId")
					.setParameter("quotationId", payment.getQuotationId()).uniqueResult();

			if (existing == null) {
				return (Integer) session.save(payment);
			} else {
				session.createQuery(
						"UPDATE Payment SET receipt = :receiptImage, collectedOn =:collectedOn WHERE quotationId = :quotationId")
						.setParameter("quotationId", payment.getQuotationId())
						.setParameter("collectedOn", payment.getCollectedOn())
						.setParameter("receiptImage", payment.getReceipt()).executeUpdate();
				return existing.getId();
			}

		} catch (Exception e) {
			logger.error("Failed to Save Payment - ", e);
			return -1;
		}
	}

	@Transactional
	public void UpdatePayment(Payment payment) {
		Session session = sessionFactory.getCurrentSession();
		session.update(payment);
	}

	@Transactional
	public void UpdatePaymentIdInQuotation(int paymentId, String quotationId) {
		try {

			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("UPDATE Quotation set paymentId = :paymentId where Id = :quotationId");
			query.setParameter("paymentId", paymentId);
			query.setParameter("quotationId", quotationId);
			int result = query.executeUpdate();
			logger.info("Rows affected: " + result);
		} catch (Exception e) {
			logger.error("UpdatePaymentIdInQuotation >> ", e);
		}

	}

	@Transactional
	@Override
	public void CreateOrder(Order order, int quotationId) {
		logger.info("CreateOrder >> ");

		try {
			Session session = sessionFactory.getCurrentSession();

			Order existingOrder = (Order) session.createQuery("FROM Order WHERE quotation_id =:quotationId")
					.setParameter("quotationId", quotationId).uniqueResult();

			if (existingOrder == null) {
				Quotation quote = session.load(Quotation.class, quotationId);
				order.setQuotation(quote);
				session.save(order);
				logger.info("New order created");
			} else {
				logger.error("Order already exist");
			}

		} catch (Exception e) {
			logger.error("CreateOrder >> ", e);
		}

	}

	@Transactional
	public List<Order> getOrders() {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Order> query = session.createQuery("from Order", Order.class);
			List<Order> orders = query.getResultList();
			return orders;
		} catch (Exception e) {
			logger.error("getOrders >> ", e);
			return null;
		}
	}

	@Transactional
	public List<MerchantIdType> GetMerchantIdTypeList() {
		Session session = sessionFactory.getCurrentSession();
		Query<MerchantIdType> query = session.createQuery("from MerchantIdType", MerchantIdType.class);
		List<MerchantIdType> merchantIdTypes = query.getResultList();

		return merchantIdTypes;
	}

	@Transactional
	public List<NatureOfBusiness> GetNatureOfBusinessList() {
		Session session = sessionFactory.getCurrentSession();
		Query<NatureOfBusiness> query = session.createQuery("from NatureOfBusiness", NatureOfBusiness.class);
		List<NatureOfBusiness> nob = query.getResultList();

		return nob;
	}

	@Transactional
	public List<UmobileECommIndustry> GetEcommIndustryList() {
		Session session = sessionFactory.getCurrentSession();
		Query<UmobileECommIndustry> query = session.createQuery("from UmobileECommIndustry",
				UmobileECommIndustry.class);
		List<UmobileECommIndustry> eCommIndustries = query.getResultList();

		return eCommIndustries;
	}

	@Transactional
	public List<UmobileMCC> GetUmobileMCCList() {
		Session session = sessionFactory.getCurrentSession();
		Query<UmobileMCC> query = session.createQuery("from UmobileMCC", UmobileMCC.class);
		List<UmobileMCC> umobileMCC = query.getResultList();

		return umobileMCC;
	}

	@Transactional
	public List<CompanyType> GetCompanyTypeList() {
		Session session = sessionFactory.getCurrentSession();
		Query<CompanyType> query = session.createQuery("from CompanyType", CompanyType.class);
		List<CompanyType> companyTypes = query.getResultList();

		return companyTypes;
	}

	@Transactional
	public List<AccountType> GetAccountTypeList() {
		Session session = sessionFactory.getCurrentSession();
		Query<AccountType> query = session.createQuery("from AccountType", AccountType.class);
		List<AccountType> accountTypes = query.getResultList();

		return accountTypes;
	}

	@Transactional
	public Order GetOrderByID(String orderID) {
		logger.info(orderID);
		Session currentSession = sessionFactory.getCurrentSession();
		Order order = currentSession.get(Order.class, Integer.parseInt(orderID));
		return order;
	}

	@Override
	@Transactional
	public List<Acquirer> GetAcquirerList() {
		Session session = sessionFactory.getCurrentSession();
		Query<Acquirer> query = session.createQuery("from Acquirer", Acquirer.class);
		List<Acquirer> acquirerTypes = query.getResultList();

		return acquirerTypes;
	}

	@Override
	@Transactional
	public List<UmobileState> GetStateList() {
		Session session = sessionFactory.getCurrentSession();
		Query<UmobileState> query = session.createQuery("from UmobileState", UmobileState.class);
		List<UmobileState> states = query.getResultList();

		return states;
	}

	@Override
	@Transactional
	public List<UmobileTown> GetCityList() {
		Session session = sessionFactory.getCurrentSession();
		Query<UmobileTown> query = session.createQuery("from UmobileTown", UmobileTown.class);
		List<UmobileTown> cities = query.getResultList();

		return cities;
	}

	@Override
	@Transactional
	public List<ProductType> GetProductTypes() {
		Session session = sessionFactory.getCurrentSession();
		Query<ProductType> query = session.createQuery("from ProductType", ProductType.class);
		List<ProductType> productTypes = query.getResultList();

		return productTypes;
	}

	@Override
	@Transactional
	public List<HostType> GetHostTypes() {
		Session session = sessionFactory.getCurrentSession();
		Query<HostType> query = session.createQuery("from HostType", HostType.class);
		List<HostType> hostTypes = query.getResultList();

		return hostTypes;
	}

	@Override
	@Transactional
	public List<HostRate> GetHostRates() {
		Session session = sessionFactory.getCurrentSession();
		Query<HostRate> query = session.createQuery("from HostRate where refId = null", HostRate.class);
		List<HostRate> hostRates = query.getResultList();

		return hostRates;
	}

	@Override
	@Transactional
	public void SaveHostRate(HostRate hostRate) {
		Session session = sessionFactory.getCurrentSession();

		Date now = new Date();
		hostRate.setCreatedOn(now);
		session.save(hostRate);
	}

	@Override
	@Transactional
	public List<HostRate> GetHostRates(String hostType, String productType) {

		Session session = sessionFactory.getCurrentSession();
		Query<HostRate> query = session.createNativeQuery(
				"select * from HostRate where hostType = :hostType and productType = :productType", HostRate.class);
		query.setParameter("hostType", hostType);
		query.setParameter("productType", productType);

		List<HostRate> hostRates = query.getResultList();

		return hostRates;

	}

	@Override
	@Transactional
	public HostRate GetHostRate(int hostRate) {
		Session currentSession = sessionFactory.getCurrentSession();
		HostRate hostRateValues = currentSession.get(HostRate.class, hostRate);
		return hostRateValues;
	}

	@Override
	@Transactional
	public List<Product> GetProductsByType(String type) {
		Session session = sessionFactory.getCurrentSession();
		Query<Product> query = session.createQuery("from Product where productType = :productType and refId = null",
				Product.class);
		query.setParameter("productType", type);

		List<Product> products = query.getResultList();

		return products;
	}

	@Override
	@Transactional
	public void AddOrderLine(int productId, int quotationId, int quantity, int quotationMDRRateId) {
		Session session = sessionFactory.getCurrentSession();
		Product product = session.load(Product.class, productId);
		Quotation quotation = session.load(Quotation.class, quotationId);
		QuotationMDRRate mdrRate = session.load(QuotationMDRRate.class, quotationMDRRateId);

		LocalDateTime now = LocalDateTime.now();
		OrderLines line = new OrderLines();

		line.setCreatedOn(now);
		line.setProduct(product);
		line.setQuotation(quotation);
		line.setQuotationMDRRate(mdrRate);
		line.setQuantity(quantity);

		session.save(line);

	}

	@Override
	@Transactional
	public List<OrderLines> GetOrderLineByQuotation(int quotationId) {
		try {

			Session session = sessionFactory.getCurrentSession();
			Query<OrderLines> query = session.createQuery("from OrderLines where quotationId = :quotationId",
					OrderLines.class);
			query.setParameter("quotationId", quotationId);
			List<OrderLines> orderLines = query.getResultList();
			return orderLines;

		} catch (Exception e) {
			logger.error(" GetOrderLineByQuotation >> ", e);
			return null;
		}
	}

	@Override
	@Transactional
	public void deleteOrderLines(String[] orderLineIDArray) {

		Session session = sessionFactory.getCurrentSession();

		for (String orderLineID : orderLineIDArray) {
			int id = Integer.parseInt(orderLineID);

			Query<OrderLines> query = session.createNativeQuery("Delete from orderLines where id = :id",
					OrderLines.class);
			query.setParameter("id", id);

			query.executeUpdate();
		}

	}

	@Override
	@Transactional
	public OrderLines getOrderLineById(int orderLineId) {
		Session session = sessionFactory.getCurrentSession();
		Query<OrderLines> query = session.createQuery("from OrderLines where id = :orderLineId", OrderLines.class);
		query.setParameter("orderLineId", orderLineId);

		OrderLines orderLine = query.getSingleResult();

		return orderLine;
	}

	@Override
	@Transactional
	public int SaveQuotationMDRRate(QuotationMDRRate quoteRate, int productId, int quotationid) {
		Session session = sessionFactory.getCurrentSession();

		Product product = session.load(Product.class, productId);
		Quotation quotation = session.load(Quotation.class, quotationid);

		quoteRate.setHostType(product.getHostType());
		quoteRate.setIncludeWallet(product.getIncludeWallet());
		quoteRate.setPayLater(product.getPayLater());

		quoteRate.setProductName(product.getProductName());
		quoteRate.setProductType(product.getProductType());
		quoteRate.setShowInQuote(product.getShowInQuote());
		quoteRate.setSubscription(String.valueOf(product.getSubscription()));
		quoteRate.setUnitPrice(product.getUnitPrice());
		// quoteRate.setProduct(product);
		quoteRate.setQuotation(quotation);

		int quotationMdrRateId = (int) session.save(quoteRate);
		return quotationMdrRateId;

	}

	@Override
	@Transactional
	public void UpdateOrderLinesWithQuotationMDRID(int quotationMDRRateId, int orderLineId) {
		Session session = sessionFactory.getCurrentSession();

		OrderLines orderLines = session.load(OrderLines.class, orderLineId);
		QuotationMDRRate quotationMDRRate = session.load(QuotationMDRRate.class, quotationMDRRateId);

		orderLines.setQuotationMDRRate(quotationMDRRate);
	}

	@Override
	@Transactional
	public void UpdateQuotationMDRRate(QuotationMDRRate quoteRate, int productId, int quotationid) {
		Session session = sessionFactory.getCurrentSession();

		// Product product = session.load(Product.class, productId);
		Quotation quotation = session.load(Quotation.class, quotationid);

		// quoteRate.setProduct(product);
		quoteRate.setQuotation(quotation);

		logger.info(quoteRate.getId());

		session.update(quoteRate);

	}

	@SuppressWarnings("null")
	@Override
	@Transactional
	public void updateQuotation(Quotation quote) {
		Session session = sessionFactory.getCurrentSession();

		try {

			// Load Orders
			if (quote.getOrder() != null && quote.getOrder().getId() != 0) {
				Order order = session.load(Order.class, quote.getOrder().getId());
				quote.setOrder(order);
			} else {
				quote.setOrder(null);
			}

			// Load SalesPerson
			if (quote.getSalesPerson() != null && quote.getSalesPerson().getId() != 0) {
				SalesPerson salesPerson = session.load(SalesPerson.class, quote.getSalesPerson().getId());
				quote.setSalesPerson(salesPerson);
			} else {
				quote.setSalesPerson(null);
			}

			// Load Payment
			if (quote.getPayment() != null && quote.getPayment().getId() != 0) {
				Payment payment = session.load(Payment.class, quote.getPayment().getId());
				quote.setPayment(payment);
			} else {
				quote.setPayment(null);
			}

			// Load Contact
			if (quote.getContact() != null && quote.getContact().getId() != 0) {
				Contact contact = session.load(Contact.class, quote.getContact().getId());
				quote.setContact(contact);
			} else {
				quote.setContact(null);
			}

			Date currentDateTime = new Date();
			quote.setLastModified(currentDateTime);

			session.update(quote);

		} catch (Exception e) {
			logger.error("Failed to update Quotation - ", e);
		}

	}

	@Override
	public List<PaymentType> GetPaymentTypes() {
		Session session = sessionFactory.getCurrentSession();
		Query<PaymentType> query = session.createQuery("from PaymentType", PaymentType.class);
		List<PaymentType> paymentTypes = query.getResultList();

		return paymentTypes;
	}

	@Override
	public List<Acquirer> GetAcquirers() {
		Session session = sessionFactory.getCurrentSession();
		Query<Acquirer> query = session.createQuery("from Acquirer", Acquirer.class);
		return query.getResultList();
	}

	@Override
	public List<SalesPerson> GetSalesPersons() {
		Session session = sessionFactory.getCurrentSession();
		Query<SalesPerson> query = session.createQuery("from SalesPerson", SalesPerson.class);
		return query.getResultList();
	}

	@Override
	public void SaveDirector(Director director, String orderId) {
		LocalDateTime now = LocalDateTime.now();
		Session session = sessionFactory.getCurrentSession();

		Order order = session.load(Order.class, Integer.parseInt(orderId));

		director.setOrder(order);
		director.setCreatedOn(now);

		session.save(director);
	}

	@Override
	public Product GetProductById(int productId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Product> query = session.createQuery("from Product where Id = :productId", Product.class);
		query.setParameter("productId", productId);

		Product product = query.getSingleResult();

		return product;
	}

	@Override
	public void AddProductToLogHistory(int productId) {
		Session session = sessionFactory.getCurrentSession();
		Product product = session.load(Product.class, productId);

		StandardMDRRate mdrRate = product.getStandardmdrRate();
		int mdrID = SaveStandardMDRRate(product.getStandardmdrRate());

		mdrRate.setId(mdrID);
		product.setStandardmdrRate(mdrRate);
		product.setRefId(String.valueOf(productId));
		session.save(product);

	}

	@Override
	public void UpdateProduct(Product product) {
		LocalDateTime now = LocalDateTime.now();
		product.setUpdatedOn(now);
		Session session = sessionFactory.getCurrentSession();
		session.update(product);
	}

	@Override
	public List<Product> GetProductHistoryList(int productId) {
		String id = String.valueOf(productId);
		Session session = sessionFactory.getCurrentSession();
		Query<Product> query = session.createQuery("from Product where refId = :productId", Product.class);
		query.setParameter("productId", id);

		List<Product> products = query.getResultList();

		return products;
	}

	@Override
	public void SaveNotes(OrderNotes notes, String orderId, String from) {
		Date now = new Date();
		Session session = sessionFactory.getCurrentSession();

		Order order = session.load(Order.class, Integer.parseInt(orderId));

		notes.setOrder(order);
		notes.setCreatedOn(now);
		notes.setFromUser(from);
		session.save(notes);
	}

	@Override
	public void UpdateOrder(Order order) {

		Session session = sessionFactory.getCurrentSession();

		Quotation quotation = session.load(Quotation.class, order.getQuotation().getId());

		order.setQuotation(quotation);
		session.update(order);

	}

	@Override
	public void UpdateTotalInQuotation(int id, double orderTotal1) {

		Session session = sessionFactory.getCurrentSession();
		Query<Quotation> query = session
				.createNativeQuery("update quotation set totalAmount = :totalAmount where id = :id", Quotation.class);
		query.setParameter("totalAmount", orderTotal1);
		query.setParameter("id", id);

		query.executeUpdate();

	}

	@Override
	public String GetUmobileCityByID(int cityId) {
		Session session = sessionFactory.getCurrentSession();
		Query<UmobileTown> query = session.createQuery("from UmobileTown where value = :cityId", UmobileTown.class);
		query.setParameter("cityId", String.valueOf(cityId));
		UmobileTown city = query.getSingleResult();
		return city.getName();
	}

	@Override
	public String GetUmobileStateByID(int stateId) {

		Session session = sessionFactory.getCurrentSession();
		Query<UmobileState> query = session.createQuery("from UmobileState where value = :stateId", UmobileState.class);
		query.setParameter("stateId", String.valueOf(stateId));

		UmobileState state = query.getSingleResult();

		return state.getName();
	}

	@Override
	public Object AddQuotationUrl(String quotationFilePath, String quotationName, int quotationId) {
		// TODO Auto-generated method stub

		try {
			Session session = sessionFactory.getCurrentSession();
			QuotationPDF newQuotationPDF = new QuotationPDF(quotationName, quotationFilePath, quotationId);

			ArrayList<QuotationPDF> existingQuotationPDF = (ArrayList<QuotationPDF>) session
					.createQuery("FROM QuotationPDF WHERE quotationId = :quotationId")
					.setParameter("quotationId", quotationId).getResultList();

			if (existingQuotationPDF.size() == 0) {
				session.save(newQuotationPDF);
			} else {
				session.createQuery("DELETE FROM QuotationPDF WHERE quotationId = :quotationId")
						.setParameter("quotationId", quotationId).executeUpdate();
				session.save(newQuotationPDF);
			}

			ArrayList<QuotationPDF> savedQuotationPDF = (ArrayList<QuotationPDF>) session
					.createQuery("FROM QuotationPDF WHERE quotationId = :quotationId")
					.setParameter("quotationId", quotationId).getResultList();

			return new CommonResponseData("0000", "Success", savedQuotationPDF);
		} catch (Exception e) {
			return new CommonResponseData("0001", "Unable get quotation list", null);
		}

	}

	// rk added
	@Override
	public Object AddProfomaUrl(String quotationFilePath, String quotationName, int quotationId) {
		// TODO Auto-generated method stub

		try {
			Session session = sessionFactory.getCurrentSession();

			Profoma newProfoma = new Profoma();
			newProfoma.setProfomaName(quotationFilePath);
			newProfoma.setProfomapath(quotationName);
			newProfoma.setQuotationid(quotationId);

			ArrayList<Profoma> existingQuotationPDF = (ArrayList<Profoma>) session
					.createQuery("FROM Profoma WHERE QuotationId = :quotationId")
					.setParameter("quotationId", quotationId).getResultList();

			if (existingQuotationPDF.size() == 0) {
				session.save(newProfoma);
			} else {
				session.createQuery("DELETE FROM Profoma WHERE QuotationId = :quotationId")
						.setParameter("quotationId", quotationId).executeUpdate();
				session.save(newProfoma);
			}

			ArrayList<QuotationPDF> savedQuotationPDF = (ArrayList<QuotationPDF>) session
					.createQuery("FROM Profoma WHERE quotationId = :quotationId")
					.setParameter("quotationId", quotationId).getResultList();

			return new CommonResponseData("0000", "Success", savedQuotationPDF);
		} catch (Exception e) {
			return new CommonResponseData("0001", "Unable get quotation list", null);
		}

	}

	@Override
	public Object getQuotationPdf(int quotationId) {
		try {
			Session session = sessionFactory.getCurrentSession();

			Object quotationPdfList = session.createQuery("FROM QuotationPDF WHERE quotationId = :quotationId")
					.setParameter("quotationId", quotationId).uniqueResult();

			if (quotationPdfList != null) {
				return new CommonResponseData("0000", "Success", quotationPdfList);
			} else {
				return new CommonResponseData("0000", "Quotation not issued yet.", null);
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.info("getQuotationPdf Exception -> " + e.getMessage());
			return new CommonResponseData("0001", "Unable to get quotation pdf, please try again", null);
		}
	}

	// rk added
	@Override
	public Object getProfomaPdf(int quotationId) {
		try {
			Session session = sessionFactory.getCurrentSession();

			Object quotationPdfList = session.createQuery("FROM Profoma WHERE QuotationId = :quotationId")
					.setParameter("quotationId", quotationId).uniqueResult();

			if (quotationPdfList != null) {
				return new CommonResponseData("0000", "Success", quotationPdfList);
			} else {
				return new CommonResponseData("0000", "Profoma not issued yet.", null);
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.info("getProfomaPdf Exception -> " + e.getMessage());
			return new CommonResponseData("0001", "Unable to get Profoma pdf, please try again", null);
		}
	}

	@Override
	public Object issueQuotation(String issuedQuotationPath, String issuedQuotationName, int id) {
		try {

			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(new IssuedQuotation(id, issuedQuotationPath, issuedQuotationName));
			IssueQuotationResponseDataModel response = new IssueQuotationResponseDataModel();
			response.setFileName(issuedQuotationName);
			response.setIssuedQuotationPath(issuedQuotationPath);

			return new CommonResponseData("0000", "Success", response);
		} catch (Exception e) {
			logger.info("issueQuotation -> " + e.getMessage());
			return new CommonResponseData("0001", "Something went wrong, please try again", null);
		}
	}

	// rk added
	@Override
	public Object issueProfoma(String issuedQuotationPath, String issuedQuotationName, int id) {
		try {

			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(new IssuedProfoma(id, issuedQuotationPath, issuedQuotationName));
			IssueQuotationResponseDataModel response = new IssueQuotationResponseDataModel();
			response.setFileName(issuedQuotationName);
			response.setIssuedQuotationPath(issuedQuotationPath);

			return new CommonResponseData("0000", "Success", response);
		} catch (Exception e) {
			logger.info("issueQuotation -> " + e.getMessage());
			return new CommonResponseData("0001", "Something went wrong, please try again", null);
		}
	}

	@Override
	public Object getQuotationHistory(String quotationId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			List<IssuedQuotation> arrayList = session
					.createQuery("FROM IssuedQuotation WHERE quotationId = :quotationId ORDER BY createdAt DESC")
					.setParameter("quotationId", Integer.parseInt(quotationId)).getResultList();

			return new CommonResponseData("0000", "Success", arrayList);
		} catch (Exception e) {
			logger.info("getQuotationHistory -> " + e.getMessage());
			return new CommonResponseData("0001", "Something went wrong, please try again", null);
		}
	}

	public List<BusinessType> GetBusinessTypeList() {
		Session session = sessionFactory.getCurrentSession();
		Query<BusinessType> query = session.createQuery("from BusinessType", BusinessType.class);
		List<BusinessType> businessTypes = query.getResultList();

		return businessTypes;
	}

	@Override
	public void SaveOrderLine(OrderLines line, int productId, int quotationId, int quotationMDRRateId,
			int quotationEzysplitMDRRateId) {
		Session session = sessionFactory.getCurrentSession();
		Product product = session.load(Product.class, productId);
		Quotation quotation = session.load(Quotation.class, quotationId);

		if (quotationMDRRateId == 0) {
			QuotationEzySplitMDRRate ezySplitMDRRate = session.load(QuotationEzySplitMDRRate.class,
					quotationEzysplitMDRRateId);
			line.setQuotationEzysplitMDRRate(ezySplitMDRRate);
		} else {
			QuotationMDRRate mdrRate = session.load(QuotationMDRRate.class, quotationMDRRateId);
			line.setQuotationMDRRate(mdrRate);
		}

		line.setProduct(product);
		line.setQuotation(quotation);

		session.update(line);
	}

	@Override
	public List<MasterMerchant> GetMasterMerchants() {
		Session session = sessionFactory.getCurrentSession();
		Query<MasterMerchant> query = session.createQuery("from MasterMerchant", MasterMerchant.class);
		List<MasterMerchant> masterMerchants = query.getResultList();

		return masterMerchants;
	}

	@Override
	public String GetBusinessTypeByID(String businessType) {
		Session session = sessionFactory.getCurrentSession();
		Query<BusinessType> query = session.createQuery("from BusinessType where value = :businessType",
				BusinessType.class);
		query.setParameter("businessType", businessType);

		BusinessType businessType2 = query.getSingleResult();

		return businessType2.getName();
	}

	@Override
	public CompanyType GetCompanyTypeByID(String companyType) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<CompanyType> query = session.createQuery("from CompanyType where value = :companyType",
					CompanyType.class);
			query.setParameter("companyType", companyType);
			CompanyType company = query.uniqueResult();
			return company;
		} catch (Exception e) {
			logger.error("GetCompanyTypeByID >> ", e);
			return null;
		}
	}

	@Override
	public String GetNatureOfBusinessByID(int natureOfBusiness) {
		Session session = sessionFactory.getCurrentSession();
		Query<NatureOfBusiness> query = session.createQuery("from NatureOfBusiness where value = :natureOfBusiness",
				NatureOfBusiness.class);
		query.setParameter("natureOfBusiness", String.valueOf(natureOfBusiness));

		NatureOfBusiness nob = query.getSingleResult();

		return nob.getName();
	}

	@Override
	public String GetAccountTypeByID(int accountType) {
		Session session = sessionFactory.getCurrentSession();
		Query<AccountType> query = session.createQuery("from AccountType where value = :accountType",
				AccountType.class);
		query.setParameter("accountType", String.valueOf(accountType));

		AccountType account = query.getSingleResult();

		return account.getName();
	}

	@Override
	public String GetEcommIndustryByValue(int ECommIndustry) {
		Session session = sessionFactory.getCurrentSession();
		Query<UmobileECommIndustry> query = session
				.createQuery("from UmobileECommIndustry where value = :ECommIndustry", UmobileECommIndustry.class);
		query.setParameter("ECommIndustry", String.valueOf(ECommIndustry));

		UmobileECommIndustry ecomm = query.getSingleResult();

		return ecomm.getName();
	}

	@Override
	public String GetVisaMCCByValue(int visaMCC) {
		Session session = sessionFactory.getCurrentSession();
		Query<UmobileMCC> query = session.createQuery("from UmobileMCC where value = :visaMCC", UmobileMCC.class);
		query.setParameter("visaMCC", String.valueOf(visaMCC));

		UmobileMCC mcc = query.getSingleResult();

		return mcc.getName();
	}

	@Override
	public List<Order> GetOrdersByRole(String loggedUserRole) {
		Session session = sessionFactory.getCurrentSession();
		Query<Order> query = session.createQuery("from Order where quotation.stage = :loggedUserRole", Order.class);
		query.setParameter("loggedUserRole", loggedUserRole);
		logger.info(loggedUserRole);
		List<Order> orders = query.getResultList();
		return orders;
	}

	@Override
	@Transactional
	public int MoveToStage(String stage, int quotationId) {

		try {
			Session session = sessionFactory.getCurrentSession();

			Quotation currentQuotation = (Quotation) session.createQuery("FROM Quotation WHERE id =:quotationId")
					.setParameter("quotationId", quotationId).uniqueResult();

			return moveStage(stage, quotationId);

			/*
			 * if (stage.equals("finance")) { if (currentQuotation != null) {
			 * logger.info("finance quotation is not empty"); if
			 * (currentQuotation.getPayment() != null &&
			 * currentQuotation.getPayment().getCollectedOn() != null &&
			 * currentQuotation.getPayment().getCollectedOn().isEmpty()) { return
			 * moveStage(stage, quotationId); } else {
			 * logger.info("finance payment collected on is empty"); return 0; } } else {
			 * logger.info("finance quotation is empty"); return 0; } } else { if
			 * (currentQuotation != null) { logger.info("quotation is not empty"); if
			 * (currentQuotation.getPayment() != null &&
			 * currentQuotation.getPayment().getCollectedOn() != null &&
			 * currentQuotation.getPayment().getCollectedOn().isEmpty()) { return
			 * moveStage(stage, quotationId); } else {
			 * logger.info("finance payment collected on is empty"); return 0; } } else {
			 * logger.info("quotation is empty"); return 0; } }
			 */
			// if (currentQuotation != null) {
			// if (currentQuotation.getPayment() != null &&
			// currentQuotation.getPayment().getCollectedOn() != null
			// && currentQuotation.getPayment().getCollectedOn() != "") {
			//
			// logger.info("Collected Date is not null");
			//
			// Query<Quotation> query = session.createNativeQuery(
			// "update quotation set stage = :stage where id = :quotationId",
			// Quotation.class);
			// query.setParameter("stage", stage);
			// query.setParameter("quotationId", quotationId);
			// query.executeUpdate();
			//
			// return 1;
			//
			// } else {
			// logger.info("Collected Date is null");
			// session.close();
			// return 0;
			// }
			// } else {
			// logger.info("Quotation is null");
			// session.close();
			// return 0;
			// }
			// } else {
			// Query<Quotation> query = session.createNativeQuery(
			// "update quotation set stage = :stage where id = :quotationId",
			// Quotation.class);
			// query.setParameter("stage", stage);
			// query.setParameter("quotationId", quotationId);
			// query.executeUpdate();
			// return 1;
			// }

		} catch (Exception e) {
			logger.error("MoveToStage >> ", e);
			return 0;
		}
	}

	@Override
	public SalesPerson GetSalesHead() {
		Session session = sessionFactory.getCurrentSession();
		Query<SalesPerson> query = session.createQuery("from SalesPerson where salesHead = 1", SalesPerson.class);
		SalesPerson salesPersonHead = query.getSingleResult();

		return salesPersonHead;
	}

	@Override
	public List<Quotation> GetQuotationsByStage(String stage) {

		List<Quotation> quotationsByStage = new ArrayList<Quotation>();
		try {

			Session session = sessionFactory.getCurrentSession();
			Query<Quotation> query = session.createQuery("from Quotation where stage = :stage", Quotation.class);
			query.setParameter("stage", stage);
			quotationsByStage = query.getResultList();

		} catch (NoResultException nre) {
			logger.error("No Quotations found for the Stage : " + stage);
		}

		return quotationsByStage;
	}

	@Override
	public SalesPerson GetSalesPersonByPhoneNumber(String phoneNumber) {

		try {
			Session session = sessionFactory.getCurrentSession();
			Query<SalesPerson> query = session.createQuery("from SalesPerson where phone = :phone", SalesPerson.class);
			query.setParameter("phone", phoneNumber);
			SalesPerson salesPerson = query.getSingleResult();
			return salesPerson;
		} catch (NoResultException nre) {
			logger.error("No Salesperson Found for the PhoneNumber - " + phoneNumber + " : ", nre);
			SalesPerson salesPerson = null;
			return salesPerson;

		} catch (Exception e) {
			logger.error("Error in Getting Salesperson By Phone Number : ", e);
			SalesPerson salesPerson = null;
			return salesPerson;

		}

	}

	@Override
	public Payment GetPaymentByID(String paymentId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Payment> query = session.createQuery("from Payment where id = :paymentId", Payment.class);
		query.setParameter("paymentId", Integer.parseInt(paymentId));
		Payment payment = query.getSingleResult();

		return payment;
	}

	@Override
	public List<Salutation> GetSalutationList() {
		Session session = sessionFactory.getCurrentSession();
		Query<Salutation> query = session.createQuery("from Salutation", Salutation.class);
		List<Salutation> salutations = query.getResultList();

		return salutations;
	}

	@Override
	public String UpdateQuotationAcceptanceIDInQuotation(int quotationAcceptanceID, String quotationId) {
		try {

			logger.info("quotationAcceptanceID >> " + quotationAcceptanceID);
			logger.info("quotationId >> " + quotationId);

			Session session = sessionFactory.getCurrentSession();
			Query<Quotation> query = session
					.createNativeQuery(
							"UPDATE quotation set " + "quotationAcceptanceId = :quotationAcceptanceId,"
									+ "quotationAccepted = :quotationAccepted " + "where id = :quotationId",
							Quotation.class);
			query.setParameter("quotationAcceptanceId", quotationAcceptanceID);
			query.setParameter("quotationAccepted", 1);
			query.setParameter("quotationId", Integer.parseInt(quotationId));
			query.executeUpdate();
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}

	}

	@Override
	public String UpdateWelcomeLetterAcceptanceIDInQuotation(int welcomeLetterAcceptanceId, String quotationId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Quotation> query = session.createNativeQuery(
					"update quotation set welcomeLetterAcceptanceId = :welcomeLetterAcceptanceId, welcomeLetterAccepted =:welcomeLetterAccepted where id = :quotationId",
					Quotation.class);
			query.setParameter("welcomeLetterAcceptanceId", welcomeLetterAcceptanceId);
			query.setParameter("welcomeLetterAccepted", 1);
			query.setParameter("quotationId", Integer.parseInt(quotationId));
			query.executeUpdate();
			return "Success";
		} catch (Exception e) {
			logger.error("Error in Updating Welcome Letter AcceptanceID in Quotation - ", e);
			return "Error";
		}
	}

	@Override
	public int SaveQuotationEzysplitMDRRate(QuotationEzySplitMDRRate quoteEzysplitRate, int productId,
			int quotationId) {
		Session session = sessionFactory.getCurrentSession();

		Product product = session.load(Product.class, productId);
		Quotation quotation = session.load(Quotation.class, quotationId);

		quoteEzysplitRate.setHostType(product.getHostType());
		quoteEzysplitRate.setIncludeWallet(product.getIncludeWallet());
		quoteEzysplitRate.setPayLater(product.getPayLater());

		quoteEzysplitRate.setProductName(product.getProductName());
		quoteEzysplitRate.setProductType(product.getProductType());
		quoteEzysplitRate.setShowInQuote(product.getShowInQuote());
		quoteEzysplitRate.setSubscription(String.valueOf(product.getSubscription()));
		quoteEzysplitRate.setUnitPrice(product.getUnitPrice());
		// quoteRate.setProduct(product);
		quoteEzysplitRate.setQuotation(quotation);

		int quotationEzysplitMdrRateId = (int) session.save(quoteEzysplitRate);
		return quotationEzysplitMdrRateId;
	}

	@Override
	public void AddOrderLineForEzysplit(int productId, int quotationId, int quantity, int quotationEzysplitMDRRateId) {

		Session session = sessionFactory.getCurrentSession();
		Product product = session.load(Product.class, productId);
		Quotation quotation = session.load(Quotation.class, quotationId);
		QuotationEzySplitMDRRate mdrRate = session.load(QuotationEzySplitMDRRate.class, quotationEzysplitMDRRateId);

		LocalDateTime now = LocalDateTime.now();
		OrderLines line = new OrderLines();

		line.setCreatedOn(now);
		line.setProduct(product);
		line.setQuotation(quotation);
		line.setQuotationEzysplitMDRRate(mdrRate);
		line.setQuantity(quantity);

		session.save(line);
	}

	@Override
	public void UpdateQuotationEzysplitMDRRate(QuotationEzySplitMDRRate quoteEzysplitRate, int productId,
			int quotationid) {
		Session session = sessionFactory.getCurrentSession();

		// Product product = session.load(Product.class, productId);
		Quotation quotation = session.load(Quotation.class, quotationid);

		// quoteRate.setProduct(product);
		quoteEzysplitRate.setQuotation(quotation);

		logger.info(quoteEzysplitRate.getId());

		session.update(quoteEzysplitRate);
	}

	@Override
	@Transactional
	public Payment getPaymentReceiptByQuotationId(String quotationId) {
		try {
			Session session = sessionFactory.getCurrentSession();

			Payment payment = (Payment) session.createQuery("FROM Payment WHERE quotationId = :quotationId")
					.setParameter("quotationId", quotationId).uniqueResult();

			return payment;
		} catch (Exception e) {
			logger.error("Failed to get Payment Receipt By QuotationID - ", e);
			return null;
		}
	}

	@Override
	@Transactional
	public Order GetOrderByQuotationId(int quotationid) {
		Session session = sessionFactory.getCurrentSession();
		Query<Order> query = session.createNativeQuery("select * from orders where quotation_id = :quotationid",
				Order.class);
		query.setParameter("quotationid", quotationid);
		Order order = query.getSingleResult();
		return order;
	}

	@Override
	@Transactional
	public int UpdateWelcomeLetterPath(String welcomeLetterResourcePath, int quotationId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.createNativeQuery(
					"UPDATE quotation SET welcomeLetterPath = :welcomeLetterResourcePath WHERE id=:quotationId")
					.setParameter("welcomeLetterResourcePath", welcomeLetterResourcePath)
					.setParameter("quotationId", quotationId).executeUpdate();
			return 1;
		} catch (Exception e) {
			logger.error("Failed to update Welcome Letter Path - ", e);
			return -1;
		}
	}

	@Override
	public SalesPerson GetSalesPersonByHubspotID(String hubspotOwnerId) {

		SalesPerson salesPerson = null;

		try {

			Session session = sessionFactory.getCurrentSession();
			Query<SalesPerson> query = session.createNativeQuery(
					"select * from SalesPerson where hubspotOwnerId = :hubspotOwnerId", SalesPerson.class);
			query.setParameter("hubspotOwnerId", hubspotOwnerId);

			salesPerson = query.getSingleResult();
			return salesPerson;

		} catch (NoResultException nre) {
			logger.info("Hubspot Owner ID is not found for the SalesPerson");
			return salesPerson;
		}

	}

	@Override
	public int updateMMAPath(String newMMAResourcePath, int quotationId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			int result = session
					.createNativeQuery("UPDATE quotation SET mmaPath = :newMMAResourcePath WHERE id=:quotationId")
					.setParameter("newMMAResourcePath", newMMAResourcePath).setParameter("quotationId", quotationId)
					.executeUpdate();

			if (result == 0) {
				return 0;
			} else
				return result;
		} catch (Exception e) {
			logger.error("Failed to update MMA Path - ", e);
			return -1;
		}
	}

	public void SaveWalletRate(WalletRate walletRate) {
		Session session = sessionFactory.getCurrentSession();

		Date now = new Date();
		walletRate.setCreatedOn(now);
		session.save(walletRate);
	}

	@Override
	public List<WalletRate> GetWalletRates() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<WalletRate> theQuery = currentSession.createQuery("from WalletRate", WalletRate.class);
		List<WalletRate> walletRates = theQuery.getResultList();

		return walletRates;
	}

	@Override
	public List<WalletRate> GetWalletRates(String productType) {
		Session session = sessionFactory.getCurrentSession();
		Query<WalletRate> query = session.createNativeQuery("select * from WalletRate where productType = :productType",
				WalletRate.class);
		query.setParameter("productType", productType);

		List<WalletRate> walletRates = query.getResultList();

		return walletRates;
	}

	@Override
	public WalletRate GetWalletRateByID(int walletRate) {
		Session currentSession = sessionFactory.getCurrentSession();
		WalletRate walletRateValues = currentSession.get(WalletRate.class, walletRate);
		return walletRateValues;
	}

	@Override
	public void updateOrderLines(List<OrderLines> orderLines) {

		Session session = sessionFactory.getCurrentSession();
		if (orderLines != null) {

			// Load OrderLines through session load and assign it to quotation
			for (OrderLines orderLine : orderLines) {
				OrderLines line = session.load(OrderLines.class, orderLine.getId());
				line.setQuantity(orderLine.getQuantity());
				logger.info("OrderLine ID : " + orderLine.getId() + " -- Quantity : " + orderLine.getQuantity());
				session.update(line);
			}
		}
	}

	@Override
	public void UpdateEmailAndNameInContact(int contactId, String email, String contactName) {
		Session session = sessionFactory.getCurrentSession();
		Query<Contact> query = session.createNativeQuery(
				"update Contact set email = :email,firstName = :contactName where id = :contactId", Contact.class);
		query.setParameter("contactId", contactId);
		query.setParameter("email", email);
		query.setParameter("contactName", contactName);

		query.executeUpdate();
	}

	@Override
	public void UpdateHostRate(HostRate hostRate) {
		Session session = sessionFactory.getCurrentSession();
		session.update(hostRate);
	}

	@Override
	public void UpdateWalletRate(WalletRate walletRate) {
		Session session = sessionFactory.getCurrentSession();
		session.update(walletRate);
	}

	@Override
	public List<Order> GetOrdersByStage(String stage) {
		Session session = sessionFactory.getCurrentSession();
		Query<Order> query = session.createQuery("from Order where quotation.stage = :stage", Order.class);
		query.setParameter("stage", "umobile");
		List<Order> orders = query.getResultList();

		return orders;
	}

	@Override
	public List<HostRate> GetHostRateHistoryList(int hostRateId) {
		String id = String.valueOf(hostRateId);
		Session session = sessionFactory.getCurrentSession();
		Query<HostRate> query = session.createQuery("from HostRate where refId = :hostRateId", HostRate.class);
		query.setParameter("hostRateId", id);

		List<HostRate> hostRates = query.getResultList();

		return hostRates;
	}

	@Override
	@Transactional
	public void saveStageMovement(StageMovement stageMovement) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(stageMovement);
		} catch (Exception e) {
			logger.error("Failed to Save Stage Movement - ", e);
		}
	}

	@Override
	@Transactional
	public Object getStageMovement(String orderId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			ArrayList<StageMovement> stageMovements = (ArrayList<StageMovement>) session
					.createQuery("FROM StageMovement WHERE orderId = :orderId")
					.setParameter("orderId", Integer.parseInt(orderId)).getResultList();

			return new CommonResponseData("0000", "Success", stageMovements);
		} catch (Exception e) {
			logger.error("Failed to get Stage Movement - ", e);
			return new CommonResponseData("0001", "Unable to get the stage details", null);
		}
	}

	@Override
	public Object getOrderNotes(String orderId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			ArrayList<OrderNotes> stageMovements = (ArrayList<OrderNotes>) session
					.createQuery("FROM OrderNotes WHERE OrderId = :orderId ORDER BY createdOn ASC")
					.setParameter("orderId", Integer.parseInt(orderId)).getResultList();

			ArrayList<OrderNotesResponseData> orderNotesResponseData = new ArrayList<>();
			for (OrderNotes orderNotes : stageMovements) {
				OrderNotesResponseData newOrderNotes = new OrderNotesResponseData();
				newOrderNotes.setId(orderNotes.getId());
				newOrderNotes.setSendDate(String.valueOf(orderNotes.getCreatedOn()));
				newOrderNotes.setFrom(orderNotes.getFromUser());
				newOrderNotes.setNotifyTo(orderNotes.getNotifyTo());
				newOrderNotes.setSubject(orderNotes.getSubject());
				newOrderNotes.setNotes(orderNotes.getNotes());
				orderNotesResponseData.add(newOrderNotes);
			}

			return new CommonResponseData("0000", "Success", orderNotesResponseData);
		} catch (Exception e) {
			logger.error("Failed to get Order Notes - ", e);
			return new CommonResponseData("0001", "Unable to get the stage details", null);
		}
	}

	@Override
	public Object deleteDirector(String orderId, String directorId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.createNativeQuery("DELETE FROM director WHERE id = :directorId AND orderId = :orderId")
					.setParameter("orderId", Integer.parseInt(orderId))
					.setParameter("directorId", Integer.parseInt(directorId)).executeUpdate();

			return new CommonResponseData("0000", "Director deleted successfully", null);
		} catch (Exception e) {
			logger.error("Failed to delete Director - ", e);
			return new CommonResponseData("0001", "Unable to delete director", null);
		}
	}

	@Override
	public Object getOrderDirector(String orderId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			ArrayList<Director> arrayDirector = (ArrayList<Director>) session
					.createQuery("FROM Director WHERE orderId = :orderId")
					.setParameter("orderId", Integer.parseInt(orderId)).getResultList();

			ArrayList<OrderDirectorResponseData> arrayList = new ArrayList<>();
			for (Director director : arrayDirector) {
				OrderDirectorResponseData data = new OrderDirectorResponseData();
				data.setId(director.getId());
				data.setName(director.getName());
				data.setDesignation(director.getDesignation());
				data.setContactNo(director.getContactNo());
				data.setNationality(director.getNationality());
				data.setIdNo(director.getIdNo());
				data.setIdType(director.getIdType());
				data.setAddress(director.getAddress());

				arrayList.add(data);
			}

			return new CommonResponseData("0000", "Success", arrayList);
		} catch (Exception e) {
			logger.error("Failed to get Order Director - ", e);
			return new CommonResponseData("0001", "Unable to get the stage details", null);
		}
	}

	public String GetNotificationListForTheStage(String stage) {

		try {

			Session session = sessionFactory.getCurrentSession();
			Query<NotificationList> query = session.createQuery("from NotificationList where stage = :stage",
					NotificationList.class);
			query.setParameter("stage", stage);

			NotificationList notificationList = query.getSingleResult();

			return notificationList.getEmail();
		} catch (Exception e) {
			logger.error("Exception in Notification Stage >> ", e);
			return "";
		}
	}

	@Override
	public String GetUserNameByPhoneNo(String phoneNo) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<UserDetail> query = session.createQuery("from UserDetail where phone = :phone", UserDetail.class);
			query.setParameter("phone", phoneNo);

			UserDetail userDetail = query.getSingleResult();

			return userDetail.getName();
		} catch (NoResultException npe) {
			return "";
		}

	}

	@Override
	public List<UmobileCountry> GetCountryList() {
		Session session = sessionFactory.getCurrentSession();
		Query<UmobileCountry> query = session.createQuery("from UmobileCountry", UmobileCountry.class);
		List<UmobileCountry> countries = query.getResultList();

		return countries;
	}

	@Override
	public String GetCountryIDByName(String countryName) {
		logger.info("GetCountryIDByName >> " + countryName);
		Session session = sessionFactory.getCurrentSession();
		Query<UmobileCountry> query = session.createQuery("from UmobileCountry where name = :countryName",
				UmobileCountry.class);
		query.setParameter("countryName", countryName);
		UmobileCountry country = query.getSingleResult();
		return country.getValue();
	}

	@Override
	public List<Quotation> GetQuotationsBySalesId(int salespersonId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Quotation> query = currentSession
				.createQuery("from Quotation where salesPerson.id = :salespersonId and stage =:stage", Quotation.class);
		query.setParameter("salespersonId", salespersonId);
		query.setParameter("stage", "sales");
		List<Quotation> quotations = query.getResultList();

		return quotations;
	}

	@Override
	@Transactional
	public QuotationAcceptance getQuotationAcceptanceByQuotationId(int id) {

		return null;
	}

	@Override
	@Transactional
	public Object updateQuotationUserId(String quotationId, String username) {
		try {
			Session session = sessionFactory.getCurrentSession();

			session.createNativeQuery("UPDATE quotation set userId=:username WHERE id=:quotationId")
					.setParameter("username", username).setParameter("quotationId", quotationId).executeUpdate();
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public Object getPendingQuotationMDRRates(String quotationId) {
		try {
			Session session = sessionFactory.getCurrentSession();

			Query query = session.createQuery("FROM PendingQuotationMDRRate WHERE quotation.id = :quotationID")
					.setParameter("quotationID", Integer.parseInt(quotationId));

			PendingQuotationMDRRate response = (PendingQuotationMDRRate) query.getSingleResult();
			if (response != null) {
				return new CommonResponseData("0000", "Success", response);
			} else {
				return new CommonResponseData("0000", "No Details Found", null);
			}
		} catch (NoResultException e) {
			e.printStackTrace();
			return new CommonResponseData("0000", "No Details Found", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonResponseData("0001", "Unable to get the details", null);
		}
	}

	@Override
	public Object getPendingQuotationEzySplitMDRRates(String quotationId) {
		try {
			Session session = sessionFactory.getCurrentSession();

			Query query = session.createQuery("FROM PendingQuotationEzySplitMDRRate WHERE quotation.id = :quotationID")
					.setParameter("quotationID", quotationId);

			PendingQuotationEzySplitMDRRate response = (PendingQuotationEzySplitMDRRate) query.getSingleResult();

			if (response != null) {
				return new CommonResponseData("0000", "Success", response);
			} else {
				return new CommonResponseData("0000", "No Details Found", null);
			}

		} catch (NoResultException e) {
			e.printStackTrace();
			return new CommonResponseData("0000", "No Details Found", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonResponseData("0001", "Unable to get the details", null);
		}
	}

	@Override
	@Transactional
	public int updatePendingQuotationMDRRates(PendingQuotationMDRRate pendingQuotationMDRRatesRequestData) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(pendingQuotationMDRRatesRequestData);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	@Override
	@Transactional
	public int updatePendingApprovalQuotation(Quotation quotation) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(quotation);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public List<Bank> GetBankList() {
		Session session = sessionFactory.getCurrentSession();
		Query<Bank> query = session.createQuery("from Bank", Bank.class);
		List<Bank> bankList = query.getResultList();

		return bankList;
	}

	@Override
	public Boolean IsCardIDExistForTheBoard(String cardID, String boardID) {

		Session currentSession = sessionFactory.getCurrentSession();
		Query<Quotation> query = currentSession
				.createQuery("from Quotation where cardID = :cardID and boardID = :boardID", Quotation.class);
		query.setParameter("cardID", cardID);
		query.setParameter("boardID", boardID);

		Quotation quotation = new Quotation();
		try {
			quotation = query.getSingleResult();
		} catch (NoResultException nre) {
			logger.info("No Card Found in DB!");
			return false;
		}

		logger.info("Card Already Imported!");
		return true;
	}

	@Override
	public SalesPerson GetSalesPersonByEmail(String ownerEmail) {
		SalesPerson salesPerson = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<SalesPerson> query = session.createQuery("from SalesPerson where email = :ownerEmail",
					SalesPerson.class);
			query.setParameter("ownerEmail", ownerEmail);
			salesPerson = query.getSingleResult();
			return salesPerson;
		} catch (NoResultException nre) {
			logger.error("No Salesperson Found for the Email - " + ownerEmail + " : ", nre);
			return salesPerson;

		} catch (Exception e) {
			logger.error("Error in Getting Salesperson By Phone Number : ", e);
			return salesPerson;

		}
	}

	@Override
	@Transactional
	public int UpdateStages(String stage, int quotationId) {

		try {
			Session session = sessionFactory.getCurrentSession();

			Quotation currentQuotation = (Quotation) session.createQuery("FROM Quotation WHERE id =:quotationId")
					.setParameter("quotationId", quotationId).uniqueResult();
			return moveStage(stage, quotationId);
			/*
			 * if (stage.equals("finance")) { if (currentQuotation != null) {
			 * logger.info("finance quotation is not empty"); if
			 * (currentQuotation.getPayment() != null &&
			 * currentQuotation.getPayment().getCollectedOn() != null &&
			 * !currentQuotation.getPayment().getCollectedOn().isEmpty()) { return
			 * moveStage(stage, quotationId); } else {
			 * logger.info("finance payment collected on is empty"); return 0; } } else {
			 * logger.info("finance quotation is empty"); return 0; } } else { if
			 * (currentQuotation != null) { logger.info("quotation is not empty"); if
			 * (currentQuotation.getPayment() != null &&
			 * currentQuotation.getPayment().getCollectedOn() != null &&
			 * !currentQuotation.getPayment().getCollectedOn().isEmpty()) {
			 * 
			 * } else { logger.info("finance payment collected on is empty"); return 0; } }
			 * else { logger.info("quotation is empty"); return 0; } }
			 */
			// if (currentQuotation != null) {
			// if (currentQuotation.getPayment() != null &&
			// currentQuotation.getPayment().getCollectedOn() != null
			// && currentQuotation.getPayment().getCollectedOn() != "") {
			//
			// logger.info("Collected Date is not null");
			//
			// Query<Quotation> query = session.createNativeQuery(
			// "update quotation set stage = :stage where id = :quotationId",
			// Quotation.class);
			// query.setParameter("stage", stage);
			// query.setParameter("quotationId", quotationId);
			// query.executeUpdate();
			//
			// return 1;
			//
			// } else {
			// logger.info("Collected Date is null");
			// session.close();
			// return 0;
			// }
			// } else {
			// logger.info("Quotation is null");
			// session.close();
			// return 0;
			// }
			// } else {
			// Query<Quotation> query = session.createNativeQuery(
			// "update quotation set stage = :stage where id = :quotationId",
			// Quotation.class);
			// query.setParameter("stage", stage);
			// query.setParameter("quotationId", quotationId);
			// query.executeUpdate();
			// return 1;
			// }

		} catch (Exception e) {
			logger.error("MoveToStage >> ", e);
			return 0;
		}
	}

	private int moveStage(String stage, int quotationId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Quotation> query = session
					.createNativeQuery("update quotation set stage = :stage where id = :quotationId", Quotation.class);
			query.setParameter("stage", stage);
			query.setParameter("quotationId", quotationId);
			query.executeUpdate();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public void SaveCallbackRequest(Callback callbackRequest) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(callbackRequest);
	}

	@Override
	public List<Callback> GetCallBackRequests() {
		Session session = sessionFactory.getCurrentSession();
		Query<Callback> query = session.createQuery("from Callback", Callback.class);
		List<Callback> callbackRequests = query.getResultList();

		return callbackRequests;
	}

	@Override
	public void UpdateUserID(int quotationId, String newUserId) {
		try {

			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("UPDATE Quotation set userId = :newUserId where Id = :quotationId");
			query.setParameter("newUserId", newUserId);
			query.setParameter("quotationId", quotationId);
			int result = query.executeUpdate();
			logger.info("Rows affected: " + result);
		} catch (Exception e) {
			logger.error("Issue in updating user id >> ", e);
		}

	}

	@Override
	public int MarkCallbackContacted(int callbackId) {
		try {

			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("UPDATE Callback set contactedFlag = 1 where Id = :callbackId");
			query.setParameter("callbackId", callbackId);
			int result = query.executeUpdate();
			logger.info("Rows affected: " + result);
			return 1;
		} catch (Exception e) {
			logger.error("Error in Marking >> ", e);
			return 0;
		}
	}

	public Object rollbackQuotation(String quotationId, RollbackRequestData rollbackRequestData) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.createNativeQuery(
					"UPDATE quotation SET stage =:stage, rollbackReason =:rollbackReason WHERE id =:quotationId")
					.setParameter("stage", rollbackRequestData.getRollbackStage())
					.setParameter("quotationId", quotationId)
					.setParameter("rollbackReason", rollbackRequestData.getRollbackReason()).executeUpdate();

			return new CommonResponseData("0000", "Success", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new CommonResponseData("0001", "Unable to rollback, something went wrong", null);
	}

	public Object updateOrderDirector(String orderId, OrderDirectorResponseData requestData) {
		try {
			Session session = sessionFactory.getCurrentSession();
			/*
			 * Director newDataset = new Director(); newDataset.setId(requestData.getId());
			 * newDataset.setName(requestData.getName());
			 * newDataset.setAddress(requestData.getAddress());
			 * newDataset.setIdNo(requestData.getIdNo());
			 * newDataset.setIdType(requestData.getIdType());
			 * newDataset.setNationality(requestData.getNationality());
			 * newDataset.setDesignation(requestData.getDesignation());
			 */
			session.createNativeQuery(
					"UPDATE director SET name = :name,address=:address,designation=:designation,idNo =:idNo,idType=:idType,contactNo=:contactNo,nationality =:nationality WHERE id=:id")
					.setParameter("name", requestData.getName()).setParameter("address", requestData.getAddress())
					.setParameter("designation", requestData.getDesignation())
					.setParameter("idNo", requestData.getIdNo()).setParameter("idType", requestData.getIdType())
					.setParameter("contactNo", requestData.getContactNo())
					.setParameter("nationality", requestData.getNationality()).setParameter("id", requestData.getId())
					.executeUpdate();
			/* session.update(newDataset); */

			return new CommonResponseData("0000", "Director Updated Successfully", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonResponseData("0001", "Unable to update director, please try again!", null);
		}

	}

	@Override
	public Object applyDiscount(String quotationId, DiscountPriceRequestData discountPriceRequestData) {
		try {
			Session session = sessionFactory.getCurrentSession();

			session.createNativeQuery(
					"UPDATE quotation set discountReason = :discountReason, discountPrice =:discountPrice WHERE id =:quotationId")
					.setParameter("quotationId", quotationId)
					.setParameter("discountReason", discountPriceRequestData.getDiscountReason())
					.setParameter("discountPrice", discountPriceRequestData.getDiscount()).executeUpdate();

			return new CommonResponseData("0000", "Success", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new CommonResponseData("0001", "Unable to apply discount", null);
	}

	@Override
	public Quotation getQuotationByUserName(String mSISDN) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Quotation quotation = session.createQuery("FROM Quotation WHERE userId =:userId", Quotation.class)
					.setParameter("userId", mSISDN).uniqueResult();

			if (quotation != null) {
				return quotation;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Object updateQuoatationSalesPersonId(String salesPersonId, String quotationId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Quotation quotation = session.createQuery("FROM Quotation WHERE id =:quotationId", Quotation.class)
					.setParameter("quotationId", Integer.valueOf(quotationId)).uniqueResult();

			if (quotation != null) {
				return updateQuotationSalesPerson(quotation, salesPersonId);
			} else {
				return new CommonResponseData("0001", "Quotation not availabl, please check the quoatation id", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonResponseData("0001", "something went wrong, please trya again", null);
		}
	}

	private Object updateQuotationSalesPerson(Quotation quotation, String salesPersonId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.createNativeQuery("UPDATE quotation set salesPersonID =:salesPersonID WHERE id =:quotationID",
					Quotation.class).setParameter("quotationID", quotation.getId())
					.setParameter("salesPersonID", Integer.valueOf(salesPersonId)).executeUpdate();

			return new CommonResponseData("0001", "Quotation Sales person updated", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonResponseData("0001", "something went wrong, please trya again", null);
		}
	}
}