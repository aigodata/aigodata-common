package com.aigodata.common.common.shiro.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.aigodata.common.vo.UserInfo;
import com.google.common.collect.Iterables;

import net.sf.json.JSONArray;

public class SubjectUtil {

	/**
	 * 获取当前登录用户的ID
	 * 
	 * @return
	 */
	public static Integer getId() {
		Subject currentUser = SecurityUtils.getSubject();
//		Object principal = currentUser.getPrincipals().getPrimaryPrincipal();
//		Integer userId = Integer.parseInt(principal.toString());
		PrincipalCollection principals = currentUser.getPrincipals();
		if (principals == null) {
			return 0;
		}
		Collection<Integer> integers = principals.byType(Integer.class);
		Integer userId = Iterables.getFirst(integers, null);
		return userId == null ? 0 : userId;
	}

	public static Integer getRoleId() {
		Subject currentUser = SecurityUtils.getSubject();
		PrincipalCollection principals = currentUser.getPrincipals();
		if (principals == null) {
			return 0;
		}
		Collection<String> strings = principals.byType(String.class);
		String roleIdString = Iterables.get(strings, 2);
		Integer roleId = Integer.parseInt(roleIdString.split(":")[1]);
		return roleId == null ? 0 : roleId;
	}

	/**
	 * 获取当前登录用户的名字
	 * 
	 * @return
	 */
	public static String getName() {
		Subject currentUser = SecurityUtils.getSubject();
		Collection<String> strings = currentUser.getPrincipals().byType(String.class);
		String name = Iterables.getFirst(strings, null);
		return name;
	}

	public static UserInfo getUser() {
		Subject currentUser = SecurityUtils.getSubject();
		PrincipalCollection principals = currentUser.getPrincipals();
		if (principals == null) {
			return new UserInfo();
		}
		/*
		 * Integer类型, 包含用户ID
		 */
		Collection<Integer> integers = principals.byType(Integer.class);
		Integer userId = Iterables.getFirst(integers, null);
		/*
		 * String类型, 包含用户姓名, 用户名, 角色ID, 角色名
		 */
		Collection<String> strings = principals.byType(String.class);
		String name = Iterables.getFirst(strings, null);
		name = name.split(":")[1];
		String username = Iterables.get(strings, 1);
		username = username.split(":")[1];
		String roleIdString = Iterables.get(strings, 2);
		Integer roleId = Integer.parseInt(roleIdString.split(":")[1]);
		String role = Iterables.get(strings, 3);
		role = role.split(":")[1];

		/*
		 * List 类型，包含用户组，用户组对应的角色
		 */
		Collection<List> lists = principals.byType(List.class);
		JSONArray groups = new JSONArray();
		List<Map> groupUsers = Iterables.get(lists, 0);
		if (groupUsers != null) {
			groups = JSONArray.fromObject(groupUsers);
		}
		/*
		 * isSuper
		 */
		String isSuperStr = Iterables.get(strings, 4);
		Integer isSuper = Integer.parseInt(isSuperStr.split(":")[1]);

		/*
		 * classId
		 */
		String classIdStr = Iterables.get(strings, 5);
		String classId = classIdStr.split(":")[1];
		String classNameStr = Iterables.get(strings, 6);
		String className = classNameStr.split(":")[1];
		String orgIdStr = Iterables.get(strings, 7);
		String orgId = orgIdStr.split(":")[1];
		String orgNameStr = Iterables.get(strings, 8);
		String orgName = orgNameStr.split(":")[1];
		String collegesIdStr = Iterables.get(strings, 9);
		String collegesId = collegesIdStr.split(":")[1];
		String collegesNameStr = Iterables.get(strings, 10);
		String collegesName = collegesNameStr.split(":")[1];

		return new UserInfo(userId, name, username, roleId, role, groups, isSuper, classId, className, orgId, orgName,
				collegesId, collegesName);
	}

}
