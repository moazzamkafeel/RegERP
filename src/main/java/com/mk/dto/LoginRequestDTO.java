package com.mk.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
	private String emailOrMobile;
	private String userLoginPassword;
	private String roles;
	private String otpText;
}
