package com.smv.common.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Tri Nguyen
 * @author Minh Do
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserExtraInfoDTO")
public class UserExtraInfoDTO implements Serializable {
	
	private String action;
	private String userExtraInfoIndustry;
	private String userExtraInfoCompany;
	private String userExtraInfoCompanySize;
	private String userExtraInfoZipCode;
	private String userExtraInfoTitle;
	private String userExtraInfoJobTitle;
	private String userExtraInfoMobileDeviceModel;
	private String userExtraInfoMobileManufacturer;
	private String timeZone;
	private String timeZoneLabel;
	private Long id;
	
	public String getAction() {
		return action;
	}	

	public void setAction(String action) {
		this.action = action;
	}

	public String getUserExtraInfoIndustry() {
		return userExtraInfoIndustry;
	}

	public void setUserExtraInfoIndustry(String userExtraInfoIndustry) {
		this.userExtraInfoIndustry = userExtraInfoIndustry;
	}

	public String getUserExtraInfoCompany() {
		return userExtraInfoCompany;
	}

	public void setUserExtraInfoCompany(String userExtraInfoCompany) {
		this.userExtraInfoCompany = userExtraInfoCompany;
	}

	public String getUserExtraInfoCompanySize() {
		return userExtraInfoCompanySize;
	}

	public void setUserExtraInfoCompanySize(String userExtraInfoCompanySize) {
		this.userExtraInfoCompanySize = userExtraInfoCompanySize;
	}

	public String getUserExtraInfoZipCode() {
		return userExtraInfoZipCode;
	}

	public void setUserExtraInfoZipCode(String userExtraInfoZipCode) {
		this.userExtraInfoZipCode = userExtraInfoZipCode;
	}

	public String getUserExtraInfoTitle() {
		return userExtraInfoTitle;
	}

	public void setUserExtraInfoTitle(String userExtraInfoTitle) {
		this.userExtraInfoTitle = userExtraInfoTitle;
	}

	public String getUserExtraInfoJobTitle() {
		return userExtraInfoJobTitle;
	}

	public void setUserExtraInfoJobTitle(String userExtraInfoJobTitle) {
		this.userExtraInfoJobTitle = userExtraInfoJobTitle;
	}

	public String getUserExtraInfoMobileDeviceModel() {
		return userExtraInfoMobileDeviceModel;
	}

	public void setUserExtraInfoMobileDeviceModel(
			String userExtraInfoMobileDeviceModel) {
		this.userExtraInfoMobileDeviceModel = userExtraInfoMobileDeviceModel;
	}

	public String getUserExtraInfoMobileManufacturer() {
		return userExtraInfoMobileManufacturer;
	}

	public void setUserExtraInfoMobileManufacturer(
			String userExtraInfoMobileManufacturer) {
		this.userExtraInfoMobileManufacturer = userExtraInfoMobileManufacturer;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getTimeZoneLabel() {
		return timeZoneLabel;
	}

	public void setTimeZoneLabel(String timeZoneLabel) {
		this.timeZoneLabel = timeZoneLabel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserExtraInfoDTO [action=");
		builder.append(action);
		builder.append(", id=");
		builder.append(id);
		builder.append(", timeZone=");
		builder.append(timeZone);
		builder.append(", timeZoneLabel=");
		builder.append(timeZoneLabel);
		builder.append(", userExtraInfoCompany=");
		builder.append(userExtraInfoCompany);
		builder.append(", userExtraInfoCompanySize=");
		builder.append(userExtraInfoCompanySize);
		builder.append(", userExtraInfoIndustry=");
		builder.append(userExtraInfoIndustry);
		builder.append(", userExtraInfoJobTitle=");
		builder.append(userExtraInfoJobTitle);
		builder.append(", userExtraInfoMobileDeviceModel=");
		builder.append(userExtraInfoMobileDeviceModel);
		builder.append(", userExtraInfoMobileManufacturer=");
		builder.append(userExtraInfoMobileManufacturer);
		builder.append(", userExtraInfoTitle=");
		builder.append(userExtraInfoTitle);
		builder.append(", userExtraInfoZipCode=");
		builder.append(userExtraInfoZipCode);
		builder.append("]");
		return builder.toString();
	}

}
