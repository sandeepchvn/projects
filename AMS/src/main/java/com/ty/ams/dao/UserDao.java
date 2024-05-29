package com.ty.ams.dao;

import java.util.List;
import java.util.Optional;

import com.ty.ams.entity.Batch;
import com.ty.ams.entity.User;
import com.ty.ams.util.UserCategory;
import com.ty.ams.util.UserRole;
import com.ty.ams.util.UserStatus;

public interface UserDao {
	
	User saveUser(User user);

	User updateUser(User user);

	Optional<User> findUserById(int userId);

	void deleteUserById(int id);

	Optional<User> findUserByEmpId(String empId);

	List<User> findUserByRole(UserRole role);

	List<User> findUserByCategory(UserCategory category);

	List<User> findAllUsers();

	Optional<User> findUserByPhoneNumber(long phone);

	Optional<User> findUserByEmail(String email);

	List<User> findUserByStatus(UserStatus status);

	Optional<User> findUserByEmailAndPassword(String email, String password);


	User findUserByPhoneAndPassword(long phone, String password);

	List<User> findAllActiveUsers();

	List<User> findAllInActiveUsers();


}
