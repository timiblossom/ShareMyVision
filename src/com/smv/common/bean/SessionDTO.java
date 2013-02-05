package com.smv.common.bean;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Tri Nguyen
 * @author Minh Do
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SessionDTO")
public class SessionDTO implements Serializable {

	private String action;
	private String sessionGuid;
	private Long sessionUid;
	private String sessionClieptIp;
	private Date sessionLastLogin;
	private String sessionUserAgent;
	private Boolean sessionLoggedIn;
	private Long id;
	
	public String getAction() {
		return action;
	}	

	public void setAction(String action) {
		this.action = action;
	}

	public String getSessionGuid() {
		return sessionGuid;
	}

	public void setSessionGuid(String sessionGuid) {
		this.sessionGuid = sessionGuid;
	}

	public Long getSessionUid() {
		return sessionUid;
	}

	public void setSessionUid(Long sessionUid) {
		this.sessionUid = sessionUid;
	}

	public String getSessionClieptIp() {
		return sessionClieptIp;
	}

	public void setSessionClieptIp(String sessionClieptIp) {
		this.sessionClieptIp = sessionClieptIp;
	}

	public Date getSessionLastLogin() {
		return sessionLastLogin;
	}

	public void setSessionLastLogin(Date sessionLastLogin) {
		this.sessionLastLogin = sessionLastLogin;
	}

	public String getSessionUserAgent() {
		return sessionUserAgent;
	}

	public void setSessionUserAgent(String sessionUserAgent) {
		this.sessionUserAgent = sessionUserAgent;
	}

	public Boolean getSessionLoggedIn() {
		return sessionLoggedIn;
	}

	public void setSessionLoggedIn(Boolean sessionLoggedIn) {
		this.sessionLoggedIn = sessionLoggedIn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SessionDTO [action=");
		builder.append(action);
		builder.append(", id=");
		builder.append(id);
		builder.append(", sessionClieptIp=");
		builder.append(sessionClieptIp);
		builder.append(", sessionGuid=");
		builder.append(sessionGuid);
		builder.append(", sessionLastLogin=");
		builder.append(sessionLastLogin);
		builder.append(", sessionLoggedIn=");
		builder.append(sessionLoggedIn);
		builder.append(", sessionUid=");
		builder.append(sessionUid);
		builder.append(", sessionUserAgent=");
		builder.append(sessionUserAgent);
		builder.append("]");
		return builder.toString();
	}


}
