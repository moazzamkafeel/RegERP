package com.mk.entities;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.mk.dto.LoginResponseDTO;
import com.mk.dto.UserDTO;

import lombok.Data;

@Entity
@Table(name = "user_details")
@Data
public class User {
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;

	@Column(name = "user_login_id")
	private String userLoginId;

	@Column(name = "user_login_password")
	private String userLoginPassword;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "mobile_number")
	private String mobileNumber;

	@Column(name = "identification_proof_id")
	private Integer identificationProofId;

	@Column(name = "identification_proof_number")
	private String identificationProofNumber;

	@Column(name = "identification_proof_document_path")
	private String identificationProofDocumentPath;

	@Column(name = "commission_type_id")
	private Integer commissionTypeId;

	@Column(name = "commission_id")
	private Integer commissionId;

	@Column(name = "user_status_id")
	private Integer userStatusId;

	@Column(name = "valid_from_date")
	private Date validFromDate;

	@Column(name = "valid_to_date")
	private Date validToDate;

	@Column(name = "remarks")
	private String remarks;

	@CreationTimestamp
	@Column(name = "created_on_date_time")
	private LocalDateTime createdOnDateTime;

	@Column(name = "active_status")
	private Integer activeStatus;

	@Column(name = "client_ip_address")
	private String clientIpAddress;

	@Column(name = "house_appartment_building")
	private String houseAppartmentBuilding;

	@Column(name = "block_street_mohalla")
	private String blockStreetMohalla;

	@Column(name = "landmark_locality")
	private String landmarkLocality;

	@Column(name = "address_type_id")
	private Integer addressTypeId;

	@Column(name = "state_id")
	private Integer stateId;

	@Column(name = "district_id")
	private Integer districtId;

	@Column(name = "post_office")
	private String postOffice;

	@Column(name = "post_office_record_id")
	private Long postOfficeRecordId;

	@Column(name = "pin_code", columnDefinition = "NUMERIC(8,0)")
	private Long pinCode;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private MasterRole roleId;

	private String profilePic;

	    public UserDTO mapUsertoUserDto() {
	        return new UserDTO(userName, userLoginId, userId,   mobileNumber , createdOnDateTime);
	    }
	    
//
//	    public UserResponseDto mapUsertoAddressRequestDto() {
//	    	return new UserResponseDto( userId , emailId,  houseAppartmentBuilding,  blockStreetMohalla,landmarkLocality, addressTypeId, stateId, districtId, 
//	    			postOffice, postOfficeRecordId, pinCode);
//	    }
//	    
//	    public UserResponseDto mapUsertoLoginResponseDto() {
//	    	return new UserResponseDto( userId,  userName,  emailId,  mobileNumber, "Success",
//					true);
//	    }
//	    
//	    public UserResponseDto mapUsertoaddRoleEmail() {
//	    	return new UserResponseDto( userId,  userName,  mobileNumber, emailId, roleId,commissionId);
//	    }
//	    
//	    public UserResponseDto mapUsertoUploadDocuments() {
//	    	return new UserResponseDto(  userId,  userName,  mobileNumber,  identificationProofId,
//					 identificationProofNumber,  identificationProofDocumentPath, 
//					 emailId, houseAppartmentBuilding,  blockStreetMohalla,landmarkLocality,  "User's Complete Details Add Successfully",
//					 true);
//	    }
//	    
	    public LoginResponseDTO mapUsertoLogin() {
	    	return new LoginResponseDTO( userId,  userName,  mobileNumber,  identificationProofId,
	    			 identificationProofNumber,  identificationProofDocumentPath,    emailId,
	    			 createdOnDateTime,  houseAppartmentBuilding,  blockStreetMohalla,
	    			 landmarkLocality,  postOfficeRecordId,  roleId,  commissionId);

	    }
}
