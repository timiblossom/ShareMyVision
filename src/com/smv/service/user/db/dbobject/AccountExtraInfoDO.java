package com.smv.service.user.db.dbobject;

import com.smv.util.db.AbstractVersionedDatedDO;

public class AccountExtraInfoDO extends AbstractVersionedDatedDO {

	protected String keyPair;
	protected String valuePair;

	public String getKeyPair() {
		return keyPair;
	}
	public void setKeyPair(String keyPair) {
		this.keyPair = keyPair;
	}
	public String getValuePair() {
		return valuePair;
	}
	public void setValuePair(String valuePair) {
		this.valuePair = valuePair;
	}
	
	public String toString() {
		StringBuffer result = super.getString();

		result.append("keyPair: " + this.getKeyPair() + '\n');
		result.append("valuePair: " + this.getValuePair() + '\n');
		
		return result.toString();
	}

}
