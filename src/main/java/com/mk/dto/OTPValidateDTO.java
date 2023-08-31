package com.mk.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class OTPValidateDTO {
	
	  private String emailOrMobile;
	   private String otpText;
	   private LocalDateTime otpCreationTime;
	   
}
