package com.AirlineReservation.ServiceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.AirlineReservation.DTO.AuthResponseDTO;
import com.AirlineReservation.DTO.LoginRequestDTO;
import com.AirlineReservation.DTO.RegisterRequestDTO;
import com.AirlineReservation.Entity.User;
import com.AirlineReservation.Exceptions.DuplicateResourceException;
import com.AirlineReservation.Repository.UserRepository;
import com.AirlineReservation.Service.UserService;
import com.AirlineReservation.Util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImplementation implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private JwtUtil jwtUtil;
	
	//constructor injection
	public UserServiceImplementation(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

	public String register(RegisterRequestDTO requestDTO) {
		
		if(userRepository.findByEmail(requestDTO.getEmail()).isPresent()) {
			throw new DuplicateResourceException("Email already exists");
		}
		
		User user = User.builder()
				.name(requestDTO.getName())
				.email(requestDTO.getEmail())
				.password(passwordEncoder.encode(requestDTO.getPassword()))
				.role("ADMIN") //default role
				.build();
		userRepository.save(user);
		log.info("User registered successfully: {}", requestDTO.getEmail());
		return "User Registered Successfully";
	}

	@Override
	public AuthResponseDTO login(LoginRequestDTO requestDTO) {
		//fetch user
		User user = userRepository
				.findByEmail(requestDTO.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		log.info("user details fetched sucessfully : {}", user.getEmail());
		//validate password
		boolean isMatch = passwordEncoder.matches(
						requestDTO.getPassword(), 
						user.getPassword());
		
		String sample = isMatch ? "Login successfull" : "Invalid password/username" ;
		
		log.info("Password validation : {}", isMatch ? true : false);
		
		if(!isMatch) {
			log.info("User password entered wrongly");
			throw new RuntimeException("Invalid Password");
		}
		
		String token = jwtUtil.generateToken(user.getEmail());
		
		log.info("User logged in successfully: {}", user.getEmail());
		
		return new AuthResponseDTO(token, sample);
	}
}
