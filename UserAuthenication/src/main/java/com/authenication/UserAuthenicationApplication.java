package com.authenication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.authenication")
public class UserAuthenicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthenicationApplication.class, args);
	}

}
