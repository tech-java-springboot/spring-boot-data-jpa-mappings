package com.codeoncewidakash.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.codeoncewidakash.exception.AccessJwtTokenExpiredException;
import com.codeoncewidakash.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	private JwtUtil jwtUtil;
	private UserDetailsService userDetailsService;
	private final AuthenticationEntryPoint authenticationEntryPoint;

	public SecurityFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService,
			AuthenticationEntryPoint authenticationEntryPoint) {
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
		this.authenticationEntryPoint = authenticationEntryPoint;
	}

	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain) throws ServletException, IOException {

		try {

			// 1. Read token from request header
			String authHeader = request.getHeader("Authorization");
			String token = null;
			String username = null;
			
			// 2. Strip Bearer from token
			if (authHeader != null && authHeader.startsWith("Bearer ")) {
				token = authHeader.substring(7); // strip "Bearer "
			}

			// 3. Access token expiration check
			if (token != null && jwtUtil.isTokenExpired(token)) {
				// Clear any existing authentication from the security context to ensure the expired token does not grant access.
				SecurityContextHolder.clearContext(); 
				throw new AccessJwtTokenExpiredException("Your JWT token is expired!");
			}

			if (token != null) {

				// 4. validate and read subject from token
				username = jwtUtil.getUsername(token);

				// 6. check user details
				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

					// 7. load user from DB
					UserDetails userDetails = userDetailsService.loadUserByUsername(username);
					
					// 8. Create an authentication object containing the user’s identity and granted authorities.
					// That means this user is now authenticated, trust them for the rest of the request
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					
					// 9. Attach additional request details (IP, session ID) to the authentication object
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					
					// 10. Store the authentication object in the SecurityContext so Spring Security treats the user as authenticated
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}

			filterChain.doFilter(request, response);

		} catch (AccessJwtTokenExpiredException jtee) {
			authenticationEntryPoint.commence(request, response, jtee);
		}
	}

}
