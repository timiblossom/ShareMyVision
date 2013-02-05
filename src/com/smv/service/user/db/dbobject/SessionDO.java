package com.smv.service.user.db.dbobject;

import java.util.Date;

import com.smv.util.db.AbstractVersionedDatedDO;

public class SessionDO extends AbstractVersionedDatedDO {

	private String guid;
	private Long uid;
	private String clientIp;
	private Date lastLogin;
	private String userAgent;
	private Boolean loggedIn;

	public SessionDO() {super();}
	
	public SessionDO(Long id, String guid, Long uid, String clientIp, Date lastLogin, String userAgent, Boolean loggedIn) {
		super(id);
		setGuid(guid);
		setUid(uid);
		setClientIp(clientIp);
		setLastLogin(lastLogin);
		setUserAgent(userAgent);
		setLoggedIn(loggedIn);
	}
	
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public Boolean getLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String toString() {
		StringBuffer result = super.getString();

		result.append("guid: " + getGuid() + '\n');
		result.append("uid: " + getUid() + '\n');
		result.append("clientIp: " + getClientIp() + '\n');
		result.append("lastLogin: " + getLastLogin() + '\n');
		result.append("userAgent: " + getUserAgent() + '\n');
		result.append("loggedIn: " + getLoggedIn() + '\n');
		
		return result.toString();
	}
}
