package com.mk.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mk.otp.OTP;

public interface OTPRepository extends JpaRepository<OTP, Integer> {

	@Query(value = "select otp_text from otp_log_details where mobile_number =?1 or email_id=?1" , nativeQuery = true)
	String findOtpByMobileNumberOrEmailId(String mobileNumber );
	
//	@Query(value = "select otp_text from otp_log_details where email_id =?1" , nativeQuery = true)
//	Otp findOtpByEmailId(String emailId );
//	
	@Query(value = "select otp_generation_date from otp_log_details where mobile_number =?1 " , nativeQuery = true)
	LocalDateTime findGenerationTimeByNumber(String mobileNumber);
	
	
	
	@Query(value = "select * from otp_log_details where mobile_number =?1 " , nativeQuery = true)
	Optional<OTP> findByMobileNumber(String mobileNumber);

	@Query(value = "select * from otp_log_details where email_id =?1 " , nativeQuery = true)
	Optional<OTP> findByEmailId(String emailId);

}
