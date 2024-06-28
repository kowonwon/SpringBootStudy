package com.springbootstudy.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springbootstudy.bbs.domain.Board;
import com.springbootstudy.bbs.domain.BoardDTO;
import com.springbootstudy.bbs.repository.BoardRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

//BoardService 클래스가 서비스 계층의 스프링 빈(Bean) 임을 정의 
@Service
@Slf4j
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	public List<BoardDTO> boardList() {
		log.info("BoardService: boardList()");
		// DB 테이블에서 no를 기준으로 내림차순 정렬해 게시 글 리스트를 읽어와
		// StreamAPI를 사용해 List<Board>를 List<BoardDTO>로 변환해 반환
		return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "no"))
					.stream().
					map(BoardDTO::new)
					.toList();
	}
	
	// DB 테이블에서 no에 해당하는 게시 글 을 읽어와 반환하는 메서드
	public BoardDTO getBoard(int no) {	
		log.info("BoardService: getBoard(int no)");
		return new BoardDTO(boardRepository.findById(no).get());
	}
	
	// DB 테이블에 게시 글 정보를 추가하는 메서드
	public BoardDTO addBoard(BoardDTO dto) {
		log.info("BoardService: addBoard(BoardDTO dto)");
		Board board = boardRepository.save(BoardDTO.toEntity(dto));
		return new BoardDTO(board);
	}
	
	/* 아래에서 save() 메서드를 사용하지 않고 수정된 값을 객체에 적용하면 UPDATE 쿼리가
	 * DB에 발행되는데 이는 Dirty Checking(상태 변경 검사)으로 실행되는 것으로 JPA에서
	 * 트랜잭션이 끝나는 시점에 변화가 있는 모든 엔티티 객체를 DB에 자동으로 반영해 준다. 
	 **/
	// 트랜잭션 처리 애노테이션
	@Transactional
	public BoardDTO updateBoard(BoardDTO dto) {
		log.info("BoardService: updateBoard(BoardDTO dto)");
		
		// Dirty Checking에서 상태 변화는 현재 DB에서 읽어온 최초 조회 상태에서
		Board board = boardRepository.findById(dto.getNo()).get();
		
		// 이 코드가 실행되면 메모가 수정되었기 때문에 현재 메서드가 종료되면
		// 즉 트랜잭션이 끝나면 UPDATE 쿼리가 DB에 발행된다.
		board.updateBoard(dto);
		
		// 다음과 같이 save() 함수를 사용해 DB 테이블에서 직접 수정할 수도 있지만
		// 이런 코드는 객체지향 관점에서는 좋은 코드는 아니다.
		//board = boardRepository.save(BoardDTO.toEntity(dto));
		return new BoardDTO(board);
	}
	
	// 게시 글 삭제를 처리하는 메서드
	public void deleteBoard(int no) {
		log.info("BoardService: deleteBoard(int no)");
		boardRepository.deleteById(no);
	}	
}
