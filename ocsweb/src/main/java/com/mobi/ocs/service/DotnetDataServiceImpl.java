package com.mobi.ocs.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.mobi.ocs.dao.DotnetDataDAO;
import com.mobi.ocs.dao.MerchantDAO;
import com.mobi.ocs.dao.QuotationDAO;
import com.mobi.ocs.dto.CommonResponseData;
import com.mobi.ocs.dto.HubSpotInfo;
import com.mobi.ocs.dto.IDResponse;
import com.mobi.ocs.dto.IssueQuotationResponseDataModel;
import com.mobi.ocs.dto.ReceiptResponseData;
import com.mobi.ocs.dto.ServiceResponse;
import com.mobi.ocs.dto.kanban.KanbanCardUpdate;
import com.mobi.ocs.dto.kanban.KanbanCustomFields;
import com.mobi.ocs.dto.kanban.KanbanInfo;
import com.mobi.ocs.entity.*;
import com.mobi.ocs.entity.dotnetOcs.Director_dotnet;
import com.mobi.ocs.entity.dotnetOcs.Document_dotnet;
import com.mobi.ocs.entity.dotnetOcs.MerchantDetails;
import com.mobi.ocs.entity.dotnetOcs.StageMovement_dotnet;
import com.mobi.ocs.modal.AcceptQuotationRequestData;
import com.mobi.ocs.modal.OldProductListData;
import com.mobi.ocs.modal.PaymentCollectRequestData;
import com.mobi.ocs.modal.PendingQuotationMDRRatesRequestData;
import com.mobi.ocs.utilities.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.aop.config.AbstractInterceptorDrivenBeanDefinitionDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.io.File;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class DotnetDataServiceImpl implements DotnetDataService {

	@Autowired
	private DotnetDataDAO dotnetDataDAO;

	protected static Logger logger = Logger.getLogger(DotnetDataServiceImpl.class);

	@Override
	public List<MerchantDetails> getDotnetOCSMerchantData() {
		return dotnetDataDAO.GetDotnetOCSMerchantData();
	}

	@Override
	public MerchantDetails GetDotnetOCSMerchantDataById(String orderID) {
		return dotnetDataDAO.GetDotnetOCSMerchantDataById(orderID);
	}

	@Override
	public List<Director_dotnet> GetDotnetOCSDirectorsByOrderID(String orderId) {
		return dotnetDataDAO.GetDotnetOCSDirectorsByOrderID(orderId);
	}

	@Override
	public List<StageMovement_dotnet> GetDotnetOCSStageMovementByOrderId(String orderId) {
		return dotnetDataDAO.GetDotnetOCSStageMovementByOrderId(orderId);
	}

	@Override
	public List<Document_dotnet> GetDotnetOCSDocumentsByUserId(String userId) {
		return dotnetDataDAO.GetDotnetOCSDocumentsByUserId(userId);
	}

	@Override
	public Object getQuotationFileList(String fileOrderId) {
		String filePath = String.format("C:/ocsweb/oldData/Quotation/%s", fileOrderId);
		ArrayList<Document_dotnet> path = new ArrayList<Document_dotnet>();
		Set<String> fileList = listFilesUsingJavaIO(filePath);

		if (fileList != null) {
			for (String file : fileList) {
				Document_dotnet newFile = new Document_dotnet();
				newFile.setFILE_CATEGORY("Quotation");
				newFile.setFILE_NAME(file);
				newFile.setID(Integer.parseInt(fileOrderId));
				newFile.setFileType("/quotation");
				newFile.setSTORE_FILENAME(String.format("/documents/old/files/quotation/%s/%s", fileOrderId, file));
				path.add(newFile);
			}
		}

		System.out.println("getQuotationFileList >> " + path);
		return path;
	}

	@Override
	public Object getFileShareFileList(String fileOrderId) {
		String filePath = String.format("C:/ocsweb/oldData/FileShare/%s", fileOrderId);
		ArrayList<Document_dotnet> path = new ArrayList<Document_dotnet>();
		Set<String> fileList = listFilesUsingJavaIO(filePath);

		if (fileList != null) {
			for (String file : fileList) {
				Document_dotnet newFile = new Document_dotnet();
				newFile.setFILE_CATEGORY("File Share");
				newFile.setFILE_NAME(file);
				newFile.setID(Integer.parseInt(fileOrderId));
				newFile.setFileType("/fileShare");
				newFile.setSTORE_FILENAME(String.format("/documents/old/files/fileShare/%s/%s", fileOrderId, file));
				path.add(newFile);
			}
		}

		System.out.println("getQuotationFileList >> " + path);
		return path;
	}
	
	@Override
	public Object getScheduleFileList(String fileUserName) {
		fileUserName = fileUserName.replaceAll("\\+|-| ", "");
		System.out.println("getScheduleFileList - fileUserName >> " + fileUserName);

		String filePath = String.format("C:/ocsweb/oldData/Schedule/%s", fileUserName);
		ArrayList<Document_dotnet> path = new ArrayList<Document_dotnet>();
		Set<String> fileList = listFilesUsingJavaIO(filePath);

		if (fileList != null) {
			for (String file : fileList) {
				Document_dotnet newFile = new Document_dotnet();
				newFile.setFILE_CATEGORY("Schedule");
				newFile.setFILE_NAME(file);
				newFile.setFileType("/schedule");
				newFile.setID(0);
				newFile.setUserName(fileUserName);
				newFile.setSTORE_FILENAME(String.format("/documents/old/files/schedule/%s/%s", fileUserName, file));
				path.add(newFile);
			}
		}

		System.out.println("getScheduleFileList >> " + path);
		return path;
	}

	@Override
	public Object getWelcomeLFileList(String fileUserName) {

		fileUserName = fileUserName.replaceAll("\\+|-| ", "");

		System.out.println("getWelcomeLFileList - fileUserName >> " + fileUserName);

		String filePath = String.format("C:/ocsweb/oldData/Welcome/%s", fileUserName);
		ArrayList<Document_dotnet> path = new ArrayList<Document_dotnet>();
		Set<String> fileList = listFilesUsingJavaIO(filePath);
		if (fileList != null) {
			for (String file : fileList) {
				Document_dotnet newFile = new Document_dotnet();
				newFile.setFILE_CATEGORY("Welcome Letter");
				newFile.setFILE_NAME(file);
				newFile.setFileType("/welcome");
				newFile.setID(0);
				newFile.setUserName(fileUserName);
				newFile.setSTORE_FILENAME(String.format("/documents/old/files/welcome/%s/%s", fileUserName, file));
				path.add(newFile);
			}
		}

		System.out.println("getWelcomeLFileList >> " + path);
		return path;
	}

	public Set<String> listFilesUsingJavaIO(String dir) {
		try {
			return Stream.of(new File(dir).listFiles()).filter(file -> !file.isDirectory()).map(File::getName)
					.collect(Collectors.toSet());
		} catch (NullPointerException e) {
			logger.error("listFilesUsingJavaIO", e);
			return null;
		}
	}

	@Override
	public List<OldOrderDetails> getOldOrderDetailsById(String orderID) {
		return dotnetDataDAO.getOldOrderDetailsById(orderID);
	}

	@Override
	public List<OldNotesDetails> getOldOrderNotesByOrderId(String orderID) {
		return dotnetDataDAO.getOldOrderNotesByOrderId(orderID);
	}

}
