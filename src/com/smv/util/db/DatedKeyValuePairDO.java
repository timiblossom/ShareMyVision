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
public class DatedKeyValuePairDO extends AbstractDatedDO {

	private String keyPair;
	private String valuePair;
	private Long parentContainerId;
	
	public DatedKeyValuePairDO() {
		super();
	}

	/**
	 * @param id
	 */
	public DatedKeyValuePairDO(Long id) {
		super(id);
	}

	/**
	 * @param id
	 */
	public DatedKeyValuePairDO(	Long id, 
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
	public DatedKeyValuePairDO(	Long id, 
							Date updatedOn,
							Timestamp createdOn) {
		super(id, updatedOn, createdOn);
	}

	/**
	 * @param id
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 */
	public DatedKeyValuePairDO(	Long id, 
							Date updatedOn,
							Timestamp createdOn,
							String keyPair, 
							String valuePair,
							Long parentContainerId) {
		super(id, updatedOn, createdOn);
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
		builder.append("DatedKeyValuePairDO [keyPair=");
		builder.append(keyPair);
		builder.append(", valuePair=");
		builder.append(valuePair);
		builder.append(", parentContainerId=");
		builder.append(parentContainerId);
		builder.append(", super.toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
