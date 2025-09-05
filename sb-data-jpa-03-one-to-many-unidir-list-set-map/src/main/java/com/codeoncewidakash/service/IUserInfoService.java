package com.codeoncewidakash.service;

import com.codeoncewidakash.dto.UserInfoDTO;
import com.codeoncewidakash.entity.UserInfo;

public interface IUserInfoService {
	public String createUser(UserInfoDTO userInfoDto);
	public String activateUser(Long userId);
	public String deactivateUser(Long userId);
	public UserInfo getUserByUername(String username);
}
