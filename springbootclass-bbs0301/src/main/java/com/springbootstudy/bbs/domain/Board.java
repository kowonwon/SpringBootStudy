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

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="springbbs")
@EntityListeners(AuditingEntityListener.class)
public class Board {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int no;
	
	@Column(name="title", length=50, nullable=false)
	private String title;
	
	@Column(length=10, nullable=false)
	private String writer;
	
	@Column(columnDefinition="TEXT", nullable=false)
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	@CreatedDate
	@LastModifiedDate
	private Timestamp regDate;
	
	@Column(length=20, nullable=false)
	private String pass;
	
	@Column(precision=5, nullable=false)
	private int readCount;
	
	private String file1;
	
	// 게시 글 수정을 처리하는 메서드
	public void updateBoard(BoardDTO dto) {
		this.title = dto.getTitle();
		this.writer = dto.getWriter();
		this.content = dto.getContent();
	}
	
	public void incrementReadCount() {
		this.readCount = this.readCount + 1;
	}
}