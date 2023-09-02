package com.mk.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mk.dto.LoginRequestDTO;
import com.mk.dto.LoginResponseDTO;
import com.mk.dto.OTPValidateDTO;
import com.mk.dto.UserDTO;
import com.mk.entities.User;
import com.mk.otp.OTP;
import com.mk.service.OTPService;
import com.mk.service.UserService;
import com.mk.utils.ResponseHandler;


//moazzam origin
@RestController

@RequestMapping("/")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService userService;
	
	@Autowired
	OTPService otpService;

	@PostMapping("/reg")
	public ResponseEntity<UserDTO> addUser(@RequestBody User user) {
		UserDTO saveUser = userService.addUser(user);
		return new ResponseEntity<UserDTO>(saveUser, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> findAll() {
	 List<User> user = userService.getAll();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("/allOTP")
	public ResponseEntity<List<OTP>> findAllOTP() {
		List<OTP> otp = otpService.getAllOTP();
		return new ResponseEntity<>(otp, HttpStatus.OK);
	}

	@PostMapping(value ="/validate_otp", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> validateOtp(@RequestBody OTPValidateDTO otpValidateReqDto)
			throws JsonProcessingException {
		
            log.info("API Request to validate otp");
            
		try 
		{
			String emailOrMobile = otpValidateReqDto.getEmailOrMobile();
			String otpText = otpValidateReqDto.getOtpText();
			boolean validateOtp = otpService.validateOTP(emailOrMobile, otpText);

			if (validateOtp == true) {
				return ResponseHandler.generateResponse("otp validate succesfully ", HttpStatus.OK, null, validateOtp);
			} else {
				return ResponseHandler.generateResponse("otp is expired ", HttpStatus.BAD_REQUEST, null, null);
			}
		} catch (Exception e) {
			new ResponseHandler();
			return ResponseHandler.generateResponse("user is not present in database", HttpStatus.BAD_REQUEST,
					"user not available", "");
		}

	}

	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> loginUser(@RequestBody LoginRequestDTO loginRequest) throws JsonProcessingException {

		log.info("API Request to login user and fetch the details of user");
		try {
			String emailOrMobile = loginRequest.getEmailOrMobile();
			String password = loginRequest.getUserLoginPassword();
			// String roles = loginRequest.getRoles();

			LoginResponseDTO user = userService.login(emailOrMobile, password);
			if (user.getUserId() != null) {
				return ResponseHandler.generateResponse("User logged In succesfully ", HttpStatus.OK, null, user);
			} else {
				return ResponseHandler.generateResponse("User not found by this email or login", HttpStatus.BAD_REQUEST,
						null, null);
			}
		} catch (Exception e) {
			new ResponseHandler();
			return ResponseHandler.generateResponse("User Not found", HttpStatus.BAD_REQUEST, "user not available", "");
		}
	}

	@PostMapping(value = "/login_with_otp", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> loginWithOtp(@RequestBody LoginRequestDTO loginRequest)
			throws JsonProcessingException {

		log.info("API Request to login user and fetch the details of user");
		try {
			String emailOrMobile = loginRequest.getEmailOrMobile();
			String otpText = loginRequest.getOtpText();

			LoginResponseDTO user = userService.loginByOTP(emailOrMobile, otpText);
			if (user.getUserId() != null) {
				return ResponseHandler.generateResponse("User logged In succesfully ", HttpStatus.OK, null, user);
			} else {
				return ResponseHandler.generateResponse("User not found by this email or login", HttpStatus.BAD_REQUEST,
						null, null);
			}
		} catch (Exception e) {
			new ResponseHandler();
			return ResponseHandler.generateResponse("User Not found", HttpStatus.BAD_REQUEST, "user not available", "");
		}
	}

	@PostMapping(value = "/otp_generated_for_login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> otpGenerationForLogin(@RequestBody LoginRequestDTO loginRequest)
			throws JsonProcessingException {

		log.info("API Request to login user and fetch the details of user");
		try {
			String emailOrMobile = loginRequest.getEmailOrMobile();

			if (emailOrMobile != null) {
				return ResponseHandler.generateResponse(" otp generated by email or number for the login succesfully ",
						HttpStatus.OK, null, userService.otpGenerationForLogin(emailOrMobile));
			} else {
				return ResponseHandler.generateResponse("User not found by this email or login", HttpStatus.BAD_REQUEST,
						null, null);
			}
		} catch (Exception e) {
			new ResponseHandler();
			return ResponseHandler.generateResponse("User Not found", HttpStatus.BAD_REQUEST, "user not available", "");
		}
	}
}
