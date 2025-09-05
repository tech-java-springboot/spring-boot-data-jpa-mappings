package com.codeoncewidakash.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.codeoncewidakash.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	private JwtUtil jwtUtil;
	private UserDetailsService userDetailsService;

	public SecurityFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response, 
									FilterChain filterChain) throws ServletException, IOException {
		

		// 1. skip JWT validation for public end points.
		String path = request.getRequestURI();
		
		if (path.equals("/api/v1/usermgnt/user/login")
                || path.equals("/api/v1/usermgnt/user/create")) {
            filterChain.doFilter(request, response);
            return;
        }
		
		//1. Read token from request header
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String username = null;
		
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // strip "Bearer "
            username = jwtUtil.getUsername(token);
        }
		
		
		
		if(token != null) {
			
			//2. validate and read subject from token
			String usrName = jwtUtil.getUsername(token);
			
			//3. check user details
			if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				
				//4. load user from DB
				UserDetails userDetails = userDetailsService.loadUserByUsername(usrName);
				
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		filterChain.doFilter(request, response);
	}

}
