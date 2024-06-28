package com.springbootstudy.bbs.domain;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/* Entity 클래스는 데이터베이스 영속성(Persistent)의 목적으로 사용되는 객체로
 * 요청과 응답 사이에서 계층 간에 값을 전달하는 목적으로 사용하는 것은 좋은 방법이
 * 아니다. 그러므로 별도의 BoardDTO를 정의해 사용할 것이며 Board 클래스 
 * 타입에서 BoardDTO 타입으로 또는 그 반대로 서로 변환 할 수 있도록 생성자와
 * static 메서드를 추가하였다.
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
	private int no;
	private String title;	
	private String writer;
	private String content;
	private Timestamp regDate;
	private int readCount;
	private String pass;
	private String file1;

	/* 요청 처리용 DTO와 응답 처리용 DTO를 따로 만드는 경우도 있지만 이 예제는
	 * 요청과 응답에 대한 DTO를 하나를 사용하기 위해서 Board 타입을 BoardDTO로
	 * 변환할 수 있는 생성자와 그 반대로 변환할 수 있는 static 메서드를 정의했다.	 
	 **/
	// Board 타입을 파라미터로 받아서 초기화 하는 생성자
	public BoardDTO(Board board) {
		this.no = board.getNo();
		this.title = board.getTitle();
		this.writer = board.getWriter();
		this.content = board.getContent();
		this.regDate = board.getRegDate();
		this.readCount = board.getReadCount();
		this.pass = board.getPass();
		this.file1 = board.getFile1();
	}
	
	// 빌더 패턴을 사용해 BoardDTO 객체를 Board 타입으로 변환해 반환하는 메서드
	public static Board toEntity(final BoardDTO dto) {
		return Board.builder()
							.no(dto.getNo())
							.title(dto.getTitle())
							.writer(dto.getWriter())
							.content(dto.getContent())
							//.regDate(new Date(new java.util.Date().getTime()))
							.readCount(dto.getReadCount())
							.pass(dto.getPass())
							.file1(dto.getFile1())
							.build();
	}
}
