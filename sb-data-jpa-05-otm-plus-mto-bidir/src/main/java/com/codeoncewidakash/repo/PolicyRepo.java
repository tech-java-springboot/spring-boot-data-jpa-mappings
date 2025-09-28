package com.codeoncewidakash.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeoncewidakash.entity.Policy;

public interface PolicyRepo extends JpaRepository<Policy, Long>{
	
}
