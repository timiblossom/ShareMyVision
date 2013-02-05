/**
 * 
 */
package com.smv.service.catalog.db.dbobject;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author TriNguyen
 *
 */
public class ServiceDO extends AbstractCatalogDO {

	public ServiceDO() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 * @param name
	 */
	public ServiceDO(
			Long id, 
			Long versionId,
			Date updatedOn, 
			Timestamp createdOn,
			String name
			) {
		super(id, versionId, updatedOn, createdOn, name);
	}

	public String getServiceName() {
		return super.getName();
	}
	
	/**
	 * @param serviceName
	 */
	public void setServiceName(String serviceName) {
		super.setName(serviceName);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServiceDO [getServiceName()=");
		builder.append(getServiceName());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
