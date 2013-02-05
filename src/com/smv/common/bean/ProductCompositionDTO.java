/**
 * 
 */
package com.smv.common.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Tri Nguyen
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductCompositionDTO")
public class ProductCompositionDTO implements Serializable {

	private Long parentProductId;
	private Long childProductId;
	private Long id;
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
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductCompositionDTO [childProductId=");
		builder.append(childProductId);
		builder.append(", parentProductId=");
		builder.append(parentProductId);
		builder.append("]");
		return builder.toString();
	}

}
