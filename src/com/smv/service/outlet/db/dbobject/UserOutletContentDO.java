package com.smv.service.outlet.db.dbobject;

import java.sql.Timestamp;
import java.util.Date;

import com.smv.util.db.AbstractDatedDO;

/**
 * @author Tri Nguyen
 *
 */
public class UserOutletContentDO extends AbstractDatedDO {

	protected String name;
	protected String description;
	protected Long userId;
	
	/**
	 * 
	 */
	public UserOutletContentDO() {
		super();
	}

	/**
	 * @param name
	 */
	public UserOutletContentDO(String name) {
		setName(name);
	}

	/**
	 * @param id
	 */
	public UserOutletContentDO(Long id) {
		super(id);
	}

	/**
	 * @param id
	 * @param name
	 */
	public UserOutletContentDO(Long id, String name) {
		super(id);
		setName(name);
	}

	/**
	 * @param id
	 * @param updatedOn
	 * @param createdOn
	 */
	public UserOutletContentDO(Long id, Date updatedOn, Timestamp createdOn) {
		super(id, updatedOn, createdOn);
	}

	/**
	 * @param id
	 * @param updatedOn
	 * @param createdOn
	 * @param name
	 */
	public UserOutletContentDO(Long id, Date updatedOn, Timestamp createdOn, String name) {
		super(id, updatedOn, createdOn);
		setName(name);
	}

	/**
	 * @param id
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 * @param name
	 * @param statusId
	 */
	public UserOutletContentDO(Long id, Long versionId, Date updatedOn,
			Timestamp createdOn, String name) {
		super(id, updatedOn, createdOn);
		setName(name);
	}

	/**
	 * @param id
	 * @param updatedOn
	 * @param createdOn
	 * @param name
	 * @param description
	 */
	public UserOutletContentDO(Long id, Date updatedOn,
			Timestamp createdOn, String name, String description) {
		super(id, updatedOn, createdOn);
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserOutletContentDO [name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", super.toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
