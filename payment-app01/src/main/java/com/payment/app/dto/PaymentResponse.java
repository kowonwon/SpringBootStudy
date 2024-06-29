package com.payment.app.dto;

public class PaymentResponse {
	private Amount amount;
	private String status;
	
	public Amount getAmount() {
		return amount;
	}
	public void setAmount(Amount amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public static class Amount {
		private Integer total;

		public Integer getTotal() {
			return total;
		}

		public void setTotal(Integer total) {
			this.total = total;
		}
	}
}
