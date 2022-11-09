package com.mobi.ocs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mobi.ocs.modal.PhoneNumberSearchRequestData;
import com.mobi.ocs.modal.UpdateAuthoritiesRequestData;
import com.mobi.ocs.modal.UserApprovalRequestData;
import com.mobi.ocs.service.UserServices;

@RestController
public class UserApprovalRestController {

	@Autowired
	UserServices userServices;

	@RequestMapping(path = "/user/approval/phone/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object searchUserPhoneNumber(@RequestBody PhoneNumberSearchRequestData requestData) {
		Object response = userServices.searchUserPhoneNumber(requestData.getPhoneNumber());
		return response;
	}

	@RequestMapping(path = "/user/approval/submit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object approveUser(@RequestBody UserApprovalRequestData requestData) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object response = userServices.submitForApproval(requestData, auth.getName());
		return response;
	}
	
	@RequestMapping(path = "/user/authorities/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object authoritiesUpdate(@RequestBody UpdateAuthoritiesRequestData requestData) {
		Object response = userServices.authoritiesUpdate(requestData);
		return response;
	}
}
