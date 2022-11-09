package com.mobi.ocs.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mobi.ocs.config.HibernateConfig;
import com.mobi.ocs.dao.SignatureDAO;
import com.mobi.ocs.dao.UserDAO;
import com.mobi.ocs.dto.CommonResponseData;
import com.mobi.ocs.modal.SignatureRequestModal;
import com.mobi.ocs.service.QuotationService;

@RestController
public class SignatureRestController {

	@Autowired
	private SignatureDAO signatureDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private ResourceLoader resourceLoader;
	
	protected static Logger logger = Logger.getLogger(SignatureRestController.class);

	private String signatureResourcePath = "/attachments/signature";

	@RequestMapping(path = "/signature/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object addSignature(@RequestBody SignatureRequestModal signatureRequestModal) {

		String filePath = "C:/ocsweb/signature";
		File file = new File(filePath);

		try {

			if (!file.exists()) {
				file.mkdirs();
			}

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			String extractedImageData = signatureRequestModal.getImageBaseString().split(",")[1];
			byte[] imageByteArray = decodeImage(extractedImageData);

			String fileStoreLocation = file.getAbsolutePath() + "/" + auth.getName() + ".png";

			File existingFile = new File(fileStoreLocation);
			if (existingFile.exists()) {
				existingFile.delete();
			}

			FileOutputStream imageOutFile = new FileOutputStream(fileStoreLocation);
			imageOutFile.write(imageByteArray);
			imageOutFile.close();

			signatureRequestModal.setUsername(auth.getName());

			int signatureID = (int) signatureDAO.saveUserSignature(signatureRequestModal,
					signatureResourcePath + "/" + auth.getName() + ".png");

			if (auth.getAuthorities().iterator().next().getAuthority().equals("ROLE_SALES")
					|| auth.getAuthorities().iterator().next().getAuthority().equals("ROLE_SALES-MANAGER")) {
				userDAO.setSignatureForSalesRole(signatureID, auth.getName());
			}

			return new CommonResponseData("0000", "Signature has been updated successfully", null);
		} catch (Exception e) {
			logger.error("Stacktrace : ",e);
			return new CommonResponseData("0001", "Unable to upload signature, please try again", null);
		}

	}

	public static byte[] decodeImage(String imageDataString) {
		return Base64.getDecoder().decode(imageDataString);
	}

	@RequestMapping(path = "/signature/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getSignatureByUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return signatureDAO.getUserSignature(auth.getName());
	}

	@RequestMapping(path = "/signature/{signatureId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getSignatireById(@PathVariable(name = "signatureId") String signatureId) {
		Object response = signatureDAO.getUserSignatureById(signatureId);
		return response;
	}

}
