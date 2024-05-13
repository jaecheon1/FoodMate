package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.Recommend_History;
import com.demo.persistence.Recommend_HistoryRepository;

@Service
public class Recommend_HistoryServiceImpl implements Recommend_HistoryService {

	@Autowired
	private Recommend_HistoryRepository rhRepo;
	@Override
	public List<Recommend_History> getMyRecommendHistory(String id) {
		
		return rhRepo.getMyRecommendHistoryById(id);
	}

}