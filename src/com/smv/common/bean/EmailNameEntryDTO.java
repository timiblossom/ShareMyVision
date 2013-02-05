package com.smv.common.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmailNameEntryDTO")
public class EmailNameEntryDTO {
    //Map keys cannot be null
	@XmlElement(name = "email", required = true)
    private String email;
	@XmlElement(name = "name", required = true)
    private String name;

    public void setEmail(String u) {
        email = u;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String k) {
        name = k;
    }

    public String getName() {
        return name;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmailNameEntryDTO [email=");
		builder.append(email);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

}