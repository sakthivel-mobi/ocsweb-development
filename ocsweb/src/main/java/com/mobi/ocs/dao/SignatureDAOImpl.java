package com.mobi.ocs.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mobi.ocs.modal.SignatureRequestModal;
import com.mobi.ocs.dto.CommonResponseData;
import com.mobi.ocs.entity.Signature;

@Repository
public class SignatureDAOImpl implements SignatureDAO {

	@Autowired
	private SessionFactory sessionFactory;
	protected static Logger logger = Logger.getLogger(SignatureDAOImpl.class);

	@Override
	@Transactional
	public Object saveUserSignature(SignatureRequestModal signatureRequestModal, String imageUrl) {

		int signatureID = -1;
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			logger.info("imageUrl -> " + imageUrl);

			Signature existingSignature = (Signature) currentSession
					.createQuery("FROM Signature WHERE username = :username")
					.setParameter("username", signatureRequestModal.getUsername())
					.uniqueResult();

			if (existingSignature != null) {
				existingSignature.setImageUrl(imageUrl);
				currentSession.update(existingSignature);
				signatureID = existingSignature.getId();
			} else {
				Signature signature = new Signature(signatureRequestModal.getUsername(), imageUrl);
				signatureID = (int) currentSession.save(signature);
			}

			logger.info("Image url update in database");
		} catch (Exception e) {
			logger.error("Failed to Save User Signature - " , e);
		}
		return signatureID;
	}

	@Override
	@Transactional
	public Object getUserSignature(String username) {
		try {
			
			logger.info("getUserSignature -> "+ username);
			
			Session currentSession = sessionFactory.getCurrentSession();
			Signature signature = (Signature) currentSession.createQuery("FROM Signature WHERE username = :username")
					.setParameter("username", username).uniqueResult();

			if(signature != null) {
				return new CommonResponseData("0000", "Success", signature);
			}else {
				return new CommonResponseData("0001", "Sinature not available", null);
			}

		} catch (Exception e) {
			logger.error("Failed to get User Signature - " , e);
			return new CommonResponseData("0001", e.getMessage(), null);
		}
	}

	@Override
	@Transactional
	public Object getSignatureById(String quotationId) {
		try {
			logger.info("getUserSignature -> "+ quotationId);
			
			Session currentSession = sessionFactory.getCurrentSession();
			Signature signature = (Signature) currentSession.createQuery("FROM Signature WHERE quotationId = :quotationId")
					.setParameter("quotationId", Integer.parseInt(quotationId)).uniqueResult();
			return new CommonResponseData("0000", "Success", signature);
		} catch (Exception e) {
			logger.error("Failed to get Signature By ID - " , e);
			return new CommonResponseData("0001", e.getMessage(), null);
		}
	}

	@Override
	@Transactional
	public Object getUserSignatureById(String signatureId) {
		try {
			logger.info("getUserSignature -> "+ signatureId);
			
			Session currentSession = sessionFactory.getCurrentSession();
			Signature signature = (Signature) currentSession.createQuery("FROM Signature WHERE id = :signatureId")
					.setParameter("signatureId", Integer.parseInt(signatureId)).uniqueResult();
			return new CommonResponseData("0000", "Success", signature);
		} catch (Exception e) {
			logger.error("Failed to get User Signature By ID - " , e);
			return new CommonResponseData("0001", e.getMessage(), null);
		}
	}

}
