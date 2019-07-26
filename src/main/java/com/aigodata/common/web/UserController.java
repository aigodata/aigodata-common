package com.aigodata.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.aigodata.common.domain.User;
import com.aigodata.common.model.ResultModel;
import com.aigodata.common.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@PostMapping
	public ResultModel add(@RequestBody User user) {
		userService.add(user);
		return ResultModel.success(user);
	}

	@PutMapping("/{id}")
	public ResultModel update(@PathVariable Integer id, @RequestBody User user) {
		user.setId(id);
		int count = userService.update(user);
		return ResultModel.success(Maps.immutableEntry("count", count));
	}
}
