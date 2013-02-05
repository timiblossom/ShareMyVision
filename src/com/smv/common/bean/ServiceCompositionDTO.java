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
@XmlType(name = "ServiceCompositionDTO")
public class ServiceCompositionDTO implements Serializable {

	private Long parentServiceId;
	private Long childServiceId;
	private Long id;
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
		builder.append("ServiceCompositionDTO [childServiceId=");
		builder.append(childServiceId);
		builder.append(", parentServiceId=");
		builder.append(parentServiceId);
		builder.append("]");
		return builder.toString();
	}

}
