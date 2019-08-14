package com.aigodata.common.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/***
 * 2019-08-14 09:53:24.0
 * 
 * @Author Saps.Weaver
 * 
 * @Deprecated 用户组、用户关联表
 */

@Table(name = "sys_group_user")
public class GroupUser extends Base {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	private Long id; // id
	@Column(name = "user_id")
	private Long userId; // 用户id
	@Column(name = "group_id")
	private Long groupId; // 用户组id

	public Long getId() {
		return id;
	}

	public void setId(Long $id) {
		this.id = $id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long $userId) {
		this.userId = $userId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long $groupId) {
		this.groupId = $groupId;
	}
}
