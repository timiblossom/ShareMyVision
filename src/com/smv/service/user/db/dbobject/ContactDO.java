package com.smv.service.user.db.dbobject;

import com.smv.util.db.AbstractVersionedDatedDO;

public class ContactDO extends AbstractVersionedDatedDO {

	private String firstName;
	private String lastName;
	private String additionalEmail;
	private Long residentAddressId;
	private Long shippingAddressId;
	private Long billingAddressId;
	private String workPhone;
	private String mobilePhone;
	private String homePhone;
	private String aim;
	private String yim;
	private String skype;
	private String facebook;
	private String twitter;

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAdditionalEmail() {
		return additionalEmail;
	}
	public void setAdditionalEmail(String additionalEmail) {
		this.additionalEmail = additionalEmail;
	}
	public Long getResidentAddressId() {
		return residentAddressId;
	}
	public void setResidentAddressId(Long residentAddressId) {
		this.residentAddressId = residentAddressId;
	}
	public Long getShippingAddressId() {
		return shippingAddressId;
	}
	public void setShippingAddressId(Long shippingAddressId) {
		this.shippingAddressId = shippingAddressId;
	}
	public Long getBillingAddressId() {
		return billingAddressId;
	}
	public void setBillingAddressId(Long billingAddressId) {
		this.billingAddressId = billingAddressId;
	}
	public String getWorkPhone() {
		return workPhone;
	}
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getAim() {
		return aim;
	}
	public void setAim(String aim) {
		this.aim = aim;
	}
	public String getYim() {
		return yim;
	}
	public void setYim(String yim) {
		this.yim = yim;
	}
	public String getSkype() {
		return skype;
	}
	public void setSkype(String skype) {
		this.skype = skype;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	
	public String toString() {
		StringBuffer result = super.getString();

		result.append("firstName: " + this.getFirstName() + '\n');
		result.append("lastName: " + this.getLastName() + '\n');
		result.append("additionalEmail: " + this.getAdditionalEmail() + '\n');
		result.append("residentAddressId: " + this.getResidentAddressId() + '\n');
		result.append("shippingAddressId: " + this.getShippingAddressId() + '\n');
		result.append("billingAddressId: " + this.getBillingAddressId() + '\n');
		result.append("workPhone: " + this.getWorkPhone() + '\n');
		result.append("mobilePhone: " + this.getMobilePhone() + '\n');
		result.append("homePhone: " + this.getHomePhone() + '\n');
		result.append("aim: " + this.getAim() + '\n');
		result.append("yim: " + this.getYim() + '\n');
		result.append("skype: " + this.getSkype() + '\n');
		result.append("facebook: " + this.getFacebook() + '\n');
		result.append("twitter: " + this.getTwitter() + '\n');
		
		return result.toString();
	}

}
