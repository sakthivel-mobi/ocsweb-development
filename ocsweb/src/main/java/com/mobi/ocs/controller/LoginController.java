package com.mobi.ocs.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.mobi.ocs.dto.CommonResponseData;
import com.mobi.ocs.entity.Callback;
import com.mobi.ocs.entity.Quotation;
import com.mobi.ocs.service.QuotationService;

@Controller
public class LoginController {

	@Autowired
	private UserDetailsManager userdetailsManager;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private QuotationService quotationService;
	protected static Logger logger = Logger.getLogger(LoginController.class);

	@GetMapping("/login")
	public String showMyLoginPage() {
		// return "plain-login";
		return "Login";
	}

	@GetMapping("/signup")
	public String signup(@RequestParam String userId, Model model) {
		
		String decodedUserId = quotationService.decodeBase64ToString(userId);
		
		model.addAttribute("userId", decodedUserId);
		return "SignUp";
	}
	
	@GetMapping("/internal/signup")
	public String internalSignup(Model model) {
		
		return "SignUp_NormalFlow";
	}

	@PostMapping(path = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object registerNewUser(@RequestBody com.mobi.ocs.entity.User user) {

		if (userdetailsManager.userExists(user.getUsername())) {
			logger.info(String.format("User Already Exists - %s", user.getUsername()));
			return new CommonResponseData("0001", "User already exsist", null);
		} else {
			String hashedPassword = passwordEncoder.encode(user.getPassword());
			Collection<? extends GrantedAuthority> roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_MERCHANT"));
			UserDetails userDetails = new User(user.getUsername(), hashedPassword, roles);
			userdetailsManager.createUser(userDetails);
			logger.info(String.format("User Created Successfully - %s", user.getUsername()));
			return new CommonResponseData("0000", "User created success", null);
		}
	}

	// add request mapping for /access-denied

	@GetMapping("/access-denied")
	public String showAccessDenied() {
		return "access-denied";
	}

	@RequestMapping("/Home")
	public String showHome() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String roleAsPerDB = quotationService
				.GetRoleNameAsPerDB(auth.getAuthorities().iterator().next().getAuthority());
		logger.info(String.format("Logged User -  %s ; Role - %s", auth.getName(), roleAsPerDB));

		if (roleAsPerDB.contains("synergy")) {
			return "redirect:/MyAction/list";
		} else if (roleAsPerDB.contains("synergy")) {
			return "redirect:/Quotation/list";
		} else if (roleAsPerDB.contains("merchant")) {
			return "redirect:/MerchantOrder";
		} else {
			return "redirect:/MyAction/list";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/showMyLoginPage";
	}
	
	
	

}
