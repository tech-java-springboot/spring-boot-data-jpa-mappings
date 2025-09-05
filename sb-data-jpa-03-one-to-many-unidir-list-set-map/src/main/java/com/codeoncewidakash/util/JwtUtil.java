package com.codeoncewidakash.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	@Value("${app.secret}")
	private String secret;
	
	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}
	
	public String generateToken(String subject) {
		Map<String, Object> claims = new HashMap<>();
		return generateToken(claims, subject);
	}
	
	private Claims getClaims(String token) {
		return Jwts.
				//parser().setSigningKey(secret.getBytes()) // deprecated use below implementation
				parserBuilder()
				.setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	private String generateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuer("ECOMAPP")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15)))
				//.signWith(SignatureAlgorithm.HS256, secret.getBytes()) - deprecated use below implementation
				.signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
				.compact();
	}
}
