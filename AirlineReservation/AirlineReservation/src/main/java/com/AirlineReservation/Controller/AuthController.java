package com.AirlineReservation.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AirlineReservation.DTO.AuthResponseDTO;
import com.AirlineReservation.DTO.LoginRequestDTO;
import com.AirlineReservation.DTO.RegisterRequestDTO;
import com.AirlineReservation.Service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	//Register API end point
	@PostMapping("/register")
	public String register(@Valid @RequestBody RegisterRequestDTO dto) {
		return userService.register(dto);
	}
	
	//Login Endpoint
	@PostMapping("/login")
	public AuthResponseDTO login(@Valid @RequestBody LoginRequestDTO dto) {
		return userService.login(dto);
	}
}
