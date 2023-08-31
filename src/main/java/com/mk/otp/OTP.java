package com.mk.otp;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "otp_log_details" )
@Data
public class OTP {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "record_id")
	private Integer recordId;

	@Column(name = "mobile_number")
	private String mobileNumber;
	
	@Column(name = "email_id")
	private String emailId;
	
	@Column(name = "otp_text")
	private String otpText;

	@Column(name = "otp_generation_date")
	private LocalDateTime otpGenerationDate;

	@Column(name = "otp_expiration_date")
	private LocalDateTime otpExpirationDate;

	@Column(name = "opt_matching_status")
	private String otpMatchingStatus;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "client_ip_address")
	private String clientIpAddress;

	@Column(name = "created_on_date_time")
	private LocalDateTime createdOnDAteTime;

	@Column(name = "active_status")
	private Integer activeStatus;
}
