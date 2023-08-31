package com.mk.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mk.exception.BadRequestException;
import com.mk.otp.OTP;
import com.mk.repository.OTPRepository;
@Service
public class OTPServiceImpl implements OTPService{

	@Autowired
	OTPRepository otpRepository;
	
	@Override
	public OTP generateOTP(String mobileNumber) {
		LocalDateTime otpGenerationTime = LocalDateTime.now();
		LocalDateTime expiartionTime = otpGenerationTime.plusMinutes(15);

		StringBuilder generateOTP = new StringBuilder();
		SecureRandom secureRandom = new SecureRandom();
		int otpLength = 6;
		try {
			secureRandom = SecureRandom.getInstance(secureRandom.getAlgorithm());
			for (int i = 0; i < otpLength; i++) {
				generateOTP.append(secureRandom.nextInt(10));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		String generatedOTP = generateOTP.toString();

		OTP otp = new OTP();
		otp.setOtpText(generatedOTP);
		otp.setOtpGenerationDate(otpGenerationTime);
		otp.setOtpExpirationDate(expiartionTime);
		otp.setMobileNumber(mobileNumber);

		OTP saveOTP = otpRepository.save(otp);
		return saveOTP;
	}
	
	public boolean isvalidEmail(String emailId)
	{
		Optional<OTP> email = otpRepository.findByEmailId(emailId);
		if(email.isPresent())
		{
			return true;
		} else return false;
	}
	private boolean isValidMobileNumber(String mobileNumber) {
		Optional<OTP> mobilenumber = otpRepository.findByMobileNumber(mobileNumber);
		if(mobilenumber.isPresent()) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public boolean validateOTP(String emailOrMobile, String otpText)
	{
		if(Objects.isNull(emailOrMobile) || emailOrMobile.isEmpty())
		{
			throw new IllegalArgumentException("Email or mobile cannot be empty");
		}
		Optional<OTP> userOTP = null;
		boolean email = isvalidEmail(emailOrMobile);
     	boolean mobileNumber = isValidMobileNumber(emailOrMobile);
     	if(email)
     	{
     		userOTP=otpRepository.findByEmailId(emailOrMobile);
     	}
     	if(mobileNumber)
     	{
     		userOTP=otpRepository.findByEmailId(emailOrMobile);
     	}
     	// Check Expiration of OTP
     	try {
			LocalDateTime currentDateTime = LocalDateTime.now();
		LocalDateTime generatedTime= otpRepository.findGenerationTimeByNumber(emailOrMobile);
			if (Objects.isNull(generatedTime))
			{
				throw new IllegalArgumentException("OTP Creation time cannot be null");
			}
			LocalDateTime expirationTime = generatedTime.plusMinutes(15);
			
			if(expirationTime.isBefore(currentDateTime))
			{
				System.out.println("Otp is expire");
				
				return false;
			} else {
				
				String otpDb = otpRepository.findOtpByMobileNumberOrEmailId(emailOrMobile);
			
				if(userOTP.isPresent() && otpDb != null && otpDb.equals(otpText))
				{
					return true;
				}
				else {
					throw new BadRequestException("Otp is not valid please try again !");
				}
			}
			
			
		} catch (Exception e) {
	e.printStackTrace();
	throw new RuntimeException("Error during OTP validation: "+e.getMessage());
		}
     	
	}

	@Override
	public List<OTP> getAllOTP() {
	List<OTP> otp = otpRepository.findAll();
		return otp;
	}
}
