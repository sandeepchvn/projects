package com.ty.ams.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ty.ams.dto.UserDto;
import com.ty.ams.entity.User;
import com.ty.ams.responsestructure.ResponseStructure;
import com.ty.ams.util.UserCategory;
import com.ty.ams.util.UserRole;
import com.ty.ams.util.UserStatus;

public interface UserService {

	ResponseEntity<ResponseStructure<User>> saveUser(User user);

	ResponseEntity<ResponseStructure<User>> updateUser(User user);

	ResponseEntity<ResponseStructure<User>> findUserById(int id);

	ResponseEntity<ResponseStructure<User>> findUserByEmpId(String id);

	ResponseEntity<ResponseStructure<User>> findUserByPhone(long phone);

	ResponseEntity<ResponseStructure<User>> findUserByEmail(String email);

	ResponseEntity<ResponseStructure<String>> deleteUserById(int id);

	ResponseEntity<ResponseStructure<User>> findUserByEmailAndPassword(String email, String password);

	ResponseEntity<ResponseStructure<User>> setUserStatusToInActive(int id);

	ResponseEntity<ResponseStructure<List<User>>> findAllUsers();

	ResponseEntity<ResponseStructure<List<User>>> findAllActiveUsers();

	ResponseEntity<ResponseStructure<List<User>>> findAllInActiveUsers();

	ResponseEntity<ResponseStructure<List<User>>> findUserByRole(UserRole role);

	ResponseEntity<ResponseStructure<List<User>>> findUserByCategory(UserCategory category);

	ResponseEntity<ResponseStructure<List<User>>> findUserByStatus(UserStatus status);

	ResponseEntity<ResponseStructure<List<LocalTime>>> findBatchTimingsOfUser(int userId);

	ResponseEntity<ResponseStructure<User>> setUserStatusToInAcativeByUserId(int userId);

	ResponseEntity<ResponseStructure<List<UserDto>>> findAllTrainersToAssiginBatch();
	
	ResponseEntity<ResponseStructure<User>> reAssignBatchToUser(int batchId, int  userId);

	ResponseEntity<ResponseStructure<List<User>>> findAllTrainersToCreateBatch();
	

}
