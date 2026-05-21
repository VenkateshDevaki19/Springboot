package com.authenication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authenication.dto.LoginRequest;
import com.authenication.dto.RegisterRequest;
import com.authenication.service.AuthService;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public String register(@RequestBody RegisterRequest request) {
		return authService.register(request);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody LoginRequest request) {
		return authService.login(request);
	}
	
	@GetMapping("/work")
	public String demo1() {
		return authService.demo();
	}
	// /auth/login/

}
