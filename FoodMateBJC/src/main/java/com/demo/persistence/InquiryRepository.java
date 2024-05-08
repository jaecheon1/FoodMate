package com.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.domain.Inquiry;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

	
	// 쿼리 메서드 정의
    List<Inquiry> findBySubject(String subject);

    // Named 쿼리 사용 예시
    @Query(value="SELECT * FROM inquiries WHERE subject = :subject", nativeQuery = true)
    List<Inquiry> findBySubjectNamedQuery(@Param("subject") String subject);
}