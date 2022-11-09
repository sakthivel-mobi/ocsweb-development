package com.mobi.ocs.dao;

import java.util.ArrayList;
import java.util.List;

import com.mobi.ocs.dto.HubSpotInfo;
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
import com.mobi.ocs.modal.DiscountPriceRequestData;
import com.mobi.ocs.modal.OrderDirectorResponseData;
import com.mobi.ocs.modal.PendingQuotationMDRRatesRequestData;
import com.mobi.ocs.modal.QuotationRequestData;
import com.mobi.ocs.modal.RollbackRequestData;
import com.mobi.ocs.entity.UmobileMCC;

public interface QuotationDAO {

	public List<Quotation> getQuotations();

	public void saveQuotation(Quotation quotation, int salespersonId);

	public Quotation getQuotationByID(int quotationID);

	public void deleteQuotation(String quotationID);

	public List<OrderType> getOrderTypes();

	public void saveContact(Contact contact);

	public Boolean IsDealExist(String dealId);

	public int SaveStandardMDRRate(StandardMDRRate rate);

	public void SaveProduct(Product product);

	public List<Product> GetProducts();

	public int SavePayment(Payment payment);

	public void UpdatePaymentIdInQuotation(int paymentId, String quotationId);

	public void CreateOrder(Order order, int quotationID);

	public List<Order> getOrders();

	public List<MerchantIdType> GetMerchantIdTypeList();

	public List<NatureOfBusiness> GetNatureOfBusinessList();

	public List<UmobileECommIndustry> GetEcommIndustryList();

	public List<UmobileMCC> GetUmobileMCCList();

	public List<CompanyType> GetCompanyTypeList();

	public List<AccountType> GetAccountTypeList();

	public Order GetOrderByID(String orderId);

	public List<Acquirer> GetAcquirerList();

	public List<UmobileState> GetStateList();

	public List<UmobileTown> GetCityList();

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

	public int SaveQuotationMDRRate(QuotationMDRRate quoteRate, int productId, int quotationid);

	public void UpdateOrderLinesWithQuotationMDRID(int quotationMDRRateId, int orderLineId);

	public void UpdateQuotationMDRRate(QuotationMDRRate quoteRate, int productId, int quotationid);

	public void updateQuotation(Quotation quote);

	public List<PaymentType> GetPaymentTypes();

	public List<Acquirer> GetAcquirers();

	public List<SalesPerson> GetSalesPersons();

	public void SaveDirector(Director director, String orderId);

	public Product GetProductById(int productId);

	public void AddProductToLogHistory(int productId);

	public void UpdateProduct(Product product);

	public List<Product> GetProductHistoryList(int productId);

	public void SaveNotes(OrderNotes notes, String orderId, String from);

	public void UpdateOrder(Order order);

	public void UpdateTotalInQuotation(int id, double orderTotal1);

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

	public String GetEcommIndustryByValue(int getECommIndustry);

	public String GetVisaMCCByValue(int visaMCC);

	public List<Order> GetOrdersByRole(String loggedUserRole);

	public int MoveToStage(String stage, int quotationID);

	public Object getQuotationPdf(int quotationId);

	public Object issueQuotation(String issuedQuotationPath, String issuedQuotationName, int id);

	public Object getQuotationHistory(String quotationId);

	public SalesPerson GetSalesHead();

	public List<Quotation> GetQuotationsByStage(String stage);

	public SalesPerson GetSalesPersonByPhoneNumber(String phoneNumber);

	public void UpdatePayment(Payment payment);

	public Payment GetPaymentByID(String paymentId);

	public List<Salutation> GetSalutationList();

	public String UpdateQuotationAcceptanceIDInQuotation(int quotationAcceptanceID, String quotationId);

	public String UpdateWelcomeLetterAcceptanceIDInQuotation(int quotationAcceptanceID, String quotationId);

	public int SaveQuotationEzysplitMDRRate(QuotationEzySplitMDRRate quoteRate, int productId, int quotationId);

	public void AddOrderLineForEzysplit(int productId, int quotationId, int quantity, int quotationEzysplitMDRRateId);

	public void UpdateQuotationEzysplitMDRRate(QuotationEzySplitMDRRate quoteEzysplitRate, int productId,
			int quotationid);

	public Payment getPaymentReceiptByQuotationId(String quotationId);

	public Order GetOrderByQuotationId(int quotationid);

	public int UpdateWelcomeLetterPath(String welcomeLetterResourcePath, int quotationId);

	public SalesPerson GetSalesPersonByHubspotID(String hubspotOwnerId);

	public int updateMMAPath(String newMMAResourcePath, int quotationId);

	public void SaveWalletRate(WalletRate walletRate);

	public List<WalletRate> GetWalletRates();

	public List<WalletRate> GetWalletRates(String productType);

	public WalletRate GetWalletRateByID(int walletRate);

	public void updateOrderLines(List<OrderLines> orderLines);

	public void UpdateEmailAndNameInContact(int contactId, String email, String contactName);

	public void UpdateHostRate(HostRate hostRate);

	public void UpdateWalletRate(WalletRate walletRate);

	public List<Order> GetOrdersByStage(String stage);

	public List<HostRate> GetHostRateHistoryList(int id);

	public void saveStageMovement(StageMovement stageMovement);

	public Object getStageMovement(String orderId);

	public Object getOrderNotes(String orderId);

	public Object deleteDirector(String orderId, String directorId);

	public Object getOrderDirector(String orderId);

	public String GetNotificationListForTheStage(String currentStage);

	public String GetUserNameByPhoneNo(String phoneNo);

	public List<UmobileCountry> GetCountryList();

	public String GetCountryIDByName(String countryID);

	public List<Quotation> GetQuotationsBySalesId(int salespersonId);

	public QuotationAcceptance getQuotationAcceptanceByQuotationId(int id);

	public Object updateQuotationUserId(String quotationId, String username);

	public Object getPendingQuotationMDRRates(String quotationId);

	public Object getPendingQuotationEzySplitMDRRates(String quotationId);

	public int updatePendingQuotationMDRRates(PendingQuotationMDRRate pendingQuotationMDRRatesRequestData);

	public int updatePendingApprovalQuotation(Quotation quotation);

	public List<Bank> GetBankList();

	public Boolean IsCardIDExistForTheBoard(String cardID, String boardID);

	public SalesPerson GetSalesPersonByEmail(String ownerEmail);

	public int UpdateStages(String stage, int quotationID);

	public void SaveCallbackRequest(Callback callbackRequest);

	public List<Callback> GetCallBackRequests();

	public void UpdateUserID(int quotationId, String newUserId);

	public int MarkCallbackContacted(int callbackId);

	public Object rollbackQuotation(String quotationId, RollbackRequestData rollbackRequestData);

	public Object updateOrderDirector(String orderId, OrderDirectorResponseData requestData);

	public List<Product> getProductName(String productName);

	public List<String> getAllProductList();

	public Object applyDiscount(String quotationId, DiscountPriceRequestData discountPriceRequestData);

	public Quotation getQuotationByUserName(String mSISDN);

	public Object AddProfomaUrl(String quotationFilePath, String quotationName, int quotationId);

	public Object getProfomaPdf(int quotationId);

	public Object issueProfoma(String issuedQuotationPath, String issuedQuotationName, int id);

	public Object updateQuoatationSalesPersonId(String salesPersonId, String quotationId);

}
