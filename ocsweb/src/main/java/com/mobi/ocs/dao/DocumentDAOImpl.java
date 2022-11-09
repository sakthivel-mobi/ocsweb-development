package com.mobi.ocs.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mobi.ocs.dto.CommonResponseData;
import com.mobi.ocs.entity.Document;
import com.mobi.ocs.modal.DeleteDocumentRequestData;
import com.mobi.ocs.modal.DocumentUploadRequestData;
import com.mobi.ocs.service.DocumentService;
import com.mobi.ocs.utilities.Constants;
import com.mobi.ocs.config.HibernateConfig;
import com.mobi.ocs.dao.DocumentDAO;

@Repository
public class DocumentDAOImpl implements DocumentDAO {

	String sDocumentResource = "/attachments/document";

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DocumentService documentService;
	protected static Logger logger = Logger.getLogger(DocumentDAOImpl.class);

	@Transactional
	@Override
	public Object insertDocument(DocumentUploadRequestData documentRequestData) {

		int count = documentRequestData.getDocumentData().size();
		Session currentSession = sessionFactory.getCurrentSession();

		for (int index = 0; index < count; index++) {
			try {

				File fileLocation = new File(Constants.getDocumentFilePath() + "/" + documentRequestData.getOrderId());

				if (!fileLocation.exists()) {
					fileLocation.mkdirs();
				}

				Authentication auth = SecurityContextHolder.getContext().getAuthentication();

				String extractedImageData = documentRequestData.getDocumentData().get(index);
				byte[] imageByteArray = decodeImage(extractedImageData);

				String fileStoreLocation = fileLocation + "/" + documentRequestData.getDocumentName().get(index);

				FileOutputStream imageOutFile = new FileOutputStream(fileStoreLocation);
				imageOutFile.write(imageByteArray);
				imageOutFile.close();

				Document document = new Document(auth.getName(), documentRequestData.getOrderId(),
						documentRequestData.getDocumentName().get(index),
						Constants.getDocumentResourcePath() + "/" + documentRequestData.getOrderId() + "/"
								+ documentRequestData.getDocumentName().get(index),
						documentRequestData.getDocumentFileCategory().get(index));

				Document existingDocument = (Document) currentSession.createQuery(
						"FROM Document WHERE userName = :username AND documentName = :documentName AND orderId = :orderId AND documentCategory =:documentCategory")
						.setParameter("username", auth.getName())
						.setParameter("documentName", documentRequestData.getDocumentName().get(index))
						.setParameter("orderId", documentRequestData.getOrderId())
						.setParameter("documentCategory", documentRequestData.getDocumentFileCategory().get(index))
						.uniqueResult();

				if (existingDocument != null) {
					currentSession.update(document);
				} else {
					currentSession.save(document);
				}

			} catch (Exception e) {
				logger.error("Failed to Insert Document - " , e);
				return new CommonResponseData("0001", "Unable to upload documents, please try again", null);
			}

		}

		return new CommonResponseData("0000", "Documents uploaded", null);

	}

	public static byte[] decodeImage(String fileDataString) {
		return Base64.getDecoder().decode(fileDataString);
	}

	@Transactional
	public Object getDocumentByOrderId(int orderId, String username) {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			List<Document> list = currentSession
					.createQuery("FROM Document WHERE orderId = :orderId")
					.setParameter("orderId", orderId).getResultList();

			if (list == null || list.isEmpty()) {
				return new CommonResponseData("0000", "No Documents Available", null);
			} else {
				return new CommonResponseData("0000", "Success", list);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Failed to get Document By OrderID - " , e);
			return new CommonResponseData("0001", e.getMessage(), null);
		}
	}

	@Override
	public Object deleteDocumentByUserId(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object clearAllDocumentByUserId(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDocumentType() {
		return documentService.getDocumentTypes();
	}

	@Override
	@Transactional
	public Object deleteDocumentById(String documentId, DeleteDocumentRequestData deleteDocumentRequestData) {
		try {

			logger.info("deleteDocumentById >> " + documentId + " deleteDocumentRequestData >> "
					+ deleteDocumentRequestData.getOrderId());

			Session currentSession = sessionFactory.getCurrentSession();
			currentSession.createQuery("DELETE FROM Document WHERE id = :id AND orderId = :orderId")
					.setParameter("id", Long.valueOf(documentId))
					.setParameter("orderId", Integer.valueOf(deleteDocumentRequestData.getOrderId())).executeUpdate();

			return new CommonResponseData("0000", "Document has been removed", null);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Failed to Delete Document By ID - " , e);
			return new CommonResponseData("0001", e.getMessage(), null);
		}
	}

}
