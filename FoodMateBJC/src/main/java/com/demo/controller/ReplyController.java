package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.domain.Com_Board_Detail;
import com.demo.domain.Reply;
import com.demo.service.Com_Board_DetailService;
import com.demo.service.ReplyService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReplyController {
	
	@Autowired
	ReplyService replyService;
	
	@Autowired
	Com_Board_DetailService comBoardDetailService;
	
	//댓글 출력
	@PostMapping("/reply_list")
	public String getReply_list(@RequestParam("seq") int seq, Model model) {
		List<Reply> ReplyList = replyService.getReplyBySeq(seq);
		Com_Board_Detail comBoardDetailVO = comBoardDetailService.getCom_Board_Datail(seq);
		model.addAttribute("ReplyList", ReplyList);
		model.addAttribute("Com_Board_DetailVO", comBoardDetailVO);
		
//		System.out.println("********************댓글 목록 = " + ReplyList);
//		System.out.println("********************게시글상세 목록 = " + comBoardDetailVO);
		return "comboard/BoardDetail";
		}
	
	
	//댓글 등록
	@GetMapping("/reply_save")
	public String insertReply(Reply vo, HttpSession session) {
		Com_Board_Detail loginUser = (Com_Board_Detail) session.getAttribute("loginUser");
		
		if (loginUser == null) { 
			return "member/login"; // 로그인페이지 넣기 (아직 안넣음!)
		}else {
			replyService.insertReply(vo);			
			return "redirect:BoardDetail";
			}
	}
	
	//댓글 수정
	@GetMapping("/reply_update")
	public String updateReply(Reply vo, HttpSession session) {
		Com_Board_Detail loginUser = (Com_Board_Detail) session.getAttribute("loginUser");
		
		if (loginUser == null) { 
			return "member/login"; // 로그인페이지 넣기 (아직 안넣음!)
		}else if(loginUser.getMember_data().getId() == vo.getMember_data().getId()){
			return "본인이 작성한 댓글만 수정가능합니다.";
		}else {
			replyService.updateReply(vo);			
			return "redirect:BoardDetail";
	}
	}
	
	//댓글 삭제 
	@GetMapping("/reply_delete")
	public String deleteReply(Reply vo, HttpSession session) {
		Com_Board_Detail loginUser = (Com_Board_Detail) session.getAttribute("loginUser");
		
		if (loginUser == null) { 
			return "member/login"; // 로그인페이지 넣기 (아직 안넣음!)
		}else if(loginUser.getMember_data().getId() == vo.getMember_data().getId()){
			return "본인이 작성한 댓글만 삭제가능합니다.";
		}else {
			replyService.deleteReply(vo);			
			return "redirect:BoardDetail";
	}
	}
	
	

}