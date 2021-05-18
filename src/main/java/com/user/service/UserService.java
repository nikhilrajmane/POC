package com.user.service;

import com.user.dto.SignUpDto;
import com.user.entity.User;

public interface UserService {
	User signUp(SignUpDto signUpDto);

	User findByEmail(String emailId);

	User findByMobile(String mobile);

	void deleteUser(Long id, String status);

	User editUser(User user);

	User findByType(String name, String surName, String pinCode);

	User sortByType();

}
