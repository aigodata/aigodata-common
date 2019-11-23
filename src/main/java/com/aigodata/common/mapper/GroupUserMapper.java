package com.aigodata.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.aigodata.common.common.generic.mapper.BaseMapper;
import com.aigodata.common.domain.GroupUser;

@Repository
public interface GroupUserMapper extends BaseMapper<GroupUser> {
	@Select("select a.group_id as groupId,b.role_id as roleId from sys_group_user a  left join sys_group_role b on a.group_id = b.group_id where a.user_id = #{userId}")
	List<Map> getUserGroup(@Param("userId") Integer userId);
}
