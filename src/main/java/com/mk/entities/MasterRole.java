package com.mk.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "master_role")
@Data
public class MasterRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Integer roleId;

	@Column(name = "role_name_en")
	private String roleName;

	@Column(name = "role_name_ll")
	private String roleNameII;

	@Column(name = "role_name_abbreviation")
	private String roleNameAbbreviation;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "active_status")
	private Integer activeStatus;

	public MasterRole(Integer roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public MasterRole() {
		super();
	}
}
