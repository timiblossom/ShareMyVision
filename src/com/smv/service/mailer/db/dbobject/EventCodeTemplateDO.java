/**
 * 
 */
package com.smv.service.mailer.db.dbobject;

import java.sql.Timestamp;
import java.util.Date;

import com.smv.util.db.AbstractVersionedDatedDO;

/**
 * @author TriNguyen
 *
 */
public class EventCodeTemplateDO extends AbstractVersionedDatedDO {

	String name;
	Long eventCodeId;
	Long subjectTemplateId;
	Long bodyTemplateId;
	
	/**
	 * 
	 */
	public EventCodeTemplateDO() {
		super();
	}

	/**
	 * @param id
	 */
	public EventCodeTemplateDO(Long id) {
		super(id);
	}

	/**
	 * @param id
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 */
	public EventCodeTemplateDO(Long id, 
			Long versionId, 
			Date updatedOn,
			Timestamp createdOn) {
		super(id, versionId, updatedOn, createdOn);
	}

	/**
	 * @param name
	 * @param eventCodeId
	 * @param templateId
	 */
	public EventCodeTemplateDO(String name, 
			Long eventCodeId, 
			Long subjectTemplateId,
			Long bodyTemplateId) {
		super();
		setName(name);
		setEventCodeId(eventCodeId);
		setSubjectTemplateId(subjectTemplateId);
		setBodyTemplateId(bodyTemplateId);
	}

	/**
	 * @param id
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 * @param name
	 * @param eventCodeId
	 * @param templateId
	 */
	public EventCodeTemplateDO(Long id, 
			Long versionId, 
			Date updatedOn,
			Timestamp createdOn, 
			String name, 
			Long eventCodeId, 
			Long subjectTemplateId,
			Long bodyTemplateId) {
		super(id, versionId, updatedOn, createdOn);
		setName(name);
		setEventCodeId(eventCodeId);
		setSubjectTemplateId(subjectTemplateId);
		setBodyTemplateId(bodyTemplateId);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the eventCodeId
	 */
	public Long getEventCodeId() {
		return eventCodeId;
	}

	/**
	 * @param eventCodeId the eventCodeId to set
	 */
	public void setEventCodeId(Long eventCodeId) {
		this.eventCodeId = eventCodeId;
	}

	/**
	 * @return the subjectTemplateId
	 */
	public Long getSubjectTemplateId() {
		return subjectTemplateId;
	}

	/**
	 * @param subjectTemplateId the subjectTemplateId to set
	 */
	public void setSubjectTemplateId(Long subjectTemplateId) {
		this.subjectTemplateId = subjectTemplateId;
	}

	/**
	 * @return the bodyTemplateId
	 */
	public Long getBodyTemplateId() {
		return bodyTemplateId;
	}

	/**
	 * @param bodyTemplateId the bodyTemplateId to set
	 */
	public void setBodyTemplateId(Long bodyTemplateId) {
		this.bodyTemplateId = bodyTemplateId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EventCodeTemplateDO [bodyTemplateId=");
		builder.append(bodyTemplateId);
		builder.append(", eventCodeId=");
		builder.append(eventCodeId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", subjectTemplateId=");
		builder.append(subjectTemplateId);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
