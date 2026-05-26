package com.AirlineReservation.Util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

// utility class for JWT operations
@Component
public class JwtUtil {
	
	//secret key for Jwt signing
	public static final String SECERT = "mysecretkeymysecretkeymysecretkey12345"; //MUST have a size >= 256 bits
	
	//JWT expiration time
	private static final long EXPIRATION = 1000 * 60 * 60;
	
	//creates signing key
	private Key getSignKey() {
		return Keys.hmacShaKeyFor(SECERT.getBytes(StandardCharsets.UTF_8));
	}
	
	//GENERATE JWT TOKEN
	public String generateToken(String email) {
		
		return Jwts.builder().setSubject(email)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
				.signWith(getSignKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	//Extract email from token
	public String extractEmail(String token) {
		
		return Jwts.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}

	//validate JWT token
	public boolean validateToken(String token, String email) {
		
		String extractedEmail = extractEmail(token);
		
		return extractedEmail.equals(email);
	}
	
	/* What happening here
	 * JWT contains - UserEmail, creation time, expriation time
	 */
}
