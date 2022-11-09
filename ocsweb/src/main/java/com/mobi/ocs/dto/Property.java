package com.mobi.ocs.dto;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Property {

	//Contact Info
	private String firstname;
	private String lastname;
	//private String phone;
	private String email;
	
	//Company Data
	private ValueString name;
	private ValueString nob;
	private ValueString city;
	private ValueString state;
	private ValueString brn;
	private ValueString zip;
	private ValueString address;
	private ValueString product_subscribed;
	private ValueString hubspot_owner_id;
	private ValueString phone;
	
	
	
	@Override
	public String toString() {
		return "Property [firstname=" + firstname + ", lastname=" + lastname + ", phone=" + phone + ", email=" + email
				+ ", name=" + name + ", nob=" + nob + ", city=" + city + ", state=" + state + ", brn=" + brn + ", zip="
				+ zip + ", address=" + address + ", product_subscribed=" + product_subscribed + ", hubspot_owner_id="
				+ hubspot_owner_id + "]";
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public ValueString getPhone() {
		return phone;
	}
	public void setPhone(ValueString phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ValueString getName() {
		return name;
	}
	public void setName(ValueString name) {
		this.name = name;
	}
	public ValueString getNob() {
		return nob;
	}
	public void setNob(ValueString nob) {
		this.nob = nob;
	}
	public ValueString getCity() {
		return city;
	}
	public void setCity(ValueString city) {
		this.city = city;
	}
	public ValueString getState() {
		return state;
	}
	public void setState(ValueString state) {
		this.state = state;
	}
	public ValueString getBrn() {
		return brn;
	}
	public void setBrn(ValueString brn) {
		this.brn = brn;
	}
	public ValueString getZip() {
		return zip;
	}
	public void setZip(ValueString zip) {
		this.zip = zip;
	}
	public ValueString getAddress() {
		return address;
	}
	public void setAddress(ValueString address) {
		this.address = address;
	}
	public ValueString getProduct_subscribed() {
		return product_subscribed;
	}
	public void setProduct_subscribed(ValueString product_subscribed) {
		this.product_subscribed = product_subscribed;
	}
	public ValueString getHubspot_owner_id() {
		return hubspot_owner_id;
	}
	public void setHubspot_owner_id(ValueString hubspot_owner_id) {
		this.hubspot_owner_id = hubspot_owner_id;
	}
	
	
}