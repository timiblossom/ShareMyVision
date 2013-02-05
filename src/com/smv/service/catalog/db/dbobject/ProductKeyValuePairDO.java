/**
 * 
 */
package com.smv.service.catalog.db.dbobject;

import java.sql.Timestamp;
import java.util.Date;

import com.smv.util.db.KeyValuePairDO;

/**
 * @author TriNguyen
 *
 */
public class ProductKeyValuePairDO extends KeyValuePairDO {
	
	public ProductKeyValuePairDO() {
		super();
	}

	/**
	 * @param id
	 */
	public ProductKeyValuePairDO(Long id) {
		super(id);
	}

	/**
	 * @param id
	 * @param keyPair
	 * @param valuePair
	 * @param parentContainerId
	 */
	public ProductKeyValuePairDO(Long id, String keyPair, String valuePair, Long parentContainerId) {
		super(id, keyPair, valuePair, parentContainerId);
	}

	/**
	 * @param id
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 */
	public ProductKeyValuePairDO(Long id, Long versionId, Date updatedOn,
			Timestamp createdOn) {
		super(id, versionId, updatedOn, createdOn);
	}

	/**
	 * @param id
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 * @param keyPair
	 * @param valuePair
	 * @param parentContainerId
	 */
	public ProductKeyValuePairDO(Long id, Long versionId, Date updatedOn,
			Timestamp createdOn, String keyPair, String valuePair, Long parentContainerId) {
		super(id, versionId, updatedOn, createdOn, keyPair, valuePair, parentContainerId);
	}

	public Long getProductId() {
		return super.getParentContainerId();
	}
	
	/**
	 * @param productId
	 */
	public void setProductId(Long productId) {
		super.setParentContainerId(productId);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductKeyValuePairDO [getProductId()=");
		builder.append(getProductId());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
}
