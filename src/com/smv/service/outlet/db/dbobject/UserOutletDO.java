package com.smv.service.outlet.db.dbobject;

import java.sql.Timestamp;
import java.util.Date;

import com.smv.util.db.AbstractDatedDO;

/**
 * @author TriNguyen
 *
 */
public class UserOutletDO extends AbstractDatedDO {

	protected String name;
	protected String description;
	protected Long outletId;
	protected Long userId;
	protected Long statusId;

	
	public UserOutletDO() {
		super();
	}

	/**
	 * @param id
	 * @param updatedOn
	 * @param createdOn
	 * @param outletId
	 * @param userId
	 */
	public UserOutletDO(
			Long id, 
			Date updatedOn, 
			Timestamp createdOn,
			Long outletId,
			Long userId
			) {
		super(id, updatedOn, createdOn);
		setOutletId(outletId);
		setUserId(userId);
	}

	/**
	 * @param id
	 * @param updatedOn
	 * @param createdOn
	 * @param outletId
	 * @param userId
	 */
	public UserOutletDO(
			Long id, 
			Date updatedOn, 
			Timestamp createdOn,
			Long outletId,
			Long userId,
			String name,
			String description
			) {
		super(id, updatedOn, createdOn);
		setOutletId(outletId);
		setUserId(userId);
		setName(name);
		setDescription(description);
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
	 * @return the outletId
	 */
	public Long getOutletId() {
		return outletId;
	}
	/**
	 * @param outletId the outletId to set
	 */
	public void setOutletId(Long outletId) {
		this.outletId = outletId;
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
	 * @return the statusId
	 */
	public Long getStatusId() {
		return statusId;
	}

	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserOutletDO [name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", outletId=");
		builder.append(outletId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", statusId=");
		builder.append(statusId);
		builder.append(", super.toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}


}
