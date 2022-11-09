package com.mobi.ocs.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mobi.ocs.entity.Director;
import com.mobi.ocs.entity.HostRate;
import com.mobi.ocs.entity.HostType;
import com.mobi.ocs.entity.Order;
import com.mobi.ocs.entity.OrderLines;
import com.mobi.ocs.entity.Product;
import com.mobi.ocs.entity.ProductType;
import com.mobi.ocs.entity.TPAFileToUmobile;
import com.mobi.ocs.service.AssignService;
import com.mobi.ocs.service.QuotationService;
import com.mobi.ocs.service.TPAService;
import com.mobi.ocs.utilities.Constants;

@Controller
@RequestMapping(value = "/TPA")
public class TPAController {

	@Autowired
	private TPAService tpaService;
	
	@Autowired
	private QuotationService quotationService;


	protected static Logger logger = Logger.getLogger(TPAController.class);

	// have to modify
	@RequestMapping(value = "/list")
	public String TPAList(Model model) {

		//Orders in UMobile Stage will be Displayed
		logger.info("Inside TPAList Controller");
		List<Order> orders = quotationService.GetOrdersByStage("umobile");

		model.addAttribute("orders", orders);
		return "TPAList";
	}
	
	// have to modify
//		@RequestMapping(value = "/downloadTPA")
//		public String DownloadTPA(@RequestParam("orderIds") String orderIds) {
//
//			System.out.println("In Download TPA Controller");
//			
//			 System.out.println(orderIds);
//			 String[] orderIDArray = orderIds.split(",");  
//			 
//			 tpaService.InsertTPADataInDB(orderIDArray);
//
//			
//			return "OK";
//		}

}
