package com.mobi.ocs.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.SaveContextOnUpdateOrErrorResponseWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.mchange.util.impl.QuotesAndSpacesTokenizer;
import com.mchange.v2.cfg.PropertiesConfigSource.Parse;
import com.mobi.ocs.dao.QuotationDAO;
import com.mobi.ocs.dto.HubSpotInfo;
import com.mobi.ocs.dto.IDResponse;
import com.mobi.ocs.dto.MDRData;
import com.mobi.ocs.dto.OrderLineIds;
import com.mobi.ocs.dto.Property;
import com.mobi.ocs.dto.ValueString;
import com.mobi.ocs.dto.kanban.KanbanCard;
import com.mobi.ocs.dto.kanban.KanbanCustomFields;
import com.mobi.ocs.dto.kanban.KanbanInfo;
import com.mobi.ocs.entity.Acquirer;
import com.mobi.ocs.entity.Contact;
import com.mobi.ocs.entity.HostRate;
import com.mobi.ocs.entity.HostType;
import com.mobi.ocs.entity.Order;
import com.mobi.ocs.entity.OrderLines;
import com.mobi.ocs.entity.OrderType;
import com.mobi.ocs.entity.Payment;
import com.mobi.ocs.entity.PaymentType;
import com.mobi.ocs.entity.Product;
import com.mobi.ocs.entity.ProductType;
import com.mobi.ocs.entity.Quotation;
import com.mobi.ocs.entity.QuotationAcceptance;
import com.mobi.ocs.entity.QuotationEzySplitMDRRate;
import com.mobi.ocs.entity.QuotationMDRRate;
import com.mobi.ocs.entity.SalesPerson;
import com.mobi.ocs.entity.UmobileCountry;
import com.mobi.ocs.service.AssignService;
import com.mobi.ocs.service.GeneratePDFService;
import com.mobi.ocs.service.QuotationService;

import kotlin.contracts.ReturnsNotNull;

@Controller
@RequestMapping("/Quotation")
public class QuotationController {

	@Autowired
	private QuotationService quotationService;
	@Autowired
	private GeneratePDFService generatePDFService;
	@Autowired
	private AssignService assignService;

	protected static Logger logger = Logger.getLogger(QuotationController.class);

	@RequestMapping("/list")
	public String QuotationList(Model model) {

		try {

			logger.info("Inside Quotation List Controller");
			List<Quotation> quotations = new ArrayList<Quotation>();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			String sUserName = auth.getName().toString();
			logger.info("UserName Logged in - 	" + sUserName);

			// if (auth.getAuthorities().stream().anyMatch(a ->
			// a.getAuthority().equals("ROLE_SALES"))) {
			// quotations = quotationService.GetQuotationsByStage("sales");
			// }
			//
			// else if (auth.getAuthorities().stream().anyMatch(a ->
			// a.getAuthority().equals("ROLE_FINANCE"))) {
			// quotations = quotationService.GetQuotationsByStage("finance");
			// } else {
			//
			// quotations = quotationService.getQuotations();
			// }

			quotations = quotationService.getQuotations();
			model.addAttribute("quotations", quotations);

		} catch (Exception e) {
			logger.error("Failed to Get Quotation List - ", e);
		}

		return "QuotationList";
	}

	@RequestMapping("/addQuotation")
	public @ResponseBody String QuotationAdd(Model model, @RequestParam String dealId) throws IOException {

		try {
			logger.info("Inside AddQuotation Controller");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Boolean isDealExist = quotationService.IsDealExist(dealId.trim());
			if (isDealExist) {
				return "Deal ID Already Imported!";
				// quotationService.ShowWarning("Deal ID Already Imported!", "Alert");

			} else {

				int ContactAssociateID = 3;
				int CompanyAssociateID = 5;
				String contactID = quotationService.GetContactORCompanyFromHubSpot(dealId, ContactAssociateID);
				String companyID = quotationService.GetContactORCompanyFromHubSpot(dealId, CompanyAssociateID);

				logger.info("Contact ID : " + contactID);
				logger.info("Company ID : " + companyID);

				if (contactID.equals("") || companyID.equals("")) {
					return "Contact or Company Detail Not available for the Deal.";
				} else {

					Quotation quotation = new Quotation();
					Contact contact = new Contact();
					String contactNoString = "";

					// Get SalesPerson INFO from Deal
					HubSpotInfo dealInfo = quotationService.GetDealInfoFromHubspot(dealId);
					Property dealProp = dealInfo.getProperties();

					String dealOwnerID = (dealProp.getHubspot_owner_id() == null
							|| dealProp.getHubspot_owner_id().getValue() == "") ? ""
									: dealProp.getHubspot_owner_id().getValue();
					logger.info("Hubspot Owner ID for this deal : " + dealOwnerID);
					SalesPerson dealOwner = quotationService.GetSalesPersonByHubspotID(dealOwnerID);
					if (dealOwner != null) {

						if (!companyID.equals("")) {
							HubSpotInfo companyInfo = quotationService.GetCompanyInfoFromHubSpot(companyID);
							Property companyProp = companyInfo.getProperties();

							quotation = assignService.AssignCompanyInfoToQuotation(companyInfo, companyProp);

							contactNoString = companyProp.getPhone() == null ? "" : companyProp.getPhone().getValue();
							contactNoString = contactNoString.replace(" ", "").replace("+6", "").replace("-", "")
									.replace("(", "").replace(")", "");

							logger.info("Saving UserID - " + contactNoString);

							if (!contactID.equals("")) {
								HubSpotInfo contactInfo = quotationService.GetContactInfoFromHubSpot(contactID);
								Property contactProp = contactInfo.getProperties();
								contact = assignService.AssignContact(contactInfo, contactProp);

								if (contactNoString != "") {
									contact.setPhoneNumber(contactNoString);
								}
								quotationService.saveContact(contact);
							}
							quotation.setDealID(dealId);
							quotation.setContact(contact);
							quotation.setStage("sales");
							quotation.setOrderType("New");
							quotation.setUserId(contactNoString);
							// quotation.setSalesPerson(dealOwner);

							quotationService.saveQuotation(quotation, dealOwner.getId());
							// } else {
							// quotationService.ShowWarning("Phone Number Not Available for the Deal ID
							// imported.", "Alert");
							// }
						} else {
							return "Company Details Not Available for the Deal ID imported.";
							// quotationService.ShowWarning("Company Details Not Available for the Deal ID
							// imported.", "Alert");
						}
						// return "redirect:/Quotation/list";
					} else {
						return "Salesperson Detail Not available for the Deal ID trying to Import.";
					}

				}
			}
//			return "redirect:/Quotation/list";
			return "Deal Imported Successfully!";

		} catch (Exception e) {
			logger.error("Failed to Add Quotation - ", e);
			return e.getMessage();
		}

	}

	@RequestMapping("/addQuotationFromKanban")
	public @ResponseBody String quotationAddFromKanban(Model model, @RequestParam String cardId,
			@RequestParam String boardId) throws IOException {

		try {
			logger.info(String.format("Add Quotation From Kanban - CardID : %s , BoardID : %s .", cardId, boardId));
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Boolean isCardExist = quotationService.IsCardIDExistForTheBoard(cardId.trim(), boardId.trim());
			if (isCardExist) {
				return String.format("Card ID %s in the Board %s already Imported!", cardId, boardId);

			} else {

				Quotation quotation = new Quotation();
				Contact contact = new Contact();
				String contactNoString = "";

				KanbanInfo kanbanInfo = quotationService.GetCardDetailsFromKanban(cardId, boardId);
				logger.info(kanbanInfo.toString());

				for (KanbanCard card : kanbanInfo.getCards()) {

					String ownerEmail = card.getCardItem().getOwner() == null ? "" : card.getCardItem().getOwner();

					if (ownerEmail != "") {
						SalesPerson salesPerson = quotationService.GetSalesPersonByEmail(ownerEmail);
						if (salesPerson != null) {
							List<KanbanCustomFields> customFields = card.getCardItem().getCustomFields();

							for (KanbanCustomFields customField : customFields) {

								switch (customField.getLabel()) {
								case "Merchant Name":
									quotation.setCompanyName(customField.getValue());
									break;
								case "PIC Name":
									contact.setFirstName(customField.getValue());
									break;
								case "Mobile No":
									contactNoString = customField.getValue().replace(" ", "").replace("+6", "")
											.replace("+60", "").replace("-", "").replace("(", "").replace(")", "");
									contact.setPhoneNumber(contactNoString);
									quotation.setUserId(contactNoString);
									break;
								case "Email Address":
									contact.setEmail(customField.getValue());
									break;

								default:
									break;
								}
							}
							quotationService.saveContact(contact);

							quotation.setCardID(cardId);
							quotation.setBoardID(boardId);
							quotation.setContact(contact);
							quotation.setStage("sales");
							quotation.setOrderType("New");

							quotationService.saveQuotation(quotation, salesPerson.getId());
						} else {
							return "Salesperson Email Not Available in the DB. Please Contact Developer.";
						}
					} else {
						return "Lead Not Assigned to Any Owner. Please Assign the owner in Kanban and Retry Again";
					}
				}

			}
			// return "redirect:/Quotation/list";
			return "Deal Imported Successfully!";

		} catch (Exception e) {
			logger.error("Failed to Add Quotation - ", e);
			return e.getMessage();
		}

	}

	/*
	 * @PostMapping(value = "/submitAddForm") public String
	 * submitForm(@ModelAttribute("quotation") Quotation quote) { //
	 * quotationService.saveQuotation(quote); return "redirect:/Quotation/list"; }
	 */

	@PostMapping(value = "/updateQuotation")
	public String updateQuotation(@ModelAttribute("quotation") Quotation quote, BindingResult bindingResult) {
		for (FieldError fieldError : bindingResult.getFieldErrors())
			logger.info(fieldError.getField() + " : " + fieldError.getDefaultMessage());

		try {
			logger.info("Inside UpdateQuotation Controller");

			if (quote.getCardID() != null) {
				quotationService.UpdateDataInKanban(quote);
			}

			quotationService.UpdateEmailAndNameInContact(quote.getContact().getId(), quote.getContact().getEmail(),
					quote.getContact().getFirstName());
			quotationService.UpdateOrderLines(quote.getOrderLines());
			quotationService.updateQuotation(quote);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Unable to update Quotation - ", e);
		}

		return "redirect:/Quotation/QuotationView?id=" + quote.getId();
	}

	@PostMapping(value = "/submitQuotation", headers = ("content-type=multipart/*"))
	public void SubmitQuotation(@ModelAttribute("quotation") Quotation quote, @RequestParam MultipartFile file,
			BindingResult bindingResult) {
		for (FieldError fieldError : bindingResult.getFieldErrors())
			logger.info(fieldError.getField() + " : " + fieldError.getDefaultMessage());

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_SALES"))
				&& (quote.getStage().equals("sales"))) {

			logger.info("Authority : Sales");
			quote.setStage("finance");
			quotationService.updateQuotation(quote);
		} else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_FINANCE"))
				&& (quote.getStage().equals("finance"))) {

			// Create Order For This Quotation as Payment is Done
			logger.info("Authority : Finance");
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

			logger.info(auth.getName());
			quote.getPayment().setVerifiedOn(now.format(formatter));
			quote.getPayment().setVerifiedBy(auth.getName());
			logger.info(quote.getPayment().toString());
			quotationService.updateQuotation(quote);
			quotationService.CreateOrder(quote.getId());
		}

		// return "redirect:/Order/list";
	}

	@PostMapping(value = "/paymentCollected", headers = ("content-type=multipart/*"))
	public String paymentCollected(@RequestParam("quotationId") String quotationID,
			@RequestParam("acquirer") String acquirer, @ModelAttribute Payment payment,
			@RequestParam MultipartFile file, BindingResult bindingResult) {

		for (FieldError fieldError : bindingResult.getFieldErrors())
			logger.info(fieldError.getField() + " : " + fieldError.getDefaultMessage());

		try {

			logger.info("Inside PaymentCollected Controller");
			logger.info(" paymentCollected >> " + payment.toString());
			logger.info(" paymentCollected >> " + quotationID);

			int paymentId = quotationService.SavePayment(payment);
			quotationService.UpdatePaymentIdInQuotation(paymentId, quotationID);

			String currentStage = "sales";

			quotationService.MoveToNextStage(currentStage, acquirer, Integer.parseInt(quotationID));

		} catch (Exception e) {
			logger.error("Failed to Collect Payment", e);
		}

		return "redirect:/Order/list";
	}

	@RequestMapping(value = "/moveToFinance")
	public @ResponseBody String MoveToFinance(@RequestParam("quotationId") String quotationID,
			@RequestParam("acquirer") String acquirer) {

		try {
			logger.info("Inside MoveToFinance Controller");
			logger.info(quotationID);

			String currentStage = "sales";
			String nextStage = quotationService.MoveToNextStage(currentStage, acquirer, Integer.parseInt(quotationID));

		} catch (Exception e) {
			logger.error("Failed to Move to Finance", e);
		}

		return "Ok";
	}

	@RequestMapping("/paymentVerified")
	public @ResponseBody String paymentVerified(@RequestParam("quotationId") String quotationId,
			@RequestParam("paymentId") int paymentId, @RequestParam("invoiceNo") String invoiceNo,
			@RequestParam("modeOfPayment") String modeOfPayment, @RequestParam("acquirer") String acquirer) {
		logger.info("/paymentVerified");

		try {
			logger.info("Inside PaymentVerified Controller");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

			if (paymentId == 0) {
				Payment payment = new Payment();

				payment.setInvoiceNo(invoiceNo);
				payment.setModeOfPayment(modeOfPayment);
				payment.setVerifiedBy(auth.getName());
				payment.setVerifiedByUserName(quotationService.GetUserNameByPhoneNo(auth.getName()));
				payment.setVerifiedOn(now.format(formatter));

				quotationService.SavePayment(payment);
			} else {
				Payment payment = quotationService.GetPaymentByID(String.valueOf(paymentId));

				payment.setInvoiceNo(invoiceNo);
				payment.setModeOfPayment(modeOfPayment);
				payment.setVerifiedBy(auth.getName());
				payment.setVerifiedByUserName(quotationService.GetUserNameByPhoneNo(auth.getName()));
				payment.setVerifiedOn(now.format(formatter));

				quotationService.UpdatePayment(payment);
			}

			// Create Order For This Quotation as Payment is Done

			quotationService.CreateOrder(Integer.parseInt(quotationId));
			String currentStage = "finance";
			quotationService.MoveToNextStage(currentStage, acquirer, Integer.parseInt(quotationId));
			return "OK";

		} catch (Exception e) {
			logger.error("Failed to Verify Payment - ", e);
			return "FAILURE";
		}
	}

	@RequestMapping("/QuotationView")
	public String viewQuotation(@RequestParam("id") String quotationID, Model model) {

		try {
			logger.info("Inside QuotationView Controller");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			List<OrderType> orderTypes = quotationService.getOrderTypes();
			List<Product> products = quotationService.GetProducts();
			List<ProductType> productTypes = quotationService.GetProductTypes();

			List<PaymentType> paymentTypes = quotationService.GetPaymentTypes();
			List<HostType> acquirers = quotationService.GetHostTypes();
			List<SalesPerson> salesPersons = quotationService.GetSalesPersons();
			List<UmobileCountry> CountryList = quotationService.GetCountryList();

			Quotation quote = quotationService.getQuotationByID(Integer.parseInt(quotationID));
			List<OrderLines> orderLines = quotationService.GetOrderLineByQuotation(Integer.parseInt(quotationID));

			// logger.info(orderLines);
			logger.info("Company Name : " + quote.getCompanyName());

			if (quote.getPayment() != null) {
				model.addAttribute("payment", quote.getPayment());
			} else {
				model.addAttribute("payment", new Payment());
			}

			if (quote.getQuotationAcceptance() != null) {
				model.addAttribute("quotationAcceptance", quote.getQuotationAcceptance());
			} else {
				model.addAttribute("quotationAcceptance", new QuotationAcceptance());
			}

			// logger.info(quote.getPayment().getCollectedOn());
			model.addAttribute("orderLines", new OrderLines());
			model.addAttribute("quotation", quote);

			model.addAttribute("orderTypes", orderTypes);
			model.addAttribute("products", products);
			model.addAttribute("productTypes", productTypes);
			model.addAttribute("orderLineList", orderLines);
			model.addAttribute("paymentTypes", paymentTypes);
			model.addAttribute("acquirers", acquirers);
			model.addAttribute("salesPersons", salesPersons);
			model.addAttribute("CountryList", CountryList);
			model.addAttribute("loggedUserRole",
					quotationService.GetRoleNameAsPerDB(auth.getAuthorities().iterator().next().getAuthority()));
			model.addAttribute("loggedUserId", auth.getName());

			double subTotal = 0.0;

			for (OrderLines item : orderLines) {
				subTotal += (item.getQuantity() * item.getProduct().getUnitPrice());
			}

			model.addAttribute("subtotal", subTotal);

			model.addAttribute("discountPrice", quote.getDiscountPrice());
			model.addAttribute("discountReason", quote.getDiscountReason());

			// model.addAttribute("mdrData", new MDRData());

		} catch (Exception e) {
			logger.error("Failed to View Quotation - ", e);
		}

		return "QuotationView";
	}

	@RequestMapping("/deleteQuotation")
	public String deleteQuotation(@RequestParam("id") String quotationID, Model model) {

		try {
			quotationService.deleteQuotation(quotationID);

		} catch (Exception e) {
			logger.error("Failed to Delete Quotation - ", e);
		}

		return "redirect:/Quotation/list";
	}

	@RequestMapping("/getProducts")
	public @ResponseBody List<Product> getProducts(@RequestParam("type") String type, Model model) {

		type = (type.equals("EZYREC_P") ? "EZYREC+" : type);

		System.out.println("getProducts >> " + type);

		List<Product> productList = quotationService.GetProductsByType(type);
		logger.info("productList : " + productList.toString());
		return productList;
	}

	/*
	 * @RequestMapping("/addOrderLine") public String
	 * addOrderLine(@ModelAttribute("orderLines") OrderLines orderLines,
	 * 
	 * @RequestParam("quotationId") int quotationId) {
	 * 
	 * logger.info("Order Lines : " + orderLines.toString());
	 * 
	 * quotationService.AddOrderLine(orderLines.getProduct().getId(), quotationId,
	 * orderLines.getQuantity());
	 * 
	 * logger.info("Order Lines : " + orderLines.toString()); return
	 * "redirect:/Quotation/QuotationView?id=" + quotationId;
	 * 
	 * }
	 */

	@RequestMapping("/addOrderLine")
	public @ResponseBody List<OrderLines> saveQuotationMDR(@RequestParam Map queryMap) {

		List<OrderLines> orderLines = new ArrayList<OrderLines>();
		try {

			logger.info("Inside QuotationView Controller");
			String quotationId = (String) queryMap.get("quotationId");
			String productId = (String) queryMap.get("productId");

			logger.info("product Id : " + productId + "quotationId : " + quotationId);

			Product product = quotationService.GetProductById(Integer.parseInt(productId));

			if (product.getProductType().equals("EZYSPLIT")) {
				int quotationEzysplitMDRRateId = quotationService.SaveQuotationEzysplitMDRRate(
						Integer.parseInt(productId), Integer.parseInt(quotationId), product);
				quotationService.AddOrderLineForEzysplit(Integer.parseInt(productId), Integer.parseInt(quotationId), 1,
						quotationEzysplitMDRRateId);
			} else {
				int quotationMDRRateId = quotationService.SaveQuotationMDRRate(Integer.parseInt(productId),
						Integer.parseInt(quotationId));
				quotationService.AddOrderLine(Integer.parseInt(productId), Integer.parseInt(quotationId), 1,
						quotationMDRRateId);
			}

			orderLines = quotationService.GetOrderLineByQuotation(Integer.parseInt(quotationId));

			// logger.info("Order Lines : " + orderLines.toString());

		} catch (Exception e) {

			logger.error("Failed to Add Order Line - ", e);
		}

		return orderLines;

	}

	@RequestMapping("/deleteOrderLines")
	public @ResponseBody String deleteOrderLines(@RequestParam("orderLineIds") String orderLineIds,
			@RequestParam("quotationId") String quotationId) {

		try {

			logger.info("Inside QuotationView Controller");
			logger.info(orderLineIds);
			String[] orderLineIDArray = orderLineIds.split(",");

			quotationService.deleteOrderLines(orderLineIDArray);

			List<OrderLines> orderLines = quotationService.GetOrderLineByQuotation(Integer.parseInt(quotationId));
			Quotation quotation = quotationService.getQuotationByID(Integer.parseInt(quotationId));

			if (!orderLines.isEmpty()) {
				quotation.setDiscountPrice(0.00);
				quotationService.updateQuotation(quotation);
			} else {
				double subTotal = 0.00;

				for (OrderLines item : orderLines) {
					subTotal += item.getQuantity() * item.getProduct().getUnitPrice();
				}

				if (quotation.getDiscountPrice() > subTotal) {
					quotation.setDiscountPrice(0.00);
					quotationService.updateQuotation(quotation);
				}
			}

			return "Ok";

		} catch (Exception e) {
			logger.error("Failed to Delete OrderLine - ", e);
			return "e";
		}

	}

	@RequestMapping("/updateQuotationMDR")
	public @ResponseBody String updateQuotationMDR(@ModelAttribute() MDRData mdrData, BindingResult bindingResult) {

		for (FieldError fieldError : bindingResult.getFieldErrors())
			logger.info(fieldError.getField() + " : " + fieldError.getDefaultMessage());

		try {
			logger.info("Inside QuotationView Controller");

			logger.info("saveQuotationMDR Controller");
			logger.info(mdrData.toString());

			if (mdrData.getProductType().equals("EZYSPLIT")) {

				QuotationEzySplitMDRRate quoteRate = new QuotationEzySplitMDRRate();
				LocalDateTime now = LocalDateTime.now();

				quoteRate.setUnitPrice(mdrData.getAmount());

				// Assign the Customer,Merchant,Host Rates
				quoteRate.setLoc_Credit_Merch_Master_Insta3(mdrData.getLoc_Credit_Merch_Master_Insta3());
				quoteRate.setLoc_Credit_Cus_Master_Insta3(mdrData.getLoc_Credit_Cus_Master_Insta3());
				quoteRate.setLoc_Credit_Host_Master_Insta3(mdrData.getLoc_Credit_Host_Master_Insta3());

				quoteRate.setLoc_Credit_Merch_Master_Insta6(mdrData.getLoc_Credit_Merch_Master_Insta6());
				quoteRate.setLoc_Credit_Cus_Master_Insta6(mdrData.getLoc_Credit_Cus_Master_Insta6());
				quoteRate.setLoc_Credit_Host_Master_Insta6(mdrData.getLoc_Credit_Host_Master_Insta6());

				quoteRate.setLoc_Credit_Merch_Master_Insta9(mdrData.getLoc_Credit_Merch_Master_Insta9());
				quoteRate.setLoc_Credit_Cus_Master_Insta9(mdrData.getLoc_Credit_Cus_Master_Insta9());
				quoteRate.setLoc_Credit_Host_Master_Insta9(mdrData.getLoc_Credit_Host_Master_Insta9());

				quoteRate.setLoc_Credit_Merch_Master_Insta12(mdrData.getLoc_Credit_Merch_Master_Insta12());
				quoteRate.setLoc_Credit_Cus_Master_Insta12(mdrData.getLoc_Credit_Cus_Master_Insta12());
				quoteRate.setLoc_Credit_Host_Master_Insta12(mdrData.getLoc_Credit_Host_Master_Insta12());

				// Calculate and assign the Mobi MDR Rate as per the changed Customer/Merchant
				// Rates
				quoteRate.setLoc_Credit_Mobi_Master_Insta3(
						((mdrData.getLoc_Credit_Merch_Master_Insta3() + mdrData.getLoc_Credit_Cus_Master_Insta3())
								- mdrData.getLoc_Credit_Host_Master_Insta3()));
				quoteRate.setLoc_Credit_Mobi_Master_Insta6(
						((mdrData.getLoc_Credit_Merch_Master_Insta6() + mdrData.getLoc_Credit_Cus_Master_Insta6())
								- mdrData.getLoc_Credit_Host_Master_Insta6()));
				quoteRate.setLoc_Credit_Mobi_Master_Insta9(
						((mdrData.getLoc_Credit_Merch_Master_Insta9() + mdrData.getLoc_Credit_Cus_Master_Insta9())
								- mdrData.getLoc_Credit_Host_Master_Insta9()));
				quoteRate.setLoc_Credit_Mobi_Master_Insta12(
						((mdrData.getLoc_Credit_Merch_Master_Insta12() + mdrData.getLoc_Credit_Cus_Master_Insta12())
								- mdrData.getLoc_Credit_Host_Master_Insta12()));

				quoteRate.setProductSettlement(mdrData.getProductSettlement());
				quoteRate.setBoostSettlement(mdrData.getBoostSettlement());
				quoteRate.setGrabSettlement(mdrData.getGrabSettlement());
				quoteRate.setFpxSettlement(mdrData.getFpxSettlement());
				quoteRate.setTngSettlement(mdrData.getTngSettlement());
				quoteRate.setShopeepaySettlement(mdrData.getShopeepaySettlement());

				quoteRate.setBoostMDREcomm(mdrData.getBoostMDREcomm());
				quoteRate.setBoostMDRQR(mdrData.getBoostMDRQR());
				quoteRate.setGrabMDREcomm(mdrData.getGrabMDREcomm());
				quoteRate.setGrabMDRQR(mdrData.getGrabMDRQR());
				quoteRate.setfPXMDR_Percent(mdrData.getfPXMDR_Percent());
				quoteRate.setfPXMDR_RM(mdrData.getfPXMDR_RM());
				quoteRate.setHostRateIdRef(mdrData.getHostRateId());
				quoteRate.setTngMDREcomm(mdrData.getTngMDREcomm());
				quoteRate.setTngMDRQR(mdrData.getTngMDRQR());
				
				quoteRate.setShopeepayMDREcomm(mdrData.getShopeepayMDREcomm());
				quoteRate.setShopeepayMDRQR(mdrData.getShopeepayMDRQR());

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

				quoteRate.setId(mdrData.getQuotationEzysplitMdrRateId());
				quotationService.UpdateQuotationEzysplitMDRRate(quoteRate, productId, quotationid);

			} else {
				QuotationMDRRate quoteRate = new QuotationMDRRate();
				LocalDateTime now = LocalDateTime.now();

				// HostRate hostRate =
				// quotationService.GetHostRate(Integer.parseInt(mdrData.getHostRateId()));

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

				quoteRate.setLoc_Credit_Host_Master(mdrData.getLoc_Credit_Host_Master());
				quoteRate.setLoc_Credit_Host_Union(mdrData.getLoc_Credit_Host_Union());
				quoteRate.setLoc_Credit_Host_Visa(mdrData.getLoc_Credit_Host_Visa());
				quoteRate.setLoc_Debit_Host_Master(mdrData.getLoc_Debit_Host_Master());
				quoteRate.setLoc_Debit_Host_Union(mdrData.getLoc_Debit_Host_Union());
				quoteRate.setLoc_Debit_Host_Visa(mdrData.getLoc_Debit_Host_Visa());

				quoteRate.setFor_Credit_Host_Master(mdrData.getFor_Credit_Host_Master());
				quoteRate.setFor_Credit_Host_Union(mdrData.getFor_Credit_Host_Union());
				quoteRate.setFor_Credit_Host_Visa(mdrData.getFor_Credit_Host_Visa());
				quoteRate.setFor_Debit_Host_Master(mdrData.getFor_Debit_Host_Master());
				quoteRate.setFor_Debit_Host_Union(mdrData.getFor_Debit_Host_Union());
				quoteRate.setFor_Debit_Host_Visa(mdrData.getFor_Debit_Host_Visa());

				// Calculate and assign Mobi MDR

				quoteRate.setLoc_Credit_Mobi_Master(
						mdrData.getFor_Credit_Merch_Master() - mdrData.getLoc_Credit_Host_Master());
				quoteRate.setLoc_Credit_Mobi_Union(
						mdrData.getFor_Credit_Merch_Union() - mdrData.getLoc_Credit_Host_Union());
				quoteRate.setLoc_Credit_Mobi_Visa(
						mdrData.getFor_Credit_Merch_Visa() - mdrData.getLoc_Credit_Host_Visa());
				quoteRate.setLoc_Debit_Host_Master(
						mdrData.getFor_Debit_Merch_Master() - mdrData.getLoc_Debit_Host_Master());
				quoteRate.setLoc_Debit_Mobi_Union(
						mdrData.getFor_Debit_Merch_Union() - mdrData.getLoc_Debit_Host_Union());
				quoteRate.setLoc_Debit_Mobi_Visa(mdrData.getFor_Debit_Merch_Visa() - mdrData.getLoc_Debit_Host_Visa());

				quoteRate.setFor_Credit_Mobi_Master(
						mdrData.getLoc_Credit_Merch_Master() - mdrData.getFor_Credit_Host_Master());
				quoteRate.setFor_Credit_Mobi_Union(
						mdrData.getLoc_Credit_Merch_Union() - mdrData.getFor_Credit_Host_Union());
				quoteRate.setFor_Credit_Mobi_Visa(
						mdrData.getLoc_Credit_Merch_Visa() - mdrData.getFor_Credit_Host_Visa());
				quoteRate.setFor_Debit_Mobi_Master(
						mdrData.getLoc_Debit_Merch_Master() - mdrData.getFor_Debit_Host_Master());
				quoteRate.setFor_Debit_Mobi_Union(
						mdrData.getLoc_Debit_Merch_Union() - mdrData.getFor_Debit_Host_Union());
				quoteRate.setFor_Debit_Mobi_Visa(mdrData.getLoc_Debit_Merch_Visa() - mdrData.getFor_Debit_Host_Visa());

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
				
				quoteRate.setTngMDREcomm(mdrData.getTngMDREcomm());
				quoteRate.setTngMDRQR(mdrData.getTngMDRQR());
				
				quoteRate.setShopeepayMDREcomm(mdrData.getShopeepayMDREcomm());
				quoteRate.setShopeepayMDRQR(mdrData.getShopeepayMDRQR());
				
				quoteRate.setTngSettlement(mdrData.getTngSettlement());
				quoteRate.setShopeepaySettlement(mdrData.getShopeepaySettlement());

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
				quotationService.UpdateQuotationMDRRate(quoteRate, productId, quotationid);

			}
		} catch (Exception e) {
			logger.error("Failed to Update Quotation MDR - ", e);

		}
		return "Ok";

	}

	@RequestMapping("/getQuotationMDR")
	public @ResponseBody MDRData getStandardOrQuotationMDR(@RequestParam("orderLineId") int orderLineId, Model model) {

		MDRData mdrData = new MDRData();

		try {
			OrderLines orderLine = quotationService.getOrderLineById(orderLineId);
			String ezysplitFlag = orderLine.getQuotationEzysplitMDRRate() == null ? "NON-EZYSPLIT" : "EZYSPLIT";

			if (ezysplitFlag.equals("EZYSPLIT")) {

				mdrData.setProductId(orderLine.getProduct().getId());
				mdrData.setQuotationId(orderLine.getQuotation().getId());
				mdrData.setOrderLineId(orderLine.getId());

				mdrData.setQuotationEzysplitMdrRateId(orderLine.getQuotationEzysplitMDRRate().getId());

				mdrData.setAmount(orderLine.getQuotationEzysplitMDRRate().getUnitPrice());
				mdrData.setProductName(orderLine.getQuotationEzysplitMDRRate().getProductName());
				mdrData.setHostRateId(orderLine.getQuotationEzysplitMDRRate().getHostRateIdRef());

				mdrData.setProductSettlement(orderLine.getQuotationEzysplitMDRRate().getProductSettlement());
				mdrData.setBoostSettlement(orderLine.getQuotationEzysplitMDRRate().getBoostSettlement());
				mdrData.setGrabSettlement(orderLine.getQuotationEzysplitMDRRate().getGrabSettlement());
				mdrData.setFpxSettlement(orderLine.getQuotationEzysplitMDRRate().getFpxSettlement());
				mdrData.setTngSettlement(orderLine.getQuotationEzysplitMDRRate().getTngSettlement());
				mdrData.setShopeepaySettlement(orderLine.getQuotationEzysplitMDRRate().getShopeepaySettlement());

				mdrData.setBoostMDREcomm(orderLine.getQuotationEzysplitMDRRate().getBoostMDREcomm());
				mdrData.setBoostMDRQR(orderLine.getQuotationEzysplitMDRRate().getBoostMDRQR());
				mdrData.setGrabMDREcomm(orderLine.getQuotationEzysplitMDRRate().getGrabMDREcomm());
				mdrData.setGrabMDRQR(orderLine.getQuotationEzysplitMDRRate().getGrabMDRQR());
				mdrData.setfPXMDR_Percent(orderLine.getQuotationEzysplitMDRRate().getfPXMDR_Percent());
				mdrData.setfPXMDR_RM(orderLine.getQuotationEzysplitMDRRate().getfPXMDR_RM());
				mdrData.setTngMDREcomm(orderLine.getQuotationEzysplitMDRRate().getTngMDREcomm());
				mdrData.setTngMDRQR(orderLine.getQuotationEzysplitMDRRate().getTngMDRQR());
				mdrData.setShopeepayMDREcomm(orderLine.getQuotationEzysplitMDRRate().getShopeepayMDREcomm());
				mdrData.setShopeepayMDRQR(orderLine.getQuotationEzysplitMDRRate().getShopeepayMDRQR());

				mdrData.setLoc_Credit_Merch_Master_Insta3(
						orderLine.getQuotationEzysplitMDRRate().getLoc_Credit_Merch_Master_Insta3());
				mdrData.setLoc_Credit_Cus_Master_Insta3(
						orderLine.getQuotationEzysplitMDRRate().getLoc_Credit_Cus_Master_Insta3());
				mdrData.setLoc_Credit_Host_Master_Insta3(
						orderLine.getQuotationEzysplitMDRRate().getLoc_Credit_Host_Master_Insta3());

				mdrData.setLoc_Credit_Merch_Master_Insta6(
						orderLine.getQuotationEzysplitMDRRate().getLoc_Credit_Merch_Master_Insta6());
				mdrData.setLoc_Credit_Cus_Master_Insta6(
						orderLine.getQuotationEzysplitMDRRate().getLoc_Credit_Cus_Master_Insta6());
				mdrData.setLoc_Credit_Host_Master_Insta6(
						orderLine.getQuotationEzysplitMDRRate().getLoc_Credit_Host_Master_Insta6());

				mdrData.setLoc_Credit_Merch_Master_Insta9(
						orderLine.getQuotationEzysplitMDRRate().getLoc_Credit_Merch_Master_Insta9());
				mdrData.setLoc_Credit_Cus_Master_Insta9(
						orderLine.getQuotationEzysplitMDRRate().getLoc_Credit_Cus_Master_Insta9());
				mdrData.setLoc_Credit_Host_Master_Insta9(
						orderLine.getQuotationEzysplitMDRRate().getLoc_Credit_Host_Master_Insta9());

				mdrData.setLoc_Credit_Merch_Master_Insta12(
						orderLine.getQuotationEzysplitMDRRate().getLoc_Credit_Merch_Master_Insta12());
				mdrData.setLoc_Credit_Cus_Master_Insta12(
						orderLine.getQuotationEzysplitMDRRate().getLoc_Credit_Cus_Master_Insta12());
				mdrData.setLoc_Credit_Host_Master_Insta12(
						orderLine.getQuotationEzysplitMDRRate().getLoc_Credit_Host_Master_Insta12());

				// Addition fields to hold Data
				mdrData.setSubscription(orderLine.getQuotationEzysplitMDRRate().getSubscription());
				mdrData.setShowInQuote(orderLine.getQuotationEzysplitMDRRate().getShowInQuote());
				mdrData.setProductType(orderLine.getQuotationEzysplitMDRRate().getProductType());
				mdrData.setIncludeWallet(orderLine.getQuotationEzysplitMDRRate().getIncludeWallet());
				mdrData.setPayLater(orderLine.getQuotationEzysplitMDRRate().getPayLater());
				mdrData.setHostType(orderLine.getQuotationEzysplitMDRRate().getHostType());
				mdrData.setCreatedOn(orderLine.getQuotationEzysplitMDRRate().getCreatedOn());

			} else {

				mdrData.setProductId(orderLine.getProduct().getId());
				mdrData.setQuotationId(orderLine.getQuotation().getId());
				mdrData.setOrderLineId(orderLine.getId());

				mdrData.setQuotationMdrRateId(orderLine.getQuotationMDRRate().getId());

				mdrData.setAmount(orderLine.getQuotationMDRRate().getUnitPrice());
				mdrData.setProductName(orderLine.getQuotationMDRRate().getProductName());
				mdrData.setHostRateId(orderLine.getQuotationMDRRate().getHostRateIdRef());

				mdrData.setProductSettlement(orderLine.getQuotationMDRRate().getProductSettlement());
				mdrData.setBoostSettlement(orderLine.getQuotationMDRRate().getBoostSettlement());
				mdrData.setGrabSettlement(orderLine.getQuotationMDRRate().getGrabSettlement());
				mdrData.setFpxSettlement(orderLine.getQuotationMDRRate().getFpxSettlement());
				mdrData.setTngSettlement(orderLine.getQuotationMDRRate().getTngSettlement());
				mdrData.setShopeepaySettlement(orderLine.getQuotationMDRRate().getShopeepaySettlement());


				mdrData.setBoostMDREcomm(orderLine.getQuotationMDRRate().getBoostMDREcomm());
				mdrData.setBoostMDRQR(orderLine.getQuotationMDRRate().getBoostMDRQR());
				mdrData.setGrabMDREcomm(orderLine.getQuotationMDRRate().getGrabMDREcomm());
				mdrData.setGrabMDRQR(orderLine.getQuotationMDRRate().getGrabMDRQR());
				mdrData.setfPXMDR_Percent(orderLine.getQuotationMDRRate().getfPXMDR_Percent());
				mdrData.setfPXMDR_RM(orderLine.getQuotationMDRRate().getfPXMDR_RM());
				mdrData.setTngMDREcomm(orderLine.getQuotationMDRRate().getTngMDREcomm());
				mdrData.setTngMDRQR(orderLine.getQuotationMDRRate().getTngMDRQR());
				mdrData.setShopeepayMDREcomm(orderLine.getQuotationMDRRate().getShopeepayMDREcomm());
				mdrData.setShopeepayMDRQR(orderLine.getQuotationMDRRate().getShopeepayMDRQR());


				// Merch
				mdrData.setFor_Credit_Merch_Master(orderLine.getQuotationMDRRate().getFor_Credit_Merch_Master());
				mdrData.setFor_Credit_Merch_Union(orderLine.getQuotationMDRRate().getFor_Credit_Merch_Union());
				mdrData.setFor_Credit_Merch_Visa(orderLine.getQuotationMDRRate().getFor_Credit_Merch_Visa());
				mdrData.setFor_Debit_Merch_Master(orderLine.getQuotationMDRRate().getFor_Debit_Merch_Master());
				mdrData.setFor_Debit_Merch_Union(orderLine.getQuotationMDRRate().getFor_Debit_Merch_Union());
				mdrData.setFor_Debit_Merch_Visa(orderLine.getQuotationMDRRate().getFor_Debit_Merch_Visa());

				mdrData.setLoc_Credit_Merch_Master(orderLine.getQuotationMDRRate().getLoc_Credit_Merch_Master());
				mdrData.setLoc_Credit_Merch_Union(orderLine.getQuotationMDRRate().getLoc_Credit_Merch_Union());
				mdrData.setLoc_Credit_Merch_Visa(orderLine.getQuotationMDRRate().getLoc_Credit_Merch_Visa());
				mdrData.setLoc_Debit_Merch_Master(orderLine.getQuotationMDRRate().getLoc_Debit_Merch_Master());
				mdrData.setLoc_Debit_Merch_Union(orderLine.getQuotationMDRRate().getLoc_Debit_Merch_Union());
				mdrData.setLoc_Debit_Merch_Visa(orderLine.getQuotationMDRRate().getLoc_Debit_Merch_Visa());

				// Host
				mdrData.setFor_Credit_Host_Master(orderLine.getQuotationMDRRate().getFor_Credit_Host_Master());
				mdrData.setFor_Credit_Host_Union(orderLine.getQuotationMDRRate().getFor_Credit_Host_Union());
				mdrData.setFor_Credit_Host_Visa(orderLine.getQuotationMDRRate().getFor_Credit_Host_Visa());
				mdrData.setFor_Debit_Host_Master(orderLine.getQuotationMDRRate().getFor_Debit_Host_Master());
				mdrData.setFor_Debit_Host_Union(orderLine.getQuotationMDRRate().getFor_Debit_Host_Union());
				mdrData.setFor_Debit_Host_Visa(orderLine.getQuotationMDRRate().getFor_Debit_Host_Visa());

				mdrData.setLoc_Credit_Host_Master(orderLine.getQuotationMDRRate().getLoc_Credit_Host_Master());
				mdrData.setLoc_Credit_Host_Union(orderLine.getQuotationMDRRate().getLoc_Credit_Host_Union());
				mdrData.setLoc_Credit_Host_Visa(orderLine.getQuotationMDRRate().getLoc_Credit_Host_Visa());
				mdrData.setLoc_Debit_Host_Master(orderLine.getQuotationMDRRate().getLoc_Debit_Host_Master());
				mdrData.setLoc_Debit_Host_Union(orderLine.getQuotationMDRRate().getLoc_Debit_Host_Union());
				mdrData.setLoc_Debit_Host_Visa(orderLine.getQuotationMDRRate().getLoc_Debit_Host_Visa());

				mdrData.setLoc_Credit_Cus_Master(orderLine.getQuotationMDRRate().getLoc_Credit_Cus_Master());
				mdrData.setLoc_Credit_Cus_Union(orderLine.getQuotationMDRRate().getLoc_Credit_Cus_Union());
				mdrData.setLoc_Credit_Cus_Visa(orderLine.getQuotationMDRRate().getLoc_Credit_Cus_Visa());

				// Addition fields to hold Data
				mdrData.setSubscription(orderLine.getQuotationMDRRate().getSubscription());
				mdrData.setShowInQuote(orderLine.getQuotationMDRRate().getShowInQuote());
				mdrData.setProductType(orderLine.getQuotationMDRRate().getProductType());
				mdrData.setIncludeWallet(orderLine.getQuotationMDRRate().getIncludeWallet());
				mdrData.setPayLater(orderLine.getQuotationMDRRate().getPayLater());
				mdrData.setHostType(orderLine.getQuotationMDRRate().getHostType());
				mdrData.setCreatedOn(orderLine.getQuotationMDRRate().getCreatedOn());

			}
		} catch (Exception e) {
			logger.error("Failed to Get Quotation MDR - ", e);
		}
		return mdrData;
	}

	@RequestMapping("/generateQuotationMobi")
	public @ResponseBody Object GenerateQuotationMobi(@RequestParam("quotationId") String quotationId,
			HttpServletRequest request, HttpServletResponse response)
			throws DocumentException, MalformedURLException, IOException {

		logger.info("Generating PDF for QuotationID : " + quotationId);

		Quotation quotation = quotationService.getQuotationByID(Integer.parseInt(quotationId));
		Object responseData = generatePDFService.GenerateQuotationMobi(quotation, request);
		return responseData;
	}

}
