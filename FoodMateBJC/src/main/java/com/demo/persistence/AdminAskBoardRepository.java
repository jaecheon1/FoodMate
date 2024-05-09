package com.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.domain.Inquiry;

public interface AdminAskBoardRepository extends JpaRepository<Inquiry, Long> {

	@Query(value="SELECT * FROM inquiries ORDER BY regdate DESC", nativeQuery = true)
	List<Inquiry> getAllAskListMain();
	
	@Query(value="SELECT * FROM inquiries WHERE inquiry_id = :boardnum", nativeQuery = true)
	Inquiry findByAskBoardnum(long boardnum);
}
