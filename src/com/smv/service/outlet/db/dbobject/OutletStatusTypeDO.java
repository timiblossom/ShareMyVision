package com.smv.service.outlet.db.dbobject;

import java.sql.Timestamp;
import java.util.Date;

import com.smv.util.db.AbstractDatedDO;

/**
 * @author Tri Nguyen
 *
 */
public class OutletStatusTypeDO extends AbstractDatedDO {

	public static final String ACTIVE = "ACTIVE";
	public static final String INACTIVE = "INACTIVE";
	
	protected String name;
	protected String description;
	
	/**
	 * 
	 */
	public OutletStatusTypeDO() {
		super();
	}

	/**
	 * @param name
	 */
	public OutletStatusTypeDO(String name) {
		setName(name);
	}

	/**
	 * @param id
	 */
	public OutletStatusTypeDO(Long id) {
		super(id);
	}

	/**
	 * @param id
	 * @param name
	 */
	public OutletStatusTypeDO(Long id, String name) {
		super(id);
		setName(name);
	}

	/**
	 * @param id
	 * @param updatedOn
	 * @param createdOn
	 */
	public OutletStatusTypeDO(Long id, Date updatedOn, Timestamp createdOn) {
		super(id, updatedOn, createdOn);
	}

	/**
	 * @param id
	 * @param updatedOn
	 * @param createdOn
	 * @param name
	 */
	public OutletStatusTypeDO(Long id, Date updatedOn, Timestamp createdOn, String name) {
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
	public OutletStatusTypeDO(Long id, Long versionId, Date updatedOn,
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
	public OutletStatusTypeDO(Long id, Date updatedOn,
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OutletStatusTypeDO [name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", id=");
		builder.append(id);
		builder.append(", updatedOn=");
		builder.append(updatedOn);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", operation=");
		builder.append(operation);
		builder.append("]");
		return builder.toString();
	}

}
