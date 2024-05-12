package com.demo.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.domain.askBoard;

public interface AdminAskBoardRepository extends JpaRepository<askBoard, Long> {

	@Query(value="SELECT * FROM inquiries ORDER BY regdate DESC", nativeQuery = true)
	List<askBoard> getAllAskListMain();
	
	@Query(value="SELECT * FROM inquiries WHERE inquiry_id = :boardnum", nativeQuery = true)
	askBoard findByAskBoardnum(long boardnum);
	
	Page<askBoard> findAllByStatus(String status, Pageable pageable);
}
