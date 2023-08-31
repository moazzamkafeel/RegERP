package com.mk.service;

import java.util.List;

import com.mk.dto.LoginResponseDTO;
import com.mk.dto.UserDTO;
import com.mk.entities.User;

public interface UserService {

	UserDTO addUser(User user);

	String otpGenerationForLogin(String emailOrMobile);

	LoginResponseDTO login(String emailOrMobile, String password);

	LoginResponseDTO loginByOTP(String emailOrMobile, String otpText);

	List<User> getAll();

	boolean validatePassword(String password);
	
	//Practice
	UserDTO detailsBydate();

}
