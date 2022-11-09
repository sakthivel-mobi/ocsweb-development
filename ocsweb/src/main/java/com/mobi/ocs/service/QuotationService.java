package com.mobi.ocs.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;

import com.itextpdf.text.BadElementException;
import com.mobi.ocs.dto.HubSpotInfo;
import com.mobi.ocs.dto.kanban.KanbanInfo;
import com.mobi.ocs.entity.AccountType;
import com.mobi.ocs.entity.Acquirer;
import com.mobi.ocs.entity.Bank;
import com.mobi.ocs.entity.BusinessType;
import com.mobi.ocs.entity.Callback;
import com.mobi.ocs.entity.CompanyType;
import com.mobi.ocs.entity.Contact;
import com.mobi.ocs.entity.Director;
import com.mobi.ocs.entity.UmobileECommIndustry;
import com.mobi.ocs.entity.HostRate;
import com.mobi.ocs.entity.HostType;
import com.mobi.ocs.entity.MasterMerchant;
import com.mobi.ocs.entity.MerchantIdType;
import com.mobi.ocs.entity.NatureOfBusiness;
import com.mobi.ocs.entity.OrderNotes;
import com.mobi.ocs.entity.Order;
import com.mobi.ocs.entity.OrderLines;
import com.mobi.ocs.entity.OrderType;
import com.mobi.ocs.entity.Payment;
import com.mobi.ocs.entity.PaymentType;
import com.mobi.ocs.entity.PendingQuotationMDRRate;
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
import com.mobi.ocs.entity.UmobileState;
import com.mobi.ocs.entity.UmobileTown;
import com.mobi.ocs.entity.WalletRate;
import com.mobi.ocs.entity.dotnetOcs.MerchantDetails;
import com.mobi.ocs.modal.AcceptQuotationRequestData;
import com.mobi.ocs.modal.DiscountPriceRequestData;
import com.mobi.ocs.modal.OrderDirectorResponseData;
import com.mobi.ocs.modal.PaymentCollectRequestData;
import com.mobi.ocs.modal.PendingQuotationMDRRatesRequestData;
import com.mobi.ocs.modal.RollbackRequestData;
import com.mobi.ocs.utilities.Attachment;
import com.mobi.ocs.entity.UmobileMCC;

public interface QuotationService {

	public List<Quotation> getQuotations();

	public void saveQuotation(Quotation quotation, int salespersonId);

	public Quotation getQuotationByID(int quotationID);

	public void deleteQuotation(String quotationID);

	public List<OrderType> getOrderTypes();

	public String GetContactORCompanyFromHubSpot(String dealID, int ContactORCompany);

	public HubSpotInfo GetContactInfoFromHubSpot(String contactID);

	public void saveContact(Contact contact);

	public void ShowWarning(String string, String string2);

	public HubSpotInfo GetCompanyInfoFromHubSpot(String companyID);

	public Boolean IsDealExist(String dealId);

	public int SaveStandardMDRRate(StandardMDRRate rate);

	public void SaveProduct(Product product);

	public List<Product> GetProducts();

	public int SavePayment(Payment payment);

	public void UpdatePaymentIdInQuotation(int paymentId, String quotationId);

	public void CreateOrder(int id);

	public List<Order> getOrders();

	public List<MerchantIdType> GetMerchantIdTypeList();

	public List<NatureOfBusiness> GetNatureOfBusinessList();

	public List<UmobileECommIndustry> GetEcommIndustryList();

	public List<UmobileMCC> GetUmobileMCCList();

	public List<CompanyType> GetCompanyTypeList();

	public List<AccountType> GetAccountTypeList();

	public Order GetOrderByID(String orderID);

	public List<Acquirer> GetAcquirerList();

	public List<UmobileState> GetStateList();

	public List<UmobileTown> GetCityList();

	public Product GetProduct();

	public List<ProductType> GetProductTypes();

	public List<HostType> GetHostTypes();

	public List<HostRate> GetHostRates();

	public void SaveHostRate(HostRate hostRate);

	public List<HostRate> GetHostRates(String hostType, String productType);

	public HostRate GetHostRate(int hostRate);

	public List<Product> GetProductsByType(String type);

	public void AddOrderLine(int productId, int quotationId, int quantity, int quotationMDRRateId);

	public List<OrderLines> GetOrderLineByQuotation(int quotationId);

	public void deleteOrderLines(String[] orderLineIDArray);

	public OrderLines getOrderLineById(int orderLineId);

	public int SaveQuotationMDRRate(int productId, int quotationid);

	public void UpdateQuotationMDRRate(QuotationMDRRate quoteRate, int productId, int quotationid);

	public void updateQuotation(Quotation quote);

	public List<PaymentType> GetPaymentTypes();

	public List<Acquirer> GetAcquirers();

	public List<SalesPerson> GetSalesPersons();

	public void SaveDirector(Director director, String orderId);

	public Product GetProductById(int productId);

	public void AddProductToLogHistory(int id);

	public void UpdateProduct(Product product);

	public List<Product> GetProductHistoryList(int productId);

	public void SaveNotes(OrderNotes notes, String orderId, String string);

	public void UpdateOrder(Order order);

	public String GetFromProperyFile(String string);

	public void SendEmail(String fromAddress, String toAddress, String ccAddress, String subject, String filePath,
			String fileName, String emailBody, List<Attachment> attachment);

	public void UpdateTotalInQuotation(int id, double orderTotal1);

	public String FormatAmountFromDoubleToString(double unitPrice);

	public String GetUmobileCityByID(int cityId);

	public String GetUmobileStateByID(int stateId);

	public Object AddQuotationUrl(String quotationFilePath, String quotationName, int quotationId);

	public List<BusinessType> GetBusinessTypeList();

	public void SaveOrderLine(OrderLines lines, int productId, int quotationId, int quotationMDRRateId,
			int quotationEzysplitMDRRateId);

	public List<MasterMerchant> GetMasterMerchants();

	public String GetBusinessTypeByID(String businessType);

	public CompanyType GetCompanyTypeByID(String companyType);

	public String GetNatureOfBusinessByID(int natureOfBusiness);

	public String GetAccountTypeByID(int accountType);

	public String GetEcommIndustryByValue(int geteCommIndustry);

	public String GetVisaMCCByValue(int visaMCC);

	public String GetRoleNameAsPerDB(String authority);

	public List<Order> getOrdersByRole(String loggedUserRole);

	public String MoveToNextStage(String currentStage, String acquirer, int quotationId);

	public Object getQuotationPdf(int quotationId);

	public Object getQuotationHistory(String quotationId);

	public Object insertIssuedQuotation(String issuedQuotationPath, String issuedQuotationName, int id);

	public SalesPerson GetSalesPersonByPhoneNumber(String phoneNumber);

	public SalesPerson GetSalesHead();

	public List<Quotation> GetQuotationsByStage(String string);

	public void UpdatePayment(Payment payment);

	public Payment GetPaymentByID(String paymentId);

	public List<Salutation> GetSalutationList();

	public void SendQuotationIssueEmail(int quotationID, Object response, HttpServletRequest req)
			throws FileNotFoundException, BadElementException, MalformedURLException, IOException;

	public void SendWelcomeLetterEmail(String orderId)
			throws FileNotFoundException, BadElementException, MalformedURLException, IOException;

	public void SendStageMovementEmail(Order order, String submittedBy, String submittedByRole, String currentStage,
			String orderViewURL) throws FileNotFoundException, IOException;

	public int SaveQuotationEzysplitMDRRate(int productId, int quotationId, Product product);

	public void AddOrderLineForEzysplit(int productId, int quotationId, int quantity, int quotationEzysplitMDRRateId);

	public void UpdateQuotationEzysplitMDRRate(QuotationEzySplitMDRRate quoteRate, int productId, int quotationid);

	public String UpdateQuotationAcceptanceIDInQuotation(int quotationAcceptanceID, String quotationId);

	public Object AcceptWelcomeLetter(String quotationId, AcceptQuotationRequestData acceptQuotationRequestData);

	String UpdateWelcomeLetterAcceptanceIDInQuotation(int quotationAcceptanceID, String quotationId);

	public String GeneratePaymentRecept(PaymentCollectRequestData paymentCollectRequestData);

	public Object getReceiptByQuotationId(String parseInt);

	public Order GetOrderByQuotationId(int parseInt);

	public String GetURLWithContextPath(HttpServletRequest req);

	public int UpdateWelcomeLetterPath(String welcomeLetterResourcePath, int quotationId);

	public SalesPerson GetSalesPersonByHubspotID(String hubspotOwnerId);

	public int updateMMAPath(int id, String newMMAResourcePath);

	public void SaveWalletRate(WalletRate walletRate);

	public List<WalletRate> GetWalletRates();

	public List<WalletRate> GetWalletRates(String productType);

	public WalletRate GetWalletRateByID(int walletRate);

	public void UpdateOrderLines(List<OrderLines> orderLines);

	public HubSpotInfo GetDealInfoFromHubspot(String dealId);

	public void UpdateEmailAndNameInContact(int contactId, String email, String contactName);

	public void UpdateHostRate(HostRate hostRate);

	public void UpdateWalletRate(WalletRate walletRate);

	public List<Order> GetOrdersByStage(String stage);

	public List<HostRate> GetHostRateHistoryList(int id);

	public void saveStageMovement(StageMovement stageMovement);

	public Object getStageMovement(String orderId);

	public String GetUserNameByPhoneNo(String phoneNo);

	public Object getOrderNotes(String orderId);

	public Object deleteDirector(String orderId, String directorId);

	public Object getOrderDirector(String orderId);

	public void setNotesEmail(String orderId, String companyName, OrderNotes notes, String senderName);

	public List<UmobileCountry> GetCountryList();

	public String GetCountryIDByName(String nationality);

	public List<Quotation> GetQuotationsBySalesId(int id);

	public void SendWelcomeLetterAcceptedEmail(String quotationId);

	public void SendQuotationAcceptedEmail(String quotationId);

	public Object updateQuotationUserId(String quotationId, String username);

	public void storeInvoiceDocument(String quotationId, String invoiceDocument, String invoiceDocumentName,
			String modeOfPayment, String invoiceNumber, String verifiedBy);

	public Object getPendingQuotationMDRRates(String quotationId);

	public Object getPendingQuotationEzySplitMDRRates(String quotationId);

	public Object updatePendingQuotationMDRRates(String quotationId,
			PendingQuotationMDRRate pendingQuotationMDRRatesRequestData);

	public List<Bank> GetBankList();

	public Boolean IsCardIDExistForTheBoard(String cardID, String boardID);

	public KanbanInfo GetCardDetailsFromKanban(String cardId, String boardId);

	public SalesPerson GetSalesPersonByEmail(String ownerEmail);

	public CompanyType GetCompanyTypeByValue(String companyType);

	public void UpdateDataInKanban(Quotation quote);

	public int UpdateStages(String currentStage, String acquirer, int quotationID);

	public void SaveCallbackRequest(Callback callbackRequest);

	public List<Callback> GetCallBackRequests();

	public Object ModifyOCSUserID(int quotationId, String newUserId);

	public int MarkCallbackContacted(int callbackId);

	public String decodeBase64ToString(String userId);

	public Object rollbackQuotation(String quotationId, RollbackRequestData rollbackRequestData);

	public Object getMerchantQuotationDetailsByQuotationId(String quotationId);

	public Object updateOrderDirector(String orderId, OrderDirectorResponseData requestData);

	public Object applyDiscount(String quotationId, DiscountPriceRequestData discountPriceRequestData);

	public QuotationAcceptance getQuotationAcceptanceByUserName(String userId);

	public Quotation getQuotationByUserName(String mSISDN);

	public Object AddProfomaUrl(String quotationFilePath, String quotationName, int quotationId);

	public Object getProfomaPdf(int quotationId);

//rk added
	public Object insertIssuedProfoma(String issuedQuotationPath, String issuedQuotationName, int id);

	public void SendProfomaEmail(int quotationID, Object response, HttpServletRequest req)
			throws BadElementException, MalformedURLException, IOException;

	public Object updateQuoatationSalesPersonId(String salesPersonId, String quotationId);

	public void sendNotificationToFinance(Quotation quotation);

}
