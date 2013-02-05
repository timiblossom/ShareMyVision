package com.smv.service.user.db.dbobject;

import com.smv.util.db.AbstractVersionedDatedDO;

public class CcInfoDO extends AbstractVersionedDatedDO {

	private String lastFour;
	private String expireMmyy;
	private String type;
	private String status;
	private Long addressId;

	public String getLastFour() {
		return lastFour;
	}
	public void setLastFour(String lastFour) {
		this.lastFour = lastFour;
	}
	public String getExpireMmyy() {
		return expireMmyy;
	}
	public void setExpireMmyy(String expireMmyy) {
		this.expireMmyy = expireMmyy;
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
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	
	public String toString() {
		StringBuffer result = super.getString();

		result.append("lastFour: " + this.getLastFour() + '\n');
		result.append("expireMmyy: " + this.getExpireMmyy() + '\n');
		result.append("type: " + this.getType() + '\n');
		result.append("status: " + this.getStatus() + '\n');
		result.append("addressId: " + this.getAddressId() + '\n');
		
		return result.toString();
	}

}
