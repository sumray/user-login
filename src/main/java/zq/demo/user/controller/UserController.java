package zq.demo.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import zq.demo.common.CommonCache;
import zq.demo.common.ResultMessage;
import zq.demo.common.UserException;
import zq.demo.common.VerifyCodeUtils;
import zq.demo.user.po.User;
import zq.demo.user.po.UserRegRequest;
import zq.demo.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;

	@Autowired
	private CommonCache commonCache;
	
	@PostMapping("/reg")
	public ResultMessage reg(@RequestBody UserRegRequest request) {
		try {
			validateRegistryParam(request);
			userService.create(request.getTelephone(), request.getPassword());
			return ResultMessage.success();
		} catch (UserException e) {
			return ResultMessage.newFailMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@GetMapping("/get/code")
	public ResultMessage getCode(String telephone) {
		try {
			if (StringUtils.isEmpty(telephone)) {
				throw new UserException("电话不得为空");
			}
			String code = VerifyCodeUtils.get();
			commonCache.cache(telephone, code);
			logger.info("手机验证码：{}", code);
			return ResultMessage.success();
		} catch (UserException e) {
			return ResultMessage.newFailMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@PostMapping("/login")
	public ResultMessage login(@RequestBody User user) {
		try {
			validateLoginParam(user);
			User fromDb = userService.get(user.getTelephone());
			return ResultMessage.newSuccessMessage(fromDb);
		} catch (UserException e) {
			return ResultMessage.newFailMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	private void validateRegistryParam(UserRegRequest request) {
		if (StringUtils.isEmpty(request.getTelephone())) {
			throw new UserException("电话号码不得为空");
		}
		if (StringUtils.isEmpty(request.getPassword())) {
			throw new UserException("密码不得为空");
		}
		if (StringUtils.isEmpty(request.getCode())) {
			throw new UserException("验证码不得为空");
		}
		if (userService.get(request.getTelephone()) != null) {
			throw new UserException("该手机号已被注册");
		}
		if (!request.getCode().equals(commonCache.get(request.getTelephone()))) {
			throw new UserException("验证码错误");	
		}
	}

	private void validateLoginParam(User request) {
		if (StringUtils.isEmpty(request.getTelephone())) {
			throw new UserException("电话号码不得为空");
		}
		if (StringUtils.isEmpty(request.getPassword())) {
			throw new UserException("密码不得为空");
		}
		User user = userService.get(request.getTelephone());
		if (user == null) {
			throw new UserException("该手机号尚未注册");
		}
		if (!user.getPassword().equals(request.getPassword())) {
			throw new UserException("密码错误");
		}
	}
}
