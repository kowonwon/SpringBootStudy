package com.payment.app.dto;

// ajax가 보내는 정보를 받는 용도
public class PaymentRequest {
	private String title;
  private String price;

  public PaymentRequest() {
  }

  public String getTitle() {
      return title;
  }

  public void setTitle(String title) {
      this.title = title;
  }

  public String getPrice() {
      return price;
  }

  public void setPrice(String price) {
      this.price = price;
  }
}
