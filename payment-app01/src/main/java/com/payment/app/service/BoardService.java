package com.payment.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.payment.app.domain.Board;
import com.payment.app.repository.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	public List<Board> boardList() {
		log.info("BoardService: boardList()");
		return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "no"));
	}
}
