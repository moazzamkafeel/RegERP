package com.mk.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mk.dto.LoginResponseDTO;
import com.mk.dto.UserDTO;
import com.mk.entities.User;
import com.mk.exception.BadRequestException;
import com.mk.otp.OTP;
import com.mk.repository.OTPRepository;
import com.mk.repository.UserRepository;



@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	UserRepository userRepository;

	@Autowired
	OTPRepository otpRepository;

	@Autowired
	private OTPService otpService;

	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public UserDTO addUser(User userReq) {

		User user = new User();
		user.setUserName(userReq.getUserName());
		user.setUserLoginId(userReq.getMobileNumber());
		user.setUserLoginPassword(passEncoder.encode(userReq.getUserLoginPassword()));
		user.setCreatedOnDateTime(userReq.getCreatedOnDateTime());
		user.setRoleId(userReq.getRoleId());

//      Validate Mobile number is already registered or not

		if (validateNumber(user.getMobileNumber())) {
			throw new BadRequestException("Please enter a new Mobile Number");
		} else {
			user.setMobileNumber(userReq.getMobileNumber());
		}

		// Generate OTP

		OTP otp = otpService.generateOTP(userReq.getMobileNumber());

		userRepository.save(user);

		return user.mapUsertoUserDto();
	}

//		------------------------------- login by email or mobile and mobile number ------------
	@Override
	public String otpGenerationForLogin(String emailOrMobile) {
		
		if (Objects.isNull(emailOrMobile) || emailOrMobile.isEmpty()) {
			throw new IllegalArgumentException("Please enter the mobile number ");
		}
		
		String otpstr = null;
		
		User user = null;
		
		boolean validNumber = validateNumber(emailOrMobile);
		
		boolean validEmail = validateEmail(emailOrMobile);

		if (validEmail) {
			user = userRepository.findByUserEmailId(emailOrMobile);
		}
		
		if (validNumber) {
			user = userRepository.findByUserMobileNumber(emailOrMobile);
		}
		
		if (user != null || user.getEmailId().equals(emailOrMobile) || user.getMobileNumber().equals(emailOrMobile)) {
			OTP otp = otpService.generateOTP(user.getMobileNumber());
			otp.setEmailId(emailOrMobile);
			otp.setMobileNumber(emailOrMobile);
			otpstr = otp.toString();
			System.out.println("login with otp" + otp);
			otpRepository.save(otp);
		} 
		return otpstr;

	}

	@Override
	public LoginResponseDTO loginByOTP(String emailOrMobile, String otpText) {
		User user = null;
		boolean validEmail = validateEmail(emailOrMobile);
		boolean validNumber = validateNumber(emailOrMobile);
		if (validEmail) {
			user = userRepository.findByUserEmailId(emailOrMobile);
		}
		if (validNumber) {
			user = userRepository.findByUserMobileNumber(emailOrMobile);
		}
		LoginResponseDTO loginDTO = new LoginResponseDTO();
		if (user != null && !otpText.isEmpty()) {
			loginDTO.setUserId(user.getUserId());
			loginDTO.setUserName(user.getUserName());
			loginDTO.setEmailId(user.getEmailId());
			loginDTO.setMobileNumber(user.getMobileNumber());
		}
		return user.mapUsertoLogin();
	}

	@Override
	public List<User> getAll() {
		List<User> user = userRepository.findAll();
		return user;
	}
	// ------------------------------- login by email or mobile and mobile number
	// ------------

	@Override
	public LoginResponseDTO login(String emailOrMobile, String userLoginPassword) {
		
		User user = null;
	
		boolean validEmail = validateEmail(emailOrMobile);
		boolean validNumber = validateNumber(emailOrMobile);
		log.info("ab validate hoga");
	//	boolean validPassword = validatePassword(userLoginPassword);
		log.info("Ho gya");
		if (validNumber) {
			user = userRepository.findByUserMobileNumber(emailOrMobile);
		}
		if (validEmail) {
			user = userRepository.findByUserEmailId(emailOrMobile);
		}
         
		if (user != null && !user.getUserLoginPassword().isEmpty() ) {
			return user.mapUsertoLogin();
		} else {
			throw new BadRequestException("User not found");
		}

	}

	public boolean validateNumber(String mobileNumber) {
		User number = userRepository.findByUserMobileNumber(mobileNumber);

		if (Objects.isNull(number)) {
			return false;
		} else
			return true;
	}

	@Override
	public boolean validatePassword(String userLoginPassword) {
		// User user = userRepository.findPassword(userLoginPassword);
		log.info("agya yaha");
		User user = userRepository.findUserByPassword(userLoginPassword);
		String storedHashedPassword = user.getUserLoginPassword();
		System.out.println(storedHashedPassword);
		log.info(storedHashedPassword);
		if (user != null && !user.getUserLoginPassword().isEmpty()
				&& passEncoder.matches(userLoginPassword, storedHashedPassword)) {

			return true;// passEncoder.matches(userLoginPassword, storedHashedPassword);

		}

		return false;
	}
//		if (Objects.isNull(user)) {
//			return false;
//		} else {
//			return true;
//		}

	public Boolean validateEmail(String emailId) {
		Optional<OTP> email = otpRepository.findByEmailId(emailId);
		if (email.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public UserDTO detailsBydate() {
		
		List<User> listOfUser = userRepository.findAll();
		Map<Object, List<User>> detailsByDate = listOfUser.stream().collect(Collectors.groupingBy(user->user
				.getCreatedOnDateTime()
				));
		UserDTO userDTO=new UserDTO();
		//BeanUtils.populate(userDTO, detailsByDate);
//		UserDTO userDTO = convertUserToDTO(detailsByDate);
		
		return userDTO;
		
	}
	
	public UserDTO convertUserToDTO(List<User> listOfUser) {
	  //  ModelMapper modelMapper = new ModelMapper();
	UserDTO userDTO = modelMapper.map(listOfUser, UserDTO.class);
	           
	   return userDTO;
	}







}
