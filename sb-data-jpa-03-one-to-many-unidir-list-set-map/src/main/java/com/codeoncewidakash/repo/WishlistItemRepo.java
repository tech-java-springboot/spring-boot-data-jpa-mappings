package com.codeoncewidakash.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeoncewidakash.entity.WishlistItem;

public interface WishlistItemRepo extends JpaRepository<WishlistItem, Long> {

}
