package com.codeoncewidakash.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeoncewidakash.dto.CartItemDTO;
import com.codeoncewidakash.dto.CustomerRequestDTO;
import com.codeoncewidakash.dto.CustomerResponseDTO;
import com.codeoncewidakash.dto.CustomerWithRemovedWishlistItemResponseDto;
import com.codeoncewidakash.dto.OrderDTO;
import com.codeoncewidakash.dto.WishlistItemDTO;
import com.codeoncewidakash.service.ICartItemService;
import com.codeoncewidakash.service.ICustomerService;
import com.codeoncewidakash.service.IOrderService;
import com.codeoncewidakash.service.IWishlistItemService;

@RestController
@RequestMapping("/api/v1")
public class CustomerRestController {
	
	private ICustomerService customerService;
	
	private IOrderService orderService;
	
	private IWishlistItemService wishlistItemService;
	
	private ICartItemService cartItemService;
	
	public CustomerRestController(ICustomerService customerService, IOrderService orderService, IWishlistItemService wishlistItemService, ICartItemService cartItemService) {
		this.customerService = customerService;
		this.orderService = orderService;
		this.wishlistItemService = wishlistItemService;
		this.cartItemService = cartItemService;
	}
	
	@PostMapping("/customer/register")
	public ResponseEntity<String> registerCustomer(@RequestBody CustomerRequestDTO customerReqDto){
		String response = customerService.registerCustomer(customerReqDto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/order/create/{custId}")
	public ResponseEntity<String> createOrder(@PathVariable(name = "custId") Long customerId, @RequestBody OrderDTO orderDto){
		String response = orderService.createOrder(customerId, orderDto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/wishlist/added/{custId}")
	public ResponseEntity<String> addItemsIntoWishlist(@PathVariable(name = "custId") Long customerId, @RequestBody WishlistItemDTO wishlistItemDTO){
		String response = wishlistItemService.addItemsIntoWishlist(customerId, wishlistItemDTO);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/cartitem/added/{custId}")
	public ResponseEntity<String> addItemsIntoCart(@PathVariable(name = "custId") Long customerId, @RequestBody CartItemDTO cartItemDTO){
		String response = cartItemService.addItemToCart(customerId, cartItemDTO);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/customer/get/{custId}")
	public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable(name = "custId") Long customerId){
		CustomerResponseDTO customerResponse = customerService.getCustomer(customerId);
		return new ResponseEntity<>(customerResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/customer/wishlist/remove/{custId}/{wishlistId}")
	public ResponseEntity<CustomerWithRemovedWishlistItemResponseDto> removeItemsFromCustomerWishlist(@PathVariable(name = "custId") Long customerId, @PathVariable(name = "wishlistId") Long wishlistId){
		CustomerWithRemovedWishlistItemResponseDto responseDto = customerService.removeWishlistItem(customerId, wishlistId);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}	
}
