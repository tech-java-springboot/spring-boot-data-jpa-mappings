package com.codeoncewidakash.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.codeoncewidakash.filter.SecurityFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private UserDetailsService userDetailsService;
	
	private BCryptPasswordEncoder passwordEncoder;
	
	private AuthenticationEntryPoint authenticationEntryPoint;
	
	private SecurityFilter securityFilter;
	
	
	public SecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder passwordEncoder, AuthenticationEntryPoint authenticationEntryPoint, SecurityFilter securityFilter) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.securityFilter = securityFilter;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}
	
	@Bean
	public SecurityFilterChain configureAuth(HttpSecurity http) throws Exception{
		return http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers(
						"/api/v1/usermgnt/user/login", 
						"/api/v1/usermgnt/user/create").permitAll()
				.requestMatchers("/api/v1/usermgnt/role/**").hasAuthority("ADMIN")
				.requestMatchers("/api/v1/usermgnt/user/**").hasAuthority("ADMIN")
				.anyRequest().authenticated())
				.exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint))
				.sessionManagement(ses -> ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
		
	}
}
