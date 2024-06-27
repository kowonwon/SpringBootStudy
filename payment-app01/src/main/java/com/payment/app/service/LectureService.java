package com.payment.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.payment.app.domain.Board;
import com.payment.app.domain.Lecture;
import com.payment.app.repository.BoardRepository;
import com.payment.app.repository.LectureRepository;

import lombok.extern.slf4j.Slf4j;

@Service
public class LectureService {
	
	@Autowired
	private LectureRepository lectureRepository;
	
	public List<Lecture> lectureList() {
		return lectureRepository.findAll();
	}
}