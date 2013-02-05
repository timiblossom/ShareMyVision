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
public class SubjectTemplateKeyValuePairDO extends AbstractVersionedDatedDO {

	String name;
	String description;
	Long templateId;
	String keyPair;
	String defaultValuePair;
	
	/**
	 * 
	 */
	public SubjectTemplateKeyValuePairDO() {
		super();
	}

	/**
	 * @param id
	 */
	public SubjectTemplateKeyValuePairDO(Long id) {
		super(id);
	}

	/**
	 * @param id
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 */
	public SubjectTemplateKeyValuePairDO(Long id, 
			Long versionId, 
			Date updatedOn,
			Timestamp createdOn) {
		super(id, versionId, updatedOn, createdOn);
	}

	/**
	 * @param id
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 * @param name
	 * @param description
	 * @param templateId
	 * @param keyPair
	 * @param defaultValuePair
	 */
	public SubjectTemplateKeyValuePairDO(Long id, 
			Long versionId, 
			Date updatedOn,
			Timestamp createdOn, 
			String name, 
			String description,
			Long templateId, 
			String keyPair, 
			String defaultValuePair) {
		super(id, versionId, updatedOn, createdOn);
		setName(name);
		setDescription(description);
		setSubjectTemplateId(templateId);
		setKeyPair(keyPair);
		setDefaultValuePair(defaultValuePair);
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the templateId
	 */
	public Long getSubjectTemplateId() {
		return templateId;
	}

	/**
	 * @param templateId the templateId to set
	 */
	public void setSubjectTemplateId(Long templateId) {
		this.templateId = templateId;
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
	 * @return the defaultValuePair
	 */
	public String getDefaultValuePair() {
		return defaultValuePair;
	}

	/**
	 * @param defaultValuePair the defaultValuePair to set
	 */
	public void setDefaultValuePair(String defaultValuePair) {
		this.defaultValuePair = defaultValuePair;
	}

}
