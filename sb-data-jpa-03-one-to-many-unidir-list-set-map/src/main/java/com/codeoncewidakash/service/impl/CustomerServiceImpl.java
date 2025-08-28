package com.codeoncewidakash.service.impl;

import org.springframework.stereotype.Service;

import com.codeoncewidakash.dto.CustomerDTO;
import com.codeoncewidakash.entity.Customer;
import com.codeoncewidakash.repo.CustomerRepo;
import com.codeoncewidakash.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {

	private final CustomerRepo customerRepo;
	
	public CustomerServiceImpl(CustomerRepo customerRepo) {
		this.customerRepo = customerRepo;
	}
	
	@Override
	public String registerCustomer(CustomerDTO customerDTO) {
		//1. create customer entity class object
		Customer customerEntity = Customer.builder()
		.customerName(customerDTO.customerName())
		.customerEmail(customerDTO.customerEmail())
		.customerAddrs(customerDTO.customerAddrs())
		.customerMobileNum(customerDTO.customerMobileNum())
		.isActive(true)
		.build();
		
		//2. save customer entity
		Customer saved = customerRepo.save(customerEntity);
		
		return new StringBuilder("Customer registered successfully with id: ").append(saved.getId()).toString();
	}

}
