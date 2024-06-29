package com.payment.app.service;

import org.springframework.stereotype.Service;

import com.payment.app.domain.Order;

@Service
public class OrderService {
	public Order findById(String orderId) {
		return new Order();
	}
}
