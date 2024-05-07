package com.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.demo.domain.Com_Board_Detail;

public interface Com_Board_DetailService {
	
	public void insertBoard(Com_Board_Detail vo);
	
	public void updateBoard(Com_Board_Detail vo);
	
	public void deleteBoard(Com_Board_Detail vo);

	public Com_Board_Detail getCom_Board_Datail(int seq); //seq로 해당글 찾기
	
	public List<Com_Board_Detail> getCom_Board();
	
	public Page<Com_Board_Detail> getAllCom_Board(int seq, int page, int size); //전체 페이징
	
	public Page<Com_Board_Detail> getCom_BoardByTitle(int seq, int page, int size, String title); // 제목으로검색
	
	public Page<Com_Board_Detail> getCom_BoardByWriter(int seq, int page, int size, String id); //작성자로 검색
	
	public Page<Com_Board_Detail> getCom_Board_DetailByKind(int seq, int page, int size, String kind); //분류
	

}