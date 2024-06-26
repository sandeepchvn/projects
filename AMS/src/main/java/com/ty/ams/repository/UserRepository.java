package com.ty.ams.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.ams.entity.User;
import com.ty.ams.util.UserCategory;
import com.ty.ams.util.UserRole;
import com.ty.ams.util.UserStatus;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmpId(String empId);

	List<User> findByUserRole(UserRole role);

	List<User> findByUserCategory(UserCategory category);
	
	@Query("Select u from User u where u.userStatus='ACTIVE'")
	public List<User> findByAllActiveUsers() ;
	
	@Query("Select u from User u where u.userStatus='IN_ACTIVE'")
	public List<User> findAllInActiveUsers();

	Optional<User> findByPhone(long phone);

	Optional<User> findByEmail(String email);

	List<User> findByUserStatus(UserStatus status);

	Optional<User> findByEmailAndPassword(String email, String password);

	Optional<User> findByPhoneAndPassword(long phone, String password);
	
//	List<LocalTime> findByBatchTimingsOfUser(int userId);

//	@Query("select u from User u where u.userStatus = 1?")
//	Optional<List<User>> findAllActiveUsers();

}
