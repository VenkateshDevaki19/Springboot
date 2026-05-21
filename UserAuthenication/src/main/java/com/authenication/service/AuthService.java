package com.authenication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.authenication.dto.LoginRequest;
import com.authenication.dto.RegisterRequest;
import com.authenication.entity.User;
import com.authenication.repository.UserRepository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder encoder;
	
	
	private static final Logger logger =
	        LoggerFactory.getLogger(AuthService.class);
	
	public String register(RegisterRequest request) {
		
		if(userRepository.findByEmail(request.getEmail()).isPresent()){
			return "Email already exist";
		}
		
		User user = new User(
			    null, request.getName(),
			    request.getEmail(),
			    encoder.encode(request.getPassword()),
			    "USER"
			);
		
		userRepository.save(user);

//		user.setName(((RegisterRequest) request).getName());
//		user.setEmail(request.getEmail());
//		user.setPassword(encoder.encode(request.getPassword()));
//		user.setRole("User");

		return "User Registered Successfully";
	}
	
	
	public String login(LoginRequest request) {
		
		logger.info("Login attempt for {}", request.getEmail());
		
		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(
						() -> {

			                logger.error("User not found {}", request.getEmail());

			                return new RuntimeException("User not found");
			            });
		
		if(!encoder.matches(
				request.getPassword(), 
				user.getPassword())) {
			logger.warn("Invalid password attempt for {}",
	                request.getEmail());

	        throw new RuntimeException("Invalid Password");
		}
		logger.info("Login successful for {}", request.getEmail());
		return "login success";
	}
	
	public String demo() {
		return "Working";
	}
	

}
