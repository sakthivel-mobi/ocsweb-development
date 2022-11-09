package com.mobi.ocs.dao;

import java.util.List;

import com.mobi.ocs.entity.MerchantRegistration;
import com.mobi.ocs.entity.Order;
import com.mobi.ocs.entity.Quotation;
import com.mobi.ocs.entity.UmobileMCC;
import com.mobi.ocs.modal.MerchantRegistrationResponseData.MerchantRegistrationResponse;

public interface MerchantRegistrationDAO {

	List<MerchantRegistration> GetRegistrationOrders();

	MerchantRegistration GetRegistrationOrderByID(String ID);

	List<Order> getPendingDeploymentOrderIDs();

	Object CreateMerchantRegistrationForOrder(String orderId);

	void UpdateMerchantRegistration(MerchantRegistration registration);

	void UpdateSuccessStatusForRegistration(String orderId);

	void updateAPIKeyAndActivationCode(String mobiApiKey, String activationCode, int quotationId, String password,
			String userName);

	Object GetMerchantRegistrationListMobile(String username);

	List<MerchantRegistration> getGrapPayReport();

	UmobileMCC getUmobileMccList(int visaMCC);

	void updateMidForMerchantRegistration(String orderId, MerchantRegistrationResponse responseData);

	MerchantRegistration getMerchantRegistrationDetailsByRegistrationId(String registrationID);

	MerchantRegistration getMerchantRegistrationByRegistrationId(int id);

	void saveWebPortalCredentials(String merchantRegistrationId, MerchantRegistrationResponse responseData);

	void updateMerchantAppCredentials(int merchantRegistrationId, String username, String password, String activationCode,
			String mobiApiKey);

	Object getMerchantRegistrationByOrderId(String orderId);
	
	void updateEwalletSyncCompleted(int merchantRegistrationId);

}
