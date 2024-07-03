package com.springbootstudy.bbs.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springbootstudy.bbs.domain.Board;
import com.springbootstudy.bbs.domain.BoardDTO;
import com.springbootstudy.bbs.service.BoardService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/delete")
	public String deleteBoard(HttpServletResponse response, PrintWriter out,
			RedirectAttributes reAttrs,
			@RequestParam("no") int no,
			@RequestParam("pass") String pass,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum) {
		
		BoardDTO board = boardService.getBoard(no, false);
		
		if(! board.getPass().equals(pass)) {
			response.setContentType("text/html; charset=utf-8");
			out.println("<script>");
			out.println("alert('비밀번호가 틀림');");
			out.println("history.back();");
			out.println("</script>");
			
			return null;
		}
		
		boardService.deleteBoard(no);
		reAttrs.addAttribute("pageNum", pageNum);
		return "redirect:boardList";
	}
	
	@PostMapping("/update")
	public String updateBoard(BoardDTO dto,
			HttpServletResponse response,
			PrintWriter out,
			RedirectAttributes reAttrs,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum) {
		
		BoardDTO board = boardService.getBoard(dto.getNo(), false);
		
		if(! dto.getPass().equals(board.getPass())) {
			response.setContentType("text/html; charset=utf-8");
			out.println("<script>");
			out.println("alert('비밀번호가 틀림');");
			out.println("history.back();");
			out.println("</script>");
			
			return null;
		}
		
		boardService.updateForm(dto);
		
		reAttrs.addAttribute("pageNum", pageNum);
		return "redirect:boardList";
	}
	
	@PostMapping("/updateForm")
	public String updateForm(Model model,
			HttpServletResponse response, PrintWriter out,
			@RequestParam("no") int no,
			@RequestParam("pass") String pass,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum) {
		
		// DB -> PASS
		BoardDTO dto = boardService.getBoard(no, false);
		if(! dto.getPass().equals(pass)) {
			response.setContentType("text/html; charset=utf-8");
			//PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 틀림');");
			out.println("history.back();");
			out.println("</script>");
			
			return null;
		}
		
		model.addAttribute("board", dto);
		model.addAttribute("pageNum", pageNum);
		return "views/updateForm";
	}
	
	@PostMapping("/addBoard")
	public String addBoard(BoardDTO board) {
		boardService.addBoard(board);
		return "redirect:boardList";
	}
	
	@GetMapping("/addBoard")
	public String addboard() {
		return "views/writeForm";
	}
	
	@GetMapping("/boardDetail")
	public String getBoard(Model model, @RequestParam("no") int no,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum) {
		model.addAttribute("board", boardService.getBoard(no, true));
		model.addAttribute("pageNum", pageNum);
		return "views/boardDetail";
	}
	
	@GetMapping({"/", "boardList"})
	public String boardList(Model model,
			@RequestParam(value="pageNum", defaultValue="0") int pageNum,
			@RequestParam(value="type", defaultValue="null") String type,
			@RequestParam(value="keyword", defaultValue="null")String keyword) {
		log.info("BoardController : boardList(Model model)");
		
		pageNum = pageNum == 0 ? 0 : pageNum -1;
		
		model.addAllAttributes(boardService.boardList(pageNum, type, keyword));
		
		return "views/boardList";
	}
}
