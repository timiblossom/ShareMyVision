package com.smv.service.user.db.dbobject;

import com.smv.util.db.AbstractVersionedDatedDO;

public class PermissionDO extends AbstractVersionedDatedDO {

	private String name;
	private String description;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString() {
		StringBuffer result = super.getString();

		result.append("name: " + this.getName() + '\n');
		result.append("description: " + this.getDescription() + '\n');
		
		return result.toString();
	}

}
