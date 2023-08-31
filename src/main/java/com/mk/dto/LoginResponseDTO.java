package com.mk.dto;

import java.time.LocalDateTime;

import com.mk.entities.MasterRole;

import lombok.Data;

@Data
public class LoginResponseDTO {

	private Integer userId;

	private String userName;

	private String mobileNumber;

	private Integer identificationProofId;

	private String identificationProofNumber;

	private String identificationProofDocumentPath;

	private String emailId;

	private LocalDateTime createdOnDateTime;

	private String houseAppartmentBuilding;

	private String blockStreetMohalla;

	private String landmarkLocality;

	private Long postOfficeRecordId;

	private MasterRole roleId;

	private Integer commissionId;

	public LoginResponseDTO() {

	}

	public LoginResponseDTO(Integer userId, String userName, String mobileNumber, Integer identificationProofId,
			String identificationProofNumber, String identificationProofDocumentPath, String emailId,
			LocalDateTime createdOnDateTime, String houseAppartmentBuilding, String blockStreetMohalla,
			String landmarkLocality, Long postOfficeRecordId, MasterRole roleId, Integer commissionId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.mobileNumber = mobileNumber;
		this.identificationProofId = identificationProofId;
		this.identificationProofNumber = identificationProofNumber;
		this.identificationProofDocumentPath = identificationProofDocumentPath;
		this.emailId = emailId;
		this.createdOnDateTime = createdOnDateTime;
		this.houseAppartmentBuilding = houseAppartmentBuilding;
		this.blockStreetMohalla = blockStreetMohalla;
		this.landmarkLocality = landmarkLocality;
		this.postOfficeRecordId = postOfficeRecordId;
		this.roleId = roleId;
		this.commissionId = commissionId;
	}

}
