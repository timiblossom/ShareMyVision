package com.smv.service.user.db.dbobject;

import com.smv.util.db.AbstractVersionedDatedDO;

public class UserDO extends AbstractVersionedDatedDO {

	private String displayName;
	private String email;
	private String password;
	private String salt;
	private Long statusId;
	private Long typeId;
	private String activationCode;
	private String passwordResetCode;
	private Long accountId; 
	private Long contactId; 
	private Long userExtraInfoId; 
	private Long roleId;
	private String language;

	public UserDO() {super();}
	
	public UserDO ( Long id,
					String displayName,
					String email,
					String password,
					String salt,
					Long statusId,
					String activationCode,
					String passwordResetCode,
					Long accountId, 
					Long contactId, 
					Long userExtraInfoId, 
					Long roleId,
					String language
			)
	{
		super(id);
		setDisplayName(displayName);
		setEmail(email);
		setPassword(password);
		setSalt(salt);
		setStatusId(statusId);
		setActivationCode(activationCode);
		setPasswordResetCode(passwordResetCode);
		setAccountId(accountId);
		setContactId(contactId);
		setUserExtraInfoId(userExtraInfoId);
		setRoleId(roleId);
		setLanguage(language);
	}
	
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * @return the status_id
	 */
	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	/**
	 * @return the typeId
	 */
	public Long getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId the typeId to set
	 */
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public String getPasswordResetCode() {
		return passwordResetCode;
	}

	public void setPasswordResetCode(String passwordResetCode) {
		this.passwordResetCode = passwordResetCode;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public long getContactIdAsLong() {
		return contactId.longValue();
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public Long getUserExtraInfoId() {
		return userExtraInfoId;
	}

	public void setUserExtraInfoId(Long userExtraInfoId) {
		this.userExtraInfoId = userExtraInfoId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserDO [accountId=");
		builder.append(accountId);
		builder.append(", activationCode=");
		builder.append(activationCode);
		builder.append(", contactId=");
		builder.append(contactId);
		builder.append(", displayName=");
		builder.append(displayName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", language=");
		builder.append(language);
		builder.append(", password=");
		builder.append(password);
		builder.append(", passwordResetCode=");
		builder.append(passwordResetCode);
		builder.append(", roleId=");
		builder.append(roleId);
		builder.append(", salt=");
		builder.append(salt);
		builder.append(", statusId=");
		builder.append(statusId);
		builder.append(", typeId=");
		builder.append(typeId);
		builder.append(", userExtraInfoId=");
		builder.append(userExtraInfoId);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
