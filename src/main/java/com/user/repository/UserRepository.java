package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String emailId);

	User findByMobile(String mobile);

	User findByNameAndIsActive(String name, Boolean isActive);

	User findBySurnameAndIsActive(String surName, Boolean isActive);

	User findByPincodeAndIsActive(String pinCode, Boolean isActive);

	@Query(value = "SELECT * FROM User u order by u.DOB,u.joiningDate desc", nativeQuery = true)
	User sortByDOBANDJoiningDate();

}
