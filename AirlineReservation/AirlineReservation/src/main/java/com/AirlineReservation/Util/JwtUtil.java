package com.AirlineReservation.Util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	//Utility class for JWT operations
	//Secret key for Jwt signing
	//MUST have a size >= 256 bits
	public static final String SECERT = "mysecretkeymysecretkeymysecretkey12345";
	
	//JWT expiration time
	private static final long EXPIRATION = 1000 * 60 * 60 * 24; //token expiry time
	
	//Creates signing key
	private Key getSignKey() {
		return Keys.hmacShaKeyFor(SECERT.getBytes(StandardCharsets.UTF_8));
	}
	
	//GENERATE JWT TOKEN
	public String generateToken(String email) {
		
		return Jwts.builder().setSubject(email)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
				.signWith(getSignKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	//Extract email from token
	public String extractEmail(String token) {
		
		return Jwts.parserBuilder()
				.setSigningKey(getSignKey())
				.setAllowedClockSkewSeconds(60)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}

	//Validate JWT token
	public boolean validateToken(String token, String email) {
		
		String extractedEmail = extractEmail(token);
		
		return extractedEmail.equals(email);
	}
	
	/* What happening here
	 * JWT contains - UserEmail, creation time, expriation time
	 */
}
