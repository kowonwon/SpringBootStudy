package com.springbootstudy.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springbootstudy.app.domain.Member;

@RestController
public class SecondController {
	
	@PostMapping("/members") 
	public Member addMember(Member member) {
		return member;
	}
	
	@GetMapping("/members/{id}")
	public Map<String, Object> getMember(@PathVariable("id") String id) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("name", "홍길동");
		map.put("age", 30);
		
		return map;
	}
	
	
	@GetMapping("/hello")
	public Map<String, String> hello(@RequestParam(value="name") String name) {
		
		Map<String, String> map = new HashMap<>();
		map.put("title", "Second Controller");
		map.put("greeting", "안녕 하세요 " + name + "님~" );
		
		return map;
	}
	
	
	@GetMapping("/")
	public String main() {
		
		return "{\"main\":\"여기는 메인\"}";
	}
}
