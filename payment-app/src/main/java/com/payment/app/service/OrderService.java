package com.payment.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.app.domain.Order;
import com.payment.app.mapper.OrderMapper;

@Service
public class OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	public Order findByOrderId(String orderId) {
		return orderMapper.findByOrderId(orderId);
	}
}
