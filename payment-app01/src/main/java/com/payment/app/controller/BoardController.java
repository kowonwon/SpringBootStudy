package com.payment.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.payment.app.service.BoardService;
import com.payment.app.service.LectureService;

import lombok.extern.slf4j.Slf4j;

@Controller
public class BoardController {
	@Autowired
	private LectureService lectureService;
	
	@GetMapping({"/", "/boardList"})
	public String boardList(Model model) {
		model.addAttribute("lList", lectureService.lectureList());
		return "main";
	}
}