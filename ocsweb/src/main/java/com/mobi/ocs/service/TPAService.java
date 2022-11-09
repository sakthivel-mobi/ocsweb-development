package com.mobi.ocs.service;

import java.util.ArrayList;

import com.mobi.ocs.entity.TPAFileToUmobile;
import com.mobi.ocs.modal.TPADocumentDownloadRequestData;

public interface TPAService {

	ArrayList<TPAFileToUmobile> InsertTPADataInDB(TPADocumentDownloadRequestData orderIDArray);

}
