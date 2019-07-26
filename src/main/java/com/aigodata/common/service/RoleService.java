package com.aigodata.common.service;

import com.aigodata.common.domain.Role;

public interface RoleService {

	public Role add(Role role, Integer[] permissionIds);

	public int update(Role role, Integer[] permissionIds);

}
