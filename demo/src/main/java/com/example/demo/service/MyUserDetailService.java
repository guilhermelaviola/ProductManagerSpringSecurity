package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.MyUserDetail;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> user = userRepository.findUserByUsername(username);
		user.orElseThrow(()-> new UsernameNotFoundException(username + " not found"));
		
		return user.map(MyUserDetail::new).get();
	}
	
	
}
