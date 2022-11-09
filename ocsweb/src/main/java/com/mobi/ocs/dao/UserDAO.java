package com.mobi.ocs.dao;

import com.mobi.ocs.entity.UserDetail;
import com.mobi.ocs.modal.UpdateAuthoritiesRequestData;

public interface UserDAO {

	String GetUserOldPassword(String userName);

	int ResetUserPassword(String userName, String newPassword);

    void setSignatureForSalesRole(int signatureId, String userName);

	Object searchUserPhoneNumber(String phoneNumber);

	int addNewUserDetails(UserDetail userDetail, String Role);

	Object updateAuthorities(String phone, String accessRequired);

	Object getUserDetail(String phone);

	Object authoritiesUpdate(UpdateAuthoritiesRequestData requestData);
	
	Boolean isUserExist(String userName);

}
