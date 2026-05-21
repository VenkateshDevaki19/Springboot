//package com.authenication.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import jakarta.annotation.PostConstruct;
//
//@Configuration
//public class SecurityConfig {
//	
//	@PostConstruct
//	public void init() {
//	    System.out.println("SECURITY CONFIG LOADED");
//	}
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            // Disable CSRF for APIs
//            .csrf(csrf -> csrf.disable())
//
//            // Allow public access to /auth/**
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/auth/**").permitAll()
//                .anyRequest().authenticated()
//            )
//
//            // Enable HTTP Basic authentication (new syntax)
//            .httpBasic(basic -> {});  // empty customizer
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}

package com.authenication.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http

                .csrf(csrf -> csrf.disable())

                .httpBasic(httpBasic -> httpBasic.disable())

                .formLogin(form -> form.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/auth/**")
                        .permitAll()

                        .anyRequest()
                        .authenticated()
                );

        return http.build();
    }
}