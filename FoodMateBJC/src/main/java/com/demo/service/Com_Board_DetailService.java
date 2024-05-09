package com.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.demo.domain.Com_Board_Detail;
import com.demo.domain.Reply;

public interface Com_Board_DetailService {
	
	public void insertBoard(Com_Board_Detail vo);
	
	public void updateBoard(Com_Board_Detail vo);
	
	public void deleteBoard(Com_Board_Detail vo);

	public Com_Board_Detail getCom_Board_Datail(int seq); //seq로 해당글 찾기
	
	public List<Com_Board_Detail> getCom_Board();
	
	public Page<Com_Board_Detail> getAllCom_Board(int seq, int page, int size); //전체 페이징
	
	public Page<Com_Board_Detail> getCom_BoardByTitle(int seq, int page, int size, String title); // 제목으로검색
	
	public Page<Com_Board_Detail> getCom_BoardByWriter(int seq, int page, int size, String id); //작성자로 검색
	
	public Page<Com_Board_Detail> getCom_Board_DetailByKind(int seq, int page, int size, String kind); //카테고리 분류
	
	public Page<Com_Board_Detail> getCom_Board_DetailByCnt(int seq, int page, int size); // 조회순 정렬
	
	public Page<Com_Board_Detail> getCom_Board_DetailByGoodpoint(int seq, int page, int size); // 추천순 정렬
	
	/**
	 * 
	 * 여기서부터 댓글
	 */
	
	public void insertReply(Reply vo);
	
	public void updateReply(Reply vo);
	
	public void deleteReply(Reply vo);
	
	public List<Reply> getReplyBySeq(int seq);
	
	public Page<Reply> getReplyList_paging(int replynum , int page, int size);
	

}