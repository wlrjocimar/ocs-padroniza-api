package com.cenop4011.security.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenop4011.security.enuns.RoleList;
import com.cenop4011.security.models.Role;
import com.cenop4011.security.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;

@Service

@RequiredArgsConstructor
public class RoleService {
	
	
	private final RoleRepository roleRepository;
	
	
	public Optional<Role> getByRoleName(RoleList roleName){
		
		return roleRepository.findByRoleName(roleName);
		
	}
	
	
	
	
	

}
