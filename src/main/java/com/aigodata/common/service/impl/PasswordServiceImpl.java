package com.aigodata.common.service.impl;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aigodata.common.common.exception.GlobalException;
import com.aigodata.common.common.shiro.realm.UserAuthRealm;
import com.aigodata.common.common.shiro.util.SubjectUtil;
import com.aigodata.common.domain.User;
import com.aigodata.common.mapper.UserMapper;
import com.aigodata.common.model.ResultModel.ResultStatus;
import com.aigodata.common.service.PasswordService;

@Service
public class PasswordServiceImpl implements PasswordService {

	@Autowired
	private UserAuthRealm realm;

	@Autowired
	private UserMapper userMapper;

	@Transactional
	public void modify(String oldPassword, String newPassword) {
		User user = userMapper.selectByPrimaryKey(SubjectUtil.getId());
		// 密码加密信息
		HashedCredentialsMatcher matcher = (HashedCredentialsMatcher) realm.getCredentialsMatcher();
		// 判断原密码是否正确
		String encryptedOldPassword = new SimpleHash(matcher.getHashAlgorithmName(), oldPassword, user.getSalt(),
				matcher.getHashIterations()).toString();
		if (!encryptedOldPassword.equals(user.getPassword())) {
			throw new GlobalException(ResultStatus.OLD_PASSWORD_ERROR);
		}
		// 新密码
		String encryptedNewPassword = new SimpleHash(matcher.getHashAlgorithmName(), newPassword, user.getSalt(),
				matcher.getHashIterations()).toString();
		user.setPassword(encryptedNewPassword);
		userMapper.updateByPrimaryKey(user);
	}

	@Override
	public void reset(String newPassword) {
		User user = userMapper.selectByPrimaryKey(SubjectUtil.getId());
		// 密码加密信息
		HashedCredentialsMatcher matcher = (HashedCredentialsMatcher) realm.getCredentialsMatcher();
		// 新密码
		String encryptedNewPassword = new SimpleHash(matcher.getHashAlgorithmName(), newPassword, user.getSalt(),
				matcher.getHashIterations()).toString();
		user.setPassword(encryptedNewPassword);
		userMapper.updateByPrimaryKey(user);
	}
}
