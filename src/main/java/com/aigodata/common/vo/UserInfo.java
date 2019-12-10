package com.aigodata.common.vo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserInfo {

	private Integer id;

	private String name;

	private String username;

	private Integer roleId;

	private Integer isSuper;

	private String role;

	private JSONArray groups;

	private JSONObject extendJson;

	public UserInfo() {
	}

	public UserInfo(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public UserInfo(Integer id, String name, String username, Integer roleId, String role, JSONArray groups,
			Integer isSuper, JSONObject json) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.roleId = roleId;
		this.isSuper = isSuper;
		this.role = role;
		this.groups = groups;
		this.extendJson = json;
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

	public JSONObject getExtendJson() {
		return extendJson;
	}

	public void setExtendJson(JSONObject extendJson) {
		this.extendJson = extendJson;
	}

}
