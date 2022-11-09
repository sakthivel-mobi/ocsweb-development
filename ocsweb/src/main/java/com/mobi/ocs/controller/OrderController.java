package com.mobi.ocs.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.text.BadElementException;
import com.mobi.ocs.dto.EmailNotes;
import com.mobi.ocs.dto.EmailStageMovement;
import com.mobi.ocs.entity.AccountType;
import com.mobi.ocs.entity.Acquirer;
import com.mobi.ocs.entity.Bank;
import com.mobi.ocs.entity.BusinessType;
import com.mobi.ocs.entity.CompanyType;
import com.mobi.ocs.entity.Director;
import com.mobi.ocs.entity.MasterMerchant;
import com.mobi.ocs.entity.UmobileECommIndustry;
import com.mobi.ocs.entity.MerchantIdType;
import com.mobi.ocs.entity.NatureOfBusiness;
import com.mobi.ocs.entity.OrderNotes;
import com.mobi.ocs.entity.SalesPerson;
import com.mobi.ocs.entity.Salutation;
import com.mobi.ocs.entity.StageMovement;
import com.mobi.ocs.entity.UmobileCountry;
import com.mobi.ocs.entity.Order;
import com.mobi.ocs.entity.OrderLines;
import com.mobi.ocs.entity.UmobileState;
import com.mobi.ocs.entity.UmobileTown;
import com.mobi.ocs.entity.UmobileMCC;
import com.mobi.ocs.service.AssignService;
import com.mobi.ocs.service.QuotationService;
import com.mobi.ocs.utilities.EmailTemplateNotes;
import com.mobi.ocs.utilities.smtp.SmtpEmail;
import com.mobi.ocs.utilities.smtp.SmtpEmailClient;

@Controller
@RequestMapping("/Order")
public class OrderController {

	@Autowired
	private QuotationService quotationService;

	@Autowired
	private AssignService assignService;

	protected static Logger logger = Logger.getLogger(OrderController.class);

	@RequestMapping("/list")
	public String OrderList(Model model) {

		logger.info("Inside Order Controller");
		List<Order> orders = quotationService.getOrders();

		model.addAttribute("orders", orders);
		return "OrderList";
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
			// List<SalesPerson> salesPersons = quotationService.GetSalesPersons();
			List<UmobileState> StateList = quotationService.GetStateList();
			List<UmobileTown> CityList = quotationService.GetCityList();
			List<UmobileCountry> CountryList = quotationService.GetCountryList();
			List<Salutation> salutationList = quotationService.GetSalutationList();
			List<Bank> bankList = quotationService.GetBankList();

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
			model.addAttribute("bankList", bankList);
			// model.addAttribute("salesPersons", salesPersons);
			model.addAttribute("loggedUserRole",
					quotationService.GetRoleNameAsPerDB(auth.getAuthorities().iterator().next().getAuthority()));

			model.addAttribute("order", order);

		} catch (Exception e) {
			logger.info(e);
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
			List<Bank> bankList = quotationService.GetBankList();

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
			model.addAttribute("bankList", bankList);

			model.addAttribute("loggedUserRole",
					quotationService.GetRoleNameAsPerDB(auth.getAuthorities().iterator().next().getAuthority()));

			model.addAttribute("order", order);

		} catch (Exception e) {
			logger.error("Failed to View OrderViewPaydee - ", e);
		}
		return "OrderViewPaydee";
	}

	@RequestMapping("/saveDirector")
	public @ResponseBody String SaveDirector(@ModelAttribute() Director director, @RequestParam String orderId) {
		logger.info("Saving Director for Order ID : " + orderId);
		quotationService.SaveDirector(director, orderId);
		return "OK";
	}

	@RequestMapping("/saveNotes")
	public @ResponseBody String SaveNotes(@ModelAttribute() OrderNotes notes, @RequestParam String orderId,
			@RequestParam String companyName) {
		logger.info("Saving Notes for Order ID : " + orderId);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		EmailNotes params = new EmailNotes();
		params.setOrderId(orderId);
		params.setSender(auth.getName());
		params.setRegarding(notes.getSubject());
		params.setNotes(notes.getNotes());
		params.setCompanyName(companyName);

		String emailBody = EmailTemplateNotes.Template(params);

//		String fromAddress = "Info@gomobi.io";
//		// String fromAddress = notes.getFromUser();
//		String toAddress = notes.getNotifyTo();
//		String ccAddress = "";
//		String subject = "Message for Order ID : " + orderId;

		String toAddress = notes.getNotifyTo();
		String ccAddress = "";
		String subject = "Message for Order ID : " + orderId;
		String bccAddress = null;
		String textBody = "notes";

		quotationService.SaveNotes(notes, orderId, auth.getName());
		quotationService.setNotesEmail(orderId, companyName, notes, auth.getName());

		SmtpEmail send = new SmtpEmail(subject, toAddress, ccAddress, bccAddress, null, textBody, emailBody);

		logger.info("This is the object we passed into the api" + send);
		SmtpEmailClient smtp = new SmtpEmailClient();

		logger.info("Sending to Api");
		smtp.sendMessage(send);
		// quotationService.SendEmail(fromAddress, toAddress, ccAddress, subject, "",
		// "", emailBody, null);
		return "OK";
	}

	@RequestMapping("/submitOrder")
	public String SubmitOrder(Order order, Model model, BindingResult bindingResult, HttpServletRequest req) {
		try {
			for (FieldError fieldError : bindingResult.getFieldErrors())
				logger.info(fieldError.getField() + " : " + fieldError.getDefaultMessage());

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			logger.info("Submit Order Controller");
			logger.info(order.getQuotation().getStage());

			order.setMasterCardMCC(order.getVisaMCC());
			order.setUnionPayMCC(order.getVisaMCC());

			String submittedBy = auth.getName();

			String submittedByRole = order.getQuotation().getStage();

			String currentStage = quotationService.MoveToNextStage(order.getQuotation().getStage(),
					order.getQuotation().getAcquirer(), order.getQuotation().getId());

			StageMovement stageMovement = new StageMovement();
			stageMovement.setUserName(submittedBy);
			stageMovement.setFromStage(submittedByRole);
			stageMovement.setOrderId(order.getId());
			stageMovement.setToStage(currentStage);

			logger.info("submittedByRole >> " + stageMovement.toString());

			// log stage movement
			quotationService.saveStageMovement(stageMovement);

			quotationService.UpdateOrder(order);

			String orderViewURL = quotationService.GetURLWithContextPath(req) + "/Order/list";

			quotationService.SendStageMovementEmail(order, submittedBy, submittedByRole, currentStage, orderViewURL);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/Order/list";
	}

	@RequestMapping("/updateOrder")
	public String UpdateOrder(Order order, Model model, BindingResult bindingResult) {

		for (FieldError fieldError : bindingResult.getFieldErrors())
			logger.info(fieldError.getField() + " : " + fieldError.getDefaultMessage());

		logger.info("Update Order Controller");

		quotationService.UpdateOrder(order);

		return "redirect:/Order/OrderView?id=" + order.getId();
	}

}
