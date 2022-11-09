package com.mobi.ocs.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mobi.ocs.entity.OrderLines;
import com.mobi.ocs.entity.TPAFileToUmobile;

@Repository
public class TPADaoImpl implements TPADao {

	@Autowired
	private SessionFactory sessionFactory;
	protected static Logger logger = Logger.getLogger(TPADaoImpl.class);

	@Override
	public void SaveTPA(ArrayList<TPAFileToUmobile> tpas, int orderId) {

		deleteExistingOrders(orderId);

		for (TPAFileToUmobile tpaFileToUmobile : tpas) {
			logger.error("SaveTPA >> " + tpaFileToUmobile.getId());
			try {
				Session session = sessionFactory.getCurrentSession();
				session.save(tpaFileToUmobile);
			} catch (Exception e) {
				logger.error("SaveTPA >> ", e);
			}
		}

	}

	private void deleteExistingOrders(int orderId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.createQuery("DELETE TPAFileToUmobile WHERE orderId =:orderId")
					.setParameter("orderId", String.valueOf(orderId)).executeUpdate();
		} catch (Exception e) {
			logger.error("deleteExistingOrders >> ", e);
		}

	}

	@Override
	public void DeleteTPARowByID(String orderId) {
		System.out.println("DeleteTPARowByID -> " + orderId);
		Session session = sessionFactory.getCurrentSession();
		try {

			session.createQuery("DELETE FROM TPAFileToUmobile WHERE orderId = :orderId")
					.setParameter("orderId", orderId).executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Failed to Delete TPA Row By ID - ", e);
		}
	}

	@Override
	public List<TPAFileToUmobile> getItemByOrderId(String orderId) {
		try {
			Session session = sessionFactory.getCurrentSession();

			List<TPAFileToUmobile> tpaFileToUmobile = (List<TPAFileToUmobile>) session
					.createQuery("FROM TPAFileToUmobile WHERE orderId = :orderId").setParameter("orderId", orderId)
					.getResultList();
			return tpaFileToUmobile;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Failed to get Item by OrderID - ", e);
			return null;
		}

	}

}
