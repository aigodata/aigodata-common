package com.aigodata.common.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aigodata.common.model.ResultModel;

@RequestMapping("/session")
@RestController
public class SessionTimeoutAction {

	@RequestMapping("/check")
	public ResultModel check() {
		return ResultModel.success();
	}
}
