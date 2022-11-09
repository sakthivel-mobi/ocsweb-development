package com.mobi.ocs.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.google.gson.Gson;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.mobi.ocs.dto.CommonResponseData;
import com.mobi.ocs.dto.EzysplitRateID;
import com.mobi.ocs.dto.MDRData;
import com.mobi.ocs.dto.MDRSyncToPaymentServer;
import com.mobi.ocs.dto.MerchRegDto;
import com.mobi.ocs.dto.WelcomeLetterPathDataModel;
import com.mobi.ocs.dto.mdrSyncDto;
import com.mobi.ocs.entity.AccountType;
import com.mobi.ocs.entity.Acquirer;
import com.mobi.ocs.entity.BusinessType;
import com.mobi.ocs.entity.CompanyType;
import com.mobi.ocs.entity.Director;
import com.mobi.ocs.entity.HostRate;
import com.mobi.ocs.entity.MerchantIdType;
import com.mobi.ocs.entity.MerchantRegistration;
import com.mobi.ocs.entity.NatureOfBusiness;
import com.mobi.ocs.entity.Order;
import com.mobi.ocs.entity.OrderLines;
import com.mobi.ocs.entity.Product;
import com.mobi.ocs.entity.Quotation;
import com.mobi.ocs.entity.QuotationMDRRate;
import com.mobi.ocs.entity.SalesPerson;
import com.mobi.ocs.entity.UmobileCountry;
import com.mobi.ocs.entity.UmobileECommIndustry;
import com.mobi.ocs.entity.UmobileMCC;
import com.mobi.ocs.entity.UmobileState;
import com.mobi.ocs.entity.UmobileTown;
import com.mobi.ocs.entity.WelcomeLetterAcceptance;
import com.mobi.ocs.modal.MDRSyncResponseData.BrandResponseData;
import com.mobi.ocs.modal.OldMerchantDataUpdate;
import com.mobi.ocs.modal.PaymentMDRSyncResponse;
import com.mobi.ocs.service.GeneratePDFService;
import com.mobi.ocs.service.MerchantRegistrationService;
import com.mobi.ocs.service.QuotationService;
import com.mobi.ocs.utilities.Constants;

import lombok.val;

@Controller
@RequestMapping(value = "/merchantReg")
public class MerchantRegistrationController {

	@Autowired
	private QuotationService quotationService;

	@Autowired
	private MerchantRegistrationService regService;

	@Autowired
	private GeneratePDFService generatePDFService;

	protected static Logger logger = Logger.getLogger(MerchantRegistrationController.class);

	@RequestMapping(value = "/list")
	public String RegList(Model model) {

		try {

			System.out.println("Inside MerchantRegistration Controller");
			List<Order> pendingDeployementOrders = regService.GetPendingDeploymentOrders();
			List<MerchantRegistration> registrationOrders = regService.getRegistrationOrders();

			for (MerchantRegistration reg : registrationOrders) {
				logger.info(reg.getOrder().getStage());
				logger.info(reg.getOrder().getMerchantTradingName());
			}

			model.addAttribute("pendingDeployementOrders", pendingDeployementOrders);
			model.addAttribute("registrationOrders", registrationOrders);

		} catch (Exception e) {
			logger.info(e);
		}

		return "MerchantRegList";
	}

	@RequestMapping(value = "/addRegOrder")
	public @ResponseBody Object AddRegOrder(Model model, @RequestParam("orderId") String orderId) {
		logger.info("Inside AddRegOrder Controller");
		Object response = regService.CreateMerchantRegistrationForOrder(orderId);
		return response;
	}

	@RequestMapping("/registrationView")
	public String RegistrationView(@RequestParam("regId") String id,
			@RequestParam("registerMessage") String registerMessage, Model model) {

		MerchantRegistration reg = regService.GetRegistrationOrderByID(id);

		// Get Values For DropDown
		List<MerchantIdType> merchantIdTypeList = quotationService.GetMerchantIdTypeList();
		List<NatureOfBusiness> natureOfBusinessList = quotationService.GetNatureOfBusinessList();
		List<UmobileECommIndustry> eCommIndustryList = quotationService.GetEcommIndustryList();
		List<UmobileMCC> umobileMCCList = quotationService.GetUmobileMCCList();
		List<AccountType> accountTypeList = quotationService.GetAccountTypeList();
		List<CompanyType> companyTypeList = quotationService.GetCompanyTypeList();
		List<BusinessType> businessTypeList = quotationService.GetBusinessTypeList();
		List<Acquirer> acquirerList = quotationService.GetAcquirerList();
		List<SalesPerson> salesPersons = quotationService.GetSalesPersons();
		List<OrderLines> orderLines = quotationService.GetOrderLineByQuotation(reg.getOrder().getQuotation().getId());

		List<UmobileCountry> CountryList = quotationService.GetCountryList();
		List<UmobileState> StateList = quotationService.GetStateList();
		List<UmobileTown> CityList = quotationService.GetCityList();

		model.addAttribute("merchantRegistrationId", reg.getId());
		model.addAttribute("orderLineList", orderLines);
		model.addAttribute("merchantIdTypeList", merchantIdTypeList);
		model.addAttribute("natureOfBusinessList", natureOfBusinessList);
		model.addAttribute("eCommIndustryList", eCommIndustryList);
		model.addAttribute("umobileMCCList", umobileMCCList);
		model.addAttribute("accountTypeList", accountTypeList);
		model.addAttribute("businessTypeList", businessTypeList);
		model.addAttribute("companyTypeList", companyTypeList);
		model.addAttribute("acquirerList", acquirerList);
		model.addAttribute("salesPersons", salesPersons);
		model.addAttribute("StateList", StateList);
		model.addAttribute("CityList", CityList);
		model.addAttribute("CountryList", CountryList);
		model.addAttribute("quotationMDR", reg.getOrder().getQuotation().getOrderLines().get(0).getQuotationMDRRate());

		model.addAttribute("registerMessage", registerMessage);

		logger.info("MerchantRegistration >> " + reg.toString());

		model.addAttribute("reg", reg);

		return "MerchantRegView";
	}

	@RequestMapping("/getOrderLineDetails")
	public @ResponseBody mdrSyncDto GetOrderLineDetails(@RequestParam("orderLineId") String orderLineId, Model model) {

		OrderLines orderLine = quotationService.getOrderLineById(Integer.parseInt(orderLineId));

		// Direct OrderLine Object not used to bind results as we used JSON ignore
		// property in the Orderlines Class
		mdrSyncDto sync = new mdrSyncDto();

		sync.setOrderLineId(orderLine.getId());
		if (orderLine.getQuotationEzysplitMDRRate() == null) {

			sync.setQuotationMdrRateId(orderLine.getQuotationMDRRate().getId());

			sync.setProductName(orderLine.getQuotationMDRRate().getProductName());
			sync.setIncludeWallet(orderLine.getQuotationMDRRate().getIncludeWallet());
			sync.setShowInQuote(orderLine.getQuotationMDRRate().getShowInQuote());
			sync.setPayLater(orderLine.getQuotationMDRRate().getPayLater());
			sync.setSubscription(orderLine.getQuotationMDRRate().getSubscription());
			sync.setUnitPrice(orderLine.getQuotationMDRRate().getUnitPrice());
			sync.setProductType(orderLine.getQuotationMDRRate().getProductType());
			sync.setProductSettlement(String.valueOf(orderLine.getQuotationMDRRate().getProductSettlement()));
			sync.setHostType(orderLine.getQuotationMDRRate().getHostType());

			// Debit Merchant
			sync.setLoc_Debit_Merch_Master(orderLine.getQuotationMDRRate().getLoc_Debit_Merch_Master());
			sync.setLoc_Debit_Merch_Visa(orderLine.getQuotationMDRRate().getLoc_Debit_Merch_Visa());
			sync.setLoc_Debit_Merch_Union(orderLine.getQuotationMDRRate().getLoc_Debit_Merch_Union());
			sync.setFor_Debit_Merch_Master(orderLine.getQuotationMDRRate().getFor_Debit_Merch_Master());
			sync.setFor_Debit_Merch_Visa(orderLine.getQuotationMDRRate().getFor_Debit_Merch_Visa());
			sync.setFor_Debit_Merch_Union(orderLine.getQuotationMDRRate().getFor_Debit_Merch_Union());

			// Credit Mechant
			sync.setLoc_Credit_Merch_Master(orderLine.getQuotationMDRRate().getLoc_Credit_Merch_Master());
			sync.setLoc_Credit_Merch_Visa(orderLine.getQuotationMDRRate().getLoc_Credit_Merch_Visa());
			sync.setLoc_Credit_Merch_Union(orderLine.getQuotationMDRRate().getLoc_Credit_Merch_Union());
			sync.setFor_Credit_Merch_Master(orderLine.getQuotationMDRRate().getFor_Credit_Merch_Master());
			sync.setFor_Credit_Merch_Visa(orderLine.getQuotationMDRRate().getFor_Credit_Merch_Visa());
			sync.setFor_Credit_Merch_Union(orderLine.getQuotationMDRRate().getFor_Credit_Merch_Union());

		} else {

			sync.setQuotationEzysplitMdrRateId(orderLine.getQuotationEzysplitMDRRate().getId());

			sync.setEzylinkTid(orderLine.getEzylinkTid());
			sync.setEzywayTid(orderLine.getEzywayTid());

			sync.setProductName(orderLine.getQuotationEzysplitMDRRate().getProductName());
			sync.setIncludeWallet(orderLine.getQuotationEzysplitMDRRate().getIncludeWallet());
			sync.setShowInQuote(orderLine.getQuotationEzysplitMDRRate().getShowInQuote());
			sync.setPayLater(orderLine.getQuotationEzysplitMDRRate().getPayLater());
			sync.setSubscription(orderLine.getQuotationEzysplitMDRRate().getSubscription());
			sync.setUnitPrice(orderLine.getQuotationEzysplitMDRRate().getUnitPrice());
			sync.setProductType(orderLine.getQuotationEzysplitMDRRate().getProductType());
			sync.setProductSettlement(String.valueOf(orderLine.getQuotationEzysplitMDRRate().getProductSettlement()));
			sync.setHostType(orderLine.getQuotationEzysplitMDRRate().getHostType());

			// Ezysplit Merchant
			sync.setLoc_Credit_Merch_Master_Ezysplit_Insta3(
					orderLine.getQuotationEzysplitMDRRate().getLoc_Credit_Merch_Master_Insta3());
			sync.setLoc_Credit_Merch_Master_Ezysplit_Insta6(
					orderLine.getQuotationEzysplitMDRRate().getLoc_Credit_Merch_Master_Insta6());
			sync.setLoc_Credit_Merch_Master_Ezysplit_Insta9(
					orderLine.getQuotationEzysplitMDRRate().getLoc_Credit_Merch_Master_Insta9());
			sync.setLoc_Credit_Merch_Master_Ezysplit_Insta12(
					orderLine.getQuotationEzysplitMDRRate().getLoc_Credit_Merch_Master_Insta12());

			sync.setLoc_Credit_Cus_Master_Ezysplit_Insta3(
					orderLine.getQuotationEzysplitMDRRate().getLoc_Credit_Cus_Master_Insta3());
			sync.setLoc_Credit_Cus_Master_Ezysplit_Insta6(
					orderLine.getQuotationEzysplitMDRRate().getLoc_Credit_Cus_Master_Insta6());
			sync.setLoc_Credit_Cus_Master_Ezysplit_Insta9(
					orderLine.getQuotationEzysplitMDRRate().getLoc_Credit_Cus_Master_Insta9());
			sync.setLoc_Credit_Cus_Master_Ezysplit_Insta12(
					orderLine.getQuotationEzysplitMDRRate().getLoc_Credit_Cus_Master_Insta12());

		}

		sync.setProductId(orderLine.getProduct().getId());
		sync.setQuotationId(orderLine.getQuotation().getId());
		sync.setQuantity(orderLine.getQuantity());
		sync.setMid(orderLine.getMid());
		sync.setTid(orderLine.getTid());
		sync.setDtl(orderLine.getDtl());
		sync.setHashkey(orderLine.getHashkey());
		sync.setCallbackURL(orderLine.getCallbackURL());
		sync.setEzywireDeviceId(orderLine.getEzywireDeviceId());
		sync.setEzywireRefNo(orderLine.getEzywireRefNo());
		sync.setCreatedOn(orderLine.getCreatedOn());

		return sync;
	}

	@RequestMapping("/submitMDRRates")
	public @ResponseBody String submitMDRRates(@ModelAttribute() mdrSyncDto sync,
			@RequestParam("orderId") String orderId, @RequestParam("chkUpdateMDR") String chkUpdateMDR,
			@RequestParam("merchantRegistrationId") String merchantRegistrationId, HttpServletRequest req)
			throws FileNotFoundException, BadElementException, MalformedURLException, IOException {

		logger.info("SubmitMDRRates Controller");

		OrderLines lines = new OrderLines();
		LocalDateTime now = LocalDateTime.now();

		int productId = sync.getProductId();
		int quotationId = sync.getQuotationId();

		lines.setId(sync.getOrderLineId());
		lines.setMid(sync.getMid());
		lines.setTid(sync.getTid());

		if (!sync.getDtl().equals("")) {
			DecimalFormat df = new DecimalFormat("#.##");
			String formattedDtl = df.format(Double.valueOf(sync.getDtl()));

			logger.info("Formatted DTL : " + formattedDtl);
			lines.setDtl(formattedDtl);
		} else {
			lines.setDtl("");
		}

		lines.setHashkey(sync.getHashkey());
		lines.setCreatedOn(sync.getCreatedOn());

		lines.setCallbackURL(sync.getCallbackURL());
		lines.setEzywireDeviceId(sync.getEzywireDeviceId());
		lines.setEzywireRefNo(sync.getEzywireRefNo());
		lines.setEzylinkTid(sync.getEzylinkTid());
		lines.setEzywayTid(sync.getEzywayTid());
		lines.setQuantity(sync.getQuantity());

		if (sync.getProductType().equals("EZYSPLIT")) {

			int quotationMDRRateId = 0;
			int quotationEzysplitMDRRateId = sync.getQuotationEzysplitMdrRateId();

			quotationService.SaveOrderLine(lines, productId, quotationId, quotationMDRRateId,
					quotationEzysplitMDRRateId);
			List<MDRSyncToPaymentServer> lstMdrSync = new ArrayList<MDRSyncToPaymentServer>();

			// Send RateID Values to Payment DB and refer RateID
			EzysplitRateID ezysplitRateIDs = regService.GetRateIDs(sync);

			MDRSyncToPaymentServer mdrSyncEzysplit = regService.AssignMDRSyncDataEzysplit(sync.getOrderLineId(),
					ezysplitRateIDs, chkUpdateMDR);
			lstMdrSync.add(mdrSyncEzysplit);

			String response = regService.SendMDRDataToPayment(lstMdrSync, quotationId, orderId, req);

			/*
			 * response.forEach((key, value) -> {
			 * regService.updateAPIKeyAndActivationCode(value.getMobiApiKey(),
			 * value.getActivationCode(), quotationId, value.getPassword(),
			 * value.getUsername()); return; });
			 * 
			 * if (!response.isEmpty()) { logger.info("Email will be send for the Order - "
			 * + orderId); quotationService.SendWelcomeLetterEmail(orderId, req);
			 * regService.UpdateSuccessStatusForRegistration(orderId); return
			 * "MDR Data Updated Successfully"; } else {
			 * logger.info("Failed To Send Email for OrderID : " + orderId +
			 * " Due to Response Code - " + response.get("activationCode") +
			 * " and Response Description " + response.get("responseDescription")); return
			 * "Unable to update MDR Data"; }
			 */

			return response;
		} else {
			int quotationEzysplitMDRRateId = 0;
			int quotationMDRRateId = sync.getQuotationMdrRateId();

			MerchantRegistration merchantRegistration = regService
					.getMerchantRegistrationByRegistrationId(Integer.valueOf(merchantRegistrationId));

			quotationService.SaveOrderLine(lines, productId, quotationId, quotationMDRRateId,
					quotationEzysplitMDRRateId);

			List<MDRSyncToPaymentServer> lstMdrSync = new ArrayList<MDRSyncToPaymentServer>();

			MDRSyncToPaymentServer mdrSyncVisa = regService.AssignMDRSyncData(sync.getOrderLineId(),
					sync.getProductType(), "VISA", chkUpdateMDR, merchantRegistration);
			MDRSyncToPaymentServer mdrSyncMaster = regService.AssignMDRSyncData(sync.getOrderLineId(),
					sync.getProductType(), "MASTERCARD", chkUpdateMDR, merchantRegistration);
			MDRSyncToPaymentServer mdrSyncUnionPay = regService.AssignMDRSyncData(sync.getOrderLineId(),
					sync.getProductType(), "UNIONPAY", chkUpdateMDR, merchantRegistration);

			mdrSyncVisa.setBoostTid(merchantRegistration.getBoostTid());
			mdrSyncMaster.setBoostTid(merchantRegistration.getBoostTid());
			mdrSyncUnionPay.setBoostTid(merchantRegistration.getBoostTid());

			lstMdrSync.add(mdrSyncVisa);
			lstMdrSync.add(mdrSyncMaster);
			lstMdrSync.add(mdrSyncUnionPay);

//			HashMap<String, BrandResponseData> response = regService.SendMDRDataToPayment(lstMdrSync,  quotationId, orderId, req);

			String response = regService.SendMDRDataToPayment(lstMdrSync, quotationId, orderId, req);

//			if (response.get("activationCode") != null) {
//				logger.info("Email will be send for the Order - " + orderId);
//				quotationService.SendWelcomeLetterEmail(orderId, req);
//				regService.UpdateSuccessStatusForRegistration(orderId);
//			} else {
//				logger.info("Failed To Send Email for OrderID : " + orderId + " Due to  Response Description "
//						+ response.get("responseDescription"));
//			}
//			return response.get("responseDescription");

			/*
			 * logger.info("response >> " + response.toString());
			 * 
			 * response.forEach((key, value) -> {
			 * regService.updateAPIKeyAndActivationCode(value.getMobiApiKey(),
			 * value.getActivationCode(), quotationId, value.getPassword(),
			 * value.getUsername()); return; });
			 * 
			 * if (!response.isEmpty()) { logger.info("Email will be send for the Order - "
			 * + orderId); quotationService.SendWelcomeLetterEmail(orderId, req);
			 * regService.UpdateSuccessStatusForRegistration(orderId);
			 * 
			 * PaymentMDRSyncResponse paymentMDRSyncResponse = null; try {
			 * logger.info("Convert paymentMDRSyncResponse from json to object ");
			 * paymentMDRSyncResponse = new Gson().fromJson(response.toString(),
			 * PaymentMDRSyncResponse.class); logger.info("paymentMDRSyncResponse >> " +
			 * paymentMDRSyncResponse.toString()); } catch (Exception e) {
			 * e.printStackTrace(); }
			 * 
			 * logger.info("paymentMDRSyncResponse >> " +
			 * paymentMDRSyncResponse.toString());
			 * 
			 * if (paymentMDRSyncResponse != null &&
			 * paymentMDRSyncResponse.getResponseCode().equals("0000")) { return
			 * paymentMDRSyncResponse.getResponseDescription(); } else { return
			 * "Something went wrong unable to complete MDR Sync"; }
			 * 
			 * } else { logger.info("Failed To Send Email for OrderID : " + orderId +
			 * " Due to Response Code - " + response.get("activationCode") +
			 * " and Response Description " + response.get("responseDescription")); return
			 * "Unable to update MDR Data"; }
			 */

			return response;
		}

	}

	@RequestMapping(value = "/registerMerchant")
	public String RegisterMerchant(Model model, @ModelAttribute() MerchantRegistration reg,
			@RequestParam("order.id") String orderId, BindingResult bindingResult) {

		for (FieldError fieldError : bindingResult.getFieldErrors())
			System.out.println(fieldError.getField() + " : " + fieldError.getDefaultMessage());

		logger.info("Inside RegisterMerchant Controller");

		regService.UpdateMerchantRegistration(reg);

		int flag = 0;
		MerchRegDto regReq = regService.AssignMerchRegData(reg, flag);
		String responseDescription = regService.SendMerchantRegToPayment(regReq, String.valueOf(reg.getId()), flag);
		System.out.println("responseDescription --> " + responseDescription);
		
//		 Submit e-wallet after merchant registration
		regService.submitEWalletMDR(String.valueOf(reg.getId()));
		
		try {
			quotationService.SendWelcomeLetterEmail(String.valueOf(reg.getOrder().getId()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/merchantReg/registrationView?" + "regId=" + reg.getId() + "&registerMessage="
				+ responseDescription;

		// return "redirect:/merchantReg/list";
	}

	@RequestMapping(value = "/registerMerchantUpdate")
	public String RegisterMerchantUpdate(Model model, @ModelAttribute() MerchantRegistration reg,
			@RequestParam("order.id") String orderId, BindingResult bindingResult) {
		
		logger.info("registerMerchantUpdate");
		logger.info("Merchant Registration >> "+ reg.toString());

		MerchantRegistration merchantRegistration = regService.getMerchantRegistrationByRegistrationId(reg.getId());
		
		logger.info("Merchant Registration >> "+ merchantRegistration.toString());

		for (FieldError fieldError : bindingResult.getFieldErrors())
			System.out.println(fieldError.getField() + " : " + fieldError.getDefaultMessage());

		if (merchantRegistration.getBoostMid() == null || merchantRegistration.getBoostMid().isEmpty()) {
			logger.info("Update Old Merchant Details");

			regService.UpdateMerchantRegistration(reg);

			OldMerchantDataUpdate requestData = new OldMerchantDataUpdate();
			requestData.setIsCombo(merchantRegistration.getEzyComboEnable());
			requestData.setOfficeEmail(merchantRegistration.getOrder().getOfficeEmail());
			requestData.setReActivateDate(merchantRegistration.getReRegister());

			String responseDescription = regService.UpdateOldMerchantData(requestData);

			return "redirect:/merchantReg/registrationView?" + "regId=" + reg.getId() + "&registerMessage="
					+ responseDescription;

		} else {

			logger.info("Inside RegisterMerchant Controller");

			logger.info("Merchnat data update");

			regService.UpdateMerchantRegistration(reg);

//		0 - Merchant Registration
//		1 - Merchant Registration Update
			int flag = 1;
			MerchRegDto regReq = regService.AssignMerchRegData(reg, flag);
			regReq.setBoostMid(reg.getBoostMid());
			regReq.setService("MERCHANT_UPDATE");
			String responseDescription = regService.SendMerchantRegToPayment(regReq, String.valueOf(reg.getId()), flag);

			System.out.println("responseDescription --> " + responseDescription);

			return "redirect:/merchantReg/registrationView?" + "regId=" + reg.getId() + "&registerMessage="
					+ responseDescription;
		}

		// return "redirect:/merchantReg/list";
	}

	@RequestMapping(value = "/updateRegistration")
	public String UpdateRegistration(Model model, @ModelAttribute() MerchantRegistration reg,
			@RequestParam("order.id") String orderId, BindingResult bindingResult) {

		for (FieldError fieldError : bindingResult.getFieldErrors())
			System.out.println(fieldError.getField() + " : " + fieldError.getDefaultMessage());

		logger.info("Inside RegisterMerchant Controller");
		logger.info("MerchantRegistration >> " + reg.toString());

		regService.UpdateMerchantRegistration(reg);

		return String.format("redirect:/merchantReg/registrationView?regId=%s&registerMessage=0", reg.getId());
	}

	@RequestMapping("/generateWL")
	public @ResponseBody Object GenerateWL(@RequestParam("orderId") String orderId, HttpServletRequest request,
			HttpServletResponse response) throws DocumentException, MalformedURLException, IOException {

		logger.info("Generating WL PDF for OrderID : " + orderId);

		String wlResourcePath = generatePDFService.GenerateWL(orderId);

		if (wlResourcePath != null) {

			WelcomeLetterPathDataModel welcomeLetterPathDataModel = new WelcomeLetterPathDataModel();
			welcomeLetterPathDataModel.setWelcomeLetterPath(wlResourcePath);
			return new CommonResponseData("0000", "Success", welcomeLetterPathDataModel);
		} else {
			return new CommonResponseData("0001", "Something went wrong, please try again", null);
		}
	}

	@RequestMapping("/generateMMA")
	public @ResponseBody Object GenerateMMA(@RequestParam("orderId") String orderId, HttpServletRequest request,
			HttpServletResponse response) throws DocumentException, MalformedURLException, IOException {
		logger.info("Generating MMA PDF for OrderID : " + orderId);
		return generatePDFService.GenerateMMA(orderId);
	}

}
