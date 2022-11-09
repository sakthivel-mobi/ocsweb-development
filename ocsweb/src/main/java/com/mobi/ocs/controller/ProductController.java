package com.mobi.ocs.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.mchange.v2.cfg.PropertiesConfigSource.Parse;
import com.mobi.ocs.dto.MobiMDRRate;
import com.mobi.ocs.dto.ProductDto;
import com.mobi.ocs.entity.HostRate;
import com.mobi.ocs.entity.HostType;
import com.mobi.ocs.entity.Product;
import com.mobi.ocs.entity.ProductType;
import com.mobi.ocs.entity.StandardEzySplitMDRRate;
import com.mobi.ocs.entity.StandardMDRRate;
import com.mobi.ocs.entity.WalletRate;
import com.mobi.ocs.service.AssignService;
import com.mobi.ocs.service.QuotationService;

@Controller
@RequestMapping(value = "/Product")
public class ProductController {

	@Autowired
	private QuotationService service;
	@Autowired
	private AssignService assignService;

	protected static Logger logger = Logger.getLogger(ProductController.class);

	@RequestMapping(value = "/productList")
	public String ProductList(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		List<Product> productList = service.GetProducts();
		List<HostRate> hostRateList = service.GetHostRates();
		List<WalletRate> walletRateList = service.GetWalletRates();

		List<ProductType> productTypes = service.GetProductTypes();
		List<HostType> hostTypes = service.GetHostTypes();

		model.addAttribute("productList", productList);
		model.addAttribute("hostRateList", hostRateList);
		model.addAttribute("walletRateList", walletRateList);
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("hostTypes", hostTypes);
		model.addAttribute("hostRate", new HostRate());
		model.addAttribute("product", new Product());
		model.addAttribute("walletRate", new WalletRate());
		model.addAttribute("loggedUserRole",
				service.GetRoleNameAsPerDB(auth.getAuthorities().iterator().next().getAuthority()));
		return "Products";
	}

	@RequestMapping(value = "/productAdd", method = RequestMethod.POST)
	public String AddProduct(@ModelAttribute("product") Product product, BindingResult bindingResult) {
		for (FieldError fieldError : bindingResult.getFieldErrors())
			logger.warn(fieldError.getField() + " : " + fieldError.getDefaultMessage());

		try {

			if (product.getProductType().equals("EZYSPLIT")) {
				logger.info("Adding EZYSPLIT Product");
				product.setStandardmdrRate(null);

				product.getStandardEzySplitMDRRate().setLoc_Credit_Mobi_Master_Insta3(

						(product.getStandardEzySplitMDRRate().getLoc_Credit_Merch_Master_Insta3()
								+ product.getStandardEzySplitMDRRate().getLoc_Credit_Cus_Master_Insta3())
								- product.getStandardEzySplitMDRRate().getLoc_Credit_Host_Master_Insta3());

				product.getStandardEzySplitMDRRate().setLoc_Credit_Mobi_Master_Insta6(
						(product.getStandardEzySplitMDRRate().getLoc_Credit_Merch_Master_Insta6()
								+ product.getStandardEzySplitMDRRate().getLoc_Credit_Cus_Master_Insta6())
								- product.getStandardEzySplitMDRRate().getLoc_Credit_Host_Master_Insta6());

				product.getStandardEzySplitMDRRate().setLoc_Credit_Mobi_Master_Insta9(
						(product.getStandardEzySplitMDRRate().getLoc_Credit_Merch_Master_Insta9()
								+ product.getStandardEzySplitMDRRate().getLoc_Credit_Cus_Master_Insta9())
								- product.getStandardEzySplitMDRRate().getLoc_Credit_Host_Master_Insta9());

				product.getStandardEzySplitMDRRate().setLoc_Credit_Mobi_Master_Insta12(
						(product.getStandardEzySplitMDRRate().getLoc_Credit_Merch_Master_Insta12()
								+ product.getStandardEzySplitMDRRate().getLoc_Credit_Cus_Master_Insta12())
								- product.getStandardEzySplitMDRRate().getLoc_Credit_Host_Master_Insta12());

				logger.info(product.toString());

				service.SaveProduct(product);
			} else {

				// StandardMDRRate mdrRate = product.getStandardmdrRate();

				product.setStandardEzySplitMDRRate(null);
				/*
				 * int mdrID = service.SaveStandardMDRRate(mdrRate); mdrRate.setId(mdrID);
				 * 
				 * product.setStandardmdrRate(mdrRate);
				 */

				product.getStandardmdrRate()
						.setLoc_Debit_Mobi_Master(product.getStandardmdrRate().getLoc_Debit_Merch_Master()
								- product.getStandardmdrRate().getLoc_Debit_Host_Master());

				product.getStandardmdrRate()
						.setLoc_Debit_Mobi_Union(product.getStandardmdrRate().getLoc_Debit_Merch_Union()
								- product.getStandardmdrRate().getLoc_Debit_Host_Union());

				product.getStandardmdrRate()
						.setLoc_Debit_Mobi_Visa(product.getStandardmdrRate().getLoc_Debit_Merch_Visa()
								- product.getStandardmdrRate().getLoc_Debit_Host_Visa());

				product.getStandardmdrRate()
						.setLoc_Credit_Mobi_Master(product.getStandardmdrRate().getLoc_Credit_Merch_Master()
								- product.getStandardmdrRate().getLoc_Credit_Host_Master());

				product.getStandardmdrRate()
						.setLoc_Credit_Mobi_Union(product.getStandardmdrRate().getLoc_Credit_Merch_Union()
								- product.getStandardmdrRate().getLoc_Credit_Host_Union());

				product.getStandardmdrRate()
						.setLoc_Credit_Mobi_Visa(product.getStandardmdrRate().getLoc_Credit_Merch_Visa()
								- product.getStandardmdrRate().getLoc_Credit_Host_Visa());

				product.getStandardmdrRate()
						.setFor_Debit_Mobi_Master(product.getStandardmdrRate().getFor_Debit_Merch_Master()
								- product.getStandardmdrRate().getFor_Debit_Host_Master());

				product.getStandardmdrRate()
						.setFor_Debit_Mobi_Union(product.getStandardmdrRate().getFor_Debit_Merch_Union()
								- product.getStandardmdrRate().getFor_Debit_Host_Union());

				product.getStandardmdrRate()
						.setFor_Debit_Mobi_Visa(product.getStandardmdrRate().getFor_Debit_Merch_Visa()
								- product.getStandardmdrRate().getFor_Debit_Host_Visa());

				product.getStandardmdrRate()
						.setFor_Credit_Mobi_Master(product.getStandardmdrRate().getFor_Credit_Merch_Master()
								- product.getStandardmdrRate().getFor_Credit_Host_Master());

				product.getStandardmdrRate()
						.setFor_Credit_Mobi_Union(product.getStandardmdrRate().getFor_Credit_Merch_Union()
								- product.getStandardmdrRate().getFor_Credit_Host_Union());

				product.getStandardmdrRate()
						.setFor_Credit_Mobi_Visa(product.getStandardmdrRate().getFor_Credit_Merch_Visa()
								- product.getStandardmdrRate().getFor_Credit_Host_Visa());

				logger.info("productAdd >> " + product.toString());
				service.SaveProduct(product);
			}
		} catch (Exception e) {
			logger.error("Error in Adding Product : " + e);
		}

		return "redirect:/Product/productList";
	}

	@RequestMapping(value = "/productView")
	public String ProductView(Model model, @RequestParam("id") int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Product product = service.GetProductById(id);

		List<Product> productHistoryList = service.GetProductHistoryList(id);
		List<ProductType> productTypes = service.GetProductTypes();
		List<HostType> hostTypes = service.GetHostTypes();

		model.addAttribute("productHistoryList", productHistoryList);
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("hostTypes", hostTypes);
		model.addAttribute("product", product);
		model.addAttribute("hostType", product.getHostType());
		model.addAttribute("loggedUserRole",
				service.GetRoleNameAsPerDB(auth.getAuthorities().iterator().next().getAuthority()));

		return "ProductView";
	}

	@RequestMapping(value = "/hostRateView")
	public String HostRateView(Model model, @RequestParam("id") int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		HostRate hostRate = service.GetHostRate(id);

		List<HostRate> hostRateHistoryList = service.GetHostRateHistoryList(id);
		List<ProductType> productTypes = service.GetProductTypes();
		List<HostType> hostTypes = service.GetHostTypes();

		model.addAttribute("hostRateHistoryList", hostRateHistoryList);
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("hostTypes", hostTypes);
		model.addAttribute("hostRate", hostRate);
		model.addAttribute("loggedUserRole",
				service.GetRoleNameAsPerDB(auth.getAuthorities().iterator().next().getAuthority()));

		return "HostRateView";
	}

	@RequestMapping(value = "/walletRateView")
	public String WalletRateView(Model model, @RequestParam("id") int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		WalletRate walletRate = service.GetWalletRateByID(id);

		List<ProductType> productTypes = service.GetProductTypes();

		model.addAttribute("productTypes", productTypes);
		model.addAttribute("walletRate", walletRate);
		model.addAttribute("loggedUserRole",
				service.GetRoleNameAsPerDB(auth.getAuthorities().iterator().next().getAuthority()));

		return "WalletRateView";
	}

	@RequestMapping(value = "/productHistoryView")
	public String ProductHistoryView(Model model, @RequestParam("id") int id) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Product product = service.GetProductById(id);
		List<ProductType> productTypes = service.GetProductTypes();
		List<HostType> hostTypes = service.GetHostTypes();

		model.addAttribute("productTypes", productTypes);
		model.addAttribute("hostTypes", hostTypes);
		model.addAttribute("product", product);
		model.addAttribute("loggedUserRole",
				service.GetRoleNameAsPerDB(auth.getAuthorities().iterator().next().getAuthority()));

		return "ProductHistoryView";
	}

	@RequestMapping(value = "/productEdit", method = RequestMethod.POST)
	public String EditProduct(@ModelAttribute("product") Product product, BindingResult bindingResult) {

		for (FieldError fieldError : bindingResult.getFieldErrors())
			logger.info(fieldError.getField() + " : " + fieldError.getDefaultMessage());

		// For Product Edit, A Copy of Current Values are saved with Ref ID and the
		// Edited values are updated in the Current Row

		// New Row is Inserted

		Product NewProduct = service.GetProductById(product.getId());

		if (product.getProductType().equals("EZYSPLIT")) {
			NewProduct.setStandardmdrRate(null);
		} else {
			NewProduct.setStandardEzySplitMDRRate(null);
		}

		NewProduct.setRefId(String.valueOf(product.getId()));
		service.SaveProduct(NewProduct);
logger.info("mdr is "+NewProduct.getStandardmdrRate().getTngMDREcomm());
		// Update the Current Product
		service.UpdateProduct(product);
		return "redirect:/Product/productList";
	}

	// Host

	@RequestMapping(value = "/hostAdd", method = RequestMethod.POST)
	public String AddHostRate(@ModelAttribute("hostRate") HostRate hostRate, BindingResult bindingResult) {

		for (FieldError fieldError : bindingResult.getFieldErrors())
			logger.info(fieldError.getField() + " : " + fieldError.getDefaultMessage());

		service.SaveHostRate(hostRate);

		return "redirect:/Product/productList";
	}

	@RequestMapping(value = "/hostRateEdit", method = RequestMethod.POST)
	public String HostRateEdit(@ModelAttribute("hostRate") HostRate hostRate, BindingResult bindingResult) {

		for (FieldError fieldError : bindingResult.getFieldErrors())
			logger.info(fieldError.getField() + " : " + fieldError.getDefaultMessage());

		// For HostRate Edit, A Copy of Current Values are saved with Ref ID and the
		// Edited values are updated in the Current Row

		// New Row is Inserted

		HostRate hostRateForHistory = service.GetHostRate(hostRate.getId());

		hostRateForHistory.setRefId(String.valueOf(hostRate.getId()));
		service.SaveHostRate(hostRateForHistory);

		service.UpdateHostRate(hostRate);

		return "redirect:/Product/hostRateView?id=" + hostRate.getId();
	}

	@RequestMapping(value = "/hostRateHistoryView")
	public String HostRateHistoryView(Model model, @RequestParam("id") int id) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		HostRate hostRate = service.GetHostRate(id);
		List<ProductType> productTypes = service.GetProductTypes();
		List<HostType> hostTypes = service.GetHostTypes();

		model.addAttribute("productTypes", productTypes);
		model.addAttribute("hostTypes", hostTypes);
		model.addAttribute("hostRate", hostRate);
		model.addAttribute("loggedUserRole",
				service.GetRoleNameAsPerDB(auth.getAuthorities().iterator().next().getAuthority()));

		return "HostRateHistoryView";
	}

	@RequestMapping(value = "/walletRateAdd", method = RequestMethod.POST)
	public String AddWalletRate(@ModelAttribute("walletRate") WalletRate walletRate, BindingResult bindingResult) {

		for (FieldError fieldError : bindingResult.getFieldErrors())
			logger.info(fieldError.getField() + " : " + fieldError.getDefaultMessage());

		service.SaveWalletRate(walletRate);

		return "redirect:/Product/productList";
	}

	@RequestMapping(value = "/walletRateEdit", method = RequestMethod.POST)
	public String WalletRateEdit(@ModelAttribute("walletRate") WalletRate walletRate, BindingResult bindingResult) {

		logger.info("walletRate >> " + walletRate.toString());
		for (FieldError fieldError : bindingResult.getFieldErrors())
			logger.info(fieldError.getField() + " : " + fieldError.getDefaultMessage());

		service.UpdateWalletRate(walletRate);

		// return "redirect:/Product/productList";
		return "redirect:/Product/walletRateView?id=" + walletRate.getId();
	}

	@RequestMapping(value = "/getProductById", method = RequestMethod.GET)
	public @ResponseBody Product GetProductById(@RequestParam int productId) {

		Product product = service.GetProductById(productId);

		return product;
	}

	@RequestMapping(value = "/getHostRates", method = RequestMethod.GET)
	public @ResponseBody List<HostRate> getHostRates(@RequestParam Map queryMap) {

		List<HostRate> hostRateList = new ArrayList<HostRate>();
		try {
			String hostType = (String) queryMap.get("hostType");
			String productType = (String) queryMap.get("productType");
			logger.info("Before DB Action:HostType : " + hostType + "productType : " + productType);

			hostRateList = service.GetHostRates(hostType, productType);
			logger.info(hostRateList.size());

		} catch (Exception e) {
			logger.error("Error in getting Host Rates" + e);
		}
		return hostRateList;
	}

	@RequestMapping(value = "/getHostRateValues", method = RequestMethod.GET)
	public @ResponseBody HostRate getHostRateValues(@RequestParam int hostRate) {

		HostRate hostRateValues = service.GetHostRate(hostRate);
		logger.info(hostRateValues.toString());
		return hostRateValues;
	}

	@RequestMapping(value = "/getHostRateValueEZYSPLIT", method = RequestMethod.GET)
	public @ResponseBody double getHostRateValuesEZYSPLIT(@RequestParam int hostRate) {

		HostRate hostRateValues = service.GetHostRate(hostRate);

		// Send Only Local Credit Host MasterCard Value for EZYSPLIT Product
		return hostRateValues.getLoc_Credit_Host_Master_EZYSPLIT();
	}

	@RequestMapping(value = "/getWalletRates", method = RequestMethod.GET)
	public @ResponseBody List<WalletRate> getWalletRates(@RequestParam Map queryMap) {
		String productType = (String) queryMap.get("productType");
		logger.info("Before DB Action:productType : " + productType);

		List<WalletRate> walletRateList = service.GetWalletRates(productType);
		logger.info(walletRateList.size());

		return walletRateList;
	}

	@RequestMapping(value = "/getWalletRateValues", method = RequestMethod.GET)
	public @ResponseBody WalletRate getWalletRateValues(@RequestParam int walletRate) {
    
		logger.info("in /getWalletRateValues "+walletRate);
		WalletRate walletRateValues = service.GetWalletRateByID(walletRate);

		return walletRateValues;
	}

	@RequestMapping(value = "/makeProductActive", method = RequestMethod.POST)
	public @ResponseBody String MakeProductActive(@RequestParam Map queryMap) {

		try {

			String productId = (String) queryMap.get("productId");
			String newProductName = (String) queryMap.get("newProductName");

			Product NewProduct = service.GetProductById(Integer.parseInt(productId));

			if (NewProduct.getProductType().equals("EZYSPLIT")) {
				NewProduct.setStandardmdrRate(null);
			} else {
				NewProduct.setStandardEzySplitMDRRate(null);
			}

			NewProduct.setProductName(newProductName);
			NewProduct.setRefId(null);
			service.SaveProduct(NewProduct);

			return "OK";
		} catch (Exception e) {
			logger.error("Error in Making Product Active", e);
			return "Error";
		}

	}

	@RequestMapping(value = "/calculateMobiMDR", method = RequestMethod.GET)
	public @ResponseBody MobiMDRRate calculateMobiMDR(@ModelAttribute StandardMDRRate standardMDRRate,
			@ModelAttribute HostRate hostRate) {

		MobiMDRRate mobiMDRRate = new MobiMDRRate();

		try {
			logger.info(standardMDRRate);
			logger.info(hostRate);
			String.format("%.2f", standardMDRRate.getLoc_Debit_Merch_Master() - hostRate.getLoc_Debit_Host_Master());

			mobiMDRRate.setLoc_Debit_Mobi_Master(String.format("%.2f",
					standardMDRRate.getLoc_Debit_Merch_Master() - hostRate.getLoc_Debit_Host_Master()));
			mobiMDRRate.setLoc_Debit_Mobi_Union(String.format("%.2f",
					standardMDRRate.getLoc_Debit_Merch_Union() - hostRate.getLoc_Debit_Host_Union()));
			mobiMDRRate.setLoc_Debit_Mobi_Visa(String.format("%.2f",
					standardMDRRate.getLoc_Debit_Merch_Visa() - hostRate.getLoc_Debit_Host_Visa()));

			mobiMDRRate.setLoc_Credit_Mobi_Master(String.format("%.2f",
					standardMDRRate.getLoc_Credit_Merch_Master() - hostRate.getLoc_Credit_Host_Master()));
			mobiMDRRate.setLoc_Credit_Mobi_Union(String.format("%.2f",
					standardMDRRate.getLoc_Credit_Merch_Union() - hostRate.getLoc_Credit_Host_Union()));
			mobiMDRRate.setLoc_Credit_Mobi_Visa(String.format("%.2f",
					standardMDRRate.getLoc_Credit_Merch_Visa() - hostRate.getLoc_Credit_Host_Visa()));

			mobiMDRRate.setFor_Debit_Mobi_Master(String.format("%.2f",
					standardMDRRate.getFor_Debit_Merch_Master() - hostRate.getFor_Debit_Host_Master()));
			mobiMDRRate.setFor_Debit_Mobi_Union(String.format("%.2f",
					standardMDRRate.getFor_Debit_Merch_Union() - hostRate.getFor_Debit_Host_Union()));
			mobiMDRRate.setFor_Debit_Mobi_Visa(String.format("%.2f",
					standardMDRRate.getFor_Debit_Merch_Visa() - hostRate.getFor_Debit_Host_Visa()));

			mobiMDRRate.setFor_Credit_Mobi_Master(String.format("%.2f",
					standardMDRRate.getFor_Credit_Merch_Master() - hostRate.getFor_Credit_Host_Master()));
			mobiMDRRate.setFor_Credit_Mobi_Union(String.format("%.2f",
					standardMDRRate.getFor_Credit_Merch_Union() - hostRate.getFor_Credit_Host_Union()));
			mobiMDRRate.setFor_Credit_Mobi_Visa(String.format("%.2f",
					standardMDRRate.getFor_Credit_Merch_Visa() - hostRate.getFor_Credit_Host_Visa()));

		} catch (Exception e) {
			logger.error("Error in Calculating Mobi MDR", e);
		}

		return mobiMDRRate;
	}

	@RequestMapping(value = "/calculateMobiMDR_EZYSPLIT", method = RequestMethod.GET)
	public @ResponseBody MobiMDRRate CalculateMobiMDR_EZYSPLIT(
			@ModelAttribute StandardEzySplitMDRRate standardEzySplitMDRRate, @ModelAttribute HostRate hostRate) {

		MobiMDRRate mobiMDRRate = new MobiMDRRate();
		mobiMDRRate.setLoc_Credit_Mobi_Master_Insta3(String.format("%.2f",
				((standardEzySplitMDRRate.getLoc_Credit_Merch_Master_Insta3()
						+ standardEzySplitMDRRate.getLoc_Credit_Cus_Master_Insta3())
						- standardEzySplitMDRRate.getLoc_Credit_Host_Master_Insta3())));
		mobiMDRRate.setLoc_Credit_Mobi_Master_Insta6(String.format("%.2f",
				((standardEzySplitMDRRate.getLoc_Credit_Merch_Master_Insta6()
						+ standardEzySplitMDRRate.getLoc_Credit_Cus_Master_Insta6())
						- standardEzySplitMDRRate.getLoc_Credit_Host_Master_Insta6())));
		mobiMDRRate.setLoc_Credit_Mobi_Master_Insta9(String.format("%.2f",
				((standardEzySplitMDRRate.getLoc_Credit_Merch_Master_Insta9()
						+ standardEzySplitMDRRate.getLoc_Credit_Cus_Master_Insta9())
						- standardEzySplitMDRRate.getLoc_Credit_Host_Master_Insta9())));
		mobiMDRRate.setLoc_Credit_Mobi_Master_Insta12(String.format("%.2f",
				((standardEzySplitMDRRate.getLoc_Credit_Merch_Master_Insta12()
						+ standardEzySplitMDRRate.getLoc_Credit_Cus_Master_Insta12())
						- standardEzySplitMDRRate.getLoc_Credit_Host_Master_Insta12())));

		return mobiMDRRate;
	}

	/*
	 * @RequestMapping(value = "/hostList") public String HostMDRList(Model model) {
	 * List<HostRate> hostRateList = service.GetHostRates();
	 * 
	 * List<ProductType> productTypes = service.GetProductTypes(); List<HostType>
	 * hostTypes = service.GetHostTypes();
	 * 
	 * model.addAttribute("hostRateList",hostRateList);
	 * model.addAttribute("productTypes",productTypes);
	 * model.addAttribute("hostTypes",hostTypes);
	 * 
	 * return "ProductList"; }
	 */

}
