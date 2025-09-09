package com.codeoncewidakash.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	@Value("${app.secret}")
	private String secret;
	
	// Access token 15m
	private static final long ACCESS_TOKEN_VALIDITY = TimeUnit.MINUTES.toMillis(3);
	
	// Refresh token 7 days
	private static final long REFRESH_TOKEN_VALIDITY = TimeUnit.DAYS.toMillis(7);
	
	public boolean isTokenExpired(String token) {
		try {
			Date tokenExpiration = getClaims(token).getExpiration();
			return tokenExpiration.before(new Date());
		} catch (ExpiredJwtException e) {
			return true;
		}
	}
	
	public String getUsername(String token) {
		return getClaims(token).getSubject();
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
	
	public String generateAccessToken(String subject) {
		Map<String, Object> claims = new HashMap<>();
		return generateToken(claims, subject, ACCESS_TOKEN_VALIDITY);
	}
	
	public String generateRefreshToken(String subject) {
		Map<String, Object> claims = new HashMap<>();
		return generateToken(claims, subject, REFRESH_TOKEN_VALIDITY);
	}
	
	private String generateToken(Map<String, Object> claims, String subject, long valididty) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuer("ECOMAPP")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + valididty))
				//.signWith(SignatureAlgorithm.HS256, secret.getBytes()) - deprecated use below implementation
				.signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
				.compact();
	}
}
