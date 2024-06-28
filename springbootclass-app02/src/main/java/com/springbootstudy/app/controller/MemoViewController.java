package com.springbootstudy.app.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.springbootstudy.app.service.MemoEntityService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemoViewController {
	
	private final MemoEntityService entityService;
	
	// thymeleaf 사용법
	@GetMapping("/thymeleaf1")
	public String thDefault01(Model model) {
		model.addAttribute("text1", "<h1>Hello Thymeleaf</h1>");
		model.addAttribute("memo", entityService.getMemo(1));
		model.addAttribute("today", LocalDate.now());
		return "th/default1";
	}
	@GetMapping("/thymeleaf2/{no}")
	public String thDefault02(Model model, @PathVariable(name="no") int no) {
		model.addAttribute("mList", entityService.memoList());
		return "th/default2";
	}
	@GetMapping("/thymeleaf3")
	public String thDefault03(Model model, @RequestParam("no") int no) {
		model.addAttribute("memo1", entityService.getMemo(no));
		model.addAttribute("score", 70);
		return "th/default3";
	}
	
	@GetMapping("/memoListJsp")
	public String memoList(Model model) {
		model.addAttribute("mList", entityService.memoList());
		return "main";
	}
}