package com.mobi.ocs.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.aop.config.AbstractInterceptorDrivenBeanDefinitionDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.mchange.v2.cfg.PropertiesConfigSource.Parse;
import com.mobi.ocs.controller.QuotationController;
import com.mobi.ocs.dao.MerchantDAO;
import com.mobi.ocs.dao.MerchantDAOImpl;
import com.mobi.ocs.dto.CommonResponseData;
import com.mobi.ocs.dto.MMAResponseDataModal;
import com.mobi.ocs.controller.MerchantRegistrationController;
import com.mobi.ocs.dto.SysParam;
import com.mobi.ocs.entity.CompanyType;
import com.mobi.ocs.entity.Director;
import com.mobi.ocs.entity.Order;
import com.mobi.ocs.entity.OrderLines;
import com.mobi.ocs.entity.Quotation;
import com.mobi.ocs.entity.Signature;
import com.mobi.ocs.modal.QuotationRequestData;
import com.mobi.ocs.utilities.Constants;
import com.mobi.ocs.utilities.RoundedBorder;
import com.sun.xml.bind.v2.runtime.reflect.opt.Const;

import javassist.expr.NewArray;

@Service
@Transactional
@PropertySource("classpath:resourcePaths.properties")
public class GeneratePDFServiceImpl implements GeneratePDFService {

	protected static Logger logger = Logger.getLogger(GeneratePDFServiceImpl.class);

	@Autowired
	private QuotationService service;

	// set up variable to hold the properties
	@Autowired
	private Environment env;

	// generate a PDF at the specified location

	@Override
	public Object GenerateQuotationMobi(Quotation quote, HttpServletRequest req)
			throws DocumentException, MalformedURLException, IOException {
		String pdfFilePath = Constants.getQuotationPath();
		pdfFilePath = String.format("%s/%s/", pdfFilePath, quote.getId());

		File fPDFFilePath = new File(pdfFilePath);

		try {

			if (!fPDFFilePath.exists()) {
				fPDFFilePath.mkdirs();
			}
			int storedFilePath = generateNewQuotation(pdfFilePath, quote, req);
			logger.info("GenerateQuotationMobi -> storedFilePath " + storedFilePath);
			if (storedFilePath == 0) {
				// File created Successfully
				String quotationFilePath = String.format("%s/%s/Quotation_%s.pdf", Constants.getQuotationResourcePath(),
						quote.getId(), quote.getId());
				String quotationName = String.format("Quotation_%s.pdf", quote.getId());
				insertImageInto(quotationName, quotationFilePath, quote.getId());
				Object latestGeneratedQuotation = getLatestGeneratedQuotation(quote.getId());
				return latestGeneratedQuotation;
			} else {
				// File not created error
				logger.error("Stored File Path == 0;");
				return new CommonResponseData("0001", "Something went wrong, please try again", null);
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Stacktrace : ", e);
			return new CommonResponseData("0001", "Something went wrong, please try again", null);
		}

		/*
		 * Vignesh Selvam Changes Code Updated
		 * 
		 * if (!fPDFFilePath.exists()) {
		 * logger.info("GenerateQuotationMobi -> Quotaion Not available"); // Vignesh
		 * selvam // Quotation not available need to create new one
		 * 
		 * int storedFilePath = generateNewQuotation(pdfFilePath, quote);
		 * logger.info("GenerateQuotationMobi -> storedFilePath " + storedFilePath); if
		 * (storedFilePath == 0) { // File created Successfully String quotationFilePath
		 * = String.format("%s/%s/Quotation_%s.pdf",
		 * Constants.getQuotationResourcePath(), quote.getId(), quote.getId()); String
		 * quotationName = String.format("Quotation_%s.pdf", quote.getId());
		 * insertImageInto(quotationFilePath, quotationName, quote.getId()); Object
		 * latestGeneratedQuotation = getLatestGeneratedQuotation(quote.getId()); return
		 * latestGeneratedQuotation; } else { // File not created error return new
		 * CommonResponseData("0001", "Something went wrong, please try again", null); }
		 * 
		 * } else { // Vignesh selvam // Quotation available
		 * logger.info("GenerateQuotationMobi -> quotation available"); Object
		 * latestGeneratedQuotation = getLatestGeneratedQuotation(quote.getId()); return
		 * latestGeneratedQuotation; }
		 */
	}

	// rk added

	@Override
	public Object GenerateProfoma(Quotation quote, HttpServletRequest req)
			throws DocumentException, MalformedURLException, IOException {
		String pdfFilePath = Constants.getProfomaPath();
		pdfFilePath = String.format("%s/%s/", pdfFilePath, quote.getId());

		File fPDFFilePath = new File(pdfFilePath);

		try {

			if (!fPDFFilePath.exists()) {
				fPDFFilePath.mkdirs();
			}
			int storedFilePath = generateNewProfoma(pdfFilePath, quote, req);
			logger.info("GenerateProfomaMobi -> storedFilePath " + storedFilePath);
			if (storedFilePath == 0) {
				// File created Successfully
				String quotationFilePath = String.format("/attachments/profoma/%s/Profoma_%s.pdf", quote.getId(),
						quote.getId());
				String quotationName = String.format("Profoma_%s.pdf", quote.getId());
				SaveProfomoPath(quotationName, quotationFilePath, quote.getId());
				Object latestGeneratedQuotation = getLatestGeneratedProfoma(quote.getId());
				return latestGeneratedQuotation;
			} else {
				// File not created error
				logger.error("Stored File Path == 0;");
				return new CommonResponseData("0001", "Something went wrong, please try again", null);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Stacktrace : ", e);
			return new CommonResponseData("0001", "Something went wrong, please try again", null);
		}

		/*
		 * Vignesh Selvam Changes Code Updated
		 * 
		 * if (!fPDFFilePath.exists()) {
		 * logger.info("GenerateQuotationMobi -> Quotaion Not available"); // Vignesh
		 * selvam // Quotation not available need to create new one
		 * 
		 * int storedFilePath = generateNewQuotation(pdfFilePath, quote);
		 * logger.info("GenerateQuotationMobi -> storedFilePath " + storedFilePath); if
		 * (storedFilePath == 0) { // File created Successfully String quotationFilePath
		 * = String.format("%s/%s/Quotation_%s.pdf",
		 * Constants.getQuotationResourcePath(), quote.getId(), quote.getId()); String
		 * quotationName = String.format("Quotation_%s.pdf", quote.getId());
		 * insertImageInto(quotationFilePath, quotationName, quote.getId()); Object
		 * latestGeneratedQuotation = getLatestGeneratedQuotation(quote.getId()); return
		 * latestGeneratedQuotation; } else { // File not created error return new
		 * CommonResponseData("0001", "Something went wrong, please try again", null); }
		 * 
		 * } else { // Vignesh selvam // Quotation available
		 * logger.info("GenerateQuotationMobi -> quotation available"); Object
		 * latestGeneratedQuotation = getLatestGeneratedQuotation(quote.getId()); return
		 * latestGeneratedQuotation; }
		 */
	}

	private void insertImageInto(String quotationFilePath, String quotationName, int quotationId) {
		Object response = service.AddQuotationUrl(quotationFilePath, quotationName, quotationId);
		logger.info("insertImageInto -> " + response);
	}

	// rk added
	private void SaveProfomoPath(String quotationFilePath, String quotationName, int quotationId) {
		Object response = service.AddProfomaUrl(quotationFilePath, quotationName, quotationId);
		logger.info("insertImageInto -> " + response);
	}

	@Override
	public Object getAvailableQuotationById(String quotationId) {
		Object latestGeneratedQuotation = getLatestGeneratedQuotation(Integer.parseInt(quotationId));
		return latestGeneratedQuotation;
	}

	private Object getLatestGeneratedQuotation(int quotationId) {
		return service.getQuotationPdf(quotationId);
	}

	// rk added

	private Object getLatestGeneratedProfoma(int quotationId) {
		return service.getProfomaPdf(quotationId);
	}

	private int generateNewQuotation(String pdfFilePath, Quotation quote, HttpServletRequest req) {
		// TODO Auto-generated method stub

		File fPDFFilePath = new File(pdfFilePath);
		try {

			if (!fPDFFilePath.exists()) {
				fPDFFilePath.mkdirs();
			}

			String fileStorePath = String.format("%s/Quotation_%s.pdf", fPDFFilePath, quote.getId());

			Document pdfDoc = new Document();

			pdfDoc.setPageSize(PageSize.A4); // SET MARGINS

			Font font5 = new Font(FontFamily.HELVETICA, 8);
			Font fontWhite = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(255, 255, 255));
			Font boldFontWhite = new Font(FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(255, 255, 255));
			Font fontmoh = new Font(FontFamily.HELVETICA, 6);
			Font fontmoh4 = new Font(FontFamily.HELVETICA, 6);
			Font normalFont = new Font(FontFamily.HELVETICA, 8);
			Font boldFont = new Font(FontFamily.HELVETICA, 10, Font.BOLD);
			Font fontHead = new Font(FontFamily.HELVETICA, 14);
			Font font7 = new Font(FontFamily.HELVETICA, 9);
			Font linkTextFont = new Font(FontFamily.HELVETICA, 8, Font.ITALIC, new BaseColor(51, 51, 255));

			BaseFont f_cn = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			BaseFont Merchfont = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, false);
			Font fontTinyItalic = new Font(FontFamily.HELVETICA, 30, Font.BOLDITALIC, BaseColor.BLACK);

			OutputStream fos = new FileOutputStream(fileStorePath);
			PdfWriter writer = PdfWriter.getInstance(pdfDoc, fos);

			// ByteArrayOutputStream baos = new ByteArrayOutputStream();
			// PdfWriter writer = PdfWriter.getInstance(pdfDoc, baos);

			pdfDoc.open();

			// Mobi Cover Letter

			if (pdfDoc.getPageNumber() == 0) {
				PdfContentByte content = writer.getDirectContent();
				Rectangle rectangle = new Rectangle(pdfDoc.getPageSize());
				rectangle.setLeft(rectangle.getLeft() + pdfDoc.leftMargin());
				rectangle.setRight(rectangle.getRight() - pdfDoc.rightMargin());
				rectangle.setBottom(rectangle.getBottom() + pdfDoc.bottomMargin());
				rectangle.setTop(rectangle.getTop() - pdfDoc.topMargin());

				rectangle.setBorderWidth(50);
				content.setColorStroke(new BaseColor(0, 91, 170));
				content.setLineWidth(5.0f);
				content.rectangle(25f, 25f, 545f, 795f);
				content.stroke();
			}

			PdfContentByte cbimg = writer.getDirectContent();

			// URL mobiPath = classLoaderBoost.getResource("assets/images/mobi.png");
			URL mobiPath = getClass().getClassLoader().getResource("/assets/images/mobi.png");
			Image image5 = Image.getInstance(mobiPath);
			image5.scaleAbsolute(260f, 120f);
			// image5.scaleAbsolute(400f, 190f);
			image5.setAlignment(Element.ALIGN_CENTER);
			image5.setAbsolutePosition(160, 645);
			cbimg.addImage(image5);

			PdfContentByte cbMerchant = writer.getDirectContent();
			PdfPTable tableMerchant = new PdfPTable(1);

			Paragraph importantNotice = new Paragraph(quote.getCompanyName(), fontTinyItalic);
			PdfPCell cellMerch = new PdfPCell(importantNotice);
			cellMerch.setBorderWidth(PdfPCell.NO_BORDER);
			cellMerch.setHorizontalAlignment(1);
			tableMerchant.addCell(cellMerch);
			tableMerchant.setWidthPercentage(50);
			tableMerchant.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			tableMerchant.setTotalWidth(400f);
			tableMerchant.writeSelectedRows(0, -1, 90, 485, cbMerchant);
			// tableMerchant.writeSelectedRows(0, -1, 90, 277, cbMerchant);

			PdfContentByte cbQuotationNo = writer.getDirectContent();
			cbQuotationNo.beginText();
			cbQuotationNo.setFontAndSize(f_cn, 20);
			cbQuotationNo.showTextAligned(PdfContentByte.ALIGN_CENTER, "Quotation - " + String.valueOf(quote.getId()),
					295, 185, 0);
			cbQuotationNo.endText();

			PdfContentByte cbDate = writer.getDirectContent();
			cbDate.beginText();
			cbDate.setFontAndSize(f_cn, 20);
			cbDate.showTextAligned(PdfContentByte.ALIGN_CENTER, String.valueOf(quote.getCreatedOn()).split("T")[0], 295,
					160, 0);
			cbDate.endText();

			// Mobi Logo & Address
			pdfDoc.newPage();
			PdfPTable pdfsign = new PdfPTable(2); // two colmns create tabble
			float[] widthsign = new float[] { 9f, 30f };
			pdfsign.setWidthPercentage(100f);// table %100 width
			pdfsign.setWidths(widthsign);

			PdfPCell cellxysign = new PdfPCell();

			Image image = Image.getInstance(mobiPath);
			image.scaleAbsolute(55f, 18f);
			image.setAlignment(Element.ALIGN_LEFT);
			cellxysign.addElement(new Paragraph("\n"));
			cellxysign.addElement(image);
			cellxysign.disableBorderSide(15);
			pdfsign.addCell(cellxysign);

			pdfsign.setWidthPercentage(75f);

			PdfPCell cellxxsign = new PdfPCell();
			Phrase phraseCompany = new Phrase();
			phraseCompany.add(new Chunk("", normalFont));
//			phraseCompany.add(new Chunk("Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) (Reg.# 1105429-U)",
//					boldFont));
			
			phraseCompany.add(new Chunk("Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) (Reg.# 201401029343) (1105429-U)",
                    boldFont));
			
//			phraseCompany.add(Chunk.NEWLINE);
//			phraseCompany.add(new Chunk("Suite #07-01, Wisma UOA Damansara II, No. 6, Changkat Semantan,", normalFont));
//			phraseCompany.add(Chunk.NEWLINE);
//			phraseCompany.add(new Chunk("Damansara Heights,", normalFont));
//			phraseCompany.add(Chunk.NEWLINE);
//			phraseCompany.add(new Chunk("Kuala Lumpur 50490, Malaysia", normalFont));
//			phraseCompany.add(Chunk.NEWLINE);
//			phraseCompany.add(new Chunk("Email : info@mobiversa.com", normalFont));
//			phraseCompany.add(Chunk.NEWLINE);
			
			 phraseCompany.add(Chunk.NEWLINE);
	            phraseCompany.add(new Chunk("17-109, Equatorial Plaza,", normalFont));
	            phraseCompany.add(Chunk.NEWLINE);
	            phraseCompany.add(new Chunk("Jalan Sultan Ismail,", normalFont));
	            phraseCompany.add(Chunk.NEWLINE);
	            phraseCompany.add(new Chunk("Kuala Lumpur 50250 , Malaysia", normalFont));
	            phraseCompany.add(Chunk.NEWLINE);
	            /* phraseCompany.add(new Chunk("Email : info@mobiversa.com", normalFont)); */
	            phraseCompany.add(new Chunk("Email : info@gomobi.io", normalFont));
	            phraseCompany.add(Chunk.NEWLINE);

			cellxxsign.disableBorderSide(15);
			cellxxsign.addElement(phraseCompany);
			pdfsign.addCell(cellxxsign);
			pdfsign.setHorizontalAlignment(Element.ALIGN_LEFT);
			pdfDoc.add(pdfsign);

			// MERCHANT ADDRESS & QUOTE INFO

			PdfPTable pdfsign1 = new PdfPTable(2); // two colmns create tabble
			float[] widthsign1 = new float[] { 50f, 20f };
			pdfsign1.setWidthPercentage(100f); // table %100 width
			pdfsign1.setWidths(widthsign1);
			PdfPCell cellxxsign1 = new PdfPCell();
			String address = "";

			if (quote.getCompanyName() != null) {
				address = "" + quote.getContact().getFirstName() + "\n" + quote.getAddress() + "\n" + quote.getCity()
						+ ", " + quote.getState() + ", " + "\n" + "Malaysia";

			}
			Phrase phraseAddress = new Phrase();
			phraseAddress.add(new Chunk("INVOICE TO", boldFont));
			phraseAddress.add(Chunk.NEWLINE);
			phraseAddress.add(new Chunk(address, normalFont));
			cellxxsign1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellxxsign1.disableBorderSide(15);
			cellxxsign1.addElement(phraseAddress);
			PdfPCell cellxxsign2 = new PdfPCell();
			Phrase phraseQuoteInfo = new Phrase();
			phraseQuoteInfo.add(new Chunk("Quotation Number: ", boldFont));
			phraseQuoteInfo.add(new Chunk(String.valueOf(quote.getId()), normalFont));
			phraseQuoteInfo.add(Chunk.NEWLINE);
			phraseQuoteInfo.add(new Chunk("Date: ", boldFont));

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String quotationDate = quote.getCreatedOn().format(formatter);

			phraseQuoteInfo.add(new Chunk(quotationDate, normalFont));
			phraseQuoteInfo.add(Chunk.NEWLINE);
			phraseQuoteInfo.add(new Chunk("Valid Upto: ", boldFont));
			phraseQuoteInfo.add(new Chunk(quote.getExpiryDate() == null ? "" : quote.getExpiryDate(), normalFont));
			phraseQuoteInfo.add(Chunk.NEWLINE);

			cellxxsign2.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellxxsign2.disableBorderSide(15);
			cellxxsign2.addElement(phraseQuoteInfo);
			pdfsign1.addCell(cellxxsign1);
			pdfsign1.addCell(cellxxsign2);
			pdfsign1.setWidthPercentage(100f);
			pdfDoc.add(pdfsign1);

			String patternString = Constants.amountPattern;
			logger.info(("amountPattern = : " + patternString));

			// ADD ORDER LINES

			LineSeparator lineSeparator = new LineSeparator();
			lineSeparator.setLineColor(new BaseColor(0, 91, 170));
			lineSeparator.setLineWidth(2.0F);

			List<OrderLines> odList = quote.getOrderLines();
			PdfPTable table = new PdfPTable(6);
			float[] widths = new float[] { 2f, 5.5f, 1.0f, 1.0f, 2f, 0.5f };
			table.setWidths(widths);
			table.setWidthPercentage(100);
			table.getDefaultCell().disableBorderSide(15);
			table.getDefaultCell().enableBorderSide(2);
			table.getDefaultCell().setBorderColor(new BaseColor(200, 224, 230));
			Paragraph p4 = new Paragraph();
			p4.add(lineSeparator);
			pdfDoc.add(p4);
			pdfDoc.add(Chunk.NEWLINE);
			Paragraph p5new = new Paragraph(new Phrase("\n"));
			pdfDoc.add(p5new);
			table.getDefaultCell().setPaddingBottom(7f);
			table.getDefaultCell().setBackgroundColor(new BaseColor(0, 91, 170));
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			String spacing = "          ";
			table.addCell(new Phrase(spacing + "Product", fontWhite));
			table.addCell(new Phrase(spacing + "Description", fontWhite));
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(new Phrase("Quantity", fontWhite));
			table.addCell(new Phrase("Unit Price", fontWhite));
			table.addCell(new Phrase(spacing + "Total", fontWhite));
			table.addCell(new Phrase(" ", fontWhite));
			table.getDefaultCell().setBackgroundColor(new BaseColor(255, 255, 255));
			double orderTotal = 0.00;
			int noofrow = odList.size();
			for (OrderLines od : odList) {
				if (odList.size() > 0) {
					/*
					 * if (od.showInQuote == 1) {
					 */
					table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(new Phrase(spacing + od.getProduct().getProductType(), font5));
					table.addCell(new Phrase(spacing + od.getProduct().getProductName(), font5));
					table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(new Phrase("    " + String.valueOf(od.getQuantity()), font5));
					table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);

					table.addCell(
							new Phrase(service.FormatAmountFromDoubleToString(od.getProduct().getUnitPrice()), font5));
					table.addCell(new Phrase(
							spacing + service
									.FormatAmountFromDoubleToString(od.getProduct().getUnitPrice() * od.getQuantity()),
							font5));
					table.addCell(new Phrase(" ", font5));
					orderTotal += (od.getProduct().getUnitPrice() * od.getQuantity());
					// }
				}
			}
			pdfDoc.add(table);

			double orderTotal1 = orderTotal - quote.getDiscountPrice();
			Paragraph p8 = new Paragraph();
			p8.add(lineSeparator);
			pdfDoc.add(p8);
			PdfPTable discounttab = new PdfPTable(4);
			discounttab.getDefaultCell().setPaddingBottom(3f);
			discounttab.getDefaultCell().disableBorderSide(Rectangle.NO_BORDER);
			float[] widthsdiscount = new float[] { 8f, 1.5f, 1.5f, 0.5f };
			discounttab.setWidths(widthsdiscount);
			discounttab.setWidthPercentage(100f);

			ArrayList<SysParam> spi = new ArrayList<SysParam>();
			SysParam sp = new SysParam();
			sp.setParameter("SUB TOTAL");
			sp.setValue("RM " + service.FormatAmountFromDoubleToString(orderTotal));
			spi.add(sp);

			sp = new SysParam();
			sp.setParameter("DISCOUNT");
			sp.setValue("RM " + String.format("%.2f", quote.getDiscountPrice()));
			spi.add(sp);

			sp = new SysParam();
			sp.setParameter("TAX TOTAL");
			sp.setValue("RM 0.00");
			spi.add(sp);

			// Discount Region

			// Total Due
			sp = new SysParam();
			sp.setParameter("TOTAL DUE");
			sp.setValue("RM " + service.FormatAmountFromDoubleToString(orderTotal1));
			service.UpdateTotalInQuotation(quote.getId(), orderTotal1);
			spi.add(sp);

			int i = 0;
			discounttab.getDefaultCell().setBackgroundColor(new BaseColor(220, 234, 240));
			discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
			for (SysParam spo : spi) {
				i++;
				Rectangle r;
				PdfPCell cellw = new PdfPCell();
				PdfPCell cellx = new PdfPCell();
				PdfPCell celly = new PdfPCell();
				PdfPCell cellz = new PdfPCell();
				Paragraph pw = new Paragraph("  ");
				Paragraph px = new Paragraph(spo.getParameter() + ":");
				Paragraph p1x = new Paragraph(spo.getValue());
				if (spo.getParameter() == "TOTAL DUE") {
					cellx.setBackgroundColor(new BaseColor(0, 91, 170));
					celly.setBackgroundColor(new BaseColor(0, 91, 170));
					cellw.setBackgroundColor(new BaseColor(0, 91, 170));
					cellz.setBackgroundColor(new BaseColor(0, 91, 170));
					px.setFont(boldFontWhite);
					p1x.setFont(boldFontWhite);
				} else {
					cellx.setBackgroundColor(new BaseColor(255, 255, 255));
					celly.setBackgroundColor(new BaseColor(255, 255, 255));
					cellw.setBackgroundColor(new BaseColor(255, 255, 255));
					cellz.setBackgroundColor(new BaseColor(255, 255, 255));
					px.setFont(boldFont);
					p1x.setFont(font5);
				}
				cellw.setBorder(Rectangle.NO_BORDER);
				cellw.addElement(pw);
				discounttab.addCell(cellw);
				cellx.setBorder(Rectangle.NO_BORDER);
				cellx.addElement(px);
				cellx.setBorder(Rectangle.NO_BORDER);
				discounttab.addCell(cellx);
				p1x.setAlignment(Element.ALIGN_RIGHT);
				celly.addElement(p1x);
				celly.setBorder(Rectangle.NO_BORDER);
				discounttab.addCell(celly);
				cellz.setBorder(Rectangle.NO_BORDER);
				cellz.addElement(pw);
				discounttab.addCell(cellz);
			}
			pdfDoc.add(discounttab);

			Paragraph p5 = new Paragraph("\n");
			pdfDoc.add(p5);
			Paragraph tnc = new Paragraph();
			tnc.setLeading(0, 2);
			String tncdynamic = "";

			if (quote.getOrderType() != "Topup") {
				int SplitCount = 0;
				for (OrderLines od : odList) {
					if (od.getProduct().getProductType().equals("EZYSPLIT")) {
						SplitCount++;
					}
				}
				// MDR TABLE FOR OTHER PRODUCTS

				for (OrderLines od : odList) {
					if (od.getQuotationMDRRate() != null) {

						Paragraph pmdr = new Paragraph();
						pmdr.setFont(boldFont);
						pmdr.add(od.getProduct().getProductName());
						pdfDoc.add(pmdr);
						pdfDoc.add(p5);
						PdfPTable mdrTable = new PdfPTable(6);
						float[] mdrWidths = new float[] { 2f, 2f, 2f, 2f, 2f, 2f };
						mdrTable.setWidths(mdrWidths);
						mdrTable.setWidthPercentage(100);
						mdrTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
						mdrTable.getDefaultCell().disableBorderSide(15);
						mdrTable.getDefaultCell().enableBorderSide(2);
						mdrTable.getDefaultCell().setBorderColor(new BaseColor(200, 224, 230));
						mdrTable.getDefaultCell().setPadding(3f);
						mdrTable.getDefaultCell().setBackgroundColor(new BaseColor(0, 91, 170));
						// mdrTable.addCell(new Phrase("Product Type", fontWhite));

						mdrTable.addCell(new Phrase("Card Brand", fontWhite));
						mdrTable.addCell(new Phrase("Local Credit", fontWhite));
						mdrTable.addCell(new Phrase("Local Debit", fontWhite));
						mdrTable.addCell(new Phrase("Foreign Credit", fontWhite));
						mdrTable.addCell(new Phrase("Foreign Debit", fontWhite));
						mdrTable.addCell(new Phrase("Settlement", fontWhite));
						mdrTable.getDefaultCell().setBackgroundColor(new BaseColor(255, 255, 255));

						discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);
						if (odList.size() > 0) {
							for (int iloop = 0; iloop < 3; iloop++) {
								if (iloop == 0) {
									discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);
									// mdrTable.addCell(new Phrase(od.getQuotationMDRRate().getProductName(),
									// fontmoh4));
									// mdrTable.addCell(new Phrase(od.getQuotationMDRRate().getProductType(),
									// fontmoh4));
									mdrTable.addCell(new Phrase("MasterCard", fontmoh4));
									discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
									mdrTable.addCell(new Phrase(
											String.valueOf(od.getQuotationMDRRate().getLoc_Credit_Merch_Master()) + "%",
											fontmoh4));
									mdrTable.addCell(new Phrase(
											String.valueOf(od.getQuotationMDRRate().getLoc_Debit_Merch_Master()) + "%",
											fontmoh4));
									mdrTable.addCell(new Phrase(
											String.valueOf(od.getQuotationMDRRate().getFor_Credit_Merch_Master()) + "%",
											fontmoh4));
									mdrTable.addCell(new Phrase(
											String.valueOf(od.getQuotationMDRRate().getFor_Debit_Merch_Master()) + "%",
											fontmoh4));
									if (od.getQuotationMDRRate().getProductSettlement() != 0) {
										mdrTable.addCell(new Phrase(
												"T + " + String
														.valueOf(od.getQuotationMDRRate().getProductSettlement()),
												fontmoh4));
									} else {
										mdrTable.addCell(new Phrase("", fontmoh4));
									}
								} else if (iloop == 1) {
									discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);
									// mdrTable.addCell(new Phrase(od.getQuotationMDRRate().getProductName(),
									// fontmoh4));
									// mdrTable.addCell(new Phrase(od.getQuotationMDRRate().getProductType(),
									// fontmoh4));
									mdrTable.addCell(new Phrase("Visa", fontmoh4));
									discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
									mdrTable.addCell(new Phrase(
											String.valueOf(od.getQuotationMDRRate().getLoc_Credit_Merch_Visa()) + "%",
											fontmoh4));
									mdrTable.addCell(new Phrase(
											String.valueOf(od.getQuotationMDRRate().getLoc_Debit_Merch_Visa()) + "%",
											fontmoh4));
									mdrTable.addCell(new Phrase(
											String.valueOf(od.getQuotationMDRRate().getFor_Credit_Merch_Visa()) + "%",
											fontmoh4));
									mdrTable.addCell(new Phrase(
											String.valueOf(od.getQuotationMDRRate().getFor_Debit_Merch_Visa()) + "%",
											fontmoh4));
									if (od.getQuotationMDRRate().getProductSettlement() != 0) {
										mdrTable.addCell(new Phrase(
												"T + " + String
														.valueOf(od.getQuotationMDRRate().getProductSettlement()),
												fontmoh4));
									} else {
										mdrTable.addCell(new Phrase("", fontmoh4));
									}
								} else if (iloop == 2) {
									discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);
									// mdrTable.addCell(new Phrase(od.getQuotationMDRRate().getProductName(),
									// fontmoh4));
									// mdrTable.addCell(new Phrase(od.getQuotationMDRRate().getProductType(),
									// fontmoh4));
									mdrTable.addCell(new Phrase("UnionPay", fontmoh4));
									discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
									mdrTable.addCell(new Phrase(
											String.valueOf(od.getQuotationMDRRate().getLoc_Credit_Merch_Union()) + "%",
											fontmoh4));
									mdrTable.addCell(new Phrase(
											String.valueOf(od.getQuotationMDRRate().getLoc_Debit_Merch_Union()) + "%",
											fontmoh4));
									mdrTable.addCell(new Phrase(
											String.valueOf(od.getQuotationMDRRate().getFor_Credit_Merch_Union()) + "%",
											fontmoh4));
									mdrTable.addCell(new Phrase(
											String.valueOf(od.getQuotationMDRRate().getFor_Debit_Merch_Union()) + "%",
											fontmoh4));
									if (od.getQuotationMDRRate().getProductSettlement() != 0) {
										mdrTable.addCell(new Phrase(
												"T + " + String
														.valueOf(od.getQuotationMDRRate().getProductSettlement()),
												fontmoh4));
									} else {
										mdrTable.addCell(new Phrase("", fontmoh4));
									}
								}
							}

						}

						pdfDoc.add(mdrTable);
					}

				}

				Paragraph pminimumtxn = new Paragraph();
				pminimumtxn.setFont(font5);
				pminimumtxn.add("* Minimum transaction permitted on this terminal is MYR 5.00");
				pdfDoc.add(pminimumtxn);
				// endregion

				if (SplitCount > 0) {
					// MDR TABLE FOR EZYSPLIT

					Paragraph SPLITpmdr = new Paragraph();
					SPLITpmdr.setFont(boldFont);
					SPLITpmdr.add("EZYSPLIT MDR Table");
					pdfDoc.add(SPLITpmdr);
					pdfDoc.add(p5);
					PdfPTable SPLITmdrTable = new PdfPTable(4);
					float[] SPLITmdrWidths = new float[] { 2f, 3f, 3f, 2f };
					SPLITmdrTable.setWidths(SPLITmdrWidths);
					SPLITmdrTable.setWidthPercentage(100f);
					SPLITmdrTable.setHorizontalAlignment(Element.ALIGN_CENTER);
					SPLITmdrTable.getDefaultCell().disableBorderSide(15);
					SPLITmdrTable.getDefaultCell().enableBorderSide(2);

					SPLITmdrTable.getDefaultCell().setBorderColor(new BaseColor(200, 224, 230));
					SPLITmdrTable.getDefaultCell().setPaddingBottom(3f);
					SPLITmdrTable.getDefaultCell().setBackgroundColor(new BaseColor(0, 91, 170));
					// SPLITmdrTable.addCell(new Phrase("Product Type", fontWhite));
					SPLITmdrTable.addCell(new Phrase("No of Payments", fontWhite));
					SPLITmdrTable.addCell(new Phrase("Merchant MDR", fontWhite));
					SPLITmdrTable.addCell(new Phrase("Customer processing Fee", fontWhite));
					SPLITmdrTable.addCell(new Phrase("Settlement", fontWhite));
					SPLITmdrTable.getDefaultCell().setBackgroundColor(new BaseColor(255, 255, 255));

					for (OrderLines od : odList) {
						if (od.getProduct().getShowInQuote().equals("Yes")
								&& od.getQuotationEzysplitMDRRate() != null) {
							discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);

							if (odList.size() > 0) {
								for (int iloop = 0; iloop < 4; iloop++) {
									if (iloop == 0) {
										discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);

										// SPLITmdrTable.addCell(new Phrase(od.getProduct().getProductType(),
										// fontmoh4));
										SPLITmdrTable.addCell(new Phrase("Installment 3", fontmoh4));
										discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
										SPLITmdrTable
												.addCell(
														new Phrase(
																od.getQuotationEzysplitMDRRate()
																		.getLoc_Credit_Merch_Master_Insta3() + "%",
																fontmoh4));
										SPLITmdrTable
												.addCell(
														new Phrase(
																od.getQuotationEzysplitMDRRate()
																		.getLoc_Credit_Cus_Master_Insta3() + "%",
																fontmoh4));
										if (od.getQuotationEzysplitMDRRate().getProductSettlement() != 0) {
											SPLITmdrTable.addCell(new Phrase(
													"T + " + String.valueOf(
															od.getQuotationEzysplitMDRRate().getProductSettlement()),
													fontmoh4));
										} else {
											SPLITmdrTable.addCell(new Phrase("", fontmoh4));
										}
									} else if (iloop == 1) {
										discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);

										// SPLITmdrTable.addCell(new Phrase(od.getProduct().getProductType(),
										// fontmoh4));
										SPLITmdrTable.addCell(new Phrase("Installment 6", fontmoh4));
										discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
										SPLITmdrTable
												.addCell(
														new Phrase(
																od.getQuotationEzysplitMDRRate()
																		.getLoc_Credit_Merch_Master_Insta6() + "%",
																fontmoh4));
										SPLITmdrTable
												.addCell(
														new Phrase(
																od.getQuotationEzysplitMDRRate()
																		.getLoc_Credit_Cus_Master_Insta6() + "%",
																fontmoh4));
										if (od.getQuotationEzysplitMDRRate().getProductSettlement() != 0) {
											SPLITmdrTable.addCell(new Phrase(
													"T + " + String.valueOf(
															od.getQuotationEzysplitMDRRate().getProductSettlement()),
													fontmoh4));
										} else {
											SPLITmdrTable.addCell(new Phrase("", fontmoh4));
										}
									} else if (iloop == 2) {
										discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);

										// SPLITmdrTable.addCell(new Phrase(od.getProduct().getProductType(),
										// fontmoh4));
										SPLITmdrTable.addCell(new Phrase("Installment 9", fontmoh4));
										discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
										SPLITmdrTable
												.addCell(
														new Phrase(
																od.getQuotationEzysplitMDRRate()
																		.getLoc_Credit_Merch_Master_Insta9() + "%",
																fontmoh4));
										SPLITmdrTable
												.addCell(
														new Phrase(
																od.getQuotationEzysplitMDRRate()
																		.getLoc_Credit_Cus_Master_Insta9() + "%",
																fontmoh4));
										if (od.getQuotationEzysplitMDRRate().getProductSettlement() != 0) {
											SPLITmdrTable.addCell(new Phrase(
													"T + " + String.valueOf(
															od.getQuotationEzysplitMDRRate().getProductSettlement()),
													fontmoh4));
										} else {
											SPLITmdrTable.addCell(new Phrase("", fontmoh4));
										}
									} else if (iloop == 3) {
										discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);

										// SPLITmdrTable.addCell(new Phrase(od.getProduct().getProductType(),
										// fontmoh4));
										SPLITmdrTable.addCell(new Phrase("Installment 12", fontmoh4));
										discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
										SPLITmdrTable
												.addCell(
														new Phrase(
																od.getQuotationEzysplitMDRRate()
																		.getLoc_Credit_Merch_Master_Insta12() + "%",
																fontmoh4));
										SPLITmdrTable
												.addCell(
														new Phrase(
																od.getQuotationEzysplitMDRRate()
																		.getLoc_Credit_Cus_Master_Insta12() + "%",
																fontmoh4));
										if (od.getQuotationEzysplitMDRRate().getProductSettlement() != 0) {
											SPLITmdrTable.addCell(new Phrase(
													"T + " + String.valueOf(
															od.getQuotationEzysplitMDRRate().getProductSettlement()),
													fontmoh4));
										} else {
											SPLITmdrTable.addCell(new Phrase("", fontmoh4));
										}
									}

								}
							}
						}
					}
					pdfDoc.add(SPLITmdrTable);
					Paragraph card = new Paragraph();
					card.setFont(font5);
					card.add("* MDR for EZYSPLIT is only for MasterCard");
					pdfDoc.add(card);

					// endregion
				}

			}
			
			
			

//			// WALLET MDR TABLE
//
//			Paragraph pwmdr = new Paragraph();
//			PdfPTable wmdrTable = new PdfPTable(7);
//			float[] wmdrWidths = new float[] { 0.5f, 1f, 1f, 1f, 1f, 1f, 1f };
//
//			// if (od.getQuotationMDRRate().getIncludeWallet().equals("Yes"))
//			// {
//			discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);
//			if (odList.size() > 0) {
//				pwmdr.setFont(boldFont);
//				pwmdr.add("Wallets");
//				pdfDoc.add(pwmdr);
//				pdfDoc.add(p5);
//				wmdrTable.setWidths(wmdrWidths);
//				wmdrTable.setWidthPercentage(100f);
//				wmdrTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//				wmdrTable.getDefaultCell().disableBorderSide(15);
//				wmdrTable.getDefaultCell().enableBorderSide(2);
//				wmdrTable.getDefaultCell().setBorderColor(new BaseColor(200, 224, 230));
//				wmdrTable.getDefaultCell().setPaddingBottom(3f);
//				wmdrTable.getDefaultCell().setBackgroundColor(new BaseColor(0, 91, 170));
//				wmdrTable.addCell(new Phrase("Product", fontWhite));
//				wmdrTable.addCell(new Phrase("Boost EComm", fontWhite));
//				wmdrTable.addCell(new Phrase("Boost QR", fontWhite));
//				wmdrTable.addCell(new Phrase("Grab EComm", fontWhite));
//				wmdrTable.addCell(new Phrase("Grab QR", fontWhite));
//				
//				wmdrTable.addCell(new Phrase("FPX(RM)", fontWhite));
//				wmdrTable.addCell(new Phrase("FPX(%)", fontWhite));
//				wmdrTable.getDefaultCell().setBackgroundColor(new BaseColor(255, 255, 255));
//				discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);
//
//				image.scaleAbsolute(55f, 18f);
//				image.setAlignment(Element.ALIGN_LEFT);
//				cellxysign.addElement(new Paragraph("\n"));
//				cellxysign.addElement(image);
//				cellxysign.disableBorderSide(15);
//				pdfsign.addCell(cellxysign);
//
//				for (OrderLines od : odList) {
//					if (od.getQuotationEzysplitMDRRate() != null) {
//
//						/*
//						 * URL grabPayPath =
//						 * getClass().getClassLoader().getResource("/assets/images/grabpay.png"); Image
//						 * gpimage = Image.getInstance(grabPayPath); gpimage.scaleAbsolute(11f, 4f);
//						 * gpimage.setAlignment(Element.ALIGN_LEFT); wmdrTable.addCell(gpimage);
//						 */
//
//						discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
//						wmdrTable.addCell(new Phrase(od.getQuotationEzysplitMDRRate().getProductType(), fontmoh4));
//						
//						if(od.getProduct().getProductType().equalsIgnoreCase("EZYWIRE"))
//								{
//							wmdrTable.addCell(new Phrase("NILL", fontmoh4));
//									
//									}
//						
//						else {
//						wmdrTable.addCell(new Phrase(
//								String.valueOf(od.getQuotationEzysplitMDRRate().getBoostMDREcomm()) + "%", fontmoh4));
//						}
//						
//						wmdrTable.addCell(new Phrase(
//								String.valueOf(od.getQuotationEzysplitMDRRate().getBoostMDRQR()) + "%", fontmoh4));
//						
//						if(od.getProduct().getProductType().equalsIgnoreCase("EZYWIRE"))
//						{
//							wmdrTable.addCell(new Phrase("NILL", fontmoh4));
//						
//						}
//						
//						else {
//							wmdrTable.addCell(new Phrase(
//									String.valueOf(od.getQuotationEzysplitMDRRate().getGrabMDREcomm()) + "%", fontmoh4));
//							
//						}
//						
//						
//						
//						
//						wmdrTable.addCell(new Phrase(
//								String.valueOf(od.getQuotationEzysplitMDRRate().getGrabMDRQR()) + "%", fontmoh4));
//						
//						if(od.getProduct().getProductType().equalsIgnoreCase("EZYWIRE"))
//						{
//							wmdrTable.addCell(new Phrase("NILL", fontmoh4));
//							wmdrTable.addCell(new Phrase("NILL", fontmoh4));
//							
//						}
//						else
//						{
//						wmdrTable.addCell(
//								new Phrase(String.valueOf(od.getQuotationEzysplitMDRRate().getfPXMDR_RM()), fontmoh4));
//						wmdrTable.addCell(new Phrase(
//								String.valueOf(od.getQuotationEzysplitMDRRate().getfPXMDR_Percent()) + "%", fontmoh4));
//						
//						}
//						/*
//						 * if (od.getQuotationEzysplitMDRRate().getGrabSettlement() !=
//						 * Integer.parseInt("0")) { wmdrTable.addCell(new Phrase("T + " +
//						 * String.valueOf(od.getQuotationEzysplitMDRRate().getGrabSettlement()),
//						 * fontmoh4)); } else { wmdrTable.addCell(new Phrase("", fontmoh4)); }
//						 */
//
//						discounttab.setHorizontalAlignment(Element.ALIGN_CENTER);
//
//						/*
//						 * URL boostPath =
//						 * getClass().getClassLoader().getResource("/assets/images/boost.png"); Image
//						 * bimage = Image.getInstance(boostPath); bimage.scaleAbsolute(11f, 4f);
//						 * bimage.setAlignment(Element.ALIGN_CENTER); wmdrTable.addCell(bimage);
//						 * discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT); wmdrTable.addCell(
//						 * new Phrase(String.valueOf(od.getQuotationEzysplitMDRRate().getBoostMDR()) +
//						 * "%", fontmoh4)); if (od.getQuotationEzysplitMDRRate().getBoostSettlement() !=
//						 * Integer.parseInt("0")) { wmdrTable.addCell(new Phrase( "T + " +
//						 * String.valueOf(od.getQuotationEzysplitMDRRate().getBoostSettlement()),
//						 * fontmoh4)); } else { wmdrTable.addCell(new Phrase("", fontmoh4)); }
//						 * 
//						 * discounttab.setHorizontalAlignment(Element.ALIGN_CENTER);
//						 * 
//						 * URL fpxPath =
//						 * getClass().getClassLoader().getResource("/assets/images/fpx.png"); Image
//						 * fpximage = Image.getInstance(fpxPath); fpximage.scaleAbsolute(11f, 4f);
//						 * fpximage.setAlignment(Element.ALIGN_CENTER); wmdrTable.addCell(fpximage);
//						 * discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
//						 * wmdrTable.addCell(new Phrase( "RM" +
//						 * od.getQuotationEzysplitMDRRate().getfPXMDR_RM() + " or " +
//						 * od.getQuotationEzysplitMDRRate().getfPXMDR_Percent() +
//						 * "% whichever is higher", fontmoh4)); if
//						 * (od.getQuotationEzysplitMDRRate().getFpxSettlement() !=
//						 * Integer.parseInt("0")) { wmdrTable.addCell(new Phrase( "T + " +
//						 * String.valueOf(od.getQuotationEzysplitMDRRate().getFpxSettlement()),
//						 * fontmoh4)); } else { wmdrTable.addCell(new Phrase("", fontmoh4)); }
//						 */
//
//					} else {
//						/*
//						 * URL grabPayPath =
//						 * getClass().getClassLoader().getResource("/assets/images/grabpay.png"); Image
//						 * gpimage = Image.getInstance(grabPayPath); gpimage.scaleAbsolute(11f, 4f);
//						 * gpimage.setAlignment(Element.ALIGN_LEFT); wmdrTable.addCell(gpimage);
//						 */
//
//						// discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
//						wmdrTable.addCell(new Phrase(od.getQuotationMDRRate().getProductType(), fontmoh4));
//						
//						if(od.getProduct().getProductType().equalsIgnoreCase("EZYWIRE"))
//						{
//							wmdrTable.addCell(new Phrase("NILL", fontmoh4));
//							
//						}
//						
//						else
//						{
//						
//						wmdrTable.addCell(new Phrase(String.valueOf(od.getQuotationMDRRate().getBoostMDREcomm()) + "%",
//								fontmoh4));
//						}
//						
//						wmdrTable.addCell(
//								new Phrase(String.valueOf(od.getQuotationMDRRate().getBoostMDRQR()) + "%", fontmoh4));
//						
//						if(od.getProduct().getProductType().equalsIgnoreCase("EZYWIRE"))
//						{
//							wmdrTable.addCell(new Phrase("NILL", fontmoh4));
//							
//						}
//						
//						else {
//						
//						wmdrTable.addCell(
//								new Phrase(String.valueOf(od.getQuotationMDRRate().getGrabMDREcomm()) + "%", fontmoh4));
//						}
//						
//						wmdrTable.addCell(
//								new Phrase(String.valueOf(od.getQuotationMDRRate().getGrabMDRQR()) + "%", fontmoh4));
//						
//						
//						if(od.getProduct().getProductType().equalsIgnoreCase("EZYWIRE"))
//						{
//							wmdrTable.addCell(new Phrase("NILL", fontmoh4));
//							wmdrTable.addCell(new Phrase("NILL", fontmoh4));
//							
//						}
//						
//						else {
//						
//						wmdrTable
//								.addCell(new Phrase(String.valueOf(od.getQuotationMDRRate().getfPXMDR_RM()), fontmoh4));
//						wmdrTable.addCell(new Phrase(String.valueOf(od.getQuotationMDRRate().getfPXMDR_Percent()) + "%",
//								fontmoh4));
//						}
//						/*
//						 * if (od.getQuotationMDRRate().getGrabSettlement() != Integer.parseInt("0")) {
//						 * wmdrTable.addCell(new Phrase( "T + " +
//						 * String.valueOf(od.getQuotationMDRRate().getGrabSettlement()), fontmoh4)); }
//						 * else { wmdrTable.addCell(new Phrase("", fontmoh4)); }
//						 */
//						discounttab.setHorizontalAlignment(Element.ALIGN_CENTER);
//
//						/*
//						 * URL boostPath =
//						 * getClass().getClassLoader().getResource("/assets/images/boost.png"); Image
//						 * bimage = Image.getInstance(boostPath); bimage.scaleAbsolute(11f, 4f);
//						 * bimage.setAlignment(Element.ALIGN_CENTER); wmdrTable.addCell(bimage);
//						 * discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT); wmdrTable.addCell(
//						 * new Phrase(String.valueOf(od.getQuotationMDRRate().getBoostMDR()) + "%",
//						 * fontmoh4)); if (od.getQuotationMDRRate().getBoostSettlement() !=
//						 * Integer.parseInt("0")) { wmdrTable.addCell(new Phrase( "T + " +
//						 * String.valueOf(od.getQuotationMDRRate().getBoostSettlement()), fontmoh4)); }
//						 * else { wmdrTable.addCell(new Phrase("", fontmoh4)); }
//						 * 
//						 * discounttab.setHorizontalAlignment(Element.ALIGN_CENTER);
//						 * 
//						 * URL fpxPath =
//						 * getClass().getClassLoader().getResource("/assets/images/fpx.png"); Image
//						 * fpximage = Image.getInstance(fpxPath); fpximage.scaleAbsolute(11f, 4f);
//						 * fpximage.setAlignment(Element.ALIGN_CENTER); wmdrTable.addCell(fpximage);
//						 * discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
//						 * wmdrTable.addCell(new Phrase( "RM" + od.getQuotationMDRRate().getfPXMDR_RM()
//						 * + " or " + od.getQuotationMDRRate().getfPXMDR_Percent() +
//						 * "% whichever is higher", fontmoh4)); if
//						 * (od.getQuotationMDRRate().getFpxSettlement() != Integer.parseInt("0")) {
//						 * wmdrTable.addCell(new Phrase( "T + " +
//						 * String.valueOf(od.getQuotationMDRRate().getFpxSettlement()), fontmoh4)); }
//						 * else { wmdrTable.addCell(new Phrase("", fontmoh4)); }
//						 * 
//						 * break;
//						 */
//					}
//
//					// }
//				}
//
//			}
//
//			pdfDoc.add(wmdrTable);
//		// endregion
//			
//			
//			//tng&shp start
//			
//			Paragraph pwmdr1 = new Paragraph();
//			PdfPTable wmdrTable1 = new PdfPTable(5);
//			float[] wmdrWidths1 = new float[] { 1f,1f, 1f, 1f, 1f };
//
//			// if (od.getQuotationMDRRate().getIncludeWallet().equals("Yes"))
//			// {
//			discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);
//			if (odList.size() > 0) {
//				
//				pdfDoc.add(pwmdr1);
//				pdfDoc.add(p5);
//				wmdrTable1.setWidths(wmdrWidths1);
//				wmdrTable1.setWidthPercentage(100f);
//				   wmdrTable1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//					wmdrTable1.getDefaultCell().disableBorderSide(15);
//					wmdrTable1.getDefaultCell().enableBorderSide(2);
//					wmdrTable1.getDefaultCell().setBorderColor(new BaseColor(200, 224, 230));
//					wmdrTable1.getDefaultCell().setPaddingBottom(3f);
//					wmdrTable1.getDefaultCell().setBackgroundColor(new BaseColor(0, 91, 170));
//					wmdrTable1.addCell(new Phrase("Product", fontWhite));
//					wmdrTable1.addCell(new Phrase("Tng EComm", fontWhite));
//					wmdrTable1.addCell(new Phrase("Tng QR", fontWhite));
//					wmdrTable1.addCell(new Phrase("Shopeepay EComm", fontWhite));
//					wmdrTable1.addCell(new Phrase("Shopeepay QR", fontWhite));
//					
//				
//				wmdrTable1.getDefaultCell().setBackgroundColor(new BaseColor(255, 255, 255));
//				discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);
//
//				image.scaleAbsolute(55f, 18f);
//				image.setAlignment(Element.ALIGN_LEFT);
//				cellxysign.addElement(new Paragraph("\n"));
//				cellxysign.addElement(image);
//				cellxysign.disableBorderSide(15);
//				pdfsign.addCell(cellxysign);
//
//				for (OrderLines od : odList) {
//					if (od.getQuotationEzysplitMDRRate() != null) {
//
//						/*
//						 * URL grabPayPath =
//						 * getClass().getClassLoader().getResource("/assets/images/grabpay.png"); Image
//						 * gpimage = Image.getInstance(grabPayPath); gpimage.scaleAbsolute(11f, 4f);
//						 * gpimage.setAlignment(Element.ALIGN_LEFT); wmdrTable.addCell(gpimage);
//						 */
//
//						discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
//						wmdrTable1.addCell(new Phrase(od.getQuotationEzysplitMDRRate().getProductType(), fontmoh4));
//						
//						if(od.getProduct().getProductType().equalsIgnoreCase("EZYWIRE"))
//						{
//							wmdrTable1.addCell(new Phrase("NILL", fontmoh4));
//						
//						}
//						
//						else {
//						wmdrTable1.addCell(new Phrase(
//								String.valueOf(od.getQuotationEzysplitMDRRate().getTngMDREcomm()) + "%", fontmoh4));
//						}
//						
//						wmdrTable1.addCell(new Phrase(
//								String.valueOf(od.getQuotationEzysplitMDRRate().getTngMDRQR()) + "%", fontmoh4));
//						
//						if(od.getProduct().getProductType().equalsIgnoreCase("EZYWIRE"))
//						{
//							wmdrTable1.addCell(new Phrase("NILL", fontmoh4));
//						
//						}
//						
//						else {
//						wmdrTable1.addCell(new Phrase(
//								String.valueOf(od.getQuotationEzysplitMDRRate().getShopeepayMDREcomm()) + "%", fontmoh4));
//						}
//						
//						wmdrTable1.addCell(new Phrase(
//								String.valueOf(od.getQuotationEzysplitMDRRate().getShopeepayMDRQR()) + "%", fontmoh4));
//						
//						/*
//						 * if (od.getQuotationEzysplitMDRRate().getGrabSettlement() !=
//						 * Integer.parseInt("0")) { wmdrTable.addCell(new Phrase("T + " +
//						 * String.valueOf(od.getQuotationEzysplitMDRRate().getGrabSettlement()),
//						 * fontmoh4)); } else { wmdrTable.addCell(new Phrase("", fontmoh4)); }
//						 */
//
//						discounttab.setHorizontalAlignment(Element.ALIGN_CENTER);
//
//						/*
//						 * URL boostPath =
//						 * getClass().getClassLoader().getResource("/assets/images/boost.png"); Image
//						 * bimage = Image.getInstance(boostPath); bimage.scaleAbsolute(11f, 4f);
//						 * bimage.setAlignment(Element.ALIGN_CENTER); wmdrTable.addCell(bimage);
//						 * discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT); wmdrTable.addCell(
//						 * new Phrase(String.valueOf(od.getQuotationEzysplitMDRRate().getBoostMDR()) +
//						 * "%", fontmoh4)); if (od.getQuotationEzysplitMDRRate().getBoostSettlement() !=
//						 * Integer.parseInt("0")) { wmdrTable.addCell(new Phrase( "T + " +
//						 * String.valueOf(od.getQuotationEzysplitMDRRate().getBoostSettlement()),
//						 * fontmoh4)); } else { wmdrTable.addCell(new Phrase("", fontmoh4)); }
//						 * 
//						 * discounttab.setHorizontalAlignment(Element.ALIGN_CENTER);
//						 * 
//						 * URL fpxPath =
//						 * getClass().getClassLoader().getResource("/assets/images/fpx.png"); Image
//						 * fpximage = Image.getInstance(fpxPath); fpximage.scaleAbsolute(11f, 4f);
//						 * fpximage.setAlignment(Element.ALIGN_CENTER); wmdrTable.addCell(fpximage);
//						 * discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
//						 * wmdrTable.addCell(new Phrase( "RM" +
//						 * od.getQuotationEzysplitMDRRate().getfPXMDR_RM() + " or " +
//						 * od.getQuotationEzysplitMDRRate().getfPXMDR_Percent() +
//						 * "% whichever is higher", fontmoh4)); if
//						 * (od.getQuotationEzysplitMDRRate().getFpxSettlement() !=
//						 * Integer.parseInt("0")) { wmdrTable.addCell(new Phrase( "T + " +
//						 * String.valueOf(od.getQuotationEzysplitMDRRate().getFpxSettlement()),
//						 * fontmoh4)); } else { wmdrTable.addCell(new Phrase("", fontmoh4)); }
//						 */
//
//					} else {
//						/*
//						 * URL grabPayPath =
//						 * getClass().getClassLoader().getResource("/assets/images/grabpay.png"); Image
//						 * gpimage = Image.getInstance(grabPayPath); gpimage.scaleAbsolute(11f, 4f);
//						 * gpimage.setAlignment(Element.ALIGN_LEFT); wmdrTable.addCell(gpimage);
//						 */
//
//						// discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
//						wmdrTable1.addCell(new Phrase(od.getQuotationMDRRate().getProductType(), fontmoh4));
//						
//						if(od.getProduct().getProductType().equalsIgnoreCase("EZYWIRE"))
//						{
//							wmdrTable1.addCell(new Phrase("NILL", fontmoh4));
//						
//						}
//						else {
//						
//						wmdrTable1.addCell(new Phrase(String.valueOf(od.getQuotationMDRRate().getTngMDREcomm()) + "%",
//								fontmoh4));
//						}
//						wmdrTable1.addCell(
//								new Phrase(String.valueOf(od.getQuotationMDRRate().getTngMDRQR()) + "%", fontmoh4));
//						
//						if(od.getProduct().getProductType().equalsIgnoreCase("EZYWIRE"))
//						{
//							wmdrTable1.addCell(new Phrase("NILL", fontmoh4));
//						
//						}
//						
//						else {
//						wmdrTable1.addCell(
//								new Phrase(String.valueOf(od.getQuotationMDRRate().getShopeepayMDREcomm()) + "%", fontmoh4));
//						}
//						
//						wmdrTable1.addCell(
//								new Phrase(String.valueOf(od.getQuotationMDRRate().getShopeepayMDRQR()) + "%", fontmoh4));
//						
//						/*
//						 * if (od.getQuotationMDRRate().getGrabSettlement() != Integer.parseInt("0")) {
//						 * wmdrTable.addCell(new Phrase( "T + " +
//						 * String.valueOf(od.getQuotationMDRRate().getGrabSettlement()), fontmoh4)); }
//						 * else { wmdrTable.addCell(new Phrase("", fontmoh4)); }
//						 */
//						discounttab.setHorizontalAlignment(Element.ALIGN_CENTER);
//
//						/*
//						 * URL boostPath =
//						 * getClass().getClassLoader().getResource("/assets/images/boost.png"); Image
//						 * bimage = Image.getInstance(boostPath); bimage.scaleAbsolute(11f, 4f);
//						 * bimage.setAlignment(Element.ALIGN_CENTER); wmdrTable.addCell(bimage);
//						 * discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT); wmdrTable.addCell(
//						 * new Phrase(String.valueOf(od.getQuotationMDRRate().getBoostMDR()) + "%",
//						 * fontmoh4)); if (od.getQuotationMDRRate().getBoostSettlement() !=
//						 * Integer.parseInt("0")) { wmdrTable.addCell(new Phrase( "T + " +
//						 * String.valueOf(od.getQuotationMDRRate().getBoostSettlement()), fontmoh4)); }
//						 * else { wmdrTable.addCell(new Phrase("", fontmoh4)); }
//						 * 
//						 * discounttab.setHorizontalAlignment(Element.ALIGN_CENTER);
//						 * 
//						 * URL fpxPath =
//						 * getClass().getClassLoader().getResource("/assets/images/fpx.png"); Image
//						 * fpximage = Image.getInstance(fpxPath); fpximage.scaleAbsolute(11f, 4f);
//						 * fpximage.setAlignment(Element.ALIGN_CENTER); wmdrTable.addCell(fpximage);
//						 * discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
//						 * wmdrTable.addCell(new Phrase( "RM" + od.getQuotationMDRRate().getfPXMDR_RM()
//						 * + " or " + od.getQuotationMDRRate().getfPXMDR_Percent() +
//						 * "% whichever is higher", fontmoh4)); if
//						 * (od.getQuotationMDRRate().getFpxSettlement() != Integer.parseInt("0")) {
//						 * wmdrTable.addCell(new Phrase( "T + " +
//						 * String.valueOf(od.getQuotationMDRRate().getFpxSettlement()), fontmoh4)); }
//						 * else { wmdrTable.addCell(new Phrase("", fontmoh4)); }
//						 * 
//						 * break;
//						 */
//					}
//
//					// }
//				}
//
//			}
//
//			pdfDoc.add(wmdrTable1);
			
		
			
            for (OrderLines odc : odList) {

                if (!odc.getProduct().getProductType().equalsIgnoreCase("EZYMOTO")) {

                       logger.info("If the product IS EZYMOTO then SKIP Wallets...--->");

                       Paragraph pwmdr = new Paragraph();
                       PdfPTable wmdrTable = new PdfPTable(7);

                       float[] wmdrWidths = new float[] { 0.5f, 1f, 1f, 1f, 1f, 1f, 1f };

                       // if (od.getQuotationMDRRate().getIncludeWallet().equals("Yes"))
                       // {
                       discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);
                       if (odList.size() > 0) {
                              pwmdr.setFont(boldFont);
                             pwmdr.add("Wallets");
                             pdfDoc.add(pwmdr);
                             pdfDoc.add(p5);
                              wmdrTable.setWidths(wmdrWidths);
                              wmdrTable.setWidthPercentage(100f);
                       wmdrTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                              wmdrTable.getDefaultCell().disableBorderSide(15);
                              wmdrTable.getDefaultCell().enableBorderSide(2);
                              wmdrTable.getDefaultCell().setBorderColor(new BaseColor(200, 224, 230));
                              wmdrTable.getDefaultCell().setPaddingBottom(3f);
                              wmdrTable.getDefaultCell().setBackgroundColor(new BaseColor(0, 91, 170));
                             wmdrTable.addCell(new Phrase("Product", fontWhite));
                             wmdrTable.addCell(new Phrase("Boost EComm", fontWhite));
                             wmdrTable.addCell(new Phrase("Boost QR", fontWhite));
                             wmdrTable.addCell(new Phrase("Grab EComm", fontWhite));
                             wmdrTable.addCell(new Phrase("Grab QR", fontWhite));

                             wmdrTable.addCell(new Phrase("FPX(RM)", fontWhite));
                             wmdrTable.addCell(new Phrase("FPX(%)", fontWhite));
                              wmdrTable.getDefaultCell().setBackgroundColor(new BaseColor(255, 255, 255));
                              discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);

                              image.scaleAbsolute(55f, 18f);
                              image.setAlignment(Element.ALIGN_LEFT);
                              cellxysign.addElement(new Paragraph("\n"));
                              cellxysign.addElement(image);
                              cellxysign.disableBorderSide(15);
                              pdfsign.addCell(cellxysign);

                             for (OrderLines od : odList) {
                                    if (od.getProduct().getProductType().equalsIgnoreCase("EZYMOTO")) {
                                           wmdrTable.getDefaultCell().disableBorderSide(15);
                                           wmdrTable.getDefaultCell().disableBorderSide(15);
                                           wmdrTable.getDefaultCell().disableBorderSide(15);
                                           wmdrTable.getDefaultCell().disableBorderSide(15);
                                           wmdrTable.getDefaultCell().disableBorderSide(15);
                                           wmdrTable.getDefaultCell().disableBorderSide(15);
                                           wmdrTable.getDefaultCell().disableBorderSide(15);
                                    } else {
                                          if (od.getQuotationEzysplitMDRRate() != null) {

                                                 /*
                                                 * URL grabPayPath =
                                                 * getClass().getClassLoader().getResource("/assets/images/grabpay.png"); Image
                                                 * gpimage = Image.getInstance(grabPayPath); gpimage.scaleAbsolute(11f, 4f);
                                                 * gpimage.setAlignment(Element.ALIGN_LEFT); wmdrTable.addCell(gpimage);
                                                 */

                                                discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                                 wmdrTable.addCell(
                                                              new Phrase(od.getQuotationEzysplitMDRRate().getProductType(), fontmoh4));

                                                 if (od.getProduct().getProductType().equalsIgnoreCase("EZYMOTO")) {
                                                        // wmdrTable.addCell(new Phrase("NILLOS", fontmoh4));
                                                        // wmdrTable.getDefaultCell().disableBorderSide(15);
//                                               PdfPCell empty = new PdfPCell();
//                                               empty.disableBorderSide(4);
                                                        // empty.disableBorderSide(6);
                                                        continue;

                                                 }

                                                 else {
                                                        wmdrTable.addCell(new Phrase(
                                                              String.valueOf(od.getQuotationEzysplitMDRRate().getBoostMDREcomm())
                                                                                  + "%",
                                                                     fontmoh4));
                                                 }

                                                 wmdrTable.addCell(new Phrase(
                                                        String.valueOf(od.getQuotationEzysplitMDRRate().getBoostMDRQR()) + "%",
                                                              fontmoh4));

                                                 if (od.getProduct().getProductType().equalsIgnoreCase("EZYMOTO")) {
                                                        wmdrTable.addCell(new Phrase("NILLOS", fontmoh4));
//                                               //wmdrTable.flushContent();
//                                               PdfPCell empty = new PdfPCell();
//                                               empty.disableBorderSide(4);

                                                 }

                                                 else {
                                                        wmdrTable.addCell(new Phrase(
                                                              String.valueOf(od.getQuotationEzysplitMDRRate().getGrabMDREcomm())
                                                                                  + "%",
                                                                     fontmoh4));

                                                 }

                                                 wmdrTable.addCell(new Phrase(
                                                        String.valueOf(od.getQuotationEzysplitMDRRate().getGrabMDRQR()) + "%",
                                                              fontmoh4));

                                                 if (od.getProduct().getProductType().equalsIgnoreCase("EZYMOTO")) {
                                                        wmdrTable.addCell(new Phrase("NILLOS", fontmoh4));
                                                        wmdrTable.addCell(new Phrase("NILLOS", fontmoh4));

                                                 } else {
                                                        wmdrTable.addCell(new Phrase(
                                                              String.valueOf(od.getQuotationEzysplitMDRRate().getfPXMDR_RM()),
                                                                     fontmoh4));
                                                        wmdrTable.addCell(new Phrase(
                                                              String.valueOf(od.getQuotationEzysplitMDRRate().getfPXMDR_Percent())
                                                                                  + "%",
                                                                     fontmoh4));

                                                 }
                                                 /*
                                                 * if (od.getQuotationEzysplitMDRRate().getGrabSettlement() !=
                                                 * Integer.parseInt("0")) { wmdrTable.addCell(new Phrase("T + " +
                                                 * String.valueOf(od.getQuotationEzysplitMDRRate().getGrabSettlement()),
                                                 * fontmoh4)); } else { wmdrTable.addCell(new Phrase("", fontmoh4)); }
                                                 */

                                               discounttab.setHorizontalAlignment(Element.ALIGN_CENTER);

                                                 /*
                                                 * URL boostPath =
                                                 * getClass().getClassLoader().getResource("/assets/images/boost.png"); Image
                                                 * bimage = Image.getInstance(boostPath); bimage.scaleAbsolute(11f, 4f);
                                                 * bimage.setAlignment(Element.ALIGN_CENTER); wmdrTable.addCell(bimage);
                                                 * discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT); wmdrTable.addCell(
                                                 * new Phrase(String.valueOf(od.getQuotationEzysplitMDRRate().getBoostMDR()) +
                                                 * "%", fontmoh4)); if (od.getQuotationEzysplitMDRRate().getBoostSettlement() !=
                                                 * Integer.parseInt("0")) { wmdrTable.addCell(new Phrase( "T + " +
                                                 * String.valueOf(od.getQuotationEzysplitMDRRate().getBoostSettlement()),
                                                 * fontmoh4)); } else { wmdrTable.addCell(new Phrase("", fontmoh4)); }
                                                 * 
                                                  * discounttab.setHorizontalAlignment(Element.ALIGN_CENTER);
                                                 * 
                                                  * URL fpxPath =
                                                 * getClass().getClassLoader().getResource("/assets/images/fpx.png"); Image
                                                 * fpximage = Image.getInstance(fpxPath); fpximage.scaleAbsolute(11f, 4f);
                                                 * fpximage.setAlignment(Element.ALIGN_CENTER); wmdrTable.addCell(fpximage);
                                                 * discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                                 * wmdrTable.addCell(new Phrase( "RM" +
                                                 * od.getQuotationEzysplitMDRRate().getfPXMDR_RM() + " or " +
                                                 * od.getQuotationEzysplitMDRRate().getfPXMDR_Percent() +
                                                 * "% whichever is higher", fontmoh4)); if
                                                 * (od.getQuotationEzysplitMDRRate().getFpxSettlement() !=
                                                 * Integer.parseInt("0")) { wmdrTable.addCell(new Phrase( "T + " +
                                                 * String.valueOf(od.getQuotationEzysplitMDRRate().getFpxSettlement()),
                                                 * fontmoh4)); } else { wmdrTable.addCell(new Phrase("", fontmoh4)); }
                                                 */

                                          } else {
                                                 /*
                                                 * URL grabPayPath =
                                                 * getClass().getClassLoader().getResource("/assets/images/grabpay.png"); Image
                                                 * gpimage = Image.getInstance(grabPayPath); gpimage.scaleAbsolute(11f, 4f);
                                                 * gpimage.setAlignment(Element.ALIGN_LEFT); wmdrTable.addCell(gpimage);
                                                 */

                                                 // discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                                 wmdrTable.addCell(new Phrase(od.getQuotationMDRRate().getProductType(), fontmoh4));

                                                 if (od.getProduct().getProductType().equalsIgnoreCase("EZYMOTO")) {
//                                               wmdrTable.addCell(new Phrase("NILLOS", fontmoh4));
                                                        wmdrTable.getDefaultCell().disableBorderSide(15);
//                                               wmdrTable.flushContent();
//                                               PdfPCell empty = new PdfPCell();
//                                               empty.disableBorderSide(4);

                                                 }

                                                 else {

                                                        wmdrTable.addCell(new Phrase(
                                                                 String.valueOf(od.getQuotationMDRRate().getBoostMDREcomm()) + "%",
                                                                     fontmoh4));
                                                 }

                                                 wmdrTable.addCell(new Phrase(
                                                              String.valueOf(od.getQuotationMDRRate().getBoostMDRQR()) + "%", fontmoh4));

                                                 if (od.getProduct().getProductType().equalsIgnoreCase("EZYMOTO")) {
                                                        wmdrTable.addCell(new Phrase("NILLOS", fontmoh4));
                                                        // wmdrTable.flushContent();
//                                               PdfPCell empty = new PdfPCell();
//                                               empty.disab/leBorderSide(4);

                                                 }

                                                 else {

                                                        wmdrTable.addCell(new Phrase(
                                                                  String.valueOf(od.getQuotationMDRRate().getGrabMDREcomm()) + "%",
                                                                     fontmoh4));
                                                 }

                                                 wmdrTable.addCell(new Phrase(
                                                               String.valueOf(od.getQuotationMDRRate().getGrabMDRQR()) + "%", fontmoh4));

                                                 if (od.getProduct().getProductType().equalsIgnoreCase("EZYMOTO")) {
                                                        wmdrTable.addCell(new Phrase("NILLOS", fontmoh4));
                                                        wmdrTable.addCell(new Phrase("NILLOSOS", fontmoh4));
                                                        // wmdrTable.flushContent();
//                                               PdfPCell empty = new PdfPCell();
//                                               empty.disableBorderSide(4);

                                                 }

                                                 else {

                                                        wmdrTable.addCell(new Phrase(
                                                                    String.valueOf(od.getQuotationMDRRate().getfPXMDR_RM()), fontmoh4));
                                                        wmdrTable.addCell(new Phrase(
                                                                String.valueOf(od.getQuotationMDRRate().getfPXMDR_Percent()) + "%",
                                                                     fontmoh4));
                                                 }
                                                 /*
                                                 * if (od.getQuotationMDRRate().getGrabSettlement() != Integer.parseInt("0")) {
                                                 * wmdrTable.addCell(new Phrase( "T + " +
                                                 * String.valueOf(od.getQuotationMDRRate().getGrabSettlement()), fontmoh4)); }
                                                 * else { wmdrTable.addCell(new Phrase("", fontmoh4)); }
                                                 */
                                               discounttab.setHorizontalAlignment(Element.ALIGN_CENTER);

                                                 /*
                                                 * URL boostPath =
                                                 * getClass().getClassLoader().getResource("/assets/images/boost.png"); Image
                                                 * bimage = Image.getInstance(boostPath); bimage.scaleAbsolute(11f, 4f);
                                                 * bimage.setAlignment(Element.ALIGN_CENTER); wmdrTable.addCell(bimage);
                                                 * discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT); wmdrTable.addCell(
                                                 * new Phrase(String.valueOf(od.getQuotationMDRRate().getBoostMDR()) + "%",
                                                 * fontmoh4)); if (od.getQuotationMDRRate().getBoostSettlement() !=
                                                 * Integer.parseInt("0")) { wmdrTable.addCell(new Phrase( "T + " +
                                                 * String.valueOf(od.getQuotationMDRRate().getBoostSettlement()), fontmoh4)); }
                                                 * else { wmdrTable.addCell(new Phrase("", fontmoh4)); }
                                                 * 
                                                  * discounttab.setHorizontalAlignment(Element.ALIGN_CENTER);
                                                 * 
                                                  * URL fpxPath =
                                                 * getClass().getClassLoader().getResource("/assets/images/fpx.png"); Image
                                                 * fpximage = Image.getInstance(fpxPath); fpximage.scaleAbsolute(11f, 4f);
                                                 * fpximage.setAlignment(Element.ALIGN_CENTER); wmdrTable.addCell(fpximage);
                                                 * discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                                 * wmdrTable.addCell(new Phrase( "RM" + od.getQuotationMDRRate().getfPXMDR_RM()
                                                 * + " or " + od.getQuotationMDRRate().getfPXMDR_Percent() +
                                                 * "% whichever is higher", fontmoh4)); if
                                                 * (od.getQuotationMDRRate().getFpxSettlement() != Integer.parseInt("0")) {
                                                 * wmdrTable.addCell(new Phrase( "T + " +
                                                 * String.valueOf(od.getQuotationMDRRate().getFpxSettlement()), fontmoh4)); }
                                                 * else { wmdrTable.addCell(new Phrase("", fontmoh4)); }
                                                 * 
                                                  * break;
                                                 */
                                          }

                                          // }
                                    }

                             }
                       }

                       pdfDoc.add(wmdrTable);
                       // endregion

                       // tng&shp start

                       Paragraph pwmdr1 = new Paragraph();
                       PdfPTable wmdrTable1 = new PdfPTable(5);
                       float[] wmdrWidths1 = new float[] { 1f, 1f, 1f, 1f, 1f };

                       // if (od.getQuotationMDRRate().getIncludeWallet().equals("Yes"))
                       // {
                       discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);
                       if (odList.size() > 0) {

                             pdfDoc.add(pwmdr1);
                             pdfDoc.add(p5);
                              wmdrTable1.setWidths(wmdrWidths1);
                              wmdrTable1.setWidthPercentage(100f);
                       wmdrTable1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                              wmdrTable1.getDefaultCell().disableBorderSide(15);
                              wmdrTable1.getDefaultCell().enableBorderSide(2);
                              wmdrTable1.getDefaultCell().setBorderColor(new BaseColor(200, 224, 230));
                              wmdrTable1.getDefaultCell().setPaddingBottom(3f);
                              wmdrTable1.getDefaultCell().setBackgroundColor(new BaseColor(0, 91, 170));
                             wmdrTable1.addCell(new Phrase("Product", fontWhite));
                             wmdrTable1.addCell(new Phrase("Tng EComm", fontWhite));
                             wmdrTable1.addCell(new Phrase("Tng QR", fontWhite));
                             wmdrTable1.addCell(new Phrase("Shopeepay EComm", fontWhite));
                             wmdrTable1.addCell(new Phrase("Shopeepay QR", fontWhite));

                              wmdrTable1.getDefaultCell().setBackgroundColor(new BaseColor(255, 255, 255));
                              discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);

                              image.scaleAbsolute(55f, 18f);
                              image.setAlignment(Element.ALIGN_LEFT);
                              cellxysign.addElement(new Paragraph("\n"));
                              cellxysign.addElement(image);
                              cellxysign.disableBorderSide(15);
                              pdfsign.addCell(cellxysign);

                             for (OrderLines od : odList) {
                                    if (od.getProduct().getProductType().equalsIgnoreCase("EZYMOTO")) {
                                           wmdrTable.getDefaultCell().disableBorderSide(15);
                                           wmdrTable.getDefaultCell().disableBorderSide(15);
                                           wmdrTable.getDefaultCell().disableBorderSide(15);
                                           wmdrTable.getDefaultCell().disableBorderSide(15);
                                           wmdrTable.getDefaultCell().disableBorderSide(15);
                                           wmdrTable.getDefaultCell().disableBorderSide(15);
                                    } else {

                                          if (od.getQuotationEzysplitMDRRate() != null) {

                                                 /*
                                                 * URL grabPayPath =
                                                 * getClass().getClassLoader().getResource("/assets/images/grabpay.png"); Image
                                                 * gpimage = Image.getInstance(grabPayPath); gpimage.scaleAbsolute(11f, 4f);
                                                 * gpimage.setAlignment(Element.ALIGN_LEFT); wmdrTable.addCell(gpimage);
                                                 */

                                                discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                                 wmdrTable1.addCell(
                                                              new Phrase(od.getQuotationEzysplitMDRRate().getProductType(), fontmoh4));

                                                 if (od.getProduct().getProductType().equalsIgnoreCase("EZYMOTO")) {
                                                        wmdrTable1.addCell(new Phrase("NILL", fontmoh4));

                                                 }

                                                 else {
                                                        wmdrTable1.addCell(new Phrase(
                                                              String.valueOf(od.getQuotationEzysplitMDRRate().getTngMDREcomm()) + "%",
                                                                     fontmoh4));
                                                 }

                                                 wmdrTable1.addCell(new Phrase(
                                                        String.valueOf(od.getQuotationEzysplitMDRRate().getTngMDRQR()) + "%",
                                                              fontmoh4));

                                                 if (od.getProduct().getProductType().equalsIgnoreCase("EZYMOTO")) {
                                                        wmdrTable1.addCell(new Phrase("NILL", fontmoh4));

                                                 }

                                                 else {
                                                        wmdrTable1.addCell(new Phrase(
                                                              String.valueOf(od.getQuotationEzysplitMDRRate().getShopeepayMDREcomm())
                                                                                  + "%",
                                                                     fontmoh4));
                                                 }

                                                 wmdrTable1.addCell(new Phrase(
                                                        String.valueOf(od.getQuotationEzysplitMDRRate().getShopeepayMDRQR()) + "%",
                                                              fontmoh4));

                                                 /*
                                                 * if (od.getQuotationEzysplitMDRRate().getGrabSettlement() !=
                                                 * Integer.parseInt("0")) { wmdrTable.addCell(new Phrase("T + " +
                                                 * String.valueOf(od.getQuotationEzysplitMDRRate().getGrabSettlement()),
                                                 * fontmoh4)); } else { wmdrTable.addCell(new Phrase("", fontmoh4)); }
                                                 */

                                               discounttab.setHorizontalAlignment(Element.ALIGN_CENTER);

                                                 /*
                                                 * URL boostPath =
                                                 * getClass().getClassLoader().getResource("/assets/images/boost.png"); Image
                                                 * bimage = Image.getInstance(boostPath); bimage.scaleAbsolute(11f, 4f);
                                                 * bimage.setAlignment(Element.ALIGN_CENTER); wmdrTable.addCell(bimage);
                                                 * discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT); wmdrTable.addCell(
                                                 * new Phrase(String.valueOf(od.getQuotationEzysplitMDRRate().getBoostMDR()) +
                                                 * "%", fontmoh4)); if (od.getQuotationEzysplitMDRRate().getBoostSettlement() !=
                                                 * Integer.parseInt("0")) { wmdrTable.addCell(new Phrase( "T + " +
                                                 * String.valueOf(od.getQuotationEzysplitMDRRate().getBoostSettlement()),
                                                 * fontmoh4)); } else { wmdrTable.addCell(new Phrase("", fontmoh4)); }
                                                 * 
                                                  * discounttab.setHorizontalAlignment(Element.ALIGN_CENTER);
                                                 * 
                                                  * URL fpxPath =
                                                 * getClass().getClassLoader().getResource("/assets/images/fpx.png"); Image
                                                 * fpximage = Image.getInstance(fpxPath); fpximage.scaleAbsolute(11f, 4f);
                                                 * fpximage.setAlignment(Element.ALIGN_CENTER); wmdrTable.addCell(fpximage);
                                                 * discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                                 * wmdrTable.addCell(new Phrase( "RM" +
                                                 * od.getQuotationEzysplitMDRRate().getfPXMDR_RM() + " or " +
                                                 * od.getQuotationEzysplitMDRRate().getfPXMDR_Percent() +
                                                 * "% whichever is higher", fontmoh4)); if
                                                 * (od.getQuotationEzysplitMDRRate().getFpxSettlement() !=
                                                 * Integer.parseInt("0")) { wmdrTable.addCell(new Phrase( "T + " +
                                                 * String.valueOf(od.getQuotationEzysplitMDRRate().getFpxSettlement()),
                                                 * fontmoh4)); } else { wmdrTable.addCell(new Phrase("", fontmoh4)); }
                                                 */

                                          } else {
                                                 /*
                                                 * URL grabPayPath =
                                                 * getClass().getClassLoader().getResource("/assets/images/grabpay.png"); Image
                                                 * gpimage = Image.getInstance(grabPayPath); gpimage.scaleAbsolute(11f, 4f);
                                                 * gpimage.setAlignment(Element.ALIGN_LEFT); wmdrTable.addCell(gpimage);
                                                 */

                                                 // discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                                 wmdrTable1.addCell(new Phrase(od.getQuotationMDRRate().getProductType(), fontmoh4));

                                                 if (od.getProduct().getProductType().equalsIgnoreCase("EZYMOTO")) {
                                                        wmdrTable1.addCell(new Phrase("NILL", fontmoh4));

                                                 } else {

                                                        wmdrTable1.addCell(new Phrase(
                                                                   String.valueOf(od.getQuotationMDRRate().getTngMDREcomm()) + "%",
                                                                     fontmoh4));
                                                 }
                                                 wmdrTable1.addCell(new Phrase(
                                                               String.valueOf(od.getQuotationMDRRate().getTngMDRQR()) + "%", fontmoh4));

                                                 if (od.getProduct().getProductType().equalsIgnoreCase("EZYMOTO")) {
                                                        wmdrTable1.addCell(new Phrase("NILL", fontmoh4));

                                                 }

                                                 else {
                                                        wmdrTable1.addCell(new Phrase(
                                                              String.valueOf(od.getQuotationMDRRate().getShopeepayMDREcomm()) + "%",
                                                                     fontmoh4));
                                                 }

                                                 wmdrTable1.addCell(new Phrase(
                                                          String.valueOf(od.getQuotationMDRRate().getShopeepayMDRQR()) + "%",
                                                              fontmoh4));

                                                 /*
                                                 * if (od.getQuotationMDRRate().getGrabSettlement() != Integer.parseInt("0")) {
                                                 * wmdrTable.addCell(new Phrase( "T + " +
                                                 * String.valueOf(od.getQuotationMDRRate().getGrabSettlement()), fontmoh4)); }
                                                 * else { wmdrTable.addCell(new Phrase("", fontmoh4)); }
                                                 */
                                               discounttab.setHorizontalAlignment(Element.ALIGN_CENTER);

                                                 /*
                                                 * URL boostPath =
                                                 * getClass().getClassLoader().getResource("/assets/images/boost.png"); Image
                                                 * bimage = Image.getInstance(boostPath); bimage.scaleAbsolute(11f, 4f);
                                                 * bimage.setAlignment(Element.ALIGN_CENTER); wmdrTable.addCell(bimage);
                                                 * discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT); wmdrTable.addCell(
                                                 * new Phrase(String.valueOf(od.getQuotationMDRRate().getBoostMDR()) + "%",
                                                 * fontmoh4)); if (od.getQuotationMDRRate().getBoostSettlement() !=
                                                 * Integer.parseInt("0")) { wmdrTable.addCell(new Phrase( "T + " +
                                                 * String.valueOf(od.getQuotationMDRRate().getBoostSettlement()), fontmoh4)); }
                                                 * else { wmdrTable.addCell(new Phrase("", fontmoh4)); }
                                                 * 
                                                  * discounttab.setHorizontalAlignment(Element.ALIGN_CENTER);
                                                 * 
                                                  * URL fpxPath =
                                                 * getClass().getClassLoader().getResource("/assets/images/fpx.png"); Image
                                                 * fpximage = Image.getInstance(fpxPath); fpximage.scaleAbsolute(11f, 4f);
                                                 * fpximage.setAlignment(Element.ALIGN_CENTER); wmdrTable.addCell(fpximage);
                                                 * discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                                 * wmdrTable.addCell(new Phrase( "RM" + od.getQuotationMDRRate().getfPXMDR_RM()
                                                 * + " or " + od.getQuotationMDRRate().getfPXMDR_Percent() +
                                                 * "% whichever is higher", fontmoh4)); if
                                                 * (od.getQuotationMDRRate().getFpxSettlement() != Integer.parseInt("0")) {
                                                 * wmdrTable.addCell(new Phrase( "T + " +
                                                 * String.valueOf(od.getQuotationMDRRate().getFpxSettlement()), fontmoh4)); }
                                                 * else { wmdrTable.addCell(new Phrase("", fontmoh4)); }
                                                 * 
                                                  * break;
                                                 */
                                          } // ELSE Condition

                                          // }
                                    }
                             } // for (OrderLines od : odList)

                       } // if (odList.size() > 0)

                       pdfDoc.add(wmdrTable1);
                }
          }

			
			
			
			//tng &shp end
			
			
			
			pdfDoc.newPage();
			Phrase notes = new Phrase();
			notes.add(new Chunk("Notes: ", normalFont));
			notes.add(Chunk.NEWLINE);
			notes.add(new Chunk(
					"1. Payment by cheque should be crossed 'A/C PAYEE ONLY' and payable to 'Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.)'",
					font5));
			notes.add(Chunk.NEWLINE);
			notes.add(new Chunk("2. Remittance by IBG :- Bank Name : CIMB BANK BERHAD, Account No : 8007 810 373",
					font5));
			notes.add(Chunk.NEWLINE);
			notes.add(new Chunk("Additional Notes : ", normalFont));
			notes.add(Chunk.NEWLINE);
			notes.add(new Chunk(quote.getNotes() == null ? "" : quote.getNotes(), font5));
			notes.add(Chunk.NEWLINE);
			pdfDoc.add(notes);

			Paragraph lineSep = new Paragraph();
			lineSep.add(lineSeparator);
			pdfDoc.add(lineSep);
			pdfDoc.add(Chunk.NEWLINE);
			Paragraph blankpara = new Paragraph(new Phrase("The page below is left Blank intentionally."));
			pdfDoc.add(blankpara);

			Paragraph tnc1 = new Paragraph();
			tnc1.setLeading(0, 2);
			pdfDoc.newPage();
			pdfDoc.add(new Paragraph("\n"));
			tnc1.add("TERMS & CONDITIONS:");

			tnc1.setLeading(0, 2);
			tnc1.setFont(font7);
			pdfDoc.add(tnc1);

			Phrase phraseTNC = new Phrase();
			phraseTNC.add(new Chunk("Please refer the following link for T&C: ", normalFont));
			phraseTNC.add(new Chunk("https://gomobi.io/merchant-tnc/", linkTextFont));
			phraseTNC.add(Chunk.NEWLINE);
			phraseTNC.add(Chunk.NEWLINE);
			phraseTNC.add(new Chunk("Yours faithfully,", font5));
			phraseTNC.add(Chunk.NEWLINE);
			//phraseTNC.add(new Chunk("Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) (1105429-U)", font5));
			phraseTNC.add(new Chunk("Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) 201401029343 (1105429-U)", font5));
			phraseTNC.add(Chunk.NEWLINE);
			phraseTNC.add(new Chunk(
					"[This quotation is computer generated and requires no signature. Please note that terms and conditions of this quotation become legally binding between you and us as soon as you sign and return the copy of this letter]",
					font5));
			phraseTNC.add(Chunk.NEWLINE);

			pdfDoc.add(phraseTNC);
			Paragraph p6 = new Paragraph();
			p6.add(lineSeparator);
			Paragraph tncccris = new Paragraph();
			tncccris.setLeading(0, 2);

			tncccris.add(Chunk.NEWLINE);
			tncccris.add("CONSENT UNDER CREDIT REPORT AGENCY ACT 2010");
			tncccris.setLeading(0, 2);
			tncccris.setFont(font7);
			pdfDoc.add(tncccris);
			Paragraph tncccris1 = new Paragraph();
			tncccris1.setLeading(0, 2);
			tncccris1.setFont(font5);
			tncccris1.add(
					"Pursuant to the Credit Reporting Agencies (CRA) Act 2010 and Central Bank of Malaysia Act 2009, I/we the undersigned do hereby give my/our consent to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) (Mobi) and any/all of the following a registered credit reporting agency under the CRA Act to process my/our company personal data.");
			tncccris1.add(Chunk.NEWLINE);
			tncccris1.add("*   CTOS Data Systems Sdn Bhd (CTOS)");
			tncccris1.add(Chunk.NEWLINE);
			tncccris1.add("*   Experian Information Services (Malaysia) Sdn Bhd (EXPERIAN)");
			tncccris1.add(Chunk.NEWLINE);
			tncccris1.add(
					"i) By this consent, I/we understand and agree that: i) Mobi may conduct credit/trade check, CCRIS and DCHEQS checks on me/us and where applicable on our directors, shareholders, guarantors, etc with CTOS and/or EXPERIAN at any time for as long as I/we have a trade relationship with Mobi or where any dues remain unpaid and outstanding with Mobi, for any one or more of the following purposes (but not limited to):");
			tncccris1.add(Chunk.NEWLINE);
			tncccris1.add("* Opening of account");
			tncccris1.add(Chunk.NEWLINE);
			tncccris1.add("* Credit/Account monitoring");
			tncccris1.add(Chunk.NEWLINE);
			tncccris1.add("* Debt recovery");
			tncccris1.add(Chunk.NEWLINE);
			tncccris1.add("* Credit/Account evaluation");
			tncccris1.add(Chunk.NEWLINE);
			tncccris1.add("* Credit/Account review");
			tncccris1.add(Chunk.NEWLINE);
			tncccris1.add("* Legal documentation consequent to a contract or facility granted by you.");
			tncccris1.add(Chunk.NEWLINE);
			tncccris1.add(
					"ii) Mobi may disclose any information on my/our conduct of my/our account(s) with Mobi, to any business entity/ies for bona fide trade checking at any time. I/We am/are also aware and understand that such information will be provided to CTOS and/or EXPERIAN, who may in turn share such information to subscribers of their service.");
			tncccris1.add(Chunk.NEWLINE);
			tncccris1.add(
					"iii) Where Mobi require any processing of my/our application to be processed by any processing centre located outside Malaysia (including your Head Office), I/we hereby give consent to CTOS and/or EXPERIAN to disclose my/our credit, CCRIS & DCHEQS reports to such locations outside Malaysia.");
			tncccris1.add(Chunk.NEWLINE);
			tncccris1.add(
					"iv) Apart from the above, I/we the undersigned do give my/our consent to Mobi and CTOS and/or EXPERIAN, to process my/our personal data as per the PDPA Act.");
			tncccris1.add(Chunk.NEWLINE);
			tncccris1.add(
					"v) This consent shall remain applicable as long as I/our company am/is maintaining an account/loan/credit/any transaction with Mobi.");
			tncccris1.add(Chunk.NEWLINE);
			tncccris1.add(
					"vi)	Mobi reserves the right to vary any terms & conditions governing the account without prior notice to applicant(s).\");");
			tncccris1.add(Chunk.NEWLINE);

			tncccris1.setFont(font5);
			tncccris1.setLeading(0, 2);
			pdfDoc.add(tncccris1);
			Paragraph p7 = new Paragraph();
			p7.add(lineSeparator);

			pdfDoc.newPage();
			Paragraph conf = new Paragraph();
			conf.setLeading(0, 2);
			pdfDoc.add(new Paragraph("\n"));
			conf.add("CONFIRMATION:");
			conf.setLeading(0, 2);
			conf.setFont(font7);
			pdfDoc.add(conf);

			Paragraph tnccus = new Paragraph();
			tnccus.setLeading(0, 2);
			tnccus.setFont(font5);
			tnccus.add("I/We hereby accept the Terms & Conditions ");
			tnccus.add(new Chunk("https://gomobi.io/merchant-tnc/ ", linkTextFont));
			tnccus.add("and Merchant Agreement on the website  ");
			tnccus.add(new Chunk("https://gomobi.io/merchant-agreement/agree ", linkTextFont));
			tnccus.add(
					"to be bound by them. I/We also acknowledge you have referred me/us to your Privacy Policy in the website ");
			tnccus.add(new Chunk(" https://gomobi.io/ ", linkTextFont));
			tnccus.add(
					"and I / we require no clarification thereof. I / We further represent and warrant that I/ We am / are duly authorised to bind the Merchant to the terms and conditions herein contained. ");

			pdfDoc.add(tnccus);

			// if (quote.getQuotationAcceptance() != null) {
			//
			// Image imagesign = Image.getInstance(env.getProperty("signature.basePath") +
			// quote.getUserId() + ".png");
			// imagesign.scaleAbsolute(150f, 150f);
			// imagesign.setAlignment(Element.ALIGN_LEFT);
			// pdfDoc.add(imagesign);
			//
			// }

			// PDF Signature New Design
			pdfDoc.add(new Chunk("\n"));
			PdfPTable tableSignature = new PdfPTable(3);

			Paragraph legalEntity = new Paragraph();
			legalEntity.setLeading(0, 2);
			legalEntity.setFont(font5);
			legalEntity.add("Legal Entity Name");

			Paragraph legalEntityMerch = new Paragraph();
			legalEntityMerch.setLeading(0, 2);
			legalEntityMerch.setFont(font5);
			legalEntityMerch.add(quote.getCompanyName());

			Paragraph legalEntityMobi = new Paragraph();
			legalEntityMobi.setLeading(0, 2);
			legalEntityMobi.setFont(font5);
			legalEntityMobi.add("Mobi Asia Sdn Bhd");

			Paragraph representative = new Paragraph();
			representative.setLeading(0, 2);
			representative.setFont(font5);
			representative.add("Name As Per IC");

			Paragraph representativeMerch = new Paragraph();
			representativeMerch.setLeading(0, 2);
			representativeMerch.setFont(font5);
			representativeMerch
					.add(quote.getQuotationAcceptance() == null ? "" : quote.getQuotationAcceptance().getNameAsPerIC());

			Paragraph representativeMobi = new Paragraph();
			representativeMobi.setLeading(0, 2);
			representativeMobi.setFont(font5);
			representativeMobi.add(quote.getSalesPerson() == null ? "" : quote.getSalesPerson().getAliasName());

			Paragraph title = new Paragraph();
			title.setLeading(0, 2);
			title.setFont(font5);
			title.add("Title");

			Paragraph titleMerch = new Paragraph();
			titleMerch.setLeading(0, 2);
			titleMerch.setFont(font5);
			titleMerch.add("Director");

			Paragraph titleMobi = new Paragraph();
			titleMobi.setLeading(0, 2);
			titleMobi.setFont(font5);
			titleMobi.add("Merchant Consultant");

			Paragraph date = new Paragraph();
			date.setLeading(0, 2);
			date.setFont(font5);
			date.add("Signed Date");

			Paragraph signedDateMerch = new Paragraph();
			signedDateMerch.setLeading(0, 2);
			signedDateMerch.setFont(font5);
			signedDateMerch.add(quote.getQuotationAcceptance() == null ? ""
					: String.valueOf(quote.getQuotationAcceptance().getCreatedOn()));

			Paragraph signedDateSalesMan = new Paragraph();
			signedDateSalesMan.setLeading(0, 2);
			signedDateSalesMan.setFont(font5);
			signedDateSalesMan.add(quote.getQuotationAcceptance() == null ? ""
					: String.valueOf(quote.getQuotationAcceptance().getCreatedOn()));

			Paragraph ipAddress = new Paragraph();
			ipAddress.setLeading(0, 2);
			ipAddress.setFont(font5);
			ipAddress.add("Ip Address");

			Paragraph ipMerch = new Paragraph();
			ipMerch.setLeading(0, 2);
			ipMerch.setFont(font5);
			ipMerch.add(quote.getQuotationAcceptance() == null ? "" : quote.getQuotationAcceptance().getIpAddress());

			Paragraph ipSalesMan = new Paragraph();
			ipSalesMan.setLeading(0, 2);
			ipSalesMan.setFont(font5);
			ipSalesMan.add("");

			Paragraph signature = new Paragraph();
			signature.setLeading(0, 2);
			signature.setFont(font5);
			signature.add("Signature");

			tableSignature.addCell(legalEntity);
			tableSignature.addCell(legalEntityMerch);
			tableSignature.addCell(legalEntityMobi);

			tableSignature.addCell(representative);
			tableSignature.addCell(representativeMerch);
			tableSignature.addCell(representativeMobi);

			tableSignature.addCell(title);
			tableSignature.addCell(titleMerch);
			tableSignature.addCell(titleMobi);

			tableSignature.addCell(signature);

			String signpathMerchant = "";
			String signpathSalesPerson = "";

			if (quote.getUserId() != null && quote.getQuotationAcceptance() != null) {
				// Get Customer Signature
				Image imagesign = Image.getInstance(env.getProperty("signature.basePath") + quote.getUserId() + ".png");
				imagesign.setAbsolutePosition(75f, 75f);
				imagesign.setAlignment(Element.ALIGN_LEFT);
				tableSignature.addCell(imagesign);
			} else {
				tableSignature.addCell("");
			}
			if (quote.getSalesPerson() != null && quote.getSalesPerson().getSignature() != null) {
				// Get SalesPerson Signature
				Image imagesignsp = Image.getInstance(
						env.getProperty("signature.basePath") + quote.getSalesPerson().getPhone() + ".png");

				imagesignsp.setAbsolutePosition(75f, 75f);
				imagesignsp.setAlignment(Element.ALIGN_LEFT);
				tableSignature.addCell(imagesignsp);
			} else {
				tableSignature.addCell("");
			}

			tableSignature.addCell(date);
			tableSignature.addCell(signedDateMerch);
			tableSignature.addCell(signedDateSalesMan);

			tableSignature.addCell(ipAddress);
			tableSignature.addCell(ipMerch);
			tableSignature.addCell(ipSalesMan);

			pdfDoc.add(tableSignature);

			// Signature Old
			// Paragraph tnccus1 = new Paragraph();
			// tnccus1.setLeading(0, 2);
			// tnccus1.setFont(font5);
			//
			// StringBuilder stringBuilder = new StringBuilder();
			// stringBuilder.append("For & on behalf of the Merchant \n Name of
			// Company/Business: ");
			// stringBuilder.append(quote.getCompanyName() == null ? "" :
			// quote.getCompanyName());
			// stringBuilder.append("\nAuthorised Signatory Name: ");
			// ;
			// stringBuilder.append(
			// quote.getQuotationAcceptance() == null ? "" :
			// quote.getQuotationAcceptance().getNameAsPerIC());
			// stringBuilder.append("\nNRIC No: ");
			// ;
			// stringBuilder
			// .append(quote.getQuotationAcceptance() == null ? "" :
			// quote.getQuotationAcceptance().getIcNumber());
			// stringBuilder.append("\nDate: ");
			// ;
			// stringBuilder.append(
			// quote.getQuotationAcceptance() == null ? "" :
			// quote.getQuotationAcceptance().getCreatedOn());
			// stringBuilder.append("\nIP Address: ");
			// ;
			// stringBuilder.append(
			// quote.getQuotationAcceptance() == null ? "" :
			// quote.getQuotationAcceptance().getIpAddress());

			/*
			 * tnccus1.add("For & on behalf of the Merchant \r\nName of Company/Business: "
			 * + (quote.getCompanyName() == null ? "" : quote.getCompanyName()) +
			 * "\r\nAuthorised Signatory Name: " + quote.getQuotationAcceptance() == null ?
			 * "" : quote.getQuotationAcceptance().getNameAsPerIC() + "\r\nNRIC No: " +
			 * quote.getQuotationAcceptance() == null ? "" :
			 * quote.getQuotationAcceptance().getIcNumber() + "\r\nDate: " +
			 * quote.getQuotationAcceptance() == null ? "" :
			 * quote.getQuotationAcceptance().getCreatedOn() + "\r\nIP Address: " +
			 * quote.getQuotationAcceptance() == null ? "" :
			 * quote.getQuotationAcceptance().getIpAddress() + "");
			 */
			// tnccus1.add(stringBuilder.toString());
			// pdfDoc.add(tnccus1);
			// Paragraph consent = new Paragraph();
			// consent.setLeading(0, 2);
			// consent.setFont(new Font(FontFamily.HELVETICA, 8, Font.BOLD,
			// BaseColor.BLUE));
			//
			// consent.add("Date:\t\tRM"
			// + (quote.getQuotationAcceptance() == null ? "" :
			// quote.getQuotationAcceptance().getCreatedOn())
			// + "IP Address:\t\t"
			// + (quote.getQuotationAcceptance() == null ? "" :
			// quote.getQuotationAcceptance().getIpAddress()));
			// consent.add("Name:\t\t"
			// + (quote.getQuotationAcceptance() == null ? "" :
			// quote.getQuotationAcceptance().getNameAsPerIC())
			// + "IC/Passport:\t\t"
			// + (quote.getQuotationAcceptance() == null ? "" :
			// quote.getQuotationAcceptance().getIcNumber()));
			// consent.setAlignment(Element.ALIGN_RIGHT);
			// pdfDoc.add(consent);
			// endregion

			pdfDoc.close();
			logger.info("PDF Created - Success");
			return 0;

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Stacktrace : " + e);
			return 1;
		}

	}

	// rk added

	private int generateNewProfoma(String pdfFilePath, Quotation quote, HttpServletRequest req) {

		try {


			String currdate = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());


			String invoiceno = "PRO" + quote.getId() + currdate;

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");// here u can mention whatever the
																					// format u want like dd/mm/yyyy etc

			String date = LocalDate.now().format(formatter);

			Calendar calnder = Calendar.getInstance();

			String createdon = quote.getCreatedOn().format(formatter);

			File fPDFFilePath = new File(pdfFilePath);

			if (!fPDFFilePath.exists()) {
				fPDFFilePath.mkdirs();
			}

			String fileStorePath = String.format("%s/Profoma_%s.pdf", fPDFFilePath, quote.getId());

			Font normalFont = new Font(FontFamily.HELVETICA, 8);
			Font boldFont = new Font(FontFamily.HELVETICA, 8, Font.BOLD);
			BaseColor bc = new BaseColor(0, 91, 170);
			// BaseColor bc = new BaseColor(0, 114, 168);
			Font merchantnamefont = new Font(FontFamily.HELVETICA, 15, Font.BOLD, bc);

			// Creating a PdfWriter
			// String dest = "C:/ocsweb/quotationrough.pdf";
			Document document = new Document();
			document.setPageSize(PageSize.A4);
			PdfWriter.getInstance(document, new FileOutputStream(fileStorePath));
			document.open();

			PdfPTable pdfsign = new PdfPTable(2);
			// below widths are for each cell how much u want to allocate
			// float[] widthsign = new float[] { 9f, 30f };
			// pdfsign.setWidths(widthsign);
			pdfsign.setWidthPercentage(100f);

			PdfPCell cellxysign = new PdfPCell();
			PdfPCell celladress = new PdfPCell();

			Image image = Image.getInstance(getClass().getClassLoader().getResource("/assets/images/mobi.png"));

			// image width,height
			image.scaleAbsolute(119.00f, 57.00f);

			// for alignment
			// image.setAlignment(image.RIGHT);
			image.setAlignment(Element.ALIGN_RIGHT);

			cellxysign.disableBorderSide(15);
			cellxysign.addElement(image);

			Phrase phraseCompany = new Phrase();
			phraseCompany.add(new Chunk("", normalFont));
			phraseCompany.add(new Chunk("MOBI ASIA SDN BHD", boldFont));
			phraseCompany.add(Chunk.NEWLINE);

//			phraseCompany.add(new Chunk("Suite #07-01, Wisma UOA Damansara II, No. 6,", normalFont));
//			phraseCompany.add(Chunk.NEWLINE);
//
//			phraseCompany.add(new Chunk("Changkat Semantan,", normalFont));
//			phraseCompany.add(Chunk.NEWLINE);
//
//			phraseCompany.add(new Chunk("Damansara Heights,", normalFont));
//			phraseCompany.add(Chunk.NEWLINE);
//
//			phraseCompany.add(new Chunk("Kuala Lumpur 50490 MY.", normalFont));
//			phraseCompany.add(Chunk.NEWLINE);
//
//			phraseCompany.add(new Chunk("Contact No: 60 3 2714 5308", normalFont));
//			phraseCompany.add(Chunk.NEWLINE);
//
//			phraseCompany.add(new Chunk("Contact Email: finance@gomobi.io", normalFont));
//			phraseCompany.add(Chunk.NEWLINE);
//
//			phraseCompany.add(new Chunk("Web: www.gomobi.io", normalFont));
//			phraseCompany.add(Chunk.NEWLINE);
//
//			phraseCompany.add(new Chunk("Trade Reg. Nr. 1105429-U", normalFont));
//			phraseCompany.add(Chunk.NEWLINE);
			
			
			phraseCompany.add(new Chunk("17-109, Equatorial Plaza,", normalFont));
            phraseCompany.add(Chunk.NEWLINE);



           /*
             * phraseCompany.add(new Chunk("Changkat Semantan,", normalFont));
             * phraseCompany.add(Chunk.NEWLINE);
             */



           phraseCompany.add(new Chunk("Jalan Sultan Ismail,", normalFont));
            phraseCompany.add(Chunk.NEWLINE);



           phraseCompany.add(new Chunk("Kuala Lumpur 50250  MY.", normalFont));
            phraseCompany.add(Chunk.NEWLINE);



           phraseCompany.add(new Chunk("Contact No: 60 3 2714 5308", normalFont));
            phraseCompany.add(Chunk.NEWLINE);



           phraseCompany.add(new Chunk("Contact Email: finance@gomobi.io", normalFont));
            phraseCompany.add(Chunk.NEWLINE);



           phraseCompany.add(new Chunk("Web: www.gomobi.io", normalFont));
            phraseCompany.add(Chunk.NEWLINE);



           /* phraseCompany.add(new Chunk("Trade Reg. Nr. 1105429-U", normalFont)); */
            phraseCompany.add(new Chunk("Trade Reg. Nr.201401029343 (1105429-U)", normalFont));
            phraseCompany.add(Chunk.NEWLINE);

			celladress.disableBorderSide(15);
			celladress.addElement(phraseCompany);

			pdfsign.addCell(celladress);
			pdfsign.addCell(cellxysign);

			document.add(pdfsign);

			// Phrase merchantname = new Phrase();
			// merchantname.add(new Chunk("PROFOMA INVOICE", merchantnamefont));

			Paragraph merchantname = new Paragraph("PROFOMA INVOICE", merchantnamefont);

			merchantname.setSpacingBefore(1);
			merchantname.setSpacingAfter(1);

			document.add(merchantname);

			PdfPTable pdf2 = new PdfPTable(2);
			pdf2.setWidthPercentage(100f);

			PdfPCell cell21 = new PdfPCell();
			PdfPCell cell22 = new PdfPCell();

			cell21.disableBorderSide(15);
			cell22.disableBorderSide(15);
			Phrase merchantadress = new Phrase();
			merchantadress.add(new Chunk("", normalFont));
			merchantadress.add(new Chunk("BILL TO", boldFont));
			merchantadress.add(Chunk.NEWLINE);


			merchantadress.add(new Chunk(String.format("%s,", quote.getCompanyName()), boldFont));

			merchantadress.add(Chunk.NEWLINE);

			merchantadress.add(new Chunk(
					String.format("%s, %s, Malaysia, %s,", quote.getAddress(), quote.getCity(), quote.getPostalCode()),
					normalFont));
			merchantadress.add(Chunk.NEWLINE);


			merchantadress.add(new Chunk(String.format("%s,", quote.getContact().getLastName()), normalFont));

		
			merchantadress.add(Chunk.NEWLINE);

			merchantadress
					.add(new Chunk(String.format("Phone Number: 06 %s,", quote.getUserId().toString()), normalFont));
			merchantadress.add(Chunk.NEWLINE);


			merchantadress.add(new Chunk(String.format("Email Id: %s.", quote.getContact().getEmail()), normalFont));
			merchantadress.add(Chunk.NEWLINE);


			cell21.addElement(merchantadress);
			pdf2.addCell(cell21);

			Phrase invoice = new Phrase();
			invoice.add(new Chunk("", normalFont));
			invoice.add(new Chunk("INVOICE NO    ", boldFont));

			invoice.add(new Chunk(invoiceno, normalFont));

			invoice.add(Chunk.NEWLINE);

			invoice.add(new Chunk("DATE                ", boldFont));
			invoice.add(new Chunk(date, normalFont));
			invoice.add(Chunk.NEWLINE);

			calnder.add(Calendar.DATE, 3);


			invoice.add(new Chunk("DUE DATE       ", boldFont));

			invoice.add(new Chunk(new SimpleDateFormat("dd.MM.yyyy").format(calnder.getTime()), normalFont));
			invoice.add(Chunk.NEWLINE);

			Paragraph para22 = new Paragraph();
			para22.add(invoice);
			// para22.setAlignment(Element.ALIGN_RIGHT);

			cell22.addElement(para22);

			cell22.setPaddingLeft(95);

			pdf2.addCell(cell22);

			pdf2.setSpacingAfter(5);

			document.add(pdf2);

			LineSeparator bluesep = new LineSeparator(merchantnamefont);
			document.add(bluesep);

			PdfPTable amounttable = new PdfPTable(4);

			amounttable.setSpacingBefore(30);

			float[] widthamount = new float[] { 30f, 16f, 16f, 16f };
			amounttable.setWidths(widthamount);
			amounttable.setWidthPercentage(100f);

			Font font = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);

			PdfPCell cell = new PdfPCell(new Phrase("ACTIVITY", font));


			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setPaddingLeft(40f);
			cell.setPaddingTop(0f);

			BaseColor bcc = new BaseColor(0, 91, 170);
			cell.setBackgroundColor(bcc);
			cell.setBorder(0);
			cell.setMinimumHeight(18f);
			amounttable.addCell(cell);


			Font otherTableFont = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.WHITE);

			PdfPCell QTY = new PdfPCell(new Phrase("QTY", otherTableFont));
			QTY.setBackgroundColor(bcc);
			QTY.setBorder(0);
			QTY.setHorizontalAlignment(Element.ALIGN_RIGHT);
			QTY.setVerticalAlignment(Element.ALIGN_MIDDLE);
			amounttable.addCell(QTY);

			PdfPCell RATE = new PdfPCell(new Phrase("RATE", otherTableFont));
			RATE.setBackgroundColor(bcc);
			RATE.setBorder(0);
			RATE.setHorizontalAlignment(Element.ALIGN_RIGHT);
			RATE.setVerticalAlignment(Element.ALIGN_MIDDLE);
			amounttable.addCell(RATE);

			PdfPCell AMOUNT = new PdfPCell(new Phrase("AMOUNT", otherTableFont));
			AMOUNT.setBackgroundColor(bcc);
			AMOUNT.setBorder(0);
			AMOUNT.setHorizontalAlignment(Element.ALIGN_RIGHT);
			AMOUNT.setVerticalAlignment(Element.ALIGN_MIDDLE);
			amounttable.addCell(AMOUNT);


			Font tddatefont = new Font(FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
			Font tdtextfont = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);

			PdfPCell cell31 = new PdfPCell(new Phrase(createdon, tddatefont));

			cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell31.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell31.disableBorderSide(15);
			cell31.setPaddingLeft(40f);
			cell31.setPaddingTop(0f);

			cell31.setMinimumHeight(18f);

			// tdCellStyle(cell31);
			amounttable.addCell(cell31);

			PdfPCell cell32 = new PdfPCell(new Phrase(" ", tdtextfont));
			cell32.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell32.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell32.disableBorderSide(15);
			cell32.setPaddingLeft(40f);
			cell32.setPaddingTop(0f);

			// tdCellStyle(cell32);
			amounttable.addCell(cell32);

			PdfPCell cell33 = new PdfPCell(new Phrase(" ", tdtextfont));
			tdCellStyle(cell33);
			amounttable.addCell(cell33);

			PdfPCell cell34 = new PdfPCell(new Phrase(" ", tdtextfont));
			tdCellStyle(cell34);
			amounttable.addCell(cell34);

			double subtotal = 0.0;

			for (OrderLines orderLines : quote.getOrderLines()) {

				Phrase p41 = new Phrase();
				p41.add(new Chunk(orderLines.getProduct().getProductType(), tddatefont));
				p41.add(Chunk.NEWLINE);
				p41.add(new Chunk(orderLines.getProduct().getProductName(), tdtextfont));

				PdfPCell cell41 = new PdfPCell(p41);

				cell41.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell41.setVerticalAlignment(Element.ALIGN_BOTTOM);
				cell41.disableBorderSide(15);
				cell41.setPaddingLeft(40f);
				cell41.setPaddingTop(0f);
				// tdCellStyle(cell41);
				amounttable.addCell(cell41);

				PdfPCell cell42 = new PdfPCell(new Phrase(String.valueOf(orderLines.getQuantity()), tdtextfont));

				cell42.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell42.setVerticalAlignment(Element.ALIGN_BOTTOM);
				cell42.disableBorderSide(15);
				cell42.setPaddingLeft(40f);
				cell42.setPaddingTop(0f);

				// tdCellStyle(cell42);
				amounttable.addCell(cell42);

				PdfPCell cell43 = new PdfPCell(new Phrase(
						String.valueOf(String.format("%.2f", (float) orderLines.getProduct().getUnitPrice())),
						tdtextfont));
				cell43.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell43.setVerticalAlignment(Element.ALIGN_BOTTOM);
				cell43.disableBorderSide(15);
				cell43.setPaddingLeft(40f);
				cell43.setPaddingTop(0f);

				// tdCellStyle(cell43);
				amounttable.addCell(cell43);

				PdfPCell cell44 = new PdfPCell(new Phrase(
						String.valueOf(String.format("%.2f",
								(float) orderLines.getQuantity() * orderLines.getProduct().getUnitPrice())),
						tdtextfont));
				cell44.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell44.setVerticalAlignment(Element.ALIGN_BOTTOM);
				cell44.disableBorderSide(15);
				cell44.setPaddingLeft(40f);
				cell44.setPaddingTop(0f);
				// tdCellStyle(cell44);
				amounttable.addCell(cell44);
				subtotal = subtotal + orderLines.getQuantity() * orderLines.getProduct().getUnitPrice();
			}

			amounttable.setSpacingAfter(40);

			document.add(amounttable);

			document.add(new DottedLineSeparator());


			Font balduefont = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.LIGHT_GRAY);
			Font myrfont = new Font(FontFamily.HELVETICA, 12, Font.BOLD, new BaseColor(0, 91, 170));


			double discount = quote.getDiscountPrice();
			double totaldue = subtotal - discount;


			float[] widthFloat = new float[] { 300f, 90f };
			PdfPTable amttable = new PdfPTable(widthFloat);
			amttable.setWidthPercentage(100);
			amttable.setLockedWidth(false);
			PdfPCell amtcell = new PdfPCell(new Phrase(new Chunk("SUB TOTAL\nDISCOUNT\nTOTAL DUE", balduefont)));

			PdfPCell amtcell2 = new PdfPCell(new Phrase(String.valueOf(String.format("%s\n%s\n%s",
					service.FormatAmountFromDoubleToString(subtotal), service.FormatAmountFromDoubleToString(discount),
					service.FormatAmountFromDoubleToString(totaldue))), myrfont));


			// PdfPCell amtcell2 = new PdfPCell(new
			// Phrase(String.valueOf(subtotal)+"\n"+String.valueOf(discount)+"\n"+String.valueOf(totaldue),myrfont));
			amtcell.disableBorderSide(15);
			amtcell2.disableBorderSide(15);

			amtcell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
			amtcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			amtcell.setPaddingLeft(180f);

			amttable.addCell(amtcell);
			amttable.addCell(amtcell2);
			amttable.setSpacingAfter(150);
			document.add(amttable);

			Phrase p71 = new Phrase();
			p71.add(new Chunk("Payment Instructions :-", tdtextfont));

			p71.add(Chunk.NEWLINE);
			p71.add(new Chunk(
					"1. Remittance by cheque should be crossed 'A/C PAYEE ONLY' and payable to 'Mobi Asia Sdn Bhd (Formerly Known As Mobiversa SdnBhd)'",
					tdtextfont));

			p71.add(Chunk.NEWLINE);
			p71.add(new Chunk("2. Remittance by IBG deposit directly into :-", tdtextfont));

			p71.add(Chunk.NEWLINE);
			p71.add(new Chunk("Bank Name : CIMB BANK BERHAD", tdtextfont));

			p71.add(Chunk.NEWLINE);
			p71.add(new Chunk("Account No : 8007 810 373", tdtextfont));

			p71.add(Chunk.NEWLINE);
			p71.add(new Chunk("- This is auto generated invoice. No signatory required -", tdtextfont));

			document.add(p71);

			document.close();

			return 0;
		}

		catch (Exception e) {
			e.printStackTrace();
			logger.error("Stacktrace : " + e);
			return 1;
		}
	}

	// rk
	private static PdfPCell createTHCell(String text) {
		// font

		Font font = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLUE);

		// create cell
		PdfPCell cell = new PdfPCell(new Phrase(text, font));

		// set style
		thCellStyle(cell);
		return cell;
	}

	// rk
	public static void tdCellStyle(PdfPCell cell) {
		// alignment
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

		// padding
		cell.setPaddingLeft(40f);
		cell.setPaddingTop(0f);

		// border
		cell.disableBorderSide(15);

		// height
		cell.setMinimumHeight(18f);
	}

	// rk

	public static void thCellStyle(PdfPCell cell) {
		// alignment
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

		// padding
		cell.setPaddingLeft(40f);
		cell.setPaddingTop(0f);

		// background color
		BaseColor bc = new BaseColor(173, 216, 230);
		cell.setBackgroundColor(bc);

		// border
		cell.setBorder(0);
		// cell.setBorderWidthBottom(1);
		// cell.setBorderColorBottom(BaseColor.GRAY);

		// height
		cell.setMinimumHeight(18f);
	}

	// Vignesh Selvam
	// new Issue Quotation page

	@Override
	public Object IssueQuotationByQuotationId(QuotationRequestData quotationRequestData, Quotation quotation,
			HttpServletRequest req) {

		String originalQuotationPath = Constants.getQuotationPath();
		// Check Original File Exist
		String originalFilePath = String.format("%s/%s/Quotation_%s.pdf", originalQuotationPath,
				quotationRequestData.getQuotationId(), quotationRequestData.getQuotationId());
		File originalFile = new File(originalFilePath);
		if (originalFile.exists()) {

			// Original File Available

			logger.info("originalFile available");

			String issuedPath = String.format("%s/%s/issued", originalQuotationPath, quotation.getId());
			File issueFile = new File(issuedPath);

			if (!issueFile.exists()) {
				issueFile.mkdirs();
			}

			String sourcePath = originalFilePath;
			String targetPath = String.format("%s/%s/issued/Quotation_%s_%s.pdf", originalQuotationPath,
					quotation.getId(), quotation.getId(),
					(issueFile.list().length > 0 ? String.valueOf(issueFile.list().length + 1) : "1"));

			logger.info("sourcePath -> " + sourcePath);
			logger.info("targetPath -> " + targetPath);

			try {
				Files.copy(Paths.get(sourcePath), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);

				String issuedQuotationResourcePath = String.format("%s%s/issued/Quotation_%s_%s.pdf",
						Constants.getIssueQuotationResourcePath(), quotation.getId(), quotation.getId(),
						(issueFile.list().length > 0 ? String.valueOf(issueFile.list().length) : "1"));
				String issuedQuotationResourceName = String.format("Quotation_%s_%s.pdf", quotation.getId(),
						(issueFile.list().length > 0 ? String.valueOf(issueFile.list().length) : "1"));

				// Add email block below

				Object response = service.insertIssuedQuotation(issuedQuotationResourcePath,
						issuedQuotationResourceName, quotation.getId());

				return response;

			} catch (IOException e) {
				logger.error("Stacktrace : ", e);
				return new CommonResponseData("0001", "Unable to issue quotations, please try again", null);
			}

		} else {
			originalQuotationPath = String.format("%s/%s/", originalQuotationPath, quotation.getId());
			int flag = generateNewQuotation(originalQuotationPath, quotation, req);

			if (flag == 0) {
				String quotationFilePath = String.format("%s/%s/Quotation_%s.pdf", Constants.getQuotationResourcePath(),
						quotation.getId(), quotation.getId());

				String quotationName = String.format("Quotation_%s.pdf", quotation.getId());

				// Insert image into
				insertImageInto(quotationFilePath, quotationName, quotation.getId());

				String issuedPath = String.format("%sissued", originalQuotationPath);
				File issueFile = new File(issuedPath);

				if (!issueFile.exists()) {
					issueFile.mkdirs();
				}

				String sourcePath = originalFilePath;
				String targetPath = String.format("%sissued/Quotation_%s_%s.pdf", originalQuotationPath,
						quotation.getId(),
						(issueFile.list().length > 0 ? String.valueOf(issueFile.list().length + 1) : "1"));

				logger.info("sourcePath -> " + sourcePath);
				logger.info("targetPath -> " + targetPath);

				try {
					Files.copy(Paths.get(sourcePath), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);

					String issuedQuotationResourcePath = String.format("%s%s/issued/Quotation_%s_%s.pdf",
							Constants.getIssueQuotationResourcePath(), quotation.getId(), quotation.getId(),
							(issueFile.list().length > 0 ? String.valueOf(issueFile.list().length) : "1"));
					String issuedQuotationResourceName = String.format("Quotation_%s_%s.pdf", quotation.getId(),
							(issueFile.list().length > 0 ? String.valueOf(issueFile.list().length) : "1"));

					// Add email block below

					Object response = service.insertIssuedQuotation(issuedQuotationResourcePath,
							issuedQuotationResourceName, quotation.getId());

					return response;
				} catch (IOException e) {
					logger.error("Stacktrace : ", e);
					return new CommonResponseData("0001", "Unable to issue quotations, please try again", null);
				}

			} else {
				logger.error("There is a issue in Generating Quotation");
				return new CommonResponseData("0001", "Something went wrong, please try again", null);
			}
		}
	}

	// rk added
	@Override
	public Object IssueProfomaByQuotationId(QuotationRequestData quotationRequestData, Quotation quotation,
			HttpServletRequest req) {

		String originalQuotationPath = Constants.getProfomaPath();
		// Check Original File Exist
		String originalFilePath = String.format("%s/%s/Profoma_%s.pdf", originalQuotationPath,
				quotationRequestData.getQuotationId(), quotationRequestData.getQuotationId());
		File originalFile = new File(originalFilePath);
		if (originalFile.exists()) {

			// Original File Available

			logger.info("originalFile available");

			String issuedPath = String.format("%s/%s/issued", originalQuotationPath, quotation.getId());
			File issueFile = new File(issuedPath);

			if (!issueFile.exists()) {
				issueFile.mkdirs();
			}

			String sourcePath = originalFilePath;
			String targetPath = String.format("%s/%s/issued/Profoma_%s_%s.pdf", originalQuotationPath,
					quotation.getId(), quotation.getId(),
					(issueFile.list().length > 0 ? String.valueOf(issueFile.list().length + 1) : "1"));

			logger.info("sourcePath -> " + sourcePath);
			logger.info("targetPath -> " + targetPath);

			try {
				Files.copy(Paths.get(sourcePath), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);

				String issuedQuotationResourcePath = String.format("%s%s/issued/Profoma_%s_%s.pdf",
						Constants.getIssueProfomaResourcePath(), quotation.getId(), quotation.getId(),
						(issueFile.list().length > 0 ? String.valueOf(issueFile.list().length) : "1"));
				String issuedQuotationResourceName = String.format("Profoma_%s_%s.pdf", quotation.getId(),
						(issueFile.list().length > 0 ? String.valueOf(issueFile.list().length) : "1"));

				// Add email block below

				Object response = service.insertIssuedProfoma(issuedQuotationResourcePath, issuedQuotationResourceName,
						quotation.getId());

				return response;

			} catch (IOException e) {
				logger.error("Stacktrace : ", e);
				return new CommonResponseData("0001", "Unable to send profoma, please try again", null);
			}

		} else {
			originalQuotationPath = String.format("%s/%s/", originalQuotationPath, quotation.getId());
			int flag = generateNewProfoma(originalQuotationPath, quotation, req);

			if (flag == 0) {
				String quotationFilePath = String.format("%s/%s/Profoma_%s.pdf", Constants.getProfomaResourcePath(),
						quotation.getId(), quotation.getId());

				String quotationName = String.format("Profoma_%s.pdf", quotation.getId());

				// Insert image into
				SaveProfomoPath(quotationFilePath, quotationName, quotation.getId());

				String issuedPath = String.format("%sissued", originalQuotationPath);
				File issueFile = new File(issuedPath);

				if (!issueFile.exists()) {
					issueFile.mkdirs();
				}

				String sourcePath = originalFilePath;
				String targetPath = String.format("%sissued/Profoma_%s_%s.pdf", originalQuotationPath,
						quotation.getId(),
						(issueFile.list().length > 0 ? String.valueOf(issueFile.list().length + 1) : "1"));

				logger.info("sourcePath -> " + sourcePath);
				logger.info("targetPath -> " + targetPath);

				try {
					Files.copy(Paths.get(sourcePath), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);

					String issuedQuotationResourcePath = String.format("%s%s/issued/Profoma_%s_%s.pdf",
							Constants.getIssueProfomaResourcePath(), quotation.getId(), quotation.getId(),
							(issueFile.list().length > 0 ? String.valueOf(issueFile.list().length) : "1"));
					String issuedQuotationResourceName = String.format("Profoma_%s_%s.pdf", quotation.getId(),
							(issueFile.list().length > 0 ? String.valueOf(issueFile.list().length) : "1"));

					// Add email block below

					Object response = service.insertIssuedProfoma(issuedQuotationResourcePath,
							issuedQuotationResourceName, quotation.getId());

					return response;
				} catch (IOException e) {
					logger.error("Stacktrace : ", e);
					return new CommonResponseData("0001", "Unable to send profoma, please try again", null);
				}

			} else {
				logger.error("There is a issue in Generating Profoma");
				return new CommonResponseData("0001", "Something went wrong, please try again", null);
			}
		}
	}

	@Override
	public Object acceptQuotation(String quotationId) {

		return null;
	}

	@Override
	public String GenerateWL(String orderId) throws DocumentException, IOException {

		try {

			Order order = service.GetOrderByID(orderId);
			List<OrderLines> odList = service.GetOrderLineByQuotation(order.getQuotation().getId());

			logger.info("OrderiD - " + order.getId());
			logger.info("QuotationID - " + order.getQuotation().getId());
			logger.info("Order Line Count - " + odList.size());

			String pdfFilePath = Constants.getWelcomeLetterPath();
			pdfFilePath = String.format("%s/%s/", pdfFilePath, orderId);

			ClassLoader classLoaderBoost = getClass().getClassLoader();

			File fPDFFilePath = new File(pdfFilePath);

			if (!fPDFFilePath.exists()) {
				fPDFFilePath.mkdirs();
			}

			Document pdfDoc = new Document();
			pdfDoc.setPageSize(PageSize.A4); // SET MARGINS

			Font font5 = new Font(FontFamily.HELVETICA, 8);
			Font fontWhite = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(255, 255, 255));
			Font boldFontWhite = new Font(FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(255, 255, 255));
			Font fontmoh = new Font(FontFamily.HELVETICA, 6);
			Font fontmoh4 = new Font(FontFamily.HELVETICA, 6);
			Font normalFont = new Font(FontFamily.HELVETICA, 8);
			Font boldFont = new Font(FontFamily.HELVETICA, 10, Font.BOLD);
			Font fontHead = new Font(FontFamily.HELVETICA, 14);
			Font font7 = new Font(FontFamily.HELVETICA, 9);
			Font linkTextFont = new Font(FontFamily.HELVETICA, 8, Font.ITALIC, new BaseColor(51, 51, 255));

			BaseFont f_cn = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			BaseFont Merchfont = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, false);
			Font fontTinyItalic = new Font(FontFamily.HELVETICA, 30, Font.BOLDITALIC, BaseColor.BLACK);

			String fileStorePath = String.format("%s/WelcomeLetter-%s.pdf", fPDFFilePath, orderId);

			OutputStream fos = new FileOutputStream(fileStorePath);
			PdfWriter writer = PdfWriter.getInstance(pdfDoc, fos);

			pdfDoc.open();

			logger.info("before mobiPath ");
			URL mobiPath = getClass().getClassLoader().getResource("/assets/images/mobi.png");
			URL mobiWhite = getClass().getClassLoader().getResource("/assets/images/mobi.png");

			logger.info("mobiPath : " + mobiPath);
			Image image = Image.getInstance(mobiPath);
			image.scaleAbsolute(70f, 70f);
			image.setAlignment(Element.ALIGN_LEFT);

			Image image1 = Image.getInstance(mobiWhite);
			image1.scaleAbsolute(70f, 70f);
			image1.setAlignment(Element.ALIGN_RIGHT);

			PdfPTable resimtable = new PdfPTable(3);
			resimtable.setWidthPercentage(100f);

			PdfPCell cell = new PdfPCell();
			cell.setFixedHeight(40f);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.addElement(image);
			resimtable.addCell(cell);
			PdfPCell cella = new PdfPCell();
			Paragraph xstring = new Paragraph();
			xstring.setAlignment(Element.ALIGN_CENTER);
			xstring.add("WELCOME LETTER");
			xstring.add(Chunk.NEWLINE);
			xstring.add("Document Reference :(Mobi/U/" + orderId + ")");
			xstring.setFont(font5);
			cella.addElement(xstring);
			cella.setHorizontalAlignment(Element.ALIGN_CENTER);
			cella.setBorder(Rectangle.NO_BORDER);
			resimtable.addCell(cella);
			PdfPCell cell1 = new PdfPCell();
			cell1.setFixedHeight(40f);
			cell1.addElement(new Chunk("\n"));
			cell1.setBorder(Rectangle.NO_BORDER);
			resimtable.addCell(cell1);
			resimtable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			pdfDoc.add(resimtable);
			pdfDoc.add(new Chunk("\n"));

			Paragraph text3 = new Paragraph();
			text3.setLeading(0, 2);
			text3.setFont(font5);

			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String currentDate = now.format(formatter);

			text3.add(currentDate);
			pdfDoc.add(new Chunk("\n"));
			pdfDoc.add(text3);
			Paragraph text4 = new Paragraph();
			text4.setLeading(0, 2);
			text4.setFont(font5);

			String address = "";
			if (order.getQuotation().getCompanyName() != null) {
				address = "" + order.getQuotation().getContact().getFirstName() + "\n"
						+ order.getQuotation().getAddress() + "\n" + order.getQuotation().getCity() + ", "
						+ order.getQuotation().getState() + ", " + "\n" + order.getQuotation().getCountry();

			}
			text4.add(Chunk.NEWLINE);
			text4.add(order.getQuotation().getCompanyName());
			text4.add(Chunk.NEWLINE);
			text4.add(address);
			text4.add(Chunk.NEWLINE);
			text4.add(Chunk.NEWLINE);

			text4.add("RE: Card(s) Payment Acceptance Programme");
			text4.add(Chunk.NEWLINE);
			text4.add(
					"We are pleased to inform you that your application for Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) Card(s) Payment Acceptance program has been approved based on information provided to us in Schedule 1 and Merchant Agreement executed by you.");
			text4.add("Please find the following card acceptance procedures and fulfil of the following proposal:-");
			text4.add(Chunk.NEWLINE);
			text4.add("MERCHANT INFORMATION");
			text4.add(Chunk.NEWLINE);

			pdfDoc.add(text4);

			PdfPTable discounttab = new PdfPTable(4);
			discounttab.getDefaultCell().setPaddingBottom(3f);
			discounttab.getDefaultCell().disableBorderSide(Rectangle.NO_BORDER);
			float[] widthsdiscount = new float[] { 8f, 1.5f, 1.5f, 0.5f };
			discounttab.setWidths(widthsdiscount);
			discounttab.setWidthPercentage(100f);

			Paragraph p5 = new Paragraph("\n");

			pdfDoc.add(p5);
			Paragraph tnc = new Paragraph();
			tnc.setLeading(0, 2);
			String tncdynamic = "";

			if (order.getQuotation().getOrderType() != "Topup") {
				int SplitCount = 0;
				for (OrderLines od : odList) {
					if (od.getProduct().getProductType().equals("EZYSPLIT")) {
						SplitCount++;
					}
				}
				// MDR TABLE FOR OTHER PRODUCTS

				for (OrderLines od : odList) {
					if (od.getQuotationMDRRate() != null) {

						Paragraph pmdr = new Paragraph();
						pmdr.setFont(boldFont);
						pmdr.add(od.getQuotationMDRRate().getProductType());
						pdfDoc.add(pmdr);
						pdfDoc.add(p5);
						PdfPTable mdrTable = new PdfPTable(6);
						float[] mdrWidths = new float[] { 2f, 2f, 2f, 2f, 2f, 2f };
						mdrTable.setWidths(mdrWidths);
						mdrTable.setWidthPercentage(100);
						mdrTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
						mdrTable.getDefaultCell().disableBorderSide(15);
						mdrTable.getDefaultCell().enableBorderSide(2);
						mdrTable.getDefaultCell().setBorderColor(new BaseColor(200, 224, 230));
						mdrTable.getDefaultCell().setPadding(3f);
						mdrTable.getDefaultCell().setBackgroundColor(new BaseColor(0, 91, 170));
						// mdrTable.addCell(new Phrase("Product Type", fontWhite));

						mdrTable.addCell(new Phrase("Card Brand", fontWhite));
						mdrTable.addCell(new Phrase("Local Credit", fontWhite));
						mdrTable.addCell(new Phrase("Local Debit", fontWhite));
						mdrTable.addCell(new Phrase("Foreign Credit", fontWhite));
						mdrTable.addCell(new Phrase("Foreign Debit", fontWhite));
						mdrTable.addCell(new Phrase("Settlement", fontWhite));
						mdrTable.getDefaultCell().setBackgroundColor(new BaseColor(255, 255, 255));

						discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);
						if (odList.size() > 0) {
							for (int iloop = 0; iloop < 3; iloop++) {
								if (iloop == 0) {
									discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);
									// mdrTable.addCell(new Phrase(od.getQuotationMDRRate().getProductName(),
									// fontmoh4));
									// mdrTable.addCell(new Phrase(od.getQuotationMDRRate().getProductType(),
									// fontmoh4));
									mdrTable.addCell(new Phrase("MasterCard", fontmoh4));
									discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
									mdrTable.addCell(new Phrase(
											String.valueOf(od.getQuotationMDRRate().getLoc_Credit_Merch_Master()) + "%",
											fontmoh4));
									mdrTable.addCell(new Phrase(
											String.valueOf(od.getQuotationMDRRate().getLoc_Debit_Merch_Master()) + "%",
											fontmoh4));
									mdrTable.addCell(new Phrase(
											String.valueOf(od.getQuotationMDRRate().getFor_Credit_Merch_Master()) + "%",
											fontmoh4));
									mdrTable.addCell(new Phrase(
											String.valueOf(od.getQuotationMDRRate().getFor_Debit_Merch_Master()) + "%",
											fontmoh4));
									if (od.getQuotationMDRRate().getProductSettlement() != 0) {
										mdrTable.addCell(new Phrase(
												"T + " + String
														.valueOf(od.getQuotationMDRRate().getProductSettlement()),
												fontmoh4));
									} else {
										mdrTable.addCell(new Phrase("", fontmoh4));
									}
								} else if (iloop == 1) {
									discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);
									// mdrTable.addCell(new Phrase(od.getQuotationMDRRate().getProductName(),
									// fontmoh4));
									// mdrTable.addCell(new Phrase(od.getQuotationMDRRate().getProductType(),
									// fontmoh4));
									mdrTable.addCell(new Phrase("Visa", fontmoh4));
									discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
									mdrTable.addCell(new Phrase(
											String.valueOf(od.getQuotationMDRRate().getLoc_Credit_Merch_Visa()) + "%",
											fontmoh4));
									mdrTable.addCell(new Phrase(
											String.valueOf(od.getQuotationMDRRate().getLoc_Debit_Merch_Visa()) + "%",
											fontmoh4));
									mdrTable.addCell(new Phrase(
											String.valueOf(od.getQuotationMDRRate().getFor_Credit_Merch_Visa()) + "%",
											fontmoh4));
									mdrTable.addCell(new Phrase(
											String.valueOf(od.getQuotationMDRRate().getFor_Debit_Merch_Visa()) + "%",
											fontmoh4));
									if (od.getQuotationMDRRate().getProductSettlement() != 0) {
										mdrTable.addCell(new Phrase(
												"T + " + String
														.valueOf(od.getQuotationMDRRate().getProductSettlement()),
												fontmoh4));
									} else {
										mdrTable.addCell(new Phrase("", fontmoh4));
									}
								} else if (iloop == 2) {
									discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);
									// mdrTable.addCell(new Phrase(od.getQuotationMDRRate().getProductName(),
									// fontmoh4));
									// mdrTable.addCell(new Phrase(od.getQuotationMDRRate().getProductType(),
									// fontmoh4));
									mdrTable.addCell(new Phrase("UnionPay", fontmoh4));
									discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
									mdrTable.addCell(new Phrase(
											String.valueOf(od.getQuotationMDRRate().getLoc_Credit_Merch_Union()) + "%",
											fontmoh4));
									mdrTable.addCell(new Phrase(
											String.valueOf(od.getQuotationMDRRate().getLoc_Debit_Merch_Union()) + "%",
											fontmoh4));
									mdrTable.addCell(new Phrase(
											String.valueOf(od.getQuotationMDRRate().getFor_Credit_Merch_Union()) + "%",
											fontmoh4));
									mdrTable.addCell(new Phrase(
											String.valueOf(od.getQuotationMDRRate().getFor_Debit_Merch_Union()) + "%",
											fontmoh4));
									if (od.getQuotationMDRRate().getProductSettlement() != 0) {
										mdrTable.addCell(new Phrase(
												"T + " + String
														.valueOf(od.getQuotationMDRRate().getProductSettlement()),
												fontmoh4));
									} else {
										mdrTable.addCell(new Phrase("", fontmoh4));
									}
								}
							}

						}

						pdfDoc.add(mdrTable);
					}

				}

				Paragraph pminimumtxn = new Paragraph();
				pminimumtxn.setFont(font5);
				pminimumtxn.add("* Minimum transaction permitted on this terminal is MYR 5.00");
				pdfDoc.add(pminimumtxn);
				// endregion

				if (SplitCount > 0) {
					// MDR TABLE FOR EZYSPLIT

					Paragraph SPLITpmdr = new Paragraph();
					SPLITpmdr.setFont(boldFont);
					SPLITpmdr.add("EZYSPLIT");
					pdfDoc.add(SPLITpmdr);
					pdfDoc.add(p5);
					PdfPTable SPLITmdrTable = new PdfPTable(4);
					float[] SPLITmdrWidths = new float[] { 2f, 3f, 3f, 2f };
					SPLITmdrTable.setWidths(SPLITmdrWidths);
					SPLITmdrTable.setWidthPercentage(100f);
					SPLITmdrTable.setHorizontalAlignment(Element.ALIGN_CENTER);
					SPLITmdrTable.getDefaultCell().disableBorderSide(15);
					SPLITmdrTable.getDefaultCell().enableBorderSide(2);

					SPLITmdrTable.getDefaultCell().setBorderColor(new BaseColor(200, 224, 230));
					SPLITmdrTable.getDefaultCell().setPaddingBottom(3f);
					SPLITmdrTable.getDefaultCell().setBackgroundColor(new BaseColor(0, 91, 170));
					// SPLITmdrTable.addCell(new Phrase("Product Type", fontWhite));
					SPLITmdrTable.addCell(new Phrase("No of Payments", fontWhite));
					SPLITmdrTable.addCell(new Phrase("Merchant MDR", fontWhite));
					SPLITmdrTable.addCell(new Phrase("Customer processing Fee", fontWhite));
					SPLITmdrTable.addCell(new Phrase("Settlement", fontWhite));
					SPLITmdrTable.getDefaultCell().setBackgroundColor(new BaseColor(255, 255, 255));

					for (OrderLines od : odList) {
						if (od.getProduct().getShowInQuote().equals("Yes")
								&& od.getQuotationEzysplitMDRRate() != null) {
							discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);

							if (odList.size() > 0) {
								for (int iloop = 0; iloop < 4; iloop++) {
									if (iloop == 0) {
										discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);

										// SPLITmdrTable.addCell(new Phrase(od.getProduct().getProductType(),
										// fontmoh4));
										SPLITmdrTable.addCell(new Phrase("Installment 3", fontmoh4));
										discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
										SPLITmdrTable
												.addCell(
														new Phrase(
																od.getQuotationEzysplitMDRRate()
																		.getLoc_Credit_Merch_Master_Insta3() + "%",
																fontmoh4));
										SPLITmdrTable
												.addCell(
														new Phrase(
																od.getQuotationEzysplitMDRRate()
																		.getLoc_Credit_Cus_Master_Insta3() + "%",
																fontmoh4));
										if (od.getQuotationEzysplitMDRRate().getProductSettlement() != 0) {
											SPLITmdrTable.addCell(new Phrase(
													"T + " + String.valueOf(
															od.getQuotationEzysplitMDRRate().getProductSettlement()),
													fontmoh4));
										} else {
											SPLITmdrTable.addCell(new Phrase("", fontmoh4));
										}
									} else if (iloop == 1) {
										discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);

										// SPLITmdrTable.addCell(new Phrase(od.getProduct().getProductType(),
										// fontmoh4));
										SPLITmdrTable.addCell(new Phrase("Installment 6", fontmoh4));
										discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
										SPLITmdrTable
												.addCell(
														new Phrase(
																od.getQuotationEzysplitMDRRate()
																		.getLoc_Credit_Merch_Master_Insta6() + "%",
																fontmoh4));
										SPLITmdrTable
												.addCell(
														new Phrase(
																od.getQuotationEzysplitMDRRate()
																		.getLoc_Credit_Cus_Master_Insta6() + "%",
																fontmoh4));
										if (od.getQuotationEzysplitMDRRate().getProductSettlement() != 0) {
											SPLITmdrTable.addCell(new Phrase(
													"T + " + String.valueOf(
															od.getQuotationEzysplitMDRRate().getProductSettlement()),
													fontmoh4));
										} else {
											SPLITmdrTable.addCell(new Phrase("", fontmoh4));
										}
									} else if (iloop == 2) {
										discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);

										// SPLITmdrTable.addCell(new Phrase(od.getProduct().getProductType(),
										// fontmoh4));
										SPLITmdrTable.addCell(new Phrase("Installment 9", fontmoh4));
										discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
										SPLITmdrTable
												.addCell(
														new Phrase(
																od.getQuotationEzysplitMDRRate()
																		.getLoc_Credit_Merch_Master_Insta9() + "%",
																fontmoh4));
										SPLITmdrTable
												.addCell(
														new Phrase(
																od.getQuotationEzysplitMDRRate()
																		.getLoc_Credit_Cus_Master_Insta9() + "%",
																fontmoh4));
										if (od.getQuotationEzysplitMDRRate().getProductSettlement() != 0) {
											SPLITmdrTable.addCell(new Phrase(
													"T + " + String.valueOf(
															od.getQuotationEzysplitMDRRate().getProductSettlement()),
													fontmoh4));
										} else {
											SPLITmdrTable.addCell(new Phrase("", fontmoh4));
										}
									} else if (iloop == 3) {
										discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);

										// SPLITmdrTable.addCell(new Phrase(od.getProduct().getProductType(),
										// fontmoh4));
										SPLITmdrTable.addCell(new Phrase("Installment 12", fontmoh4));
										discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
										SPLITmdrTable
												.addCell(
														new Phrase(
																od.getQuotationEzysplitMDRRate()
																		.getLoc_Credit_Merch_Master_Insta12() + "%",
																fontmoh4));
										SPLITmdrTable
												.addCell(
														new Phrase(
																od.getQuotationEzysplitMDRRate()
																		.getLoc_Credit_Cus_Master_Insta12() + "%",
																fontmoh4));
										if (od.getQuotationEzysplitMDRRate().getProductSettlement() != 0) {
											SPLITmdrTable.addCell(new Phrase(
													"T + " + String.valueOf(
															od.getQuotationEzysplitMDRRate().getProductSettlement()),
													fontmoh4));
										} else {
											SPLITmdrTable.addCell(new Phrase("", fontmoh4));
										}
									}

								}
							}
						}
					}
					pdfDoc.add(SPLITmdrTable);
					Paragraph card = new Paragraph();
					card.setFont(font5);
					card.add("* MDR for EZYSPLIT is only for MasterCard");
					pdfDoc.add(card);

					// endregion
				}

			}

			// WALLET MDR TABLE

			Paragraph pwmdr = new Paragraph();
			PdfPTable wmdrTable = new PdfPTable(7);
			float[] wmdrWidths = new float[] { 0.5f, 1f, 1f, 1f, 1f, 1f, 1f };

			// if (od.getQuotationMDRRate().getIncludeWallet().equals("Yes"))
			// {
			discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (odList.size() > 0) {
				pwmdr.setFont(boldFont);
				pwmdr.add("Wallets");
				pdfDoc.add(pwmdr);
				pdfDoc.add(p5);
				wmdrTable.setWidths(wmdrWidths);
				wmdrTable.setWidthPercentage(100f);
				wmdrTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				wmdrTable.getDefaultCell().disableBorderSide(15);
				wmdrTable.getDefaultCell().enableBorderSide(2);
				wmdrTable.getDefaultCell().setBorderColor(new BaseColor(200, 224, 230));
				wmdrTable.getDefaultCell().setPaddingBottom(3f);
				wmdrTable.getDefaultCell().setBackgroundColor(new BaseColor(0, 91, 170));
				wmdrTable.addCell(new Phrase("Product", fontWhite));
				wmdrTable.addCell(new Phrase("Boost EComm", fontWhite));
				wmdrTable.addCell(new Phrase("Boost QR", fontWhite));
				wmdrTable.addCell(new Phrase("Grab EComm", fontWhite));
				wmdrTable.addCell(new Phrase("Grab QR", fontWhite));
				wmdrTable.addCell(new Phrase("FPX(RM)", fontWhite));
				wmdrTable.addCell(new Phrase("FPX(%)", fontWhite));
				wmdrTable.getDefaultCell().setBackgroundColor(new BaseColor(255, 255, 255));
				discounttab.setHorizontalAlignment(Element.ALIGN_LEFT);

				/*
				 * image.scaleAbsolute(55f, 18f); image.setAlignment(Element.ALIGN_LEFT);
				 * cellxysign.addElement(new Paragraph("\n")); cellxysign.addElement(image);
				 * cellxysign.disableBorderSide(15); pdfsign.addCell(cellxysign);
				 */

				for (OrderLines od : odList) {
					if (od.getQuotationEzysplitMDRRate() != null) {

						discounttab.setHorizontalAlignment(Element.ALIGN_RIGHT);
						wmdrTable.addCell(new Phrase(od.getQuotationEzysplitMDRRate().getProductType(), fontmoh4));
						wmdrTable.addCell(new Phrase(
								String.valueOf(od.getQuotationEzysplitMDRRate().getBoostMDREcomm()) + "%", fontmoh4));
						wmdrTable.addCell(new Phrase(
								String.valueOf(od.getQuotationEzysplitMDRRate().getBoostMDRQR()) + "%", fontmoh4));
						wmdrTable.addCell(new Phrase(
								String.valueOf(od.getQuotationEzysplitMDRRate().getGrabMDREcomm()) + "%", fontmoh4));
						wmdrTable.addCell(new Phrase(
								String.valueOf(od.getQuotationEzysplitMDRRate().getGrabMDREcomm()) + "%", fontmoh4));
						wmdrTable.addCell(
								new Phrase(String.valueOf(od.getQuotationEzysplitMDRRate().getfPXMDR_RM()), fontmoh4));
						wmdrTable.addCell(new Phrase(
								String.valueOf(od.getQuotationEzysplitMDRRate().getfPXMDR_Percent()) + "%", fontmoh4));

						discounttab.setHorizontalAlignment(Element.ALIGN_CENTER);
					} else {
						wmdrTable.addCell(new Phrase(od.getQuotationMDRRate().getProductType(), fontmoh4));
						wmdrTable.addCell(new Phrase(String.valueOf(od.getQuotationMDRRate().getBoostMDREcomm()) + "%",
								fontmoh4));
						wmdrTable.addCell(
								new Phrase(String.valueOf(od.getQuotationMDRRate().getBoostMDRQR()) + "%", fontmoh4));
						wmdrTable.addCell(
								new Phrase(String.valueOf(od.getQuotationMDRRate().getGrabMDREcomm()) + "%", fontmoh4));
						wmdrTable.addCell(
								new Phrase(String.valueOf(od.getQuotationMDRRate().getGrabMDRQR()) + "%", fontmoh4));
						wmdrTable
								.addCell(new Phrase(String.valueOf(od.getQuotationMDRRate().getfPXMDR_RM()), fontmoh4));
						wmdrTable.addCell(new Phrase(String.valueOf(od.getQuotationMDRRate().getfPXMDR_Percent()) + "%",
								fontmoh4));

						discounttab.setHorizontalAlignment(Element.ALIGN_CENTER);
					}
					// }
				}

			}

			pdfDoc.add(wmdrTable);
			// endregion

			Paragraph textmpi = new Paragraph();
			textmpi.setLeading(0, 2);
			textmpi.setFont(font5);
			textmpi.add("MERCHANT PAYMENT INFORMATION");
			textmpi.add(Chunk.NEWLINE);
			textmpi.add(
					"You are required to maintain the same bank current account for the purpose of payment crediting.");
			textmpi.add(Chunk.NEWLINE);
			textmpi.add(Chunk.NEWLINE);

			pdfDoc.add(new Chunk("\n"));
			pdfDoc.add(textmpi);
			PdfPTable tblmpi = new PdfPTable(2); // two columns create table
			SysParam spmpi = new SysParam();
			tblmpi.setWidthPercentage(100f);
			float[] widthsmpi = new float[] { 4f, 6f };
			tblmpi.setWidths(widthsmpi);

			// Business Payee Name
			PdfPCell cellx = new PdfPCell();
			Paragraph pxax = new Paragraph("BUSINESS PAYEE NAME");
			pxax.setFont(font5);
			cellx.setBackgroundColor(new BaseColor(220, 234, 240));
			cellx.addElement(pxax);
			tblmpi.addCell(cellx);
			PdfPCell celly = new PdfPCell();
			Paragraph pyax = new Paragraph(order.getBusinessName());
			pyax.setFont(font5);
			celly.addElement(pyax);
			tblmpi.addCell(celly);

			// Bank Name
			PdfPCell cellbank = new PdfPCell();
			Paragraph paraBank = new Paragraph("BANK NAME");
			paraBank.setFont(font5);
			cellbank.setBackgroundColor(new BaseColor(220, 234, 240));
			cellbank.addElement(paraBank);
			tblmpi.addCell(cellbank);
			PdfPCell cellBankValue = new PdfPCell();
			Paragraph paraBankValue = new Paragraph(order.getBankName());
			paraBankValue.setFont(font5);
			cellBankValue.addElement(paraBankValue);
			tblmpi.addCell(cellBankValue);

			// Current Account No
			PdfPCell cellAccount = new PdfPCell();
			Paragraph paraAccount = new Paragraph("CURRENT ACCOUNT NO");
			paraAccount.setFont(font5);
			cellAccount.setBackgroundColor(new BaseColor(220, 234, 240));
			cellAccount.addElement(paraAccount);
			tblmpi.addCell(cellAccount);
			PdfPCell cellAccountValue = new PdfPCell();
			Paragraph paraAccountValue = new Paragraph(order.getAccountNo());
			paraAccountValue.setFont(font5);
			cellAccountValue.addElement(paraAccountValue);
			tblmpi.addCell(cellAccountValue);

			pdfDoc.add(tblmpi);

			Paragraph texti = new Paragraph();
			texti.setLeading(0, 2);
			texti.setFont(font5);
			texti.add("MERCHANT SERVICING");
			texti.add(Chunk.NEWLINE);
			texti.add("For any queries:");
			texti.add(Chunk.NEWLINE);
			texti.add("* Contact Number: 0122902076");
			texti.add(Chunk.NEWLINE);
			texti.add("* Email: csmobi@gomobi.io");

			pdfDoc.add(texti);

			// pdfDoc.newPage();
			for (OrderLines od : order.getQuotation().getOrderLines()) {
				if (od.getQuotationMDRRate() != null) {

					Paragraph texti2 = new Paragraph();
					texti2.setLeading(0, 2);
					texti2.setFont(font5);
					texti2.add("TERMINAL SETUP INFORMATION");
					texti2.add(Chunk.NEWLINE);
					texti2.add(Chunk.NEWLINE);

					pdfDoc.add(texti2);

					PdfPTable tbltsi = new PdfPTable(2); // two colmns create tabble
					SysParam sptsi = new SysParam();
					List<SysParam> spisti = GetTerminalSetupInfo(order);
					tblmpi.setWidthPercentage(100f);
					float[] widthsti = new float[] { 5f, 5f };
					tblmpi.setWidths(widthsti);
					int l = 0;

					for (SysParam spo : spisti) {
						logger.info(spo.toString());
						PdfPCell cellParamx = new PdfPCell();
						Paragraph paraxax = new Paragraph(spo.getParameter());
						paraxax.setFont(font5);
						cellParamx.setBackgroundColor(new BaseColor(220, 234, 240));
						cellParamx.addElement(paraxax);
						tbltsi.addCell(cellParamx);
						l++;
						PdfPCell cellParamy = new PdfPCell();
						Paragraph parayax = new Paragraph(spo.getValue());
						parayax.setFont(font5);
						cellParamy.addElement(parayax);
						tbltsi.addCell(cellParamy);
					}
					pdfDoc.add(tbltsi);
					break;
				}
			}
			Paragraph textjk = new Paragraph();
			textjk.setLeading(0, 2);
			textjk.setFont(font5);
			textjk.add(
					"If the above terms are acceptable to you, kindly signify your agreement. Thank you and we look forward to establish a mutually beneficial business relationship with you.");
			textjk.add("I/We hereby confirm our acceptance and agree to the terms and conditions of this letter.");
			pdfDoc.add(textjk);

			// PDF Signature New Design
			pdfDoc.add(new Chunk("\n"));
			PdfPTable tableSignature = new PdfPTable(3);

			Paragraph legalEntity = new Paragraph();
			legalEntity.setLeading(0, 2);
			legalEntity.setFont(font5);
			legalEntity.add("Legal Entity Name");

			Paragraph legalEntityMerch = new Paragraph();
			legalEntityMerch.setLeading(0, 2);
			legalEntityMerch.setFont(font5);
			legalEntityMerch.add(order.getBusinessName());

			Paragraph legalEntityMobi = new Paragraph();
			legalEntityMobi.setLeading(0, 2);
			legalEntityMobi.setFont(font5);
			legalEntityMobi.add("Mobi Asia Sdn Bhd");

			Paragraph representative = new Paragraph();
			representative.setLeading(0, 2);
			representative.setFont(font5);
			representative.add("Representative Name");

			Paragraph representativeMerch = new Paragraph();
			representativeMerch.setLeading(0, 2);
			representativeMerch.setFont(font5);
			representativeMerch.add(order.getQuotation().getWelcomeLetterAcceptance() == null ? ""
					: order.getQuotation().getWelcomeLetterAcceptance().getNameAsPerIC());

			Paragraph representativeMobi = new Paragraph();
			representativeMobi.setLeading(0, 2);
			representativeMobi.setFont(font5);
			representativeMobi.add(order.getQuotation().getSalesPerson().getAliasName());

			Paragraph title = new Paragraph();
			title.setLeading(0, 2);
			title.setFont(font5);
			title.add("Title");

			Paragraph titleMerch = new Paragraph();
			titleMerch.setLeading(0, 2);
			titleMerch.setFont(font5);
			titleMerch.add("Director");

			Paragraph titleMobi = new Paragraph();
			titleMobi.setLeading(0, 2);
			titleMobi.setFont(font5);
			titleMobi.add("Merchant Consultant");

			Paragraph signature = new Paragraph();
			signature.setLeading(0, 2);
			signature.setFont(font5);
			signature.add("Signature");

			tableSignature.addCell(legalEntity);
			tableSignature.addCell(legalEntityMerch);
			tableSignature.addCell(legalEntityMobi);

			tableSignature.addCell(representative);
			tableSignature.addCell(representativeMerch);
			tableSignature.addCell(representativeMobi);

			tableSignature.addCell(title);
			tableSignature.addCell(titleMerch);
			tableSignature.addCell(titleMobi);

			tableSignature.addCell(signature);

			String signpathMerchant = "";
			String signpathSalesPerson = "";

			if (order.getQuotation().getUserId() != null && order.getQuotation().getWelcomeLetterAcceptance() != null) {
				// Get Customer Signature
				Image imagesign = Image
						.getInstance(env.getProperty("signature.basePath") + order.getQuotation().getUserId() + ".png");
				imagesign.setAbsolutePosition(75f, 75f);
				imagesign.setAlignment(Element.ALIGN_LEFT);
				tableSignature.addCell(imagesign);
			} else {
				tableSignature.addCell("");
			}
			if (order.getQuotation().getSalesPerson() != null) {
				// Get SalesPerson Signature
				Image imagesignsp = Image.getInstance(env.getProperty("signature.basePath")
						+ order.getQuotation().getSalesPerson().getPhone() + ".png");
				imagesignsp.setAbsolutePosition(75f, 75f);
				imagesignsp.setAlignment(Element.ALIGN_LEFT);
				tableSignature.addCell(imagesignsp);
			} else {
				tableSignature.addCell("");
			}

			pdfDoc.add(tableSignature);

			pdfDoc.close();

			File createExistingFilePath = new File(fileStorePath);
			if (createExistingFilePath.exists()) {
				String welcomeLetterResourcePath = String.format("%s/%s/WelcomeLetter-%s.pdf",
						Constants.getWelcomeLetterResourcePath(), orderId, orderId);
				service.UpdateWelcomeLetterPath(welcomeLetterResourcePath, order.getQuotation().getId());

				return welcomeLetterResourcePath;
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("Stacktrace : ", e);
			return null;
		}
	}

	public List<SysParam> GetTerminalSetupInfo(Order order) {

		String tidValue = "";
		List<SysParam> s = new ArrayList<SysParam>();
		try {

			SysParam sp = new SysParam();
			sp.setParameter("TERMINAL ID");

			for (OrderLines od : order.getQuotation().getOrderLines()) {
				sp.setValue(sp.getValue() + "  " + od.getTid() == null ? "" : od.getTid());
			}
			s.add(sp);

			sp = new SysParam();
			sp.setParameter("ACTIVATION CODE");
			for (OrderLines od : order.getQuotation().getOrderLines()) {
				sp.setValue(sp.getValue() + "  " + od.getActivationCode() == null ? "" : od.getActivationCode());
			}
			s.add(sp);

			sp = new SysParam();
			sp.setParameter("API KEY");
			for (OrderLines od : order.getQuotation().getOrderLines()) {
				sp.setValue(sp.getValue() + "  " + od.getApiKey() == null ? "" : od.getApiKey());
			}
			s.add(sp);

		} catch (Exception e) {
			logger.error("Stacktrace : ", e);
		}
		return s;
	}

	public List<SysParam> GetSchedule1Mobi(Order order) {
		List<SysParam> s = new ArrayList<SysParam>();

		try {

			SysParam sp = new SysParam();
			sp.setParameter("Merchant ID Type");
			sp.setValue(order.getMerchantIdType());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Business Registration ID/No");
			sp.setValue(order.getRegistrationId());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Business Name");
			sp.setValue(order.getBusinessName());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Merchant Trading Name");
			sp.setValue(order.getMerchantTradingName());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Business Start Time");
			sp.setValue(order.getBusinessStartTime());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Business End Time");
			sp.setValue(order.getBusinessEndTime());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Number Of MPOS");
			sp.setValue(String.valueOf(order.getNoOfMPOS()));
			s.add(sp);
			if (order.getNoOfMPOS() > 0) {
				sp = new SysParam();
				sp.setParameter("MPOS Model");
				sp.setValue("EZYWIRE");
				s.add(sp);
			}

			String city = service.GetUmobileCityByID(order.getCity());
			String state = service.GetUmobileStateByID(order.getState());
			String ecommIndustry = "";
			String mcc = "";
			String natureOfBusiness = "";
			CompanyType companyType = null;

			if (order.geteCommIndustry() != 0) {
				ecommIndustry = service.GetEcommIndustryByValue(order.geteCommIndustry());
			}

			if (order.getVisaMCC() != 0) {
				mcc = service.GetVisaMCCByValue(order.getVisaMCC());
			}

			if (order.getNatureOfBusiness() != 0) {
				natureOfBusiness = service.GetNatureOfBusinessByID(order.getNatureOfBusiness());
			}
			if (!order.getCompanyType().equals("0")) {
				companyType = service.GetCompanyTypeByID(order.getCompanyType());
			}

			sp = new SysParam();
			sp.setParameter("Nature Of Business");
			sp.setValue(natureOfBusiness);
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("E-Commerce Industry");
			sp.setValue(ecommIndustry);
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Website/URL address");
			sp.setValue(order.getWebsiteUrl());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Mastercard MCC");
			sp.setValue(mcc);
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Visa MCC");
			sp.setValue(mcc);
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("UnionPay MCC");
			sp.setValue(mcc);
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Previous Acquirer");
			sp.setValue(order.getPreviousAcquirer());
			s.add(sp);
			if (order.getPreviousAcquirer() != null) {
				sp = new SysParam();
				sp.setParameter("Ceased date with previous Acquirer");
				sp.setValue(order.getPreviousAcquirerCeasedDate());
				s.add(sp);
			}
			sp = new SysParam();
			sp.setParameter("Business address");
			sp.setValue(order.getAddressLine() + " " + city + " " + state + " " + order.getPostCode());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Post Code");
			sp.setValue(order.getPostCode());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("State");
			sp.setValue(state);
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Town");
			sp.setValue(city);
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Country");
			sp.setValue("Malaysia");
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Office No");
			sp.setValue(order.getOfficeNo());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Mobile No");
			sp.setValue(order.getAuthContactPersonPhone());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Email");
			sp.setValue(order.getOfficeEmail());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Business Email");
			sp.setValue(order.getOfficeEmail());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Bank Name");
			sp.setValue(order.getBankName());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Account Number");
			sp.setValue(order.getAccountNo());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Date Incorporated");
			sp.setValue(order.getDateIncorporated());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Authorized Contact Person Name");
			sp.setValue(order.getAuthContactPersonName());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Authorized Contact Person ID");
			sp.setValue(order.getAuthContactPersonId());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Authorized Contact Person Phone");
			sp.setValue(order.getAuthContactPersonPhone());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Company Type");
			sp.setValue(companyType.getName());
			s.add(sp);
			//
			sp = new SysParam();
			sp.setParameter("Size Of Premise");
			sp.setValue(order.getSizeOfPremise());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Estimated Stock Value");
			sp.setValue(order.getEstimatedStockValue());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("No Of Employee");
			sp.setValue(order.getNoOfEmployee());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("No Of Daily Transaction");
			sp.setValue(order.getNoOfDailyTransaction());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Average Transaction Size");
			sp.setValue(order.getAverageTransactionSize());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Remarks");
			sp.setValue(order.getRemarks());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Who Signed?");
			sp.setValue(order.getWhoSigned());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Who Signed NRIC");
			sp.setValue(order.getWhoSignedNric());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Who Signed Mobile");
			sp.setValue(order.getWhoSignedMobile());
			s.add(sp);
			sp = new SysParam();
			sp.setParameter("Salesperson");
			sp.setValue(order.getQuotation().getSalesPerson().getName());
			s.add(sp);

		} catch (Exception e) {
			logger.error("Stacktrace : " + e);
		}
		return s;
	}

	public List<SysParam> GetSchedule1Facta(Order order) {
		List<SysParam> s = new ArrayList<SysParam>();
		SysParam sp = new SysParam();
		sp.setParameter("Is US Resident");
		if (order.getIsUSResident().equals("Yes")) {
			sp.setValue("true");
		} else {
			sp.setValue("false");
		}

		s.add(sp);
		sp = new SysParam();
		sp.setParameter("Is US Citizen");
		if (order.getIsUSCitizen().equals("Yes")) {
			sp.setValue("true");
		} else {
			sp.setValue("false");
		}
		s.add(sp);
		sp = new SysParam();
		sp.setParameter("Is green card holder");
		if (order.getIsGreenCardHolder().equals("Yes")) {
			sp.setValue("true");
		} else {
			sp.setValue("false");
		}
		s.add(sp);
		return s;
	}

	public List<SysParam> GetScheduleMobi(Order order) {

		String mid = "";
		String tid = "";
		String mdrCredit = "";
		String mdrDebit = "";
		String dtl = "";
		String mcc = "";
		String remarks = "";

		for (OrderLines odtl : order.getQuotation().getOrderLines()) {
			mid += (odtl.getMid() == null ? "" : odtl.getMid()) + " ";
			tid += (odtl.getTid() == null ? "" : odtl.getTid()) + " ";
			dtl += (odtl.getDtl() == null ? "" : odtl.getDtl()) + " ";
		}

		List<SysParam> s = new ArrayList<SysParam>();
		SysParam sp = new SysParam();
		sp.setParameter("Merchant Identification (MID)");
		sp.setValue(mid);
		s.add(sp);
		sp = new SysParam();
		sp.setParameter("Terminal Identification");
		sp.setValue(tid);
		s.add(sp);
		sp = new SysParam();
		sp.setParameter("Merchant Discout Rate (MDR) - Credit");
		sp.setValue(mdrCredit);
		s.add(sp);
		sp = new SysParam();
		sp.setParameter("Merchant Discout Rate (MDR) - Debit");
		sp.setValue(mdrDebit);
		s.add(sp);
		sp = new SysParam();
		sp.setParameter("Daily Transaction Limit (DTL)");
		sp.setValue(dtl);
		s.add(sp);
		sp = new SysParam();
		sp.setParameter("Merchant Category Code");
		sp.setValue(mcc);
		s.add(sp);
		sp = new SysParam();
		sp.setParameter("Remarks");
		sp.setValue(order.getRemarks());
		s.add(sp);
		return s;
	}

	@Override
	public Object GenerateMMA(String orderId) throws DocumentException, IOException {

		logger.info("Generate MMA Module");

		Order order = service.GetOrderByID(orderId);

		String pdfFilePath = Constants.getMMAResourcePath();
		pdfFilePath = String.format("%s/%s/", pdfFilePath, orderId);

		ClassLoader classLoaderBoost = getClass().getClassLoader();

		File fPDFFilePath = new File(pdfFilePath);

		if (!fPDFFilePath.exists()) {
			fPDFFilePath.mkdirs();
		}

		Document pdfDoc = new Document();
		pdfDoc.setPageSize(PageSize.A4); // SET MARGINS

		Font font5 = new Font(FontFamily.HELVETICA, 8);
		Font fontWhite = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(255, 255, 255));
		Font boldFontWhite = new Font(FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(255, 255, 255));
		Font boldFontBlue = new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(0, 90, 171));
		Font font6Blue = new Font(FontFamily.HELVETICA, 6, Font.BOLD, new BaseColor(0, 90, 171));
		Font fontmoh = new Font(FontFamily.HELVETICA, 6);
		Font font6Bold = new Font(FontFamily.HELVETICA, 6, Font.BOLD);
		Font font6BoldUnderline = new Font(FontFamily.HELVETICA, 6, Font.BOLD | Font.UNDERLINE);
		Font normalFont = new Font(FontFamily.HELVETICA, 8);
		Font boldFont = new Font(FontFamily.HELVETICA, 10, Font.BOLD);
		Font fontHead = new Font(FontFamily.HELVETICA, 14);
		Font font7 = new Font(FontFamily.HELVETICA, 9);
		Font linkTextFont = new Font(FontFamily.HELVETICA, 8, Font.ITALIC, new BaseColor(51, 51, 255));

		BaseFont f_cn = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		BaseFont Merchfont = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, false);
		Font fontTinyItalic = new Font(FontFamily.HELVETICA, 30, Font.BOLDITALIC, BaseColor.BLACK);

		String fileStorePath = String.format("%s/MMA-%s.pdf", fPDFFilePath, orderId);

		OutputStream fos = new FileOutputStream(fileStorePath);
		PdfWriter writer = PdfWriter.getInstance(pdfDoc, fos);

		pdfDoc.open();

		URL mobiPath = getClass().getClassLoader().getResource("/assets/images/mobi.png");

		logger.info("mobiPath : " + mobiPath);
		Image image = Image.getInstance(mobiPath);
		image.scaleAbsolute(70f, 70f);
		image.setAlignment(Element.ALIGN_LEFT);

		// Schedule 1 Details
		PdfPTable resimtable = new PdfPTable(1);
		float[] widthresim = new float[] { 8f };
		resimtable.setWidths(widthresim);
		resimtable.setWidthPercentage(50f);
		resimtable.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cella = new PdfPCell();
		Paragraph xstring1 = new Paragraph();
		xstring1.setAlignment(Element.ALIGN_CENTER);
		xstring1.add(new Chunk(image, 0, 1, true));
		xstring1.add("\nMOBI MERCHANT AGREEMENT");
		// xstring1.setFont(iTextSharp.text.FontFactory.GetFont(s +
		// "\\fonts\\Montserrat-Medium.ttf", 10, Font.BOLD);
		cella.addElement(xstring1);
		Paragraph xstring2 = new Paragraph();
		xstring2.setAlignment(Element.ALIGN_CENTER);
		xstring2.add("Document Refence :(Mobiversa/U" + orderId + ")");
		xstring2.setFont(font5);
		cella.addElement(xstring2);
		Paragraph xstring3 = new Paragraph();
		xstring3.setAlignment(Element.ALIGN_CENTER);
		xstring3.add("\n");
		xstring3.setFont(font5);
		cella.addElement(xstring3);
		cella.setBorder(Rectangle.NO_BORDER);
		resimtable.addCell(cella);

		pdfDoc.add(resimtable);

		// Merchant Information
		Paragraph head1x = new Paragraph();
		head1x.add("MERCHANT INFORMATION");
		head1x.setFont(boldFont);
		pdfDoc.add(head1x);
		Paragraph head12x = new Paragraph();
		head12x.setFont(font5);
		head12x.add("");
		head12x.setFont(font5);
		pdfDoc.add(head12x);

		PdfPTable tblfirst = new PdfPTable(3); // two colmns create tabble
		SysParam spfirst = new SysParam();
		List<SysParam> spifirst = GetSchedule1Mobi(order);
		tblfirst.setWidthPercentage(100f);
		float[] widthsfirst = new float[] { 4f, 4f, 4f };
		tblfirst.setWidths(widthsfirst);
		int ix = 0;

		for (SysParam spo : spifirst) {

			ix++;
			Rectangle r;
			PdfPCell cellx = new PdfPCell();
			Paragraph p = new Paragraph(ix + ". " + spo.getParameter() + ": ");
			p.setFont(boldFontBlue);
			cellx.setBorder(Rectangle.NO_BORDER);
			cellx.addElement(p);
			cellx.setBorder(Rectangle.NO_BORDER);
			Paragraph p1 = new Paragraph(spo.getValue());
			p1.setFont(normalFont);
			cellx.addElement(p1);
			cellx.setBorder(Rectangle.NO_BORDER);
			tblfirst.addCell(cellx);
		}
		pdfDoc.add(tblfirst);

		// Director & Uploaded Documents Details

		if (order.getDirectors().size() > 0) {

			PdfPTable table = new PdfPTable(6);
			table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			float[] widths = new float[] { 5f, 5f, 5f, 5f, 5f, 10f };
			table.setWidths(widths);
			table.setWidthPercentage(100);

			PdfPCell nameCell = new PdfPCell();
			Phrase namePhrase = new Phrase("Name", boldFontBlue);
			nameCell.addElement(namePhrase);
			nameCell.setBorder(Rectangle.BOTTOM);
			nameCell.setLeading(5, 5);
			table.addCell(nameCell);

			PdfPCell designationCell = new PdfPCell();
			Phrase designationPhrase = new Phrase("Designation", boldFontBlue);
			designationCell.addElement(designationPhrase);
			designationCell.setBorder(Rectangle.BOTTOM);
			designationCell.setLeading(5, 5);
			table.addCell(designationCell);

			PdfPCell idCell = new PdfPCell();
			Phrase idPhrase = new Phrase("ID", boldFontBlue);
			idCell.addElement(idPhrase);
			idCell.setBorder(Rectangle.BOTTOM);
			idCell.setLeading(5, 5);
			table.addCell(idCell);

			PdfPCell contactCell = new PdfPCell();
			Phrase contactPhrase = new Phrase("Contact", boldFontBlue);
			contactCell.addElement(contactPhrase);
			contactCell.setBorder(Rectangle.BOTTOM);
			contactCell.setLeading(5, 5);
			table.addCell(contactCell);

			PdfPCell nationalityCell = new PdfPCell();
			Phrase nationalityPhrase = new Phrase("Nationality", boldFontBlue);
			nationalityCell.addElement(nationalityPhrase);
			nationalityCell.setBorder(Rectangle.BOTTOM);
			nationalityCell.setLeading(5, 5);
			table.addCell(nationalityCell);

			PdfPCell addressCell = new PdfPCell();
			Phrase addressPhrase = new Phrase("Address", boldFontBlue);
			addressCell.addElement(addressPhrase);
			addressCell.setBorder(Rectangle.BOTTOM);
			addressCell.setLeading(5, 5);
			table.addCell(addressCell);

			for (Director director : order.getDirectors()) {
				table.setPaddingTop(0);
				table.addCell(new Phrase(director.getName(), font5));
				table.addCell(new Phrase(director.getDesignation(), font5));
				table.addCell(new Phrase(director.getIdNo(), font5));
				table.addCell(new Phrase(director.getContactNo(), font5));
				table.addCell(new Phrase(director.getNationality(), font5));
				table.addCell(new Phrase(director.getAddress(), font5));

			}

			Paragraph head1m = new Paragraph();
			head1m.setFont(font5);
			head1m.add("\n");
			head1m.setFont(font5);
			pdfDoc.add(head1m);
			Paragraph head1 = new Paragraph();
			head1.setFont(boldFont);
			head1.add("OWNERSHIP/OFFICE BEARER DETAILS");
			// head1.Font = iTextSharp.text.FontFactory.GetFont(s +
			// "\\fonts\\Montserrat-Medium.ttf", 9, Font.UNDERLINE);
			pdfDoc.add(head1);
			Paragraph head12 = new Paragraph();
			head12.setFont(font5);
			head12.add("\n");
			head12.setFont(font5);
			pdfDoc.add(head12);
			pdfDoc.add(table);
			// endregion
		}

		// Declaration FACTA
		PdfPTable pdft = new PdfPTable(2); // two colmns create tabble
		pdft.setWidthPercentage(100f);// table %100 width
		float[] widths1 = new float[] { 7f, 10f };
		pdft.setWidths(widths1);
		PdfPCell cellxx = new PdfPCell();
		Paragraph phr1 = new Paragraph();
		phr1.setFont(boldFont);
		phr1.setLeading(0, 2);
		pdfDoc.add(new Chunk("\n"));
		phr1.add("DECLARATION ON FOREIGN ACCOUNT TAX COMPLIANCE ACT (FATCA) (INDIVIDUAL)");
		Paragraph phr11 = new Paragraph();
		phr11.setFont(font5);
		phr11.add("I/We hereby confirm the information provided and declarations above are true, accurate and complete."
				+ "Subject to the applicable local laws, I/We hereby give my/our consent to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to share my/our information with domestic or overseas governmental, supervisory or regulatory authorities where necessary to establish my/our tax liability in any relevant jurisdiction�s requirements."
				+ "Where required by domestic or overseas governmental, supervisory or regulatory authorities, I/We also understand and agree that Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) may be required to obtain additional documents and/or forms, which I/we will sign, if I/we am/are subjected to the relevant jurisdiction�s requirements."
				+ "Where required by domestic or overseas governmental, supervisory or regulatory authorities, I/we understand and agree that Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) may withhold, and pay out, from any of my/our account(s) such amounts as may be required according to applicable laws, regulations, directives, guidelines and/or agreements with and/or from domestic or overseas governmental, supervisory or regulatory authorities.");

		phr11.add(Chunk.NEWLINE);
		phr11.add(
				"I/We also agree and undertake to notify Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) (within 30 Calendar days),or provide the information if requested by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.), if there is a change in any information which I/We have provided to Mobiversa.");
		phr11.add(Chunk.NEWLINE);
		phr11.add("(Please Circle (Yes or No) for each of the following questions)");
		phr11.setFont(font5);
		cellxx.addElement(phr1);
		cellxx.addElement(phr11);

		// region FACTA
		PdfPTable facta = new PdfPTable(3); // two colmns create tabble
		SysParam spfacta = new SysParam();
		List<SysParam> spifacta = GetSchedule1Facta(order);
		facta.setWidthPercentage(100f);
		float[] widths1facta = new float[] { 1f, 7f, 10f };
		float[] widthsxyzfacta = new float[] { 10f, 1f, 1f };
		facta.setWidths(widthsxyzfacta);
		int k = 0;

		for (SysParam spo : spifacta) {
			k++;
			Rectangle r;
			Boolean yesorno = false;
			PdfPCell cellxfacta = new PdfPCell();
			Paragraph p = new Paragraph(spo.getParameter() + ":");
			p.setFont(font5);
			cellxfacta.setBorder(Rectangle.NO_BORDER);
			cellxfacta.addElement(p);
			cellxfacta.setBorder(Rectangle.NO_BORDER);
			facta.addCell(cellxfacta);
			PdfPCell cellyfacta = new PdfPCell();
			PdfPCell cellzfacta = new PdfPCell();
			if (spo.getParameter().contains("Is")) {
				if (Boolean.parseBoolean(spo.getValue()) == true) {
					yesorno = true;
					Paragraph p1 = new Paragraph("YES");
					p1.setFont(font5);
					cellyfacta.addElement(p1);
				} else if (Boolean.parseBoolean(spo.getValue()) == false) {
					yesorno = false;
					Paragraph p2 = new Paragraph("NO");
					p2.setFont(font5);
					cellzfacta.addElement(p2);
				}

				cellyfacta.setBorder(Rectangle.NO_BORDER);
				cellzfacta.setBorder(Rectangle.NO_BORDER);

				// Paragraph p1 = new Paragraph("YES");
				// p1.setFont(font5);
				// cellyfacta.addElement(p1);
				// Paragraph p2 = new Paragraph("NO");
				// p2.setFont(font5);
				// cellzfacta.addElement(p2);
				// cellyfacta.setBorder(Rectangle.NO_BORDER);
				// cellzfacta.setBorder(Rectangle.NO_BORDER);
				//
				// if (Boolean.parseBoolean(spo.getValue()) == true) {
				// yesorno = true;
				// } else if (Boolean.parseBoolean(spo.getValue()) == false) {
				// yesorno = false;
				// }
				// if (yesorno == true) {
				// cellyfacta.setCellEvent(new RoundedBorder());
				// cellzfacta.setBorder(Rectangle.NO_BORDER);
				// } else {
				// cellyfacta.setBorder(Rectangle.NO_BORDER);
				// cellzfacta.setCellEvent(new RoundedBorder());
				// }
			} else {
				Paragraph p1 = new Paragraph(spo.getValue());
				p1.setFont(font5);
				cellyfacta.addElement(p1);
				Paragraph p2 = new Paragraph("");
				p2.setFont(font5);
				cellzfacta.addElement(p2);
				cellyfacta.setBorder(Rectangle.NO_BORDER);
				cellzfacta.setBorder(Rectangle.NO_BORDER);
			}
			facta.addCell(cellyfacta);
			facta.addCell(cellzfacta);
		}

		cellxx.addElement(facta);

		SysParam sp = new SysParam();
		Paragraph factapara = new Paragraph();
		factapara.setFont(font5);
		factapara.add("\nTax US: " + order.getTaxIdUS() + "\nCountry Of Birth:" + order.getCountryOfBirth());
		factapara.setFont(font5);
		cellxx.addElement(factapara);

		// Endregion

		pdft.addCell(cellxx);
		PdfPCell cellxy = new PdfPCell();
		String whosignednric = order.getWhoSignedNric() == null ? order.getWhoSignedNric() : "";
		Paragraph phr2 = new Paragraph();
		phr2.setFont(font5);
		phr2.setLeading(0, 2);
		phr2.add("AUTHORISED SIGNATORY:");
		phr2.add(Chunk.NEWLINE);
		phr2.add(Chunk.NEWLINE);

		phr2.add("I confirm that I am the authorized person to apply for the Paydee � Cards Acceptance Service.");
		phr2.add(Chunk.NEWLINE);
		phr2.add("I/We confirm that to my/our knowledge the information given on pages 1 to 3 is True and accurate.");
		phr2.add(Chunk.NEWLINE);
		phr2.add(
				"I/We assure Paydee that I/we will exercise due diligence not to facilitate funds from proceeds from any unlawful activity to be channeled through my/our account(s) with the Paydee.");

		phr2.add(Chunk.NEWLINE);
		phr2.add("I/We assure that all relevant information and documents, as and when\r\n"
				+ "requested, For purpose of my/our identification and/or verification of\r\n"
				+ "the source of my/our funds is comply with the �Know-Your-Customer�");
		phr2.add(Chunk.NEWLINE);
		phr2.add("Principles");
		phr2.add(Chunk.NEWLINE);
		phr2.add("I/We assure that I am authorised on behalf of the Owner(s)/Office\r\n"
				+ "bearer(s) that all the information in the Declaration on FATCA is accurate.");

		phr2.add(Chunk.NEWLINE);
		phr2.add("PRIVACY NOTICE ACKNOWLEDGMENT:");
		phr2.add(Chunk.NEWLINE);
		phr2.add("I / We hereby acknowledge that I / we have accessed and/or read the\r\n"
				+ "Privacy Policy Notice issued by Paydee and confirm my/our\r\n" + "agreement to the same.");

		phr2.add(Chunk.NEWLINE);
		phr2.add("SIGNATURE & AFFIX COMPANY STAMP");
		phr2.add(Chunk.NEWLINE);
		phr2.add("Name: " + order.getWhoSigned());
		phr2.add(Chunk.NEWLINE);
		phr2.add("Designation: Director");
		phr2.add(Chunk.NEWLINE);
		phr2.add("NRIC NO: " + order.getWhoSignedNric());
		phr2.add(Chunk.NEWLINE);
		phr2.add("Date: " + LocalDateTime.now());
		phr2.add(Chunk.NEWLINE);
		phr2.add("Signature: ");
		phr2.add(Chunk.NEWLINE);

		cellxy.addElement(phr2);

		if (order.getQuotation().getWelcomeLetterAcceptance() != null) {

			Image imagesign = Image
					.getInstance(env.getProperty("signature.basePath") + order.getQuotation().getUserId() + ".png");
			imagesign.scaleAbsolute(150f, 150f);
			imagesign.setAlignment(Element.ALIGN_LEFT);
			cellxy.addElement(imagesign);

		}

		pdft.addCell(cellxy);
		pdft.setWidthPercentage(100f);
		pdfDoc.add(pdft);

		pdfDoc.newPage();

		Paragraph kycBold = new Paragraph();
		kycBold.setFont(boldFont);
		kycBold.setLeading(0, 2);
		kycBold.add("KNOW YOUR CUSTOMER (To be completed by Sales Agent & Master Merchant)");
		kycBold.add(Chunk.NEWLINE);

		Paragraph text = new Paragraph();
		text.setFont(font5);
		text.setLeading(0, 2);
		text.add("I hereby confirmed that I have interviewed the Applicant in regards to the Card Acceptance service.");
		text.add(Chunk.NEWLINE);
		text.add(
				"I have explained the Merchant Agreement Terms and Conditions on Card Acceptance service to the Applicant.");
		text.add(Chunk.NEWLINE);
		text.add(
				"I have explained the Merchant Agreement Terms and Conditions on Card Acceptance service to the Applicant.");
		text.add(Chunk.NEWLINE);
		text.add("I have sighted the original copy of the documents submitted as support documents enclosed.");
		text.add(Chunk.NEWLINE);
		text.add(
				"I have visited the Applicant at the Applicant's principal place of business and enclosed photographs of the premises and signboard.");
		text.add(Chunk.NEWLINE);

		pdfDoc.add(kycBold);
		pdfDoc.add(text);
		pdfDoc.add(new Chunk("\n"));

		PdfPTable pdfkyc = new PdfPTable(3); // two colmns create tabble
		float[] widthskyc = { 6f, 6f, 3f };
		pdfkyc.setWidths(widthskyc);
		pdfkyc.setWidthPercentage(100f);// table %100 width
		PdfPCell cellxxkyc = new PdfPCell();
		Paragraph phr1kyc = new Paragraph();
		phr1kyc.setFont(font5);
		phr1kyc.setLeading(0, 2);
		phr1kyc.add("SALES AGENT\r\nSales Agent Name & Agent Code:" + order.getQuotation().getSalesPerson().getName()
				+ "\r\nNRIC/Passport No:" + order.getQuotation().getSalesPerson().getnRIC() + "\r\nDate:"
				+ order.getQuotation().getPayment().getCollectedOn() + "\r\nSales Personnel Signature");
		cellxxkyc.addElement(phr1kyc);

		String signPathSales = "";
		if (signPathSales != null && signPathSales != "") {
			Image imagesignsp = Image.getInstance("");
			imagesignsp.scaleAbsolute(150f, 150f);
			imagesignsp.setAlignment(Element.ALIGN_LEFT);
			cellxxkyc.addElement(imagesignsp);
		}
		pdfkyc.addCell(cellxxkyc);
		PdfPCell cellxykyc = new PdfPCell();
		Paragraph phr2kyc = new Paragraph();
		phr2kyc.setFont(font5);
		phr2kyc.setLeading(0, 2);

		phr2kyc.add(
				"MASTER MERCHANT\r\nAuthorised Signatory & Company Chop\r\nName: BASKAR.S\r\nNRIC/Passport No: E5147448N\r\nDate:"
						+ order.getQuotation().getPayment().getCollectedOn());
		cellxykyc.addElement(phr2kyc);
		if (order.getQuotation().getStage().contains("synergy") || order.getQuotation().getStage().contains("deploy")) {
			String signPathCeo = "";
			if (signPathCeo != null && signPathCeo != "") {
				Image imagesignceo = Image.getInstance("");
				imagesignceo.scaleAbsolute(150f, 150f);
				imagesignceo.setAlignment(Element.ALIGN_LEFT);
				cellxykyc.addElement(imagesignceo);
			}
			pdfkyc.addCell(cellxykyc);
		}
		PdfPCell cellxzkyc = new PdfPCell();
		Paragraph phr3kyc = new Paragraph();
		phr3kyc.setFont(font5);
		phr3kyc.setLeading(0, 2);
		phr3kyc.add("REMARKS\r\n" + order.getQuotation().getRemarks() == null ? "" : order.getQuotation().getRemarks());
		cellxzkyc.addElement(phr3kyc);
		pdfkyc.addCell(cellxzkyc);
		pdfkyc.setWidthPercentage(100f);
		pdfDoc.add(pdfkyc);

		// endregion

		// Synergy Cards, T&C
		Paragraph text1 = new Paragraph();
		text1.setLeading(0, 2);
		text1.setFont(boldFont);
		text1.add("TO BE COMPLETED BY MOBI");
		text1.add(Chunk.NEWLINE);
		pdfDoc.add(text1);
		PdfPTable tbl2 = new PdfPTable(2); // two colmns create tabble
		SysParam sp1 = new SysParam();

		List<SysParam> spiMd = GetScheduleMobi(order);
		float[] widths2 = new float[] { 7f, 10f };
		tbl2.setWidths(widths2);
		int j = 0;
		for (SysParam spo : spiMd) {
			j++;
			PdfPCell cellx = new PdfPCell();
			Paragraph p = new Paragraph(spo.getParameter());
			p.setFont(font5);
			cellx.addElement(p);
			tbl2.addCell(cellx);
			PdfPCell celly = new PdfPCell();
			Paragraph p1 = new Paragraph(spo.getValue());
			p1.setFont(font5);
			celly.addElement(p1);
			tbl2.addCell(celly);
		}
		tbl2.setWidthPercentage(100f);
		pdfDoc.add(tbl2);
		Paragraph text3 = new Paragraph();
		text3.setLeading(0, 2);
		text3.setFont(boldFont);
		text3.add("Mobi Privacy Policy Notice");
		text3.add(Chunk.NEWLINE);
		Paragraph text3a = new Paragraph();
		text3a.setFont(font5);
		text3a.add(
				"As part of Mobi�s day to day business, we value your privacy and strive to protect your personal information in compliance with the laws of Malaysia. We are committed to safeguard and respect the privacy of your personal information which you provide your personal data and your consent for the purposes of conducting our business and to deliver the service you expect. In order to enable us deal with your inquiries, open and operate an account/facility in credit card application and/or merchant application for you or to provide with our products and services, we may be required to process your personal and/or financial information. The type of personal information we receive from you including but not limited to:");
		text3a.add(Chunk.NEWLINE);
		text3a.add("   1. Personal information to establish your identity and background");
		text3a.add(Chunk.NEWLINE);
		text3a.add("   2. Personal information to establish your financial standing");
		text3a.add(Chunk.NEWLINE);
		text3a.add("   3. Personal information that you provide when you apply for any of our products and services");
		text3a.add(Chunk.NEWLINE);
		text3a.add("   4. Personal information that may be required and you provide for risk/fraud management");
		text3a.add(Chunk.NEWLINE);

		Paragraph text3b = new Paragraph();
		text3b.setLeading(0, 2);
		text3b.setFont(boldFont);
		text3b.add("How we use your Personal Information");
		Paragraph text3c = new Paragraph();
		text3c.setLeading(0, 2);
		text3c.setFont(font5);
		text3c.add("    1. Review and Verification of Reports");
		text3c.add(Chunk.NEWLINE);
		text3c.add("    2. Processing of New Merchants Applications");
		text3c.add(Chunk.NEWLINE);
		text3c.add("    3. Processing of Additional Merchant Outlets");
		text3c.add(Chunk.NEWLINE);
		text3c.add(
				"    4. Prevention, prosecution, investigations of fraud or other illegal activity to conduct investigations of violations of any governing terms and conditions");
		text3c.add(Chunk.NEWLINE);
		text3c.add(
				"    5. Requirements of a civil or criminal legal process and/or as required by law or regulation and/or for regulator compliance purpose");
		text3c.add(Chunk.NEWLINE);
		text3c.add(
				"    6. To vendors, suppliers, counter parties, persons who provide a service to Mobi or are acting as Mobi�s agents");
		text3c.add(Chunk.NEWLINE);
		text3c.add("    7. To compare information/data for accuracy, and verify it with third parties");
		text3c.add(Chunk.NEWLINE);
		text3c.add("    8. Marketing and promotions of products and services of Mobi");
		text3c.add(Chunk.NEWLINE);

		Paragraph text3d = new Paragraph();
		text3d.setLeading(0, 2);
		text3d.setFont(boldFont);
		text3d.add("Protection of Personal Data");
		Paragraph text3e = new Paragraph();
		text3e.setLeading(0, 2);
		text3e.setFont(font5);
		text3e.add(
				"   Some of our functions and activities are outsourced to service providers which include data processing services, mailing services, data storage, merchant sign-up and card applications processing based in Malaysia and overseas. The usage of your personal data is in line with the performing of the outsourced functions and subject to strict confidentiality and data security standards."
						+ "Administrative and physical security measures will be used to protect your Personal Data. These measures include computer safeguards and secured files and facilities. We take reasonable steps to securely destroy your Personal Data when we no longer require it. We will keep your data only as long as we must to deliver our products and services, unless we are required by law or regulation or for litigation and regulatory investigations to keep it.");
		text3e.add(Chunk.NEWLINE);
		text3e.add("The following practices are adopted in relation to on-line personal data collection.");
		text3e.add(Chunk.NEWLINE);
		text3e.add(
				"   1. On-Line Security � Strict standards of security and confidentially to protect any information provided on-line.");
		text3e.add(Chunk.NEWLINE);
		text3e.add(
				"   2. On-Line Correction � Personal data provided and submitted through on-line may not be facilitated to be corrected, updated or deleted on-line.");
		text3e.add(Chunk.NEWLINE);
		text3e.add(
				"Our service providers and staffs are bound by contractual duty to keep confidential any data they come into contact with against unauthorized or accidental access, processing, erasure, loss, use and retention. Sensitive personal data or information consists of information relating to the following:");

		text3e.add(Chunk.NEWLINE);
		text3e.add("    1. Camera");
		text3e.add(Chunk.NEWLINE);
		text3e.add("    2. Modify Phone State");
		text3e.add(Chunk.NEWLINE);
		text3e.add("    3. Disable Incoming Call During Payment Process");
		text3e.add(Chunk.NEWLINE);
		text3e.add("    4. Network State");
		text3e.add(Chunk.NEWLINE);
		text3e.add("    5. Internet");
		text3e.add(Chunk.NEWLINE);
		text3e.add("    6. User Current Location");

		text3e.add(Chunk.NEWLINE);
		text3e.add(
				"Any detail relating to the above clauses as provided to body corporate for providing service; and any of the information received under above clauses by body corporate for processing, stored or processed under lawful contract or otherwise: 	");
		text3e.add(
				"'Third Party' refers to any person or entity other than you or us. Personal Information that may be collected and manner of its use Personal Information provided by you in relation to the use of the Website: In order for us to successfully deliver your order to you, we collect your Personal Information as may be required for the purposes connected thereto when you place an order on the Website. We may be required to disclose such Personal Information to the Third Parties solely in connection with purchase, sale and delivery of your order in accordance with the terms and conditions stipulated by such Third Party.");

		text3e.add(
				"We use the details provided by you during check out to ensure that orders have are successfully delivered on time, and to keep you informed on the status of your order.");
		text3e.add(
				"Personal Information provided by you in relation to the registration process: In order to provide you a safe, efficient, smooth and customized experience and in order to take advantage of certain features available on the Website including, but not limited to, public forums, accessing or uploading reviews or taking part in surveys, you will be asked to complete an online registration form which will provide us with Personal Information. In the event you register with us, you will also be asked to choose a user identity and password in order to identify yourself during future uses of the Website.");
		text3e.add(
				"We also collect the information that is not Personal Information you provide as part of registration and the administration and personalization of your account profile.");
		text3e.add(Chunk.NEWLINE);
		text3e.add(
				"Cookies: Cookies are small pieces of information saved by your browser. Cookies are used to record various aspects of your visit and assist us to provide you with uninterrupted service. Cookies may be set in your browser by us when you access the Website or may be set in when you visit Third Party websites. This anonymous information is maintained distinctly and is not linked to the Personal Information that you submit to us.");

		text3e.add(Chunk.NEWLINE);
		text3e.add("We use cookies collected to:");
		text3e.add(Chunk.NEWLINE);
		text3e.add("    1. Authenticate your login information");
		text3e.add(Chunk.NEWLINE);
		text3e.add("    2. Enable our security features:");
		text3e.add(Chunk.NEWLINE);
		text3e.add("    3. Show you advertising");
		text3e.add(Chunk.NEWLINE);
		text3e.add("    4. Improve and develop the features of the Website.");
		text3e.add(Chunk.NEWLINE);

		text3e.add(
				"Please feel free to change your browser settings if you do not wish to accept cookies. However, please note that changing your browser setting may affect your experience on the Website.\r\n"
						+ "Collection of Sensitive personal data or information: We do not collect, store or process Sensitive personal data or information as part of our services on our Website.");
		text3e.add(Chunk.NEWLINE);

		Paragraph text3j = new Paragraph();
		text3j.setLeading(0, 2);
		text3j.setFont(boldFont);
		text3j.add("Others");
		Paragraph text3k = new Paragraph();
		text3k.setLeading(0, 2);
		text3k.setFont(font5);
		text3k.add(
				"In addition to the cookies, we may also collect information to enable us to better understand you so that we can improve your user experience; to assist our customer service and technical support personnel; and put processes in place to prevent fraud and unlawful use. In an effort to make the Website effective and improve the Website, certain information may be collected each time you access the Website. Such information may be saved in server logs in an encrypted form which may not identify you personally. Such information or data may include, but shall not limit to, IP address, your server details, duration of your visit, date, time or purpose of your visit. "
						+ "In addition to cookies we use web beacons, ad tags, pixels to advertise as part of the Website. The above-mentioned data may be used by us and may be shared with our sponsors, investors, advertisers, developers, strategic business partners or other associates to enhance and grow the user experience in relation to the Website."
						+ "Other uses of the information provided: We make all efforts to ensure that we collect only such Personal Information that we believe to be relevant in order to record, support, facilitate your access to the Website."
						+ "Sharing information with Third Party service providers in relation to the Website: To the extent necessary to provide you the Website, we may provide your Personal Information to Third Party contractors who work on behalf of or with us to provide you with such services, to help us communicate with you or to maintain the Website."
						+ "Communication: We may offer email, app notifications, short message service (sms), multimedia message service (mms) or other forms of communication to share information with you about certain promotions or features the Website may choose to offer or about our affiliates, subsidiaries, business partners, advertisers and sponsors. You may receive such communication when you have registered as a user."
						+ "Legal proceedings: In the event we are required to respond to subpoenas, court orders or other legal process, your Personal Information may be disclosed pursuant to such subpoena, court order or legal process, which may be without notice to you.");

		Paragraph text3f = new Paragraph();
		text3f.setLeading(0, 2);
		text3f.setFont(boldFont);
		text3f.add("Security Statement");
		Paragraph text3g = new Paragraph();
		text3g.setLeading(0, 2);
		text3g.setFont(font5);
		text3g.add(
				"We strive to ensure the security, integrity and privacy of your Personal Information and to protect your Personal Information against unauthorized access or unauthorized alteration, disclosure or destruction. We are not responsible for any breach of security or for any actions of any Third Parties that receive your Personal Information."
						+ " The Services also linked to many other sites and we are not/shall be not responsible for their privacy policies or practices as it is beyond our control."
						+ "Notwithstanding anything contained in this Privacy Policy or elsewhere, we shall not be held responsible for any loss, damage or misuse of your Personal Information, if such loss, damage or misuse is attributable to a Force Majeure Event.");
		Paragraph text3h = new Paragraph();
		text3h.setLeading(0, 2);
		text3h.setFont(boldFont);
		text3h.add("How to Contact Us");
		Paragraph text3i = new Paragraph();
		text3i.setLeading(0, 2);
		text3i.setFont(font5);
		text3i.add(
				"We will attempt to keep your Personal information complete, accurate and up-to-date. We can assist if you wish to access and/or make changes to your "
						+ "Personal Information. You may address any queries, concerns or complaints relating to handling of your personal information by contacting us at :");
		text3i.add(Chunk.NEWLINE);
		text3i.add("Department : Customer Service");
		text3i.add(Chunk.NEWLINE);
		text3i.add("Telephone : 0122902076");
		text3i.add(Chunk.NEWLINE);

		//text3i.add("Address : Suite 07-01, Wisma UOA Damansara II, Damansara Heights, W.P.Kuala Lumpur");
		text3i.add("Address : 17-109, Equatorial Plaza, Jalan Sultan Ismail, 50250 Kuala Lumpur, Malaysia");
		text3i.add(Chunk.NEWLINE);
		text3i.add("Email : cs@gomobi.io");
		text3i.add(Chunk.NEWLINE);

		text3i.add("Website : https://gomobi.io/");
		text3i.add(Chunk.NEWLINE);
		text3i.add(
				"*This Privacy Policy may be revised from time to time. We will post notice of any amendment on this Privacy Policy on the website and/or by such other means of communication deem suitable by Mobi.");
		text3i.add(Chunk.NEWLINE);
		pdfDoc.add(text3);
		pdfDoc.add(text3a);
		pdfDoc.add(text3b);
		pdfDoc.add(text3c);
		pdfDoc.add(text3d);
		pdfDoc.add(text3e);
		pdfDoc.add(text3j);
		pdfDoc.add(text3k);
		pdfDoc.add(text3f);
		pdfDoc.add(text3g);
		pdfDoc.add(text3h);
		pdfDoc.add(text3i);
		// endregion

		pdfDoc.newPage();
		Paragraph merchantagreement = new Paragraph();
		merchantagreement.setLeading(0, 2);
		merchantagreement.setFont(font6Bold);
		merchantagreement.add("MOBI MERCHANT AGREEMENT");
		Paragraph merchantagreementa = new Paragraph();
		merchantagreementa.setLeading(0, 2);
		merchantagreementa.setFont(font6Blue);
		merchantagreementa.add("This Agreement is made on the date as stated herein between: -");
		merchantagreementa.add(Chunk.NEWLINE);
		merchantagreementa.add(
//				"(A)	Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) Company no: 1105429-U), incorporated in and under the laws of Malaysia with registered address at Suite 2.7, Level 2, Block C Plaza Damansara, 45 Jalan Medan Setia 1, Bukit Damansara, Kuala Lumpur, Wilayah Persekutuan. (hereinafter called �Mobiversa�);  AND ");
				"(A)    Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) Company no: 201401029343 (1105429-U), incorporated in and under the laws of Malaysia with registered address at Suite 2.7, Level 2, Block C Plaza Damansara, 45 Jalan Medan Setia 1, Bukit Damansara, Kuala Lumpur, Wilayah Persekutuan. (hereinafter called �Mobiversa�);  AND ");	
		merchantagreementa.add(Chunk.NEWLINE);
		merchantagreementa.add(
				"(B)	The party and / or parties described in Merchant Application Form hereto and this Agreement (hereinafter called �the Merchant�). Whereas: -");
		merchantagreementa.add(Chunk.NEWLINE);
		merchantagreementa.add(
				"(C)	Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) is engaged in the business of operating a payment solutions and services, including but not limited to merchant recruitment and deployment of payment solutions for card payment and mobile wallet payment under the brand name of �MOBIVERSA� as further described in Clause 2.1 herein (hereinafter called �Mobisolutions�).");
		merchantagreementa.add(Chunk.NEWLINE);
		merchantagreementa.add(
				"(D)	The Merchant is desirous to sign this Agreement with Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) concerning the terms and conditions under which the Merchant agree to accept payment in the business of selling its goods and/or services using the Mobisolutions. ");

		Paragraph merchantagreementb = new Paragraph();
		merchantagreementb.setLeading(0, 2);
		merchantagreementb.setFont(font6BoldUnderline);
		merchantagreementb.add("1.	DEFINITIONS");
		Paragraph merchantagreementc = new Paragraph();
		merchantagreementc.setLeading(0, 2);
		merchantagreementc.setFont(font6Blue);
		merchantagreementc.add(
				"1.1 In this Agreement, the following terms and expressions shall have the meaning assigned to them unless the context requires otherwise: - ");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"�Agreement� means this Mobi Merchant Agreement together with any Merchant Application Form, Welcome Letter, Sales Quotation and any other documents or notices issued pursuant to this Agreement.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"�Authorisation� means the process of obtaining an approval code from an Issuer and/or Processor for Card Payment Transaction and/or a Mobile Wallet Transaction(s).");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"�Boost� or �Boost App�means a brand name and a mobile wallet electronic payment service provided by Axiata Digital Ecode Sdn Bhd.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"�Boost Transaction� means a payment to the Merchant by a Customer through the use of Boost for a purchase of goods andmerchantagreementc.add(or services.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"�Business Day� means any day (except Saturdays, Sundays and Public Holidays) on which Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) is opened for business.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"�Cardholder� means any person who is the holder of a Payment Card and uses it to pay of goods and/or services from a Merchant. ");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"�Card Schemes� means individually or collectively (as applicable) VISA, MasterCard, UnionPay and any other Payment System Operators as approved by Bank Negara Malaysia for which services are made available via Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) for the Merchant.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"�Chargeback� means a reversal of a Payment Card Transaction or a Mobile Transaction previously credited to the Merchant�s settlement account by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) because the Customer disputes the transaction in accordance with the rules and regulations of the relevant approved Payment System Operator or Processor.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"�Charge Card� means a Payment Card which has a prescribed monthly spending limit granted by the Issuer to the Cardholder and any amount utilized by the Cardholder must be settled in full on or before a specified date.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"�Credit Card� means a Payment Card which indicates a line of credit or financing granted by the issuer to the Cardholder and where any amount of the credit utilized by the Cardholder has not been settled in full on or before a specified date the unsettled amount may be subject to interest, profit or other Payment Card Transaction until full settlement is made. ");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"�Customer� means any person who is the user of a Payment Card or Mobile Wallet and uses it to pay of goods and/or services from a Merchant.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"�Debit Card� means a Payment Card where the transaction amount is deducted directly from the Cardholder�s current or saving�s bank account upon Authorisation.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"�Ecode� means Axiata Digital Ecode Sdn Bhd, Level 32, Axiata Tower, 9 Jalan Stesen Sentral 5, Kuala Lumpur Sentral, 50470 Kuala Lumpur, Malaysia.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"�EMV� means the global standard managed by EMVCO to facilitate worldwide interoperability and acceptance of Payment Card Transactions.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"�E-Money Issuers� means individually or collectively (as applicable) U Mobile/U Mobile App, GrabPay/GrabPay App, Boost/Boost App, Touch n� Go, WeChat/WeChat App and any other E-Money Issuers as approved by Bank Negara Malaysia for which services are made available via Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) for the Merchant.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"�Full Recourse� means Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) right to payment from Merchant for the full amount of the Payment Transaction as set out in these terms and conditions.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"�GrabPay� or �GrabPay App� means a brand name and a mobile wallet electronic payment service provided by GPay Network (M) Sdn. Bhd.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				" �GrabPay Transaction� means a payment to the Merchant by a Customer through the use of GrabPay for a purchase of goods and/or services.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"�Issuer� means any bank or financial institution or other organization or institution authorized to issue a Payment Card and their respective successors-in-title and assigns. ");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				" �MasterCard� means MasterCard Worldwide Inc., 2000 Purchase Street, Purchase, and NY10577 United States of America and includes its successors�in-title and assigns which is an approved operator of a payment system. ");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"    �Merchant� means the party and / or parties described in Merchant Application Form hereto and this Agreement and who is a business entity or other person or firm or corporation, its employees, servants or agents which pursuant to this Agreement");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"    �MID� means the unique identification number assigned by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to the Merchant under the terms of this Agreement for each of the Services and/or outlets as the case may be as further mentioned in the Welcome Letter and/or Merchant Application Form. ");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"    �MDR� means the Merchant discount rate chargeable by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to the Merchant at the agreed rate in per centum of the Payment Card Transaction or Mobile Wallet Transaction value shown on the transaction slip(s) or any other revised rate or rates as determined by Mobiversa.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"    �Mobile Wallet� means an e-wallet payment structure for the implementation and operation of an electronic payment system utilizing web accounts which includes internet banking and/or credit card payment.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"    �Mobisolutions Card Acceptance Device� or �Mobisolutions Terminal� means all electronic equipment such as electronic draft capture (EDC) terminals and printers, cardholder identification devices (if any), any device attached with a chip reader, and/or magnetic stripe and/or contactless feature, with Pin Pad and/or Printer, and/or any other device terminals which are provided by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to facilitate the processing of the Payment Card Transactions.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"    �Mobisolutions Mobile Application� means a one or more iPhone and Android mobile applications made available to Merchant which are part of the Mobisolutions Service.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"    �Mobisolutions Service� or �the Service� means the services provided by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to the Merchant to process the Payment Card Transactions of the Merchant under the terms of this Agreement as further described in the Welcome Letter.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"    �Payment Card� means any DPI or any Credit Card, Debit Card, Charge Card, Prepaid Card or any other payment instrument that is associated with or bears the logo of any approved Card Schemes, and any reference to �Payment Card� shall include a reference to both consumer and commercial cards which are issued by an Issuer from time to time and which are embedded with the EMV compliant Chip with or without the PIN and/or magnetic stripe feature for acceptance by the Merchant under the terms of this Agreement as further described in the Welcome Letter. ");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"    �Payment Card Transaction(s)� means the payment to the Merchant by a Customer through the use of a Payment Card for a purchase of goods and/or services.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"    �PIN� means in relation to a Cardholder, the Personal Identification Number of the Cardholder which is to be used by the Cardholder to validate a Payment Card Transaction.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"    �Processor(s)� means a licensed financial institution and is authorized to process Payment Card Transaction and or Mobile Wallet Transaction.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"    �Referral Response� means a response received at the Mobisolutions mobile application during the Authorization process which requires the Merchant to contact Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) for further instructions before completing the transaction.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"    �Retrieval Request� also known as a copy request which is made by the Issuer by a Cardholder to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to obtain a copy of the Transaction Slip(s) needed and/or any other form of documentation(s) for a particular transaction from the Merchant.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"    �Settlement Function� means the procedures required of and carried out by the Merchant via the Mobisolutions mobile application for the purposes of transmitting the data consisting of all the Payment Card Transactions and mobile wallet Transaction on a daily basis to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to enable Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to submit the payment claims to the Issuing Bank through either the Card Schemes Payment System or mobile wallet.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"    �Split Sale� means the process by which a Merchant split a single sale into more than one transaction to avoid authorization limits. ");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add("    �TransactionSlip (s)� means any of the following:");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"        a) �Authorization Payment Form� means the relevant forms, notices or papers used in connection with EZYMOTO transactions upon which Cardholder has in writing authorized the Merchant, Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) and Issuer to charge the amount contained thereon to the Cardholder�s account with the Issuer. ");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"        b) �Enrollment Form� means the relevant forms, notices or papers used in connection with the Bill Payment Services upon which Cardholder has in writing authorized the Merchant, Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) and the Issuer to charge the amount contained thereon to the Cardholder�s Card account with the Issuer. ");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"        c) �SalesSlip� means the relevant documents generated electronically after the completion of a Payment Transaction arising from the use of a Mobisolutions mobile application to charge the amount contained thereon to the Customer�s Payment Card account with the Issuer or mobile wallet account. ");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"        d) �Terminal Identification Number (TID)� means as an identification number assigned to each Mobisolutions card acceptance device and Mobisolutions mobile application by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to a Merchant to identify the source of a Payment Card Transaction.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"        e) �Touch n� Go� means a brand name and a mobile wallet electronic payment service provided by Touch n� Go Sdn Bhd.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"        f) �U Mobile� or �U Mobile App� means a brand name and a mobile wallet electronic payment service provided by U Mobile Sdn Bhd.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"        g) �U Mobile Transaction� means a payment to the Merchant by a Customer through the use of U Mobile for a purchase of goods and/or services.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"        h) �Union Pay� means Union Pay International Co., Ltd., Building B, Poly Plaza, No.6 Dongfang Road, Pudong New District, Shanghai, China and includes its successors-in-title and assigns which is an approved operator of a payment system.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"        i) �VISA� means Visa International Inc., P.O. Box 8999, San Francisco, CA 94128, United States of America and includes its successors-in-title and assigns which is an approved operator of a payment system.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"        j) �WeChat� or �WeChat App� means a brand name and a mobile wallet electronic payment service provided by Tencent Holdings Ltd.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"        k) �WeChat Transaction� means a payment to the Merchant by a Customer through the use of WeChat for a purchase of goods and/or services.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add("1.2 Words importing the singular number include the plural number and vice versa.");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"1.3 Words importing the masculine gender include the feminine and neuter gender and vice versa. ");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"1.4 Words are applicable to natural persons include anybody or persons, company, incorporation, firm or partnership corporate or unincorporated. ");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"1.5 The headings to the clauses of this Agreement are for reference only and shall not affect the interpretation and/or enforcement of the provisions of this Agreement. ");
		merchantagreementc.add(Chunk.NEWLINE);
		merchantagreementc.add(
				"1.6 Merchant Application Form, Welcome Letter and any other documents or notices issued by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) from time to time shall form and be construed as part of this Agreement.");

		Paragraph merchantagreementd = new Paragraph();
		merchantagreementd.setLeading(0, 2);
		merchantagreementd.setFont(font6BoldUnderline);
		merchantagreementd.add("2.	THE SERVICE");
		Paragraph merchantagreemente = new Paragraph();
		merchantagreemente.setLeading(0, 2);
		merchantagreemente.setFont(font6Blue);
		merchantagreemente.add(
				"2.1	The Mobisolutions Service or the Service which is provided under this Agreement, consists of all or selected services as follows:");
		merchantagreemente.add(Chunk.NEWLINE);
		merchantagreemente.add(Chunk.NEWLINE);
		merchantagreemente.add(Chunk.NEWLINE);

		merchantagreemente.add(
				"    (a)obtaining authorization or authorization of Payment Card Transactions and E-Money Issuers Transaction(s) ;");
		merchantagreemente.add(Chunk.NEWLINE);
		merchantagreemente.add(
				"    (b)providing an MID and the required number of TID, Mobisolutions mobile application and the access granted by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to merchant;");
		merchantagreemente.add(Chunk.NEWLINE);
		merchantagreemente.add("    (c)EZYWIRE card acceptance device and EZYWIRE electronic terminal printer;");
		merchantagreemente.add(Chunk.NEWLINE);
		merchantagreemente
				.add("    (d)outclearing of Payment Card Transactions to the appropriate PSO and/or card issuers;");
		merchantagreemente.add(Chunk.NEWLINE);
		merchantagreemente.add("    (e)outclearing of Boost Transaction to Ecode;");
		merchantagreemente.add(Chunk.NEWLINE);
		merchantagreemente.add("    (f)outclearing of GrabPay Transaction to GPay Network (M) Sdn. Bhd;");
		merchantagreemente.add(Chunk.NEWLINE);
		merchantagreemente.add(
				"    (g)receiving of payment from Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) after performing asettlement function;");
		merchantagreemente.add(Chunk.NEWLINE);
		merchantagreemente.add("    (h)dispute resolution with cardholders� banks or Ecode;");
		merchantagreemente.add(Chunk.NEWLINE);
		merchantagreemente.add("    (i)dispute resolution with cardholders� banks or GPay Network (M) Sdn. Bhd;");
		merchantagreemente.add(Chunk.NEWLINE);
		merchantagreemente
				.add("    (j)transaction-related reporting, statements and other related products & services.");
		merchantagreemente.add(Chunk.NEWLINE);
		merchantagreemente.add(
				"2.2The Merchant shall ensure that all Payment Card Transactions and E-Money Issuers Transaction(s) transacted at any of the Merchant outlets and/or through any of the service granted to the Merchant shall use/quote the correct MID assigned by Mobi. ");
		merchantagreemente.add(Chunk.NEWLINE);
		merchantagreemente.add("2.3The Welcome Letter");
		merchantagreemente.add(Chunk.NEWLINE);
		merchantagreemente.add(
				"The Welcome Letter means the Services Letter issued by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to the Merchant pursuant to this Agreement shall form part of this Agreement, and shall be read, taken and construed as an essential part of this Agreement. In the event of inconsistencies between the terms in the Welcome Letter and this Agreement, the terms in the former shall prevail. ");
		merchantagreemente.add(Chunk.NEWLINE);
		merchantagreemente.add("2.4Monthly Terminal Limit (MTL)");
		merchantagreemente.add(Chunk.NEWLINE);
		merchantagreemente.add(
				"    (a)MTLis a limit assigned to an MID by Mobi. This limit dictates the total amount of sales transactions that can be approved for that Merchant each month.");
		merchantagreemente.add(Chunk.NEWLINE);
		merchantagreemente.add(
				"    (b)Where the Merchant has been provided with the EZYWIRE card acceptance device, the MTL shall be set at RM10. Merchant shall submit the terminal setup letter to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to increase the MTL as prescribed in the Welcome Letter. ");
		merchantagreemente.add(Chunk.NEWLINE);
		merchantagreemente.add(
				"    (c)If the Merchant requires a temporary increase of MTL, the Merchant will be required to seek specific approval and/or written approval from Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) and provide the valid supporting documentations upon request. Any temporary MTL assigned shall be subject to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) discretion. ");
		merchantagreemente.add(Chunk.NEWLINE);
		merchantagreemente.add(
				"    (d)The Merchant will be notified that payments for such transactions pursuant to Clause 2.4(c) will be withheld for further review by Mobi. Merchant must also provide copies of ALL the Transaction Slip(s) and/or documents with details of the sales. ");
		merchantagreemente.add(Chunk.NEWLINE);
		merchantagreemente.add(
				"    (e)The temporary MTL shall be used until the expiry date given by Mobi. The MTL will revert to the original limit at the end of the expiry date. ");
		merchantagreemente.add(Chunk.NEWLINE);
		merchantagreemente.add(
				"    (f)If the Merchant requires a permanent increase of MTL, the Merchant will be required to seek specific approval and/or written approval from Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) and provide the valid supporting documentations upon request. Any permanent revision to the MTL shall be subject to Mobiversa� s discretion. ");
		merchantagreemente.add(Chunk.NEWLINE);

		Paragraph merchantagreementf = new Paragraph();
		merchantagreementf.setLeading(0, 2);
		merchantagreementf.setFont(font6BoldUnderline);
		merchantagreementf.add("3. PAYMENT TRANSACTION ACCEPTANCE");
		Paragraph merchantagreementg = new Paragraph();
		merchantagreementg.setLeading(0, 2);
		merchantagreementg.setFont(font6Blue);
		merchantagreementg.add(
				"3.1	Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) may at the request of the Merchant agree to and accept the following mode of Payment Transactions: -");
		merchantagreementg.add(Chunk.NEWLINE);
		merchantagreementg.add("    (a)Card Present Transaction; " + "    (b)Card Not Present Transaction; ");
		merchantagreementg.add(Chunk.NEWLINE);
		merchantagreementg.add("    (c)E-Money Issuers Transaction;   ");
		merchantagreementg.add(Chunk.NEWLINE);
		merchantagreementg.add(
				"    (d)Any other mode as Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) may deem fit from time to time");
		merchantagreementg.add(Chunk.NEWLINE);
		merchantagreementg.add(
				"3.2When a Customer uses a Payment Card or mobile wallet and is presented to the Merchant for payment, in accepting and honoring without discrimination any Payment Card prescribed by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) when properly presented by the Cardholder via the service granted by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to the merchant under the of this Agreement and further described in the Welcome Letter. It shall maintain a policy which merchant shall not discriminate any mode of Payment Transactions against other modes of payment, or discriminate Customer seeking to make payment using any mode of payment against Customers using other modes of payment, and impose any restrictions and/or conditions on the use any mode of payment other than that approved or prescribed by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) against the Customer seeking to make purchase of goods and/or services from the Merchant.");
		merchantagreementg.add(Chunk.NEWLINE);
		merchantagreementg.add(
				"3.3All Payment Card Transactions and E-Money Issuers Transactions shall be drawn in Ringgit Malaysia only, unless another currency is specified in the Welcome Letter. ");
		merchantagreementg.add(Chunk.NEWLINE);
		merchantagreementg.add(
				"3.4The Merchant must comply with the confidentiality provisions, a data storage prohibition etc prescribed by Law, Regulations and any approved operator of a Payment System and undertakes to store Cardholder�s personal and account information securely in order to prevent potential fraudulent activities. ");
		merchantagreementg.add(Chunk.NEWLINE);
		merchantagreementg.add(
				"3.5The three-digit security number (3-Digit Code) imprinted on the signature panel of Payment Card helps validate that the customer has a genuine Payment Card that is linked to a legitimate account. To prevent the 3-Digit Code from being compromised, the Merchant must NEVER keep or store a Payment Card�s 3-Digit Code once a Card Transaction has been completed. Such action is prohibited and could result in severe penalties. ");
		merchantagreementg.add(Chunk.NEWLINE);
		merchantagreementg.add("3.6Payment Transaction Acceptance Procedures");
		merchantagreementg.add(Chunk.NEWLINE);
		merchantagreementg.add(
				"When any of the below facility is offered to the Merchant by Mobi, Merchant shall strictly abide with the Terms & Conditions of the facility which will be further set out in the Welcome Letter. ");
		merchantagreementg.add(Chunk.NEWLINE);
		merchantagreementg.add("    (a)Payment Card Acceptance" + "    (b)E-Money Issuers Transaction Acceptance");
		merchantagreementg.add(Chunk.NEWLINE);
		merchantagreementg.add("    (c)Payment Card Acceptance: Pre-Authorization (Pre-Auths)");
		merchantagreementg.add(Chunk.NEWLINE);
		merchantagreementg.add("    (d)Payment Card Acceptance: Mail Order Telephone Order");
		merchantagreementg.add(Chunk.NEWLINE);
		merchantagreementg.add("    (e)Payment Cardmerchantagreementgmerce");
		merchantagreementg.add(Chunk.NEWLINE);
		merchantagreementg.add("    (f)Payment Card Acceptance: Recurring Payment");
		merchantagreementg.add(Chunk.NEWLINE);

		Paragraph merchantagreementh = new Paragraph();
		merchantagreementh.setLeading(0, 2);
		merchantagreementh.setFont(font6BoldUnderline);
		merchantagreementh.add("4.	PAYMENT BY MOBIVERSA");
		Paragraph merchantagreementi = new Paragraph();
		merchantagreementi.setLeading(0, 2);
		merchantagreementi.setFont(font6Blue);
		merchantagreementi.add(
				"4.1	Upon completion of the Payment Transaction Acceptance Procedures, the Merchant shall carry out or perform a Settlement Function on a daily basis through the Mobisolutions mobile application. Merchant has to ensure the daily settlement amount tally with the daily merchant transaction report amount, to enable Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to submit and claim the transactions from the Issuing Bankand/or E-Money Issuers so as to allow Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to make payment to the Merchant as mutually agreed between Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) and the Merchant as further described in the Welcome Letter. Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) shall be entitled to deduct the MDR, GST, fees, any amount to be withheld and all other relevant payments due to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) under this Agreement from the amount of Payment Card Transactions and/or mobile wallet Transaction presented to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) before the Merchant is paid the balance of such Payment Transactions on each occasion. ");
		merchantagreementi.add(Chunk.NEWLINE);
		merchantagreementi.add(
				"4.2Upon receipt of payment from Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.), the Merchant shall reconcile their sales records accounts with Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) (including settlements and payments between the parties) against the report provided by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) or any other Merchant�s reconciliation methods within fourteen (14) days from the date of the respective settlement dates. If discrepancies or non-receipt of payment on any of the transaction item is detected, Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) is to be notified of the same within 7 (seven) days from the date of the report failing which Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) is entitled at its sole and absolute discretion to refuse any request from the Merchant to carry out any investigations on any discrepancies or inaccuracies referred to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) and/or make any adjustments. If Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) decides to carry out any investigations on any discrepancies or inaccuracies and/or make any adjustments, the Merchant must bear and pay any charges, costs and penalty interests for late settlement and/or adjustments that may be levied on or incurred by Mobiversa. ");
		merchantagreementi.add(Chunk.NEWLINE);
		merchantagreementi.add(
				"4.3Payment by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to the Merchant does not constitute confirmation that the transactions are accepted according to the conditions and procedures stated herein free of irregularity or any violation and shall be subject to refusal or recovery action by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) in accordance with Clause 5 hereof, or withholding or refusal of payment under Clause 6 or Clause 12. ");

		merchantagreementi.add(Chunk.NEWLINE);
		merchantagreementi.add(
				"4.4Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) shall impose a processing fee on any reversal or cancellation of a Payment Card Transaction and mobile wallet Transaction that it performs on behalf of the Merchant. Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) will not refund to the Merchant the MDR or any part thereof that it had deducted from the earlier payment made to the Merchant. ");

		merchantagreementi.add(Chunk.NEWLINE);
		merchantagreementi.add(
				"4.5All payments by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to the Merchant shall be made in Ringgit Malaysia only, unless another currency is specified in the Welcome Letter. ");
		merchantagreementi.add(Chunk.NEWLINE);
		merchantagreementi.add(
				"4.6A statement which is also a tax invoice showing the daily settlement proceeds collected and paid to Merchant will be sent to Merchant�s email address monthly.");

		merchantagreementi.add(Chunk.NEWLINE);
		merchantagreementi.add(
				"4.7All unpaid settlements other than those mentioned in Clause 4.3 above shall be notified to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) in writing with the settlement slip, supporting Transaction Slip enclosed within twenty (20) days from the date of the statement. Failure to do so, will result in withholding the unpaid settlement to the Merchant up to one hundred and eighty (180) days. ");

		merchantagreementi.add(Chunk.NEWLINE);
		merchantagreementi.add("4.8Withholding of Payment");
		merchantagreementi.add(Chunk.NEWLINE);
		merchantagreementi.add(
				"    4.8.1In the event that an amount is withheld by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) under Clause 5 or for any irregularities that are detected via any Payment Transaction or Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) has reason to believe that the Payment Transaction (s) or MOTO Authorization Payment presented have not been issued for legitimate transactions or being fraudulent or unauthorized by the customer(s) or illegal or in which any of circumstances set out herein appears to exist, Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) is entitled to withhold payment until Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) has examined and verified acceptable supporting documentation and in the event that Payment Card Transaction, E-Money Issuers Transation or the Transaction Slip or MOTO Authorization Payment are, in Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) opinion, not valid, or rejected by Card Schemes or mobile wallet providers, no repayment of the amount withheld shall be made by Mobiversa. The Merchant shall irrevocably authorize Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to withhold from the Settlement any amount owing to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) pursuant to this Agreement. ");
		merchantagreementi.add(Chunk.NEWLINE);
		merchantagreementi.add("    4.8.2The terms in this Clause shall survive the termination of this Agreement. ");

		Paragraph merchantagreementj = new Paragraph();
		merchantagreementj.setLeading(0, 2);
		merchantagreementj.setFont(font6BoldUnderline);
		merchantagreementj.add("5.	RECOVERY OF PAYMENT TRANSACTION FROM MERCHANT");
		Paragraph merchantagreementk = new Paragraph();
		merchantagreementk.setLeading(0, 2);
		merchantagreementk.setFont(font6Blue);
		merchantagreementk.add(
				"5.1	It is hereby expressly agreed that Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) shall be entitled to claim the full amount of the payment transaction under Clause 5.3 or to refuse any payment to the Merchant and/or reject any Payment Card Transaction and/or mobile wallet Transaction presented by the Merchant for payment, and where payment has been made by Mobiversa, to off-set against the relevant amount successfully withheld such payment in any of the following circumstances: - ");
		merchantagreementk.add(Chunk.NEWLINE);
		merchantagreementk.add(
				"    (a)if this Agreement is terminated by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) for any reason whatsoever; or");
		merchantagreementk.add(Chunk.NEWLINE);
		merchantagreementk.add(
				"    (b)the Merchant has failed to exercise the Payment Acceptance Procedures set out in Welcome Letter herein before and as a result Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) has suffered loss; or ");
		merchantagreementk.add(Chunk.NEWLINE);
		merchantagreementk.add(
				"    (c)the transaction has been performed by the Merchant using an incorrect MID which differs from the Payment Acceptance Service MID which is granted to the Merchant by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) under the terms of this Agreement; or ");
		merchantagreementk.add(Chunk.NEWLINE);
		merchantagreementk
				.add("    (d)the Merchant did not process the transaction in compliance with the terms herein; or ");

		merchantagreementk.add(Chunk.NEWLINE);
		merchantagreementk.add(
				"    (e)the transaction was incomplete and was or has been discovered to be fraudulent, altered, ineligible or illegal; or ");
		merchantagreementk.add(Chunk.NEWLINE);
		merchantagreementk.add(
				"    (f)the value of the Transaction Slip exceeds the Authorized MTL (unless otherwise authorized by Mobi) or if the transaction is found to be a Split Sale; or");
		merchantagreementk.add(Chunk.NEWLINE);
		merchantagreementk.add("    (g)the transaction is found to be a duplicate transaction; or");
		merchantagreementk.add(Chunk.NEWLINE);
		merchantagreementk.add(
				"    (h)under a Card Present Transaction, the Cardholder�s signature capture in Mobisolutions mobile application is missing or differs from the signature appearing on the Cardholder�s Card or has not been properly authenticated or tampered with; or ");
		merchantagreementk.add(Chunk.NEWLINE);
		merchantagreementk.add(
				"    (i)the transaction is found to be one with a �Declined Authorized�, that is, where the Merchant has been previously notified by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) in response to an Authorization request that the particular Payment Card Transaction or E-Money Issuers Transation is not to be honored; or ");
		merchantagreementk.add(Chunk.NEWLINE);
		merchantagreementk.add(
				"    (j)the Transaction Slip does not bear an imprint of the Payment Card but is handwritten and/or where the Cardholder has refused payment to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) or where an authorized electronic terminal printer is present, the information in respect of the Payment Card including the Cardholder�s name, expiry date and Payment Card number are not electronically printed but is hand written or the entries on the Transaction Slip are incomplete or illegible; or ");
		merchantagreementk.add(Chunk.NEWLINE);
		merchantagreementk.add(
				"    (k)the Payment Card transaction was not entered into and/or authorized by the Customer or the transaction involved is a cash payment, cash disbursement or cash refund; or ");
		merchantagreementk.add(Chunk.NEWLINE);
		merchantagreementk.add(
				"    (l)the Payment Card concerned is found to have expired or is invalid for any reason whatever; or ");
		merchantagreementk.add(Chunk.NEWLINE);
		merchantagreementk.add("    (m)the Payment Card concerned has been listed in the Cancellation List; or ");
		merchantagreementk.add(Chunk.NEWLINE);
		merchantagreementk.add(
				"    (n)the transaction has been presented by the Merchant once before and payment has been made by Mobi; or ");
		merchantagreementk.add(Chunk.NEWLINE);
		merchantagreementk.add("    (o)the Customer disputed the sale transaction for whatsoever reason; or  ");
		merchantagreementk.add(Chunk.NEWLINE);
		merchantagreementk.add(
				"    (p)the Cardholder denies liability in respect of any transaction initiated or concluded by MOTO or Automatic Bill Settlement Services Transaction Acceptance irrelevant whether the Merchant has observed the terms and conditions of this Agreement or obtained an Authorization from the Issuer through Mobi; or ");
		merchantagreementk.add(Chunk.NEWLINE);
		merchantagreementk.add(
				"    (q)the credit or refund to a Customer by the Merchant who has issued a credit voucher or credit note to the Cardholder for the return of goods sold, service canceled, adjustment made, or otherwise; but has not provided the funds to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to process the reversal under Clause 6.1.1; or ");
		merchantagreementk.add(Chunk.NEWLINE);
		merchantagreementk.add(
				"    (r)the Merchant has performed a late Settlement Function or late submission to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) for whatsoever reason as mentioned herein before; or");
		merchantagreementk.add(Chunk.NEWLINE);
		merchantagreementk.add(
				"    (s)there is an investigation of the Payment Card Transaction or E-Money Issuers Transation by any governmental or regulatory authorities or police having jurisdiction over such matters; or");
		merchantagreementk.add(Chunk.NEWLINE);
		merchantagreementk.add(
				"    (t)upon receiving Chargeback or Retrieval Request from issuing bank or mobile wallet operators, Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) shall impose an administrative fee of RM20.00 plus applicable tax rate to the Merchant responsible for each request received.");
		merchantagreementk.add(Chunk.NEWLINE);
		merchantagreementk.add(
				"5.2Notwithstanding Clause 5.1, in the event of a breach of any of the provisions in this Agreement, Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) reserves the right to terminate this Agreement, refuse any payment under Clause 4 and the Merchant shall repay on demand all payments by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to the Merchant whatsoever which have been made in furtherance to this Agreement and until full repayment by the Merchant the said sum and all costs incurred in the enforcement of Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) rights under this Agreement (including solicitor and own client costs) shall be a debt due to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) and interest shall accrue thereon at the rate of 1.5% per month on monthly rests basis or such other rate or rates as Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) shall determine at its absolute discretion from time to time from the date of demand to the date of full settlement. ");
		merchantagreementk.add(Chunk.NEWLINE);
		merchantagreementk.add(
				"5.3In the event Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) effects a claim pursuant to Clause 5.1 above, the Merchant is required to pay upon demand by Mobiversa, the amount the payment card a fee at the rate imposed or to be imposed from time to time by any Card Schemes or the payment network operator or mobile wallet operator, as the case may be upon receiving a Chargeback or Retrieval Request from an Issuer. Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) shall also impose a processing fee at the prevailing published rate to the Merchant for each Chargeback or Retrieval Request received. ");

		Paragraph merchantagreementl = new Paragraph();
		merchantagreementl.setLeading(0, 2);
		merchantagreementl.setFont(font6BoldUnderline);
		merchantagreementl.add("6.	SPECIAL CIRCUMSTANCES");
		Paragraph merchantagreementm = new Paragraph();
		merchantagreementm.setLeading(0, 2);
		merchantagreementm.setFont(font6Blue);
		merchantagreementm.add("6.1	Authority to Refund (Payment Card Transaction)");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add(
				" 6.1.1Where an amount becomes owing by the Merchant to a Customer, whether for the return of merchandise, service canceled, adjustment made or otherwise, the Merchant shall issue an Authority to Refund to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) specifying the amount of the credit with sufficient details to identify the transaction as stated herein under Clause 6.1.2. Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) will refund the amount to the Cardholder after receipt of the required funds from the Merchant. The operation of the clause shall not in any way prejudice Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) rights under Clause 5.1. ");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add("    6.1.2Authority to Refund shall be completed with the following: -");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add("        (i)The Card account number;" + "        (ii)The date of transaction(s);");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add("        (iii)The amount of credit in Ringgit Malaysia; ");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm
				.add("        (iv)The Merchant�s imprinted name, official stamp, address or place of business; ");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add(
				"        (v)A description of the merchandise so returned, service canceled, adjustment made or otherwise; and ");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add("        (vi)Authorized signatory of the Merchant. ");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add(
				"    6.1.3The Merchant shall deliver a Report to Refund Form to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) within two (2) business days from the date of the occurrence of the transaction. ");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add("6.2Refund (Mobile Wallet Transaction)");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add(
				"    6.2.1Where the Customer made payment via mobile wallet App and thereafter request for a refund. If the Merchant agrees to such refund: -");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add(
				"        (a)on the same day when the relevant Transaction took place (the �Transaction Day�), the Merchant shall take necessary steps to void/ cancel the mobile wallet Transaction through the relevant feature available on the mobile wallet App and make sure that such cancellation is relayed to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) on the same day. The Merchant shall then be responsible for the refund to such Customer;");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add(
				"        (b)after the Transaction Day, the Merchant shall send a report of refund to Mobi. Thereafter Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) shall conduct its investigation and in case the request is found to be valid and lawful, mobile wallet operators shall refund the Customer Charge to the Customer�s mobile wallet within seven (7) Business Days following the date of refund request is accepted by mobile wallet operators. If the Merchant chooses to refund to the Customer via cash or vouchers, Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) and mobile wallet operatordsis entitled to retain the relevant MDR of the mobile wallet Transaction, other fees and charges.");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add(
				"    6.2.2Mobi shall not be responsible in any manner whatsoever for any losses, claims, damages, costs and expenses incurred by the Customer and/or the Merchant arising from the Refund.");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add("6.3Prohibited Payment Card Transactions");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add(
				"The Merchant shall not accept a transaction other than bona fide purchases by Customer of goods and services from the Merchant and the supply and/or purchase and/or performance is not in breach of any laws or regulations of Malaysia or any other country. This means, by way of example and not limitation, that the Merchant may not accept a Payment Card Transaction for any of the following: -");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm
				.add("    (a)Gambling goods or services;" + "    (b)Pornographic goods or services or prostitution;");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm
				.add("    (c)Goods or services for which the provision thereof is illegal (e.g. drug trafficking);");

		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add(
				"    (d)Sales where the amounts charged do not correspond with the value of the goods or services purchased or rendered;");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add("    (e)Sales made under a name which is different from the name of the Merchant;");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add("    (f)Sales made by a third party e.g. not the Merchant");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add(
				"    (g)Sales where the Merchant know or ought to know that the goods sold, or services provided are in copyright-infringing products and/or counterfeit trademark products and/or copyright unlocking devices which includes Mod chips or other devices designed to circumvent copyright protection; ");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add(
				"    (h)Damages, penalties, fines, charges, costs or fees of any kind which are in addition to the value of the Payment Card Transaction for the goods or services originally purchased or rendered;");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add(
				"    (i)Payment Card Transactions and/ or mobile wallet Transaction which do not represent a bona fide sale of goods or services at the Merchant;");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add(
				"    (j)Obtaining cash for anyone (including the Merchant) by seeking payment from Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) for Payment Card Transactions where the Merchant did not supply goods or services to a Cardholder; or ");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add(
				"    (k)Any Payment Card Transactions made by using the Merchant�s own Payment Card through the Merchant�s POS Terminal. ");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add("6.5Right to Off-Set and Consolidation");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add(
				"Notwithstanding any other rights available to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) under this Agreement, Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) may at its absolute discretion, and at any time without notice or assigning reason thereof withhold and, off-set against Merchant�s settlement claims or debit any or all of the Merchant settlement accounts with Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) of whatever description and wherever located for the purpose of the amount withheld under Clause 5 or towards the reduction or discharge of any sum due to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) by the Merchant under any of the terms of this Agreement. Interest at the rate of 8% per annum will be charged to the Merchant (including after judgement) in the event any outstanding sum due to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) is unpaid after 7 days. ");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add("6.6Suspension");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add(
				"Notwithstanding any other rights available to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) under this Agreement, Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) shall be entitled to suspend the Merchant from accepting any Payment Transaction under the terms of this Agreement with immediate effect for a period of time which shall be identified by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) upon the occurrence of any of the following: -");

		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add(
				"    (a)In Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) reasonable opinion that the Merchant is engaged in prohibited, irregular or fraudulent or illegal transactions: or ");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm.add(
				"    (b)The Merchant is identified by any Card Schemes or suspected by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to be the source of compromised Payment Card information: or");
		merchantagreementm.add(Chunk.NEWLINE);
		merchantagreementm
				.add("    (c)The Merchant has breached any warranty, terms and conditions of this Agreement.");

		Paragraph merchantagreementn = new Paragraph();
		merchantagreementn.setLeading(0, 2);
		merchantagreementn.setFont(font6BoldUnderline);
		merchantagreementn.add("7.	COVENANT BY Merchant");
		Paragraph merchantagreemento = new Paragraph();
		merchantagreemento.setLeading(0, 2);
		merchantagreemento.setFont(font6Blue);

		merchantagreemento.add(
				"(a)	The Merchant hereby covenants with Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) as follows: - It shall at all times observe the guidelines and procedures on the Payment Transaction Acceptance as instructed and required by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) as set out herein before including but not limited to the provisions in Clause 3 hereof;");
		merchantagreemento.add(Chunk.NEWLINE);
		merchantagreemento.add(
				"    (b)Unless otherwise provided by any written law for the time being in force, it shall not impose or require Cardholder to pay any surcharge, commission, discount whether through any increase in price or otherwise, or any other term or condition whatsoever on any Cardholder desirous of using a Payment Card or a Mobile Wallet; ");
		merchantagreemento.add(Chunk.NEWLINE);
		merchantagreemento.add(
				"    (c)It shall not impose a minimum transaction amount below which the Merchant shall refuse to honor the Payment Transactions ");
		merchantagreemento.add(Chunk.NEWLINE);
		merchantagreemento.add(
				"    (d)It shall include in the value of the Transaction Slip any tax or carrier charges required to be collected and shall not collect it separately in cash; ");
		merchantagreemento.add(Chunk.NEWLINE);
		merchantagreemento.add(
				"    (e)It shall at all times the Merchant shall ensure its business are legally and validly established and observe all prevailing laws and regulations; ");

		merchantagreemento.add(Chunk.NEWLINE);
		merchantagreemento.add(
				"    (f)It shall observe and perform all obligations under its Payment Card Acceptance/contracts with the Cardholder including but not limited to the nature, quality and delivery of goods and service contracted to be sold and supplied to the Cardholders; ");
		merchantagreemento.add(Chunk.NEWLINE);
		merchantagreemento.add(
				"    (g)It shall not reveal, sell, purchase, provide or exchange Payment Card account number, security code or PIN (where applicable) and any other information in any form obtained by reason of Payment Card transactions to any third party; ");
		merchantagreemento.add(Chunk.NEWLINE);
		merchantagreemento.add(
				"    (h)It shall obtain approval from Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) in writing prior to any publication or advertisement of promotional materials relating to the new Payment Card or Mobile Wallet; ");
		merchantagreemento.add(Chunk.NEWLINE);
		merchantagreemento.add(
				"    (i)It shall adequately display the Card Schemes Marks or mobile wallet Marks and any distinctive features of the Payment Card and/or product names on promotional materials provided by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to inform the public that the Payment Card and Mobile Wallet will be honored at the Merchant�s premises, place or business or its outlets.");
		merchantagreemento.add(Chunk.NEWLINE);
		merchantagreemento.add(
				"    (j)It shall, at all times maintain in good order and keep in safe custody all Transaction Slips, Credit Vouchers, and/or Mobi solutions card acceptance device supplied by Mobi; ");
		merchantagreemento.add(Chunk.NEWLINE);
		merchantagreemento.add(
				"    (k)Where there is uncertainty or ambiguity in the terms of this Agreement or any guidelines or requirements pertaining to the use of the Card Schemes Marks or mobile wallet marks or any security or distinctive features of the new Payment Card and Mobile Wallet, the Merchant shall counter check with Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) for its construction and meaning;");
		merchantagreemento.add(Chunk.NEWLINE);
		merchantagreemento.add(
				"    (l)It shall use its best endeavor to promote the use of Payment Cards or Mobile Wallet and to render its cooperation to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) and Customer in connection with the use of the Payment Card or Mobile Wallet; ");
		merchantagreemento.add(Chunk.NEWLINE);
		merchantagreemento.add(
				"    (m)It shall and maintain and take all necessary steps to maintain the security and confidentiality of the Merchant�s Website, Merchant�s POS, the Electronic Commerce Transactions, the Cardholders and Mobi; ");

		merchantagreemento.add(Chunk.NEWLINE);
		merchantagreemento.add(
				"    (n)It shall indemnify Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) should its employees or agents obtain a Payment Card Transaction with an intention to defraud the Payment Card and/or the PIN from the Cardholder through any means; ");
		merchantagreemento.add(Chunk.NEWLINE);
		merchantagreemento.add(
				"    (o)It shall indemnify and hold Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) harmless from and against all liabilities, claims, damages, losses, costs and expenses whatsoever, arising out of or in any way connected to the Merchant�s negligent act or omission in the operation of the Service; and ");
		merchantagreemento.add(Chunk.NEWLINE);
		merchantagreemento.add(
				"    (p)It shall operate the POS Terminal for its MOTO business in the manner specified by Mobi. ");
		merchantagreemento.add(Chunk.NEWLINE);
		merchantagreemento.add("    (q)Merchant�s Records");
		merchantagreemento.add(Chunk.NEWLINE);
		merchantagreemento.add(
				"        i.The Merchant shall at its own costs and capacity keep proper account and correct copies of all documents relating to the Payment Acceptance and/or contracts between the Merchant and the Cardholder including any Transaction Slip which are marked as the �Merchant Copy� resulting from the use of the Payment Card, and shall allow Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) at any reasonable time to inspect and/or take copies of all such documents, accounts and Transaction Slip or any Payment Transaction forms and shall preserve such documents and records for a period of at least eighteen (18) months from date of each transaction.");
		merchantagreemento.add(Chunk.NEWLINE);
		merchantagreemento.add(
				"        ii.The Merchant shall also provide a legible copy of the relevant Transaction Slip and/or any related documents upon request made by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) in writing to the Merchant for the purpose of Clause 5 and/or 4.8 hereof and shall be within any specific time required by Mobiversa. ");
		merchantagreemento.add(Chunk.NEWLINE);
		merchantagreemento.add(
				"        iii.The Merchant is obliged to provide full cooperation toward any investigations or inquiries made by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) relating to any of the Payment Card transactions which is within the period of eighteen (18) months from the transaction date including furnishing and delivering of the sale transaction documents to Mobiversa. ");

		Paragraph merchantagreementp = new Paragraph();
		merchantagreementp.setLeading(0, 2);
		merchantagreementp.setFont(font6BoldUnderline);
		merchantagreementp.add("8. FEES, DEPOSITS, SUBSCRIPTION AND CHARGES");
		Paragraph merchantagreementq = new Paragraph();
		merchantagreementq.setLeading(0, 2);
		merchantagreementq.setFont(font6Blue);
		merchantagreementq.add(
				"8.1	the Merchant shall pay to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) upon the execution of this Agreement the following fees as set out in Sales Quotation (if applicable)");
		merchantagreementq.add(Chunk.NEWLINE);
		merchantagreementq.add("(a)The Merchant Discount Rate (MDR); ");
		merchantagreementq.add(Chunk.NEWLINE);
		merchantagreementq.add("    (b)The deposit for Mobisolutions card acceptance device, ");
		merchantagreementq.add(Chunk.NEWLINE);
		merchantagreementq
				.add("    (c)The subscription fees" + "    (d)The maintenance fees" + "    (e)The support fees");
		merchantagreementq.add(Chunk.NEWLINE);
		merchantagreementq.add(
				"8.2Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) reserves the right to revise the MDR, the deposit for Mobisolutions card acceptance device, the subscription fees, the maintenance fees, the support fees or any other charges payable at any time and from time to time by giving written notice to the Merchant. Such change shall take effect from the date stated in the notice and if no date is stated from the date of the notice and payment of the MDR, the deposit for Mobisolutions card acceptance device, the subscription fees, the maintenance fees, the support fees or any other charges shall be made within the time period stated in the notice. ");
		merchantagreementq.add(Chunk.NEWLINE);
		merchantagreementq.add(
				"8.3The Merchant authorizes Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to deduct the MDR, the deposit for Mobisolutions card acceptance device, the subscription fees, the maintenance fees, the support fees or any other charges due from the Merchant to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) from the payment to be remitted by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to the Merchant in accordance to Clause 4. ");
		merchantagreementq.add(Chunk.NEWLINE);
		merchantagreementq.add(
				"8.4Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) may at any time at its absolute discretion impose a service fee or processing fee or such other charges as Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) may impose as provided herein under this Agreement. ");
		merchantagreementq.add(Chunk.NEWLINE);
		merchantagreementq.add("8.5Tax");
		merchantagreementq.add(Chunk.NEWLINE);
		merchantagreementq.add(
				"    8.5.1Tax is defined as any present or future, Malaysian or forex tax, levy, import, duty, charge, fee, deduction or withholding of any future, and any interest or penalties in respect thereof includes any tax payable on the supply of goods, services or other things in accordance with the Goods and Services Tax Act 2014 (GST), subsidiary legislation, statutory orders and regulations governing the application of GST, as amended from time to time.");
		merchantagreementq.add(Chunk.NEWLINE);
		merchantagreementq.add(
				"    8.5.2All monies, fees and charges payable by the Merchant to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) under this Agreement shall be made in full exclusive of any Tax, and without any set-off, restriction or condition and without any deduction for or on account of any counterclaim or any deduction or withholding of or in respect of any Tax.");
		merchantagreementq.add(Chunk.NEWLINE);
		merchantagreementq.add(
				"    8.5.3In the event the Merchant is required by law to make any additional payments, deduction or withholding from such monies, fees and charges payable to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) under this Agreement in respect of any Tax or otherwise, the sum payable by the Merchant in respect of which the deduction or withholding is required shall be increased so that the net monies, fees and charges received by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) is equal to that which Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) would otherwise have received had no deduction or withholding been required or made.");
		merchantagreementq.add(Chunk.NEWLINE);
		merchantagreementq.add(
				"    8.5.4In the event Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) is required by law to calculate and collect from the Merchant any amount paid or payable under this Agreement on account of any Tax, such amounts as calculated by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) and shall be paid by the Merchant as additional to and without any deduction or off-set from monies, fees and charges payable under this Agreement to Mobiversa.");
		merchantagreementq.add(Chunk.NEWLINE);
		merchantagreementq.add(
				"    8.5.5Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) and the Merchant shall be respectively liable for any GST payable in connection with or arising out of this Agreement or any services in connection therewith. ");

		Paragraph merchantagreementr = new Paragraph();
		merchantagreementr.setLeading(0, 2);
		merchantagreementr.setFont(font6BoldUnderline);
		merchantagreementr.add("9. TERMINATION");
		Paragraph merchantagreements = new Paragraph();
		merchantagreements.setLeading(0, 2);
		merchantagreements.setFont(font6Blue);
		merchantagreements.add(
				"9.1	Notwithstanding any other provision in this Agreement, either Party may give not less than one (1) month notice in writing in advance to the other Party to terminate this Agreement, Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) may forthwith terminate this Agreement if the Merchant;");
		merchantagreements.add(Chunk.NEWLINE);
		merchantagreements.add(
				"    (a)become insolvent or enter bankruptcy, receivership or administration or make an assignment for the benefit of its creditors generally; and/or");
		merchantagreements.add(Chunk.NEWLINE);
		merchantagreements.add(
				"    (b)if any step is taken for the winding-up, dissolution, liquidation or restructuring or a petition for winding-up (whether voluntary or otherwise) or bankruptcy proceedings, as the case maybe, is presented against the Merchant; and/or ");
		merchantagreements.add(Chunk.NEWLINE);
		merchantagreements.add(
				"    (c)if a receiver and/or manager have been appointed over the Merchant for any reason whatsoever; and/or ");
		merchantagreements.add(Chunk.NEWLINE);
		merchantagreements.add(
				"    (d)suffer an execution, attachment, repossession of or foreclosure on all or substantially all of its assets; and/or ");
		merchantagreements.add(Chunk.NEWLINE);
		merchantagreements.add("    (e)cease all or a substantial portion of its business or operations; and/or ");
		merchantagreements.add(Chunk.NEWLINE);
		merchantagreements.add("    (f)undergo a merger or substantial change in ownership or control; and/or ");
		merchantagreements.add(Chunk.NEWLINE);
		merchantagreements.add("    (g)if the Merchant serves any custodial sentence, becomes insane or dies; and/or ");
		merchantagreements.add(Chunk.NEWLINE);
		merchantagreements.add(
				"    (h)if the Merchant defaults on any account or accounts or facilities it has with Mobi; and/or ");

		merchantagreements.add(Chunk.NEWLINE);
		merchantagreements.add(
				"    (I)if the Merchant enters into any composition or arrangement with or for the benefit of the creditors of the Merchant or allows any judgment against the Merchant to remain unsatisfied for a period of fourteen (14) days has distress or execution or other process of court or competent jurisdiction levied upon or issued against any property or asset of the Merchant and such distress or execution or other process, as the case may be, is not satisfied by the Merchant within (7) days thereof; and/or");
		merchantagreements.add(Chunk.NEWLINE);
		merchantagreements.add(
				"    (j)if the Merchant or any of its employees is known or suspected to be involves in any fraudulent or any unlawful activity whether or not relative to the Merchant�s business; and/or (Suspension) ");

		merchantagreements.add(Chunk.NEWLINE);
		merchantagreements.add(
				"    (k)if in Mobi� s opinion that there has been a material change in the Merchant�s business; and/or (l) if in Mobiversa�s opinion that there has been an unacceptable level of incident of fraudulent or counterfeit transaction or suspicious transaction through the Merchant; and/or ");

		merchantagreements.add(Chunk.NEWLINE);
		merchantagreements.add(
				"    (m)any event occurs, or series of events occur, whether related or not, which in Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) opinion may affect the Merchant�s ability or willingness to comply with any of the Merchant�s obligations under this Agreement or to the Cardholder(s) in question; and/or ");
		merchantagreements.add(Chunk.NEWLINE);
		merchantagreements.add(
				"    (n)any breach or default on the part of the Merchant under the terms of this Agreement and/or if Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) has reason to believe or at its absolute discretion is of the view that the Merchant has presented a fraudulent or counterfeit transaction for payment and/or where the Cardholder denies/disputes such transaction, and/or the Merchant has been identified by any Card Schemes as engaging in the compromising of the Cardholder�s accounts activity; and/or ");
		merchantagreements.add(Chunk.NEWLINE);
		merchantagreements.add("    (o)If the Merchant fails to notify Mobi");
		merchantagreements.add(Chunk.NEWLINE);
		merchantagreements.add(
				"    (r)Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) has right to terminate this Agreement if the deposit activity of the Merchant remains inactive for a period of six (6) consecutive months of any other act being done by Mobiversa. Then in any such event(s), this Agreement shall terminate automatically, and all debts and obligations owed to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) shall be deemed immediately due and payable. Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) shall be entitled to maintain a reserve from payments due to the Merchant and/or take such other actions as Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) may be entitled to under this Agreement or under applicable law or equity.");
		merchantagreements.add(Chunk.NEWLINE);
		merchantagreements.add("9.2Upon termination of this Agreement,");

		merchantagreements.add(Chunk.NEWLINE);
		merchantagreements.add("    (a)the Merchant shall cease to use the Service");
		merchantagreements.add(Chunk.NEWLINE);
		merchantagreements.add(
				"    (b)Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) obligation to reimburse the Merchant under Clause 4 shall cease on the effective date of any of such termination, and it shall not be obliged or bound to make any payment");

		merchantagreements.add(Chunk.NEWLINE);
		merchantagreements.add(
				"    (c)the Merchant shall, at its own expenses return to Mobiversa, all Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) properties including but not limited to Mobisolutions card acceptance device Terminals in its possessions. ");
		merchantagreements.add(Chunk.NEWLINE);
		merchantagreements.add(
				"9.3Notwithstanding the aforesaid, Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) rights and entitlement under this Agreement (including its rights and entitlement of Chargeback under Clause 5) against the Merchant shall survive the termination of this Agreement for or relating to any Payment Transaction performed by merchant prior to the date of termination");
		merchantagreements.add(Chunk.NEWLINE);
		merchantagreements.add(
				"9.4If this Agreement is terminated under any of the provisions of this Agreement, Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) shall have the absolute rights (which shall not be questioned or challenged by the Merchant) to immediately withhold a sufficient sum of money to be determined by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) for a period of six (6) months from the date of the termination of this Agreement by a notice to the Merchant.");

		Paragraph merchantagreementt = new Paragraph();
		merchantagreementt.setLeading(0, 2);
		merchantagreementt.setFont(font6BoldUnderline);
		merchantagreementt.add("10. DISCLOSURE OF INFORMATION");
		Paragraph merchantagreementu = new Paragraph();
		merchantagreementu.setLeading(0, 2);
		merchantagreementu.setFont(font6Blue);
		merchantagreementu.add(
				"10.1The Merchant hereby authorizes Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) and/or its officers to make use of, disclose, divulge or reveal any information relating to the Merchant and its accounts in such manner and to such extent as Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) shall from time to time consider necessary to any consultant, agent or subcontractor or to any person for any purpose in connection with any Payment Card products or for any purpose in connection with the enforcement of any terms of this Agreement or to any authority or body established by BNM or any other competent authority or bodies having jurisdiction over Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) or to any Issuer bank or financial institution or Card Schemes or any other payment network operator.");
		merchantagreementu.add(Chunk.NEWLINE);
		merchantagreementu.add(
				"10.2The Merchant hereby authorizes Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) and/or its officers to make use of, disclose, divulge or reveal any information relating to its accounts for purposes of in connection with any action or proceeding taken for the purpose of Chargeback under Clause 5 or towards the recovery of monies due and payable by the Merchant to Mobiversa. ");
		merchantagreementu.add(Chunk.NEWLINE);
		merchantagreementu.add(
				"10.3The Merchant hereby undertakes and covenants that it shall keep all information which comes into possession pursuant to or during the course of this Agreement confidential and shall not disclose to any persons such information without obtaining prior written consent of Mobi. The Merchant shall ensure that its employees and/or agents who have access to such information comply with this confidentiality clause. ");
		merchantagreementu.add(Chunk.NEWLINE);
		merchantagreementu.add(
				"10.4The Merchant declares that all information given to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) are true and complete and the Merchant authorize and consent Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to verify the information given herein from whatever sources including without limitation any credit bureau established by authorities pursuant to any applicable law, regulations or directive (whether having the force of law or otherwise) or any party as Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) deem fit may be required and to use, release or exchange such information as may be obtained without further permission or consent from the Merchant. ");
		merchantagreementu.add(Chunk.NEWLINE);
		merchantagreementu.add(
				"10.5The Merchant agrees to provide information on previous Merchant Agreement(s), including the name(s) of the entity (ies) where the Merchant had the Agreement(s) and the reason(s) for terminating the Agreement(s), if applicable. ");
		merchantagreementu.add(Chunk.NEWLINE);
		merchantagreementu.add(
				"10.6Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) shall reserve the right to approve or reject any merchant application for the Service from the Merchant as it deems fit without providing any reasons thereof. ");

		Paragraph merchantagreementv = new Paragraph();
		merchantagreementv.setLeading(0, 2);
		merchantagreementv.setFont(font6BoldUnderline);
		merchantagreementv.add("11	CONFIDENTIALITY");
		Paragraph merchantagreementw = new Paragraph();
		merchantagreementw.setLeading(0, 2);
		merchantagreementw.setFont(font6Blue);
		merchantagreementw.add(
				"Notwithstanding Clause 10, this Agreement and all matters pertaining hereto including but not limited to, all information relating to Cardholder shall be considered as confidential in nature and shall not be disclosed by the Merchant to any third party unless the said disclosure is mandatory by law. The Merchant shall keep confidential any information it receives from Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) that is not publicly available and this Agreement and its terms and conditions including, without limitation, the MDR. The Merchant shall take all steps necessary to prevent the transfer or disclosure of Cardholder information to any third party and will not sell, copy, reproduce or store in any form the names and addresses of Cardholder for any purpose whatsoever. For the avoidance of doubt, the obligations of this Clause 12 shall survive the termination or expiration of this Agreement. ");
		Paragraph merchantagreementx = new Paragraph();
		merchantagreementx.setLeading(0, 2);
		merchantagreementx.setFont(font6BoldUnderline);
		merchantagreementx.add("11. NO REFUND POLICY");
		Paragraph merchantagreementy = new Paragraph();
		merchantagreementy.setLeading(0, 2);
		merchantagreementy.setFont(font6Blue);
		merchantagreementy.add(
				"All application fees and processing fees in applying or processing for the Mobisolutions and/or paid pursuant to this Agreement shall not be refundable to the applicant or the Merchant unless the application or the processing cannot proceed for any reason whatsoever attributable to the fault of Mobi");
		Paragraph merchantagreementz = new Paragraph();
		merchantagreementz.setLeading(0, 2);
		merchantagreementz.setFont(font6BoldUnderline);
		merchantagreementz.add("12. MISCELLANEOUS");
		Paragraph merchantagreementaa = new Paragraph();
		merchantagreementaa.setLeading(0, 2);
		merchantagreementaa.setFont(font6Blue);
		merchantagreementaa.add("12.1	Compliance with Laws");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"(a)This Agreement shall be governed by the laws and directives of regulatory authorities of Malaysia no matter where the transaction takes place and the Merchant hereby irrevocably submits to the non-exclusive jurisdiction of the courts of Malaysia and waives any objection to proceeding instituted in any other courts by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) on the ground of venue or that such proceedings have been brought in an inconvenient forum. ");

		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"    (b)The Merchant agrees to be bound by the terms and conditions of this Agreement and will not contravene the rules, guidelines and regulations issued by BNM and/or Card Schemes and wallet providers, such as but not limited to the prohibition of illegal transactions and double swiping (means the capturing of Payment Card data encoded on the magnetic stripes of Cardholders Payment Card at the POS reader or Electronic Cash Register (ECR). The data is captured when a payment card is swiped on retail Merchant�s POS reader / ECR. Double-swiping is not a required step in a Payment Card Transaction). ");

		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"    (c)Payment Card fraud is a serious offense and in order to protect the Merchant from any allegations of collusion as well as to prevent the spread of counterfeit syndicate activities via unwitting Merchants, the Merchant shall irrevocably authorized Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to disclose any information concerning the Merchant to any other person for any purpose in connection with the transaction through any Payment Card system. ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"    (d)In the event of any fraudulent transaction being brought to the attention of Mobi, Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) shall debit an equivalent amount from Merchant settlement proceeds; such amount will be released upon completion of the investigation which concludes that the Merchant is not a party to the fraudulent transaction. The Merchant agrees to repay Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) and Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) will have the right to debit such charges from the Merchant�s settlement account without any prior notice. Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) have the right to mandate collateral, Chargeback reserves, or a depository requirement as additional protection against financial exposure in the event the Merchant is found to be engaged in such activities as listed in Clause 12.1.");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"    (e)The Merchant has the legal obligation under applicable Malaysian Laws to implement and identify money laundering and terrorism financing activities at all times and to keep Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) informed of such procedures upon request. ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"    (f)The Merchant must not cause or permit to be done anything that may damage or endanger any Card Schemes, E-Money Issuers and/or �Mobi� and/or other payment solution providers trademarks, service marks, logos, names and designs or trademark or any other intellectual property rights owned by or licensed to any Card Schemes, E-Money Issuers  and/or other payment solution providers and/or Mobiversa. ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add("12.2Service of Legal Process");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"The Parties hereby agree that the service of any Writ of Summons or any legal process in respect of any claim arising out of or connected with this Agreement may be effected by forwarding a copy of the same by hand or by prepaid registered or ordinary post to the respective addresses of the Parties herein before mentioned and such service shall be deemed to be duly served after the expiration of five (5) days from the date it is posted and, if delivered by hand, on the day it is delivered. ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add("12.3Costs");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"The Merchant agrees to pay legal fees (on a Solicitor and Client basis) and other costs and expenses incurred and/or suffered by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) in connection with enforcement or incidental to this Agreement including the fees and stamp duty (if any) in connection with the preparation and execution of this Agreement. ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add("12.4Certificate of Indebtedness");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"A certificate signed by an officer of Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) as to the monies for the time being due and owing to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) from the Merchant for Chargeback or otherwise shall be conclusive evidence or proof that the amount appearing therein is due and owing and payable by the Merchant to Mobiversa. ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add("12.5Indemnity");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"Notwithstanding any other provisions of this Agreement, the Merchant agrees that it shall fully indemnify Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) for and against any loss or damage, penalties, costs and expenses including legal fees stated in Clause 12.2 which Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) may suffer by reason of a arising out of any breach of this Agreement or may incur in enforcing or seeking to enforce the payment of the value on the Payment Transaction from a Cardholder or Customer through Card Schemes or any wallet players and in enforcing the terms of this Agreement against the Merchant. This clause shall survive the termination or expiration of this Agreement. ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add("12.6Limitation of Liability");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"    (a)Without prejudice to any other provisions herein, Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) shall not be liable to the Merchant or any third parties for damages, loss of profits or earnings, goodwill or any type of special/ exemplary, incidental, direct or consequential loss or damage howsoever arising even if Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) has been advised of the possibility of such loss or damage or claim by any third party. ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"    (b)Subject to the provisions herein, Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) sole and entire liability to the Merchant in contract, tort (including negligence or breach of statutory duty) or otherwise arising by reason of or in connection with this Agreement or howsoever shall not exceed the MDR on the amount or value of the transaction which gave rise to the claim or the direct damages sustained, whichever is the lower. ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"    (c)Notwithstanding anything to the contrary in this Agreement, in the event Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) withholds payment and/or does not make a payment for transactions in any instances stated herein provided, Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) shall not be liable to the Merchant for any losses, claims, demands, proceedings, damages, late payment charges penalties or expenses whatsoever incurred by the Merchant by reason of non-payment or late payment by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) or in relation to or arising from this Agreement. ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add("12.7Agreement" + "    (a)Variation");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"        The Merchant agrees that Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) may from time to time by giving prior written notice to the Merchant vary add to or amend the terms and conditions herein set out. Any variation, addition and/or amendment shall become effective upon notification to the Merchant by any means Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) deem fit. ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add("    (b)Other Terms and Conditions");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"        The terms and conditions herein stated shall be in addition to and not in derogation of any specific Agreement or arrangement now hereafter from time to time subsisted between Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) and the Merchant or any terms and conditions that may be specified in any document given by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to the Merchant from time to time. ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add("    (c)Superceding Agreement");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"        All previous Agreements or arrangements, if any, made between Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) and the Merchant, whether written or oral, are hereby canceled and superseded by this Agreement. ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"    (d)Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) and the Merchant are independent contractors. This Agreement does not create a joint venture or partnership between Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) and the Merchant. ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add("12.8Notice Requirement");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"    Where the Merchant is a sole-proprietorship or a partnership, failure of the Merchant to notify Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) of any change as required in Clause 12.16 shall result in the sole-proprietor or partners (as the case maybe) of the Merchant (as notified to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) prior to the change) be held liable for any loss or damage suffered by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) therefrom. ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add("12.9Notice");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"    Save as it is otherwise expressly provided herein any notice or demand to be given under this Agreement shall be in any of the following ways:-");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"        (a)by ordinary mail to the other party and such notice or demand shall be deemed to have been served on the recipient three (3) Business Days after posting notwithstanding that it may be undelivered an in proving such service it shall be sufficient that the notice or demand was properly addressed and posted; or ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"        (b)by despatch or courier to the other party and such notice or demand shall be deemed to be given upon acknowledgment; or ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"        (c)by facsimile to the other party and such notice or demand shall be deemed to be delivered on transmission upon confirmation of the transmitting machine indicating that the transmission has been successful; or ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"        (d)by e-mail to the Merchant�s business e-mail address provided to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) for payment and other notification purposes. ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add("12.10Waiver");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"Time shall be the essence of this Agreement but no failure to exercise or any delay in exercising on the part of Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) of any right power privilege or remedy shall operate as a waiver thereof, nor shall any single or partial exercise of any right power privilege or remedy preclude any other or further exercise thereof or the exercise of any other right power privilege or remedy. The rights and remedies herein provided are cumulative and not exclusive of any right or remedy provided by law. Acceptance of payments by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) under this Agreement and/or any other indulgence given by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) shall not be deemed to operate as a waiver by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) of any right of action against the Merchant. ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add("12.11Severability");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"The invalidity or unenforceability of any of the provision herein shall not nullify the underlying intent of this Agreement and the invalid or unenforceable provision or portion thereof shall be severable and the invalidity or unenforceability of any term or provision of this Agreement shall not affect the validity or enforceability of other terms or provisions herein contained which shall remain in full force and effect. ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add("12.12Assignment");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"The Merchant shall not assign, subcontract or transfer this Agreement in whole or in part to any person or entity without Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) prior written consent. Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) shall be entitled to assign or subcontract this Agreement in whole or in part to any of Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) holding, subsidiaries or affiliate companies upon written notice to Merchant. ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add("12.13Binding Effect");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"This Agreement shall be binding on the Merchant�s personal representatives, heirs, successor�s in-title and legal assigns and on the successor�s in-title and assigns of Mobi. ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add("12.14Merchant�s Participation");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"This Agreement covermerchantagreementaa.add( the Merchant�s participation in Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) Payment Acceptance Services for all the offices, outlets and locations of the Merchant in Malaysia at the date of this Agreement and such other offices, outlets and locations as may be agreed upon by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) from time to time subject to the right of any Card Schemes or any wallet providers to limit or terminate Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) Payment Acceptance Services with the Merchant. ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add("12.15Communication");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"All communication between the Parties pertaining to this Agreement shall be in the Malay or the English Language. ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add("12.16Authority to Sign for Merchant");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"The Merchant represent that the person signing the Merchant Application Form is duly authorized on behalf of the Merchant to sign and bind the Merchant to the provisions thereof and hereof. ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add("12.17Successors Bound");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"    (a)This Agreement shall be binding on the personal representatives, heirs, successors-in-title of the Merchant and the successors-in-title and assigns of Mobi. ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add("    (b)Change in Merchant ownership particulars. ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"The Merchant undertakes to immediately inform Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) of any change in the name, style, constitution or composition of the Merchant whether by retirement, expulsion, death or admission of any partner or parties, amalgamation, reconstruction or otherwise and the Merchant hereby agrees that this Agreement shall endure and be available for all intents and purposes as if the resulting firm, company or concern had been named in this Agreement. ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add("12.18Effective Date of this Agreement");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"This Agreement shall only take effect from the date set out in the Merchant Application Form. Any Welcome Letter issued pursuant to this Agreement shall be deemed to take effect from the date of this Agreement. ");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add("12.19Additional Terms & Conditions from U Mobile");
		merchantagreementaa.add(Chunk.NEWLINE);
		merchantagreementaa.add(
				"The Merchant agrees to be bound by the Additional Terms & Conditions from U Mobile in Annexure One attached herewith.");

		Paragraph merchantagreementab = new Paragraph();
		merchantagreementab.setLeading(0, 2);
		merchantagreementab.setFont(font6BoldUnderline);
		merchantagreementab.add("ANNEXURE ONE: ADDITIONAL TERMS & CONDITIONS.");
		Paragraph merchantagreementac = new Paragraph();
		merchantagreementac.setLeading(0, 2);
		merchantagreementac.setFont(font6Blue);
		merchantagreementac.add(
				"1. On an ongoing basis, the Merchant must promptly provide Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) with its current address of each of its office or place of business, all business names used by the Merchant and a complete description of the goods/services provided.");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"2.In the event of any inconsistency between any provision of the Merchant Agreement and the Standards, the Standards shall prevail. �Standards� herein means any by-laws, rules, policies, operating regulations and procedures of the Card Schemes and U Mobile and any code of practice, guidelines or standards issued by relevant regulators or industry bodies, whether or not having the force of law, including but not limited to any manuals, guides, bulletins, standards issued by the Payment Card Industry Security Standards Council and any other data security standards as may be issued or amended from time to time.");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add("3.Compliance with guidelines and Standards:");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"    (a)the Merchant shall at all times observe the guidelines, procedures and the Standards on the acceptance of the Payment Card as provided by U Mobile or the Card Schemes to Mobi;");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add("    (b)the Merchant must comply with the Standards governing the use of the Marks;");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"    (c)if allowed by U Mobile, the Merchant must comply with the Brand Guidelines when using U Mobile Brands. �Brand Guidelines� herein means the brand guidelines provided by U Mobile to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) and as amended by U Mobile from time to time.");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"4.The Merchant shall accept all Payment Cards as a payment method for the Cardholder�s provision of the Goods.");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"5.The Merchant shall not require any Cardholder to pay any part of the Merchant Discount whether through an increase in price or otherwise or to pay any other charge or to require any security from or otherwise impose any condition on a Cardholder in connection with any Transactions.");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"6.The Merchant must not submit to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) any Transactions that:");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"    (a)the Merchant knows or should have known to be fraudulent, counterfeit, suspicious, wrongful or not authorised by the Cardholder;");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"    (b)the Merchant knows or should have known to be authorised by a Cardholder colluding with the Merchant for a fraudulent, counterfeit, suspicious or wrongful purpose; or");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"    (c)represents the refinancing or transfer of an existing Cardholder obligation that is deemed to be uncollectible or arises from the dishonour of a Cardholder�s personal cheque.");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"7.Unless otherwise provided by any written Law, the Merchant shall not impose or require Cardholders to pay any surcharge, commission or discount whether through any increase in price or otherwise, or any other terms and conditions whatsoever on any of the Cardholders desirous of using the Payment Card as opposed to any other method of payment.");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"8.The Merchant shall not impose a minimum Transaction Charge for the acceptance of an otherwise valid Payment Card from Cardholder as a method of payment.");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"9.The Merchant shall include in the value of the Sales Slips any tax required to be collected and shall not collect it separately in cash.");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"10.The Merchant shall observe and perform all obligations under its contract with the Cardholders including but not limit to the nature, quality and delivery of Goods to be sold and supplied to the Cardholders.");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"11.The Merchant shall not sell, purchase, provide or exchange Payment Card account number information in the form of imprinted Sales Slips, carbon-copies of imprinted Sales Slips or other media obtained via the Transaction to any third party.");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add("12.Suspension and termination:");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"    (a)This Merchant Agreement automatically and immediately terminates if the Card Schemes de-register Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) or if U Mobile ceases to be a member of the Card Schemes for any reason.");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"    (b)The right of Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to suspend any or all of the Merchant�s MID or TID or the Merchant Agreement indefinitely or immediately. �MID� herein means the Merchant�s unique identification number assigned by U Mobile. �TID� herein means the Merchant�s unique identification number assigned to a POS (Point of Sale) Terminal that is registered under the Merchant.");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"    (c)Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) has the right to terminate any or all of the Merchant�s MID or TID or the Merchant Agreement at any time for any reason.");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"    (d)Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) may, at its absolute discretion may immediately terminate the Merchant Agreement for activity deemed to be fraudulent, counterfeit, suspicious or otherwise wrongful by Mobiversa, U Mobile or Card Schemes, including but not limited to:");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add("        (i)fraudulent, counterfeit, suspicious or wrongful activity;");

		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"        (ii)presenting Transactions that do not result from an act between a Cardholder and the Merchant;");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"        (iii)entering into a payment facilitator agreement under a new name with the intent to circumvent the provisions of the Standards;");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add("        (iv)activity that causes U Mobile to repeatedly violate the Standards;");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"        (v)activity that has resulted in the Card Schemes prohibiting Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) from participating in any Card Schemes Programme;");

		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"        (vi)any other activity that may result in undue economic hardship or damage to the goodwill of the system of the Card Schemes;");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"        (vii)irregular Transactions by Merchant, excessive chargebacks, non-compliance with any applicable data security standards, as determined by Mobi, U Mobile, any Card Schemes, or an actual or suspected data security breach, or any other circumstances which, in the discretion of Mobiversa, U Mobile or any Card Schemes, may increase the risk exposure of such parties or otherwise present a direct or indirect financial or security risk to such parties;");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add("        (ix)any violation by the Merchant of any Laws or Standards; or");

		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"        (x)the Merchant process Transaction Charges worth more than United State Dollars One Hundred Thousand (USD100,000.00) only annually (based on the date the Transaction is approved) for any Payment Cards and does not enter into a merchant agreement directly with U Mobile;");

		merchantagreementac.add("13.The Merchant;");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"    (a)acknowledges that the Card Schemes are the sole and exclusive owners of their respective Marks;");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add("    (b)shall not contest the ownership of the Marks for any reason;");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"    (c)acknowledges that the Card Schemes may at any time, immediately and without advance notice, prohibit the Merchant from using any of the Mark for any reason;");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"    (d)acknowledges that the Card Schemes have the right to enforce any provision of the Standards and to prohibit the Merchant from engaging in any conduct the Card Schemes deem could damage or could create a risk of damage to the Card Schemes, including damage to reputation, or that could adversely affect the integrity of the Card Schemes� systems; and");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"    (e)shall not take any action that could interfere with or prevent the exercise of this right by the Card Schemes;");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"14.The Merchant shall ensure that the Goods sold comply with all Laws applicable to the Merchant, Mobi, U Mobile, the Card Schemes, the Cardholder, the Payment Cards and the Standards.");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"15.The Merchant must ensure that the Cardholder understands who is responsible for the Transactions, including delivery of the Goods, and for customer service and dispute resolution, all in accordance with the terms applicable to the Transactions.");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"16.The Merchant shall at all times cooperate with Mobi, U Mobile or any Card Schemes and provide such parties with all necessary information, documents and assistance required or requested by them to maintain compliance with the Standards and Laws.");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"17.If the Goods are categorised as High-Risk Business, the Merchant shall, in addition to Items 1-16 above, agree on the following:");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"    (a)the Merchant shall ensure that the Transactions are not fraudulent, counterfeit, suspicious or wrongful. If the Transaction is fraudulent, counterfeit, suspicious or wrongful, the Merchant will be liable for any loss suffered by Mobi, U Mobile or the Card Schemes arising from such fraudulent, counterfeit, suspicious or wrongful Transaction;");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"    (b)in the event the Merchant suspect any Transactions to be a fraudulent, counterfeit, suspicious or wrongful transaction or if such Transactions involve a material transaction value (which amount shall be informed by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) to the Merchant from time to time), the Merchant must perform card verification with the Cardholder and to submit the relevant verification document to Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) within three (3) Business Days after the date of the Transaction, failing which, the Merchant shall reverse the Transaction;");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"    (c)the Merchant shall resolve directly with the Cardholder, any claims or complaints made by the Cardholder in respect of any purchase of the Goods.");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"18.The Merchant shall not provide Goods relating to timeshare, membership, telemarketing, unlicensed investment scheme, unlicensed multi-level marketing scheme and as stipulated in the Standards.");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"19.The Merchant acknowledges that U Mobile is entitled not to pay Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) or to credit Mobi�s account if in the absolute discretion of U Mobile any of the events stipulated in Item 3 of Schedule 2 in the Third Party Acquirer Agreement made between U Mobile and Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) has occurred and that the Merchant shall have no right of recourse against Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) or U Mobile.");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"20.U Mobile or any Card Schemes shall be entitled to enter any of the Merchant�s premises with or without prior notice to audit and inspect the software, hardware, system records, procedure, any other part of its system, documents, processes, acquirer records. with a view to ascertaining whether the setting-up, operation, maintenance, security and integrity or any other matter related to the Merchant�s system may adversely affect U Mobile�s or any Card Schemes� interests or U Mobile�s rights");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"under this Agreement. All costs and expenses incurred for the audit or inspection shall be borne by Mobi Asia Sdn. Bhd. (Formerly known as Mobiversa Sdn. Bhd.) and shall be debited into Mobi�s account.");
		merchantagreementac.add(Chunk.NEWLINE);
		merchantagreementac.add(
				"21.The Merchant shall defend, indemnify and keep U Mobile indemnified against all damages, claims or liabilities and expenses (including legal costs) arising out of or resulting in any way from (i) the Merchant�s breach of any of these terms and conditions of the Merchant Agreement or Standards; (ii) any property damage or personal injury or death due to the Merchant�s acts or omissions (whether negligent or otherwise) or that of the Merchant�s agents or sub-contractors; and (iii) any infringement or alleged infringement of any intellectual property rights of third parties.");

		merchantagreementac.setFont(font6Blue);
		// merchantagreementac.setFont(fontsma);
		pdfDoc.add(merchantagreement);
		pdfDoc.add(merchantagreementa);
		pdfDoc.add(merchantagreementb);
		pdfDoc.add(merchantagreementc);
		pdfDoc.add(merchantagreementd);
		pdfDoc.add(merchantagreemente);
		pdfDoc.add(merchantagreementf);
		pdfDoc.add(merchantagreementg);
		pdfDoc.add(merchantagreementh);
		pdfDoc.add(merchantagreementi);
		pdfDoc.add(merchantagreementj);
		pdfDoc.add(merchantagreementk);
		pdfDoc.add(merchantagreementl);
		pdfDoc.add(merchantagreementm);
		pdfDoc.add(merchantagreementn);
		pdfDoc.add(merchantagreemento);
		pdfDoc.add(merchantagreementp);
		pdfDoc.add(merchantagreementq);
		pdfDoc.add(merchantagreementr);
		pdfDoc.add(merchantagreements);
		pdfDoc.add(merchantagreementt);
		pdfDoc.add(merchantagreementu);
		pdfDoc.add(merchantagreementv);
		pdfDoc.add(merchantagreementw);
		pdfDoc.add(merchantagreementx);
		pdfDoc.add(merchantagreementy);
		pdfDoc.add(merchantagreementz);
		pdfDoc.add(merchantagreementaa);
		pdfDoc.add(merchantagreementab);
		pdfDoc.add(merchantagreementac);
		pdfDoc.add(new Chunk("\n"));
		PdfPTable pdflastsign = new PdfPTable(2); // two colmns create tabble
		float[] widthlastsign = { 6f, 6f, 3f };
		pdflastsign.setWidthPercentage(100f); // table %100 width
		PdfPCell cellxxlastsign = new PdfPCell();
		Paragraph phr1lastsign = new Paragraph();
		phr1lastsign.setFont(font5);
		phr1lastsign.setLeading(0, 2);
		phr1lastsign
				.add("SALES AGENT\r\nSales Agent Name & Agent Code:" + order.getQuotation().getSalesPerson().getName()
						+ "\r\nNRIC/Passport No:" + order.getQuotation().getSalesPerson().getnRIC() + "\r\nDate:"
						+ order.getQuotation().getPayment().getCollectedOn() + "\r\nSales Personnel Signature");
		cellxxlastsign.addElement(phr1lastsign);
		cellxxlastsign.setBorder(Rectangle.NO_BORDER);

		String signPathSP = "";
		if (order.getQuotation().getSalesPerson() != null) {
			Image imagesignsp = Image.getInstance(
					env.getProperty("signature.basePath") + order.getQuotation().getSalesPerson().getPhone() + ".png");
			imagesignsp.scaleAbsolute(150f, 150f);
			imagesignsp.setAlignment(Element.ALIGN_LEFT);
			cellxxlastsign.addElement(imagesignsp);
		}
		pdflastsign.addCell(cellxxlastsign);
		PdfPCell cellxylastsign = new PdfPCell();
		Paragraph phr2lastsign = new Paragraph();
		cellxylastsign.setBorder(Rectangle.NO_BORDER);
		phr2lastsign.setFont(font5);
		phr2lastsign.setLeading(0, 2);
		phr2lastsign.add("Name:"
				+ (order.getQuotation().getWelcomeLetterAcceptance() == null ? ""
						: order.getQuotation().getWelcomeLetterAcceptance().getNameAsPerIC())
				+ "\nDesignation:" + "\nNRIC No."
				+ (order.getQuotation().getWelcomeLetterAcceptance() == null ? ""
						: order.getQuotation().getWelcomeLetterAcceptance().getIcNumber())
				+ "\nDate:"
				+ (order.getQuotation().getPayment() == null ? "" : order.getQuotation().getPayment().getCollectedOn())
				+ "\nSignature:");
		cellxylastsign.addElement(phr2lastsign);

		if (order.getQuotation().getQuotationAcceptance() != null) {
			Image imagesign = Image
					.getInstance(env.getProperty("signature.basePath") + order.getQuotation().getUserId() + ".png");
			imagesign.scaleAbsolute(150f, 150f);
			imagesign.setAlignment(Element.ALIGN_LEFT);
			cellxylastsign.addElement(imagesign);
		}

		pdflastsign.addCell(cellxylastsign);
		pdflastsign.setWidthPercentage(100f);
		pdfDoc.add(pdflastsign);

		pdfDoc.close();

		File newMMAFilePath = new File(fileStorePath);

		if (newMMAFilePath.exists()) {
			String newMMAResourcePath = String.format("%s%s/MMA-%s.pdf", Constants.getMmaResourcePath(), order.getId(),
					order.getId());
			logger.info("newMMAResourcePath --> " + newMMAResourcePath);
			int result = service.updateMMAPath(order.getQuotation().getId(), newMMAResourcePath);

			MMAResponseDataModal mmaResponseDataModal = new MMAResponseDataModal();
			mmaResponseDataModal.setMmaPath(newMMAResourcePath);

			switch (result) {
			case -1:
				return new CommonResponseData("0001", "Something went wrong, please try again", null);
			case 0:
				return new CommonResponseData("0001", "Quotation not found", null);
			default:
				return new CommonResponseData("0000", "Success", mmaResponseDataModal);
			}
		}

		return new CommonResponseData("0001", "Something went wrong, please try again", null);
	}

	@Override
	public void generatedAcceptedQuotation(QuotationRequestData quotationRequestData, Quotation quotation,
			HttpServletRequest req) {
		
		
		logger.info("generatedAcceptedQuotation >> ");

		String originalQuotationPath = Constants.getQuotationPath();
		// Check Original File Exist
		String originalFilePath = String.format("%s/%s/Quotation_%s.pdf", originalQuotationPath,
				quotationRequestData.getQuotationId(), quotationRequestData.getQuotationId());
		File originalFile = new File(originalFilePath);

		if (originalFile.exists()) {

			// Original File Available
			logger.info("generatedAcceptedQuotation >> originalFile available");

			String issuedPath = String.format("%s/%s/issued", originalQuotationPath, quotation.getId());
			File issueFile = new File(issuedPath);

			if (!issueFile.exists()) {
				issueFile.mkdirs();
			}

			String sourcePath = originalFilePath;
			String targetPath = String.format("%s/%s/issued/Quotation_%s_%s.pdf", originalQuotationPath,
					quotation.getId(), quotation.getId(), "Accepted");

			logger.info("sourcePath -> " + sourcePath);
			logger.info("targetPath -> " + targetPath);

			try {
				Files.copy(Paths.get(sourcePath), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);

				String issuedQuotationResourcePath = String.format("%s%s/issued/Quotation_%s_%s.pdf",
						Constants.getIssueQuotationResourcePath(), quotation.getId(), quotation.getId(), "Accepted");

				String issuedQuotationResourceName = String.format("Quotation_%s_%s.pdf", quotation.getId(),
						"Accepted");

				// Add email block below
				service.insertIssuedQuotation(issuedQuotationResourcePath, issuedQuotationResourceName,
						quotation.getId());

			} catch (IOException e) {
				logger.error("Stacktrace : ", e);
				e.printStackTrace();
			}
		} else {
			originalQuotationPath = String.format("%s/%s/", originalQuotationPath, quotation.getId());
			int flag = generateNewQuotation(originalQuotationPath, quotation, req);

			if (flag == 0) {
				String quotationFilePath = String.format("%s/%s/Quotation_%s.pdf", Constants.getQuotationResourcePath(),
						quotation.getId(), quotation.getId());

				String quotationName = String.format("Quotation_%s.pdf", quotation.getId());

				// Insert image into
				insertImageInto(quotationFilePath, quotationName, quotation.getId());

				String issuedPath = String.format("%sissued", originalQuotationPath);
				File issueFile = new File(issuedPath);

				if (!issueFile.exists()) {
					issueFile.mkdirs();
				}

				String sourcePath = originalFilePath;
				String targetPath = String.format("%sissued/Quotation_%s_%s.pdf", originalQuotationPath,
						quotation.getId(), "Accepted");

				logger.info("sourcePath -> " + sourcePath);
				logger.info("targetPath -> " + targetPath);

				try {
					Files.copy(Paths.get(sourcePath), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);

					String issuedQuotationResourcePath = String.format("%s%s/issued/Quotation_%s_%s.pdf",
							Constants.getIssueQuotationResourcePath(), quotation.getId(), quotation.getId(),
							"Accepted");
					String issuedQuotationResourceName = String.format("Quotation_%s_%s.pdf", quotation.getId(),
							"Accepted");

					// Add email block below

					service.insertIssuedQuotation(issuedQuotationResourcePath, issuedQuotationResourceName,
							quotation.getId());

				} catch (IOException e) {
					logger.error("Stacktrace : ", e);
					e.printStackTrace();
				}

			} else {
				logger.error("There is a issue in Generating Quotation");
			}
		}

	}

}
