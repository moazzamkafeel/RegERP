package com.mk.service;

import java.util.List;

import com.mk.otp.OTP;

public interface OTPService {

	OTP generateOTP(String mobileNumber);
	boolean validateOTP(String emailOrMobile, String otpText);
	List<OTP> getAllOTP();
}
