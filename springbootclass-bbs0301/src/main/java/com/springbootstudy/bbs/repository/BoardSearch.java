package com.springbootstudy.bbs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springbootstudy.bbs.domain.Board;

public interface BoardSearch {
	Page<Board> search1(Pageable pageable);
	public Page<Board> searchAll(Pageable pageable, String type, String keyword);
	public Page<Board> searchAll(Pageable pageable, String types[], String keyword);
}
