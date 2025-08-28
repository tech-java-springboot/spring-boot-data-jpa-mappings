package com.codeoncewidakash.service.impl;

import org.springframework.stereotype.Service;

import com.codeoncewidakash.dto.CartItemDTO;
import com.codeoncewidakash.entity.CartItem;
import com.codeoncewidakash.entity.Customer;
import com.codeoncewidakash.exception.CustomerNotFoundException;
import com.codeoncewidakash.repo.CartItemRepo;
import com.codeoncewidakash.repo.CustomerRepo;
import com.codeoncewidakash.service.ICartItemService;

import jakarta.transaction.Transactional;

@Service
public class CartItemServiceImpl implements ICartItemService {

	private CartItemRepo cartItemRepo;
	
	private CustomerRepo customerRepo;
	
	public CartItemServiceImpl(CartItemRepo cartItemRepo, CustomerRepo customerRepo) {
		this.cartItemRepo = cartItemRepo;
		this.customerRepo = customerRepo;
	}
	
	@Transactional
	public String addItemToCart(Long customerId, CartItemDTO cartItemDTO) {
		//1. find customer by id
		Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("CUSTOMER NOT FOUND WITH ID: "+customerId));
		
		//2. create and set valued to cart item entity class object
		CartItem cartItem = CartItem.builder()
		.productName(cartItemDTO.productName())
		.quantity(cartItemDTO.quantity())
		.unitPrice(cartItemDTO.unitPrice())
		.build();
		
		//3. save cartItem
		CartItem savedCartItem = cartItemRepo.save(cartItem);
		
		//4. set cartItem to customer object
		customer.getCartItems().put(savedCartItem.getProductName(), savedCartItem);
		
		//5. save customer
		customerRepo.save(customer);
		
		return "ITEM WITH NAME '"+savedCartItem.getProductName()+"' IS ADDED INTO CART";
	}

}
