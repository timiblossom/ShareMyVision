package com.smv.service.outlet.db.dbobject;

import java.sql.Timestamp;
import java.util.Date;

import com.smv.util.db.AbstractDatedDO;

/**
 * @author Tri Nguyen
 *
 */
public class OutletTypeDO extends AbstractDatedDO {

	protected String name;
	protected String prefix;
	protected String description;
	protected Long statusId;
	
	/**
	 * 
	 */
	public OutletTypeDO() {
		super();
	}

	/**
	 * @param id
	 */
	public OutletTypeDO(Long id) {
		super(id);
	}

	/**
	 * @param id
	 * @param updatedOn
	 * @param createdOn
	 */
	public OutletTypeDO(Long id, Date updatedOn, Timestamp createdOn) {
		super(id, updatedOn, createdOn);
	}

	/**
	 * @param id
	 * @param updatedOn
	 * @param createdOn
	 * @param name
	 * @param prefix
	 * @param description
	 * @param statusId
	 */
	public OutletTypeDO(Long id, Date updatedOn, Timestamp createdOn, String name, 
			String prefix,
			String description,
			Long statusId
			) {
		super(id, updatedOn, createdOn);
		setName(name);
		setPrefix(prefix);
		setDescription(description);
		setStatusId(statusId);
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
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
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
		builder.append("OutletTypeDO [name=");
		builder.append(name);
		builder.append(", prefix=");
		builder.append(prefix);
		builder.append(", description=");
		builder.append(description);
		builder.append(", statusId=");
		builder.append(statusId);
		builder.append(", super.toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}



}
