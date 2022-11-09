package com.mobi.ocs.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobi.ocs.dto.CommonResponseData;
import com.mobi.ocs.entity.DocumentType;


@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private SessionFactory sessionFactory;
	
	protected static Logger logger = Logger.getLogger(DocumentServiceImpl.class);

	@Override
	@Transactional
	public Object getDocumentTypes() {
		try {
			Session session = sessionFactory.getCurrentSession();
			
			ArrayList<DocumentType> array = (ArrayList<DocumentType>) session.createQuery("FROM DocumentType where companyType = 'SP'").getResultList();
			return new CommonResponseData("0000", "Success", array);
			
		} catch (Exception e) {
			logger.error("Stacktrace : " , e);
			return new CommonResponseData("0001", "Unable to retrive document type", null);
		}
	}

}
