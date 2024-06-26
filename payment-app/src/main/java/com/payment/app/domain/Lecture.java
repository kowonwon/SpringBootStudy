package com.payment.app.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lecture {
	private int lectureId;
	private String lectureTitle;
	private String lectureDesc;
	private String instructorId;
	private int price;
}