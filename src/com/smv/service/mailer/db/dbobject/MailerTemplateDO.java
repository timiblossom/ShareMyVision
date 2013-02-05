package com.smv.service.mailer.db.dbobject;

import org.joda.time.DateTime;

import com.smv.util.db.AbstractVersionedDatedDO;



/**
 * @author Minh Do
 * 07/22/2010
 */
public class MailerTemplateDO extends AbstractVersionedDatedDO {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private DateTime dateModified;
	private String bodyTemplate;
	private String subjectTemplate;
	
	@Override
	public Long getId() {
		return id;
	}	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DateTime getDateModified() {
		return dateModified;
	}


	public void setDateModified(DateTime dateModified) {
		this.dateModified = dateModified;
	}


	public String getBodyTemplate() {
		return bodyTemplate;
	}


	public void setBodyTemplate(String template) {
		this.bodyTemplate = template;
	}

	public String getSubjectTemplate() {
		return subjectTemplate;
	}

	public void setSubjectTemplate(String subject) {
		this.subjectTemplate = subject;
	}
	
	
	

}
