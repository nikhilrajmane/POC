package com.user.service.impl;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.dto.SignUpDto;
import com.user.entity.User;
import com.user.repository.UserRepository;
import com.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User signUp(SignUpDto signUpDto) {

		User user = new User(signUpDto.getId(), signUpDto.getFirst_name(), signUpDto.getEmail(), signUpDto.getMobile(),
				signUpDto.getPassword());
		return userRepository.save(user);
	}

	@Override
	public User findByEmail(String emailId) {
		User user = userRepository.findByEmail(emailId);
		if (!Objects.isNull(user))
			return user;
		else
			return null;
	}

	@Override
	public User findByMobile(String mobile) {
		User user = userRepository.findByMobile(mobile);
		if (!Objects.isNull(user))
			return user;
		else
			return null;
	}

	@Override
	public void deleteUser(Long id, String status) {
		if (status.equalsIgnoreCase("hardDelete")) {
			userRepository.deleteById(id);
		} else if (status.equalsIgnoreCase("softDelete")) {
			Optional<User> findById = userRepository.findById(id);
			findById.get().setIsActive(true);
			userRepository.save(findById.get());
		}
	}

	@Override
	public User editUser(User user) {
		Optional<User> findById = userRepository.findById(user.getId());
		User userRes = null;
		if (findById.isPresent()) {
			userRes = new User(user.getId(), user.getName(), user.getEmail(), user.getMobile(), user.getPassword());
			userRes = userRepository.save(userRes);
		}
		return userRes;
	}

	@Override
	public User findByType(String name, String surName, String pinCode) {

		User user = null;
		if (null != name) {
			user = userRepository.findByNameAndIsActive(name, true);
		} else if (null != surName) {
			user = userRepository.findBySurnameAndIsActive(surName, true);
		} else if (null != pinCode) {
			user = userRepository.findByPincodeAndIsActive(pinCode, true);
		}
		return user;
	}

	@Override
	public User sortByType() {
		return userRepository.sortByDOBANDJoiningDate();
	}

}
