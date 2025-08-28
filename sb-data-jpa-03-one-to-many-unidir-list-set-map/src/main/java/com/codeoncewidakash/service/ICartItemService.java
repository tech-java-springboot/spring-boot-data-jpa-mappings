package com.codeoncewidakash.service;

import com.codeoncewidakash.dto.CartItemDTO;

public interface ICartItemService {
	public String addItemToCart(Long customerId, CartItemDTO cartItemDTO);
}
