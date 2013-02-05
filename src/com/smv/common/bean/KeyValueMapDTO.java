package com.smv.common.bean;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "KeyValueMapDTO")
public class KeyValueMapDTO {

    @XmlElement(name = "entry", required = true)
    List<KeyValueEntryDTO> entries = new ArrayList<KeyValueEntryDTO>();

    public List<KeyValueEntryDTO> getEntries() {
        return entries;
    }

	public void setEntries(List<KeyValueEntryDTO> entries) {
		this.entries = entries;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("KeyValueMapDTO [entries=");
		builder.append(entriesToString());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

	protected String entriesToString() {
		if ((entries != null) && (!entries.isEmpty()) && (entries.size() > 0)) {
			StringBuilder builder = new StringBuilder();
			for (int index = 0; index < entries.size(); index++) {
				builder.append("entry[");
				builder.append(index);
				builder.append("]=");
				builder.append(entries.get(index));
				builder.append(",");
			}
			return builder.toString();
		}
		return null;
	}
	
}