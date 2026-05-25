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

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public String register(@RequestBody RegisterRequestDTO dto) {
		return userService.register(dto);
	}
	
	@PostMapping("/login")
	public AuthResponseDTO login( @RequestBody LoginRequestDTO dto) {
		return userService.login(dto);
	}
}
