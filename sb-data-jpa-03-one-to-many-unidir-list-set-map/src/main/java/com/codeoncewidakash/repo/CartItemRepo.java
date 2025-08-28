package com.codeoncewidakash.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeoncewidakash.entity.CartItem;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {

}
