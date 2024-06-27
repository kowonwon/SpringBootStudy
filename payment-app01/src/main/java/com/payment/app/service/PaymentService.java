package com.payment.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.app.domain.Payment;
import com.payment.app.dto.PaymentRequest;
import com.payment.app.repository.PaymentRepository;

@Service
public class PaymentService {
	@Autowired
  private PaymentRepository paymentRepository;

  public void savePayment(PaymentRequest paymentRequest) {
      Payment payment = new Payment();
      payment.setTitle(paymentRequest.getTitle());
      payment.setPrice(paymentRequest.getPrice());
      paymentRepository.save(payment);
  }
}
