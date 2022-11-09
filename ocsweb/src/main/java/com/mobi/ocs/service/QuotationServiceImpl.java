package com.mobi.ocs.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.mobi.ocs.dao.MerchantDAO;
import com.mobi.ocs.dao.QuotationDAO;
import com.mobi.ocs.dto.CommonResponseData;
import com.mobi.ocs.dto.HubSpotInfo;
import com.mobi.ocs.dto.IDResponse;
import com.mobi.ocs.dto.IssueQuotationResponseDataModel;
import com.mobi.ocs.dto.ReceiptResponseData;
import com.mobi.ocs.dto.ServiceResponse;
import com.mobi.ocs.dto.kanban.KanbanCardUpdate;
import com.mobi.ocs.dto.kanban.KanbanCustomFields;
import com.mobi.ocs.dto.kanban.KanbanInfo;
import com.mobi.ocs.entity.*;
import com.mobi.ocs.entity.dotnetOcs.MerchantDetails;
import com.mobi.ocs.modal.AcceptQuotationRequestData;
import com.mobi.ocs.modal.DiscountPriceRequestData;
import com.mobi.ocs.modal.MerchantQuotationDetailsResponseModel;
import com.mobi.ocs.modal.OrderDirectorResponseData;
import com.mobi.ocs.modal.PaymentCollectRequestData;
import com.mobi.ocs.modal.PendingQuotationMDRRatesRequestData;
import com.mobi.ocs.modal.RollbackRequestData;
import com.mobi.ocs.utilities.*;
import com.mobi.ocs.utilities.smtp.SmtpEmail;
import com.mobi.ocs.utilities.smtp.SmtpEmailClient;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.apache.xmpbox.type.IntegerType;
import org.hibernate.Session;
import org.springframework.aop.config.AbstractInterceptorDrivenBeanDefinitionDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.BouncyCastleAesCbcBytesEncryptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.io.File;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

@Service
@Transactional
@PropertySource("classpath:resourcePaths.properties")
public class QuotationServiceImpl implements QuotationService {

	@Autowired
	private QuotationDAO quotationDAO;

	@Autowired
	private MerchantDAO merchantDAO;

	@Autowired
	private GeneratePDFService generatePDFService;

	// set up variable to hold the properties
	@Autowired
	private Environment env;

	protected static Logger logger = Logger.getLogger(QuotationServiceImpl.class);

	@Transactional
	public List<Quotation> getQuotations() {
		return quotationDAO.getQuotations();
	}

	@Transactional
	public void saveQuotation(Quotation quotation, int salespersonId) {
		quotationDAO.saveQuotation(quotation, salespersonId);

	}

	@Transactional
	public Quotation getQuotationByID(int quotationID) {
		return quotationDAO.getQuotationByID(quotationID);
	}

	@Transactional
	public void deleteQuotation(String quotationID) {
		quotationDAO.deleteQuotation(quotationID);

	}

	@Transactional
	public List<OrderType> getOrderTypes() {
		return quotationDAO.getOrderTypes();

	}

	public String GetContactORCompanyFromHubSpot(String dealID, int ContactORCompany) {

		String urlString = null;
		StringBuffer urlStringBuffer = new StringBuffer();

		String requrl = "https://api.hubapi.com/crm-associations/v1/associations/" + dealID + "/HUBSPOT_DEFINED/"
				+ ContactORCompany;
		urlStringBuffer.append(requrl);
//		urlStringBuffer.append("?hapikey=90ef7c95-4391-4f69-bfdb-125740b7c08b");
		urlString = urlStringBuffer.toString();
		String response1 = "";
		logger.info("urlString : " + urlString);
		String line1 = null;

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(urlString);
		request.setHeader("Authorization", "Bearer " + "pat-na1-a4746b40-b0ed-4930-ab33-84f60c1c7c1a");
		try {
			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				try (InputStream stream = entity.getContent()) {
					BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

					ObjectMapper mapper = new ObjectMapper();
					IDResponse idResponse = mapper.readValue(reader, IDResponse.class);

					for (String id : idResponse.getResults()) {
						response1 = id;
						logger.info("Contact ID/Company Name : " + response1);
					}
				}
			}
			return response1;
		} catch (IOException e) {

			logger.error("Stacktrace : ", e);
			return response1;
		}

	}

	public HubSpotInfo GetContactInfoFromHubSpot(String contactID) {
		String urlString = null;
		StringBuffer urlStringBuffer = new StringBuffer();
		HubSpotInfo contactInfo = new HubSpotInfo();

		String requrl = "https://api.hubapi.com/crm/v3/objects/contacts/";
		urlStringBuffer.append(requrl);
		urlStringBuffer.append(contactID);
		urlStringBuffer.append(
				"?properties=lastname,firstname,email&archived=false&hapikey=90ef7c95-4391-4f69-bfdb-125740b7c08b");
		urlString = urlStringBuffer.toString();

		logger.info("urlString : " + urlString);
		String line1 = null;

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(urlString);
		try {
			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				try (InputStream stream = entity.getContent()) {
					BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

					ObjectMapper mapper = new ObjectMapper();
					contactInfo = mapper.readValue(reader, HubSpotInfo.class);

				}
			}
			return contactInfo;
		} catch (IOException e) {

			logger.error("Stacktrace : ", e);
			return contactInfo;
		}

	}

	public void saveContact(Contact contact) {
		quotationDAO.saveContact(contact);

	}

	public void ShowWarning(String Message, String AlertType) {
		JFrame f;
		f = new JFrame();
		JOptionPane.showMessageDialog(f, Message, AlertType, JOptionPane.WARNING_MESSAGE);

	}

	public HubSpotInfo GetCompanyInfoFromHubSpot(String companyID) {

		String urlString = null;
		StringBuffer urlStringBuffer = new StringBuffer();
		HubSpotInfo companyInfo = new HubSpotInfo();

		String requrl = "https://api.hubapi.com/companies/v2/companies/";
		urlStringBuffer.append(requrl);
		urlStringBuffer.append(companyID);
//		urlStringBuffer.append("?hapikey=90ef7c95-4391-4f69-bfdb-125740b7c08b");
		urlString = urlStringBuffer.toString();

		logger.info("urlString : " + urlString);
		String line1 = null;

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(urlString);
		request.setHeader("Authorization", "Bearer " + "pat-na1-a4746b40-b0ed-4930-ab33-84f60c1c7c1a");
		try {
			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				try (InputStream stream = entity.getContent()) {
					BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

					ObjectMapper mapper = new ObjectMapper();
					companyInfo = mapper.readValue(reader, HubSpotInfo.class);

				}
			}
			return companyInfo;
		} catch (IOException e) {

			logger.error("Stacktrace : ", e);
			return companyInfo;
		}

	}

	public Boolean IsDealExist(String dealId) {
		return quotationDAO.IsDealExist(dealId);
	}

	public int SaveStandardMDRRate(StandardMDRRate rate) {
		// TODO Auto-generated method stub
		return quotationDAO.SaveStandardMDRRate(rate);
	}

	public void SaveProduct(Product product) {
		quotationDAO.SaveProduct(product);

	}

	public List<Product> GetProducts() {
		return quotationDAO.GetProducts();
	}

	public int SavePayment(Payment payment) {
		return quotationDAO.SavePayment(payment);
	}

	public void UpdatePaymentIdInQuotation(int paymentId, String quotationId) {
		quotationDAO.UpdatePaymentIdInQuotation(paymentId, quotationId);

	}

	public void CreateOrder(int quotationID) {

		try {
			logger.info("Creating Order for the Quotation ID : " + quotationID);
			Quotation quotation = getQuotationByID(quotationID);

			Order order = new Order();
			// order.setQuotation(quotation);
			order.setStage("finance");
			order.setUserId(quotation.getUserId());
			order.setBusinessName(quotation.getCompanyName());
			order.setMerchantTradingName(quotation.getCompanyName());
			order.setRegistrationId(quotation.getRegistrationNumber());
			order.setAddressLine(quotation.getAddress());
			order.setPostCode(quotation.getPostalCode());
			order.setBusinessType("0");
			// order.setCountry(quotation.getCountry());
			order.setRemarks(quotation.getRemarks());
			LocalDateTime currentDateTime = LocalDateTime.now();
			order.setCreatedOn(currentDateTime);

			// Empty fields
			order.setCountryOfBirth("");
			order.setIsGreenCardHolder("");
			order.setIsUSCitizen("");
			order.setIsUSResident("");
			order.setTaxIdUS("");

			order.setRequstedDTL("0");
			order.setRiskApprovedDTL("0");

			quotationDAO.CreateOrder(order, quotationID);

		} catch (Exception e) {
			logger.error("Stacktrace : ", e);
		}
	}

	public List<Order> getOrders() {
		return quotationDAO.getOrders();
	}

	public List<MerchantIdType> GetMerchantIdTypeList() {
		return quotationDAO.GetMerchantIdTypeList();
	}

	public List<NatureOfBusiness> GetNatureOfBusinessList() {
		return quotationDAO.GetNatureOfBusinessList();
	}

	public List<UmobileECommIndustry> GetEcommIndustryList() {
		return quotationDAO.GetEcommIndustryList();
	}

	public List<UmobileMCC> GetUmobileMCCList() {
		return quotationDAO.GetUmobileMCCList();
	}

	public List<CompanyType> GetCompanyTypeList() {
		return quotationDAO.GetCompanyTypeList();
	}

	public List<AccountType> GetAccountTypeList() {
		return quotationDAO.GetAccountTypeList();
	}

	public Order GetOrderByID(String orderID) {
		return quotationDAO.GetOrderByID(orderID);
	}

	@Override
	public List<Acquirer> GetAcquirerList() {
		return quotationDAO.GetAcquirerList();
	}

	@Override
	public List<UmobileState> GetStateList() {
		return quotationDAO.GetStateList();
	}

	@Override
	public List<UmobileTown> GetCityList() {
		return quotationDAO.GetCityList();
	}

	@Override
	public Product GetProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductType> GetProductTypes() {
		return quotationDAO.GetProductTypes();
	}

	@Override
	public List<HostType> GetHostTypes() {
		return quotationDAO.GetHostTypes();
	}

	@Override
	public List<HostRate> GetHostRates() {
		return quotationDAO.GetHostRates();
	}

	@Override
	public void SaveHostRate(HostRate hostRate) {
		quotationDAO.SaveHostRate(hostRate);

	}

	@Override
	public List<HostRate> GetHostRates(String hostType, String productType) {

		return quotationDAO.GetHostRates(hostType, productType);
	}

	@Override
	public HostRate GetHostRate(int hostRate) {
		return quotationDAO.GetHostRate(hostRate);
	}

	@Override
	public List<Product> GetProductsByType(String type) {
		return quotationDAO.GetProductsByType(type);
	}

	@Override
	public void AddOrderLine(int productId, int quotationId, int quantity, int quotationMDRRateId) {
		quotationDAO.AddOrderLine(productId, quotationId, quantity, quotationMDRRateId);

	}

	@Override
	public List<OrderLines> GetOrderLineByQuotation(int quotationId) {
		return quotationDAO.GetOrderLineByQuotation(quotationId);
	}

	@Override
	public void deleteOrderLines(String[] orderLineIDArray) {
		quotationDAO.deleteOrderLines(orderLineIDArray);
	}

	@Override
	public OrderLines getOrderLineById(int orderLineId) {
		return quotationDAO.getOrderLineById(orderLineId);
	}

	@Override
	public int SaveQuotationMDRRate(int productId, int quotationid) {

		int quotationMDRRateId = 0;
		try {
			logger.info("Saving Quotation MDR Rate for the Quotation : " + quotationid);

			Product product = GetProductById(productId);

			QuotationMDRRate quoteRate = new QuotationMDRRate();
			LocalDateTime now = LocalDateTime.now();
			// HostRate hostRate =
			// GetHostRate(product.getStandardmdrRate().getHostRate().getId());

			quoteRate.setUnitPrice(product.getUnitPrice());

			// Merchant MDR

			quoteRate.setFor_Credit_Merch_Master(product.getStandardmdrRate().getFor_Credit_Merch_Master());
			quoteRate.setFor_Credit_Merch_Union(product.getStandardmdrRate().getFor_Credit_Merch_Union());
			quoteRate.setFor_Credit_Merch_Visa(product.getStandardmdrRate().getFor_Credit_Merch_Visa());
			quoteRate.setFor_Debit_Merch_Master(product.getStandardmdrRate().getFor_Debit_Merch_Master());
			quoteRate.setFor_Debit_Merch_Union(product.getStandardmdrRate().getFor_Debit_Merch_Union());
			quoteRate.setFor_Debit_Merch_Visa(product.getStandardmdrRate().getFor_Debit_Merch_Visa());

			quoteRate.setLoc_Credit_Merch_Master(product.getStandardmdrRate().getLoc_Credit_Merch_Master());
			quoteRate.setLoc_Credit_Merch_Union(product.getStandardmdrRate().getLoc_Credit_Merch_Union());
			quoteRate.setLoc_Credit_Merch_Visa(product.getStandardmdrRate().getLoc_Credit_Merch_Visa());
			quoteRate.setLoc_Debit_Merch_Master(product.getStandardmdrRate().getLoc_Debit_Merch_Master());
			quoteRate.setLoc_Debit_Merch_Union(product.getStandardmdrRate().getLoc_Debit_Merch_Union());
			quoteRate.setLoc_Debit_Merch_Visa(product.getStandardmdrRate().getLoc_Debit_Merch_Visa());

			// Assign Host MDR

			quoteRate.setLoc_Credit_Host_Master(product.getStandardmdrRate().getLoc_Credit_Host_Master());
			quoteRate.setLoc_Credit_Host_Union(product.getStandardmdrRate().getLoc_Credit_Host_Union());
			quoteRate.setLoc_Credit_Host_Visa(product.getStandardmdrRate().getLoc_Credit_Host_Visa());
			quoteRate.setLoc_Debit_Host_Master(product.getStandardmdrRate().getLoc_Debit_Host_Master());
			quoteRate.setLoc_Debit_Host_Union(product.getStandardmdrRate().getLoc_Debit_Host_Union());
			quoteRate.setLoc_Debit_Host_Visa(product.getStandardmdrRate().getLoc_Debit_Host_Visa());

			quoteRate.setFor_Credit_Host_Master(product.getStandardmdrRate().getFor_Credit_Host_Master());
			quoteRate.setFor_Credit_Host_Union(product.getStandardmdrRate().getFor_Credit_Host_Union());
			quoteRate.setFor_Credit_Host_Visa(product.getStandardmdrRate().getFor_Credit_Host_Visa());
			quoteRate.setFor_Debit_Host_Master(product.getStandardmdrRate().getFor_Debit_Host_Master());
			quoteRate.setFor_Debit_Host_Union(product.getStandardmdrRate().getFor_Debit_Host_Union());
			quoteRate.setFor_Debit_Host_Visa(product.getStandardmdrRate().getFor_Debit_Host_Visa());

			// Calculate and assign Mobi MDR

			quoteRate.setLoc_Credit_Mobi_Master(product.getStandardmdrRate().getFor_Credit_Merch_Master()
					- product.getStandardmdrRate().getLoc_Credit_Host_Master());
			quoteRate.setLoc_Credit_Mobi_Union(product.getStandardmdrRate().getFor_Credit_Merch_Union()
					- product.getStandardmdrRate().getLoc_Credit_Host_Union());
			quoteRate.setLoc_Credit_Mobi_Visa(product.getStandardmdrRate().getFor_Credit_Merch_Visa()
					- product.getStandardmdrRate().getLoc_Credit_Host_Visa());
			quoteRate.setLoc_Debit_Host_Master(product.getStandardmdrRate().getFor_Debit_Merch_Master()
					- product.getStandardmdrRate().getLoc_Debit_Host_Master());
			quoteRate.setLoc_Debit_Mobi_Union(product.getStandardmdrRate().getFor_Debit_Merch_Union()
					- product.getStandardmdrRate().getLoc_Debit_Host_Union());
			quoteRate.setLoc_Debit_Mobi_Visa(product.getStandardmdrRate().getFor_Debit_Merch_Visa()
					- product.getStandardmdrRate().getLoc_Debit_Host_Visa());

			quoteRate.setFor_Credit_Mobi_Master(product.getStandardmdrRate().getLoc_Credit_Merch_Master()
					- product.getStandardmdrRate().getFor_Credit_Host_Master());
			quoteRate.setFor_Credit_Mobi_Union(product.getStandardmdrRate().getLoc_Credit_Merch_Union()
					- product.getStandardmdrRate().getFor_Credit_Host_Union());
			quoteRate.setFor_Credit_Mobi_Visa(product.getStandardmdrRate().getLoc_Credit_Merch_Visa()
					- product.getStandardmdrRate().getFor_Credit_Host_Visa());
			quoteRate.setFor_Debit_Mobi_Master(product.getStandardmdrRate().getLoc_Debit_Merch_Master()
					- product.getStandardmdrRate().getFor_Debit_Host_Master());
			quoteRate.setFor_Debit_Mobi_Union(product.getStandardmdrRate().getLoc_Debit_Merch_Union()
					- product.getStandardmdrRate().getFor_Debit_Host_Union());
			quoteRate.setFor_Debit_Mobi_Visa(product.getStandardmdrRate().getLoc_Debit_Merch_Visa()
					- product.getStandardmdrRate().getFor_Debit_Host_Visa());

			// Customer MDR - Not yet implemented
			quoteRate.setLoc_Credit_Cus_Master(product.getStandardmdrRate().getLoc_Credit_Cus_Master());
			quoteRate.setLoc_Credit_Cus_Union(product.getStandardmdrRate().getLoc_Credit_Cus_Union());
			quoteRate.setLoc_Credit_Cus_Visa(product.getStandardmdrRate().getLoc_Credit_Cus_Visa());

			quoteRate.setProductSettlement(product.getProductSettlement());
			quoteRate.setBoostSettlement(product.getStandardmdrRate().getBoostSettlement());
			quoteRate.setGrabSettlement(product.getStandardmdrRate().getGrabSettlement());
			quoteRate.setFpxSettlement(product.getStandardmdrRate().getFpxSettlement());

			quoteRate.setBoostMDREcomm(product.getStandardmdrRate().getBoostMDREcomm());
			quoteRate.setBoostMDRQR(product.getStandardmdrRate().getBoostMDRQR());
			quoteRate.setGrabMDREcomm(product.getStandardmdrRate().getGrabMDREcomm());
			quoteRate.setGrabMDRQR(product.getStandardmdrRate().getGrabMDRQR());
			quoteRate.setfPXMDR_Percent(product.getStandardmdrRate().getfPXMDR_Percent());
			quoteRate.setfPXMDR_RM(product.getStandardmdrRate().getfPXMDR_RM());
			// quoteRate.setHostRateIdRef(String.valueOf(product.getStandardmdrRate().getHostRate().getId()));

			quoteRate.setTngMDREcomm(product.getStandardmdrRate().getTngMDREcomm());
			quoteRate.setTngMDRQR(product.getStandardmdrRate().getTngMDRQR());
			quoteRate.setShopeepayMDREcomm(product.getStandardmdrRate().getShopeepayMDREcomm());
			quoteRate.setShopeepayMDRQR(product.getStandardmdrRate().getShopeepayMDRQR());
			quoteRate.setTngSettlement(product.getStandardmdrRate().getTngSettlement());
			quoteRate.setShopeepaySettlement(product.getStandardmdrRate().getShopeepaySettlement());

			quoteRate.setCreatedOn(now);

			quotationMDRRateId = quotationDAO.SaveQuotationMDRRate(quoteRate, productId, quotationid);
			logger.info(String.format("Quotation MDR Rate ID %s Saved for the Quotation : %s ", quotationMDRRateId,
					quotationid));
		} catch (Exception e) {
			logger.error("Stacktrace : ", e);
			logger.info("Saving Quotation MDR Rate Failed.");
		}

		return quotationMDRRateId;

	}

	@Override
	public void UpdateQuotationMDRRate(QuotationMDRRate quoteRate, int productId, int quotationid) {
		quotationDAO.UpdateQuotationMDRRate(quoteRate, productId, quotationid);
	}

	@Override
	public void updateQuotation(Quotation quote) {
		quotationDAO.updateQuotation(quote);

	}

	@Override
	public void UpdateOrderLines(List<OrderLines> orderLines) {
		quotationDAO.updateOrderLines(orderLines);

	}

	@Override
	public List<PaymentType> GetPaymentTypes() {
		return quotationDAO.GetPaymentTypes();
	}

	@Override
	public List<Acquirer> GetAcquirers() {
		return quotationDAO.GetAcquirers();
	}

	@Override
	public List<SalesPerson> GetSalesPersons() {
		return quotationDAO.GetSalesPersons();
	}

	@Override
	public void SaveDirector(Director director, String orderId) {
		quotationDAO.SaveDirector(director, orderId);
	}

	@Override
	public Product GetProductById(int productId) {
		return quotationDAO.GetProductById(productId);
	}

	@Override
	public void AddProductToLogHistory(int productId) {
		quotationDAO.AddProductToLogHistory(productId);

	}

	@Override
	public void UpdateProduct(Product product) {
		quotationDAO.UpdateProduct(product);
	}

	@Override
	public List<Product> GetProductHistoryList(int productId) {
		return quotationDAO.GetProductHistoryList(productId);
	}

	@Override
	public void SaveNotes(OrderNotes notes, String orderId, String from) {
		quotationDAO.SaveNotes(notes, orderId, from);
	}

	@Override
	public void UpdateOrder(Order order) {
		try {
			logger.info("Updating Order with Merchant US Details");
			order.setIsGreenCardHolder(order.getIsGreenCardHolder() == null ? "No" : order.getIsGreenCardHolder());
			order.setIsUSCitizen(order.getIsUSCitizen() == null ? "No" : order.getIsUSCitizen());
			order.setIsUSResident(order.getIsUSResident() == null ? "No" : order.getIsUSResident());

			quotationDAO.UpdateOrder(order);

		} catch (Exception e) {
			logger.error("Stacktrace : ", e);
		}

	}

	@Override
	public void SendEmail(String fromAddress, String toAddress, String ccAddress, String subject, String filePath,
			String fileName, String emailBody, List<Attachment> attachments) {

		try {

			logger.info("Sending Email To : " + toAddress);
			String apiKey = Constants.apiKeyPostMark;

			logger.info("Sending Email to :" + toAddress + " : " + ccAddress);

			if (!filePath.equals("")) {

				Attachment fileAttach = null;
				try {

					fileAttach = new Attachment(fileName, "application/pdf", encodeFileToBase64Binary(filePath),
							"cid:" + filePath);
				} catch (Exception e) {
					logger.error("Stacktrace : ", e);
				}

				attachments.add(fileAttach);
			}

			try {
				PostmarkMessage message = new PostmarkMessage(fromAddress, toAddress, fromAddress, ccAddress, subject,
						emailBody, true, "test-email", null, attachments);
				PostmarkClient client = new PostmarkClient(apiKey);

				client.sendMessage(message);
				logger.info("Email Sent Successfully to " + toAddress);
			} catch (PostmarkException pe) {
				logger.error("Stacktrace : ", pe);

			}

		} catch (Exception e) {
			logger.error("Stacktrace : ", e);
		}

	}

	public static String encodeFileToBase64Binary(String fileName) throws IOException {

		File file = new File(fileName);
		byte[] bytes = loadFile(file);
		byte[] encoded = Base64.encodeBase64(bytes);
		String encodedString = new String(encoded);

		return encodedString;
	}

	private static byte[] loadFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);

		// long length = file.length();
		/*
		 * if (length > Integer.MAX_VALUE) { // File is too large }
		 */
		byte[] bytes = new byte[(int) file.length()];

		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		/*
		 * if (offset < bytes.length) { throw new
		 * IOException("Could not completely read file "+file.getName()); }
		 */

		is.close();
		return bytes;
	}

	@Override
	public String GetFromProperyFile(String key) {
		Properties prop = new Properties();

		String value = null;
		try (InputStream input = new FileInputStream("C:\\Deployments\\ocs\\config.properties")) {

			// ClassLoader loader = Thread.currentThread().getContextClassLoader();
			// input = loader.getResourceAsStream("/config.properties");

			prop.load(input);
			logger.info("Value from Property file :" + key + " - " + prop.getProperty(key));
			value = prop.getProperty(key);
			return value;
		} catch (IOException io) {
			logger.error("Stacktrace : ", io);
		}
		return value;
	}

	@Override
	public void UpdateTotalInQuotation(int id, double orderTotal1) {
		quotationDAO.UpdateTotalInQuotation(id, orderTotal1);
	}

	@Override
	public String FormatAmountFromDoubleToString(double unitPrice) {

		DecimalFormat myFormatter = new DecimalFormat(Constants.amountPattern);
		String output = myFormatter.format(unitPrice);
		return output;
	}

	@Override
	public String GetUmobileCityByID(int cityId) {
		logger.info("City iD : " + cityId);
		return quotationDAO.GetUmobileCityByID(cityId);
	}

	@Override
	public String GetUmobileStateByID(int stateId) {
		return quotationDAO.GetUmobileStateByID(stateId);
	}

	@Override
	public Object AddQuotationUrl(String quotationFilePath, String quotationName, int quotationId) {
		return quotationDAO.AddQuotationUrl(quotationFilePath, quotationName, quotationId);

	}

	// rk added
	@Override
	public Object AddProfomaUrl(String quotationFilePath, String quotationName, int quotationId) {
		return quotationDAO.AddProfomaUrl(quotationFilePath, quotationName, quotationId);

	}

	@Override
	public Object getQuotationPdf(int quotationId) {
		return quotationDAO.getQuotationPdf(quotationId);
	}

	// rk added
	@Override
	public Object getProfomaPdf(int quotationId) {
		return quotationDAO.getProfomaPdf(quotationId);
	}

	@Override
	public Object getQuotationHistory(String quotationId) {
		return quotationDAO.getQuotationHistory(quotationId);
	}

	@Override
	public Object insertIssuedQuotation(String issuedQuotationPath, String issuedQuotationName, int id) {
		return quotationDAO.issueQuotation(issuedQuotationPath, issuedQuotationName, id);
	}

	// rk added
	@Override
	public Object insertIssuedProfoma(String issuedQuotationPath, String issuedQuotationName, int id) {
		return quotationDAO.issueProfoma(issuedQuotationPath, issuedQuotationName, id);
	}

	@Override
	public List<BusinessType> GetBusinessTypeList() {
		return quotationDAO.GetBusinessTypeList();
	}

	@Override
	public void SaveOrderLine(OrderLines lines, int productId, int quotationId, int quotationMDRRateId,
			int quotationEzysplitMDRRateId) {
		quotationDAO.SaveOrderLine(lines, productId, quotationId, quotationMDRRateId, quotationEzysplitMDRRateId);
	}

	@Override
	public List<MasterMerchant> GetMasterMerchants() {
		return quotationDAO.GetMasterMerchants();
	}

	@Override
	public String GetBusinessTypeByID(String businessType) {
		return quotationDAO.GetBusinessTypeByID(businessType);
	}

	@Override
	public CompanyType GetCompanyTypeByID(String companyType) {
		return quotationDAO.GetCompanyTypeByID(companyType);
	}

	@Override
	public String GetNatureOfBusinessByID(int natureOfBusiness) {
		return quotationDAO.GetNatureOfBusinessByID(natureOfBusiness);
	}

	@Override
	public String GetAccountTypeByID(int accountType) {
		return quotationDAO.GetAccountTypeByID(accountType);
	}

	@Override
	public String GetEcommIndustryByValue(int ECommIndustry) {
		return quotationDAO.GetEcommIndustryByValue(ECommIndustry);
	}

	@Override
	public String GetVisaMCCByValue(int visaMCC) {
		return quotationDAO.GetVisaMCCByValue(visaMCC);
	}

	@Override
	public String GetRoleNameAsPerDB(String authority) {
		return authority.replace("[", "").replace("]", "").replace("ROLE_", "").toLowerCase();
	}

	@Override
	public List<Order> getOrdersByRole(String loggedUserRole) {
		return quotationDAO.GetOrdersByRole(loggedUserRole);
	}

	@Override
	public String MoveToNextStage(String currentStage, String acquirer, int quotationID) {

		try {

			String nextStage = "";
			logger.info(String.format("Moving from the Current Stage : %s . Acquirer : %s", currentStage, acquirer));

			switch (currentStage) {
			case "finance":
				nextStage = "processing";
				break;
			case "processing":
				nextStage = "risk";
				break;
			case "risk":
				if (acquirer.equals("U-Mobile")) {
					nextStage = "umobile";
				} else {
					nextStage = "synergy-vetdoc";
				}
				break;

			/*
			 * case "ceo": if (acquirer.equals("U-Mobile")) { nextStage = "umobile"; } else
			 * { nextStage = "synergy-vetdoc"; } break;
			 */

			case "umobile":
				nextStage = "pending-deployment";
				break;

			// Paydee Movement
			case "synergy-vetdoc":
				nextStage = "synergy-risk";
				break;
			case "synergy-risk":
				nextStage = "synergy-approval";
				break;
			case "synergy-approval":
				nextStage = "synergy-welcome";
				break;
			case "synergy-welcome":
				nextStage = "pending-deployment";
				break;
			case "pending-deployment":
				nextStage = "deployed";
				break;
			default:
				nextStage = "finance";
				break;
			}

			if (quotationDAO.MoveToStage(nextStage, quotationID) == 1) {
				logger.info(String.format("Moved to the Stage : %s ", nextStage));
				return nextStage;
			} else {
				return "";
			}

		} catch (Exception e) {
			logger.error("Stacktrace : ", e);
			return "";
		}
	}

	@Override
	public int UpdateStages(String currentStage, String acquirer, int quotationID) {

		String nextStage = "";
		logger.info(String.format("Moving from the Current Stage : %s . Acquirer : %s", currentStage, acquirer));

		switch (currentStage) {
		case "finance":
			nextStage = "processing";
			break;
		case "processing":
			nextStage = "risk";
			break;
		case "risk":
			if (acquirer.equals("U-Mobile")) {
				nextStage = "umobile";
			} else {
				nextStage = "synergy-vetdoc";
			}
			break;
		case "umobile":
			nextStage = "pending-deployment";
			break;

		// Paydee Movement
		case "synergy-vetdoc":
			nextStage = "synergy-risk";
			break;
		case "synergy-risk":
			nextStage = "synergy-approval";
			break;
		case "synergy-approval":
			nextStage = "synergy-welcome";
			break;
		case "synergy-welcome":
			nextStage = "pending-deployment";
			break;

		case "pending-deployment":
			nextStage = "deployed";
			break;
		default:
			nextStage = "finance";
			break;
		}

		return quotationDAO.UpdateStages(nextStage, quotationID);

	}

	@Override
	public List<Quotation> GetQuotationsByStage(String stage) {
		return quotationDAO.GetQuotationsByStage(stage);
	}

	@Override
	public SalesPerson GetSalesPersonByPhoneNumber(String phoneNumber) {
		return quotationDAO.GetSalesPersonByPhoneNumber(phoneNumber);
	}

	@Override
	public SalesPerson GetSalesHead() {
		return quotationDAO.GetSalesHead();
	}

	@Override
	public void UpdatePayment(Payment payment) {
		quotationDAO.UpdatePayment(payment);
	}

	@Override
	public Payment GetPaymentByID(String paymentId) {
		return quotationDAO.GetPaymentByID(paymentId);
	}

	@Override
	public List<Salutation> GetSalutationList() {
		return quotationDAO.GetSalutationList();
	}

	@Override
	public void SendQuotationIssueEmail(int quotationID, Object response, HttpServletRequest req)
			throws BadElementException, MalformedURLException, IOException {

		try {

			Quotation quotation = getQuotationByID(quotationID);
			String htmlString = "";

			// Get Email Template
			URL QuotationEmailTemplatePath = getClass().getClassLoader()
					.getResource(env.getProperty("quotationEmail.emailTemplate"));

			// Get Images for Email
			URL bannerImagePath = getClass().getClassLoader()
					.getResource(env.getProperty("quotationEmail.bannerImage"));
			URL quotationAcceptImagePath = getClass().getClassLoader()
					.getResource(env.getProperty("quotationEmail.quotationAcceptImage"));

			CommonResponseData commonResponseData = (CommonResponseData) response;
			IssueQuotationResponseDataModel issueQuotationResponseDataModel = (IssueQuotationResponseDataModel) commonResponseData
					.getResponseData();
			String quotationPDFPath = (env.getProperty("resources.basePath")
					+ issueQuotationResponseDataModel.getIssuedQuotationPath().replace("/attachments", ""));

			// Convert Image to Base64
			String bannerImageBase64String = encodeFileToBase64Binary(
					URLDecoder.decode(bannerImagePath.toString().replace("file:/", ""), "UTF-8"));
			String quotationAcceptImageBase64String = encodeFileToBase64Binary(
					URLDecoder.decode(quotationAcceptImagePath.toString().replace("file:/", ""), "UTF-8"));

			// String bannerImageBase64String =
			// env.getProperty("quotationEmail.bannerImageBase64");
			// String quotationAcceptImageBase64String =
			// env.getProperty("quotationEmail.quotationAcceptBase64");

			Attachment quotationBanner = new Attachment("quotationBanner.png", "image/png", bannerImageBase64String,
					"cid:quotationBannerBase65");
			Attachment quotationAcceptImage = new Attachment("quotationAccept.png", "image/png",
					quotationAcceptImageBase64String, "cid:quotationAcceptImageBase64");

			logger.info(quotationPDFPath.toString().replace("file:", ""));
			Attachment quotationPDF = new Attachment("Quotation.pdf", "application/pdf",
					encodeFileToBase64Binary(quotationPDFPath.toString().replace("file:/", "")), "cid:quotationPDF");

			logger.info("Email Template Path " + env.getProperty("quotationEmail.emailTemplate"));
			logger.info("Banner Image Path - " + env.getProperty("quotationEmail.bannerImage"));
			logger.info("Quotation Accept Path - " + env.getProperty("quotationEmail.quotationAcceptImage"));

//			List<Attachment> attachments = new ArrayList<Attachment>();
//			attachments.add(quotationBanner);
//			attachments.add(quotationAcceptImage);
//			attachments.add(quotationPDF);

			StringBuilder html = new StringBuilder();
			FileReader reader = new FileReader(
					URLDecoder.decode(QuotationEmailTemplatePath.toString().replace("file:", ""), "UTF-8"));

			BufferedReader br = new BufferedReader(reader);
			String val;

			while ((val = br.readLine()) != null) {
				html.append(val);
			}

			htmlString = html.toString();
			htmlString = htmlString.replace("[[merchantname]]",
					(quotation.getContact().getFirstName() == null ? "" : quotation.getContact().getFirstName()));
			htmlString = htmlString.replace("[[quotationAmount]]",
					(quotation.getTotalAmount() == null ? "0.00" : quotation.getTotalAmount()));
			htmlString = htmlString.replace("[[OCSUrl]]", env.getProperty("app.baseUrl") + "signup" + "?userId="
					+ encodeStringToBase64(quotation.getUserId()));
			logger.info(htmlString);

			br.close();

			String notificationList = quotationDAO.GetNotificationListForTheStage("sales-manager");

//			String emailBody = htmlString;
//			String fromAddress = "Info@gomobi.io";
//			String toAddress = quotation.getContact().getEmail();
//			String ccAddress = notificationList + "," + quotation.getSalesPerson().getEmail();
//			// String toAddress = "shafi@gomobi.io";
//			// String ccAddress = "";
//			String subject = "Mobi Quotation - Ref:" + quotationID;

			logger.info("Params that need to pass into api");

			String subject = "Mobi Quotation - Ref:" + quotationID;
			String bodyHtml = htmlString;
			String toAddress = quotation.getContact().getEmail() + "," + notificationList + ","
					+ quotation.getSalesPerson().getEmail();
			String ccAddress = null;
			String bccAddress = null;
			String textBody = null;
			String attachments = (env.getProperty("resources.basePath")
					+ issueQuotationResponseDataModel.getIssuedQuotationPath().replace("/attachments", ""));

			SmtpEmail send = new SmtpEmail(subject, toAddress, ccAddress, null, attachments, textBody, bodyHtml);

			logger.info("This is the object we passed into the api" + send);
			SmtpEmailClient smtp = new SmtpEmailClient();

			logger.info("Sending to Api");
			smtp.sendMessage(send);

			// SendEmail(fromAddress, toAddress, ccAddress, subject, "", "", emailBody,
			// attachments);

		} catch (Exception ex) {
			logger.error("Issue Quotation FAILED. : ", ex);
		}
	}

	// rk added
	@Override
	public void SendProfomaEmail(int quotationID, Object response, HttpServletRequest req)
			throws BadElementException, MalformedURLException, IOException {

		try {

			Quotation quotation = getQuotationByID(quotationID);
			String htmlString = "";

			// Get Email Template
			URL QuotationEmailTemplatePath = getClass().getClassLoader()
					.getResource(env.getProperty("profomaEmail.emailTemplate"));

			// Get Images for Email
			URL bannerImagePath = getClass().getClassLoader()
					.getResource(env.getProperty("quotationEmail.bannerImage"));
			URL quotationAcceptImagePath = getClass().getClassLoader()
					.getResource(env.getProperty("quotationEmail.quotationAcceptImage"));

			CommonResponseData commonResponseData = (CommonResponseData) response;
			IssueQuotationResponseDataModel issueQuotationResponseDataModel = (IssueQuotationResponseDataModel) commonResponseData
					.getResponseData();
			String quotationPDFPath = (env.getProperty("resources.basePath")
					+ issueQuotationResponseDataModel.getIssuedQuotationPath().replace("/attachments", ""));

			// Convert Image to Base64
			String bannerImageBase64String = encodeFileToBase64Binary(
					URLDecoder.decode(bannerImagePath.toString().replace("file:/", ""), "UTF-8"));
			String quotationAcceptImageBase64String = encodeFileToBase64Binary(
					URLDecoder.decode(quotationAcceptImagePath.toString().replace("file:/", ""), "UTF-8"));

			// String bannerImageBase64String =
			// env.getProperty("quotationEmail.bannerImageBase64");
			// String quotationAcceptImageBase64String =
			// env.getProperty("quotationEmail.quotationAcceptBase64");

			Attachment quotationBanner = new Attachment("quotationBanner.png", "image/png", bannerImageBase64String,
					"cid:quotationBannerBase65");
			Attachment quotationAcceptImage = new Attachment("quotationAccept.png", "image/png",
					quotationAcceptImageBase64String, "cid:quotationAcceptImageBase64");

			logger.info(quotationPDFPath.toString().replace("file:", ""));
			Attachment quotationPDF = new Attachment("Profoma.pdf", "application/pdf",
					encodeFileToBase64Binary(quotationPDFPath.toString().replace("file:/", "")), "cid:quotationPDF");

			logger.info("Email Template Path " + env.getProperty("quotationEmail.emailTemplate"));
			logger.info("Banner Image Path - " + env.getProperty("quotationEmail.bannerImage"));
			logger.info("Quotation Accept Path - " + env.getProperty("quotationEmail.quotationAcceptImage"));

//			List<Attachment> attachments = new ArrayList<Attachment>();
//			attachments.add(quotationBanner);
//			attachments.add(quotationAcceptImage);
//			attachments.add(quotationPDF);

			StringBuilder html = new StringBuilder();
			FileReader reader = new FileReader(
					URLDecoder.decode(QuotationEmailTemplatePath.toString().replace("file:", ""), "UTF-8"));

			BufferedReader br = new BufferedReader(reader);
			String val;

			while ((val = br.readLine()) != null) {
				html.append(val);
			}

			htmlString = html.toString();
			htmlString = htmlString.replace("[[merchantname]]",
					(quotation.getContact().getFirstName() == null ? "" : quotation.getContact().getFirstName()));
			htmlString = htmlString.replace("[[quotationAmount]]",
					(quotation.getTotalAmount() == null ? "0.00" : quotation.getTotalAmount()));
			htmlString = htmlString.replace("[[OCSUrl]]", env.getProperty("app.baseUrl") + "signup" + "?userId="
					+ encodeStringToBase64(quotation.getUserId()));
			logger.info(htmlString);

			br.close();

			String notificationList = quotationDAO.GetNotificationListForTheStage("sales-manager");

//			String emailBody = htmlString;
//			String fromAddress = "Info@gomobi.io";
//			String toAddress = quotation.getContact().getEmail();
//			String ccAddress = notificationList + "," + quotation.getSalesPerson().getEmail();
			// String toAddress = "ramakrishna@gomobi.io";
			// String ccAddress = "vignesh@gomobi.io";
//			String subject = "Profoma - Ref:" + quotationID;
//
//			SendEmail(fromAddress, toAddress, ccAddress, subject, "", "", emailBody, attachments);

			logger.info("Params that need to pass into api");

			String subject = "Profoma - Ref:" + quotationID;
			String bodyHtml = htmlString;
			String toAddress = quotation.getContact().getEmail() + "," + notificationList + ","
					+ quotation.getSalesPerson().getEmail();
			String ccAddress = null;
			String bccAddress = null;
			String attachments = (env.getProperty("resources.basePath")
					+ issueQuotationResponseDataModel.getIssuedQuotationPath().replace("/attachments", ""));
			String textBody = null;

			SmtpEmail send = new SmtpEmail(subject, toAddress, ccAddress, bccAddress, attachments, textBody, bodyHtml);

			logger.info("This is the object we passed into the api" + send);
			SmtpEmailClient smtp = new SmtpEmailClient();

			logger.info("Sending to Api");
			smtp.sendMessage(send);

		} catch (Exception ex) {
			logger.error("Issue Quotation FAILED. : ", ex);
		}
	}

	@Override
	public void SendWelcomeLetterEmail(String orderId) throws BadElementException, MalformedURLException, IOException {

		try {

			Order order = GetOrderByID(orderId);
			String htmlString = "";

			// Get Email Template
			URL welcomeLetterEmailTemplatePath = getClass().getClassLoader()
					.getResource(env.getProperty("welcomeEmail.emailTemplate"));

			// Get Images for Email
			URL bannerImagePath = getClass().getClassLoader().getResource(env.getProperty("welcomeEmail.bannerImage"));
			URL welcomeLetterActivateGIFPath = getClass().getClassLoader()
					.getResource(env.getProperty("welcomeEmail.welcomeLetterActivateGIF"));

			// Convert Image to Base64
			String bannerImageBase64String = encodeFileToBase64Binary(
					URLDecoder.decode(bannerImagePath.toString().replace("file:/", ""), "UTF-8"));
			String welcomeLetterActivateGIFBase64String = encodeFileToBase64Binary(
					URLDecoder.decode(welcomeLetterActivateGIFPath.toString().replace("file:/", ""), "UTF-8"));

			Attachment welcomeLetterBanner = new Attachment("welcomeLetterBanner.png", "image/png",
					bannerImageBase64String, "cid:welcomeLetterBannerBase64");
			Attachment welcomeLetterActivateGIF = new Attachment("welcomeLetterActivateGIF.gif", "image/png",
					welcomeLetterActivateGIFBase64String, "cid:welcomeLetterActivateGIFBase64");

			/*
			 * Attachment quotationPDF = new Attachment("Quotation.pdf", "application/pdf",
			 * encodeFileToBase64Binary(quotationPDFPath.toString().replace("file:/", "")),
			 * "cid:quotationPDF");
			 */

//			List<Attachment> attachments = new ArrayList<Attachment>();
//			attachments.add(welcomeLetterBanner);
//			attachments.add(welcomeLetterActivateGIF);

			StringBuilder html = new StringBuilder();
			FileReader reader = new FileReader(
					URLDecoder.decode(welcomeLetterEmailTemplatePath.toString().replace("file:", ""), "UTF-8"));
			try {
				BufferedReader br = new BufferedReader(reader);
				String val;

				while ((val = br.readLine()) != null) {
					html.append(val);
				}

				htmlString = html.toString();
				htmlString = htmlString.replace("[[merchantname]]",
						order.getAuthContactPersonName() == null ? "" : order.getAuthContactPersonName());
				htmlString = htmlString.replace("[[OCSUrl]]", env.getProperty("app.loginUrl"));
				// htmlString = htmlString.replace("[[bannerImagePath]]",
				// bannerImagePath.toString());
				// htmlString =
				// htmlString.replace("[[welcomeLetterActivateGIFPath]]",welcomeLetterActivateGIFPath.toString());
				logger.info(htmlString);

				br.close();
			} catch (Exception ex) {
				ex.printStackTrace();
				logger.error("Stacktrace : ", ex);
			}
			String notificationList = quotationDAO.GetNotificationListForTheStage("WLACCEPTANCE");

//			String emailBody = htmlString;
//
//			String fromAddress = "Info@gomobi.io";
//			// String fromAddress = notes.getFromUser();
//
//			String toAddress = order.getQuotation().getContact().getEmail();
//			// String ccAddress = notificationList;
//			String ccAddress = order.getQuotation().getSalesPerson().getEmail() + "," + notificationList;
//			String subject = "Welcome Onboard - Ref:" + orderId;
//
//			SendEmail(fromAddress, toAddress, ccAddress, subject, "", "", emailBody, attachments);

			logger.info("Params that need to pass into api");

			String subject = "Welcome Onboard - Ref:" + orderId;
			String emailBody = htmlString;
			String toAddress = order.getQuotation().getContact().getEmail() + ","
					+ order.getQuotation().getSalesPerson().getEmail() + "," + notificationList;
			String ccAddress = null;
			String bccAddress = null;
			String attachments = null;
			String textBody = null;

			SmtpEmail send = new SmtpEmail(subject, toAddress, ccAddress, bccAddress, attachments, textBody, emailBody);

			logger.info("This is the object we passed into the api" + send);
			SmtpEmailClient smtp = new SmtpEmailClient();

			logger.info("Sending to Api");
			smtp.sendMessage(send);

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Stacktrace : ", ex);
		}
	}

	@Override
	public void SendStageMovementEmail(Order order, String submittedBy, String submittedByRole, String currentStage,
			String orderViewURL) throws IOException {
		String htmlString = "";
		List<Attachment> attachments = new ArrayList<Attachment>();

		// Get Email Template
		URL stageMovementEmailTemplatePath = getClass().getClassLoader()
				.getResource(env.getProperty("stageMovementEmail.emailTemplate"));

		// Get Images for Email
		URL bannerImagePath = getClass().getClassLoader()
				.getResource(env.getProperty("stageMovementEmail.bannerImage"));
		URL goToOrderImagePath = getClass().getClassLoader()
				.getResource(env.getProperty("stageMovementEmail.goToOrderImage"));

		// Convert Image to Base64
		String bannerImageBase64String = encodeFileToBase64Binary(
				URLDecoder.decode(bannerImagePath.toString().replace("file:", ""), "UTF-8"));
		String goToOrderImageBase64String = encodeFileToBase64Binary(
				URLDecoder.decode(goToOrderImagePath.toString().replace("file:", ""), "UTF-8"));

		Attachment stageMovementBanner = new Attachment("quotationBanner.gif", "image/png", bannerImageBase64String,
				"cid:BannerImageBase64");
		Attachment goToOrderImage = new Attachment("quotationAccept", "image/png", goToOrderImageBase64String,
				"cid:GotoOrderImageBase64");

//		attachments.add(stageMovementBanner);
//		attachments.add(goToOrderImage);

		StringBuilder html = new StringBuilder();
		FileReader reader = new FileReader(
				URLDecoder.decode(stageMovementEmailTemplatePath.toString().replace("file:", ""), "UTF-8"));
		try {
			BufferedReader br = new BufferedReader(reader);
			String val;

			while ((val = br.readLine()) != null) {
				html.append(val);
			}

			htmlString = html.toString();

			htmlString = htmlString.replace("[[orderName]]", order.getBusinessName());
			htmlString = htmlString.replace("[[orderId]]", String.valueOf(order.getId()));
			htmlString = htmlString.replace("[[submittedUser]]", submittedBy);
			htmlString = htmlString.replace("[[submittedTeam]]", submittedByRole);
			htmlString = htmlString.replace("[[pendingWith]]", currentStage);
			htmlString = htmlString.replace("__OrderURL__", orderViewURL);
			logger.info(htmlString);

			br.close();
		}

		catch (Exception ex) {
			logger.error("Stacktrace : ", ex);
		}

		String notificationList = quotationDAO.GetNotificationListForTheStage(currentStage);

//		String emailBody = htmlString;
//
//		String fromAddress = "Info@gomobi.io";
//		String toAddress = notificationList;
//		String ccAddress = "";
//		String subject = "Stage Movement - Ref:" + order.getId();
//
//		SendEmail(fromAddress, toAddress, ccAddress, subject, "", "", emailBody, attachments);

		logger.info("Params that need to pass into api");

		String subject = "Stage Movement - Ref:" + order.getId();
		String emailBody = htmlString;
		String toAddress = notificationList;
		String ccAddress = null;
		String bccAddress = null;
		String textBody = null;

		SmtpEmail send = new SmtpEmail(subject, toAddress, ccAddress, bccAddress, null, textBody, emailBody);

		logger.info("This is the object we passed into the api" + send);
		SmtpEmailClient smtp = new SmtpEmailClient();

		logger.info("Sending to Api");
		smtp.sendMessage(send);

	}

	@Override
	public String UpdateQuotationAcceptanceIDInQuotation(int quotationAcceptanceID, String quotationId) {
		return quotationDAO.UpdateQuotationAcceptanceIDInQuotation(quotationAcceptanceID, quotationId);

	}

	@Override
	public String UpdateWelcomeLetterAcceptanceIDInQuotation(int quotationAcceptanceID, String quotationId) {
		return quotationDAO.UpdateWelcomeLetterAcceptanceIDInQuotation(quotationAcceptanceID, quotationId);

	}

	@Override
	public Object AcceptWelcomeLetter(String quotationId, AcceptQuotationRequestData acceptQuotationRequestData) {
		return merchantDAO.acceptWelcomeLetter(quotationId, acceptQuotationRequestData);
	}

	public int SaveQuotationEzysplitMDRRate(int productId, int quotationId, Product product) {

		QuotationEzySplitMDRRate quoteRate = new QuotationEzySplitMDRRate();
		LocalDateTime now = LocalDateTime.now();
		// HostRate hostRate =
		// GetHostRate(product.getStandardmdrRate().getHostRate().getId());

		quoteRate.setUnitPrice(product.getUnitPrice());

		// Merchant MDR

		logger.info("Product ID - " + product.getId());

		quoteRate.setLoc_Credit_Merch_Master_Insta3(
				product.getStandardEzySplitMDRRate().getLoc_Credit_Merch_Master_Insta3());
		quoteRate.setLoc_Credit_Cus_Master_Insta3(
				product.getStandardEzySplitMDRRate().getLoc_Credit_Cus_Master_Insta3());
		quoteRate.setLoc_Credit_Host_Master_Insta3(
				product.getStandardEzySplitMDRRate().getLoc_Credit_Host_Master_Insta3());
		quoteRate.setLoc_Credit_Mobi_Master_Insta3(
				product.getStandardEzySplitMDRRate().getLoc_Credit_Mobi_Master_Insta3());

		quoteRate.setLoc_Credit_Merch_Master_Insta6(
				product.getStandardEzySplitMDRRate().getLoc_Credit_Merch_Master_Insta6());
		quoteRate.setLoc_Credit_Cus_Master_Insta6(
				product.getStandardEzySplitMDRRate().getLoc_Credit_Cus_Master_Insta6());
		quoteRate.setLoc_Credit_Host_Master_Insta6(
				product.getStandardEzySplitMDRRate().getLoc_Credit_Host_Master_Insta6());
		quoteRate.setLoc_Credit_Mobi_Master_Insta6(
				product.getStandardEzySplitMDRRate().getLoc_Credit_Mobi_Master_Insta6());

		quoteRate.setLoc_Credit_Merch_Master_Insta9(
				product.getStandardEzySplitMDRRate().getLoc_Credit_Merch_Master_Insta9());
		quoteRate.setLoc_Credit_Cus_Master_Insta9(
				product.getStandardEzySplitMDRRate().getLoc_Credit_Cus_Master_Insta9());
		quoteRate.setLoc_Credit_Host_Master_Insta9(
				product.getStandardEzySplitMDRRate().getLoc_Credit_Host_Master_Insta9());
		quoteRate.setLoc_Credit_Mobi_Master_Insta9(
				product.getStandardEzySplitMDRRate().getLoc_Credit_Mobi_Master_Insta9());

		quoteRate.setLoc_Credit_Merch_Master_Insta12(
				product.getStandardEzySplitMDRRate().getLoc_Credit_Merch_Master_Insta12());
		quoteRate.setLoc_Credit_Cus_Master_Insta12(
				product.getStandardEzySplitMDRRate().getLoc_Credit_Cus_Master_Insta12());
		quoteRate.setLoc_Credit_Host_Master_Insta12(
				product.getStandardEzySplitMDRRate().getLoc_Credit_Host_Master_Insta12());
		quoteRate.setLoc_Credit_Mobi_Master_Insta12(
				product.getStandardEzySplitMDRRate().getLoc_Credit_Mobi_Master_Insta12());

		quoteRate.setProductSettlement(product.getStandardEzySplitMDRRate().getProductSettlement());
		quoteRate.setBoostSettlement(product.getStandardEzySplitMDRRate().getBoostSettlement());
		quoteRate.setGrabSettlement(product.getStandardEzySplitMDRRate().getGrabSettlement());
		quoteRate.setFpxSettlement(product.getStandardEzySplitMDRRate().getFpxSettlement());

		quoteRate.setBoostMDREcomm(product.getStandardEzySplitMDRRate().getBoostMDREcomm());
		quoteRate.setBoostMDRQR(product.getStandardEzySplitMDRRate().getBoostMDRQR());
		quoteRate.setGrabMDREcomm(product.getStandardEzySplitMDRRate().getGrabMDREcomm());
		quoteRate.setGrabMDRQR(product.getStandardEzySplitMDRRate().getGrabMDRQR());
		quoteRate.setfPXMDR_Percent(product.getStandardEzySplitMDRRate().getfPXMDR_Percent());
		quoteRate.setfPXMDR_RM(product.getStandardEzySplitMDRRate().getfPXMDR_RM());

		quoteRate.setTngMDREcomm(product.getStandardEzySplitMDRRate().getTngMDREcomm());
		quoteRate.setTngMDRQR(product.getStandardEzySplitMDRRate().getTngMDRQR());
		quoteRate.setShopeepayMDREcomm(product.getStandardEzySplitMDRRate().getShopeepayMDREcomm());
		quoteRate.setShopeepayMDRQR(product.getStandardEzySplitMDRRate().getShopeepayMDRQR());
		quoteRate.setTngSettlement(product.getStandardEzySplitMDRRate().getTngSettlement());
		quoteRate.setShopeepaySettlement(product.getStandardEzySplitMDRRate().getShopeepaySettlement());

		quoteRate.setCreatedOn(now);

		int quotationEzysplitMDRRateId = quotationDAO.SaveQuotationEzysplitMDRRate(quoteRate, productId, quotationId);
		return quotationEzysplitMDRRateId;
	}

	@Override
	public void AddOrderLineForEzysplit(int productId, int quotationId, int quantity, int quotationEzysplitMDRRateId) {
		quotationDAO.AddOrderLineForEzysplit(productId, quotationId, quantity, quotationEzysplitMDRRateId);
	}

	@Override
	public void UpdateQuotationEzysplitMDRRate(QuotationEzySplitMDRRate quoteEzysplitRate, int productId,
			int quotationid) {
		quotationDAO.UpdateQuotationEzysplitMDRRate(quoteEzysplitRate, productId, quotationid);

	}

	@Override
	public String GeneratePaymentRecept(PaymentCollectRequestData paymentCollectRequestData) {
		String receiptBasePath = String.format("%s/%s", Constants.getReceiptPath(),
				paymentCollectRequestData.getQuotationId());

		try {
			File receiptBasePathFile = new File(receiptBasePath);
			if (!receiptBasePathFile.exists()) {
				receiptBasePathFile.mkdirs();
			} else {
				receiptBasePathFile.delete();
				receiptBasePathFile.mkdirs();
			}

			String fileStoreLocation = String.format("%s/%s", receiptBasePath,
					paymentCollectRequestData.getDocumentName());

			String extractedImageData = paymentCollectRequestData.getDocumentData().split(",")[1];

			FileOutputStream imageOutFile = new FileOutputStream(fileStoreLocation);
			imageOutFile.write(decodeImage(extractedImageData));
			imageOutFile.close();

			return String.format("%s/%s/%s", Constants.getReceiptResourcePath(),
					paymentCollectRequestData.getQuotationId(), paymentCollectRequestData.getDocumentName());

		} catch (Exception e) {
			logger.error("Stacktrace : ", e);
		}

		return null;
	}

	public static byte[] decodeImage(String imageDataString) {
		return java.util.Base64.getDecoder().decode(imageDataString);
	}

	@Override
	public Object getReceiptByQuotationId(String quotationId) {
		Payment payment = quotationDAO.getPaymentReceiptByQuotationId(quotationId);
		if (payment != null) {
			ReceiptResponseData receiptResponseData = new ReceiptResponseData();
			receiptResponseData.setReceiptPath(payment.getReceipt());
			return new CommonResponseData("0000", "Success", receiptResponseData);
		} else {
			return new CommonResponseData("0001", "Receipt not available", null);
		}
	}

	@Override
	public Order GetOrderByQuotationId(int quotationid) {
		return quotationDAO.GetOrderByQuotationId(quotationid);
	}

	@Override
	public String GetURLWithContextPath(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
	}

	@Override
	public int UpdateWelcomeLetterPath(String welcomeLetterResourcePath, int quotationId) {
		return quotationDAO.UpdateWelcomeLetterPath(welcomeLetterResourcePath, quotationId);
	}

	@Override
	public SalesPerson GetSalesPersonByHubspotID(String hubspotOwnerId) {
		return quotationDAO.GetSalesPersonByHubspotID(hubspotOwnerId);
	}

	@Override
	public int updateMMAPath(int quotationId, String newMMAResourcePath) {
		return quotationDAO.updateMMAPath(newMMAResourcePath, quotationId);
	}

	public void SaveWalletRate(WalletRate walletRate) {
		quotationDAO.SaveWalletRate(walletRate);
	}

	@Override
	public List<WalletRate> GetWalletRates() {
		return quotationDAO.GetWalletRates();
	}

	@Override
	public List<WalletRate> GetWalletRates(String productType) {
		return quotationDAO.GetWalletRates(productType);
	}

	@Override
	public WalletRate GetWalletRateByID(int walletRate) {
		return quotationDAO.GetWalletRateByID(walletRate);
	}

	@Override
	public HubSpotInfo GetDealInfoFromHubspot(String dealId) {
		String urlString = null;
		StringBuffer urlStringBuffer = new StringBuffer();
		HubSpotInfo dealInfo = new HubSpotInfo();

		String requrl = "https://api.hubapi.com/deals/v1/deal/";
		urlStringBuffer.append(requrl);
		urlStringBuffer.append(dealId);
		// urlStringBuffer.append("?hapikey=90ef7c95-4391-4f69-bfdb-125740b7c08b");
		urlString = urlStringBuffer.toString();

		logger.info("URL To Fetch Deal Data : " + urlString);
		String line1 = null;

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(urlString);
		request.setHeader("Authorization", "Bearer " + "pat-na1-a4746b40-b0ed-4930-ab33-84f60c1c7c1a");
		try {
			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				try (InputStream stream = entity.getContent()) {
					BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

					ObjectMapper mapper = new ObjectMapper();
					dealInfo = mapper.readValue(reader, HubSpotInfo.class);

				}
			}
			return dealInfo;
		} catch (IOException e) {

			logger.error("Stacktrace : ", e);
			return dealInfo;
		}
	}

	@Override
	public void UpdateEmailAndNameInContact(int contactId, String email, String contactName) {
		quotationDAO.UpdateEmailAndNameInContact(contactId, email, contactName);
	}

	@Override
	public void UpdateHostRate(HostRate hostRate) {
		quotationDAO.UpdateHostRate(hostRate);
	}

	@Override
	public void UpdateWalletRate(WalletRate walletRate) {
		quotationDAO.UpdateWalletRate(walletRate);
	}

	@Override
	public List<Order> GetOrdersByStage(String stage) {
		return quotationDAO.GetOrdersByStage(stage);
	}

	@Override
	public List<HostRate> GetHostRateHistoryList(int id) {
		return quotationDAO.GetHostRateHistoryList(id);
	}

	@Override
	public void saveStageMovement(StageMovement stageMovement) {
		quotationDAO.saveStageMovement(stageMovement);
	}

	@Override
	public Object getStageMovement(String orderId) {
		return quotationDAO.getStageMovement(orderId);
	}

	@Override
	public String GetUserNameByPhoneNo(String phoneNo) {
		return quotationDAO.GetUserNameByPhoneNo(phoneNo);
	}

	public Object getOrderNotes(String orderId) {
		return quotationDAO.getOrderNotes(orderId);
	}

	@Override
	public Object deleteDirector(String orderId, String directorId) {
		return quotationDAO.deleteDirector(orderId, directorId);
	}

	@Override
	public Object getOrderDirector(String orderId) {
		return quotationDAO.getOrderDirector(orderId);
	}

	@Override
	public void setNotesEmail(String orderId, String companyName, OrderNotes notes, String senderPhone) {

		try {
			String fromAddress = "Info@gomobi.io";
			String htmlString = "";

			// Get Email Template
			URL QuotationEmailTemplatePath = getClass().getClassLoader()
					.getResource(env.getProperty("notesEmail.emailTemplate"));

			// Get Images for Email
			URL bannerImagePath = getClass().getClassLoader().getResource(env.getProperty("notesEmail.bannerImage"));

			// Convert Image to Base64
			String bannerImageBase64String = encodeFileToBase64Binary(
					URLDecoder.decode(bannerImagePath.toString().replace("file:/", ""), "UTF-8"));

			// String bannerImageBase64String =
			// env.getProperty("quotationEmail.bannerImageBase64");
			// String quotationAcceptImageBase64String =
			// env.getProperty("quotationEmail.quotationAcceptBase64");

			Attachment quotationBanner = new Attachment("quotationBanner.png", "image/png", bannerImageBase64String,
					"cid:notesBannerBase65");

			List<Attachment> attachments = new ArrayList<Attachment>();
			attachments.add(quotationBanner);

			StringBuilder html = new StringBuilder();
			FileReader reader = new FileReader(
					URLDecoder.decode(QuotationEmailTemplatePath.toString().replace("file:", ""), "UTF-8"));

			BufferedReader br = new BufferedReader(reader);
			String val;

			while ((val = br.readLine()) != null) {
				html.append(val);
			}

			String senderName = GetUserNameByPhoneNo(senderPhone);
			String finalSenderName = String.format("%s%s", senderPhone,
					senderName.isEmpty() ? "" : "(" + senderName + ")");

			htmlString = html.toString();
			htmlString = htmlString.replace("[[FROM_CONTENT]]", finalSenderName);
			htmlString = htmlString.replace("[[TO_CONTENT]]", notes.getNotifyTo());
			htmlString = htmlString.replace("[[MESSAGE_CONTENT]]", notes.getNotes());
			logger.info(htmlString);

			br.close();

//			String emailBody = htmlString;
//
//			String toAddress = notes.getNotifyTo();
//			String ccAddress = "";
//			String subject = "You have a Message From " + senderName;
//
//			SendEmail(fromAddress, toAddress, ccAddress, subject, "", "", emailBody, attachments);

			logger.info("Params that need to pass into api");

			String subject = "You have a Message From " + senderName;
			String emailBody = htmlString;
			String toAddress = notes.getNotifyTo();
			String ccAddress = null;
			String bccAddress = "";
			String textBody = "";

			SmtpEmail send = new SmtpEmail(subject, toAddress, ccAddress, bccAddress, null, textBody, emailBody);

			logger.info("This is the object we passed into the api" + send);
			SmtpEmailClient smtp = new SmtpEmailClient();

			logger.info("Sending to Api");
			smtp.sendMessage(send);

		} catch (Exception ex) {
			logger.error("Stacktrace : ", ex);
		}
	}

	@Override
	public List<UmobileCountry> GetCountryList() {
		return quotationDAO.GetCountryList();
	}

	@Override
	public String GetCountryIDByName(String countryID) {
		return quotationDAO.GetCountryIDByName(countryID);
	}

	@Override
	public List<Quotation> GetQuotationsBySalesId(int salespersonId) {
		return quotationDAO.GetQuotationsBySalesId(salespersonId);
	}

	@Override
	public void SendWelcomeLetterAcceptedEmail(String quotationId) {
		try {

			logger.info("Send Welcome Letter Acceptance Email Module - QuotationID : " + quotationId);
			Quotation quotation = getQuotationByID(Integer.parseInt(quotationId));
			Order order = GetOrderByQuotationId(Integer.parseInt(quotationId));

			String htmlString = "";

			// Get Email Template
			URL welcomeLetterAcceptedEmailTemplatePath = getClass().getClassLoader()
					.getResource(env.getProperty("welcomeAcceptedEmail.emailTemplate"));

			// Get Images for Email
			URL bannerImagePath = getClass().getClassLoader()
					.getResource(env.getProperty("welcomeAcceptedEmail.bannerImage"));

			// Convert Image to Base64
			String bannerImageBase64String = encodeFileToBase64Binary(
					URLDecoder.decode(bannerImagePath.toString().replace("file:/", ""), "UTF-8"));

			Attachment welcomeLetterAcceptedBanner = new Attachment("welcomeAcceptedBanner.png", "image/png",
					bannerImageBase64String, "cid:welcomeLetterAcceptedBannerBase64");

			logger.info("Adding Attachments");

			List<Attachment> attachments = new ArrayList<Attachment>();
			attachments.add(welcomeLetterAcceptedBanner);

			String wlResourcePath = generatePDFService.GenerateWL(String.valueOf(order.getId()));
			logger.info("WL Resource Path : " + wlResourcePath);
			if (wlResourcePath != null) {
				String wlPDFAbsolutePath = (env.getProperty("resources.basePath")
						+ wlResourcePath.replace("/attachments", ""));
				logger.info("WL Absolute Path : " + wlPDFAbsolutePath);
				Attachment wlPDF = new Attachment("WelcomeLetter.pdf", "application/pdf",
						encodeFileToBase64Binary(wlPDFAbsolutePath.toString().replace("file:/", "")), "cid:wlPDF");
				attachments.add(wlPDF);

			}

			StringBuilder html = new StringBuilder();
			FileReader reader = new FileReader(
					URLDecoder.decode(welcomeLetterAcceptedEmailTemplatePath.toString().replace("file:", ""), "UTF-8"));
			try {
				BufferedReader br = new BufferedReader(reader);
				String val;

				while ((val = br.readLine()) != null) {
					html.append(val);
				}

				htmlString = html.toString();
				htmlString = htmlString.replace("[[orderId]]", String.valueOf(order.getId()));
				htmlString = htmlString.replace("[[companyName]]", quotation.getCompanyName());
				htmlString = htmlString.replace("[[acceptedBy]]",
						quotation.getWelcomeLetterAcceptance().getNameAsPerIC());
				htmlString = htmlString.replace("[[acceptedNRIC]]",
						quotation.getWelcomeLetterAcceptance().getIcNumber());
				htmlString = htmlString.replace("[[acceptedIp]]",
						quotation.getWelcomeLetterAcceptance().getIpAddress());
				htmlString = htmlString.replace("[[acceptedOn]]",
						String.valueOf(quotation.getWelcomeLetterAcceptance().getCreatedOn()));

				logger.info(htmlString);

				br.close();
			}

			catch (Exception ex) {
				logger.info(ex.getMessage());
			}
			String notificationList = quotationDAO.GetNotificationListForTheStage("WLACCEPTANCE");
//
//			String emailBody = htmlString;
//
//			String fromAddress = "Info@gomobi.io";
//			String toAddress = quotation.getContact().getEmail();
//			String ccAddress = notificationList;
//			String subject = "You have Successfully Accepted the Welcome Letter - Ref:" + order.getId();
//
//			SendEmail(fromAddress, toAddress, ccAddress, subject, "", "", emailBody, attachments);

			logger.info("Params that need to pass into api");

			String subject = "You have Successfully Accepted the Welcome Letter - Ref:" + order.getId();
			String fromAddress = "Info@gomobi.io";
			String fromName = null;
			String emailBody = htmlString;
			String ccAddress = null;
			String toAddress = quotation.getContact().getEmail() + "," + notificationList;
			String bccAddress = null;
			String textBody = null;
			SmtpEmail send = new SmtpEmail(subject, toAddress, ccAddress, bccAddress, null, textBody, emailBody);

			logger.info("This is the object we passed into the api" + send);
			SmtpEmailClient smtp = new SmtpEmailClient();

			logger.info("Sending to Api");
			smtp.sendMessage(send);

		} catch (Exception ex) {
			logger.error("Stacktrace : ", ex);
		}
	}

	@Override
	public void SendQuotationAcceptedEmail(String quotationId) {
		try {

			logger.info("Send Quotation Acceptance Email Module - QuotationID : " + quotationId);
			Quotation quotation = getQuotationByID(Integer.parseInt(quotationId));
			Order order = GetOrderByQuotationId(Integer.parseInt(quotationId));

			String htmlString = "";

			// Get Email Template
			URL quotationAcceptedEmailTemplatePath = getClass().getClassLoader()
					.getResource(env.getProperty("quotationAcceptanceEmail.emailTemplate"));

			// Get Images for Email
			URL bannerImagePath = getClass().getClassLoader()
					.getResource(env.getProperty("quotationAcceptanceEmail.bannerImage"));

			// Convert Image to Base64
			String bannerImageBase64String = encodeFileToBase64Binary(
					URLDecoder.decode(bannerImagePath.toString().replace("file:/", ""), "UTF-8"));

			Attachment quotationAcceptedBanner = new Attachment("quotationAcceptanceEmail.png", "image/png",
					bannerImageBase64String, "cid:quotationAcceptedBannerBase64");

			logger.info("Adding Attachments");

			List<Attachment> attachments = new ArrayList<Attachment>();
			attachments.add(quotationAcceptedBanner);

			String wlResourcePath = generatePDFService.GenerateWL(String.valueOf(order.getId()));
			logger.info("WL Resource Path : " + wlResourcePath);
			if (wlResourcePath != null) {
				String wlPDFAbsolutePath = (env.getProperty("resources.basePath")
						+ wlResourcePath.replace("/attachments", ""));
				logger.info("WL Absolute Path : " + wlPDFAbsolutePath);
				Attachment wlPDF = new Attachment("quotationAcceptanceEmail.pdf", "application/pdf",
						encodeFileToBase64Binary(wlPDFAbsolutePath.toString().replace("file:/", "")), "cid:wlPDF");
				attachments.add(wlPDF);

			}

			StringBuilder html = new StringBuilder();
			FileReader reader = new FileReader(
					URLDecoder.decode(quotationAcceptedEmailTemplatePath.toString().replace("file:", ""), "UTF-8"));
			try {
				BufferedReader br = new BufferedReader(reader);
				String val;

				while ((val = br.readLine()) != null) {
					html.append(val);
				}

				htmlString = html.toString();
				htmlString = htmlString.replace("[[orderId]]", String.valueOf(order.getId()));
				htmlString = htmlString.replace("[[companyName]]", quotation.getCompanyName());
				htmlString = htmlString.replace("[[acceptedBy]]", quotation.getQuotationAcceptance().getNameAsPerIC());
				htmlString = htmlString.replace("[[acceptedNRIC]]", quotation.getQuotationAcceptance().getIcNumber());
				htmlString = htmlString.replace("[[acceptedIp]]", quotation.getQuotationAcceptance().getIpAddress());
				htmlString = htmlString.replace("[[acceptedOn]]",
						String.valueOf(quotation.getQuotationAcceptance().getCreatedOn()));

				logger.info(htmlString);

				br.close();
			}

			catch (Exception ex) {
				logger.info(ex.getMessage());
			}
			String notificationList = quotationDAO.GetNotificationListForTheStage("WLACCEPTANCE");

//			String emailBody = htmlString;
//
//			String fromAddress = "Info@gomobi.io";
//			String toAddress = quotation.getContact().getEmail() + ",vignesh@gomobi.io";
//			String ccAddress = notificationList;
//			String subject = "You have Successfully Accepted the Quotation - Ref:" + order.getId();
//
//			SendEmail(fromAddress, toAddress, ccAddress, subject, "", "", emailBody, attachments);

			logger.info("Params that need to pass into api");

			String subject = "You have Successfully Accepted the Quotation - Ref:" + order.getId();
			String fromAddress = "Info@gomobi.io";
			String fromName = null;
			String emailBody = htmlString;
			String toAddress = quotation.getContact().getEmail() + " , " + notificationList;
			String ccAddress = null;
			String bccAddress = null;
			String textBody = null;
			SmtpEmail send = new SmtpEmail(subject, toAddress, ccAddress, bccAddress, null, textBody, emailBody);

			logger.info("This is the object we passed into the api" + send);
			SmtpEmailClient smtp = new SmtpEmailClient();

			logger.info("Sending to Api");
			smtp.sendMessage(send);

		} catch (Exception ex) {
			logger.error("Stacktrace : ", ex);

		}

	}

	@Override
	public Object updateQuotationUserId(String quotationId, String username) {
		return quotationDAO.updateQuotationUserId(quotationId, username);
	}

	@Override
	public void storeInvoiceDocument(String quotationId, String invoiceDocument, String invoiceDocumentName,
			String modeOfPayment, String invoiceNumber, String verifiedBy) {
		// String filePath =
		// generatePDFService.StoreQuotationInvoiceDocument(quotationId,
		// invoiceDocument);

		logger.info("storeInvoiceDocument");

		String filePath = "C:/ocsweb/quotation/" + quotationId + "/receipt/";
		File file = new File(filePath);

		try {
			if (!file.exists()) {
				file.mkdirs();
			}

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			String extractedDocumentData = invoiceDocument.split(",")[1];
			byte[] byteArray = decodeImage(extractedDocumentData);

			String fileStoreLocation = file.getAbsolutePath() + "/" + invoiceDocumentName;

			File existingFile = new File(fileStoreLocation);
			if (existingFile.exists()) {
				existingFile.delete();
			}

			FileOutputStream imageOutFile = new FileOutputStream(fileStoreLocation);
			imageOutFile.write(byteArray);
			imageOutFile.close();

			String quotationReceiptFilePath = String.format("%s/%s/receipt/%s", Constants.getQuotationResourcePath(),
					quotationId, invoiceDocumentName);
			Payment payment = new Payment();
			payment.setQuotationId(quotationId);
			payment.setReceipt(quotationReceiptFilePath);
			payment.setModeOfPayment(modeOfPayment);
			payment.setInvoiceNo(invoiceNumber);
			payment.setVerifiedBy(verifiedBy);
			quotationDAO.SavePayment(payment);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Object getPendingQuotationMDRRates(String quotationId) {
		Object response = quotationDAO.getPendingQuotationMDRRates(quotationId);
		return response;
	}

	@Override
	public Object getPendingQuotationEzySplitMDRRates(String quotationId) {
		Object response = quotationDAO.getPendingQuotationEzySplitMDRRates(quotationId);
		return response;
	}

	@Override
	public Object updatePendingQuotationMDRRates(String quotationId, PendingQuotationMDRRate requestData) {
		Quotation quotation = quotationDAO.getQuotationByID(Integer.valueOf(quotationId));
		requestData.setQuotationId(quotation);
		int response = quotationDAO.updatePendingQuotationMDRRates(requestData);
		if (response == 1) {
			quotation.setPendingApproval(1);
			int quotationResponse = quotationDAO.updatePendingApprovalQuotation(quotation);
			if (quotationResponse == 1) {
				return new CommonResponseData("0000", "Success", null);
			} else {
				return new CommonResponseData("0001", "Unable to update details", null);
			}
		} else {
			return new CommonResponseData("0001", "Unable to update details", null);
		}
	}

	@Override
	public List<Bank> GetBankList() {
		return quotationDAO.GetBankList();
	}

	@Override
	public Boolean IsCardIDExistForTheBoard(String cardID, String boardID) {
		return quotationDAO.IsCardIDExistForTheBoard(cardID, boardID);
	}

	@Override
	public KanbanInfo GetCardDetailsFromKanban(String cardId, String boardId) {
		String urlString = null;
		StringBuffer urlStringBuffer = new StringBuffer();
		KanbanInfo kanbanInfo = null;

		String requrl = "https://integrations.kanbanzone.io/v1/card?board=" + boardId + "&number=" + cardId
				+ "&api_token=" + Constants.KanbanApiKeyBase64;

		urlStringBuffer.append(requrl);
		urlString = urlStringBuffer.toString();

		logger.info("urlString : " + urlString);
		String line1 = null;

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(urlString);
		try {
			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				try (InputStream stream = entity.getContent()) {
					BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

					ObjectMapper mapper = new ObjectMapper();
					kanbanInfo = mapper.readValue(reader, KanbanInfo.class);
				}
			}
			return kanbanInfo;
		} catch (IOException e) {

			logger.error("Cannot Get CardDetails from Kanban : ", e);
			return kanbanInfo;
		}

	}

	@Override
	public SalesPerson GetSalesPersonByEmail(String ownerEmail) {

		return quotationDAO.GetSalesPersonByEmail(ownerEmail);
	}

	@Override
	public CompanyType GetCompanyTypeByValue(String companyTypeValue) {

		return GetCompanyTypeByValue(companyTypeValue);
	}

	@Override
	public void UpdateDataInKanban(Quotation quote) {

		KanbanCardUpdate card = new KanbanCardUpdate();
		card.setBoard(quote.getBoardID());

		List<KanbanCustomFields> customFieldList = new ArrayList<KanbanCustomFields>();

		KanbanCustomFields merchantName = new KanbanCustomFields();
		merchantName.setLabel("Merchant Name");
		merchantName.setValue(quote.getCompanyName() == null ? "" : quote.getCompanyName());

		KanbanCustomFields picName = new KanbanCustomFields();
		picName.setLabel("PIC Name");
		picName.setValue(quote.getContact().getFirstName() == null ? "" : quote.getContact().getFirstName());

		KanbanCustomFields mobileNo = new KanbanCustomFields();
		mobileNo.setLabel("Mobile No");
		mobileNo.setValue(quote.getUserId() == null ? "" : quote.getUserId());

		KanbanCustomFields emailAddress = new KanbanCustomFields();
		emailAddress.setLabel("Email Address");
		emailAddress.setValue(quote.getContact().getEmail() == null ? "" : quote.getContact().getEmail());

		customFieldList.add(merchantName);
		customFieldList.add(picName);
		customFieldList.add(mobileNo);
		customFieldList.add(emailAddress);

		card.setCustomFields(customFieldList);
		PostUpdateDataInKanban(card, quote.getCardID());

	}

	public void PostUpdateDataInKanban(KanbanCardUpdate card, String cardId) {

		logger.info("Sending Card Data to Kanban.");
		HashMap<String, String> resdata = null;
		logger.info("Request Parameters to be posted : " + card.toString());
		String kanbanUpdateURL = "https://integrations.kanbanzone.io/v1/card/" + cardId + "?api_token="
				+ Constants.KanbanApiKeyBase64;

		try {

			URL url = new URL(kanbanUpdateURL);
			logger.info("\nSending 'PUT' request to URL : " + url);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			ObjectMapper mapper = new ObjectMapper();
			OutputStream wr = new DataOutputStream(conn.getOutputStream());

			mapper.writeValue(wr, card);

			wr.flush();
			wr.close();

			int responseCode = conn.getResponseCode();

			logger.info("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			logger.info("Response Data : " + response.toString());

			logger.info("End Of Kanban Update!");

		} catch (MalformedURLException e) {
			logger.error("Failed to Update Data in Kanban : ", e);
		}

		catch (IOException e) {
			logger.error("Failed to  Update Data in Kanban : ", e);

		}
	}

	@Override
	public void SaveCallbackRequest(Callback callbackRequest) {
		quotationDAO.SaveCallbackRequest(callbackRequest);

	}

	@Override
	public List<Callback> GetCallBackRequests() {
		return quotationDAO.GetCallBackRequests();
	}

	@Override
	public Object ModifyOCSUserID(int quotationId, String newUserId) {

		try {
			quotationDAO.UpdateUserID(quotationId, newUserId);

			SendSignupEmail(quotationId, newUserId);

			return new CommonResponseData("0000", "User ID Modified", null);
		} catch (Exception e) {
			logger.info(String.format("Exception in Modifying User ID - %s for the Quotation ID - %s", newUserId,
					quotationId), e);
			return new CommonResponseData("0001", "Something went wrong, Please try again.", null);
		}

	}

	private void SendSignupEmail(int quotationId, String newUserId) {
		try {

			logger.info("Sending Signup Email for the User : " + newUserId);
			Quotation quotation = getQuotationByID(quotationId);

			String htmlString = "";

			// Get Email Template
			URL signupEmailTemplatePath = getClass().getClassLoader()
					.getResource(env.getProperty("signupEmail.emailTemplate"));

			// Get Images for Email
			URL bannerImagePath = getClass().getClassLoader().getResource(env.getProperty("signupEmail.bannerImage"));
			URL goToOCSImagePath = getClass().getClassLoader().getResource(env.getProperty("signupEmail.goToOCSImage"));

			// Convert Image to Base64
			String bannerImageBase64String = encodeFileToBase64Binary(
					URLDecoder.decode(bannerImagePath.toString().replace("file:/", ""), "UTF-8"));
			String goToOCSImageBase64String = encodeFileToBase64Binary(
					URLDecoder.decode(goToOCSImagePath.toString().replace("file:/", ""), "UTF-8"));

			Attachment signupBanner = new Attachment("signupEmail.png", "image/png", bannerImageBase64String,
					"cid:signupBannerBase64");
			Attachment goToOCSImage = new Attachment("goToOCS.png", "image/png", goToOCSImageBase64String,
					"cid:goToOCSImageBase64");

			logger.info("Adding Attachments");

			List<Attachment> attachments = new ArrayList<Attachment>();
			attachments.add(signupBanner);
			attachments.add(goToOCSImage);

			StringBuilder html = new StringBuilder();
			FileReader reader = new FileReader(
					URLDecoder.decode(signupEmailTemplatePath.toString().replace("file:", ""), "UTF-8"));
			try {
				BufferedReader br = new BufferedReader(reader);
				String val;

				while ((val = br.readLine()) != null) {
					html.append(val);
				}

				htmlString = html.toString();
				htmlString = htmlString.replace("[[SignupUrl]]", env.getProperty("app.baseUrl") + "signup" + "?userId="
						+ encodeStringToBase64(quotation.getUserId()));
				htmlString = htmlString.replace("[[merchantname]]",
						(quotation.getContact().getFirstName() == null ? "" : quotation.getContact().getFirstName()));

				logger.info(htmlString);

				br.close();
			}

			catch (Exception ex) {
				logger.info(ex.getMessage());
			}
			String notificationList = quotationDAO.GetNotificationListForTheStage("CS");

//			String emailBody = htmlString;
//
//			String fromAddress = "Info@gomobi.io";
//			String toAddress = quotation.getContact().getEmail();
//			String ccAddress = notificationList;
//			String subject = "OCS Registration +60" + quotation.getUserId();
//
//			SendEmail(fromAddress, toAddress, ccAddress, subject, "", "", emailBody, attachments);

			logger.info("Params that need to pass into api");

			String subject = "OCS Registration +60" + quotation.getUserId();
			String toAddress = quotation.getContact().getEmail() + " ," + notificationList;
			String ccAddress = null;
			String bccAddress = null;
			String textBody = "";
			String emailBody = htmlString;

			SmtpEmail send = new SmtpEmail(subject, toAddress, ccAddress, bccAddress, null, textBody, emailBody);

			logger.info("This is the object we passed into the api" + send);
			SmtpEmailClient smtp = new SmtpEmailClient();

			logger.info("Sending to Api");
			smtp.sendMessage(send);

		} catch (Exception ex) {
			logger.error("Exception in Sending Signup Email : ", ex);

		}

	}

	@Override
	public int MarkCallbackContacted(int callbackId) {
		return quotationDAO.MarkCallbackContacted(callbackId);
	}

	private String encodeStringToBase64(String userId) {
		byte[] bytesEncoded = Base64.encodeBase64(userId.getBytes());
		System.out.println("Encoded String : " + new String(bytesEncoded));
		return new String(bytesEncoded);
	}

	@Override
	public String decodeBase64ToString(String encodedValue) {

		// Decode data on other side, by processing encoded data
		byte[] valueDecoded = Base64.decodeBase64(encodedValue);
		System.out.println("Decoded value is " + new String(valueDecoded));

		return new String(valueDecoded);
	}

	public Object rollbackQuotation(String quotationId, RollbackRequestData rollbackRequestData) {
		Object response = quotationDAO.rollbackQuotation(quotationId, rollbackRequestData);
		return response;
	}

	public Object getMerchantQuotationDetailsByQuotationId(String quotationId) {
		Quotation quotation = merchantDAO.getOrderLineByQuotationId(quotationId);
		if (quotation != null) {
			List<QuotationMDRRate> quotationMDRRate = merchantDAO.getMDRRates(quotationId);
			List<QuotationEzySplitMDRRate> quotationEzySplitMDRRate = merchantDAO.getEzySplitMDRRates(quotationId);

			MerchantQuotationDetailsResponseModel merchantQuotationDetailsResponseModel = new MerchantQuotationDetailsResponseModel();
			if (quotation.getOrderLines() != null) {
				merchantQuotationDetailsResponseModel.setOrderLines(quotation.getOrderLines());
			} else {
				merchantQuotationDetailsResponseModel.setOrderLines(null);
			}

			merchantQuotationDetailsResponseModel.setQuotationAccepted(quotation.getQuotationAccepted());

			return new CommonResponseData("0000", "Success", merchantQuotationDetailsResponseModel);
		}

		return new CommonResponseData("0001", "Unbale to get Merchant Quotation Details ", null);
	}

	public Object updateOrderDirector(String orderId, OrderDirectorResponseData requestData) {
		// TODO Auto-generated method stub
		return quotationDAO.updateOrderDirector(orderId, requestData);
	}

	@Override
	public Object applyDiscount(String quotationId, DiscountPriceRequestData discountPriceRequestData) {
		return quotationDAO.applyDiscount(quotationId, discountPriceRequestData);
	}

	@Override
	public QuotationAcceptance getQuotationAcceptanceByUserName(String quotationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quotation getQuotationByUserName(String mSISDN) {
		// TODO Auto-generated method stub
		return quotationDAO.getQuotationByUserName(mSISDN);
	}

	@Override
	public Object updateQuoatationSalesPersonId(String salesPersonId, String quotationId) {
		// TODO Auto-generated method stub
		return quotationDAO.updateQuoatationSalesPersonId(salesPersonId, quotationId);
	}

	@Override
	public void sendNotificationToFinance(Quotation quotation) {
		// TODO Auto-generated method stub
		String fromAddress = "Info@gomobi.io";

		if (quotation != null) {
			String content = String.format("New Quotation moved to finance\n" + "Quotation ID: %s | Company Name: %s\n",
					String.valueOf(quotation.getId()), quotation.getCompanyName());

//			SendEmail(fromAddress, "finance@gomobi.io, anis@gomobi.io, vignesh@gomobi.io", "",
//					String.format("New Quotation #%s in finance stage.", String.valueOf(quotation.getId())), "", "",
//					content, null);
			logger.info("Params that need to pass into api");

			String subject = String.format("New Quotation #%s in finance stage.", String.valueOf(quotation.getId()));
			String emailBody = content;
			String toAddress = "finance@gomobi.io" + "," + "anis@gomobi.io";
			String ccAddress = null;
			String bccAddress = null;
			String textBody = null;

			SmtpEmail send = new SmtpEmail(subject, toAddress, ccAddress, bccAddress, null, textBody, emailBody);

			logger.info("This is the object we passed into the api" + send);
			SmtpEmailClient smtp = new SmtpEmailClient();

			logger.info("Sending to Api");
			smtp.sendMessage(send);

		}
	}

}
