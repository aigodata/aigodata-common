package com.aigodata.common.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/***
 * 2019-08-14 09:53:10.0
 * 
 * @Author Saps.Weaver
 * 
 * @Deprecated 用户组、角色关联表
 */

@Table(name = "sys_group_role")
public class GroupRole extends Base {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	private Integer id; // id
	@Column(name = "role_id")
	private Long roleId; // 角色id
	@Column(name = "group_id")
	private Long groupId; // 用户组id

	public Integer getId() {
		return id;
	}

	public void setId(Integer $id) {
		this.id = $id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long $roleId) {
		this.roleId = $roleId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long $groupId) {
		this.groupId = $groupId;
	}
}
