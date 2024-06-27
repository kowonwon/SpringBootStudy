package com.springbootstudy.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootstudy.app.entity.MemoEntity;

@Repository
public interface MemoRepository extends JpaRepository<MemoEntity, Integer>{ // entity, 기본key타입
	MemoEntity findByNo(int no);
}