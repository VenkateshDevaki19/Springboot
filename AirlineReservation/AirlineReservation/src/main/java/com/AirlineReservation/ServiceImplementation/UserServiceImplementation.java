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
import com.AirlineReservation.Repository.UserRepository;
import com.AirlineReservation.Service.UserService;
import com.AirlineReservation.Util.JwtUtil;

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
		
		User user = User.builder()
				.name(requestDTO.getName())
				.email(requestDTO.getEmail())
				.password(passwordEncoder.encode(requestDTO.getPassword()))
				.role("USER") //default role
				.build();
		userRepository.save(user);
		
		return "User Registered Successfully";
	}

	@Override
	public AuthResponseDTO login(LoginRequestDTO requestDTO) {
		//fetch user
		User user = userRepository
				.findByEmail(requestDTO.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		//validate password
		boolean isMatch = passwordEncoder.matches(
						requestDTO.getPassword(), 
						user.getPassword());
		
		if(!isMatch) {
			throw new RuntimeException("Invalid Password");
			//throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password");
		}
		
		String token = jwtUtil.generateToken(user.getEmail());
		
		return new AuthResponseDTO(token, "Login Successful");
	}
}
