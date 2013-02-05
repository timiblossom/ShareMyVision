package com.smv.service.outlet.db.dbobject;

import java.sql.Timestamp;
import java.util.Date;

import com.smv.util.db.DatedKeyValuePairDO;

/**
 * @author Tri Nguyen
 *
 */
public class UserOutletContentKeyValuePairDO extends DatedKeyValuePairDO {

	public UserOutletContentKeyValuePairDO() {
		super();
	}

	/**
	 * @param id
	 */
	public UserOutletContentKeyValuePairDO(Long id) {
		super(id);
	}

	/**
	 * @param id
	 * @param keyPair
	 * @param valuePair
	 * @param parentContainerId
	 */
	public UserOutletContentKeyValuePairDO(Long id, String keyPair, String valuePair,
			Long parentContainerId) {
		super(id, keyPair, valuePair, parentContainerId);
	}

	/**
	 * @param id
	 * @param updatedOn
	 * @param createdOn
	 */
	public UserOutletContentKeyValuePairDO(Long id, Date updatedOn,
			Timestamp createdOn) {
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
	public UserOutletContentKeyValuePairDO(Long id, Date updatedOn,
			Timestamp createdOn, String keyPair, String valuePair,
			Long parentContainerId) {
		super(id, updatedOn, createdOn, keyPair, valuePair, parentContainerId);
	}

	public Long getUserOutletContentId() {
		return super.getParentContainerId();
	}
	
	/**
	 * @param serviceId
	 */
	public void setUserOutletContentId(Long userOutletContentId) {
		super.setParentContainerId(userOutletContentId);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserOutletContentKeyValuePairDO [getUserOutletContentId()=");
		builder.append(getUserOutletContentId());
		builder.append(", super.toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
