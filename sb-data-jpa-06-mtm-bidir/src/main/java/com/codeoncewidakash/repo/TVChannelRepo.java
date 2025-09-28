package com.codeoncewidakash.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeoncewidakash.entity.TVChannel;

public interface TVChannelRepo extends JpaRepository<TVChannel, Long> {
	Optional<TVChannel> findByName(String name);
}
