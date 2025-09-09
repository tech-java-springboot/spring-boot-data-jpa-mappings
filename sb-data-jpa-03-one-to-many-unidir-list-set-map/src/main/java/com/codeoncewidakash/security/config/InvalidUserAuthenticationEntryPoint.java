package com.codeoncewidakash.security.config;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.codeoncewidakash.exception.ExpiredJwtTokenException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class InvalidUserAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, 
						HttpServletResponse response,
						AuthenticationException authException) throws IOException, ServletException {
		
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		//response.getOutputStream().println("{ \"error\": \"" + authException.getMessage() + ", Please \" }");
		
		String message;

	    // Bad user name / password during login
	    if (authException instanceof BadCredentialsException) {
	        message = "Invalid username or password!";
	    }
	    // Trying to access a protected end point without authentication
	    else if (authException instanceof InsufficientAuthenticationException) {
	        message = "Authentication is required to access this resource!";
	    }
	    else if(authException instanceof ExpiredJwtTokenException) {
	    	message = "Your JWT token is expired!";
	    }
	    // Default for any other authentication error
	    else {
	        message = "Authentication failed: " + authException.getMessage();
	    }

	    String jsonResponse = String.format("{ \"error\": \"%s\" }", message);
	    response.getOutputStream().println(jsonResponse);
	}

}
