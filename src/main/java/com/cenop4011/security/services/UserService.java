package com.cenop4011.security.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cenop4011.security.models.User;
import com.cenop4011.security.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class UserService {

	private final UserRepository userRepository;
	 
	public Optional<User> getByUserName(String userName){
		return userRepository.findByUserName(userName);
		
	}
	
	public boolean existsByUserName(String userName) {
		return userRepository.existsByUserName(userName);
		
	}
	
	public void save(User user) {
		
			userRepository.save(user);
	}
	
}
