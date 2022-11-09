package com.mobi.ocs.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mobi.ocs.dto.ProductDto;
import com.mobi.ocs.entity.HostRate;
import com.mobi.ocs.entity.HostType;
import com.mobi.ocs.entity.Product;
import com.mobi.ocs.entity.ProductType;
import com.mobi.ocs.entity.StandardMDRRate;
import com.mobi.ocs.service.AssignService;
import com.mobi.ocs.service.QuotationService;

@Controller
@RequestMapping(value="/HostMDR")
public class HostController {

	@Autowired
	private QuotationService service;
	@Autowired
	private AssignService assignService;
	
	protected static Logger logger = Logger.getLogger(HostController.class);
	
	
	@RequestMapping(value = "/hostList")
	public String HostMDRList(Model model) {
		
		try {
			logger.info("In HostList Controller");
			List<HostRate> hostRateList = service.GetHostRates();
			List<ProductType> productTypes = service.GetProductTypes();
			List<HostType> hostTypes = service.GetHostTypes();
			
			model.addAttribute("hostRateList",hostRateList);
			model.addAttribute("productTypes",productTypes);
			model.addAttribute("hostTypes",hostTypes);
			
		} catch (Exception e) {
			logger.info(e);
		}
		
		return "HostRateList";
	}
	
	@RequestMapping(value = "/hostAdd", method = RequestMethod.POST)
	public String AddHostRate(@ModelAttribute("hostRate") HostRate hostRate) {
		
		try {
			logger.info("In HostAdd Controller");
			service.SaveHostRate(hostRate);
			
		} catch (Exception e) {
			logger.info(e);
		}
		return "HostRateList";
	}
	
}
