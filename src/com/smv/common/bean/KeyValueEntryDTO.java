package com.smv.common.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KeyValueEntryDTO")
public class KeyValueEntryDTO {
	
	//Map keys cannot be null
	@XmlElement(name = "key", required = true)
	private String key;
	@XmlElement(name = "value", required = true)
	private String value;

	public KeyValueEntryDTO() {}

	public KeyValueEntryDTO(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public void setValue(String k) {
		value = k;
	}
	public String getValue() {
		return value;
	}

	public void setKey(String u) {
		key = u;
	}
	public String getKey() {
		return key;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("KeyValueEntryDTO [key=");
		builder.append(key);
		builder.append(", value=");
		builder.append(value);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
}

