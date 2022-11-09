package com.mobi.ocs.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.SaveContextOnUpdateOrErrorResponseWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.mchange.util.impl.QuotesAndSpacesTokenizer;
import com.mchange.v2.cfg.PropertiesConfigSource.Parse;
import com.mobi.ocs.dao.QuotationDAO;
import com.mobi.ocs.dto.CommonResponseData;
import com.mobi.ocs.dto.HubSpotInfo;
import com.mobi.ocs.dto.IDResponse;
import com.mobi.ocs.dto.MDRData;
import com.mobi.ocs.dto.OrderLineIds;
import com.mobi.ocs.dto.Property;
import com.mobi.ocs.dto.ValueString;
import com.mobi.ocs.dto.kanban.KanbanCard;
import com.mobi.ocs.dto.kanban.KanbanCustomFields;
import com.mobi.ocs.dto.kanban.KanbanInfo;
import com.mobi.ocs.entity.Acquirer;
import com.mobi.ocs.entity.Callback;
import com.mobi.ocs.entity.Contact;
import com.mobi.ocs.entity.HostRate;
import com.mobi.ocs.entity.HostType;
import com.mobi.ocs.entity.Order;
import com.mobi.ocs.entity.OrderLines;
import com.mobi.ocs.entity.OrderType;
import com.mobi.ocs.entity.Payment;
import com.mobi.ocs.entity.PaymentType;
import com.mobi.ocs.entity.Product;
import com.mobi.ocs.entity.ProductType;
import com.mobi.ocs.entity.Quotation;
import com.mobi.ocs.entity.QuotationEzySplitMDRRate;
import com.mobi.ocs.entity.QuotationMDRRate;
import com.mobi.ocs.entity.SalesPerson;
import com.mobi.ocs.entity.UmobileCountry;
import com.mobi.ocs.service.AssignService;
import com.mobi.ocs.service.GeneratePDFService;
import com.mobi.ocs.service.QuotationService;

import kotlin.contracts.ReturnsNotNull;

@Controller
@RequestMapping("/CallBack")
public class CallBackController {

	@Autowired
	private QuotationService quotationService;
	protected static Logger logger = Logger.getLogger(CallBackController.class);

	@RequestMapping("/list")
	public String CallBackList(Model model) {

		try {

			logger.info("Inside CallBack List Controller");
			List<Callback> callbackRequests = new ArrayList<Callback>();

			callbackRequests = quotationService.GetCallBackRequests();
			model.addAttribute("callbackRequests", callbackRequests);

		} catch (Exception e) {
			logger.error("Failed to Get CallBackList - ", e);
		}

		return "CallBackRequestList";
	}

	@PostMapping(path = "/saveCallBackRequest", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object SaveCallBackRequest(@RequestBody Callback callbackRequest)
			throws DocumentException, MalformedURLException, IOException {

		logger.info("Callback Request Received : " + callbackRequest.toString());
		callbackRequest.setDate(DateTime.now().toString());
		quotationService.SaveCallbackRequest(callbackRequest);
		return new CommonResponseData("0000", "CallBack Request Saved", null);

	}

	@PostMapping(path = "/markCallbackContacted", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object MarkCallbackContacted(int callbackId)
			throws DocumentException, MalformedURLException, IOException {

		logger.info("To Mark Contacted For the CallBack Request ID : " + callbackId);
		quotationService.MarkCallbackContacted(callbackId);
		return new CommonResponseData("0000", "CallBack Request Marked Contacted", null);

	}

}
