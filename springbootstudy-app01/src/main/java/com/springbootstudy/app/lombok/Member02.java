package com.springbootstudy.app.lombok;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor // 빈 생성자 만들어
@RequiredArgsConstructor // null이면 안되는 애들(필수)로 생성자 만들어
@AllArgsConstructor // 전체 받는 생성자 만들어
public class Member02 {
	
	@NonNull
	private String name;
	private String gender;
	private int age;
	//private final int grade;
}