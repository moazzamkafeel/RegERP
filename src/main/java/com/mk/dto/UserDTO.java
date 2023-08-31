package com.mk.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mk.entities.MasterRole;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class UserDTO {

	private Integer userId;

	private String userLoginId;

	private String userName;

	private String mobileNumber;

	private Integer identificationProofId;

	private String identificationProofNumber;

	private String identificationProofDocumentPath;

	private LocalDateTime createdOnDateTime;

	private String emailId;

	private String houseAppartmentBuilding;

	private String blockStreetMohalla;

	private String landmarkLocality;

	private String message;

	private Boolean status;

	private MasterRole roleId;

	private Integer commissionId;

	private Integer addressTypeId;

	private Integer stateId;

	private Integer districtId;

	private String postOffice;

	private Long postOfficeRecordId;

	private Long pinCode;

	public UserDTO(String userName, String userLoginId, Integer userId, String mobileNumber,
			LocalDateTime createdOnDateTime) {
		super();
		this.userName = userName;
		this.userLoginId = userLoginId;
		this.userId = userId;
		this.mobileNumber = mobileNumber;
		this.createdOnDateTime = createdOnDateTime;

	}
//
//	public UserResponseDto(Integer userId, String emailId, String houseAppartmentBuilding, String blockStreetMohalla,
//			String landmarkLocality, Integer addressTypeId, Integer stateId, Integer districtId, String postOffice,
//			Long postOfficeRecordId, Long pinCode) {
//		super();
//		this.userId = userId;
//		this.emailId = emailId;
//		this.houseAppartmentBuilding = houseAppartmentBuilding;
//		this.blockStreetMohalla = blockStreetMohalla;
//		this.landmarkLocality = landmarkLocality;
//		this.addressTypeId = addressTypeId;
//		this.stateId = stateId;
//		this.districtId = districtId;
//		this.postOffice = postOffice;
//		this.postOfficeRecordId = postOfficeRecordId;
//		this.pinCode = pinCode;
//	}
//
//	public UserResponseDto(Integer userId, String userName, String mobileNumber, String emailId, String message,
//			Boolean status) {
//		super();
//		this.userId = userId;
//		this.userName = userName;
//		this.mobileNumber = mobileNumber;
//		this.emailId = emailId;
//		this.message = message;
//		this.status = status;
//	}
//
//	public UserResponseDto(Integer userId, String userName, String mobileNumber, String emailId, MasterRole roleId,
//			Integer commissionId) {
//		super();
//		this.userId = userId;
//		this.userName = userName;
//		this.mobileNumber = mobileNumber;
//		this.roleId = roleId;
//		this.emailId = emailId;
//	}
//
//	public UserResponseDto(Integer userId, String userName, String mobileNumber, Integer identificationProofId,
//			String identificationProofNumber, String identificationProofDocumentPath, String emailId,
//			String houseAppartmentBuilding, String blockStreetMohalla, String landmarkLocality, String message,
//			Boolean status) {
//		super();
//		this.userId = userId;
//		this.userName = userName;
//		this.mobileNumber = mobileNumber;
//		this.identificationProofId = identificationProofId;
//		this.identificationProofNumber = identificationProofNumber;
//		this.identificationProofDocumentPath = identificationProofDocumentPath;
//		this.emailId = emailId;
//		this.houseAppartmentBuilding = houseAppartmentBuilding;
//		this.blockStreetMohalla = blockStreetMohalla;
//		this.landmarkLocality = landmarkLocality;
//		this.message = message;
//		this.status = status;
//	}

	
	

}
