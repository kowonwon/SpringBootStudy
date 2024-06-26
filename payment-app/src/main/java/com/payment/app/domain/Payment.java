package com.payment.app.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
	private String payCode;
	private Timestamp payDate;
	private String payWay;
	private int price;
	private String studentId;
	private int lectureId;
}