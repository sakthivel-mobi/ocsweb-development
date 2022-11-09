package com.mobi.ocs.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mobi.ocs.dto.CommonResponseData;
import com.mobi.ocs.dto.EzysplitRateID;
import com.mobi.ocs.dto.MDRSyncToPaymentServer;
import com.mobi.ocs.dto.MerchRegDto;
import com.mobi.ocs.dto.mdrSyncDto;
import com.mobi.ocs.entity.MerchantRegistration;
import com.mobi.ocs.entity.Order;
import com.mobi.ocs.modal.MDRSyncResponseData.BrandResponseData;
import com.mobi.ocs.modal.OldMerchantDataUpdate;

public interface MerchantRegistrationService {

	MerchantRegistration GetRegistrationOrderByID(String ID);

	List<Order> GetPendingDeploymentOrders();

	List<MerchantRegistration> getRegistrationOrders();

	Object CreateMerchantRegistrationForOrder(String orderId);

	MDRSyncToPaymentServer AssignMDRSyncData(int orderLineId, String productType, String cardBrand,
			String chkUpdateMDR, MerchantRegistration merchantRegistration);

	String SendMDRDataToPayment(List<MDRSyncToPaymentServer> lstMdrSync, int quotationId, String orderId, HttpServletRequest req);

	void UpdateMerchantRegistration(MerchantRegistration registration);

	MerchRegDto AssignMerchRegData(MerchantRegistration reg, int flag);

	String SendMerchantRegToPayment(MerchRegDto regReq, String orderId, int flag);

	void setSignatureForSalesRole(int signatureId, String name);

	MDRSyncToPaymentServer AssignMDRSyncDataEzysplit(int orderLineId, EzysplitRateID ezysplitRateIDs,
			String chkUpdateMDR);

	EzysplitRateID GetRateIDs(mdrSyncDto sync);

	void UpdateSuccessStatusForRegistration(String orderId);

	void updateAPIKeyAndActivationCode(String mobiApiKey, String activationCode, int quotationId, String password,
			String userName);

	Object GetMerchantRegistrationListMobile(String string);

	Object getGrapPayReport();

	Object submitEWalletMDR(String registrationID);

	MerchantRegistration getMerchantRegistrationByRegistrationId(int id);

	void updateMerchantAppCredentials(String password, String username, String activationCode, String mobiApiKey,
			int merchantRegistrationId);

	Object getMerchantRegistrationByOrderId(String orderId);

	String UpdateOldMerchantData(OldMerchantDataUpdate requestData);

	Object submitGrabPayEWalletMDR(String registrationID);

	void updateEwalletSyncCompleted(Integer valueOf);

}
