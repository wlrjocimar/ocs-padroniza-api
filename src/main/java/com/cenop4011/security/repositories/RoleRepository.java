package com.cenop4011.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cenop4011.security.enuns.RoleList;
import com.cenop4011.security.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Optional<Role> findByRoleName(RoleList roleName);
	

}
