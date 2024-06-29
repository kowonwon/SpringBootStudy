package com.payment.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PaymentApp01Application {

	public static void main(String[] args) {
		SpringApplication.run(PaymentApp01Application.class, args);
	}

	@Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
      return builder.build();
  }
}
