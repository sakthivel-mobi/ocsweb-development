package com.mobi.ocs.service;

import com.mobi.ocs.dto.HubSpotInfo;
import com.mobi.ocs.dto.ProductDto;
import com.mobi.ocs.dto.Property;
import com.mobi.ocs.entity.Contact;
import com.mobi.ocs.entity.Product;
import com.mobi.ocs.entity.Quotation;
import com.mobi.ocs.entity.SalesPerson;
import com.mobi.ocs.entity.StandardMDRRate;

public interface AssignService {

	Contact AssignContact(HubSpotInfo contactInfo, Property contactProp);

	Quotation AssignCompanyInfoToQuotation(HubSpotInfo companyInfo, Property companyProp);

	Product AssignProductFromDto(ProductDto productDto);

	StandardMDRRate AssignMdrRateFromDto(ProductDto productDto);

}
