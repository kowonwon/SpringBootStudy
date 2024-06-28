package com.springbootstudy.bbs.domain;

import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/* 클래스에 @Entity 애노테이션을 적용하면 Spring Data JPA가 엔티티로 인식함
 * Entity 클래스는 실제 DB 테이블과 1:1로 맵핑되는 핵심 클래스로 DB 테이블에
 * 존재하는 컬럼들을 필드로 가지며 테이블에 없는 컬럼을 필드로 가져서는 안 된다. 
 * Entity 클래스는 객체의 일관성과 안전성을 보장하기 위해서 setter 메서드의 사용을
 * 권장하지 않으며 대신 생성자 또는 Builder 사용을 권장한다. 이외에도 요청과 응답을
 * 처리하는 과정에서 계층 간에 값으로 전달되는 DTO로 사용하는 것도 권장하지 않는다. 
 **/
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="springbbs")
/* JPA Entity에서 이벤트가 발생할 때마다 특정 로직을 실행시킬 수 있는 애노테이션
 * Entity의 값이 변경되는 것을 감시(Auditing) 하도록 설정하는 애노테이션 임
 **/
@EntityListeners(AuditingEntityListener.class)
public class Board {
	// @Id는 no를 기본 키로 지정한다.
	@Id
	
	/* @GeneratedValue 애노테이션은 데이터가 저장될 때 자동으로 1씩 증가함
	 * strategy 옵션을 생략할 경우 모두 동일한 번호를 생성하므로 고유 번호를 가질
	 * 수 없기 때문에 일반적으로 GenerationType.IDENTITY를 많이 사용한다.
	 **/
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int no;
	
	/* @Column 애노테이션은 컬럼의 세부 설정을 위해서 사용하고 세부 설정이 
	 * 필요 없다면 사용하지 않아도 된다. name 옵션을 지정해 테이블 컬럼의 이름을
	 * 지정할 수 있으며 생략하면 필드 이름이 사용된다. length 옵션은 문자 길이 
	 * 제약조건을 설정하는 것으로 String 타입만 지정하며 생략하면 기본 값은 255이다.
	 * nullable은 null 허용여부를 지정하는 것으로 생략하면 기본 값은 true 이다.
	 **/
	@Column(name="title", length=50, nullable = false)
	private String title;	
	@Column(length=10, nullable=false)	
	private String writer;
	
	// columnDefinition 옵션은 글자 수를 제한할 수 없는 경우에 사용한다.
	@Column(columnDefinition = "TEXT", nullable=false)	
	private String content;

	/* 날짜 데이터를 맵핑하는 @Temporal 애노테이션의 value 속성 값
	 * 
	 * TemporalType.DATE : 날짜 데이터(2023-12-29)
	 * TemporalType.TIME : 시간 데이터(14:22:52)
	 * TemporalType.TIMESTAMP : 날짜와 시간 데이터(2023-12-19 14:22:52)
	 **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	@CreatedDate			// Entity가 저장될 때 시간이 자동으로 저장되는 애노테이션
	@LastModifiedDate	// Entity가 수정될 때 시간이 자동으로 수정되는 애노테이션
	private Timestamp regDate;
		
	/* 테이블이 생성될 때 자바의 카멜케이스는 DB의 언더스코어로 변경된다.
	 * precision : 소수점 제외 전체자리 수, scale : 소수 자리수
	 **/
	@Column(precision=5, nullable=false)
	private int readCount;
	@Column(length=20, nullable=false)
	private String pass;	
	
	// @Column 애노테이션을 지정하지 않아도 Entity 클래스의 필드는 null 허용 컬럼이 된다.
	private String file1;
	
	// 게시 글 수정을 처리하는 메서드
	public void updateBoard(BoardDTO dto) {
		this.title = dto.getTitle();
		this.writer = dto.getWriter();
		this.content = dto.getContent();
	}
}
