package com.codeoncewidakash.service.impl;

import org.springframework.stereotype.Service;

import com.codeoncewidakash.dto.OrderDTO;
import com.codeoncewidakash.entity.Customer;
import com.codeoncewidakash.entity.Order;
import com.codeoncewidakash.exception.CustomerNotFoundException;
import com.codeoncewidakash.repo.CustomerRepo;
import com.codeoncewidakash.repo.OrderRepo;
import com.codeoncewidakash.service.IOrderService;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements IOrderService {

	private OrderRepo orderRepo;
	private CustomerRepo customerRepo;
	
	public OrderServiceImpl(OrderRepo orderRepo, CustomerRepo customerRepo) {
		this.orderRepo = orderRepo;
		this.customerRepo = customerRepo;
	}
	
	@Transactional
	public String createOrder(Long customerId, OrderDTO orderDTO) {
		//1. find customer is there or not with provided id
		Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("CUSTOMER '"+customerId+"' NOT FOUND"));
		
		//2. create Order Entity class object
		Order order = Order.builder()
		.orderNumber(orderDTO.orderNumber())
		.productName(orderDTO.productName())
		.price(orderDTO.price())
		.orderStatus("Order Placed")
		.build();
		
		//3. save order
		Order savedOrder = orderRepo.save(order);
		
		//4. set order to customer
		customer.getOrders().add(savedOrder);
		
		//5. save customer and order
		customerRepo.save(customer);
		
		return "ORDER IS CREATED SUCCESSFULLY WITH ID: "+order.getId();
	}

}
