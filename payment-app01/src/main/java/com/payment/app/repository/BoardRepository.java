package com.payment.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payment.app.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	
}
