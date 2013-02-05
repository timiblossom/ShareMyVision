package com.smv.common.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Tri Nguyen
 * 12/2010
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserOutletDTO")
public class UserOutletDTO implements Serializable {
	
	protected String action;
	protected String name;
	protected String description;
	protected String statusType;
	protected Long userId;
	protected OutletDTO outlet;
	protected KeyValueMapDTO keyValueMap;

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the statusType
	 */
	public String getStatusType() {
		return statusType;
	}

	/**
	 * @param statusType the statusType to set
	 */
	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the outlet
	 */
	public OutletDTO getOutlet() {
		return outlet;
	}

	/**
	 * @param outlet the outlet to set
	 */
	public void setOutlet(OutletDTO outlet) {
		this.outlet = outlet;
	}

	/**
	 * @return the keyValueMap
	 */
	public KeyValueMapDTO getKeyValueMap() {
		return keyValueMap;
	}

	/**
	 * @param keyValueMap the keyValueMap to set
	 */
	public void setKeyValueMap(KeyValueMapDTO keyValueMap) {
		this.keyValueMap = keyValueMap;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserOutletDTO [userId=");
		builder.append(userId);
		builder.append(", outlet=");
		builder.append(outlet);
		builder.append(", keyValueMap=");
		builder.append(keyValueMap);
		builder.append(", super.toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
