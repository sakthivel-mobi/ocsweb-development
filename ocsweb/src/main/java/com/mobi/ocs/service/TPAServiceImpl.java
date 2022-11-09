package com.mobi.ocs.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import com.mobi.ocs.dao.QuotationDAO;
import com.mobi.ocs.dao.TPADao;
import com.mobi.ocs.entity.CompanyType;
import com.mobi.ocs.entity.Director;
import com.mobi.ocs.entity.Order;
import com.mobi.ocs.entity.OrderLines;
import com.mobi.ocs.entity.TPAFileToUmobile;
import com.mobi.ocs.modal.TPADocumentDownloadRequestData;
import com.mobi.ocs.utilities.Constants;

@Service
@Transactional
public class TPAServiceImpl implements TPAService {

	@Autowired
	private QuotationService quotationService;
	@Autowired
	private TPADao tpaDao;

	private static final Logger logger = Logger.getLogger(TPAServiceImpl.class);

	@Override
	public ArrayList<TPAFileToUmobile> InsertTPADataInDB(TPADocumentDownloadRequestData orderIDArray) {

		// Get the Order ID from the List Page

		logger.info("InsertTPADataInDB -> " + orderIDArray.getOrderIds().length);
		ArrayList<TPAFileToUmobile> tpaArrayList = new ArrayList<TPAFileToUmobile>();
		for (String orderId : orderIDArray.getOrderIds()) {

			logger.info("InsertTPADataInDB -> " + orderId.toString());

			// Delete the TPA rows in DB w.r to the Order ID we have got
			tpaDao.DeleteTPARowByID(orderId);

			// for loop to create tpa row for each orderlines

			int count = 0, index = 0;
			LocalDateTime now = LocalDateTime.now();
			Order order = quotationService.GetOrderByID(orderId);

			int quotatioId = order.getQuotation().getId();
			quotationService.GetOrderLineByQuotation(quotatioId);

			ArrayList<OrderLines> orderLines = (ArrayList<OrderLines>) quotationService
					.GetOrderLineByQuotation(quotatioId);
			logger.info("OrderLines Size -> " + orderLines.size());

			for (OrderLines lines : orderLines) {

				TPAFileToUmobile tpa = new TPAFileToUmobile();

				logger.info("OrderLines -> " + lines.getId());

				tpa.setPassCode("");
				if (count == 0) {
					tpa.setHQSUB("HQ");
				}
				tpa.setReferenceName(lines.getProduct().getProductName());
				tpa.setCreatedOn(now);
				tpa.setVerificationURL(Constants.umobileTPAVerificationURL + orderId);
				tpa.setValidTill(now.plusDays(7));

				if (now.getHour() > 14) {
					tpa.setRunDate(now.plusDays(1));
				} else {
					tpa.setRunDate(now);
				}

				tpa.setBatchNo(String.valueOf(1));
				tpa.setOrderId(orderId);
				tpa.setIsProcessed(0);
				tpa.setStatus("DRAFT");
				tpa.setResponseCode(0);
				tpa.setResponseDescription("");
				tpa.setOperationCode("A");

				// Vignesh Selvam
				// Changed Date Time Formatter to Simple Date Format
				// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
				// LocalDateTime date = LocalDateTime.parse(now.toString(), formatter);

				String simpleDateFormat = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime())
						.toString();

				tpa.setTpaReferenceNo(
						Constants.chainNo + simpleDateFormat + StringUtils.leftPad(String.valueOf(lines.getId()).trim(), 6, "0"));

				logger.info("ternary ->" + (order.getRiskApprovedDTL() == "" || order.getRiskApprovedDTL() == null ? "0"
						: order.getRiskApprovedDTL()));

				if (lines.getQuotationMDRRate() == null
						? lines.getQuotationEzysplitMDRRate().getProductName().contains("LINK")
						: lines.getQuotationMDRRate().getProductName().contains("LINK")) {
					tpa.setProductCode("MVTTPA2021");

				} 
				// rk changes for moto product
				
				else if (lines.getQuotationMDRRate() == null
						? lines.getQuotationEzysplitMDRRate().getProductName().contains("MOTO")
						: lines.getQuotationMDRRate().getProductName().contains("MOTO")) {
					logger.info("Inside Moto");				
					tpa.setProductCode("MOBIMOTO202105");

				} 
				
				//rk changes for moto product
				
				
				else if (Integer
						.parseInt((order.getRiskApprovedDTL() == null || order.getRiskApprovedDTL().equals("")) ? "0"
								: order.getRiskApprovedDTL()) < 60000) {
					tpa.setProductCode("202101TPASMALLSME");
				} else if (Integer.parseInt(order.getRiskApprovedDTL()) >= 60000) {
					tpa.setProductCode("202101TPALARGEACCOUNT");
				}

				tpa.setMid("");
				tpa.setChainNo(Constants.chainNo);
				tpa.setRootMerchantNo(Constants.chainNo);
				tpa.setMerchantIdType(order.getMerchantIdType());
				tpa.setBusinessRegistrationNo(order.getRegistrationId());
				tpa.setBusinessName(order.getBusinessName());
				tpa.setMerchantTradingName(order.getMerchantTradingName());
				tpa.setBusinessStartTime(order.getBusinessStartTime());
				tpa.setBusinessEndTime(order.getBusinessEndTime());
				tpa.setNatureOfBusiness(StringUtils.leftPad(String.valueOf(order.getNatureOfBusiness()), 4, "0"));

				String[] addresses = order.getAddressLine().split(",");
				String city = "";

				if (addresses.length > 0 && addresses[0] != null) {
					tpa.setBusinessAddressLine1(addresses[0]);

				}
				if (addresses.length > 1 && addresses[1] != null) {
					tpa.setBusinessAddressLine2(addresses[1]);

				}

				if (addresses.length > 2 && addresses[2] != null) {
					tpa.setBusinessAddressLine3(addresses[2]);

				}

				if (addresses.length > 3 && addresses[3] != null) {
					tpa.setBusinessAddressLine4(addresses[3]);

				}

				if (order.getState() != 0) {
					String state = quotationService.GetUmobileStateByID(order.getState());
				}

				if (order.getCity() != 0) {
					city = quotationService.GetUmobileCityByID(order.getCity());
				}

				tpa.setCountry(order.getCountry());
				tpa.setState(order.getState());

				logger.info("city >> " + city);
				tpa.setTown(city);
				tpa.setPostCode(order.getPostCode());

				int directorCount = 0;
				for (Director director : order.getDirectors()) {

					directorCount++;
					switch (directorCount) {
					case 1:

						tpa.setDirector1Name(director.getName() == null ? "" : director.getName());
						tpa.setDirector1MobileNo(director.getContactNo() == null ? "" : director.getContactNo());
						tpa.setDirector1IdType(director.getIdType() == null ? "" : director.getIdType());
						tpa.setDirector1IdNumber(director.getIdNo() == null ? "" : director.getIdNo());

						if (director.getNationality() == null || director.getNationality() == "") {
							tpa.setDirector1Country("");
						} else {
							String countryID = quotationService.GetCountryIDByName(director.getNationality());
							tpa.setDirector1Country(countryID);
						}

						break;

					case 2:

						tpa.setDirector2Name(director.getName() == null ? "" : director.getName());
						tpa.setDirector2MobileNo(director.getContactNo() == null ? "" : director.getContactNo());
						tpa.setDirector2IdType(director.getIdType() == null ? "" : director.getIdType());
						tpa.setDirector2IdNumber(director.getIdNo() == null ? "" : director.getIdNo());

						if (director.getNationality() == null || director.getNationality() == "") {
							tpa.setDirector2Country("");
						} else {
							String countryID = quotationService.GetCountryIDByName(director.getNationality());
							tpa.setDirector2Country(countryID);
						}

						break;

					case 3:

						tpa.setDirector3Name(director.getName() == null ? "" : director.getName());
						tpa.setDirector3MobileNo(director.getContactNo() == null ? "" : director.getContactNo());
						tpa.setDirector3IdType(director.getIdType() == null ? "" : director.getIdType());
						tpa.setDirector3IdNumber(director.getIdNo() == null ? "" : director.getIdNo());

						if (director.getNationality() == null || director.getNationality() == "") {
							tpa.setDirector3Country("");
						} else {
							String countryID = quotationService.GetCountryIDByName(director.getNationality());
							tpa.setDirector3Country(countryID);
						}
						break;

					case 4:

						tpa.setDirector4Name(director.getName() == null ? "" : director.getName());
						tpa.setDirector4MobileNo(director.getContactNo() == null ? "" : director.getContactNo());
						tpa.setDirector4IdType(director.getIdType() == null ? "" : director.getIdType());
						tpa.setDirector4IdNumber(director.getIdNo() == null ? "" : director.getIdNo());

						if (director.getNationality() == null || director.getNationality() == "") {
							tpa.setDirector4Country("");
						} else {
							String countryID = quotationService.GetCountryIDByName(director.getNationality());
							tpa.setDirector4Country(countryID);
						}
						break;

					default:
						break;
					}

				}

				if (lines.getQuotationMDRRate() != null) {
					if (!lines.getQuotationMDRRate().getProductType().contains("WIRE")) {

						// Set Ecomm Details
						tpa.seteCommerceIndicator("2");
						tpa.seteCommerceTerminalFlag("Y");
						tpa.seteCommerceIndustry(String.valueOf(order.geteCommIndustry()));

						if (lines.getQuotationMDRRate().getProductType().contains("LINK")
								|| lines.getQuotationMDRRate().getProductType().contains("MOTO")) {

							tpa.setWebsiteUrl("www.gomobi.io");
						} else {
							tpa.setWebsiteUrl(order.getWebsiteUrl());
						}

						// set MPOS detail to null for ECOMM
						tpa.setNumberOfMpos("0");
						tpa.setMposModel("");

					} else {
						// set Ecomm details to null
						tpa.seteCommerceIndicator("0");
						tpa.seteCommerceTerminalFlag("");
						tpa.seteCommerceIndustry("");
						tpa.setWebsiteUrl("");

						// set MPOS details
						tpa.setNumberOfMpos(String.valueOf(lines.getQuantity()));
						tpa.setMposModel("EZYWIRE");
					}
				} else {

					// non EZY Wire

					// Set Ecomm Details
					tpa.seteCommerceIndicator("2");
					tpa.seteCommerceTerminalFlag("Y");
					tpa.seteCommerceIndustry(String.valueOf(order.geteCommIndustry()));
					tpa.setWebsiteUrl(order.getWebsiteUrl() == null ? "" : order.getWebsiteUrl());

					// set MPOS detail to null for ECOMM
					tpa.setNumberOfMpos("0");
					tpa.setMposModel("");

				}

				tpa.setNumberOfEdc("0");
				tpa.setEdcModel("");
				tpa.setMerchantSize("S");
				tpa.setMasterCardMCC(String.valueOf(order.getMasterCardMCC()));
				tpa.setVisaMCC(String.valueOf(order.getVisaMCC()));
				tpa.setUnionPayMCC(String.valueOf(order.getUnionPayMCC()));

				logger.error("order.getPreviousAcquirer() >> "
						+ (order.getPreviousAcquirer() != null ? order.getPreviousAcquirer().trim().isEmpty() : ""));

				if (order.getPreviousAcquirer() != null && !order.getPreviousAcquirer().equals("")) {
					logger.info(order.getPreviousAcquirer());
					tpa.setPreviousAcquirerIndicator("Y");
					tpa.setPreviousAcquirer(order.getPreviousAcquirer());
					tpa.setCeasedDateWithPreviousAcquirer(order.getPreviousAcquirerCeasedDate());
				} else {
					tpa.setPreviousAcquirerIndicator("N");
					tpa.setPreviousAcquirer("");
					tpa.setCeasedDateWithPreviousAcquirer("");
				}
				tpa.setEmail(order.getQuotation().getContact().getEmail());
				CompanyType companyType = quotationService.GetCompanyTypeByID(order.getCompanyType());
				if (companyType != null)
					tpa.setCompanyType(companyType.getUmCompanyType());
				// tpa.setCompanyType(order.getCompanyType());

				boolean flag = false;

				for (TPAFileToUmobile tpaFileToUmobile : tpaArrayList) {
					if (tpaFileToUmobile.getReferenceName().trim() == tpa.getReferenceName().trim()) {
						flag = true;
					}
				}

				if (!flag) {
					tpaArrayList.add(tpa);
				}

				index++;
			}

			tpaDao.SaveTPA(tpaArrayList, order.getId());
			logger.error("Save TPA  >>  ");

		}

		// Vignesh Selvam
		// get data from tpafiletoumobile by orderID

		ArrayList<TPAFileToUmobile> tpaFileToUmobiles = new ArrayList<TPAFileToUmobile>();

		logger.info("tpaFileToUmobiles >> " + orderIDArray.getOrderIds().toString());

		for (String orderId : orderIDArray.getOrderIds()) {
			if (getTPAFileToUmobileList(orderId) != null) {
				tpaFileToUmobiles.addAll(getTPAFileToUmobileList(orderId));
			}
		}

		logger.info("tpaFileToUmobiles >> " + tpaFileToUmobiles.size());
		logger.info("tpaFileToUmobiles >> " + tpaFileToUmobiles.toString());

		return tpaFileToUmobiles;
	}

	private ArrayList<TPAFileToUmobile> getTPAFileToUmobileList(String orderId) {
		try {

			return (ArrayList<TPAFileToUmobile>) tpaDao.getItemByOrderId(orderId);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("getTPAFileToUmobileList exception - > " + e);
			return null;
		}

	}
}
