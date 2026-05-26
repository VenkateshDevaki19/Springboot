package com.AirlineReservation.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
	
	@Email(message = "Email should be valid")
	@NotBlank(message = "Email is Required")
	private String email;
	
	@NotBlank(message = "Name is Required")
	private String name;
	
	@NotBlank(message = "Password is Required")
	private String password;

}
