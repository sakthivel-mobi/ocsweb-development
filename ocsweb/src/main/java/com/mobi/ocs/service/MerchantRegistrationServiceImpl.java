package com.mobi.ocs.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.mobi.ocs.dao.MerchantRegistrationDAO;
import com.mobi.ocs.dao.UserDAO;
import com.mobi.ocs.dto.CommonResponseData;
import com.mobi.ocs.dto.EzysplitRateID;
import com.mobi.ocs.dto.MDRSyncToPaymentServer;
import com.mobi.ocs.dto.MerchRegDto;
import com.mobi.ocs.dto.RateIDInsertEzysplit;
import com.mobi.ocs.dto.ServiceResponse;
import com.mobi.ocs.dto.mdrSyncDto;
import com.mobi.ocs.entity.CompanyType;
import com.mobi.ocs.entity.Director;
import com.mobi.ocs.entity.MerchantRegistration;
import com.mobi.ocs.entity.Order;
import com.mobi.ocs.entity.OrderLines;
import com.mobi.ocs.entity.QuotationEzySplitMDRRate;
import com.mobi.ocs.entity.UmobileMCC;
import com.mobi.ocs.modal.GrabPayReportResponseData;
import com.mobi.ocs.modal.MDRSyncResponseData;
import com.mobi.ocs.modal.MerchantRegistrationResponseData;
import com.mobi.ocs.modal.WalletMDRSyncRequestData;
import com.mobi.ocs.modal.WalletMDRSyncResponseData;
import com.mobi.ocs.modal.MerchantRegistrationResponseData.MerchantRegistrationResponse;
import com.mobi.ocs.modal.OldMerchantDataUpdate;
import com.mobi.ocs.modal.OldMerchantResponseData;
import com.mobi.ocs.modal.PaymentMDRSyncResponse;
import com.mobi.ocs.modal.MDRSyncResponseData.BrandResponseData;
import com.mobi.ocs.utilities.Constants;

@Service
@Transactional
@PropertySource("classpath:UrlList.properties")
public class MerchantRegistrationServiceImpl implements MerchantRegistrationService {

	// set up variable to hold the properties
	@Autowired
	private Environment env;

	@Autowired
	private QuotationService quotationService;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private MerchantRegistrationDAO regDAO;

	@Autowired
	private MerchantRegistrationService regService;

	protected static Logger logger = Logger.getLogger(MerchantRegistrationServiceImpl.class);

	@Override
	public List<MerchantRegistration> getRegistrationOrders() {
		return regDAO.GetRegistrationOrders();
	}

	@Override
	public MerchantRegistration GetRegistrationOrderByID(String ID) {
		return regDAO.GetRegistrationOrderByID(ID);
	}

	@Override
	public List<Order> GetPendingDeploymentOrders() {
		return regDAO.getPendingDeploymentOrderIDs();
	}

	@Override
	public Object CreateMerchantRegistrationForOrder(String orderId) {
		return regDAO.CreateMerchantRegistrationForOrder(orderId);
	}

	@Override
	public MDRSyncToPaymentServer AssignMDRSyncData(int orderLineId, String productType, String cardBrand,
			String chkUpdateMDR, MerchantRegistration merchantRegistration) {

		OrderLines line = quotationService.getOrderLineById(orderLineId);
		MDRSyncToPaymentServer sync = new MDRSyncToPaymentServer();

		if (line.getQuotationMDRRate().getfPXMDR_Percent() != 0) {
			sync.setFpxMercAmt(line.getQuotationMDRRate().getfPXMDR_RM());
			sync.setFpxHostAmt(Constants.fpxEcommHostAmt);
			sync.setFpxMobiAmt(line.getQuotationMDRRate().getfPXMDR_RM() - Constants.fpxEcommHostAmt);
			sync.setFpxTxnMdr(line.getQuotationMDRRate().getfPXMDR_RM());
			
			
			sync.setTngEcomMercMdr(line.getQuotationMDRRate().getTngMDREcomm());
			sync.setTngEcomHostMdr(Constants.tngEcommHostAmt);
			sync.setTngEcomMobiMdr(line.getQuotationMDRRate().getTngMDREcomm() - Constants.tngEcommHostAmt);
			
			
			sync.setShopeepayEcomMercMdr(line.getQuotationMDRRate().getShopeepayMDREcomm());
			sync.setShopeepayEcomHostMdr(Constants.shopeepayEcommHostAmt);
			sync.setShopeepayEcomMobiMdr(line.getQuotationMDRRate().getShopeepayMDREcomm() - Constants.shopeepayEcommHostAmt);
			
			
			sync.setTngQrMercMdr(line.getQuotationMDRRate().getTngMDRQR());
			sync.setTngQrHostMdr(Constants.tngQRHostAmt);
			sync.setTngQrMobiMdr(line.getQuotationMDRRate().getTngMDRQR() - Constants.tngQRHostAmt);
			
			
			sync.setShopeepayQrMercMdr(line.getQuotationMDRRate().getShopeepayMDRQR());
			sync.setShopeepayQrHostMdr(Constants.shopeepayQRHostAmt);
			sync.setShopeepayQrMobiMdr(line.getQuotationMDRRate().getShopeepayMDRQR() - Constants.shopeepayQRHostAmt);
			
			

			sync.setBoostEcomMercMdr(line.getQuotationMDRRate().getBoostMDREcomm());
			sync.setBoostEcomHostMdr(Constants.boostEcommHostAmt);
			sync.setBoostEcomMobiMdr(line.getQuotationMDRRate().getBoostMDREcomm() - Constants.boostEcommHostAmt);

			sync.setGrabEcomMercMdr(line.getQuotationMDRRate().getGrabMDREcomm());
			sync.setGrabEcomHostMdr(Constants.grabEcommHostAmt);
			sync.setGrabEcomMobiMdr(line.getQuotationMDRRate().getGrabMDREcomm() - Constants.grabEcommHostAmt);

			// Currently, the rates are same both for ECOMM & QR

			sync.setBoostQrMercMdr(line.getQuotationMDRRate().getBoostMDRQR());
			sync.setBoostQrHostMdr(Constants.boostQRHostAmt);
			sync.setBoostQrMobiMdr(line.getQuotationMDRRate().getBoostMDRQR() - Constants.boostQRHostAmt);

			sync.setGrabQrMercMdr(line.getQuotationMDRRate().getGrabMDRQR());
			sync.setGrabQrHostMdr(Constants.grabQRHostAmt);
			sync.setGrabQrMobiMdr(line.getQuotationMDRRate().getGrabMDRQR() - Constants.grabQRHostAmt);
		} else {
			sync.setFpxMercAmt(0.00);
			sync.setFpxHostAmt(0.00);
			sync.setFpxMobiAmt(0.00);
			sync.setFpxTxnMdr(0.00);

			sync.setBoostEcomMercMdr(0.00);
			sync.setBoostEcomHostMdr(0.00);
			sync.setBoostEcomMobiMdr(0.00);
			
			
			sync.setTngEcomMercMdr(0.00);
			sync.setTngEcomHostMdr(0.00);
			sync.setTngEcomMobiMdr(0.00);
			
			
			sync.setTngQrMercMdr(0.00);
			sync.setTngQrHostMdr(0.00);
			sync.setTngQrMobiMdr(0.00);
			
			
			sync.setShopeepayEcomMercMdr(0.00);
			sync.setShopeepayEcomHostMdr(0.00);
			sync.setShopeepayEcomMobiMdr(0.00);
			
			
			sync.setShopeepayQrMercMdr(0.00);
			sync.setShopeepayQrHostMdr(0.00);
			sync.setShopeepayQrMobiMdr(0.00);
			
			

			sync.setGrabEcomMercMdr(0.00);
			sync.setGrabEcomHostMdr(0.00);
			sync.setGrabEcomMobiMdr(0.00);

			// Currently, the rates are same both for ECOMM & QR

			sync.setBoostQrMercMdr(0.00);
			sync.setBoostQrHostMdr(0.00);
			sync.setBoostQrMobiMdr(0.00);

			sync.setGrabQrMercMdr(0.00);
			sync.setGrabQrHostMdr(0.00);
			sync.setGrabQrMobiMdr(0.00);
		}

		if (chkUpdateMDR.equals("Yes")) {
			sync.setService(Constants.UPDATE_MDR_SYNC);
		} else if (line.getQuotation().getOrderType().equals("Migration")) {

			sync.setService(Constants.MIGRATION_MDR_SYNC);
			if (line.getQuotationMDRRate().getProductType().equals("EZYWIRE")) {

				sync.setRefNo(line.getEzywireRefNo());
				sync.setDeviceId(line.getEzywireDeviceId());
			}
		} else if (line.getQuotationMDRRate().getProductType().equals("EZYWIRE")) {

			sync.setService(Constants.MDR_SYNC);
			sync.setRefNo(line.getEzywireRefNo());
			sync.setDeviceId(line.getEzywireDeviceId());

		} else {
			sync.setService(Constants.MDR_SYNC);
		}

		if (line.getQuotation().getAcquirer().equals("Paydee")) {
			sync.setHostType("P");
		} else {
			sync.setHostType("U");
		}

		sync.setPayLater(line.getQuotationMDRRate().getPayLater());

		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		String formattedAmount = decimalFormat.format(line.getQuotationMDRRate().getUnitPrice());

		if (merchantRegistration.getPreAuth() == null) {
			sync.setPreAuth("No");
		} else {
			sync.setPreAuth(merchantRegistration.getPreAuth());
		}

		sync.setAmount(formattedAmount);
		sync.setSettlePeriod(String.valueOf(line.getQuotationMDRRate().getProductSettlement()));

		sync.setMid(line.getMid());
		sync.setTid(line.getTid());

		DecimalFormat df = new DecimalFormat("#.00");
		String formattedDtl = df
				.format(Double.valueOf(line.getDtl() == null || line.getDtl().isEmpty() ? "0.00" : line.getDtl()));

		logger.info("Formatted DTL : " + formattedDtl);

		sync.setDtl(formattedDtl);
		sync.setHashKey(line.getHashkey());
		sync.setCallBackUrl(line.getCallbackURL());
		sync.setSubscription(line.getQuotationMDRRate().getSubscription());
		sync.setProductType(line.getQuotationMDRRate().getProductType());
		sync.setForceReferral("Yes");
		// logger.info(line.getQuotation().getOrder().getMerchantRegistration().getId());
		sync.setRefereeMID(line.getQuotation().getRefreeMID());

		sync.setBusinessRegNo(line.getQuotation().getRegistrationNumber());

		sync.setCardBrand(cardBrand);

		switch (cardBrand) {
		case "VISA":

			sync.setCreditLocalMerchantMDR(line.getQuotationMDRRate().getLoc_Credit_Merch_Visa());
			sync.setCreditLocalHostMDR(line.getQuotationMDRRate().getLoc_Credit_Host_Visa());
			sync.setDebitLocalMerchantMDR(line.getQuotationMDRRate().getLoc_Debit_Merch_Visa());
			sync.setDebitLocalHostMDR(line.getQuotationMDRRate().getLoc_Debit_Host_Visa());

			sync.setCreditForeignMerchantMDR(line.getQuotationMDRRate().getFor_Credit_Merch_Visa());
			sync.setCreditForeignHostMDR(line.getQuotationMDRRate().getFor_Credit_Host_Visa());
			sync.setDebitForeignMerchantMDR(line.getQuotationMDRRate().getFor_Debit_Merch_Visa());
			sync.setDebitForeignHostMDR(line.getQuotationMDRRate().getFor_Debit_Host_Visa());
			break;

		case "MASTERCARD":

			sync.setCreditLocalMerchantMDR(line.getQuotationMDRRate().getLoc_Credit_Merch_Master());
			sync.setCreditLocalHostMDR(line.getQuotationMDRRate().getLoc_Credit_Host_Master());
			sync.setDebitLocalMerchantMDR(line.getQuotationMDRRate().getLoc_Debit_Merch_Master());
			sync.setDebitLocalHostMDR(line.getQuotationMDRRate().getLoc_Debit_Host_Master());

			sync.setCreditForeignMerchantMDR(line.getQuotationMDRRate().getFor_Credit_Merch_Master());
			sync.setCreditForeignHostMDR(line.getQuotationMDRRate().getFor_Credit_Host_Master());
			sync.setDebitForeignMerchantMDR(line.getQuotationMDRRate().getFor_Debit_Merch_Master());
			sync.setDebitForeignHostMDR(line.getQuotationMDRRate().getFor_Debit_Host_Master());
			break;

		case "UNIONPAY":

			sync.setCreditLocalMerchantMDR(line.getQuotationMDRRate().getLoc_Credit_Merch_Union());
			sync.setCreditLocalHostMDR(line.getQuotationMDRRate().getLoc_Credit_Host_Union());
			sync.setDebitLocalMerchantMDR(line.getQuotationMDRRate().getLoc_Debit_Merch_Union());
			sync.setDebitLocalHostMDR(line.getQuotationMDRRate().getLoc_Debit_Host_Union());

			sync.setCreditForeignMerchantMDR(line.getQuotationMDRRate().getFor_Credit_Merch_Union());
			sync.setCreditForeignHostMDR(line.getQuotationMDRRate().getFor_Credit_Host_Union());
			sync.setDebitForeignMerchantMDR(line.getQuotationMDRRate().getFor_Debit_Merch_Union());
			sync.setDebitForeignHostMDR(line.getQuotationMDRRate().getFor_Debit_Host_Union());
			break;

		default:
			break;
		}

		return sync;
	}

	@Override
	public String SendMDRDataToPayment(List<MDRSyncToPaymentServer> lstMdrSync, int quotationId, String orderId,
			HttpServletRequest req) {

		boolean status = false;
		PaymentMDRSyncResponse paymentMDRSyncResponse = null;

		for (MDRSyncToPaymentServer mdrSync : lstMdrSync) {

			logger.info("SendToPaymentServer Module Started");

			logger.info("Request Parameters to be posted : " + new Gson().toJson(mdrSync));

			String mdrSyncUrl = env.getProperty("mdrSync.paymentServerUrl");

			try {

				URL url = new URL(mdrSyncUrl);
				logger.info("\nSending 'POST' request to URL : " + url);

				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");
				conn.setDoOutput(true);

				ObjectMapper mapper = new ObjectMapper();
				OutputStream wr = new DataOutputStream(conn.getOutputStream());

				mapper.writeValue(wr, mdrSync);

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

				if (responseCode == 200) {
					MDRSyncResponseData mdrSyncResponseData = new Gson().fromJson(response.toString(),
							MDRSyncResponseData.class);
					logger.info("mdrSyncResponseData >> " + mdrSyncResponseData.toString());

				}

				try {
					logger.info("Convert paymentMDRSyncResponse from json to object ");

					paymentMDRSyncResponse = new Gson().fromJson(response.toString(), PaymentMDRSyncResponse.class);

					logger.info("paymentMDRSyncResponse >> " + paymentMDRSyncResponse.toString());

					if (paymentMDRSyncResponse != null && paymentMDRSyncResponse.getResponseCode().equals("0000")) {
						status = true;
					} else {
						status = false;
					}

				} catch (Exception e) {
					e.printStackTrace();
					status = false;
				}

			} catch (MalformedURLException e) {
				logger.error("Failed to Send MDRData To Payment : ", e);
				status = false;
			} catch (IOException e) {
				status = false;
			}
		}

		logger.error("status >>> " + String.valueOf(status), null);
		if (status) {
			try {
				regService.updateAPIKeyAndActivationCode(paymentMDRSyncResponse.getResponseData().getMobiApiKey(),
						paymentMDRSyncResponse.getResponseData().getActivationCode(), quotationId, "", "");
				logger.info("Email will be send for the Order - " + orderId);
				quotationService.SendWelcomeLetterEmail(orderId);
				regService.UpdateSuccessStatusForRegistration(orderId);
				return "MDR Sync success";
			} catch (Exception e) {
				e.printStackTrace();
				return "MDR Sync failed, please try again later";
			}

		} else {
			return "MDR Sync failed, please try again later";
		}

	}

	@Override
	public void UpdateMerchantRegistration(MerchantRegistration registration) {
		regDAO.UpdateMerchantRegistration(registration);
	}

	@Override
	public MerchRegDto AssignMerchRegData(MerchantRegistration reg, int flag) {
		MerchantRegistration registration = GetRegistrationOrderByID(String.valueOf(reg.getId()));
		
		

		MerchRegDto regReq = new MerchRegDto();

		if (registration.getOrder().getQuotation().getOrderType().equals("Migration")) {
			regReq.setService(Constants.MIGRATION_MERCH_REG);
		} else {
			regReq.setService(Constants.REGULAR_MERCH_REG);
		}
		logger.info("reg id is and owner count is "+reg.getId()+ ""+registration.getOwnerCount()); 
		System.out.println();

		regReq.setIsEzywirePlus(registration.getIsEzywirePlus() == null || registration.getUmEzywireMID().isEmpty()
				|| registration.getUmEzywireMID() == null || registration.getUmEzywireMID().isEmpty() ? "Normal"
						: registration.getIsEzywirePlus());

		regReq.setEnblBoth(registration.getEnblBoth());
		regReq.setiSwitchDiscount(registration.getiSwitchDiscount());
		regReq.setMid(registration.getEzywireMID());
		regReq.setEzymotoMid(registration.getEzylinkMID());
		regReq.setEzyrecMid(registration.getEzyrecplusMID());
		regReq.setEzywayMid(registration.getEzywayMID());
		regReq.setEzysplitMid(registration.getEzysplitMID());
		regReq.setMasterMerchantId(registration.getOrder().getMasterMerchant());
//		Ezy Moto vcc added

		if (registration.getEzyMotoVcc() == null) {
			regReq.setEzyMotoVcc("No");
		} else {
			regReq.setEzyMotoVcc(registration.getEzyMotoVcc());
		}

//		Enable Payment Method 
		regReq.setEnableCard(registration.getCard());
		regReq.setEnableEwallet(registration.getEWallet());
		regReq.setEnableFpx(registration.getFPX());

		if (!registration.getReRegister().trim().equals("")) {
			try {
				System.out.println("Date >> " + modifyDateLayout(registration.getReRegister()));
				regReq.setReActivateDate(modifyDateLayout(registration.getReRegister()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			regReq.setReActivateDate(registration.getReRegister());
		}

		regReq.setIsCombo(registration.getEzyComboEnable() == null ? "No" : registration.getEzyComboEnable());

		if (registration.getOrder().getQuotation().getAcquirer().equals("Paydee")) {
			regReq.setMerchantType("P");
		} else {
			regReq.setMerchantType("U");
		}

		regReq.setUmMid(registration.getUmEzywireMID());
		regReq.setUmEzymotoMid(registration.getUmEzylinkMID());
		regReq.setUmEzyrecMid(registration.getUmEzyrecplusMID());
		regReq.setUmEzywayMid(registration.getUmEzywayMID());
		regReq.setUmEzysplitMid(registration.getUmEzysplitMID());
		regReq.setMasterMerchantId(registration.getOrder().getMasterMerchant());

		regReq.setAgentName(registration.getOrder().getQuotation().getSalesPerson().getEmail());
		regReq.setRegisteredName(registration.getOrder().getMerchantTradingName());
		regReq.setBusinessName(registration.getOrder().getBusinessName());
		regReq.setBusinessRegNo(registration.getOrder().getRegistrationId());
		regReq.setRegisteredAddress(registration.getOrder().getAddressLine());
		regReq.setBusinessAddress(registration.getOrder().getAddressLine());
		regReq.setMailingAddress(registration.getOrder().getAddressLine());

		regReq.setName(registration.getOrder().getAuthContactPersonName());
		regReq.setContactNo(registration.getOrder().getAuthContactPersonPhone());
		regReq.setEmail(registration.getOrder().getQuotation().getContact().getEmail());

		regReq.setSalutation(registration.getOrder().getSalutation());
		regReq.setOfficeEmail(registration.getOrder().getOfficeEmail());
		regReq.setWebsite(registration.getOrder().getWebsiteUrl());

		regReq.setOfficeNo(registration.getOrder().getOfficeNo());
		regReq.setFaxNo(registration.getOrder().getOfficeNo());
		regReq.setBankName(registration.getOrder().getBankName());
		regReq.setBankAccNo(registration.getOrder().getAccountNo());

		String city = quotationService.GetUmobileCityByID(registration.getOrder().getCity());
		String state = quotationService.GetUmobileStateByID(registration.getOrder().getState());
		String businessType = "";
		CompanyType companyType = null;
		String natureOfBusiness = "";
		String accountType = "";

		if (!registration.getOrder().getBusinessType().equals("0")) {
			businessType = quotationService.GetBusinessTypeByID(registration.getOrder().getBusinessType());
		}

		if (!registration.getOrder().getCompanyType().equals("0")) {
			companyType = quotationService.GetCompanyTypeByID(registration.getOrder().getCompanyType());
		}

		if (registration.getOrder().getNatureOfBusiness() != 0) {
			natureOfBusiness = quotationService.GetNatureOfBusinessByID(registration.getOrder().getNatureOfBusiness());
		}

		if (registration.getOrder().getAccountType() != 0) {
			accountType = quotationService.GetAccountTypeByID(registration.getOrder().getAccountType());
		}

		regReq.setBusinessState(state);
		regReq.setBusinessCity(city);

		regReq.setBusinessPostCode(registration.getOrder().getPostCode());
		regReq.setReferralId(registration.getOrder().getQuotation().getRefreeMID());
		regReq.setWavierMonth(registration.getWaiverMonth());
		regReq.setTradingName(registration.getOrder().getMerchantTradingName());
		regReq.setYearIncorporated(registration.getOrder().getDateIncorporated());
		regReq.setSignedPackage(registration.getSignedPackage());

		regReq.setNoOfReaders(registration.getNoOfReader());
		regReq.setBusinessType(businessType);
		regReq.setCompanyType(companyType.getName());
		regReq.setNatureOfBusiness(natureOfBusiness);
		regReq.setAccountType(accountType);
		regReq.setDocuments(registration.getDocuments());
		regReq.setStatusRemarks(registration.getStatusRemarks());

		regReq.setMdr(registration.getMdr());
		regReq.setPreAuth(String.valueOf(registration.getPreAuth()));
		regReq.setAutoSettled(String.valueOf(registration.getAutoSettled()));
		regReq.setBankOTP(String.valueOf(registration.getBankOTP()));
		regReq.setiSwitchEnable(String.valueOf(registration.getiSwitchEnable()));
		regReq.setOwnerCount(String.valueOf(registration.getOwnerCount()));

		regReq.setMerchantSector(
				registration.getOrder().getMerchantSector() == null ? "" : registration.getOrder().getMerchantSector());
		regReq.setMerchantCategory(registration.getOrder().getMerchantCategory() == null ? ""
				: registration.getOrder().getMerchantCategory());

		int directorCount = 0;
		for (Director director : registration.getOrder().getDirectors()) {
			directorCount++;
			switch (directorCount) {
			case 1:
				regReq.setOwnerSalutation1(registration.getOrder().getSalutation());
				regReq.setOwnerName1(director.getName());
				regReq.setPassportNo1(director.getIdNo());
				regReq.setOwnerContactNo1(director.getContactNo());
				regReq.setResidentialAddress1(director.getAddress());
				break;

			case 2:
				regReq.setOwnerSalutation2(registration.getOrder().getSalutation());
				regReq.setOwnerName2(director.getName());
				regReq.setPassportNo2(director.getIdNo());
				regReq.setOwnerContactNo2(director.getContactNo());
				regReq.setResidentialAddress2(director.getAddress());
				break;

			case 3:
				regReq.setOwnerSalutation3(registration.getOrder().getSalutation());
				regReq.setOwnerName3(director.getName());
				regReq.setPassportNo3(director.getIdNo());
				regReq.setOwnerContactNo3(director.getContactNo());
				regReq.setResidentialAddress3(director.getAddress());
				break;

			case 4:
				regReq.setOwnerSalutation4(registration.getOrder().getSalutation());
				regReq.setOwnerName4(director.getName());
				regReq.setPassportNo4(director.getIdNo());
				regReq.setOwnerContactNo4(director.getContactNo());
				regReq.setResidentialAddress4(director.getAddress());
				break;

			case 5:
				regReq.setOwnerSalutation5(registration.getOrder().getSalutation());
				regReq.setOwnerName5(director.getName());
				regReq.setPassportNo5(director.getIdNo());
				regReq.setOwnerContactNo5(director.getContactNo());
				regReq.setResidentialAddress5(director.getAddress());
				break;

			default:
				break;
			}
		}

		return regReq;
	}

	private String modifyDateLayout(String inputDate) throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(inputDate);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	@Override
	public String SendMerchantRegToPayment(MerchRegDto regReq, String merchantRegistrationId, int flag) {

		logger.info("SendToPaymentServer Module Started");

		HashMap<String, String> resdata = null;

		logger.info("Request Parameters to be posted : " + regReq.toString());
		logger.info("Request Parameters to be posted getiSwitchEnable : " + regReq.getiSwitchEnable());

		String merchRegUrl = env.getProperty("merchReg.paymentServerUrl");

		try {

			URL url = new URL(merchRegUrl);
			logger.info("\nSending 'POST' request to URL : " + url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			ObjectMapper mapper = new ObjectMapper();
			OutputStream wr = new DataOutputStream(conn.getOutputStream());

			mapper.writeValue(wr, regReq);

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

			MerchantRegistrationResponseData merchantRegistrationResponseData = null;

			try {
				merchantRegistrationResponseData = new Gson().fromJson(response.toString(),
						MerchantRegistrationResponseData.class);
				logger.info(
						"merchantRegistrationResponseData >> " + merchantRegistrationResponseData.getResponseCode());
			} catch (Exception e) {
				logger.error("SendMerchantRegToPayment", e);
			}

			logger.info(merchantRegistrationResponseData.getResponseDescription());

			if (String.valueOf(merchantRegistrationResponseData.getResponseCode()).equals("0000")) {
				saveWebPortalCredentials(merchantRegistrationId, merchantRegistrationResponseData.getResponseData());
			}

			if (flag != 1 && String.valueOf(merchantRegistrationResponseData.getResponseCode()).equals("0000")
					&& merchantRegistrationResponseData.getResponseData().getFpxMid() != null) {
				updateMidForMerchantRegistration(merchantRegistrationId,
						merchantRegistrationResponseData.getResponseData());
			}

			logger.info("End Of SendPaymentServer");
			return merchantRegistrationResponseData.getResponseDescription();
		} catch (MalformedURLException e) {
			logger.error("Failed to Connect Payment Server : ", e);
			return "Failed to Connect.";

		} catch (IOException e) {
			logger.error("Failed to Connect Payment Server : ", e);
			return "Failed to Connect.";
		} catch (Exception e) {
			logger.error("Failed to Connect Payment Server : ", e);
			return "Failed to Connect.";
		}

	}

	private void saveWebPortalCredentials(String merchantRegistrationId, MerchantRegistrationResponse responseData) {
		regDAO.saveWebPortalCredentials(merchantRegistrationId, responseData);
	}

	private void updateMidForMerchantRegistration(String merchantRegistrationId,
			MerchantRegistrationResponse responseData) {
		regDAO.updateMidForMerchantRegistration(merchantRegistrationId, responseData);
	}

	@Override
	public void setSignatureForSalesRole(int signatureId, String userName) {
		userDAO.setSignatureForSalesRole(signatureId, userName);
	}

	@Override
	public MDRSyncToPaymentServer AssignMDRSyncDataEzysplit(int orderLineId, EzysplitRateID ezysplitRateIDs,
			String chkUpdateMDR) {
		OrderLines line = quotationService.getOrderLineById(orderLineId);
		MDRSyncToPaymentServer sync = new MDRSyncToPaymentServer();

		sync.setRateId03(ezysplitRateIDs.getRateID03());
		sync.setRateId06(ezysplitRateIDs.getRateID06());
		sync.setRateId09(ezysplitRateIDs.getRateID09());
		sync.setRateId12(ezysplitRateIDs.getRateID12());

		if (line.getQuotationEzysplitMDRRate().getfPXMDR_Percent() != 0) {
			sync.setFpxMercAmt(line.getQuotationEzysplitMDRRate().getfPXMDR_RM());
			sync.setFpxHostAmt(Constants.fpxEcommHostAmt);
			sync.setFpxMobiAmt(line.getQuotationEzysplitMDRRate().getfPXMDR_RM() - Constants.fpxEcommHostAmt);
			sync.setFpxTxnMdr(line.getQuotationEzysplitMDRRate().getfPXMDR_RM());

			sync.setBoostEcomMercMdr(line.getQuotationEzysplitMDRRate().getBoostMDREcomm());
			sync.setBoostEcomHostMdr(Constants.boostEcommHostAmt);
			sync.setBoostEcomMobiMdr(
					line.getQuotationEzysplitMDRRate().getBoostMDREcomm() - Constants.boostEcommHostAmt);

			sync.setGrabEcomMercMdr(line.getQuotationEzysplitMDRRate().getGrabMDREcomm());
			sync.setGrabEcomHostMdr(Constants.grabEcommHostAmt);
			sync.setGrabEcomMobiMdr(line.getQuotationEzysplitMDRRate().getGrabMDREcomm() - Constants.grabEcommHostAmt);

			
			
			
			sync.setTngEcomMercMdr(line.getQuotationEzysplitMDRRate().getTngMDREcomm());
			sync.setTngEcomHostMdr(Constants.tngEcommHostAmt);
			sync.setTngEcomMobiMdr(line.getQuotationEzysplitMDRRate().getTngMDREcomm() - Constants.tngEcommHostAmt);
			
			
			sync.setShopeepayEcomMercMdr(line.getQuotationEzysplitMDRRate().getShopeepayMDREcomm());
			sync.setShopeepayEcomHostMdr(Constants.shopeepayEcommHostAmt);
			sync.setShopeepayEcomMobiMdr(line.getQuotationEzysplitMDRRate().getShopeepayMDREcomm() - Constants.shopeepayEcommHostAmt);
			
			
			sync.setTngQrMercMdr(line.getQuotationEzysplitMDRRate().getTngMDRQR());
			sync.setTngQrHostMdr(Constants.tngQRHostAmt);
			sync.setTngQrMobiMdr(line.getQuotationEzysplitMDRRate().getTngMDRQR() - Constants.tngQRHostAmt);
			
			
			sync.setShopeepayQrMercMdr(line.getQuotationEzysplitMDRRate().getShopeepayMDRQR());
			sync.setShopeepayQrHostMdr(Constants.shopeepayQRHostAmt);
			sync.setShopeepayQrMobiMdr(line.getQuotationEzysplitMDRRate().getShopeepayMDRQR() - Constants.shopeepayQRHostAmt);
			
			
			
			
			
			// Currently, the rates are same both for ECOMM & QR

			sync.setBoostQrMercMdr(line.getQuotationEzysplitMDRRate().getBoostMDRQR());
			sync.setBoostQrHostMdr(Constants.boostQRHostAmt);
			sync.setBoostQrMobiMdr(line.getQuotationEzysplitMDRRate().getBoostMDRQR() - Constants.boostQRHostAmt);

			sync.setGrabQrMercMdr(line.getQuotationEzysplitMDRRate().getGrabMDRQR());
			sync.setGrabQrHostMdr(Constants.grabQRHostAmt);
			sync.setGrabQrMobiMdr(line.getQuotationEzysplitMDRRate().getGrabMDRQR() - Constants.grabQRHostAmt);
		} else {
			sync.setFpxMercAmt(0.00);
			sync.setFpxHostAmt(0.00);
			sync.setFpxMobiAmt(0.00);
			sync.setFpxTxnMdr(0.00);

			sync.setBoostEcomMercMdr(0.00);
			sync.setBoostEcomHostMdr(0.00);
			sync.setBoostEcomMobiMdr(0.00);

			sync.setGrabEcomMercMdr(0.00);
			sync.setGrabEcomHostMdr(0.00);
			sync.setGrabEcomMobiMdr(0.00);
			
			
			
			sync.setTngEcomMercMdr(0.00);
			sync.setTngEcomHostMdr(0.00);
			sync.setTngEcomMobiMdr(0.00);
			
			
			sync.setTngQrMercMdr(0.00);
			sync.setTngQrHostMdr(0.00);
			sync.setTngQrMobiMdr(0.00);
			
			
			sync.setShopeepayEcomMercMdr(0.00);
			sync.setShopeepayEcomHostMdr(0.00);
			sync.setShopeepayEcomMobiMdr(0.00);
			
			
			sync.setShopeepayQrMercMdr(0.00);
			sync.setShopeepayQrHostMdr(0.00);
			sync.setShopeepayQrMobiMdr(0.00);
			
			
			

			// Currently, the rates are same both for ECOMM & QR

			sync.setBoostQrMercMdr(0.00);
			sync.setBoostQrHostMdr(0.00);
			sync.setBoostQrMobiMdr(0.00);

			sync.setGrabQrMercMdr(0.00);
			sync.setGrabQrHostMdr(0.00);
			sync.setGrabQrMobiMdr(0.00);
		}

		sync.setService(Constants.SPLIT_MDR_SYNC_UPDATE);

		sync.setPayLater(line.getQuotationEzysplitMDRRate().getPayLater());

		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		String formattedAmount = decimalFormat.format(line.getQuotationEzysplitMDRRate().getUnitPrice());

		sync.setAmount(formattedAmount);
		sync.setSettlePeriod(String.valueOf(line.getQuotationEzysplitMDRRate().getProductSettlement()));

		sync.setEzysplitMid(line.getMid());
		sync.setEzysplitTid(line.getTid());
		sync.setEzylinkTid(line.getEzylinkTid());
		sync.setEzywayTid(line.getEzywayTid());

		if (!line.getDtl().equals("")) {
			DecimalFormat df = new DecimalFormat("#.00");
			String formattedDtl = df.format(Double.valueOf(line.getDtl()));

			logger.info("Formatted DTL : " + formattedDtl);

			sync.setDtl(formattedDtl);
		} else {
			sync.setDtl("");
		}

		sync.setHashKey(line.getHashkey());
		sync.setCallBackUrl(line.getCallbackURL());
		sync.setSubscription(line.getQuotationEzysplitMDRRate().getSubscription());
		sync.setProductType(line.getQuotationEzysplitMDRRate().getProductType());
		sync.setForceReferral("Yes");
		// logger.info(line.getQuotation().getOrder().getMerchantRegistration().getId());
		sync.setRefereeMID(line.getQuotation().getRefreeMID());

		sync.setBusinessRegNo(line.getQuotation().getRegistrationNumber());

		return sync;
	}

	@Override
	public EzysplitRateID GetRateIDs(mdrSyncDto sync) {

		EzysplitRateID id = new EzysplitRateID();
		try {
			for (int i = 1; i <= 4; i++) {

				RateIDInsertEzysplit rate = new RateIDInsertEzysplit();

				rate.setService(Constants.SPLIT_MDR_RATES_INSERT);

				double LocCreditMcHost = 1.40;
				rate.setLocCreditMcHost(String.valueOf(LocCreditMcHost));
				rate.setProductId("EZYSPLIT");

				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter rateIDateTimeFormatter = DateTimeFormatter.ofPattern("YYYYMMddHHmmssSSS");

				rate.setCreatedBy("ADMIN");
				rate.setCreatedDate("");
				rate.setUpdatedBy("ADMIN");
				rate.setUpdatedDate("");

				rate.setUserReference("EZYSPLITMDR");
				rate.setValidFrom("01-01-0001");
				rate.setValidTo("31-12-2999");

				rate.setBoostMdr("");
				rate.setGrabpayMdr("");
				rate.setHostType("U-Mobile");
				rate.setIpAddress("1.1.1.1");

				switch (i) {
				case 1:

					String rateID03 = "EZYSPLIT" + sync.getQuotationId() + rateIDateTimeFormatter.format(now);
					id.setRateID03(rateID03);

					rate.setRateId(rateID03);
					rate.setLocCreditMcMer(String.valueOf(sync.getLoc_Credit_Merch_Master_Ezysplit_Insta3()));
					rate.setLocCreditMcCust(String.valueOf(sync.getLoc_Credit_Cus_Master_Ezysplit_Insta3()));
					rate.setLocCreditMcMobi(String.valueOf((sync.getLoc_Credit_Merch_Master_Ezysplit_Insta3()
							+ sync.getLoc_Credit_Cus_Master_Ezysplit_Insta3()) - LocCreditMcHost));

					SendRateIDValuesToPaymentDB(rate);
					break;

				case 2:

					String rateID06 = "EZYSPLIT" + sync.getQuotationId() + rateIDateTimeFormatter.format(now);
					rate.setRateId(rateID06);
					id.setRateID06(rateID06);
					rate.setLocCreditMcMer(String.valueOf(sync.getLoc_Credit_Merch_Master_Ezysplit_Insta6()));
					rate.setLocCreditMcCust(String.valueOf(sync.getLoc_Credit_Cus_Master_Ezysplit_Insta6()));
					rate.setLocCreditMcMobi(String.valueOf((sync.getLoc_Credit_Merch_Master_Ezysplit_Insta6()
							+ sync.getLoc_Credit_Cus_Master_Ezysplit_Insta6()) - LocCreditMcHost));

					SendRateIDValuesToPaymentDB(rate);
					break;

				case 3:

					String rateID09 = "EZYSPLIT" + sync.getQuotationId() + rateIDateTimeFormatter.format(now);
					rate.setRateId(rateID09);
					id.setRateID09(rateID09);

					rate.setLocCreditMcMer(String.valueOf(sync.getLoc_Credit_Merch_Master_Ezysplit_Insta9()));
					rate.setLocCreditMcCust(String.valueOf(sync.getLoc_Credit_Cus_Master_Ezysplit_Insta9()));
					rate.setLocCreditMcMobi(String.valueOf((sync.getLoc_Credit_Merch_Master_Ezysplit_Insta9()
							+ sync.getLoc_Credit_Cus_Master_Ezysplit_Insta9()) - LocCreditMcHost));

					SendRateIDValuesToPaymentDB(rate);
					break;

				case 4:

					String rateID12 = "EZYSPLIT" + sync.getQuotationId() + rateIDateTimeFormatter.format(now);
					rate.setRateId(rateID12);
					id.setRateID12(rateID12);

					rate.setLocCreditMcMer(String.valueOf(sync.getLoc_Credit_Merch_Master_Ezysplit_Insta12()));
					rate.setLocCreditMcCust(String.valueOf(sync.getLoc_Credit_Cus_Master_Ezysplit_Insta12()));
					rate.setLocCreditMcMobi(String.valueOf((sync.getLoc_Credit_Merch_Master_Ezysplit_Insta12()
							+ sync.getLoc_Credit_Cus_Master_Ezysplit_Insta12()) - LocCreditMcHost));

					SendRateIDValuesToPaymentDB(rate);
					break;

				default:
					break;
				}

			}

			return id;
		} catch (Exception e) {
			logger.error("Failed to GetRateIDs : ", e);
			return id;
		}

	}

	private void SendRateIDValuesToPaymentDB(RateIDInsertEzysplit rate) {

		logger.info("SendRateIDValuesToPaymentDB Module Started");
		HashMap<String, String> resdata = null;
		logger.info("Rate ID Data to be posted to Payment DB: " + rate.toString());
		String mdrInsertURL = env.getProperty("mdrSync.rateIDInsertUrl");

		try {

			URL url = new URL(mdrInsertURL);
			logger.info("\nSending 'POST' request to URL : " + url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			ObjectMapper mapper = new ObjectMapper();
			OutputStream wr = new DataOutputStream(conn.getOutputStream());

			mapper.writeValue(wr, rate);

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

			resdata = ServiceResponse.splitData(response.toString());
			if (resdata.containsKey("responseDescription")) {
				logger.info(resdata.get("responseDescription"));
			}

			logger.info("End Of MDR Insert");
		} catch (MalformedURLException e) {
			logger.error("Failed to Send RateID Values to Payment DB : ", e);
		}

		catch (IOException e) {
			logger.error("Failed to Send RateID Values to Payment DB : ", e);
		}

	}

	@Override
	public void UpdateSuccessStatusForRegistration(String orderId) {
		regDAO.UpdateSuccessStatusForRegistration(orderId);

	}

	@Override
	public void updateAPIKeyAndActivationCode(String mobiApiKey, String activationCode, int quotationId,
			String password, String userName) {
		regDAO.updateAPIKeyAndActivationCode(mobiApiKey, activationCode, quotationId, password, userName);
	}

	@Override
	public Object GetMerchantRegistrationListMobile(String username) {
		return regDAO.GetMerchantRegistrationListMobile(username);
	}

	@Override
	public Object getGrapPayReport() {
		// TODO Auto-generated method stub

		ArrayList<MerchantRegistration> merchantRegistrationList = (ArrayList<MerchantRegistration>) regDAO
				.getGrapPayReport();

		if (merchantRegistrationList != null) {
			ArrayList<GrabPayReportResponseData> grabPayReportResponseDatas = new ArrayList<>();

			for (MerchantRegistration merchantRegistration : merchantRegistrationList) {

				Order order = merchantRegistration.getOrder();

				UmobileMCC umobileMCC = regDAO.getUmobileMccList(order.getVisaMCC());

				if (umobileMCC != null) {
					List<OrderLines> ordreLines = order.getQuotation().getOrderLines();
					for (OrderLines ordreLine : ordreLines) {

						GrabPayReportResponseData dataSet = new GrabPayReportResponseData(String.valueOf(order.getId()),
								order.getBusinessName(), order.getMerchantTradingName(), order.getRegistrationId(),
								umobileMCC.getValue(), umobileMCC.getName(), order.getAddressLine(),
								order.getQuotation().getState(), order.getQuotation().getCity(), order.getPostCode(),
								order.getGrabPayLatitude(), order.getGrabPayLongitude(), "1", ordreLine.getTid());

						grabPayReportResponseDatas.add(dataSet);
					}
				}
			}
			return new CommonResponseData("0000", "Success", grabPayReportResponseDatas);
		} else {
			return new CommonResponseData("0001", "Unable to get the Grap Pay Report", null);
		}
	}

	@Override
	public Object submitEWalletMDR(String registrationID) {

		MerchantRegistration merchantRegistration = regDAO
				.getMerchantRegistrationDetailsByRegistrationId(registrationID);

		String[] products = new String[] { "BOOST", "FPX","TNG","SHOPPY" };
		WalletMDRSyncRequestData requestData = new WalletMDRSyncRequestData();

		boolean flag = true;

		for (String string : products) {

			requestData.setService("MDR_SYNC");
			requestData.setPayLater("No");
			requestData.setSettlePeriod("3");
			requestData.setForceReferral("NO");
			requestData.setRefereeMID("");
			requestData.setSubscription("365");
			requestData.setBoostTid(merchantRegistration.getBoostTid()); // Default

			if (merchantRegistration.getPreAuth() == null) {
				requestData.setPreAuth("No");
			} else {
				requestData.setPreAuth(merchantRegistration.getPreAuth());
			}

			if (string.equals("BOOST")) {

				requestData.setMid(merchantRegistration.getBoostMid());
				requestData.setTid(merchantRegistration.getBoostTid());

				requestData.setSubscription("365");
				requestData.setProductType("BOOST-EZYWAY");

				requestData.setForceReferral("NO");
				requestData.setDeviceID("");
				requestData.setRefNo("");
				requestData.setHostType("");

				requestData.setFpxHostAmt(0.0);
				requestData.setFpxMercAmt(0.0);
				requestData.setFpxMobiAmt(0.0);
				requestData.setFpxTxnMdr(0.0);
				
				requestData.setShopeepayEcomHostMdr(0.0);
				requestData.setShopeepayEcomMercMdr(0.0);
				requestData.setShopeepayEcomMobiMdr(0.0);
				
				
				requestData.setShopeepayQrHostMdr(0.0);
				requestData.setShopeepayQrMercMdr(0.0);
				requestData.setShopeepayQrMobiMdr(0.0);
				
				requestData.setTngEcomHostMdr(0.0);
				requestData.setTngEcomMercMdr(0.0);
				requestData.setTngEcomMobiMdr(0.0);
				
				
				requestData.setTngQrHostMdr(0.0);
				requestData.setTngQrMercMdr(0.0);
				requestData.setTngQrMobiMdr(0.0);
				
				

				requestData.setBoostEcomHostMdr(Constants.boostEcommHostAmt);
				requestData.setBoostEcomMercMdr(merchantRegistration.getOrder().getQuotation().getOrderLines().get(0)
						.getQuotationMDRRate().getBoostMDREcomm());
				requestData.setBoostEcomMobiMdr(merchantRegistration.getOrder().getQuotation().getOrderLines().get(0)
						.getQuotationMDRRate().getBoostMDREcomm() - Constants.boostEcommHostAmt);

				requestData.setBoostQrHostMdr(Constants.boostQRHostAmt);
				requestData.setBoostQrMercMdr(merchantRegistration.getOrder().getQuotation().getOrderLines().get(0)
						.getQuotationMDRRate().getBoostMDRQR());
				requestData.setBoostQrMobiMdr(merchantRegistration.getOrder().getQuotation().getOrderLines().get(0)
						.getQuotationMDRRate().getBoostMDRQR() - Constants.boostQRHostAmt);

				requestData.setGrabEcomHostMdr(0.0);
				requestData.setGrabEcomMercMdr(0.0);
				requestData.setGrabEcomMobiMdr(0.0);

				requestData.setGrabQrHostMdr(0.0);
				requestData.setGrabQrMercMdr(0.0);
				requestData.setGrabQrMobiMdr(0.0);

				requestData.setBusinessRegNo(merchantRegistration.getOrder().getRegistrationId());
				requestData.setCardBrand(string);

				requestData.setDebitLocalMerchantMDR(0);
				requestData.setDebitLocalHostMDR(0);

				requestData.setCreditLocalMerchantMDR(0.0);
				requestData.setCreditLocalHostMDR(0.0);

				requestData.setDebitForeignMerchantMDR(0.0);
				requestData.setDebitForeignHostMDR(0.0);

				requestData.setCreditForeignMerchantMDR(0.0);
				requestData.setCreditForeignHostMDR(0.0);

			} 
			
			else if (string.equals("TNG")) {
				requestData.setMid(merchantRegistration.getTngMid());
				requestData.setTid(merchantRegistration.getTngTid());

				requestData.setSubscription("365");
				requestData.setProductType("TNG-EZYWAY");

				requestData.setForceReferral("NO");
				requestData.setDeviceID("");
				requestData.setRefNo("");
				requestData.setHostType("");

				requestData.setFpxHostAmt(0.0);
				requestData.setFpxMercAmt(0.0);
				requestData.setFpxMobiAmt(0.0);
				requestData.setFpxTxnMdr(0.0);

				requestData.setBoostEcomHostMdr(0.0);
				requestData.setBoostEcomMercMdr(0.0);
				requestData.setBoostEcomMobiMdr(0.0);

				requestData.setBoostQrHostMdr(0.0);
				requestData.setBoostQrMercMdr(0.0);
				requestData.setBoostQrMobiMdr(0.0);
				
				requestData.setGrabEcomHostMdr(0.0);
				requestData.setGrabEcomMercMdr(0.0);
				requestData.setGrabEcomMobiMdr(0.0);

				requestData.setGrabQrHostMdr(0.0);
				requestData.setGrabQrMercMdr(0.0);
				requestData.setGrabQrMobiMdr(0.0);
				
				requestData.setShopeepayEcomHostMdr(0.0);
				requestData.setShopeepayEcomMercMdr(0.0);
				requestData.setShopeepayEcomMobiMdr(0.0);
				
				
				requestData.setShopeepayQrHostMdr(0.0);
				requestData.setShopeepayQrMercMdr(0.0);
				requestData.setShopeepayQrMobiMdr(0.0);
				
				
				

				requestData.setTngEcomHostMdr(Constants.tngEcommHostAmt);
				requestData.setTngEcomMercMdr(merchantRegistration.getOrder().getQuotation().getOrderLines().get(0)
						.getQuotationMDRRate().getTngMDREcomm());
				requestData.setTngEcomMobiMdr(merchantRegistration.getOrder().getQuotation().getOrderLines().get(0)
						.getQuotationMDRRate().getTngMDREcomm() - Constants.tngEcommHostAmt);

				requestData.setTngQrHostMdr(Constants.tngQRHostAmt);
				requestData.setTngQrMercMdr(merchantRegistration.getOrder().getQuotation().getOrderLines().get(0)
						.getQuotationMDRRate().getTngMDRQR());
				requestData.setTngQrMobiMdr(merchantRegistration.getOrder().getQuotation().getOrderLines().get(0)
						.getQuotationMDRRate().getTngMDRQR() - Constants.tngQRHostAmt);

				requestData.setBusinessRegNo(merchantRegistration.getOrder().getRegistrationId());
				requestData.setCardBrand(string);

				requestData.setDebitLocalMerchantMDR(0.0);
				requestData.setDebitLocalHostMDR(0.0);

				requestData.setCreditLocalMerchantMDR(0.0);
				requestData.setCreditLocalHostMDR(0.0);

				requestData.setDebitForeignMerchantMDR(0.0);
				requestData.setDebitForeignHostMDR(0.0);

				requestData.setCreditForeignMerchantMDR(0.0);
				requestData.setCreditForeignHostMDR(0.0);
			} 
			
			else if (string.equals("SHOPPY")) {
				requestData.setMid(merchantRegistration.getShopeepayMid());
				requestData.setTid(merchantRegistration.getShopeepayTid());

				requestData.setSubscription("365");
				requestData.setProductType("SHOPPY-EZYWAY");

				requestData.setForceReferral("NO");
				requestData.setDeviceID("");
				requestData.setRefNo("");
				requestData.setHostType("");

				requestData.setFpxHostAmt(0.0);
				requestData.setFpxMercAmt(0.0);
				requestData.setFpxMobiAmt(0.0);
				requestData.setFpxTxnMdr(0.0);

				requestData.setBoostEcomHostMdr(0.0);
				requestData.setBoostEcomMercMdr(0.0);
				requestData.setBoostEcomMobiMdr(0.0);

				requestData.setBoostQrHostMdr(0.0);
				requestData.setBoostQrMercMdr(0.0);
				requestData.setBoostQrMobiMdr(0.0);
				
				requestData.setGrabEcomHostMdr(0.0);
				requestData.setGrabEcomMercMdr(0.0);
				requestData.setGrabEcomMobiMdr(0.0);

				requestData.setGrabQrHostMdr(0.0);
				requestData.setGrabQrMercMdr(0.0);
				requestData.setGrabQrMobiMdr(0.0);
				
				
				requestData.setTngEcomHostMdr(0.0);
				requestData.setTngEcomMercMdr(0.0);
				requestData.setTngEcomMobiMdr(0.0);
				
				
				requestData.setTngQrHostMdr(0.0);
				requestData.setTngQrMercMdr(0.0);
				requestData.setTngQrMobiMdr(0.0);
				
				
				
				
				

				requestData.setShopeepayEcomHostMdr(Constants.shopeepayEcommHostAmt);
				requestData.setShopeepayEcomMercMdr(merchantRegistration.getOrder().getQuotation().getOrderLines().get(0)
						.getQuotationMDRRate().getShopeepayMDREcomm());
				requestData.setShopeepayEcomMobiMdr(merchantRegistration.getOrder().getQuotation().getOrderLines().get(0)
						.getQuotationMDRRate().getShopeepayMDREcomm() - Constants.shopeepayEcommHostAmt);

				requestData.setShopeepayQrHostMdr(Constants.shopeepayQRHostAmt);
				requestData.setShopeepayQrMercMdr(merchantRegistration.getOrder().getQuotation().getOrderLines().get(0)
						.getQuotationMDRRate().getShopeepayMDRQR());
				requestData.setShopeepayQrMobiMdr(merchantRegistration.getOrder().getQuotation().getOrderLines().get(0)
						.getQuotationMDRRate().getShopeepayMDRQR() - Constants.shopeepayQRHostAmt);

				requestData.setBusinessRegNo(merchantRegistration.getOrder().getRegistrationId());
				requestData.setCardBrand(string);

				requestData.setDebitLocalMerchantMDR(0.0);
				requestData.setDebitLocalHostMDR(0.0);

				requestData.setCreditLocalMerchantMDR(0.0);
				requestData.setCreditLocalHostMDR(0.0);

				requestData.setDebitForeignMerchantMDR(0.0);
				requestData.setDebitForeignHostMDR(0.0);

				requestData.setCreditForeignMerchantMDR(0.0);
				requestData.setCreditForeignHostMDR(0.0);
			} 
			
			
			
			
			
			
			
			
			
			else if (string.equals("GRAB")) {
				requestData.setMid(merchantRegistration.getGrabMid());
				requestData.setTid(merchantRegistration.getGrabTid());

				requestData.setSubscription("365");
				requestData.setProductType("GRAB-EZYWAY");

				requestData.setForceReferral("NO");
				requestData.setDeviceID("");
				requestData.setRefNo("");
				requestData.setHostType("");

				requestData.setFpxHostAmt(0.0);
				requestData.setFpxMercAmt(0.0);
				requestData.setFpxMobiAmt(0.0);
				requestData.setFpxTxnMdr(0.0);

				requestData.setBoostEcomHostMdr(0.0);
				requestData.setBoostEcomMercMdr(0.0);
				requestData.setBoostEcomMobiMdr(0.0);

				requestData.setBoostQrHostMdr(0.0);
				requestData.setBoostQrMercMdr(0.0);
				requestData.setBoostQrMobiMdr(0.0);
				
				
				requestData.setShopeepayEcomHostMdr(0.0);
				requestData.setShopeepayEcomMercMdr(0.0);
				requestData.setShopeepayEcomMobiMdr(0.0);
				
				
				requestData.setShopeepayQrHostMdr(0.0);
				requestData.setShopeepayQrMercMdr(0.0);
				requestData.setShopeepayQrMobiMdr(0.0);
				
				requestData.setTngEcomHostMdr(0.0);
				requestData.setTngEcomMercMdr(0.0);
				requestData.setTngEcomMobiMdr(0.0);
				
				
				requestData.setTngQrHostMdr(0.0);
				requestData.setTngQrMercMdr(0.0);
				requestData.setTngQrMobiMdr(0.0);
				
				
				
				

				requestData.setGrabEcomHostMdr(Constants.grabEcommHostAmt);
				requestData.setGrabEcomMercMdr(merchantRegistration.getOrder().getQuotation().getOrderLines().get(0)
						.getQuotationMDRRate().getGrabMDREcomm());
				requestData.setGrabEcomMobiMdr(merchantRegistration.getOrder().getQuotation().getOrderLines().get(0)
						.getQuotationMDRRate().getGrabMDREcomm() - Constants.grabEcommHostAmt);

				requestData.setGrabQrHostMdr(Constants.grabQRHostAmt);
				requestData.setGrabQrMercMdr(merchantRegistration.getOrder().getQuotation().getOrderLines().get(0)
						.getQuotationMDRRate().getGrabMDRQR());
				requestData.setGrabQrMobiMdr(merchantRegistration.getOrder().getQuotation().getOrderLines().get(0)
						.getQuotationMDRRate().getGrabMDRQR() - Constants.grabQRHostAmt);

				requestData.setBusinessRegNo(merchantRegistration.getOrder().getRegistrationId());
				requestData.setCardBrand(string);

				requestData.setDebitLocalMerchantMDR(0.0);
				requestData.setDebitLocalHostMDR(0.0);

				requestData.setCreditLocalMerchantMDR(0.0);
				requestData.setCreditLocalHostMDR(0.0);

				requestData.setDebitForeignMerchantMDR(0.0);
				requestData.setDebitForeignHostMDR(0.0);

				requestData.setCreditForeignMerchantMDR(0.0);
				requestData.setCreditForeignHostMDR(0.0);
			} else {
				requestData.setMid(merchantRegistration.getFpxMid());
				requestData.setTid(merchantRegistration.getFpxTid());

				requestData.setSubscription("365");
				requestData.setProductType("FPX-EZYWAY");

				requestData.setForceReferral("NO");
				requestData.setDeviceID("");
				requestData.setRefNo("");
				requestData.setHostType("");

				requestData.setFpxHostAmt(Constants.fpxEcommHostAmt);
				requestData.setFpxMercAmt(merchantRegistration.getOrder().getQuotation().getOrderLines().get(0)
						.getQuotationMDRRate().getfPXMDR_RM());
				requestData.setFpxMobiAmt(merchantRegistration.getOrder().getQuotation().getOrderLines().get(0)
						.getQuotationMDRRate().getfPXMDR_RM() - Constants.fpxEcommHostAmt);
				requestData.setFpxTxnMdr(merchantRegistration.getOrder().getQuotation().getOrderLines().get(0)
						.getQuotationMDRRate().getfPXMDR_RM());

				requestData.setGrabEcomHostMdr(0.0);
				requestData.setGrabEcomMercMdr(0.0);
				requestData.setGrabEcomMobiMdr(0.0);

				requestData.setBoostEcomHostMdr(0.0);
				requestData.setBoostEcomMercMdr(0.0);
				requestData.setBoostEcomMobiMdr(0.0);

				requestData.setBoostQrHostMdr(0.0);
				requestData.setBoostQrMercMdr(0.0);
				requestData.setBoostQrMobiMdr(0.0);

				requestData.setGrabQrHostMdr(0.0);
				requestData.setGrabQrMercMdr(0.0);
				requestData.setGrabQrMobiMdr(0.0);
				
				
				requestData.setShopeepayEcomHostMdr(0.0);
				requestData.setShopeepayEcomMercMdr(0.0);
				requestData.setShopeepayEcomMobiMdr(0.0);
				
				
				requestData.setShopeepayQrHostMdr(0.0);
				requestData.setShopeepayQrMercMdr(0.0);
				requestData.setShopeepayQrMobiMdr(0.0);
				
				requestData.setTngEcomHostMdr(0.0);
				requestData.setTngEcomMercMdr(0.0);
				requestData.setTngEcomMobiMdr(0.0);
				
				
				requestData.setTngQrHostMdr(0.0);
				requestData.setTngQrMercMdr(0.0);
				requestData.setTngQrMobiMdr(0.0);

				requestData.setBusinessRegNo(merchantRegistration.getOrder().getRegistrationId());
				requestData.setCardBrand(string);

				requestData.setDebitLocalMerchantMDR(0.0);
				requestData.setDebitLocalHostMDR(0.0);

				requestData.setCreditLocalMerchantMDR(0.0);
				requestData.setCreditLocalHostMDR(0.0);

				requestData.setDebitForeignMerchantMDR(0.0);
				requestData.setDebitForeignHostMDR(0.0);

				requestData.setCreditForeignMerchantMDR(0.0);
				requestData.setCreditForeignHostMDR(0.0);
			}

			int response = syncEWallet(requestData, merchantRegistration.getOrder().getQuotation().getId(),
					merchantRegistration.getId());
			if (response == 2) {
				flag = false;
			}
		}

		if (flag) {
			return new CommonResponseData("0000", "MDR Submit Successful", null);
		} else {
			return new CommonResponseData("0001", "Unable to submit MDR rate for E-Wallets", null);
		}
	}

	@Override
	public Object submitGrabPayEWalletMDR(String registrationID) {
		MerchantRegistration merchantRegistration = regDAO
				.getMerchantRegistrationDetailsByRegistrationId(registrationID);

		WalletMDRSyncRequestData requestData = new WalletMDRSyncRequestData();

		boolean flag = true;

		requestData.setService("MDR_SYNC");
		requestData.setPayLater("No");
		requestData.setSettlePeriod("3");
		requestData.setForceReferral("NO");
		requestData.setRefereeMID("");
		requestData.setSubscription("365");

		if (merchantRegistration.getPreAuth() == null) {
			requestData.setPreAuth("No");
		} else {
			requestData.setPreAuth(merchantRegistration.getPreAuth());
		}

		requestData.setBoostTid(merchantRegistration.getBoostTid()); // Default

		requestData.setGrabMid(merchantRegistration.getGrabMidAcquired());
		requestData.setMid(merchantRegistration.getGrabMid());
		requestData.setTid(merchantRegistration.getGrabTid());

		requestData.setSubscription("365");
		requestData.setProductType("GRAB-EZYWAY");

		requestData.setForceReferral("NO");
		requestData.setDeviceID("");
		requestData.setRefNo("");
		requestData.setHostType("");

		requestData.setFpxHostAmt(0.0);
		requestData.setFpxMercAmt(0.0);
		requestData.setFpxMobiAmt(0.0);
		requestData.setFpxTxnMdr(0.0);

		requestData.setBoostEcomHostMdr(0.0);
		requestData.setBoostEcomMercMdr(0.0);
		requestData.setBoostEcomMobiMdr(0.0);

		requestData.setBoostQrHostMdr(0.0);
		requestData.setBoostQrMercMdr(0.0);
		requestData.setBoostEcomMobiMdr(0.0);
		
		requestData.setShopeepayEcomHostMdr(0.0);
		requestData.setShopeepayEcomMercMdr(0.0);
		requestData.setShopeepayEcomMobiMdr(0.0);
		
		
		requestData.setShopeepayQrHostMdr(0.0);
		requestData.setShopeepayQrMercMdr(0.0);
		requestData.setShopeepayQrMobiMdr(0.0);
		
		requestData.setTngEcomHostMdr(0.0);
		requestData.setTngEcomMercMdr(0.0);
		requestData.setTngEcomMobiMdr(0.0);
		
		
		requestData.setTngQrHostMdr(0.0);
		requestData.setTngQrMercMdr(0.0);
		requestData.setTngQrMobiMdr(0.0);

		requestData.setGrabEcomHostMdr(Constants.grabEcommHostAmt);
		requestData.setGrabEcomMercMdr(merchantRegistration.getOrder().getQuotation().getOrderLines().get(0)
				.getQuotationMDRRate().getGrabMDREcomm());
		requestData.setGrabEcomMobiMdr(merchantRegistration.getOrder().getQuotation().getOrderLines().get(0)
				.getQuotationMDRRate().getGrabMDREcomm() - Constants.grabEcommHostAmt);

		requestData.setGrabQrHostMdr(Constants.grabQRHostAmt);
		requestData.setGrabQrMercMdr(merchantRegistration.getOrder().getQuotation().getOrderLines().get(0)
				.getQuotationMDRRate().getGrabMDRQR());
		requestData.setGrabQrMobiMdr(merchantRegistration.getOrder().getQuotation().getOrderLines().get(0)
				.getQuotationMDRRate().getGrabMDRQR() - Constants.grabQRHostAmt);

		requestData.setBusinessRegNo(merchantRegistration.getOrder().getRegistrationId());
		requestData.setCardBrand("GRAB");

		requestData.setDebitLocalMerchantMDR(0.0);
		requestData.setDebitLocalHostMDR(0.0);

		requestData.setCreditLocalMerchantMDR(0.0);
		requestData.setCreditLocalHostMDR(0.0);

		requestData.setDebitForeignMerchantMDR(0.0);
		requestData.setDebitForeignHostMDR(0.0);

		requestData.setCreditForeignMerchantMDR(0.0);
		requestData.setCreditForeignHostMDR(0.0);

		int response = syncEWallet(requestData, merchantRegistration.getOrder().getQuotation().getId(),
				merchantRegistration.getId());
		if (response == 2) {
			flag = false;
		}

		if (flag) {
			return new CommonResponseData("0000", "MDR Submit Successful", null);
		} else {
			return new CommonResponseData("0001", "Unable to submit MDR rate for E-Wallets", null);
		}
	}

	private int syncEWallet(WalletMDRSyncRequestData requestData, int quotationId, int merchantRegistrationId) {
		logger.info("syncEWallet Module Started");
		logger.info("syncEWallet WalletMDRSyncRequestData " + new Gson().toJson(requestData));
		HashMap<String, String> resdata = null;

		String merchRegUrl = env.getProperty("mdrSync.paymentServerUrl");

		try {

			URL url = new URL(merchRegUrl);
			logger.info("\nSending 'POST' request to URL : " + url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			ObjectMapper mapper = new ObjectMapper();
			OutputStream wr = new DataOutputStream(conn.getOutputStream());

			mapper.writeValue(wr, requestData);

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

			WalletMDRSyncResponseData walletMDRSyncResponseData = null;
			try {
				walletMDRSyncResponseData = new Gson().fromJson(response.toString(), WalletMDRSyncResponseData.class);
			} catch (Exception e) {
				logger.error("Unable to convert json to modal data", e);
				return 2;
			}

			if (walletMDRSyncResponseData != null
					&& walletMDRSyncResponseData.getResponseCode().toString().equals("0000")) {

				if (String.valueOf(walletMDRSyncResponseData.getResponseData().getCardBrand()).equals("BOOST")) {

					logger.error("Product Type  >>> BOOST-EZYWAY");

					regService.updateEwalletSyncCompleted(Integer.valueOf(merchantRegistrationId));

					if (walletMDRSyncResponseData.getResponseData().getPassword() != null
							&& walletMDRSyncResponseData.getResponseData().getUsername() != null
							&& walletMDRSyncResponseData.getResponseData().getActivationCode() != null
							&& walletMDRSyncResponseData.getResponseData().getMobiApiKey() != null) {

						regService.updateMerchantAppCredentials(
								walletMDRSyncResponseData.getResponseData().getPassword(),
								walletMDRSyncResponseData.getResponseData().getUsername(),
								walletMDRSyncResponseData.getResponseData().getActivationCode(),
								walletMDRSyncResponseData.getResponseData().getMobiApiKey(),
								Integer.valueOf(merchantRegistrationId));
					}
				}

				logger.info("End Of SendPaymentServer");
				return 1;
			} else {
				logger.info("End Of SendPaymentServer");
				return 2;
			}

		} catch (MalformedURLException e) {
			logger.error("Failed to Connect Payment Server : ", e);
			return 2;
		} catch (IOException e) {
			logger.error("Failed to Connect Payment Server : ", e);
			return 2;
		} catch (Exception e) {
			logger.error("Failed to Connect Payment Server : ", e);
			return 2;
		}

	}

	@Override
	public MerchantRegistration getMerchantRegistrationByRegistrationId(int id) {
		return regDAO.getMerchantRegistrationDetailsByRegistrationId(String.valueOf(id));
	}

	@Override
	public void updateMerchantAppCredentials(String password, String username, String activationCode, String mobiApiKey,
			int merchantRegistrationId) {
		regDAO.updateMerchantAppCredentials(merchantRegistrationId, username, password, activationCode, mobiApiKey);
	}

	@Override
	public Object getMerchantRegistrationByOrderId(String orderId) {
		return regDAO.getMerchantRegistrationByOrderId(orderId);
	}

	@Override
	public String UpdateOldMerchantData(OldMerchantDataUpdate requestData) {
		String merchRegUrl = env.getProperty("merchReg.paymentServerUrl");
		logger.info("Request Data >> " + new Gson().toJson(requestData));
		try {
			URL url = new URL(merchRegUrl);
			logger.info("\nSending 'POST' request to URL : " + url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			ObjectMapper mapper = new ObjectMapper();
			OutputStream wr = new DataOutputStream(conn.getOutputStream());

			mapper.writeValue(wr, requestData);

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

			OldMerchantResponseData responseData = new OldMerchantResponseData();
			responseData.setResponseDescription("Some thing went wrong , please try again");
			try {
				responseData = new Gson().fromJson(response.toString(), OldMerchantResponseData.class);

			} catch (Exception e) {
				logger.error(e);
			}

			return responseData.getResponseDescription();

		} catch (Exception e) {
			logger.error(e);
		}
		return "Some thing went wrong , please try again";
	}

	@Override
	public void updateEwalletSyncCompleted(Integer merchantRegID) {
		regDAO.updateEwalletSyncCompleted(merchantRegID);
	}

}
