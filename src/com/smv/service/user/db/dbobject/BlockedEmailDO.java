package com.smv.service.user.db.dbobject;

import com.smv.util.db.AbstractVersionedDatedDO;

public class BlockedEmailDO extends AbstractVersionedDatedDO {

	private String emailAddress;

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String toString() {
		StringBuffer result = super.getString();

		result.append("emailAddress: " + this.getEmailAddress() + '\n');
		
		return result.toString();
	}

}
