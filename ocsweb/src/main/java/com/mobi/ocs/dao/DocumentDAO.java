package com.mobi.ocs.dao;

import com.mobi.ocs.modal.DeleteDocumentRequestData;
import com.mobi.ocs.modal.DocumentUploadRequestData;

public interface DocumentDAO {

	public Object insertDocument(DocumentUploadRequestData documentRequestData);

	public Object getDocumentByOrderId(int orderId, String username);

	public Object deleteDocumentByUserId(String username);

	public Object clearAllDocumentByUserId(String username);

	public Object getDocumentType();

	public Object deleteDocumentById(String documentId, DeleteDocumentRequestData deleteDocumentRequestData);

}
