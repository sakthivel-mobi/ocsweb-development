package com.mobi.ocs.controller;

import java.awt.MediaTracker;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mobi.ocs.dao.QuotationDAO;
import com.mobi.ocs.dto.CommonResponseData;
import com.mobi.ocs.entity.Product;

@RestController
public class ProductRestController {

	@Autowired
	QuotationDAO quotationDAO;

	@RequestMapping(path = "/product/name/list", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getProductList() {
		List<String> response = quotationDAO.getAllProductList();
		return new CommonResponseData("0000", "Success", response);
	}

}