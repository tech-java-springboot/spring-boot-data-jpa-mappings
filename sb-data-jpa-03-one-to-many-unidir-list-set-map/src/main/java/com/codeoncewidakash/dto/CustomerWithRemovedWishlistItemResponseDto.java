package com.codeoncewidakash.dto;

import java.util.Set;

import com.codeoncewidakash.entity.WishlistItem;

public record CustomerWithRemovedWishlistItemResponseDto(Long id,String customerName, String customerEmail, String customerAddrs, Long customerMobileNum, Set<WishlistItem> wishlistItems) {

}
