package com.cenop4011.security.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginUser {
	
		@NotBlank
		private String userName;
		@NotBlank
		private String password;

}
