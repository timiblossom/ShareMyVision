package com.smv.common.bean;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MailerDataDTO")
public class MailerDataDTO {
	private static final long serialVersionUID = 12345678900L;
	
	private String mailerEventCode;
	private String fromEmail;
	private String fromName;
	private EmailNameListDTO to;
	private KeyValueMapDTO subjectData;
	private KeyValueMapDTO bodyData;
	
	public String getMailerEventCode() {
		return mailerEventCode;
	}

	public void setMailerEventCode(String mailerEventCode) {
		this.mailerEventCode = mailerEventCode;
	}

	public String getFromEmail() {
		return fromEmail;
	}
	
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	
	public String getFromName() {
		return fromName;
	}
	
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	
	public EmailNameListDTO getTo() {
		return to;
	}
	
	public void setTo(EmailNameListDTO to) {
		this.to = to;
	}
	
	public KeyValueMapDTO getSubjectData() {
		return subjectData;
	}

	public void setSubjectData(KeyValueMapDTO subjectData) {
		this.subjectData = subjectData;
	}

	public KeyValueMapDTO getBodyData() {
		return bodyData;
	}

	public void setBodyData(KeyValueMapDTO bodyData) {
		this.bodyData = bodyData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
