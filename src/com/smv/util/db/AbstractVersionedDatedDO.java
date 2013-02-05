package com.smv.util.db;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;


/**
 * @author Tri Nguyen
 * @author Minh Do
 * 03/2010
 */

public abstract class AbstractVersionedDatedDO extends AbstractDO {

	private static final long serialVersionUID = -6586896997366632016L;

	protected Long id;
	protected Long version;
	protected Date updatedOn;
	protected Timestamp createdOn;
	
	public AbstractVersionedDatedDO() {
		super();
		updatedOn = new Date();
		createdOn = new Timestamp( updatedOn.getTime());
	}
	
	
	public AbstractVersionedDatedDO(Long id) {
		super();
		setId(id);
	}

	/**
	 * @param id
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 */
	public AbstractVersionedDatedDO(Long id, 
			Long versionId,
			Date updatedOn, 
			Timestamp createdOn) {
		super();
		setId(id);
		setVersion(versionId);
		setUpdatedOn(updatedOn);
		setCreatedOn(createdOn);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	public Date getUpdatedOn() {
		return updatedOn;
	}
	
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}
	
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	protected StringBuffer getString() {
		StringBuffer result = new StringBuffer();
		
		result.append("id: " + getId() + '\n');
		result.append("versionId: " + getVersion() + '\n');
		result.append("updatedOn: " + getUpdatedOn() + '\n');
		result.append("createdOn: " + getCreatedOn() + '\n');
		
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AbstractVersionedDatedDO [createdOn=");
		builder.append(createdOn);
		builder.append(", id=");
		builder.append(id);
		builder.append(", updatedOn=");
		builder.append(updatedOn);
		builder.append(", version=");
		builder.append(version);
		builder.append(", operation=");
		builder.append(operation);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
