package com.mobi.ocs.dao;

import java.util.ArrayList;
import java.util.List;

import com.mobi.ocs.entity.OrderLines;
import com.mobi.ocs.entity.TPAFileToUmobile;

public interface TPADao {

	void SaveTPA(ArrayList<TPAFileToUmobile> tpaArrayList, int orderId);

	void DeleteTPARowByID(String orderId);
	
	List<TPAFileToUmobile> getItemByOrderId(String orderId);

}
