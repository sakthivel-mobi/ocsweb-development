package com.mobi.ocs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobi.ocs.dao.QuotationDAO;
import com.mobi.ocs.dao.UserDAO;
import com.mobi.ocs.dto.CommonResponseData;
import com.mobi.ocs.entity.UserDetail;
import com.mobi.ocs.modal.UpdateAuthoritiesRequestData;
import com.mobi.ocs.modal.UserApprovalRequestData;

@Service
public class UserServicesImpl implements UserServices {

	@Autowired
	UserDAO userDAO;

	@Autowired
	QuotationDAO quotationDAO;

	@Override
	public String getOldPassword(String userName) {
		return userDAO.GetUserOldPassword(userName);
	}

	@Override
	public int ResetPassword(String userName, String newPassword) {
		// TODO Auto-generated method stub
		return userDAO.ResetUserPassword(userName, newPassword);
	}

	@Override
	public Object searchUserPhoneNumber(String phoneNumber) {
		Object response = userDAO.searchUserPhoneNumber(phoneNumber);
		return response;
	}

	@Override
	public Object submitForApproval(UserApprovalRequestData requestData, String name) {
//		 if phone number exist  in user detail
		UserDetail result = (UserDetail) userDAO.getUserDetail(requestData.getPhone());
		if (result != null) {
//			alert back
			return new CommonResponseData("0000", "User details already exist", null);
		} else {
			UserDetail userDetail = new UserDetail();
			userDetail.setName(requestData.getName());
			userDetail.setAliasName(requestData.getAlias());
			userDetail.setEmail(requestData.getEmail());
			userDetail.setPhone(requestData.getPhone());
			userDetail.setnRIC(requestData.getNric());
			int status = userDAO.addNewUserDetails(userDetail, requestData.getAccessRequired());
			return new CommonResponseData("0000", "User details added", null);
		}
	}

	@Override
	public Object authoritiesUpdate(UpdateAuthoritiesRequestData requestData) {
		Object response = userDAO.authoritiesUpdate(requestData);
		return response;
	}

	@Override
	public Boolean isUserExist(String username) {
		return userDAO.isUserExist(username);
	}

}
