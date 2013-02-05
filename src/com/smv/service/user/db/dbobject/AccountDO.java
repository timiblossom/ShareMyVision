package com.smv.service.user.db.dbobject;

import com.smv.util.db.AbstractVersionedDatedDO;

public class AccountDO extends AbstractVersionedDatedDO {

	private String name;
	private String description;
	private String type;
	private String status;
	private Long contactId;
	private Long ccInfoId;
	private Long extraInfo;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getContactId() {
		return contactId;
	}
	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}
	public Long getCcInfoId() {
		return ccInfoId;
	}
	public void setCcInfoId(Long ccInfoId) {
		this.ccInfoId = ccInfoId;
	}
	public Long getExtraInfo() {
		return extraInfo;
	}
	public void setExtraInfo(Long extraInfo) {
		this.extraInfo = extraInfo;
	}
	
	public String toString() {
		StringBuffer result = super.getString();

		result.append("name: " + this.getName() + '\n');
		result.append("description: " + this.getDescription() + '\n');
		result.append("type: " + this.getType() + '\n');
		result.append("status: " + this.getStatus() + '\n');
		result.append("contactId: " + this.getContactId() + '\n');
		result.append("ccInfoId: " + this.getCcInfoId() + '\n');
		result.append("extraInfo: " + this.getExtraInfo() + '\n');
		
		return result.toString();
	}

}
