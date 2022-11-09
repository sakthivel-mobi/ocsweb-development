package com.mobi.ocs.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.event.internal.EntityCopyAllowedLoggedObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobi.ocs.dto.EmailNotes;
import com.mobi.ocs.dto.HubSpotInfo;
import com.mobi.ocs.dto.Property;
import com.mobi.ocs.entity.AccountType;
import com.mobi.ocs.entity.Acquirer;
import com.mobi.ocs.entity.BusinessType;
import com.mobi.ocs.entity.CompanyType;
import com.mobi.ocs.entity.Contact;
import com.mobi.ocs.entity.Director;
import com.mobi.ocs.entity.MasterMerchant;
import com.mobi.ocs.entity.UmobileECommIndustry;
import com.mobi.ocs.entity.MerchantIdType;
import com.mobi.ocs.entity.NatureOfBusiness;
import com.mobi.ocs.entity.OrderNotes;
import com.mobi.ocs.entity.Quotation;
import com.mobi.ocs.entity.SalesPerson;
import com.mobi.ocs.entity.Salutation;
import com.mobi.ocs.entity.UmobileCountry;
import com.mobi.ocs.entity.Order;
import com.mobi.ocs.entity.OrderLines;
import com.mobi.ocs.entity.UmobileState;
import com.mobi.ocs.entity.UmobileTown;
import com.mobi.ocs.entity.UmobileMCC;
import com.mobi.ocs.service.AssignService;
import com.mobi.ocs.service.QuotationService;
import com.mobi.ocs.utilities.EmailTemplateNotes;

@Controller
@RequestMapping("/MyAction")
public class MyActionController {

	@Autowired
	private QuotationService quotationService;
	@Autowired
	private AssignService assignService;

	protected static Logger logger = Logger.getLogger(MyActionController.class);

	@RequestMapping("/list")
	public String OrderList(Authentication authentication, Model model) {

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String loggedUserRole = quotationService
				.GetRoleNameAsPerDB(userDetails.getAuthorities().iterator().next().getAuthority());
		logger.info("Retrieving Orders For the Role - " + loggedUserRole);

		if (loggedUserRole.equals("sales") || loggedUserRole.equals("sales-manager")) {
			
			SalesPerson salesperson = quotationService.GetSalesPersonByPhoneNumber(authentication.getName());
			
			List<Quotation> quotationsBySalesId = new ArrayList<Quotation>();
			if(salesperson != null) {
				quotationsBySalesId = quotationService.GetQuotationsBySalesId(salesperson.getId());
			}
			
			model.addAttribute("quotations", quotationsBySalesId);
			logger.info("Size of Quotations For Salesperson ID - " + quotationsBySalesId.size());
			return "QuotationList";
		}
		else if(loggedUserRole.equals("finance")) {
			List<Quotation> quotationsByRole = quotationService.GetQuotationsByStage(loggedUserRole);
			model.addAttribute("quotations", quotationsByRole);
			logger.info("Size of Quotations By Role - " + quotationsByRole.size());
			return "QuotationList";
		}
		else {
			List<Order> ordersByRole = quotationService.getOrdersByRole(loggedUserRole);
			model.addAttribute("orders", ordersByRole);
			return "OrderList";
		}
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
				
				if(contactID.equals("") && companyID.equals("")){
					return "Contact & Company Detail Not available for the Deal.";
				}
				else {

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

						contactNoString = companyProp.getPhone() == null ? ""
								: companyProp.getPhone().getValue();
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
						//quotation.setSalesPerson(dealOwner);

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
			// return "redirect:/Quotation/list";
			return "Deal Imported Successfully!";

		} catch (Exception e) {
			logger.error("Failed to Add Quotation - " , e);
			return e.getMessage();
		}

	}

	@RequestMapping("/OrderView")
	public String viewOrder(@RequestParam("id") String orderID, Model model) {

		try {

			logger.info("Inside OrderView Controller");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			Order order = quotationService.GetOrderByID(orderID);
			// logger.info(order.getQuotation().getContact().getFirstName());
			List<OrderLines> orderLines = quotationService.GetOrderLineByQuotation(order.getQuotation().getId());
			// Get Values For DropDown
			List<MerchantIdType> merchantIdTypeList = quotationService.GetMerchantIdTypeList();
			List<NatureOfBusiness> natureOfBusinessList = quotationService.GetNatureOfBusinessList();
			List<UmobileECommIndustry> eCommIndustryList = quotationService.GetEcommIndustryList();
			List<UmobileMCC> umobileMCCList = quotationService.GetUmobileMCCList();
			List<AccountType> accountTypeList = quotationService.GetAccountTypeList();
			List<BusinessType> businessTypeList = quotationService.GetBusinessTypeList();
			List<CompanyType> companyTypeList = quotationService.GetCompanyTypeList();
			List<Acquirer> acquirerList = quotationService.GetAcquirerList();
			List<MasterMerchant> masterMerchants = quotationService.GetMasterMerchants();
			List<UmobileState> StateList = quotationService.GetStateList();
			List<UmobileTown> CityList = quotationService.GetCityList();
			List<UmobileCountry> CountryList = quotationService.GetCountryList();
			List<Salutation> salutationList = quotationService.GetSalutationList();

			model.addAttribute("orderLineList", orderLines);
			model.addAttribute("merchantIdTypeList", merchantIdTypeList);
			model.addAttribute("natureOfBusinessList", natureOfBusinessList);
			model.addAttribute("eCommIndustryList", eCommIndustryList);
			model.addAttribute("umobileMCCList", umobileMCCList);
			model.addAttribute("accountTypeList", accountTypeList);
			model.addAttribute("businessTypeList", businessTypeList);
			model.addAttribute("companyTypeList", companyTypeList);
			model.addAttribute("acquirerList", acquirerList);
			model.addAttribute("masterMerchants", masterMerchants);
			model.addAttribute("StateList", StateList);
			model.addAttribute("CityList", CityList);
			model.addAttribute("CountryList", CountryList);
			model.addAttribute("salutationList", salutationList);
			model.addAttribute("loggedUserRole",
					quotationService.GetRoleNameAsPerDB(auth.getAuthorities().iterator().next().getAuthority()));

			model.addAttribute("order", order);

		} catch (Exception e) {
			logger.error("Error In Order View Controller : " , e);
		}

		return "OrderView";
	}

	@RequestMapping("/OrderViewPaydee")
	public String viewOrderPaydee(@RequestParam("id") String orderID, Model model) {

		try {

			logger.info("Inside OrderViewPaydee Controller");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			Order order = quotationService.GetOrderByID(orderID);
			// logger.info(order.getQuotation().getContact().getFirstName());
			List<OrderLines> orderLines = quotationService.GetOrderLineByQuotation(order.getQuotation().getId());
			// Get Values For DropDown
			List<MerchantIdType> merchantIdTypeList = quotationService.GetMerchantIdTypeList();
			List<NatureOfBusiness> natureOfBusinessList = quotationService.GetNatureOfBusinessList();
			List<UmobileECommIndustry> eCommIndustryList = quotationService.GetEcommIndustryList();
			List<UmobileMCC> umobileMCCList = quotationService.GetUmobileMCCList();
			List<AccountType> accountTypeList = quotationService.GetAccountTypeList();
			List<CompanyType> companyTypeList = quotationService.GetCompanyTypeList();
			List<Acquirer> acquirerList = quotationService.GetAcquirerList();
			List<MasterMerchant> masterMerchants = quotationService.GetMasterMerchants();
			List<SalesPerson> salesPersons = quotationService.GetSalesPersons();

			List<UmobileState> StateList = quotationService.GetStateList();
			List<UmobileTown> CityList = quotationService.GetCityList();
			List<UmobileCountry> CountryList = quotationService.GetCountryList();
			List<Salutation> salutationList = quotationService.GetSalutationList();

			model.addAttribute("orderLineList", orderLines);
			model.addAttribute("merchantIdTypeList", merchantIdTypeList);
			model.addAttribute("natureOfBusinessList", natureOfBusinessList);
			model.addAttribute("eCommIndustryList", eCommIndustryList);
			model.addAttribute("umobileMCCList", umobileMCCList);
			model.addAttribute("accountTypeList", accountTypeList);
			model.addAttribute("companyTypeList", companyTypeList);
			model.addAttribute("acquirerList", acquirerList);
			model.addAttribute("StateList", StateList);
			model.addAttribute("CityList", CityList);
			model.addAttribute("CountryList", CountryList);
			model.addAttribute("salesPersons", salesPersons);
			model.addAttribute("salutationList", salutationList);
			model.addAttribute("loggedUserRole",
					quotationService.GetRoleNameAsPerDB(auth.getAuthorities().iterator().next().getAuthority()));

			model.addAttribute("order", order);

		} catch (Exception e) {
			logger.error("Error In Order View Paydee Controller : " , e);
		}

		return "OrderViewPaydee";
	}

	@RequestMapping("/saveDirector")
	public @ResponseBody String SaveDirector(@ModelAttribute() Director director, @RequestParam String orderId) {
		try {

			logger.info("Saving Director for Order ID : " + orderId);
			quotationService.SaveDirector(director, orderId);
			return "OK";

		} catch (Exception e) {

			logger.error("Error In Saving Director : " , e);
			return "FAILURE";
		}

	}

	@RequestMapping("/saveNotes")
	public @ResponseBody String SaveNotes(@ModelAttribute() OrderNotes notes, @RequestParam String orderId,
			@RequestParam String companyName) {

		try {

			logger.info("Saving Notes for Order ID : " + orderId);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			EmailNotes params = new EmailNotes();
			params.setOrderId(orderId);
			params.setSender(auth.getName());
			params.setRegarding(notes.getSubject());
			params.setNotes(notes.getNotes());
			params.setCompanyName(companyName);

			String emailBody = EmailTemplateNotes.Template(params);

			String fromAddress = "Info@gomobi.io";
			// String fromAddress = notes.getFromUser();
			String toAddress = notes.getNotifyTo();
			String ccAddress = "";
			String subject = "Message for Order ID : " + orderId;

			quotationService.SaveNotes(notes, orderId, auth.getName());
			quotationService.SendEmail(fromAddress, toAddress, ccAddress, subject, "", "", emailBody, null);
			return "OK";

		} catch (Exception e) {
			logger.error("Error In Saving Notes : " , e);
			return "FAILURE";
		}

	}

	@RequestMapping("/submitOrder")
	public String SubmitOrder(Order order, Model model, BindingResult bindingResult, HttpServletRequest req)
			throws FileNotFoundException, IOException {
		for (FieldError fieldError : bindingResult.getFieldErrors())
			logger.info(fieldError.getField() + " : " + fieldError.getDefaultMessage());
		
		try {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.info("Submit Order Controller");
		String submittedBy = auth.getName();
		String submittedByRole = order.getQuotation().getStage();

		String currentStage = quotationService.MoveToNextStage(order.getQuotation().getStage(),
				order.getQuotation().getAcquirer(), order.getQuotation().getId());

		quotationService.UpdateOrder(order);
		String orderViewURL = quotationService.GetURLWithContextPath(req) + "/Order/list";
		quotationService.SendStageMovementEmail(order, submittedBy, submittedByRole, currentStage, orderViewURL);
		
		} 
		catch (Exception e) {
			logger.error("Error In Submitting Order : " , e);
		}


		return "redirect:/MyAction/list";
	}

	@RequestMapping("/updateOrder")
	public String UpdateOrder(Order order, Model model, BindingResult bindingResult) {

		for (FieldError fieldError : bindingResult.getFieldErrors())
			logger.info(fieldError.getField() + " : " + fieldError.getDefaultMessage());

		logger.info("Update Order Controller");

		quotationService.UpdateOrder(order);

		return "redirect:/Order/list";
	}

}
