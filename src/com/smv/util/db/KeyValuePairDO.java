/**
 * 
 */
package com.smv.util.db;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author TriNguyen
 *
 */
public class KeyValuePairDO extends AbstractVersionedDatedDO {

	private String keyPair;
	private String valuePair;
	private Long parentContainerId;
	
	public KeyValuePairDO() {
		super();
	}

	/**
	 * @param id
	 */
	public KeyValuePairDO(Long id) {
		super(id);
	}

	/**
	 * @param id
	 */
	public KeyValuePairDO(	Long id, 
							String keyPair, 
							String valuePair, 
							Long parentContainerId) {
		super(id);
		setKeyPair(keyPair);
		setValuePair(valuePair);
		setParentContainerId(parentContainerId);
	}

	/**
	 * @param id
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 */
	public KeyValuePairDO(	Long id, 
							Long versionId, 
							Date updatedOn,
							Timestamp createdOn) {
		super(id, versionId, updatedOn, createdOn);
	}

	/**
	 * @param id
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 */
	public KeyValuePairDO(	Long id, 
							Long versionId, 
							Date updatedOn,
							Timestamp createdOn,
							String keyPair, 
							String valuePair,
							Long parentContainerId) {
		super(id, versionId, updatedOn, createdOn);
		setKeyPair(keyPair);
		setValuePair(valuePair);
		setParentContainerId(parentContainerId);
	}

	/**
	 * @return the keyPair
	 */
	public String getKeyPair() {
		return keyPair;
	}

	/**
	 * @param keyPair the keyPair to set
	 */
	public void setKeyPair(String keyPair) {
		this.keyPair = keyPair;
	}

	/**
	 * @return the valuePair
	 */
	public String getValuePair() {
		return valuePair;
	}

	/**
	 * @param valuePair the valuePair to set
	 */
	public void setValuePair(String valuePair) {
		this.valuePair = valuePair;
	}

	/**
	 * @return the parentContainerId
	 */
	public Long getParentContainerId() {
		return parentContainerId;
	}

	/**
	 * @param parentContainerId the parentContainerId to set
	 */
	public void setParentContainerId(Long parentContainerId) {
		this.parentContainerId = parentContainerId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("KeyValuePairDO [keyPair=");
		builder.append(keyPair);
		builder.append(", parentContainerId=");
		builder.append(parentContainerId);
		builder.append(", valuePair=");
		builder.append(valuePair);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", id=");
		builder.append(id);
		builder.append(", updatedOn=");
		builder.append(updatedOn);
		builder.append(", versionId=");
		builder.append(version);
		builder.append(", operation=");
		builder.append(operation);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
