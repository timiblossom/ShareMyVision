/**
 * 
 */
package com.smv.service.catalog.db.dbobject;

import com.smv.util.db.AbstractVersionedDatedDO;

/**
 * @author TriNguyen
 *
 */
public class ServiceCompositionDO extends AbstractVersionedDatedDO {

	private Long parentServiceId;
	private Long childServiceId;

	public ServiceCompositionDO() {
		super();
	}
	/**
	 * @return the parentServiceId
	 */
	public Long getParentServiceId() {
		return parentServiceId;
	}
	/**
	 * @param parentServiceId the parentServiceId to set
	 */
	public void setParentServiceId(Long parentServiceId) {
		this.parentServiceId = parentServiceId;
	}
	/**
	 * @return the childServiceId
	 */
	public Long getChildServiceId() {
		return childServiceId;
	}
	/**
	 * @param childServiceId the childServiceId to set
	 */
	public void setChildServiceId(Long childServiceId) {
		this.childServiceId = childServiceId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer builder = super.getString();
		builder.append("ServiceCompositionDO [childServiceId=");
		builder.append(childServiceId);
		builder.append(", parentServiceId=");
		builder.append(parentServiceId);
		builder.append("]");
		return builder.toString();
	}

}
