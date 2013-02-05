/**
 * 
 */
package com.smv.service.catalog.db.dbobject;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.smv.service.catalog.db.dao.StatusDAO;
import com.smv.util.db.AbstractVersionedDatedDO;

/**
 * @author TriNguyen
 *
 */
public class StatusDO extends AbstractVersionedDatedDO {

	public static final String STATUS_NAME_ACTIVE = "ACTIVE";
	public static final String STATUS_NAME_INACTIVE = "INACTIVE";

	private String name;
	private String description;


	public static StatusDO getStatus(String name) throws IllegalArgumentException {
		List<StatusDO> allStatuses = StatusDAO.getAllStatuses();
		for (StatusDO status : allStatuses) {
			if (status.getName().equals(name)) {
				return status;
			}
		}
		
		throw new IllegalArgumentException("Non-existent Status with name: " + name);
	}
	
	public static StatusDO getStatus(Long id) throws IllegalArgumentException {
		List<StatusDO> allStatuses = StatusDAO.getAllStatuses();
		for (StatusDO status : allStatuses) {
			if (status.getId().equals(id)) {
				return status;
			}
		}
		
		throw new IllegalArgumentException("Non-existent Status with id: " + id);
	}
	

	/**
	 * 
	 */
	public StatusDO() {
	}

	/**
	 * @param id
	 */
	public StatusDO(Long id) {
		super(id);
	}

	/**
	 * @param id
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 */
	public StatusDO(Long id, Long versionId, Date updatedOn, Timestamp createdOn) {
		super(id, versionId, updatedOn, createdOn);
	}

	/**
	 * @param id
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 * @param name
	 */
	public StatusDO(Long id, Long versionId, Date updatedOn, Timestamp createdOn, String name) {
		super(id, versionId, updatedOn, createdOn);
		setName(name);
	}

	/**
	 * @param id
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 * @param name
	 */
	public StatusDO(Long id, Long versionId, Date updatedOn, Timestamp createdOn, String name, String description) {
		super(id, versionId, updatedOn, createdOn);
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

}
