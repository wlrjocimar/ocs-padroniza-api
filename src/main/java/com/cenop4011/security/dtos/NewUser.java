package com.cenop4011.security.dtos;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class NewUser {
	
	@NotBlank
	private String userName;
	
	
	@NotNull
	private  String password;
	
	private Set<String> roles = new HashSet<>();
    public NewUser() {
    }
    public NewUser(@NotBlank String userName, @NotBlank String password,
            Set<String> roles) {

        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }
	
	
	

}
