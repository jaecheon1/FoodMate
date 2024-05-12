package com.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.domain.AdminQnaBoard;
import com.demo.domain.askBoard;
import com.demo.service.CustomerService;

@Controller
@RequestMapping("/")
public class CustomerServiceController {

    private final CustomerService customerService;

    @Autowired
    public CustomerServiceController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // 1:1 문의 등록 폼 보여주기
    @GetMapping("/inquiry/inquiryForm")
    public String showInquiryForm(Model model) {
//        model.addAttribute("loginUser", new askBoard());
        return "inquiry/inquiryForm";
    }

    // 1:1 문의 저장
    @PostMapping("/saveInquiry")
    public String saveInquiry(
    		// @ModelAttribute("loginUser") Member member
    		askBoard vo
    		) {
    	askBoard inquiry = new askBoard();
        inquiry.setName(vo.getName());
        inquiry.setEmail(vo.getEmail());
        inquiry.setSubject(vo.getSubject());
        inquiry.setMessage(vo.getMessage());
        inquiry.setRegdate(new Date()); // 현재 시간으로 설정
        customerService.addInquiry(inquiry);
        return "redirect:/inquiry/inquiryList";
    }

    // 1:1 문의 목록 보기
    @GetMapping("/inquiry/inquiryList")
    public String showInquiryList(Model model) {
        List<askBoard> inquiries = customerService.getInquiryList();
        model.addAttribute("inquiries", inquiries);
        return "inquiry/inquiryList";
    }

    // 관리자 질문답변 목록 보기
    @GetMapping("qna/all")
    public String showQnaList(Model model) {
        List<AdminQnaBoard> qnaList = customerService.getAllQnaBoards();
        model.addAttribute("qnaList", qnaList);
        return "qna/all";
    }
    
    @GetMapping("/home")
	 public String homePage(Model model) {
       // 홈페이지에 필요한 데이터를 모델에 추가하고, 뷰 이름을 반환합니다.
   	model.addAttribute("welcomeMessage", "건강한 식단을 추천해드릴게요!");
       return "main"; // 여기서 "main"는 타임리프 템플릿 파일의 이름입니다.
   }
    
    @GetMapping("/customer_service") // 새로운 메서드 추가
    public String showCustomerServicePage(Model model) {
        // 여기에서 필요한 모델 데이터를 추가할 수 있습니다.
        return "customer_service/customer_service"; // 해당 뷰 이름 반환
    }
	
}
