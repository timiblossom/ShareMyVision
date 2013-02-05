/**
 * 
 */
package com.smv.common.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author TriNguyen
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KeyValuePairDTO")
public abstract class KeyValuePairDTO implements Serializable {

	private Long id;
	private String keyPair;
	private String valuePair;
	private Long containerParentId;

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
	 * @return the containerParentId
	 */
	public Long getContainerParentId() {
		return containerParentId;
	}
	/**
	 * @param containerParentId the containerParentId to set
	 */
	public void setContainerParentId(Long containerParentId) {
		this.containerParentId = containerParentId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("KeyValuePairDTO [containerParentId=");
		builder.append(containerParentId);
		builder.append(", id=");
		builder.append(id);
		builder.append(", keyPair=");
		builder.append(keyPair);
		builder.append(", valuePair=");
		builder.append(valuePair);
		builder.append("]");
		return builder.toString();
	}

}
