package com.authenication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authenication.dto.ForgotPassword;
import com.authenication.dto.LoginRequest;
import com.authenication.dto.RegisterRequest;
import com.authenication.dto.ResetPassword;
import com.authenication.service.AuthService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
//	public String register(@RequestBody RegisterRequest request) {
//		return authService.register(request);
//	}
	public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request){
		
		return ResponseEntity.ok(authService.register(request));
	}
	
	@PostMapping("/login")
	public String login(@RequestBody LoginRequest request) {
		return authService.login(request);
	}
	
	@GetMapping("/work")
	public String demo1() {
		return authService.demo();
	}
	
	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(
	        @RequestBody ForgotPassword request) {

	    return ResponseEntity.ok(
	            authService.forgotPassword(request));
	}

	@PostMapping("/reset-password")
	public ResponseEntity<?> resetPassword(
	        @RequestBody ResetPassword request) {

	    return ResponseEntity.ok(
	            authService.resetPassword(request));
	}
	// /auth/login/

}
