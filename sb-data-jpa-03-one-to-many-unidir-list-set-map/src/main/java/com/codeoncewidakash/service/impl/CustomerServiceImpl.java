package com.codeoncewidakash.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codeoncewidakash.dto.CustomerRequestDTO;
import com.codeoncewidakash.dto.CustomerResponseDTO;
import com.codeoncewidakash.dto.CustomerWithRemovedWishlistItemResponseDto;
import com.codeoncewidakash.entity.Customer;
import com.codeoncewidakash.entity.WishlistItem;
import com.codeoncewidakash.exception.CustomerNotFoundException;
import com.codeoncewidakash.exception.WishlistItemNotFoundException;
import com.codeoncewidakash.repo.CustomerRepo;
import com.codeoncewidakash.repo.WishlistItemRepo;
import com.codeoncewidakash.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {

	private final CustomerRepo customerRepo;
	
	private final WishlistItemRepo wishlistItemRepo;
	
	public CustomerServiceImpl(CustomerRepo customerRepo, WishlistItemRepo wishlistItemRepo) {
		this.customerRepo = customerRepo;
		this.wishlistItemRepo = wishlistItemRepo;
	}
	
	@Override
	public String registerCustomer(CustomerRequestDTO customerReqDto) {
		//1. create customer entity class object
		Customer customerEntity = Customer.builder()
		.customerName(customerReqDto.customerName())
		.customerEmail(customerReqDto.customerEmail())
		.customerAddrs(customerReqDto.customerAddrs())
		.customerMobileNum(customerReqDto.customerMobileNum())
		.isActive(true)
		.build();
		
		//2. save customer entity
		Customer saved = customerRepo.save(customerEntity);
		
		return new StringBuilder("Customer registered successfully with id: ").append(saved.getId()).toString();
	}
	
	private Customer getCustomerDetailsById(Long customerId) {
		return customerRepo.findById(customerId).orElseThrow(()-> new CustomerNotFoundException("CUSTOMER WITH ID '"+customerId+"' NOT FOUND"));
	}
	
	@Override
	public CustomerResponseDTO getCustomer(Long customerId) {
		 Customer customer = getCustomerDetailsById(customerId);
		 return new CustomerResponseDTO(customer.getId(), customer.getCustomerName(), customer.getCustomerEmail(), customer.getCustomerAddrs(), customer.getCustomerMobileNum(), customer.getIsActive(), customer.getCreatedOn(), customer.getUpdatedOn(), customer.getOrders(), customer.getWishlistItems(), customer.getCartItems());
	}
	
	@Override
	public CustomerWithRemovedWishlistItemResponseDto removeWishlistItem(Long cusomerId, Long wishlistItemId) {
		Customer customer = getCustomerDetailsById(cusomerId);
		
		WishlistItem wishlistItem = wishlistItemRepo.findById(wishlistItemId).orElseThrow(() -> new WishlistItemNotFoundException("WISHLIST ITEM WITH ID '"+wishlistItemId+"' NOT FOUND"));
		
		customer.getWishlistItems().remove(wishlistItem);
		
		Customer savedCustomer = customerRepo.save(customer);
		
		return new CustomerWithRemovedWishlistItemResponseDto(savedCustomer.getId(), savedCustomer.getCustomerName(), savedCustomer.getCustomerEmail(), savedCustomer.getCustomerAddrs(), savedCustomer.getCustomerMobileNum(), savedCustomer.getWishlistItems());
	}

}
