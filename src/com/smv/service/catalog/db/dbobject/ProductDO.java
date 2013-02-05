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
public class ProductDO extends AbstractCatalogDO {

	public ProductDO() {
		super();
	}
	
	/**
	 * @param id
	 * @param versionId,
	 * @param updatedOn, 
	 * @param createdOn
	 * @param name
	 */
	public ProductDO(
			Long id, 
			Long versionId,
			Date updatedOn, 
			Timestamp createdOn,
			String name
			) {
		super(id, versionId, updatedOn, createdOn, name);
	}

	public String getProductName() {
		return super.getName();
	}
	
	/**
	 * @param productName
	 */
	public void setProductName(String productName) {
		super.setName(productName);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductDO [getProductName()=");
		builder.append(getProductName());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
