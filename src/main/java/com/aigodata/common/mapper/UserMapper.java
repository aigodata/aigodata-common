package com.aigodata.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.aigodata.common.common.MakeSql;
import com.aigodata.common.common.generic.mapper.BaseMapper;
import com.aigodata.common.domain.User;

@Repository
public interface UserMapper extends BaseMapper<User> {

	@SelectProvider(type = MakeSql.class, method = "sql")
	public Map selectOne(String sql);

	@SelectProvider(type = MakeSql.class, method = "sql")
	public List<Map> selectList(String sql);
}
