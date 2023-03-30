package com.cenop4011.security.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cenop4011.security.models.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	Optional<User> findByUserName(String userName);
	boolean existsByUserName(String userName);

}
