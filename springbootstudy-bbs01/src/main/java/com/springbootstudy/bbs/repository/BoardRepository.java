package com.springbootstudy.bbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootstudy.bbs.domain.Board;

/* 우리의 인터페이스를 저장소(Repository) 역할을 하는 레포지토리로 만들기 위해서는 
 * JpaRepository 인터페이스를 상속 받아야 한다. JpaRepository 인터페이스를
 * 상속할 때 제네릭은 <엔티티 타입, 엔티티의 기본 키(PK)의 타입>을 지정하면 된다.
 * JpaRepository를 상속받는 interface는 @Repository 애노테이션을 적용하지 않아도
 * 스프링 부트의 기본 설정에 의해서 자동으로 스프링 빈으로 등록된다.
 **/
public interface BoardRepository extends JpaRepository<Board, Integer>{
	/* 기본적인 CRUD 작업을 위한 메서드가 상위 인터페이스에 정의되어 있어서
	 * 기본적인 CRUD 작업은 별도의 메서드를 정의하지 않고 사용할 수 있으며 
	 * 아래 메서드를 서비스에서 호출하면 메서드가 실행되면서 쿼리가 자동으로 생성된다.
	 * 
	 * List<MemoEntity> findAll();
	 * Optinal<MemoEntiry> findById(no)
	 * MemoEntity save(memoEntity) 
	 * void deleteById(no)
	 **/
}
