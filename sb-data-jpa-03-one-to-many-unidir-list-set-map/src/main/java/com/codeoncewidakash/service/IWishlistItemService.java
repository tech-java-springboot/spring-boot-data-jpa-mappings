package com.codeoncewidakash.service;

import com.codeoncewidakash.dto.WishlistItemDTO;

public interface IWishlistItemService {
	public String addItemsIntoWishlist(Long customerId, WishlistItemDTO wishlistItemDTO);
}
