package com.aigodata.common.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/***
 * 2019-08-14 09:51:41.0
 * 
 * @Author Saps.Weaver
 * 
 * @Deprecated 用户组表
 */

@Table(name = "sys_group")
public class Group extends Base {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	private Integer id; // id
	@Column(name = "name")
	private String name; // 用户组名称
	@Column(name = "description")
	private String description; // 描述
	@Column(name = "parent_id")
	private Long parentId; // 父节点id

	public Integer getId() {
		return id;
	}

	public void setId(Integer $id) {
		this.id = $id;
	}

	public String getName() {
		return name;
	}

	public void setName(String $name) {
		this.name = $name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String $description) {
		this.description = $description;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long $parentId) {
		this.parentId = $parentId;
	}
}
