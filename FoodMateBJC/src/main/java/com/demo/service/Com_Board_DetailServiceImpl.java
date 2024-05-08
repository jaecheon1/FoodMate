package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.demo.domain.Com_Board_Detail;
import com.demo.persistence.Com_Board_DetailReposiotry;

@Service
public class Com_Board_DetailServiceImpl implements Com_Board_DetailService {

	@Autowired
	Com_Board_DetailReposiotry BoardDetailRepo;

	// 게시글 목록
	@Override
	public List<Com_Board_Detail> getCom_Board() {
		return BoardDetailRepo.getBoard_List();
	}

	// 게시글 상세정보
	@Override
	public Com_Board_Detail getCom_Board_Datail(int seq) {
		return BoardDetailRepo.getCom_Board_Detail(seq);
	}

	@Override
	public void insertBoard(Com_Board_Detail vo) {
		BoardDetailRepo.save(vo);

	}

	@Override
	public void updateBoard(Com_Board_Detail vo) {
		Com_Board_Detail p = BoardDetailRepo.findById(vo.getSeq()).get();
		vo.setD_regdate(p.getD_regdate());
		vo.setCnt(p.getCnt());
		BoardDetailRepo.save(vo);

	}

	@Override
	public void deleteBoard(Com_Board_Detail vo) {
		BoardDetailRepo.delete(vo);

	}

	// 페이징처리
	@Override
	public Page<Com_Board_Detail> getAllCom_Board(int seq, int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size, Direction.ASC, "seq");
		return BoardDetailRepo.findAllCom_Board_Detail(seq, pageable);
	}

	// 제목으로 검색
	@Override
	public Page<Com_Board_Detail> getCom_BoardByTitle(int seq, int page, int size, String title) {
		Pageable pageable = PageRequest.of(page - 1, size, Direction.ASC, "seq");
		return BoardDetailRepo.findCom_Board_DetailByTitleContainingOrderByTitle(title, seq, pageable);
	}

	// 아이디로검색
	@Override
	public Page<Com_Board_Detail> getCom_BoardByWriter(int seq, int page, int size, String id) {
		Pageable pageable = PageRequest.of(page - 1, size, Direction.ASC, "seq");
		return BoardDetailRepo.findCom_Board_DetailByIdContainingOrderById(id, seq, pageable);
	}
	
	//분류
	@Override
	public Page<Com_Board_Detail> getCom_Board_DetailByKind(int seq, int page, int size, String kind) {
		Pageable pageable = PageRequest.of(page - 1, size, Direction.ASC, "seq");
		return BoardDetailRepo.findCom_Board_DetailByKindContaining(kind, seq, pageable);
	}
	
	//조회순
	@Override
	public Page<Com_Board_Detail> getCom_Board_DetailByCnt(int seq, int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size, Direction.ASC, "seq");
		return BoardDetailRepo.findAllByOrderByCntDesc(seq, pageable);
	}
	
	//추천순
	@Override
	public Page<Com_Board_Detail> getCom_Board_DetailByGoodpoint(int seq, int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size, Direction.ASC, "seq");
		return BoardDetailRepo.findAllByOrderByGoodpointDesc(seq, pageable);
	}

}