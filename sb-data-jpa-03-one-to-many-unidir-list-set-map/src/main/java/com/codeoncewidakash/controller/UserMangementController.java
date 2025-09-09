package com.codeoncewidakash.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeoncewidakash.dto.LoginRequest;
import com.codeoncewidakash.dto.LoginResponse;
import com.codeoncewidakash.dto.RoleInfoDTO;
import com.codeoncewidakash.dto.UserInfoDTO;
import com.codeoncewidakash.entity.UserInfo;
import com.codeoncewidakash.service.IRoleInfoService;
import com.codeoncewidakash.service.IUserInfoService;
import com.codeoncewidakash.util.JwtUtil;

@RestController
@RequestMapping("/api/v1/usermgnt")
public class UserMangementController {
	
	private IUserInfoService userInfoService;
	private IRoleInfoService roleInfoService;
	private AuthenticationManager authenticationManager;
	private JwtUtil jwtUtil;
	
	public UserMangementController(IUserInfoService userInfoService, IRoleInfoService roleInfoService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		this.userInfoService = userInfoService;
		this.roleInfoService = roleInfoService;
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}
	
	@PostMapping("/role/create")
	public ResponseEntity<String> createRole(@RequestBody RoleInfoDTO roleInfoDto){
		String roleResponse = roleInfoService.createRole(roleInfoDto);
		return new ResponseEntity<>(roleResponse, HttpStatus.CREATED);
	}
	
	@PostMapping("/user/create")
	public ResponseEntity<String> registerUser(@RequestBody UserInfoDTO userInfoDto){
		String userResponse = userInfoService.createUser(userInfoDto);
		return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
	}
	
	@GetMapping("/user/get/{username}")
	public ResponseEntity<UserInfo> getUserByUsername(@PathVariable(name = "username") String usrName){
		UserInfo usrResponse = userInfoService.getUserByUername(usrName);
		return ResponseEntity.ok(usrResponse);
	}
	
	@PostMapping("/user/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));	
		
		String accessToken = jwtUtil.generateAccessToken(loginRequest.username());
		String refreshToken = jwtUtil.generateRefreshToken(loginRequest.username());
		
		return new ResponseEntity<>(new LoginResponse(accessToken, refreshToken, "Token Generated Successfully!"), HttpStatus.OK);
	}
	
	@PostMapping("/user/refresh")
	public ResponseEntity<LoginResponse> refreshToken(@RequestBody Map<String, String> request){
		String refreshToken = request.get("refreshToken");
		
		if(refreshToken == null || jwtUtil.isTokenExpired(refreshToken)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new LoginResponse(null, null, "Invalid or expired refresh token"));
		}
		
		String username = jwtUtil.getUsername(refreshToken);
		String newAccessToken = jwtUtil.generateAccessToken(username);
		
		return ResponseEntity.ok(new LoginResponse(newAccessToken, null, "TOKEN REFRESHED"));
	}
}
