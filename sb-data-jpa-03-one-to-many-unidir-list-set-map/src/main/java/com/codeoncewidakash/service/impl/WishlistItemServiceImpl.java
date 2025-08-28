package com.codeoncewidakash.service.impl;

import org.springframework.stereotype.Service;

import com.codeoncewidakash.dto.WishlistItemDTO;
import com.codeoncewidakash.entity.Customer;
import com.codeoncewidakash.entity.WishlistItem;
import com.codeoncewidakash.exception.CustomerNotFoundException;
import com.codeoncewidakash.repo.CustomerRepo;
import com.codeoncewidakash.repo.WishlistItemRepo;
import com.codeoncewidakash.service.IWishlistItemService;

import jakarta.transaction.Transactional;

@Service
public class WishlistItemServiceImpl implements IWishlistItemService {

	private WishlistItemRepo wishlistItemRepo;
	
	private CustomerRepo customerRepo;
	
	public WishlistItemServiceImpl(WishlistItemRepo wishlistItemRepo, CustomerRepo customerRepo) {
		this.wishlistItemRepo = wishlistItemRepo;
		this.customerRepo = customerRepo;
	}
	
	@Transactional
	public String addItemsIntoWishlist(Long customerId, WishlistItemDTO wishlistItemDTO) {
		//1. find customer by id
		Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("CUSTOMER NOT FOUND WITH ID "+customerId));
		
		//2. create wish list entity class object
		WishlistItem wishlistItem = WishlistItem.builder()
		.productCode(wishlistItemDTO.productCode())
		.productName(wishlistItemDTO.productName())
		.build();
		
		//3. save wish list object
		WishlistItem savedWishlistItem = wishlistItemRepo.save(wishlistItem);
		
		//3. associate the wishlistItem object to Customer object
		customer.getWishlistItems().add(savedWishlistItem);
		customerRepo.save(customer);
		
		return "WISHLIST IS CREATED WITH ID "+savedWishlistItem.getId();
	}

}
