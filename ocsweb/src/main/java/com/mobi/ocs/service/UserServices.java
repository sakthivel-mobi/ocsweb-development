package com.mobi.ocs.service;

import com.mobi.ocs.modal.UpdateAuthoritiesRequestData;
import com.mobi.ocs.modal.UserApprovalRequestData;

public interface UserServices {

	String getOldPassword(String userName);

	int ResetPassword(String userName, String newPassword);
	
	Boolean isUserExist(String username);

	Object searchUserPhoneNumber(String phoneNumber);

	Object submitForApproval(UserApprovalRequestData requestData, String name);

	Object authoritiesUpdate(UpdateAuthoritiesRequestData requestData);
}
