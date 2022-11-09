package com.mobi.ocs.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.mobi.ocs.dto.CommonResponseData;
import com.mobi.ocs.dto.MDRData;
import com.mobi.ocs.entity.HostRate;
import com.mobi.ocs.entity.HostType;
import com.mobi.ocs.entity.OrderLines;
import com.mobi.ocs.entity.OrderType;
import com.mobi.ocs.entity.Payment;
import com.mobi.ocs.entity.PaymentType;
import com.mobi.ocs.entity.PendingQuotationMDRRate;
import com.mobi.ocs.entity.Product;
import com.mobi.ocs.entity.ProductType;
import com.mobi.ocs.entity.Quotation;
import com.mobi.ocs.entity.QuotationMDRRate;
import com.mobi.ocs.entity.QuotationPDF;
import com.mobi.ocs.entity.SalesPerson;
import com.mobi.ocs.entity.UmobileCountry;
import com.mobi.ocs.modal.DiscountPriceRequestData;
import com.mobi.ocs.modal.PaymentCollectRequestData;
import com.mobi.ocs.modal.QuotationBasicInfoResponseData;
import com.mobi.ocs.modal.QuotationRequestData;
import com.mobi.ocs.modal.RollbackRequestData;
import com.mobi.ocs.service.GeneratePDFService;
import com.mobi.ocs.service.QuotationService;

@RestController()
@RequestMapping(path = "quotation", method = RequestMethod.POST)
public class QuotationRestController {

	@Autowired
	private GeneratePDFService generatePDFService;

	@Autowired
	private QuotationService quotationService;

	protected static Logger logger = Logger.getLogger(QuotationController.class);

	@RequestMapping(path = "/{quotationId}/mdr/pending", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getPendingQuotationMDRRates(@PathVariable String quotationId) {
		Object response = quotationService.getPendingQuotationMDRRates(quotationId);
		return response;
	}

	@RequestMapping(path = "/{quotationId}/mdr/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object updatePendingQuotationMDRRates(@PathVariable String quotationId, @RequestBody MDRData mdrData) {
		QuotationMDRRate quoteRate = new QuotationMDRRate();
		LocalDateTime now = LocalDateTime.now();

		HostRate hostRate = quotationService.GetHostRate(Integer.parseInt(mdrData.getHostRateId()));

		quoteRate.setUnitPrice(mdrData.getAmount());

		// Merchant MDR

		quoteRate.setFor_Credit_Merch_Master(mdrData.getFor_Credit_Merch_Master());
		quoteRate.setFor_Credit_Merch_Union(mdrData.getFor_Credit_Merch_Union());
		quoteRate.setFor_Credit_Merch_Visa(mdrData.getFor_Credit_Merch_Visa());
		quoteRate.setFor_Debit_Merch_Master(mdrData.getFor_Debit_Merch_Master());
		quoteRate.setFor_Debit_Merch_Union(mdrData.getFor_Debit_Merch_Union());
		quoteRate.setFor_Debit_Merch_Visa(mdrData.getFor_Debit_Merch_Visa());

		quoteRate.setLoc_Credit_Merch_Master(mdrData.getLoc_Credit_Merch_Master());
		quoteRate.setLoc_Credit_Merch_Union(mdrData.getLoc_Credit_Merch_Union());
		quoteRate.setLoc_Credit_Merch_Visa(mdrData.getLoc_Credit_Merch_Visa());
		quoteRate.setLoc_Debit_Merch_Master(mdrData.getLoc_Debit_Merch_Master());
		quoteRate.setLoc_Debit_Merch_Union(mdrData.getLoc_Debit_Merch_Union());
		quoteRate.setLoc_Debit_Merch_Visa(mdrData.getLoc_Debit_Merch_Visa());

		// Assign Host MDR

		quoteRate.setLoc_Credit_Host_Master(hostRate.getLoc_Credit_Host_Master());
		quoteRate.setLoc_Credit_Host_Union(hostRate.getLoc_Credit_Host_Union());
		quoteRate.setLoc_Credit_Host_Visa(hostRate.getLoc_Credit_Host_Visa());
		quoteRate.setLoc_Debit_Host_Master(hostRate.getLoc_Debit_Host_Master());
		quoteRate.setLoc_Debit_Host_Union(hostRate.getLoc_Debit_Host_Union());
		quoteRate.setLoc_Debit_Host_Visa(hostRate.getLoc_Debit_Host_Visa());

		quoteRate.setFor_Credit_Host_Master(hostRate.getFor_Credit_Host_Master());
		quoteRate.setFor_Credit_Host_Union(hostRate.getFor_Credit_Host_Union());
		quoteRate.setFor_Credit_Host_Visa(hostRate.getFor_Credit_Host_Visa());
		quoteRate.setFor_Debit_Host_Master(hostRate.getFor_Debit_Host_Master());
		quoteRate.setFor_Debit_Host_Union(hostRate.getFor_Debit_Host_Union());
		quoteRate.setFor_Debit_Host_Visa(hostRate.getFor_Debit_Host_Visa());

		// Calculate and assign Mobi MDR

		quoteRate
				.setLoc_Credit_Mobi_Master(mdrData.getFor_Credit_Merch_Master() - hostRate.getLoc_Credit_Host_Master());
		quoteRate.setLoc_Credit_Mobi_Union(mdrData.getFor_Credit_Merch_Union() - hostRate.getLoc_Credit_Host_Union());
		quoteRate.setLoc_Credit_Mobi_Visa(mdrData.getFor_Credit_Merch_Visa() - hostRate.getLoc_Credit_Host_Visa());
		quoteRate.setLoc_Debit_Host_Master(mdrData.getFor_Debit_Merch_Master() - hostRate.getLoc_Debit_Host_Master());
		quoteRate.setLoc_Debit_Mobi_Union(mdrData.getFor_Debit_Merch_Union() - hostRate.getLoc_Debit_Host_Union());
		quoteRate.setLoc_Debit_Mobi_Visa(mdrData.getFor_Debit_Merch_Visa() - hostRate.getLoc_Debit_Host_Visa());

		quoteRate
				.setFor_Credit_Mobi_Master(mdrData.getLoc_Credit_Merch_Master() - hostRate.getFor_Credit_Host_Master());
		quoteRate.setFor_Credit_Mobi_Union(mdrData.getLoc_Credit_Merch_Union() - hostRate.getFor_Credit_Host_Union());
		quoteRate.setFor_Credit_Mobi_Visa(mdrData.getLoc_Credit_Merch_Visa() - hostRate.getFor_Credit_Host_Visa());
		quoteRate.setFor_Debit_Mobi_Master(mdrData.getLoc_Debit_Merch_Master() - hostRate.getFor_Debit_Host_Master());
		quoteRate.setFor_Debit_Mobi_Union(mdrData.getLoc_Debit_Merch_Union() - hostRate.getFor_Debit_Host_Union());
		quoteRate.setFor_Debit_Mobi_Visa(mdrData.getLoc_Debit_Merch_Visa() - hostRate.getFor_Debit_Host_Visa());

		// Customer MDR - Not yet implemented
		quoteRate.setLoc_Credit_Cus_Master(mdrData.getLoc_Credit_Cus_Master());
		quoteRate.setLoc_Credit_Cus_Union(mdrData.getLoc_Credit_Cus_Union());
		quoteRate.setLoc_Credit_Cus_Visa(mdrData.getLoc_Credit_Cus_Visa());

		quoteRate.setProductSettlement(mdrData.getProductSettlement());
		quoteRate.setBoostSettlement(mdrData.getBoostSettlement());
		quoteRate.setGrabSettlement(mdrData.getGrabSettlement());
		quoteRate.setFpxSettlement(mdrData.getFpxSettlement());

		quoteRate.setBoostMDREcomm(mdrData.getBoostMDREcomm());
		quoteRate.setBoostMDRQR(mdrData.getBoostMDRQR());
		quoteRate.setGrabMDREcomm(mdrData.getGrabMDREcomm());
		quoteRate.setGrabMDRQR(mdrData.getGrabMDRQR());
		quoteRate.setfPXMDR_Percent(mdrData.getfPXMDR_Percent());
		quoteRate.setfPXMDR_RM(mdrData.getfPXMDR_RM());
		quoteRate.setHostRateIdRef(mdrData.getHostRateId());

		quoteRate.setProductName(mdrData.getProductName());
		quoteRate.setSubscription(mdrData.getSubscription());
		quoteRate.setShowInQuote(mdrData.getShowInQuote());
		quoteRate.setProductType(mdrData.getProductType());
		quoteRate.setIncludeWallet(mdrData.getIncludeWallet());
		quoteRate.setPayLater(mdrData.getPayLater());
		quoteRate.setHostType(mdrData.getHostType());
		quoteRate.setCreatedOn(mdrData.getCreatedOn());

		int productId = mdrData.getProductId();
		int quotationid = mdrData.getQuotationId();
		// int orderLineId = mdrData.getOrderLineId();

		quoteRate.setId(mdrData.getQuotationMdrRateId());
		// Object response =
		// quotationService.updatePendingQuotationMDRRates(quotationId, requestData);
		return "";
	}

	@RequestMapping(path = "/{quotationId}/mdr/ezysplit/pending", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getPendingQuotationEzySplitMDRRates(@PathVariable String quotationId) {
		Object response = quotationService.getPendingQuotationEzySplitMDRRates(quotationId);
		return response;
	}

	@RequestMapping(path = "/issue", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object issueQuotation(@RequestBody QuotationRequestData quotationRequestData, HttpServletRequest req)
			throws NumberFormatException, FileNotFoundException, MalformedURLException, IOException, DocumentException {
		Quotation quotation = quotationService
				.getQuotationByID(Integer.parseInt(quotationRequestData.getQuotationId()));
		generatePDFService.GenerateQuotationMobi(quotation, req);
		Object response = generatePDFService.IssueQuotationByQuotationId(quotationRequestData, quotation, req);
		quotationService.SendQuotationIssueEmail(Integer.parseInt(quotationRequestData.getQuotationId()), response,
				req);
		return response;
	}

	// rk added
	// this one is used to send the mail
	@RequestMapping(path = "/profoma/issue", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object issueprofoma(@RequestBody QuotationRequestData quotationRequestData, HttpServletRequest req)
			throws NumberFormatException, FileNotFoundException, MalformedURLException, IOException, DocumentException {
		Quotation quotation = quotationService
				.getQuotationByID(Integer.parseInt(quotationRequestData.getQuotationId()));
		generatePDFService.GenerateProfoma(quotation, req);
		Object response = generatePDFService.IssueProfomaByQuotationId(quotationRequestData, quotation, req);
		quotationService.SendProfomaEmail(Integer.parseInt(quotationRequestData.getQuotationId()), response, req);
		return response;
	}

	// rk added
	// this one is used to generate the pdf and store
	@RequestMapping(value = "/profoma/view", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object SendProfoma(@RequestParam("quotationId") String quotationID, HttpServletRequest request,
			HttpServletResponse response) throws DocumentException, MalformedURLException, IOException {

		logger.info("Generating PDF for QuotationID : " + quotationID);

		Quotation quotation = quotationService.getQuotationByID(Integer.parseInt(quotationID));
		
		Object responseData = generatePDFService.GenerateProfoma(quotation, request);
		return responseData;

	}

	@RequestMapping(path = "/history", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object historyQuotation(@RequestBody QuotationRequestData quotationRequestData) {
		Object response = quotationService.getQuotationHistory(quotationRequestData.getQuotationId());
		return response;
	}

	@RequestMapping(path = "/download/{quotationId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getQuotatioinById(@PathVariable(name = "quotationId") String quotationId) {
		Object response = generatePDFService.getAvailableQuotationById(quotationId);
		return response;
	}

	@RequestMapping(path = "/accept/{quotationId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object acceptQuotation(@PathVariable(name = "quotationId") String quotationPath) {
		Object response = generatePDFService.acceptQuotation(quotationPath);
		return "";
	}

	@RequestMapping(path = "/payment/collect", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object paymentCollect(@RequestBody PaymentCollectRequestData paymentCollectRequestData) {

		Quotation quotation = quotationService
				.getQuotationByID(Integer.parseInt(paymentCollectRequestData.getQuotationId()));
		String receiptFilePath = quotationService.GeneratePaymentRecept(paymentCollectRequestData);

		logger.error(paymentCollectRequestData);

		if (paymentCollectRequestData.getPaymentType() == 2) {
			try {
				Payment payment = new Payment();
				payment.setCollectedOn(paymentCollectRequestData.getCollectedOn());
				payment.setReceipt(receiptFilePath);
				payment.setQuotation(quotation);
				payment.setQuotationId(paymentCollectRequestData.getQuotationId());

				logger.error("paymentCollect >> " + payment.toString());

				int paymentId = quotationService.SavePayment(payment);

				if (paymentId != -1) {
					quotationService.UpdatePaymentIdInQuotation(paymentId, paymentCollectRequestData.getQuotationId());
					// String currentStage = "sales";
					// quotationService.MoveToNextStage(currentStage, quotation.getAcquirer(),
					// Integer.parseInt(paymentCollectRequestData.getQuotationId()));
					return new CommonResponseData("0000", "Receipt Uploaded", null);
				} else {
					// exception case sql qury error
					return new CommonResponseData("0001", "Unable to upload receipt!", null);
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return new CommonResponseData("0001", "Unable to upload receipt", null);
			}
		} else if (paymentCollectRequestData.getPaymentType() == 1) {
			if (receiptFilePath != null) {
				try {
					Payment payment = new Payment();
					payment.setCollectedOn(paymentCollectRequestData.getCollectedOn());
					payment.setReceipt(receiptFilePath);
					payment.setQuotation(quotation);
					payment.setQuotationId(paymentCollectRequestData.getQuotationId());

					logger.error("paymentCollect >> " + payment.toString());

					int paymentId = quotationService.SavePayment(payment);

					if (paymentId != -1) {
						quotationService.UpdatePaymentIdInQuotation(paymentId,
								paymentCollectRequestData.getQuotationId());
						// String currentStage = "sales";
						// quotationService.MoveToNextStage(currentStage, quotation.getAcquirer(),
						// Integer.parseInt(paymentCollectRequestData.getQuotationId()));
						return new CommonResponseData("0000", "Receipt Uploaded", null);
					} else {
						// exception case sql qury error
						return new CommonResponseData("0001", "Unable to upload receipt!", null);
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					return new CommonResponseData("0001", "Unable to upload receipt", null);
				}
			} else {
				return new CommonResponseData("0001", "Unable to upload receipt", null);
			}
		} else {
			return new CommonResponseData("0001", "Unable to upload receipt", null);
		}

	}

	@RequestMapping(path = "/payment/{quotationId}/receipt", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object viewReceipt(@PathVariable(name = "quotationId") String quotationId) {
		Object response = quotationService.getReceiptByQuotationId(quotationId);
		return response;
	}

	@RequestMapping(value = "/stage/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String paymentCollected(@RequestParam("quotationId") String quotationID,
			@RequestParam("acquirer") String acquirer, @ModelAttribute Payment payment,
			@RequestParam MultipartFile file, BindingResult bindingResult) {

		for (FieldError fieldError : bindingResult.getFieldErrors())
			logger.info(fieldError.getField() + " : " + fieldError.getDefaultMessage());

		try {

			logger.info("Inside PaymentCollected Controller");
			logger.info(payment.toString());
			logger.info(quotationID);

			int paymentId = quotationService.SavePayment(payment);
			quotationService.UpdatePaymentIdInQuotation(paymentId, quotationID);

			String currentStage = "sales";

			quotationService.MoveToNextStage(currentStage, acquirer, Integer.parseInt(quotationID));

		} catch (Exception e) {
			logger.error("Failed to Collect Payment", e);
		}

		return "redirect:/Order/list";
	}

	@RequestMapping(value = "/stage/update/finance", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object MoveToFinance(@RequestParam("quotationId") String quotationID,
			@RequestParam("acquirer") String acquirer) {

		// try {
		logger.info("Inside MoveToFinance Controller");
		logger.info(quotationID);
		// String nextStage = quotationService.MoveToNextStage(currentStage, acquirer,
		// Integer.parseInt(quotationID));
		// System.out.println("nextStage >> "+ nextStage);

		String currentStage = "sales";

		int status = quotationService.UpdateStages(currentStage, acquirer, Integer.parseInt(quotationID));
		
		Quotation quotation = quotationService.getQuotationByID(Integer.valueOf(quotationID));
		
		quotationService.sendNotificationToFinance(quotation);

		if (status == 1) {
			return new CommonResponseData("0000", "Success", null);
		} else {
			return new CommonResponseData("0001", "Unable to move stage, please check the details!", null);
		}
	}

	@RequestMapping(value = "/modifyOCSUserId", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object ModifyOCSUserID(@RequestParam("newUserId") String newUserId,
			@RequestParam("quotationId") String quotationId, HttpServletRequest request, HttpServletResponse response)
			throws DocumentException, MalformedURLException, IOException {

		logger.info("New User ID for the Quotation : " + quotationId + " - " + newUserId);

		Object responseData = quotationService.ModifyOCSUserID(Integer.parseInt(quotationId), newUserId);
		return responseData;
	}

	@RequestMapping(path = "/{quotationId}/rollback", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object rollbackQuotation(@PathVariable(name = "quotationId") String quotationId,
			@RequestBody RollbackRequestData rollbackRequestData) {

		Object response = quotationService.rollbackQuotation(quotationId, rollbackRequestData);

		return response;
	}

	@RequestMapping(path = "/{quotationId}/discount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object applyDiscount(@PathVariable(name = "quotationId") String quotationId,
			@RequestBody DiscountPriceRequestData discountPriceRequestData) {

		Object response = quotationService.applyDiscount(quotationId, discountPriceRequestData);

		return response;
	}

	@RequestMapping(value = "/stage/update/processing", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object MoveToProcessing(@RequestParam("quotationId") String quotationID,
			@RequestParam("acquirer") String acquirer) {

		// try {
		logger.info("Inside MoveToFinance Controller");
		logger.info(quotationID);
		// String nextStage = quotationService.MoveToNextStage(currentStage, acquirer,
		// Integer.parseInt(quotationID));
		// System.out.println("nextStage >> "+ nextStage);

		String currentStage = "finance";

		int status = quotationService.UpdateStages(currentStage, acquirer, Integer.parseInt(quotationID));

		if (status == 1) {
			return new CommonResponseData("0000", "Success", null);
		} else {
			return new CommonResponseData("0001", "Unable to move stage, please check the details!", null);
		}
	}

	@RequestMapping(path = "/{quotationId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getQuotationByQuotationid(@PathVariable(name = "quotationId") String quotationID) {
		try {
			logger.info("getQuotationByyQuotationid >> " + quotationID);
			Quotation quotation = quotationService.getQuotationByID(Integer.parseInt(quotationID));

			double subTotals = 0.00;

			for (OrderLines orderLines : quotation.getOrderLines()) {
				subTotals += orderLines.getQuantity() * orderLines.getProduct().getUnitPrice();
			}

			QuotationBasicInfoResponseData data = new QuotationBasicInfoResponseData();
			data.setDiscountPrice(String.valueOf(quotation.getDiscountPrice()));
			data.setDiscountReason(quotation.getDiscountReason());
			data.setSubTotalPrice(String.valueOf(subTotals));
			data.setQuotationID(quotationID);

			return new CommonResponseData("0000", "Success", data);
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonResponseData("0001", "Unable to get quotation", null);
		}
	}

	@RequestMapping(path = "/salesPerson/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getSalespersonList() {
		try {

			List<SalesPerson> data = quotationService.GetSalesPersons();

			return new CommonResponseData("0000", "Success", data);
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonResponseData("0001", "Unable to get quotation", null);
		}
	}

	@RequestMapping(path = "/salesPerson/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object updateQuoatationSalesPersonId(@RequestParam("salesPersonId") String salesPersonId,
			@RequestParam("quotationId") String quotationId) {
		try {

			Object response = quotationService.updateQuoatationSalesPersonId(salesPersonId, quotationId);

			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonResponseData("0001", "Unable to get quotation", null);
		}
	}

	@RequestMapping(path = "/{quotationId}/stage/change", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object updateQuotationStag(@PathVariable("quotationId") String quotationId,
			@RequestParam("stage") String stage) {
		try {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonResponseData("0001", "Unable to get quotation", null);
		}
	}
}
