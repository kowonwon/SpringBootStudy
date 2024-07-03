package com.springbootstudy.bbs.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springbootstudy.bbs.domain.Board;

//@Repository 자동설정되어있음.
public interface BoardRepository extends JpaRepository<Board, Integer> {
	
	Page<Board> findByTitleLike(Pageable pageable, String keyword);
	Page<Board> findByWriterLike(Pageable pageable, String keyword);
	Page<Board> findByContentLike(Pageable pageable, String keyword);
	
	@Query("SELECT b FROM Board b WHERE b.title LIKE CONCAT('%', :keyword, '%')")
	Page<Board> searchTitleLike(String keyword, Pageable pageable);
	
	void deleteByNo(int no);
	Optional<Board> findByNo(int no);
// List<Board> findAll()
// Optional<Board> findById(no)
// Board save(board)
// void deleteById(no)
// Page<T> findAll(Pageable pageable);
}