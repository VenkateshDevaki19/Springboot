package com.AirlineReservation.Service;

import com.AirlineReservation.DTO.*;

// we define authenication methods here
public interface UserService {
	
	//register new user
	String register(RegisterRequestDTO requestDTO);

	//login user
	AuthResponseDTO login(LoginRequestDTO requestDTO);
}
