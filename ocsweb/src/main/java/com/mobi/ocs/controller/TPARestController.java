package com.mobi.ocs.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mobi.ocs.dto.CommonResponseData;
import com.mobi.ocs.entity.TPAFileToUmobile;
import com.mobi.ocs.modal.TPADocumentDownloadRequestData;
import com.mobi.ocs.service.TPAService;

@RestController
public class TPARestController {

	@Autowired
	private TPAService tpaService;

	@RequestMapping(value = "/document/tpa/download", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getTPADocuments(@RequestBody TPADocumentDownloadRequestData tpaDocumentDownloadRequestData) {
		
		ArrayList<TPAFileToUmobile> responseData = (ArrayList<TPAFileToUmobile>) tpaService
				.InsertTPADataInDB(tpaDocumentDownloadRequestData);
		
		if (responseData.size() > 0) {
			return new CommonResponseData("0000", "Success", responseData);
		} else {
			return new CommonResponseData("0001", "Unable to get tpa documents", null);
		}
	}
}
