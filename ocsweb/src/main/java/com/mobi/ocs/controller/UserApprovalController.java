package com.mobi.ocs.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserApprovalController {
	

	@RequestMapping("Approvals")
	public String userApproval() {
		return "Approvals";
	}

}
