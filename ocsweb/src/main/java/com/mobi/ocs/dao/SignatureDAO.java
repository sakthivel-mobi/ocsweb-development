package com.mobi.ocs.dao;

import com.mobi.ocs.modal.SignatureRequestModal;

public interface SignatureDAO {
	
	public Object saveUserSignature(SignatureRequestModal signatureRequestModal, String imageUrl);
	
	public Object getUserSignature(String username);
	
	public Object getSignatureById(String quotationId);

	public Object getUserSignatureById(String signatureId);

}
