package com.systems.order.service;

import java.util.List;

import com.systems.order.dto.OrderRequest;
import com.systems.order.dto.OrderResponse;

public interface OrderService {
	
	OrderResponse createOrder(OrderRequest orderRequest) throws Exception;
	
	List<OrderResponse> getAllOrders();

}
