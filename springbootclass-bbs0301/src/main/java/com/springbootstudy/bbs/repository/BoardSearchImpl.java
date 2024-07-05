package com.springbootstudy.bbs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.springbootstudy.bbs.domain.Board;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

	public BoardSearchImpl() {
		super(Board.class);
	}

	@Override
	public Page<Board> search1(Pageable pageable) {
		return null;
	}

	@Override
	public Page<Board> searchAll(Pageable pageable, String type, String keyword) {
		return null;
	}

	@Override
	public Page<Board> searchAll(Pageable pageable, String[] types, String keyword) {
		return null;
	}
	
}
