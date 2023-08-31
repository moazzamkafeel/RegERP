package com.mk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mk.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	 @Query(value = "select * from user_details where user_id = ?1 " , nativeQuery = true)
	 Optional<User> findByUserId(Integer userId);

   Optional<User> findByEmailId(String emailId);
   
   @Query(value = "select * from user_details where email_id = ?1 " , nativeQuery = true)
   User findByUserEmailId(String emailId);
   
   @Query(value = "select * from user_details where mobile_number = ?1 " , nativeQuery = true)
   User findByUserMobileNumber(String mobileNumber);

   @Query(value = "select * from user_details where user_login_password = ?1 And (email_id = ?2 OR mobile_number = ?3) "
   		 , nativeQuery = true)
	User findOneByEmailIdOrMobileNumberAndUserLoginPassword(String userLoginPassword ,String emailId, Long mobileNumber );

   @Query(value = "select * from user_details where email_id = ?1 OR mobile_number = ?2 "
  		 , nativeQuery = true)
	User findOneByEmailIdOrMobileNumber(String emailId,  Long mobileNumber);
   
   @Query(value = "select user_login_password from user_details where user_id = ?1 " , nativeQuery = true)
   User findPassword(String userLoginPassword);
   
   @Query(value = "select * from user_details where user_login_password = ?1 " , nativeQuery = true)
   User findUserByPassword(String userLoginPassword);
	
}
