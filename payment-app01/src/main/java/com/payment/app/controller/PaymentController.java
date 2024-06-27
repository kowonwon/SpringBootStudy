package com.payment.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.payment.app.dto.PaymentRequest;
import com.payment.app.service.PaymentService;

@RestController
public class PaymentController {
	@Autowired
  private PaymentService paymentService;

  @PostMapping("/savePayment")
  public ResponseEntity<String> savePayment(@RequestBody PaymentRequest paymentRequest) {
      paymentService.savePayment(paymentRequest);
      return ResponseEntity.ok("결제 성공");
  }
}
