package com.codeoncewidakash.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.codeoncewidakash.entity.CartItem;
import com.codeoncewidakash.entity.Order;
import com.codeoncewidakash.entity.WishlistItem;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CustomerResponseDTO(Long id,String customerName, String customerEmail, String customerAddrs, Long customerMobileNum, Boolean isActive, LocalDate createdOn, LocalDate updatedOn, List<Order> orders, Set<WishlistItem> wishlistItems, Map<String, CartItem> cartItems) {

}
