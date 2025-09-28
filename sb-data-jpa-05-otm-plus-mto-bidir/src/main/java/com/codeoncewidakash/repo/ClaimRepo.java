package com.codeoncewidakash.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeoncewidakash.entity.Claim;

public interface ClaimRepo extends JpaRepository<Claim, Long> {

}
