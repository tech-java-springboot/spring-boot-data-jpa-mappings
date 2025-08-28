package com.codeoncewidakash.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeoncewidakash.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

}
