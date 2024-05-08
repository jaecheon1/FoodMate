package com.demo.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.domain.Recommend_History;

public interface Recommend_HistoryRepository extends JpaRepository<Recommend_History, String> {

	// 회원별 추천받은 음식(레시피)기록 조회
	@Query(value= "SELECT rh FROM RecommendHistory rh "+
			" JOIN MemberData m ON rh.h_no_data.id = m.id"+
			" JOIN foodRecipe r ON rh.recommend_food.idx = r.idx"+
			" WHERE m.id=:id ORDER BY rh.recommend_date ASC", nativeQuery=false)
	public Page<Recommend_History> getMyRecommendHistoryById(@Param("id") String id, Pageable pageable);
	
}