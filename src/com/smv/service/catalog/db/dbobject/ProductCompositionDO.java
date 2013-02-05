/**
 * 
 */
package com.smv.service.catalog.db.dbobject;

import com.smv.util.db.AbstractVersionedDatedDO;

/**
 * @author TriNguyen
 *
 */
public class ProductCompositionDO extends AbstractVersionedDatedDO {

	private Long parentProductId;
	private Long childProductId;

	public ProductCompositionDO() {
		super();
	}
	/**
	 * @return the parentProductId
	 */
	public Long getParentProductId() {
		return parentProductId;
	}
	/**
	 * @param parentProductId the parentProductId to set
	 */
	public void setParentProductId(Long parentProductId) {
		this.parentProductId = parentProductId;
	}
	/**
	 * @return the childProductId
	 */
	public Long getChildProductId() {
		return childProductId;
	}
	/**
	 * @param childProductId the childProductId to set
	 */
	public void setChildProductId(Long childProductId) {
		this.childProductId = childProductId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductCompositionDO [childProductId=");
		builder.append(childProductId);
		builder.append(", parentProductId=");
		builder.append(parentProductId);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
