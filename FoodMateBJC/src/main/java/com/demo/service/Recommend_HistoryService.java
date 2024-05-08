package com.demo.service;

import org.springframework.data.domain.Page;

import com.demo.domain.Recommend_History;

public interface Recommend_HistoryService {

	// 회원별 추천받은 음식(레시피)기록 조회
	public Page<Recommend_History> getMyRecommendHistory(String id, int page, int size);
	
}