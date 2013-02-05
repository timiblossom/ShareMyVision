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
public class CatalogDO extends AbstractCatalogDO {

	private Long productId;
	private Long serviceId;

	public CatalogDO() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 * @param name
	 * @param productId
	 * @param serviceId
	 */
	public CatalogDO(
			Long id, 
			Long versionId,
			Date updatedOn, 
			Timestamp createdOn,
			String name,
			Long productId,
			Long serviceId
			) {
		super(id, versionId, updatedOn, createdOn, name);
		setProductId(productId);
		setServiceId(serviceId);
	}

	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	/**
	 * @return the serviceId
	 */
	public Long getServiceId() {
		return serviceId;
	}
	/**
	 * @param serviceId the serviceId to set
	 */
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getCatalogName() {
		return super.getName();
	}
	
	/**
	 * @param catalogName
	 */
	public void setCatalogName(String catalogName) {
		super.setName(catalogName);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CatalogDO [productId=");
		builder.append(productId);
		builder.append(", serviceId=");
		builder.append(serviceId);
		builder.append(", getCatalogName()=");
		builder.append(getCatalogName());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
