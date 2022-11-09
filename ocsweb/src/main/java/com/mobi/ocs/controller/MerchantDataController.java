package com.mobi.ocs.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.itextpdf.text.BadElementException;
import com.mobi.ocs.dao.DotnetDataDAO;
import com.mobi.ocs.dto.CommonResponseData;
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
import com.mobi.ocs.entity.OldNotesDetails;
import com.mobi.ocs.entity.OldOrderDetails;
import com.mobi.ocs.entity.OrderNotes;
import com.mobi.ocs.entity.SalesPerson;
import com.mobi.ocs.entity.Salutation;
import com.mobi.ocs.entity.StageMovement;
import com.mobi.ocs.entity.UmobileCountry;
import com.mobi.ocs.entity.Order;
import com.mobi.ocs.entity.OrderLines;
import com.mobi.ocs.entity.UmobileState;
import com.mobi.ocs.entity.UmobileTown;
import com.mobi.ocs.entity.dotnetOcs.Director_dotnet;
import com.mobi.ocs.entity.dotnetOcs.Document_dotnet;
import com.mobi.ocs.entity.dotnetOcs.MerchantDetails;
import com.mobi.ocs.entity.dotnetOcs.StageMovement_dotnet;
import com.mobi.ocs.modal.OldProductListData;
import com.mobi.ocs.entity.UmobileMCC;
import com.mobi.ocs.service.AssignService;
import com.mobi.ocs.service.DotnetDataService;
import com.mobi.ocs.service.MerchantRegistrationService;
import com.mobi.ocs.service.QuotationService;
import com.mobi.ocs.utilities.EmailTemplateNotes;

@Controller
@RequestMapping("/MerchantData")
public class MerchantDataController {

	@Autowired
	private DotnetDataService dotnetDataService;

	@Autowired
	private QuotationService quotationService;

	@Autowired
	private MerchantRegistrationService merchantRegistrationService;

	protected static Logger logger = Logger.getLogger(MerchantDataController.class);

	@RequestMapping("/list")
	public String OrderList(Model model) {

		logger.info("Inside MerchantData Controller");
		List<MerchantDetails> merchantDetails = dotnetDataService.getDotnetOCSMerchantData();

		model.addAttribute("merchantDetails", merchantDetails);
		return "dotnetData/MerchantDataList";
	}

	@RequestMapping("/OrderView")
	public String viewOrder(@RequestParam("id") String orderID, Model model) {

		MerchantDetails merchantDetail = null;
		try {

			logger.info("Inside OrderView Controller");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			merchantDetail = dotnetDataService.GetDotnetOCSMerchantDataById(orderID);
			List<Director_dotnet> directors = dotnetDataService
					.GetDotnetOCSDirectorsByOrderID(merchantDetail.getOrderId());
			List<StageMovement_dotnet> stageMovement = dotnetDataService.GetDotnetOCSStageMovementByOrderId(orderID);

			List<Document_dotnet> documents = dotnetDataService
					.GetDotnetOCSDocumentsByUserId(merchantDetail.getUserId());

			List<OldOrderDetails> oldOrderDetails = dotnetDataService.getOldOrderDetailsById(orderID);
			List<OldNotesDetails> oldNotesDetails = dotnetDataService.getOldOrderNotesByOrderId(orderID);

			logger.info("oldOrderDetails >> " + oldOrderDetails.toString());

			String fileOrderId = orderID;
			String fileUserName = merchantDetail.getUserName();

			List<Document_dotnet> quotationFileList = new ArrayList<Document_dotnet>();
			List<Document_dotnet> fileShareFileList = new ArrayList<Document_dotnet>();
			List<Document_dotnet> scheduleFileList = new ArrayList<Document_dotnet>();
			List<Document_dotnet> welcomeFileList = new ArrayList<Document_dotnet>();

			Document_dotnet welcomeLetter = new Document_dotnet();
			Document_dotnet schedule = new Document_dotnet();

			try {
				quotationFileList = (List<Document_dotnet>) dotnetDataService.getQuotationFileList(fileOrderId);
				documents.addAll(quotationFileList);
				
				fileShareFileList = (List<Document_dotnet>) dotnetDataService.getFileShareFileList(fileOrderId);
				documents.addAll(fileShareFileList);

				scheduleFileList = (ArrayList<Document_dotnet>) dotnetDataService.getScheduleFileList(fileUserName);

				if (scheduleFileList.size() > 0) {
					int scheduleSize = scheduleFileList.size();
//					documents.add(scheduleFileList.get(scheduleSize - 1));
					schedule = scheduleFileList.get(scheduleSize - 1);
				}

				welcomeFileList = (ArrayList<Document_dotnet>) dotnetDataService.getWelcomeLFileList(fileUserName);
				if (welcomeFileList.size() > 0) {
					int welcomeLetterSize = welcomeFileList.size();
//					documents.add(welcomeFileList.get(welcomeLetterSize - 1));
					welcomeLetter = welcomeFileList.get(welcomeLetterSize - 1);
				}

			} catch (NoSuchFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			logger.info(merchantDetail.toString());

			for (Director_dotnet director : directors) {
				logger.info("Director_dotnet >> " + director.getId());
			}

			model.addAttribute("merchantDetail", merchantDetail);
			model.addAttribute("directors", directors);
			model.addAttribute("stageMovement", stageMovement);
			model.addAttribute("documents", documents);

			model.addAttribute("welcomeLetter", welcomeLetter);
			model.addAttribute("schedule", schedule);

			model.addAttribute("oldOrderDetails", oldOrderDetails);
			model.addAttribute("oldNotesDetails", oldNotesDetails);

		} catch (Exception e) {
			logger.error("Error in Viewing Merchant Data >> ", e);
		}
		return "dotnetData/MerchantDataView";
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

}
