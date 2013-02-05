package com.smv.util.db;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;


/**
 * @author Tri Nguyen
 * 03/2010
 */

public abstract class AbstractDatedDO extends AbstractDO {

	private static final long serialVersionUID = -6586896997366632016L;

	protected Long id;
	protected Date updatedOn;
	protected Timestamp createdOn;
	
	public AbstractDatedDO() {
		super();
		updatedOn = new Date();
		createdOn = new Timestamp( updatedOn.getTime());
	}
	
	
	public AbstractDatedDO(Long id) {
		super();
		setId(id);
	}

	/**
	 * @param id
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 */
	public AbstractDatedDO(Long id, 
			Date updatedOn, 
			Timestamp createdOn) {
		super();
		setId(id);
		setUpdatedOn(updatedOn);
		setCreatedOn(createdOn);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		builder.append("AbstractDatedDO [createdOn=");
		builder.append(createdOn);
		builder.append(", id=");
		builder.append(id);
		builder.append(", updatedOn=");
		builder.append(updatedOn);
		builder.append(", operation=");
		builder.append(operation);
		builder.append(", super.toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
