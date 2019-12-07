package com.aigodata.common.common.shiro.realm;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.aigodata.common.common.constant.UserStatus;
import com.aigodata.common.common.util.ReadWriteUtil;
import com.aigodata.common.common.util.StringUtil;
import com.aigodata.common.domain.Permission;
import com.aigodata.common.domain.Role;
import com.aigodata.common.domain.User;
import com.aigodata.common.mapper.GroupUserMapper;
import com.aigodata.common.mapper.PermissionMapper;
import com.aigodata.common.mapper.RoleMapper;
import com.aigodata.common.mapper.UserMapper;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;

/**
 * 用户认证和授权
 * 
 * @author mengxiangyun
 *
 */
public class UserAuthRealm extends AuthorizingRealm {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private PermissionMapper permissionMapper;
	@Autowired
	private GroupUserMapper groupUserMapper;

	/**
	 * 权限验证
	 */
	@Override
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		Object principal = principals.getPrimaryPrincipal();
//		Integer id = Integer.parseInt(principal.toString());
		Collection<Integer> integers = principals.byType(Integer.class);
		Integer id = Iterables.getFirst(integers, null);

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 从数据库获取用户角色权限信息
		List<String> roleNames = roleMapper.selectNamesByUserId(id);
		List<Permission> permissions = permissionMapper.selectByUserId(id);
		List<String> permissionNames = permissions.stream().map(e -> e.getModule() + ":" + e.getAction())
				.collect(Collectors.toList());
		info.addRoles(roleNames);
		info.addStringPermissions(permissionNames);
		return info;
	}

	/**
	 * 登录验证
	 */
	@Override
	public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upsToken = (UsernamePasswordToken) token;
		String username = upsToken.getUsername();
		if (Strings.isNullOrEmpty(username)) {
			return null;
		}

		User query = new User();
		query.setUsername(username);
		List<User> users = userMapper.select(query);
		if (users.isEmpty()) {
			return null;
		}
		Map studentInfo = userMapper.findOne("select * from zg_b_suser where user_code = '" + username + "'");
		Map teachInfo = userMapper.findOne("select * from zg_b_tuser where user_code = '" + username + "'");
		String classId = "";
		String className = "";
		String orgId = "";
		String orgName = "";
		String collegesId = "";
		String collegesName = "";
		if (studentInfo != null) {
			classId = StringUtil.ifNull(studentInfo.get("p_id"));
			className = StringUtil.ifNull(studentInfo.get("user_classname"));
			if (StringUtil.isNotNull(classId)) {
				Map collegesInfo = userMapper
						.findOne("select * from zg_b_zzjg where id = (select parent_tg_id from zg_b_zzjg where id = "
								+ classId + ")");
				if (collegesInfo != null) {
					collegesId = StringUtil.ifNull(collegesInfo.get("id"));
					collegesName = StringUtil.ifNull(collegesInfo.get("group_name"));
				}
			}
		} else if (teachInfo != null) {
			orgId = StringUtil.ifNull(teachInfo.get("p_id"));
			orgName = StringUtil.ifNull(teachInfo.get("p_name"));
		}
		User user = users.get(0);
		// 添加用户组
		List<Map> groupUsers = groupUserMapper.getUserGroup(user.getId());
		// 添加角色信息
		List<Role> roles = roleMapper.selectByUserId(user.getId());
		// 只考虑第一个角色
		Integer roleId = roles.isEmpty() ? 0 : roles.get(0).getId();
		String roleName = roles.isEmpty() ? null : roles.get(0).getName();
		if (user.getStatus() == UserStatus.LOCKED.value()) {
			throw new LockedAccountException("用户 [" + username + "] 已锁定");
		}
		Integer isSuper = user.getIsSuper() == null ? 0 : user.getIsSuper();
		// 用户身份信息
		SimplePrincipalCollection principalCollection = new SimplePrincipalCollection();
		principalCollection.add(user, this.getName());
		principalCollection.add(user.getId(), getName());
		principalCollection.add("name:" + user.getName(), getName());
		principalCollection.add("username:" + username, getName());
		principalCollection.add("roleId:" + roleId, getName());
		// 属性增加 前缀, 避免与其他属性合并(Shiro的问题, 如果属性值一样会合并)
		principalCollection.add("role:" + roleName, getName());
		principalCollection.add(groupUsers, getName());
		principalCollection.add("isSuper:" + isSuper, getName());

		principalCollection.add("classId:" + classId, getName());
		principalCollection.add("className:" + className, getName());
		principalCollection.add("orgId:" + orgId, getName());
		principalCollection.add("orgName:" + orgName, getName());
		principalCollection.add("collegesId:" + collegesId, getName());
		principalCollection.add("collegesName:" + collegesName, getName());
		// 盐值
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());

		// 盐值加密认证信息
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principalCollection, user.getPassword(),
				credentialsSalt);
		return info;
	}

}
