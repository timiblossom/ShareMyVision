
package com.smv.common.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * @author Tri Nguyen
 * @author Minh Do
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserDTO")
public class UserDTO implements Serializable {
	
	private String action;
	private String userDisplayName;
	private String userEmail;
	private String userPassword;
	private String userSalt;
	private String userStatus;
	private String userType;
	private String userActivationCode;
	private String userPasswordResetUserCode;
	private String userLanguage;
	private String sessionClientIp;
	private String sessionUserAgent;
	private AccountDTO account;
	private ContactDTO contact;
	private UserExtraInfoDTO userExtraInfo;
	private String roleName;
	private String roleDescription;
	private String userAgent;
	private SessionDTO session;
	private Long userId;
	
	public String getAction() {
		return action;
	}	

	public void setAction(String action) {
		this.action = action;
	}

	public String getUserDisplayName() {
		return userDisplayName;
	}

	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserSalt() {
		return userSalt;
	}

	public void setUserSalt(String userSalt) {
		this.userSalt = userSalt;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserActivationCode() {
		return userActivationCode;
	}

	public void setUserActivationCode(String userActivationCode) {
		this.userActivationCode = userActivationCode;
	}

	public String getUserPasswordResetUserCode() {
		return userPasswordResetUserCode;
	}

	public void setUserPasswordResetUserCode(String userPasswordResetUserCode) {
		this.userPasswordResetUserCode = userPasswordResetUserCode;
	}

	public String getUserLanguage() {
		return userLanguage;
	}

	public void setUserLanguage(String userLanguage) {
		this.userLanguage = userLanguage;
	}

	public String getSessionClientIp() {
		return sessionClientIp;
	}

	public void setSessionClientIp(String sessionClientIp) {
		this.sessionClientIp = sessionClientIp;
	}

	public ContactDTO getContact() {
		return contact;
	}

	public void setContact(ContactDTO contact) {
		this.contact = contact;
	}

	public UserExtraInfoDTO getUserExtraInfo() {
		return userExtraInfo;
	}

	public void setUserExtraInfo(UserExtraInfoDTO userExtraInfo) {
		this.userExtraInfo = userExtraInfo;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public AccountDTO getAccount() {
		return account;
	}

	public void setAccount(AccountDTO account) {
		this.account = account;
	}

	public void setSession(SessionDTO session) {
		this.session = session;
	}

	public SessionDTO getSession() {
		return session;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserDTO [action=");
		builder.append(action);
		builder.append(", userDisplayName=");
		builder.append(userDisplayName);
		builder.append(", userEmail=");
		builder.append(userEmail);
		builder.append(", userPassword=");
		builder.append(userPassword);
		builder.append(", userSalt=");
		builder.append(userSalt);
		builder.append(", userStatus=");
		builder.append(userStatus);
		builder.append(", userType=");
		builder.append(userType);
		builder.append(", userActivationCode=");
		builder.append(userActivationCode);
		builder.append(", userPasswordResetUserCode=");
		builder.append(userPasswordResetUserCode);
		builder.append(", userLanguage=");
		builder.append(userLanguage);
		builder.append(", sessionClientIp=");
		builder.append(sessionClientIp);
		builder.append(", sessionUserAgent=");
		builder.append(sessionUserAgent);
		builder.append(", account=");
		builder.append(account);
		builder.append(", contact=");
		builder.append(contact);
		builder.append(", userExtraInfo=");
		builder.append(userExtraInfo);
		builder.append(", roleName=");
		builder.append(roleName);
		builder.append(", roleDescription=");
		builder.append(roleDescription);
		builder.append(", userAgent=");
		builder.append(userAgent);
		builder.append(", session=");
		builder.append(session);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
}
