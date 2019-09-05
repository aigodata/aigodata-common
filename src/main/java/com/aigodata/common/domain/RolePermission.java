package com.aigodata.common.domain;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_role_permission")
public class RolePermission extends Base {

	private static final long serialVersionUID = 1L;

	/*
	 * 数据id
	 */
	@Id
	private Integer roleId;

	/*
	 * 权限ID
	 */
	@Id
	private Integer permissionId;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

}
