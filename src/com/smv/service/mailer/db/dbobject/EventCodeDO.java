/**
 * 
 */
package com.smv.service.mailer.db.dbobject;

import java.sql.Timestamp;
import java.util.Date;

import com.smv.util.db.AbstractVersionedDatedDO;

/**
 * @author TriNguyen
 *
 */
public class EventCodeDO extends AbstractVersionedDatedDO {
	
	String name;
	String description;

	/**
	 * 
	 */
	public EventCodeDO() {
		super();
	}

	/**
	 * @param id
	 */
	public EventCodeDO(Long id) {
		super(id);
	}

	/**
	 * @param id
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 */
	public EventCodeDO(	Long id, 
						Long versionId, 
						Date updatedOn,
						Timestamp createdOn) {
		super(id, versionId, updatedOn, createdOn);
	}

	/**
	 * @param name
	 * @param description
	 */
	public EventCodeDO(String name, String description) {
		super();
		setName(name);
		this.setDescription(description);
	}

	/**
	 * @param id
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 * @param name
	 * @param description
	 */
	public EventCodeDO(	Long id, 
						Long versionId, 
						Date updatedOn,
						Timestamp createdOn,
						String name,
						String description) {
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EventCodeDO [description=");
		builder.append(description);
		builder.append(", name=");
		builder.append(name);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
