package com.cenop4011.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.cenop4011.security.models.MainUser;
import com.cenop4011.security.models.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	
		private final UserService userService;
		
		@Override
		public  UserDetails loadUserByUsername(String userName) {
			User user = userService.getByUserName(userName).get();
			return MainUser.build(user);
			
			
		}

		
}
