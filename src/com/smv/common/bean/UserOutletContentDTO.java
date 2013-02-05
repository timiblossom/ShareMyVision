package com.smv.common.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Tri Nguyen
 * 12/2010
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserOutletContentDTO")
public class UserOutletContentDTO implements Serializable {

	// Contains data such url, description of smv's image, etc ... in a Key-Value Map
	// E.g. Key = "url", Value = "http://www.sharemyvision.com/"
	public KeyValueMapDTO data;

	/**
	 * @return the data
	 */
	public KeyValueMapDTO getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(KeyValueMapDTO data) {
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserOutletContentDTO [data=");
		builder.append(data);
		builder.append(", super.toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	} 

}
