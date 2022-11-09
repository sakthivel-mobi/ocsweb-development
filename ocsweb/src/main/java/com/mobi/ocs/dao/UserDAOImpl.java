package com.mobi.ocs.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.mobi.ocs.controller.QuotationController;
import com.mobi.ocs.dto.CommonResponseData;
import com.mobi.ocs.entity.User;
import com.mobi.ocs.entity.UserDetail;
import com.mobi.ocs.modal.PhoneNumberSearchResponseData;
import com.mobi.ocs.modal.UpdateAuthoritiesRequestData;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserDetailsManager userDetailsManager;

	protected static Logger logger = Logger.getLogger(UserDAOImpl.class);

	@Override
	@Transactional
	public String GetUserOldPassword(String userName) {
		System.out.println("GetUserOldPassword --> " + userName);
		try {
			Session session = sessionFactory.getCurrentSession();
			String oldStringPassword = (String) session
					.createNativeQuery("SELECT users.password FROM users WHERE username = :userName")
					.setParameter("userName", userName).getSingleResult();
			return oldStringPassword;
		} catch (Exception e) {
			logger.error("Failed to Get User Old Password", e);
			return null;
		}
	}

	@Override
	@Transactional
	public int ResetUserPassword(String userName, String newPassword) {
		System.out.println("GetUserOldPassword --> " + userName);
		try {
			Session session = sessionFactory.getCurrentSession();
			int oldStringPassword = session
					.createNativeQuery("UPDATE users SET password=:password WHERE username=:userName")
					.setParameter("userName", userName).setParameter("password", newPassword).executeUpdate();
			return oldStringPassword;
		} catch (Exception e) {
			logger.error("Failed to Reset User Password", e);
			return -1;
		}
	}

	@Override
	@Transactional
	public void setSignatureForSalesRole(int signatureId, String userName) {

		try {
			Session session = sessionFactory.getCurrentSession();
			session.createNativeQuery("UPDATE SalesPerson SET signature=:signatureId WHERE phone=:userName")
					.setParameter("userName", userName).setParameter("signatureId", signatureId).executeUpdate();
		} catch (Exception e) {
			logger.error("Failed to Get Signature for Sales Role", e);
		}

	}

	@Override
	@Transactional
	public Object searchUserPhoneNumber(String phoneNumber) {
		try {
			Session session = sessionFactory.getCurrentSession();
			List<PhoneNumberSearchResponseData> response = session.createQuery(
					"Select username as phoneNumber  FROM User WHERE username LIKE CONCAT('%',:phoneNumber,'%')")
					.setParameter("phoneNumber", phoneNumber).getResultList();

			return new CommonResponseData("0000", "Success", response);
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonResponseData("0001", "Unable to search phone number", null);

		}
	}

	@Transactional
	@Override
	public int addNewUserDetails(UserDetail userDetail, String role) {
		try {
			Session session = sessionFactory.getCurrentSession();
//			UserDetail result = (UserDetail) session.createQuery("FROM UserDetail WHERE phone=:phoneNumber")
//					.setParameter("phoneNumber", userDetail.getPhone()).uniqueResult();

			// role == slaes >> 1 or 0

			session.createSQLQuery("CALL AddUserDetail(:name,:alias,:email,:phone,:nRic,'',1,:role)")
					.addEntity(UserDetail.class).setParameter("name", userDetail.getName())
					.setParameter("alias", userDetail.getAliasName()).setParameter("email", userDetail.getEmail())
					.setParameter("phone", userDetail.getPhone()).setParameter("nRic", userDetail.getnRIC())
					.setParameter("role", role).executeUpdate();

//			if (result == null) {
//				session.save(userDetail);
//			}

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Transactional
	@Override
	public Object updateAuthorities(String phone, String accessRequired) {
		try {
			Session session = sessionFactory.getCurrentSession();
			int result = session
					.createNativeQuery("UPDATE authorities SET authority =:accessRequired WHERE username =:phoneNumber")
					.setParameter("phoneNumber", phone).setParameter("accessRequired", accessRequired).executeUpdate();

//			if (result != 1) {
//				session.createNativeQuery(
//						"INSERT INTO authorities (username,authority) VALUES (:userName, :authority) ")
//						.setParameter("userName", phone).setParameter("authority", accessRequired).executeUpdate();
//			}

			return new CommonResponseData("0000", "Success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonResponseData("0001", "Unable to update authorities", null);
		}

	}

	@Transactional
	@Override
	public Object getUserDetail(String phone) {
		try {
			Session session = sessionFactory.getCurrentSession();
			UserDetail userDetail = (UserDetail) session.createQuery("FROM UserDetail WHERE phone =:userPhone")
					.setParameter("userPhone", phone).uniqueResult();
			return userDetail;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public Object authoritiesUpdate(UpdateAuthoritiesRequestData requestData) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.createNativeQuery("UPDATE authorities set authority = :newRole WHERE username = :userName")
					.setParameter("newRole", requestData.getUserRole())
					.setParameter("userName", requestData.getUserName()).executeUpdate();

			return new CommonResponseData("0000", "Role changed to " + requestData.getUserRole().toString(), null);

		} catch (Exception e) {
			logger.error("authoritiesUpdate >> ", e);
		}
		return new CommonResponseData("0001", "Unable to update the authorities", null);
	}

	@Override
	@Transactional
	public Boolean isUserExist(String userName) {
		try {

			Session session = sessionFactory.getCurrentSession();

			User user = (User) session.createQuery("FROM User WHERE username =:username")
					.setParameter("username", userName).uniqueResult();

			logger.info("isUserExist >> " + userDetailsManager.userExists(userName));
			return userDetailsManager.userExists(userName);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}
}
