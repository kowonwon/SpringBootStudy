package com.springbootstudy.bbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootstudy.bbs.domain.Board;

//@Repository 자동설정되어있음.
public interface BoardRepository extends JpaRepository<Board, Integer> {
// List<Board> findAll()
// Optional<Board> findById(no)
// Board save(board)
// void deleteById(no)
}