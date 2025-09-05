package com.codeoncewidakash.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.codeoncewidakash.dto.UserInfoDTO;
import com.codeoncewidakash.entity.RoleInfo;
import com.codeoncewidakash.entity.UserInfo;
import com.codeoncewidakash.exception.RoleNotExistException;
import com.codeoncewidakash.exception.UserNotExistException;
import com.codeoncewidakash.exception.UserNotFoundException;
import com.codeoncewidakash.repo.RoleInfoRepo;
import com.codeoncewidakash.repo.UserInfoRepo;
import com.codeoncewidakash.service.IUserInfoService;

@Service
public class UserInfoServiceImpl implements IUserInfoService, UserDetailsService {

	private UserInfoRepo userInfoRepo;
	private RoleInfoRepo roleInfoRepo;
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserInfoServiceImpl(UserInfoRepo userInfoRepo, RoleInfoRepo roleInfoRepo, BCryptPasswordEncoder passwordEncoder) {
		this.userInfoRepo = userInfoRepo;
		this.roleInfoRepo = roleInfoRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	public String createUser(UserInfoDTO userInfoDto) {
		
		// Get roles by role id's
		List<RoleInfo> roles = roleInfoRepo.findAllById(userInfoDto.roleIds());
		
		UserInfo userInfo = UserInfo.builder()
		.firstName(userInfoDto.firstName())
		.lastName(userInfoDto.lastName())
		.username(userInfoDto.username())
		.password(passwordEncoder.encode(userInfoDto.password()))
		.mobileNum(userInfoDto.mobile())
		.isActive(Boolean.FALSE)
		.build();
		
		if(roles.isEmpty())
			throw new RoleNotExistException("Role with ids : '"+userInfoDto.roleIds()+"' are not exist!");
		else 
			roles.forEach(role -> userInfo.getRoles().add(role));
		
		UserInfo registerdUser = userInfoRepo.save(userInfo);
		
		return "User created successfully with user id: '"+registerdUser.getId()+"'";
	}

	public String activateUser(Long userId) {
		UserInfo userInfo = userInfoRepo.findById(userId).orElseThrow(() -> new UserNotExistException("User with id '"+userId+"' not exist!"));
		userInfo.setIsActive(Boolean.TRUE);
		userInfoRepo.save(userInfo);
		return "User is activated successfully!";
	}

	public String deactivateUser(Long userId) {
		UserInfo userInfo = userInfoRepo.findById(userId).orElseThrow(() -> new UserNotExistException("User with id '"+userId+"' not exist!"));
		userInfo.setIsActive(Boolean.FALSE);
		userInfoRepo.save(userInfo);
		return "User is de-activated successfully!";
	}
	
	@Override
	public UserInfo getUserByUername(String username) {
		return userInfoRepo.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username + "NOT FOUND!"));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo user = getUserByUername(username);
		
		//get roles as GrantedAuthority
		List<SimpleGrantedAuthority> grantedRoles = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
		//return spring security supplied user object 
		return new User(user.getUsername(), user.getPassword(), grantedRoles);
	}
}
