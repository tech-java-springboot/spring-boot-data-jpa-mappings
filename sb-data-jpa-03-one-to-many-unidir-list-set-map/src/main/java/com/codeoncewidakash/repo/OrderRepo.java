package com.codeoncewidakash.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeoncewidakash.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {

}
