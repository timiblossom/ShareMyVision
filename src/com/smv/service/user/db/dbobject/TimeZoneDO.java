package com.smv.service.user.db.dbobject;

import com.smv.util.db.AbstractVersionedDatedDO;

public class TimeZoneDO extends AbstractVersionedDatedDO {

	private String timeZone;
	private String label;

	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String toString() {
		StringBuffer result = super.getString();

		result.append("timeZone: " + this.getTimeZone() + '\n');
		result.append("label: " + this.getLabel() + '\n');
		
		return result.toString();
	}

}
