package com.codeoncewidakash.service;

import com.codeoncewidakash.dto.OrderDTO;

public interface IOrderService {
	public String createOrder(Long customerId, OrderDTO orderDTO);
}
