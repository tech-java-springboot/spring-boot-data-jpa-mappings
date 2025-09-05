package com.codeoncewidakash.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeoncewidakash.entity.UserInfo;

public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {
	public Optional<UserInfo> findByUsername(String username);
}
