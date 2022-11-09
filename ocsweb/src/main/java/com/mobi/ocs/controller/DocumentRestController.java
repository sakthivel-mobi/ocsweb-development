package com.mobi.ocs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mobi.ocs.dao.DocumentDAO;
import com.mobi.ocs.dto.DocumentRequestData;
import com.mobi.ocs.modal.DeleteDocumentRequestData;
import com.mobi.ocs.modal.DocumentUploadRequestData;

@RestController()
public class DocumentRestController {

	@Autowired
	DocumentDAO documentDAO;

	@RequestMapping(path = "document/category", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getDocumentType() {
		Object response = documentDAO.getDocumentType();
		return response;
	}

	@RequestMapping(path = "document/upload", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object uploadDocuments(@RequestBody DocumentUploadRequestData documentUploadRequestData) {
		System.out.println("uploadDocuments -> " + documentUploadRequestData.toString());
		Object response = documentDAO.insertDocument(documentUploadRequestData);
		return response;
	}

	@RequestMapping(path = "document/order/{orderId}")
	public Object getDocumentsByOrderId(@PathVariable(name = "orderId") int orderId) {
//		System.out.println("getDocumentsByOrderId -> OrderId: " + orderId);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object response = documentDAO.getDocumentByOrderId(orderId, auth.getName());
		return response;
	}

	@RequestMapping(path = "document/{documentID}/remove", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object removeDocumentByDocumentId(@PathVariable(name = "documentID") String documentId,
			@RequestBody DeleteDocumentRequestData deleteDocumentRequestData) {
		System.out.println("removeDocumentByDocumentId");
		Object response = documentDAO.deleteDocumentById(documentId, deleteDocumentRequestData);
		return response;
	}
}
