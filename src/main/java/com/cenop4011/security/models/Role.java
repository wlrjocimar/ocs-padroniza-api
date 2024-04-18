package com.cenop4011.security.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.cenop4011.security.enuns.RoleList;
import lombok.Data;

@Entity
@Table(name = "tb_roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private RoleList roleName;
	
	public Role() {
		
	}

	public Role(Integer id, @NotNull RoleList roleName) {
		
		this.id = id;
		this.roleName = roleName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleList getRoleName() {
		return roleName;
	}

	public void setRole(RoleList roleName) {
		this.roleName = roleName;
	}

		
	
}
