package com.smv.service.user.db.dbobject;

import com.smv.util.db.AbstractVersionedDatedDO;

public class UserExtraInfoDO extends AbstractVersionedDatedDO {

	private String industry;
	private String company;
	private String companySize;
	private String zipCode;
	private Long timeZoneId;
	private String title;
	private String jobTitle;
	private String mobileDeviceModel;
	private String mobileManufacturer;

	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCompanySize() {
		return companySize;
	}
	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public Long getTimeZoneId() {
		return timeZoneId;
	}
	public void setTimeZoneId(Long timeZoneId) {
		this.timeZoneId = timeZoneId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getMobileDeviceModel() {
		return mobileDeviceModel;
	}
	public void setMobileDeviceModel(String mobileDeviceModel) {
		this.mobileDeviceModel = mobileDeviceModel;
	}
	public String getMobileManufacturer() {
		return mobileManufacturer;
	}
	public void setMobileManufacturer(String mobileManufacturer) {
		this.mobileManufacturer = mobileManufacturer;
	}
	
	public String toString() {
		StringBuffer result = super.getString();

		result.append("industry: " + this.getIndustry() + '\n');
		result.append("company: " + this.getCompany() + '\n');
		result.append("companySize: " + this.getCompanySize() + '\n');
		result.append("zipCode: " + this.getZipCode() + '\n');
		result.append("timeZoneId: " + this.getTimeZoneId() + '\n');
		result.append("title: " + this.getTitle() + '\n');
		result.append("jobTitle: " + this.getJobTitle() + '\n');
		result.append("mobileDeviceModel: " + this.getMobileDeviceModel() + '\n');
		result.append("mobileManufacturer: " + this.getMobileManufacturer() + '\n');
		
		return result.toString();
	}

}
