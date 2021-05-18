package com.user.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.constants.AppsConstant;
import com.user.dto.ResponseDto;
import com.user.dto.SignUpDto;
import com.user.dto.UserDto;
import com.user.entity.User;
import com.user.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

	private final Logger logger = LogManager.getLogger(this);

	@Autowired
	private UserService userService;

	@PostMapping("/sign-up")
	public ResponseDto<User> signUp(@RequestBody SignUpDto signUpDto, HttpServletResponse httpServletResponse) {

		logger.info("UserController-Inside sign up Method");

		ResponseDto<User> responseDto = new ResponseDto<User>();
		int httpStatusCode;
		if (Objects.isNull(userService.findByEmail(signUpDto.getEmail()))
				&& Objects.isNull(userService.findByMobile(signUpDto.getMobile()))) {

			User user = userService.signUp(signUpDto);
			httpStatusCode = HttpStatus.OK.value();
			httpServletResponse.setStatus(httpStatusCode);
			responseDto.setStatusCode(httpStatusCode);
			responseDto.setMessage(AppsConstant.USER_REGISTERED_SUCCESSFULLY);
			responseDto.setData(user);
		} else {
			httpStatusCode = HttpStatus.CONFLICT.value();
			httpServletResponse.setStatus(httpStatusCode);
			responseDto.setMessage(AppsConstant.EMAIL_OR_MOBILE_ALREADY_EXIST);
		}
		return responseDto;
	}

	@PutMapping("/edit")
	public ResponseDto<User> editUser(@RequestBody User user, HttpServletResponse httpServletResponse) {
		ResponseDto<User> responseDto = new ResponseDto<User>();
		int httpStatusCode;
		User userResponse = userService.editUser(user);

		if (Objects.nonNull(user)) {
			httpStatusCode = HttpStatus.OK.value();
			httpServletResponse.setStatus(httpStatusCode);
			responseDto.setStatusCode(httpStatusCode);
			responseDto.setMessage(AppsConstant.RECORD_UPDATED_SUCCESSFULLY);
			responseDto.setData(userResponse);
		} else {
			httpStatusCode = HttpStatus.CONFLICT.value();
			httpServletResponse.setStatus(httpStatusCode);
			responseDto.setMessage(AppsConstant.RECORD_NOT_FOUND);
		}

		return responseDto;
	}

	@GetMapping("/byType")
	public ResponseDto<User> getUserByName(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "surName", required = false) String surName,
			@RequestParam(value = "pinCode", required = false) String pinCode,
			HttpServletResponse httpServletResponse) {

		ResponseDto<User> responseDto = new ResponseDto<User>();
		int httpStatusCode;
		User user = userService.findByType(name, surName, pinCode);
		if (Objects.nonNull(user)) {
			httpStatusCode = HttpStatus.OK.value();
			httpServletResponse.setStatus(httpStatusCode);
			responseDto.setStatusCode(httpStatusCode);
			responseDto.setMessage(AppsConstant.RECORDS_FETCHED_SUCCESSFULLY);
			responseDto.setData(user);
		} else {
			httpStatusCode = HttpStatus.CONFLICT.value();
			httpServletResponse.setStatus(httpStatusCode);
			responseDto.setMessage(AppsConstant.RECORD_NOT_FOUND);
		}
		return responseDto;
	}

	@GetMapping("/sortByDOBAndJoinDate")
	public ResponseDto<User> sortUserbyType(HttpServletResponse httpServletResponse) {

		ResponseDto<User> responseDto = new ResponseDto<User>();
		int httpStatusCode;
		User user = userService.sortByType();
		if (Objects.nonNull(user)) {
			httpStatusCode = HttpStatus.OK.value();
			httpServletResponse.setStatus(httpStatusCode);
			responseDto.setStatusCode(httpStatusCode);
			responseDto.setMessage(AppsConstant.RECORDS_FETCHED_SUCCESSFULLY);
			responseDto.setData(user);
		} else {
			httpStatusCode = HttpStatus.CONFLICT.value();
			httpServletResponse.setStatus(httpStatusCode);
			responseDto.setMessage(AppsConstant.RECORD_NOT_FOUND);
		}
		return responseDto;
	}

	@DeleteMapping("/delete")
	public ResponseDto<List<UserDto>> delete(@RequestParam(value = "id") Long id,
			@RequestParam(value = "status", required = false) String status, HttpServletResponse httpServletResponse) {

		ResponseDto<List<UserDto>> responseDto = new ResponseDto<>();
		int httpStatusCode;
		userService.deleteUser(id, status);
		httpStatusCode = HttpStatus.OK.value();
		httpServletResponse.setStatus(httpStatusCode);
		responseDto.setStatusCode(httpStatusCode);
		responseDto.setMessage(AppsConstant.RECORD_DELETED_SUCCESSFULLY);
		return responseDto;
	}

}
