package com.springbootstudy.app.lombok;

public class Lomboktest02 {
	public static void main(String[] args) {
		Member02 m1 = new Member02();
		Member02 m2 = new Member02("홍길동");
		Member02 m3 = new Member02("이순신", "남", 15);
		System.out.println(m1);
		System.out.println(m2);
		System.out.println(m3);
	}
}