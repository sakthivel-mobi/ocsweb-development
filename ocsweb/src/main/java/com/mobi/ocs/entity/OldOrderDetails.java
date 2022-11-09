package com.mobi.ocs.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orderdetails")
public class OldOrderDetails {
	@Id
	@Column(name = "OrderDetailId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int OrderDetailId;

	@Column(name = "OrderId")
	private String OrderId;
	
	@Column(name = "Username")
	private String Username;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ProductId")
	private ProductDotNet product;

	@Column(name = "isEZYSPLIT")
	private String isEZYSPLIT;

	@Column(name = "Insta03")
	private String Insta03;
	@Column(name = "Insta06")
	private String Insta06;
	@Column(name = "Insta09")
	private String Insta09;
	@Column(name = "Insta12")
	private String Insta12;
	@Column(name = "Quantity")
	private String Quantity;
	@Column(name = "UnitPrice")
	private String UnitPrice;
	@Column(name = "MID")
	private String MID;
	@Column(name = "SETTLEMENTPERIOD")
	private String SETTLEMENTPERIOD;
	@Column(name = "GrabSettlement")
	private String GrabSettlement;
	@Column(name = "BoostSettlement")
	private String BoostSettlement;
	@Column(name = "FPXSettlement")
	private String FPXSettlement;
	@Column(name = "TID")
	private String TID;
	@Column(name = "EZYLINKTID")
	private String EZYLINKTID;
	@Column(name = "MDRCreditApproved")
	private String MDRCreditApproved;
	@Column(name = "MDRDebitApproved")
	private String MDRDebitApproved;
	@Column(name = "DTL")
	private String DTL;
	@Column(name = "HASHKEY")
	private String HASHKEY;
	@Column(name = "TNCFILE")
	private String TNCFILE;
	@Column(name = "APIKEY")
	private String APIKEY;
	@Column(name = "ACTIVATIONCODE")
	private String ACTIVATIONCODE;
	@Column(name = "CALLBACKURL")
	private String CALLBACKURL;
	@Column(name = "REFEREEMID")
	private String REFEREEMID;
	@Column(name = "DEVICEID")
	private String DEVICEID;
	@Column(name = "REFNO")
	private String REFNO;
	@Column(name = "GRABPAYMDR")
	private String GRABPAYMDR;
	@Column(name = "BOOSTMDR")
	private String BOOSTMDR;
	@Column(name = "FPXMDRRM")
	private String FPXMDRRM;
	@Column(name = "FPXMDRPERCENT")
	private String FPXMDRPERCENT;

	public OldOrderDetails() {

	}

	public int getOrderDetailId() {
		return OrderDetailId;
	}

	public void setOrderDetailId(int orderDetailId) {
		OrderDetailId = orderDetailId;
	}

	public String getOrderId() {
		return OrderId;
	}

	public void setOrderId(String orderId) {
		OrderId = orderId;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public ProductDotNet getProductId() {
		return product;
	}

	public void setProductId(ProductDotNet productId) {
		product = productId;
	}

	public String getIsEZYSPLIT() {
		return isEZYSPLIT;
	}

	public void setIsEZYSPLIT(String isEZYSPLIT) {
		this.isEZYSPLIT = isEZYSPLIT;
	}

	public String getInsta03() {
		return Insta03;
	}

	public void setInsta03(String insta03) {
		Insta03 = insta03;
	}

	public String getInsta06() {
		return Insta06;
	}

	public void setInsta06(String insta06) {
		Insta06 = insta06;
	}

	public String getInsta09() {
		return Insta09;
	}

	public void setInsta09(String insta09) {
		Insta09 = insta09;
	}

	public String getInsta12() {
		return Insta12;
	}

	public void setInsta12(String insta12) {
		Insta12 = insta12;
	}

	public String getQuantity() {
		return Quantity;
	}

	public void setQuantity(String quantity) {
		Quantity = quantity;
	}

	public String getUnitPrice() {
		return UnitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		UnitPrice = unitPrice;
	}

	public String getMID() {
		return MID;
	}

	public void setMID(String mID) {
		MID = mID;
	}

	public String getSETTLEMENTPERIOD() {
		return SETTLEMENTPERIOD;
	}

	public void setSETTLEMENTPERIOD(String sETTLEMENTPERIOD) {
		SETTLEMENTPERIOD = sETTLEMENTPERIOD;
	}

	public String getGrabSettlement() {
		return GrabSettlement;
	}

	public void setGrabSettlement(String grabSettlement) {
		GrabSettlement = grabSettlement;
	}

	public String getBoostSettlement() {
		return BoostSettlement;
	}

	public void setBoostSettlement(String boostSettlement) {
		BoostSettlement = boostSettlement;
	}

	public String getFPXSettlement() {
		return FPXSettlement;
	}

	public void setFPXSettlement(String fPXSettlement) {
		FPXSettlement = fPXSettlement;
	}

	public String getTID() {
		return TID;
	}

	public void setTID(String tID) {
		TID = tID;
	}

	public String getEZYLINKTID() {
		return EZYLINKTID;
	}

	public void setEZYLINKTID(String eZYLINKTID) {
		EZYLINKTID = eZYLINKTID;
	}

	public String getMDRCreditApproved() {
		return MDRCreditApproved;
	}

	public void setMDRCreditApproved(String mDRCreditApproved) {
		MDRCreditApproved = mDRCreditApproved;
	}

	public String getMDRDebitApproved() {
		return MDRDebitApproved;
	}

	public void setMDRDebitApproved(String mDRDebitApproved) {
		MDRDebitApproved = mDRDebitApproved;
	}

	public String getDTL() {
		return DTL;
	}

	public void setDTL(String dTL) {
		DTL = dTL;
	}

	public String getHASHKEY() {
		return HASHKEY;
	}

	public void setHASHKEY(String hASHKEY) {
		HASHKEY = hASHKEY;
	}

	public String getTNCFILE() {
		return TNCFILE;
	}

	public void setTNCFILE(String tNCFILE) {
		TNCFILE = tNCFILE;
	}

	public String getAPIKEY() {
		return APIKEY;
	}

	public void setAPIKEY(String aPIKEY) {
		APIKEY = aPIKEY;
	}

	public String getACTIVATIONCODE() {
		return ACTIVATIONCODE;
	}

	public void setACTIVATIONCODE(String aCTIVATIONCODE) {
		ACTIVATIONCODE = aCTIVATIONCODE;
	}

	public String getCALLBACKURL() {
		return CALLBACKURL;
	}

	public void setCALLBACKURL(String cALLBACKURL) {
		CALLBACKURL = cALLBACKURL;
	}

	public String getREFEREEMID() {
		return REFEREEMID;
	}

	public void setREFEREEMID(String rEFEREEMID) {
		REFEREEMID = rEFEREEMID;
	}

	public String getDEVICEID() {
		return DEVICEID;
	}

	public void setDEVICEID(String dEVICEID) {
		DEVICEID = dEVICEID;
	}

	public String getREFNO() {
		return REFNO;
	}

	public void setREFNO(String rEFNO) {
		REFNO = rEFNO;
	}

	public String getGRABPAYMDR() {
		return GRABPAYMDR;
	}

	public void setGRABPAYMDR(String gRABPAYMDR) {
		GRABPAYMDR = gRABPAYMDR;
	}

	public String getBOOSTMDR() {
		return BOOSTMDR;
	}

	public void setBOOSTMDR(String bOOSTMDR) {
		BOOSTMDR = bOOSTMDR;
	}

	public String getFPXMDRRM() {
		return FPXMDRRM;
	}

	public void setFPXMDRRM(String fPXMDRRM) {
		FPXMDRRM = fPXMDRRM;
	}

	public String getFPXMDRPERCENT() {
		return FPXMDRPERCENT;
	}

	public void setFPXMDRPERCENT(String fPXMDRPERCENT) {
		FPXMDRPERCENT = fPXMDRPERCENT;
	}

	@Override
	public String toString() {
		return "OldOrderDetails [ACTIVATIONCODE=" + ACTIVATIONCODE + ", APIKEY=" + APIKEY + ", BOOSTMDR=" + BOOSTMDR
				+ ", BoostSettlement=" + BoostSettlement + ", CALLBACKURL=" + CALLBACKURL + ", DEVICEID=" + DEVICEID
				+ ", DTL=" + DTL + ", EZYLINKTID=" + EZYLINKTID + ", FPXMDRPERCENT=" + FPXMDRPERCENT + ", FPXMDRRM="
				+ FPXMDRRM + ", FPXSettlement=" + FPXSettlement + ", GRABPAYMDR=" + GRABPAYMDR + ", GrabSettlement="
				+ GrabSettlement + ", HASHKEY=" + HASHKEY + ", Insta03=" + Insta03 + ", Insta06=" + Insta06
				+ ", Insta09=" + Insta09 + ", Insta12=" + Insta12 + ", MDRCreditApproved=" + MDRCreditApproved
				+ ", MDRDebitApproved=" + MDRDebitApproved + ", MID=" + MID + ", OrderDetailId=" + OrderDetailId
				+ ", OrderId=" + OrderId + ", ProductId=" + product + ", Quantity=" + Quantity + ", REFEREEMID="
				+ REFEREEMID + ", REFNO=" + REFNO + ", SETTLEMENTPERIOD=" + SETTLEMENTPERIOD + ", TID=" + TID
				+ ", TNCFILE=" + TNCFILE + ", UnitPrice=" + UnitPrice + ", Username=" + Username + ", isEZYSPLIT="
				+ isEZYSPLIT + "]";
	}

}
