package com.mobi.ocs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.mobi.ocs.dto.CommonResponseData;
import com.mobi.ocs.modal.ForgotPasswordRequestData;
import com.mobi.ocs.service.UserServices;

@Controller
public class ForgotPasswordController {

	@RequestMapping("/ForgotPassword")
	public String gotoForgotPassword() {
		return "ForgotPassword";
	}

}
