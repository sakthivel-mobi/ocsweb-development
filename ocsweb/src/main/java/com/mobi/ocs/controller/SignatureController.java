package com.mobi.ocs.controller;

import java.util.ArrayList;

import com.mobi.ocs.dao.MerchantDAO;
import com.mobi.ocs.entity.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignatureController {

	@GetMapping("/Signature")
	public String gotoSigntaureView() {
		return "Signature";
	}

	@Autowired
	MerchantDAO merchantDAO;

}
