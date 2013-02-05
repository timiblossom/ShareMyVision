/**
 * 
 */
package com.smv.service.catalog.db.dbobject;

import java.sql.Timestamp;
import java.util.Date;

import com.smv.util.db.AbstractVersionedDatedDO;

/**
 * @author TriNguyen
 *
 */
public abstract class AbstractCatalogDO extends AbstractVersionedDatedDO {

	protected String name;
	protected String description;
	protected Long statusId;
	protected Date startDate;
	protected Date endDate;
	
	/**
	 * 
	 */
	public AbstractCatalogDO() {
		super();
	}

	/**
	 * @param name
	 */
	public AbstractCatalogDO(String name) {
		setName(name);
	}

	/**
	 * @param id
	 */
	public AbstractCatalogDO(Long id) {
		super(id);
	}

	/**
	 * @param id
	 * @param name
	 */
	public AbstractCatalogDO(Long id, String name) {
		super(id);
		setName(name);
	}

	/**
	 * @param id
	 * @param name
	 */
	public AbstractCatalogDO(Long id, String name, Long statusId) {
		super(id);
		setName(name);
		setStatusId(statusId);
	}

	/**
	 * @param id
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 */
	public AbstractCatalogDO(Long id, Long versionId, Date updatedOn,
			Timestamp createdOn) {
		super(id, versionId, updatedOn, createdOn);
	}

	/**
	 * @param id
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 * @param name
	 */
	public AbstractCatalogDO(Long id, Long versionId, Date updatedOn,
			Timestamp createdOn, String name) {
		super(id, versionId, updatedOn, createdOn);
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
	public AbstractCatalogDO(Long id, Long versionId, Date updatedOn,
			Timestamp createdOn, String name, Long statusId) {
		super(id, versionId, updatedOn, createdOn);
		setName(name);
		setStatusId(statusId);
	}

	/**
	 * @param id
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 * @param name
	 * @param description
	 * @param statusId
	 * @param startDate
	 * @param endDate
	 */
	public AbstractCatalogDO(Long id, Long versionId, Date updatedOn,
			Timestamp createdOn, String name, String description, Long statusId,
			Date startDate, Date endDate) {
		super(id, versionId, updatedOn, createdOn);
		setName(name);
		setDescription(description);
		setStatusId(statusId);
		setStartDate(startDate);
		setEndDate(endDate);
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

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AbstractCatalogDO [name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", statusId=");
		builder.append(statusId);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", id=");
		builder.append(id);
		builder.append(", version=");
		builder.append(version);
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
