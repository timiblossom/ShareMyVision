package com.smv.service.outlet.db.dbobject;

import java.sql.Timestamp;
import java.util.Date;

import com.smv.util.db.DatedKeyValuePairDO;

/**
 * @author Tri Nguyen
 *
 */
public class UserOutletKeyValuePairDO extends DatedKeyValuePairDO {

	public UserOutletKeyValuePairDO() {
		super();
	}

	/**
	 * @param id
	 */
	public UserOutletKeyValuePairDO(Long id) {
		super(id);
	}

	/**
	 * @param id
	 * @param keyPair
	 * @param valuePair
	 * @param parentContainerId
	 */
	public UserOutletKeyValuePairDO(Long id, String keyPair, String valuePair,
			Long parentContainerId) {
		super(id, keyPair, valuePair, parentContainerId);
	}

	/**
	 * @param id
	 * @param updatedOn
	 * @param createdOn
	 */
	public UserOutletKeyValuePairDO(Long id, Date updatedOn, Timestamp createdOn) {
		super(id, updatedOn, createdOn);
	}

	/**
	 * @param id
	 * @param updatedOn
	 * @param createdOn
	 * @param keyPair
	 * @param valuePair
	 * @param parentContainerId
	 */
	public UserOutletKeyValuePairDO(Long id, Date updatedOn,
			Timestamp createdOn, String keyPair, String valuePair,
			Long parentContainerId) {
		super(id, updatedOn, createdOn, keyPair, valuePair, parentContainerId);
	}

	public Long getUserOutletId() {
		return super.getParentContainerId();
	}
	
	/**
	 * @param serviceId
	 */
	public void setUserOutletId(Long userOutletId) {
		super.setParentContainerId(userOutletId);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserOutletKeyValuePairDO [getUserOutletId()=");
		builder.append(getUserOutletId());
		builder.append(", super.toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
