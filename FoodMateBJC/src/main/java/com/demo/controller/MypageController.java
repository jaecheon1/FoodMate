package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.domain.Com_Board_Detail;
import com.demo.domain.MemberData;
import com.demo.domain.Recommend_History;
import com.demo.domain.askBoard;
import com.demo.service.Com_Board_DetailService;
import com.demo.service.CustomerService;
import com.demo.service.MemberService;
import com.demo.service.Recommend_HistoryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MypageController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private Com_Board_DetailService boardService;
	@Autowired
	private Recommend_HistoryService recommendService;
	@Autowired
	private CustomerService customerService;

	
	// 마이페이지 메인화면
	@GetMapping("/mypage")
	public String mypageView(HttpSession session, Model model) {
		MemberData loginUser = (MemberData)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			return "redirect:/login";
		} else {
			
			return "mypage/mypageMain";
		}
	}
	
	// 내 정보 화면
	@GetMapping("/infoView")
	public String myInfoView(HttpSession session, Model model) {
		MemberData loginUser = (MemberData)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			return "redirect:/login";
		} else {
			
	        model.addAttribute("memberData", loginUser);
			model.addAttribute("********", "********");
		}
		return "mypage/infoView";
	}
	
	// 내 정보 수정 화면
	@GetMapping("/infoUpdate")
	public String infoUpdateView(HttpSession session, Model model) {
		MemberData loginUser = (MemberData)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			return "redirect:/login";
		} else {
			model.addAttribute("loginUser", loginUser);
			return "mypage/infoUpdate";
		}
	}
	
	// 개인정보 수정
	@PostMapping("/update_info")
	public String infoUpdateAction(HttpSession session, MemberData vo) {
	    MemberData loginUser = (MemberData) session.getAttribute("loginUser");
	    
	    if (loginUser == null) {
	        return "redirect:/login";
	    } else {
	        
	        	MemberData existingUser = memberService.getMember(loginUser.getId());
	        	existingUser.setPassword(vo.getPassword());
	        	memberService.changeInfo(existingUser);
	        
	        
	        
	        return "mypage/mypageMain";
	    }
	}

	
	// 닉네임 중복 확인 처리
	@GetMapping("/nickname_check_form")
	public String nicknameCheckView(MemberData vo, Model model) {
		int result = memberService.confirmNickname(vo.getNickname());
		
		model.addAttribute("message", result);
		model.addAttribute("nickname", vo.getNickname());
		
		return "mypage/nickcheck";
	}
	
	// 바디데이터 수정 화면
	@GetMapping("bodyUpdate")
	public String bodyUpdateView(HttpSession session) {
		MemberData loginUser = (MemberData)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			return "redirect:/login";
		} else {
			return "mypage/bodyUpdate";
		}
	}
	
	// 바디데이터 수정
	@PostMapping("/update_body")
	public String bodyUpdateAction(HttpSession session, MemberData vo) {
		MemberData loginUser = (MemberData)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			return "redirect:/login";
		} else {
			// 로그인한 회원 바디데이터 수정
			vo.setId(loginUser.getId());
			memberService.changeBodyData(vo);
			
			return "mypage/mypageMain";
		}
	}
	
	// 나의 레시피 화면
	@GetMapping("/myRecipeList")
	public String myRecipeListView(HttpSession session, Model model) {
		MemberData loginUser = (MemberData)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			return "redirect:/login";
		} else {
			List<Com_Board_Detail> recipeList = boardService.getMyRecipe(loginUser.getId());
			
			model.addAttribute("recipeList", recipeList);
			
			return "mypage/myRecipeList";
		}
	}
	
	// 추천받은 음식 화면
	@GetMapping("/foodRecommend")
	public String foodRecommendView(HttpSession session, Model model) {
		MemberData loginUser = (MemberData)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			return "redirect:/login";
		} else {
			List<Recommend_History> recommend = recommendService.getMyRecommendHistory(loginUser.getId());
			model.addAttribute("recommend", recommend);
			
			return "mypage/recommendHistory";
		}
	}
	
	// 1:1문의 관련
	@PostMapping("/myInquiry")
	public String myInquiryView(HttpSession session, Model model) {
		MemberData loginUser = (MemberData)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			return "redirect:/login";
		} else {
//			List<askBoard> inquiry = customerService.getMyInquiry(loginUser.getId());
			
//			model.addAttribute("myInquiry", inquiry);
			return "mypage/myInquiry";
		}
	}
	
	
	/* 추가 기능용 자리
	 * 아직 구상중..
	@PostMapping("/myGoal")
	public String adviceOfGoal(HttpSession session, Model model) {
		MemberData loginUser = (MemberData)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			return "redirect:login";
		} else {
			
			
			return "mypage/advice";
		}
	}
	
	
	
	
	
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}