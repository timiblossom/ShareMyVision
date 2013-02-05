package com.smv.service.user.db.dbobject;

import com.smv.util.db.AbstractVersionedDatedDO;

public class RolePermissionDO extends AbstractVersionedDatedDO {

	private Long roleId;
	private Long permissionId;

	public RolePermissionDO () {super();}
	
	public RolePermissionDO (Long id, Long roleId, Long permissionId) {
		super(id);
		setRoleId(roleId);
		setPermissionId(permissionId);
	}
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	
	public String toString() {
		StringBuffer result = super.getString();

		result.append("roleId: " + this.getRoleId() + '\n');
		result.append("permissionId: " + this.getPermissionId() + '\n');
		
		return result.toString();
	}

}
