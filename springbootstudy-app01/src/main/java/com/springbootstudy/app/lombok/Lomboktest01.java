package com.springbootstudy.app.lombok;

public class Lomboktest01 {

	public static void main(String[] args) {
		Member01 m1 = new Member01();
		m1.setAge(20);
		m1.setName("홍길동");
		m1.setGender("남");
		
		System.out.println(m1);
		System.out.println(m1.getName());
	}
}