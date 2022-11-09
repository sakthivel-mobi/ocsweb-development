package com.mobi.ocs.service;

import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import org.apache.log4j.pattern.LogEvent;
import org.springframework.stereotype.Service;

import com.mobi.ocs.dao.DocumentDAOImpl;
import com.mobi.ocs.dto.HubSpotInfo;
import com.mobi.ocs.dto.ProductDto;
import com.mobi.ocs.dto.Property;
import com.mobi.ocs.entity.Contact;
import com.mobi.ocs.entity.Product;
import com.mobi.ocs.entity.Quotation;
import com.mobi.ocs.entity.SalesPerson;
import com.mobi.ocs.entity.StandardMDRRate;

@Service
public class AssignServiceImpl implements AssignService {

	protected static Logger logger = Logger.getLogger(AssignServiceImpl.class);
	
	public Contact AssignContact(HubSpotInfo contactInfo, Property contactProp) {
		Contact contact = new Contact();
		
		try {
			contact.setId(contactInfo.getId());
			contact.setFirstName(contactProp.getFirstname() == null ? "" : contactProp.getFirstname());
			contact.setLastName(contactProp.getLastname() == null ? "" : contactProp.getLastname());
			contact.setEmail(contactProp.getEmail() == null ? "" : contactProp.getEmail());
			//contact.setPhoneNumber(contactProp.getPhone());
		} catch (Exception e) {
			logger.error(e);
		}
		return contact;
	}

	
	public Quotation AssignCompanyInfoToQuotation(HubSpotInfo companyInfo, Property companyProp) {

		Quotation quotation = new Quotation();
		
		try {
		quotation.setCompanyName(companyProp.getName() == null ? "" : companyProp.getName().getValue());
		quotation.setCity(companyProp.getCity() == null ? "" : companyProp.getCity().getValue());
		quotation.setState(companyProp.getState() == null ? "" : companyProp.getState().getValue());
		quotation.setRegistrationNumber(companyProp.getBrn() == null ? "" : companyProp.getBrn().getValue());
		quotation.setPostalCode(companyProp.getZip() == null ? "" : companyProp.getZip().getValue());
		quotation.setAddress(companyProp.getAddress() == null ? "" : companyProp.getAddress().getValue());
		
		} catch (Exception e) {
			logger.error(e);
		}
		
		return quotation;

	}


	@Override
	public Product AssignProductFromDto(ProductDto productDto) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public StandardMDRRate AssignMdrRateFromDto(ProductDto productDto) {
		// TODO Auto-generated method stub
		return null;
	}

	
//	public Product AssignProductFromDto(ProductDto productDto) {
//
//		LocalDateTime currentDateTime = LocalDateTime.now();
//		
//		Product product = new Product();
//		product.setCreatedOn(currentDateTime);
//		product.setHostType(productDto.getHostType());
//		product.setIncludeWallet(productDto.isIncludeWallet());
//		product.setOrderTypeID(productDto.getOrderTypeID());
//		product.setPayLater(productDto.isPayLater());
//		product.setProductName(productDto.getProductName());
//		product.setProductType(productDto.getProductType());
//		product.setStandardmdrRate(productDto.getStandardMdrRate());
//		product.setSubscription(productDto.getSubscription());
//		product.setUnitPrice(productDto.getUnitPrice());
//		
//		return product;
//	}
//
//	
//	public StandardMDRRate AssignMdrRateFromDto(ProductDto productDto) {
//		
//		StandardMDRRate rate = new StandardMDRRate();
//		
//		rate.setBoostMDR(productDto.getBoostMDR());
//		rate.setBoostSettlement(productDto.getBoostSettlement());
//		rate.setCreatedOn(productDto.getCreatedOn());
//		rate.setFor_Credit_Host_Master(productDto.getFor_Credit_Host_Master());
//		rate.setFor_Credit_Host_Union(productDto.getFor_Credit_Host_Union());
//		rate.setFor_Credit_Host_Visa(productDto.getFor_Credit_Host_Visa());
//		rate.setFor_Credit_Merch_Master(productDto.getFor_Credit_Merch_Master());
//		rate.setFor_Credit_Merch_Union(productDto.getFor_Credit_Merch_Union());
//		rate.setFor_Credit_Merch_Visa(productDto.getFor_Credit_Merch_Visa());
//		rate.setFor_Credit_Mobi_Master(productDto.getFor_Credit_Mobi_Master());
//		rate.setFor_Credit_Mobi_Union(productDto.getFor_Credit_Mobi_Union());
//		rate.setFor_Credit_Mobi_Visa(productDto.getFor_Credit_Mobi_Visa());
//		
//		rate.setFor_Debit_Host_Master(productDto.getFor_Debit_Host_Master());
//		rate.setFor_Debit_Host_Union(productDto.getFor_Debit_Host_Union());
//		rate.setFor_Debit_Host_Visa(productDto.getFor_Debit_Host_Visa());
//		rate.setFor_Debit_Merch_Master(productDto.getFor_Debit_Merch_Master());
//		rate.setFor_Debit_Merch_Union(productDto.getFor_Debit_Merch_Union());
//		rate.setFor_Debit_Merch_Visa(productDto.getFor_Debit_Merch_Visa());
//		rate.setFor_Debit_Mobi_Master(productDto.getFor_Debit_Mobi_Master());
//		rate.setFor_Debit_Mobi_Union(productDto.getFor_Debit_Mobi_Union());
//		rate.setFor_Debit_Mobi_Visa(productDto.getFor_Debit_Mobi_Visa());
//		
//		//Local
//		
//		rate.setLoc_Credit_Host_Master(productDto.getLoc_Credit_Host_Master());
//		rate.setLoc_Credit_Host_Union(productDto.getLoc_Credit_Host_Union());
//		rate.setLoc_Credit_Host_Visa(productDto.getLoc_Credit_Host_Visa());
//		rate.setLoc_Credit_Merch_Master(productDto.getLoc_Credit_Merch_Master());
//		rate.setLoc_Credit_Merch_Union(productDto.getLoc_Credit_Merch_Union());
//		rate.setLoc_Credit_Merch_Visa(productDto.getLoc_Credit_Merch_Visa());
//		rate.setLoc_Credit_Mobi_Master(productDto.getLoc_Credit_Mobi_Master());
//		rate.setLoc_Credit_Mobi_Union(productDto.getLoc_Credit_Mobi_Union());
//		rate.setLoc_Credit_Mobi_Visa(productDto.getLoc_Credit_Mobi_Visa());
//		
//		rate.setLoc_Debit_Host_Master(productDto.getLoc_Debit_Host_Master());
//		rate.setLoc_Debit_Host_Union(productDto.getLoc_Debit_Host_Union());
//		rate.setLoc_Debit_Host_Visa(productDto.getLoc_Debit_Host_Visa());
//		rate.setLoc_Debit_Merch_Master(productDto.getLoc_Debit_Merch_Master());
//		rate.setLoc_Debit_Merch_Union(productDto.getLoc_Debit_Merch_Union());
//		rate.setLoc_Debit_Merch_Visa(productDto.getLoc_Debit_Merch_Visa());
//		rate.setLoc_Debit_Mobi_Master(productDto.getLoc_Debit_Mobi_Master());
//		rate.setLoc_Debit_Mobi_Union(productDto.getLoc_Debit_Mobi_Union());
//		rate.setLoc_Debit_Mobi_Visa(productDto.getLoc_Debit_Mobi_Visa());
//		
//		//cus
//		rate.setLoc_Credit_Cus_Master(productDto.getLoc_Credit_Cus_Master());
//		rate.setLoc_Credit_Cus_Union(productDto.getLoc_Credit_Cus_Union());
//		rate.setLoc_Credit_Cus_Visa(productDto.getLoc_Credit_Cus_Visa());
//		
//		rate.setfPXMDR_Percent(productDto.getfPXMDR_Percent());
//		rate.setfPXMDR_RM(productDto.getfPXMDR_RM());
//		rate.setFpxSettlement(productDto.getFpxSettlement());
//		rate.setGrabMDR(productDto.getGrabMDR());
//		rate.setGrabSettlement(productDto.getGrabSettlement());
//		rate.setProductSettlement(productDto.getProductSettlement());
//		
//		rate.setProductSettlement(productDto.getProductSettlement());
//		
//		return rate;
//	}

}
