package com.codeoncewidakash.service;

import com.codeoncewidakash.dto.CustomerRequestDTO;
import com.codeoncewidakash.dto.CustomerResponseDTO;
import com.codeoncewidakash.dto.CustomerWithRemovedWishlistItemResponseDto;

public interface ICustomerService {
	public String registerCustomer(CustomerRequestDTO customerDTO);
	public CustomerResponseDTO getCustomer(Long customerId);
	public CustomerWithRemovedWishlistItemResponseDto removeWishlistItem(Long cusomerId, Long wishlistItemId);
}
