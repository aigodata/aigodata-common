package com.aigodata.common.vo;

import net.sf.json.JSONArray;

public class UserInfo {

	private Integer id;

	private String name;

	private String username;

	private Integer roleId;

	private Integer isSuper;

	private String role;

	private JSONArray groups;

	private String classId;
	private String className;
	private String orgId;
	private String orgName;
	private String collegesId;
	private String collegesName;

	public UserInfo() {
	}

	public UserInfo(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public UserInfo(Integer id, String name, String username, Integer roleId, String role, JSONArray groups,
			Integer isSuper, String classId, String className, String orgId, String orgName, String collegesId,
			String collegesName) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.roleId = roleId;
		this.isSuper = isSuper;
		this.role = role;
		this.groups = groups;
		this.classId = classId;
		this.className = className;
		this.orgId = orgId;
		this.orgName = orgName;
		this.collegesId = collegesId;
		this.collegesName = collegesName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public JSONArray getGroups() {
		return groups;
	}

	public void setGroups(JSONArray groups) {
		this.groups = groups;
	}

	public Integer getIsSuper() {
		return isSuper;
	}

	public void setIsSuper(Integer isSuper) {
		this.isSuper = isSuper;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCollegesId() {
		return collegesId;
	}

	public void setCollegesId(String collegesId) {
		this.collegesId = collegesId;
	}

	public String getCollegesName() {
		return collegesName;
	}

	public void setCollegesName(String collegesName) {
		this.collegesName = collegesName;
	}

}
