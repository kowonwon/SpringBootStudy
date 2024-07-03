package com.payment.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.payment.app.domain.Order;
import com.payment.app.dto.PaymentRequest;
import com.payment.app.dto.PaymentResponse;
import com.payment.app.service.OrderService;

@RestController
@RequestMapping("/patment")
public class PaymentController {
	
	@Value("${portone.api.secret}")
	private String portoneApiSecret;
	
	private final RestTemplate restTemplate;
	private final OrderService orderService;
	
	public PaymentController(RestTemplate restTemplate, OrderService orderservice) {
		this.restTemplate = restTemplate;
		this.orderService = orderservice;
	}
	
	@PostMapping("/complete")
	public ResponseEntity<?> completePayment(@RequestBody PaymentRequest paymentRequest) {
		try {
			String paymentId = paymentRequest.getPaymentId();
			String orderId = paymentRequest.getOrderId();
			
			// 1. 포트원 결제내역 단건조회 API 호출
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "PortOne" + portoneApiSecret);
			HttpEntity<String> entity = new HttpEntity<>(headers);
			
			ResponseEntity<PaymentResponse> paymentResponse =
					restTemplate.exchange("https://api.portone.io/payments/" + paymentId,
					HttpMethod.GET, entity, PaymentResponse.class);
			
			PaymentResponse payment = paymentResponse.getBody();
			if (payment == null) {
				throw new Exception("payment not found");
			}
			
			// 2. 고객사 내부 주문 데이터의 가격과 실제 지불된 금액 비교
			Order order = orderService.findById(orderId);
			if (order.getAmount().equals(payment.getAmount().getTotal())) {
				switch (payment.getStatus()) {
					case "VIRTUAL_ACCOUNT_ISSUED":
						break;
					case "PAID":
						break;
				}
			} else {
				System.out.println("위/변조 의심됨");
				throw new Exception("Payment amount mismatch");
			}
			
			return ResponseEntity.ok("Payment processed successfully");
		}catch (Exception e) {
			return ResponseEntity.status(400).body(e.getMessage());
		}
	}
}