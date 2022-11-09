package com.mobi.ocs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExperianController {

	@RequestMapping("/Experian/Search")
	public String gotoExperianSearch() {
		return "ExperianSearch";
	}

}
