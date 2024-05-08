package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.demo.domain.Recommend_History;
import com.demo.persistence.Recommend_HistoryRepository;

@Service
public class Recommend_HistoryServiceImpl implements Recommend_HistoryService {

	@Autowired
	private Recommend_HistoryRepository rhRepo;
	@Override
	public Page<Recommend_History> getMyRecommendHistory(String id, int page, int size) {
		Pageable pageable = PageRequest.of(page-1, size, Direction.ASC, "recommend_date");
		return rhRepo.getMyRecommendHistoryById(id, pageable);
	}

}