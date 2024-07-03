package com.payment.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.payment.app.domain.Order;
import com.payment.app.service.OrderService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/complete")
	public ResponseEntity<String> completePaytment(@RequestBody Map<String, String> request) {
		String paymentId = request.get("paymentId");
		String orderId = request.get("orderId");
		
		try {
			Order order = orderService.findByOrderId(orderId);
			
			// 포트원 결제내역 단건조회 API 호출
			RestTemplate restTemplate = new RestTemplate();
			String url = "https://api.portone.io/payments/" + paymentId;
			String apiKey = "PortOne " + "sgfC0bhqj3uAAa6NQaHET4EMwUleCpTP4CCuKbasVOuIcGQDjBCIT4wsSIRyNsfqXFCKPaohQQ145W5s";
			ResponseEntity<Map> paymentResponse = restTemplate.getForEntity(url, Map.class, apiKey);
			
			if (! paymentResponse.getStatusCode().is2xxSuccessful()) {
				throw new RuntimeException("Failed to fetch payment details from PortOne");
			}
			
			Map payment = paymentResponse.getBody();
			String status = (String) payment.get("status");
			
			if ("PAID".equals(status)) {
				// 결제 완료 로직
			}else {
				throw new RuntimeException("payment status is not PAID");
			}
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.ok("payment success");
	}
	
}
