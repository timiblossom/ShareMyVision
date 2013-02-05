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
public class SubjectTemplateDO extends AbstractVersionedDatedDO {

	String name;
	String description;
	String fileLocation;
	
	/**
	 * 
	 */
	public SubjectTemplateDO() {
		super();
	}

	/**
	 * @param id
	 */
	public SubjectTemplateDO(Long id) {
		super(id);
	}

	/**
	 * @param id
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 */
	public SubjectTemplateDO(	Long id, 
						Long versionId, 
						Date updatedOn,
						Timestamp createdOn) {
		super(id, versionId, updatedOn, createdOn);
	}

	/**
	 * @param name
	 * @param description
	 * @param subjectSubjectTemplateContent
	 * @param useBodySubjectTemplateFileLocation
	 * @param bodySubjectTemplateContent
	 * @param fileLocation
	 */
	public SubjectTemplateDO(	String name, 
						String description,
						String fileLocation) {
		super();
		setName(name);
		setDescription(description);
		setFileLocation(fileLocation);
	}

	/**
	 * @param id
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 */
	public SubjectTemplateDO(	Long id, 
						Long versionId, 
						Date updatedOn,
						Timestamp createdOn,
						String name, 
						String description,
						String subjectSubjectTemplateContent, 
						Boolean useBodySubjectTemplateFileLocation,
						String bodySubjectTemplateContent, 
						String fileLocation) {
		super(id, versionId, updatedOn, createdOn);
		setName(name);
		setDescription(description);
		setFileLocation(fileLocation);
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
	 * @return the fileLocation
	 */
	public String getFileLocation() {
		return fileLocation;
	}

	/**
	 * @param fileLocation the fileLocation to set
	 */
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SubjectTemplateDO [description=");
		builder.append(description);
		builder.append(", fileLocation=");
		builder.append(fileLocation);
		builder.append(", name=");
		builder.append(name);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
