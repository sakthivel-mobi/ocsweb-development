package com.mobi.ocs.utilities;

import java.security.SecureRandom;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public final class Constants {

	public final static class Experian {

		public final static String BASE_URL = "https://b2buat.experian.com.my/index.php/mobiversa";

		public static String getBaseUrl() {
			return BASE_URL;
		}

	}

	public static String KanbanApiKey = "lyobpiAP:UlfA3hIymdQ4lp1Ai2SPp";
	public static String KanbanApiKeyBase64 = "bHlvYnBpQVA6VWxmQTNoSXltZFE0bHAxQWkyU1Bw";

	public static String amountPattern = "#,##0.00";
	public static String blueMobiImagePNG = "D://BlueMobi.png";

	// PostMark
	public static String fromAddressPostMark = "info@mobiversa.com";
	public static String apiKeyPostMark = "4a2c6464-9e34-461f-bac4-361b04cdd712";

	// U-Mobile TPA Constants
	public static String umobileTPAVerificationURL = "https://ocs.gomobi.io/MMA?order=";
	public static String chainNo = "000000000000146";

	// Vignesh Selvam
	public final static String DOCUMENT_PATH = "C:/ocsweb/document";
	public final static String QUOTATION_PATH = "C:/ocsweb/quotation";
	public final static String ISSUE_QUOTATION_PATH = "C:/ocsweb/quotation/issue";
	public final static String RECEIPT_PATH = "C:/ocsweb/receipt";
	public final static String MMA_PATH = "C:/ocsweb/mma";

	// rk
	public final static String PROFOMA_PATH = "C:/ocsweb/profoma";

	public final static String DOCUMENT_RESOURCE_PATH = "/attachments/document";
	public final static String RECEIPT_RESOURCE_PATH = "/attachments/receipt";
	public final static String QUOTATION_RESOURCE_PATH = "/attachments/quotation";
	public final static String WELCOME_LETTER_RESOURCE_PATH = "/attachments/welcomeLetter";
	public final static String ISSUE_QUOTATION_RESOURCE_PATH = "/attachments/quotation/";
	public final static String ISSUE_PROFOMA_RESOURCE_PATH = "/attachments/profoma/";
	public final static String PROFOMA_RESOURCE_PATH = "/attachments/profoma";
	
	public static String getProfomaResourcePath() {
		return PROFOMA_RESOURCE_PATH;
	}

	public static String getIssueProfomaResourcePath() {
		return ISSUE_PROFOMA_RESOURCE_PATH;
	}

	public final static String MMA_RESOURCE_PATH = "/attachments/mma/";

	public static String getDocumentResourcePath() {
		return DOCUMENT_RESOURCE_PATH;
	}

	public static String getMmaResourcePath() {
		return MMA_RESOURCE_PATH;
	}

	public static String getMmaPath() {
		return MMA_PATH;
	}

	public static String getProfomaPath() {
		return PROFOMA_PATH;
	}

	public static String getReceiptPath() {
		return RECEIPT_PATH;
	}

	public static String getWelcomeLetterResourcePath() {
		return WELCOME_LETTER_RESOURCE_PATH;
	}

	public static String getReceiptResourcePath() {
		return RECEIPT_RESOURCE_PATH;
	}

	public static String getDocumentFilePath() {
		return DOCUMENT_PATH;
	}

	public static String getQuotationPath() {
		return QUOTATION_PATH;
	}

	public static String getIssueQuotationPath() {
		return ISSUE_QUOTATION_PATH;
	}

	public static String getQuotationResourcePath() {
		return QUOTATION_RESOURCE_PATH;
	}

	public static String getIssueQuotationResourcePath() {
		return ISSUE_QUOTATION_RESOURCE_PATH;
	}

	// FPX-Grab-Boost Ecomm Host Rate
	public static double fpxEcommHostAmt = 0.70;
	public static double grabEcommHostAmt = 1.00;
	public static double boostEcommHostAmt = 0.50;
	public static double tngEcommHostAmt = 1.15;
	public static double shopeepayEcommHostAmt = 1.15;

	// FPX-Grab-Boost QR Host Rate
	public static double fpxQRHostAmt = 0.70;
	public static double grabQRHostAmt = 0.65;
	public static double boostQRHostAmt = 0.50;
	public static double tngQRHostAmt = 1.15;
	public static double shopeepayQRHostAmt = 1.15;
	

	// Service Names
	// MDR SYNC
	public static String UPDATE_MDR_SYNC = "UPDATE_MDR_SYNC";
	public static String MIGRATION_MDR_SYNC = "MIGRATE_MDR_SYNC";
	public static String EZYWIRE_MDR_SYNC = "WIRE_MDR_SYNC";
	public static String SPLIT_MDR_SYNC_UPDATE = "SPLIT_MDR_SYNC_UPDATE";
	public static String MDR_SYNC = "MDR_SYNC";
	public static final String SPLIT_MDR_RATES_INSERT = "SPLIT_MDR_RATES_INSERT";
	public static final String SPLIT_MDR_SYNC = "SPLIT_MDR_SYNC";

	// Merchant Registration
	public static String REGULAR_MERCH_REG = "MERCHANT_REG";
	public static String MIGRATION_MERCH_REG = "OCS_MERCHANT_MIGRATE";

	public static final String WELCOMELETTER_PATH = "c:/ocsweb/welcomeLetter";

	public static String getWelcomeLetterPath() {
		return WELCOMELETTER_PATH;
	}

	public static String getMMAResourcePath() {
		return MMA_PATH;
	}

	public static String getFileType(String documentType) {

		switch (documentType) {
			case "application/pdf":
				return "pdf";

			case "image/jpg":
				return "jpg";

			case "image/png":
				return "png";

			case "image/jpeg":
				return "jpeg";
			default:
				return "";
		}

	}

	public static String getOTPMessageContent(String mobile, String randomNumber) {
		return String.format(
				"Your OTP is %s, Please contact Mobi customer support csmobi@gomobi.io if you haven't generated this OTP.",
				randomNumber);
	}

	public static String getMessageContent(String mobile) {
		return String.format(
				"OTP has been sent to the mobile number %s and to you registered email id. Please do not share OTP with any one.",
				mobile);
	}

	public static String GenerateNumber() {
		SecureRandom random = new SecureRandom();
		int num = random.nextInt(100000);
		String formatted = String.format("%06d", num);
		return formatted;
	}

}
