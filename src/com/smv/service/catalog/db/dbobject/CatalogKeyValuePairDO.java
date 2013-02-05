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
public class CatalogKeyValuePairDO extends KeyValuePairDO {

	public CatalogKeyValuePairDO() {
		super();
	}

	/**
	 * @param id
	 */
	public CatalogKeyValuePairDO(Long id) {
		super(id);
	}

	/**
	 * @param id
	 * @param keyPair
	 * @param valuePair
	 * @param parentContainerId
	 */
	public CatalogKeyValuePairDO(Long id, String keyPair, String valuePair,
			Long parentContainerId) {
		super(id, keyPair, valuePair, parentContainerId);
	}

	/**
	 * @param id
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 */
	public CatalogKeyValuePairDO(Long id, Long versionId, Date updatedOn,
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
	public CatalogKeyValuePairDO(Long id, Long versionId, Date updatedOn,
			Timestamp createdOn, String keyPair, String valuePair,
			Long parentContainerId) {
		super(id, versionId, updatedOn, createdOn, keyPair, valuePair,
				parentContainerId);
	}

	public Long getCatalogId() {
		return super.getParentContainerId();
	}
	
	/**
	 * @param serviceId
	 */
	public void setCatalogId(Long catalogId) {
		super.setParentContainerId(catalogId);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CatalogKeyValuePairDO [getCatalogId()=");
		builder.append(getCatalogId());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
