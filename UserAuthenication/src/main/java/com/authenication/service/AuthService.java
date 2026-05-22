package com.authenication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.authenication.dto.ForgotPassword;
import com.authenication.dto.LoginRequest;
import com.authenication.dto.RegisterRequest;
import com.authenication.dto.ResetPassword;
import com.authenication.entity.Otp;
import com.authenication.entity.User;
import com.authenication.repository.OtpRepository;
import com.authenication.repository.UserRepository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AuthService {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder encoder;
	
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private OtpRepository otpRepository;
	
	
	private static final Logger logger =
	        LoggerFactory.getLogger(AuthService.class);
	
	public String register(RegisterRequest request) {
		
		if(userRepository.findByEmail(request.getEmail()).isPresent()){
			logger.info("Email already exist", request.getEmail());
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
		
		String output = "User Registered Successfully"+ user.getName();

		return output;
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

	        throw new RuntimeException("Invalid Username/Password");
		}
		logger.info("Login successful for {}", request.getEmail());
		
		String output = "Login  Successfully"+ user.getName();
		
		return output;
	}
	
	public String demo() {
		return "Working";
	}
	
	public String forgotPassword(ForgotPassword forgotPassword) {
		
		logger.info("Forgot password request for {}", forgotPassword.getEmail());
		
		String otp = String.valueOf((int)(Math.random()* 9000)+1000);
		
		Otp otpEntity = new Otp();
		
		otpEntity.setEmail(forgotPassword.getEmail());
		otpEntity.setOtp(otp);
		
		otpRepository.save(otpEntity);
		
		logger.info("OtpEnity Values {}", otpEntity.getOtp());
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(forgotPassword.getEmail());
		message.setSubject("Password Reset OTP");
		message.setText("your OTP is: "+otp);
		System.out.println(otp);
		logger.info("OTP value", otp.toString());
		mailSender.send(message);
		
		logger.info("mail sending details", message.getText());
		
		logger.info("OTP Sent Successfully");
		
		return "OTP Sent Successfully";
	}
	
	public String resetPassword(ResetPassword request) {
		
		Otp otpEntity = otpRepository
				.findByEmail(request.getEmail())
				.orElseThrow(() -> new RuntimeException("OTP not found"));
		
		if(!otpEntity.getOtp().equals(request.getOtp())) {
			logger.warn("Invalid OTP request");
			
			throw new RuntimeException("Invalid OTP");
		}
		
		User user = userRepository
				.findByEmail(request.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		user.setPassword(encoder.encode(request.getNewPassword()));
		
		userRepository.save(user);
		
		logger.info("Password reset successful");
		
		return "Password updated successfully";
		
	}

}
