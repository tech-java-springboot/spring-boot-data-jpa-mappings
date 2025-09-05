package com.codeoncewidakash.service.impl;

import org.springframework.stereotype.Service;

import com.codeoncewidakash.dto.RoleInfoDTO;
import com.codeoncewidakash.entity.RoleInfo;
import com.codeoncewidakash.repo.RoleInfoRepo;
import com.codeoncewidakash.service.IRoleInfoService;

@Service
public class RoleInfoServiceImpl implements IRoleInfoService {

	private RoleInfoRepo roleInfoRepo;
	
	public RoleInfoServiceImpl(RoleInfoRepo roleInfoRepo) {
		this.roleInfoRepo = roleInfoRepo;
	}
	
	@Override
	public String createRole(RoleInfoDTO roleInfoDto) {
		RoleInfo roleInfo = RoleInfo.builder()
		.role(roleInfoDto.role())
		.desc(roleInfoDto.desc())
		.build();
		
		RoleInfo registeredRole = roleInfoRepo.save(roleInfo);
		return "Role is created with Id : '"+registeredRole.getId()+"'";
	}

	

}
