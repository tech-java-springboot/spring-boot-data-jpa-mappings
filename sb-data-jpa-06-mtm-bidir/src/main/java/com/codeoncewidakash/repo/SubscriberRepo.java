package com.codeoncewidakash.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeoncewidakash.entity.Subscriber;
import java.util.Optional;


public interface SubscriberRepo extends JpaRepository<Subscriber, Long> {
	Optional<Subscriber> findByPhoneNumber(Long phoneNumber);
}
