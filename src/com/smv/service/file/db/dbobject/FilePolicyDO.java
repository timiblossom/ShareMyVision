package com.smv.service.file.db.dbobject;

import com.smv.util.db.AbstractVersionedDatedDO;

public class FilePolicyDO extends AbstractVersionedDatedDO {
	private Long policyId;
	private String name;
	private String value;
	
	public Long getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}	
	
}
