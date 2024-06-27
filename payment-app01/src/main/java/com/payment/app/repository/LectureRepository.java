package com.payment.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payment.app.domain.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Integer>{
	
}
