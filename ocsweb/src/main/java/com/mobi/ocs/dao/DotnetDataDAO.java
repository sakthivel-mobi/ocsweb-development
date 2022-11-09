package com.mobi.ocs.dao;

import java.util.ArrayList;
import java.util.List;

import com.mobi.ocs.dto.HubSpotInfo;
import com.mobi.ocs.entity.AccountType;
import com.mobi.ocs.entity.Acquirer;
import com.mobi.ocs.entity.Bank;
import com.mobi.ocs.entity.BusinessType;
import com.mobi.ocs.entity.CompanyType;
import com.mobi.ocs.entity.Contact;
import com.mobi.ocs.entity.Director;
import com.mobi.ocs.entity.UmobileECommIndustry;
import com.mobi.ocs.entity.HostRate;
import com.mobi.ocs.entity.HostType;
import com.mobi.ocs.entity.MasterMerchant;
import com.mobi.ocs.entity.MerchantIdType;
import com.mobi.ocs.entity.NatureOfBusiness;
import com.mobi.ocs.entity.OldNotesDetails;
import com.mobi.ocs.entity.OldOrderDetails;
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
import com.mobi.ocs.entity.dotnetOcs.Director_dotnet;
import com.mobi.ocs.entity.dotnetOcs.Document_dotnet;
import com.mobi.ocs.entity.dotnetOcs.MerchantDetails;
import com.mobi.ocs.entity.dotnetOcs.StageMovement_dotnet;
import com.mobi.ocs.modal.OldProductListData;
import com.mobi.ocs.modal.PendingQuotationMDRRatesRequestData;
import com.mobi.ocs.modal.QuotationRequestData;
import com.mobi.ocs.entity.UmobileMCC;

public interface DotnetDataDAO {

	public List<MerchantDetails> GetDotnetOCSMerchantData();

	public MerchantDetails GetDotnetOCSMerchantDataById(String orderID);

	public List<Director_dotnet> GetDotnetOCSDirectorsByOrderID(String userId);

	public List<StageMovement_dotnet> GetDotnetOCSStageMovementByOrderId(String orderId);

	public List<Document_dotnet> GetDotnetOCSDocumentsByUserId(String userId);

	public List<OldOrderDetails> getOldOrderDetailsById(String orderID);

	public List<OldNotesDetails> getOldOrderNotesByOrderId(String orderID);
}
