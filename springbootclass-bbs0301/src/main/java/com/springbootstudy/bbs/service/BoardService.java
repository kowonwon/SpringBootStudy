package com.springbootstudy.bbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springbootstudy.bbs.domain.Board;
import com.springbootstudy.bbs.domain.BoardDTO;
import com.springbootstudy.bbs.repository.BoardRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardService {
	
	private static final int PAGE_SIZE = 10;
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional
	public void deleteBoard(int no) {
//		boardRepository.deleteById(no);
		boardRepository.deleteByNo(no);
	}
	
	@Transactional
	public BoardDTO updateForm(BoardDTO dto) {
		Board board = boardRepository.findById(dto.getNo()).get();
		board.updateBoard(dto);
		
		return new BoardDTO(board);
	}
	
	public BoardDTO addBoard(BoardDTO dto) {
		Board board = boardRepository.save(BoardDTO.toEntity(dto));
		return new BoardDTO(board);
	}
	
	@Transactional
	public BoardDTO getBoard(int no, boolean isCount) { // 조회수 증가 여부
		
		// 먼저 isCount가 true면 조회수를 증가
		if(isCount) {
			Board board = boardRepository.findById(no).get();
			board.incrementReadCount();
		}
		
		return new BoardDTO(boardRepository.findByNo(no).get());
//		return new BoardDTO(boardRepository.findById(no).get());
	}
	
	public Map<String, Object> boardList(int pageNum, String type, String keyword) {
		
		PageRequest pageable = PageRequest.of(
				pageNum, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "no"));
		log.info("pageble : " + pageable);
		
		boolean searchOption = !(type.equals("null") || keyword.equals("null"));
		
		Page<Board> boardPage = null;
		
		if(searchOption && type.equals("title")) {
			boardPage = boardRepository.findByTitleLike(pageable, "%" + keyword + "%");
//			boardPage = boardRepository.searchTitleLike(keyword, pageable);
			
		} else if(searchOption && type.equals("writer")) {
			boardPage = boardRepository.findByWriterLike(pageable, "%" + keyword + "%");
		} else if(searchOption && type.equals("content")) {
			boardPage = boardRepository.findByContentLike(pageable, "%" + keyword + "%");
		} else {
			boardPage = boardRepository.findAll(pageable);
		}
		
		log.info("boardPage : " + boardPage);
		Page<BoardDTO> dtoPage = boardPage.map(board -> new BoardDTO(board));

		Map<String, Object> modelMap = new HashMap<>();
		modelMap.put("page", dtoPage);
		modelMap.put("searchOption", searchOption);
		if(searchOption) {
			modelMap.put("type", type);
			modelMap.put("keyword", keyword);
		}
		return modelMap;
	}
}