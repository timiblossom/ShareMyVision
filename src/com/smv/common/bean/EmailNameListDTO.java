package com.smv.common.bean;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmailNameListDTO")
public class EmailNameListDTO {

    @XmlElement(name = "entry", required = true)
    List<EmailNameEntryDTO> entries = new ArrayList<EmailNameEntryDTO>();

    public List<EmailNameEntryDTO> getEntries() {
        return entries;
    }

	public void setEntries(List<EmailNameEntryDTO> entries) {
		this.entries = entries;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmailNameListDTO [entries=");
		builder.append(entriesToString());
		builder.append("]");
		return builder.toString();
	}
	
}