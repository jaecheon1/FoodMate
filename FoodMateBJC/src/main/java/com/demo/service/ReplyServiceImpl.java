package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.demo.domain.Reply;
import com.demo.persistence.ReplyRepository;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	ReplyRepository ReplyRepo;

	@Override
	public void insertReply(Reply vo) {
		ReplyRepo.save(vo);

	}

	@Override
	public void updateReply(Reply vo) {
		Reply p = ReplyRepo.findById(vo.getReplynum()).get();
		ReplyRepo.save(vo);

	}

	@Override
	public void deleteReply(Reply vo) {
		ReplyRepo.delete(vo);

	}

	@Override
	public List<Reply> getReplyBySeq(int seq) {
		return ReplyRepo.getReplyList(seq);
	}
	
	/*
	 * page번호는 0부터 시작하므로 -1을 해준다. (내가 가고싶은페이지가 1일경우 0이 될수있도록)
	 */
	
	@Override
	public Page<Reply> getReplyList_paging(int replynum, int page, int size) {
		Pageable pageable = PageRequest.of(page-1, size, Direction.DESC, "replynum");
		return ReplyRepo.findReplyByreplynumContainingOrderByReplynum(replynum, pageable);
	}

}