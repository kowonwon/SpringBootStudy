package com.springbootstudy.app.entity;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="memo_entity") // table이 됨.
public class MemoEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int no; // column이 됨.
	
	@Column(name="title", length=30, nullable=false)
	private String title;
	
	@Column(length=20, nullable=false)
	private String writer;
	
	@Column(columnDefinition="TEXT", nullable=false)
	private String content;
	
	private Date regDate;
}