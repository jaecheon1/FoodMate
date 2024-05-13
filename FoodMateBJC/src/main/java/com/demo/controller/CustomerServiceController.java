package com.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.domain.AdminQnaBoard;
import com.demo.domain.MemberData;
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
    public String saveInquiry(@ModelAttribute("loginUser") MemberData loginUser, askBoard vo) {
        // 현재 사용자의 이름 가져오기
        String username = loginUser.getName();
        
        // 사용자 정보를 이용하여 작업 수행
        askBoard inquiry = new askBoard();
        inquiry.setName(username);
        inquiry.setSubject(vo.getSubject());
        inquiry.setMessage(vo.getMessage());
        inquiry.setRegdate(new Date()); // 현재 시간으로 설정
        customerService.addInquiry(inquiry);
        
        return "redirect:/inquiry/inquiryList";
    }




    // 1:1 문의 목록 보기
    @GetMapping("/inquiry/inquiryList")
    public String showInquiryList(@ModelAttribute("loginUser") MemberData loginUser, Model model) {
        // inquiryList를 가져와서 모델에 추가
        List<askBoard> inquiries = customerService.getInquiryList();
        
        // 현재 로그인된 사용자의 이름 가져오기
        String username = loginUser.getName();
        
        // 모델에 현재 사용자의 이름 추가
        model.addAttribute("username", username);
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
    
    @GetMapping("/qna/qna_detail/{id}")
    public String showQnaDetails(@PathVariable Long id, Model model) {
    	// 서비스 클래스를 통해 해당 ID에 대한 질문 세부 정보를 가져옵니다.
        AdminQnaBoard qnaDetail = customerService.getQnaDetailsById(id);
        
     // 가져온 질문 세부 정보가 null인지 확인합니다.
        if (qnaDetail == null) {
            // 가져온 질문 세부 정보가 null인 경우, 적절한 오류 처리를 수행하거나 사용자에게 메시지를 전달할 수 있습니다.
            // 여기서는 간단히 오류 페이지로 리다이렉트하도록 하겠습니다.
            return "redirect:/error"; // 예를 들어, error 페이지로 리다이렉트합니다.
        }
        
        model.addAttribute("qnaDetail", qnaDetail);
        return "qna/qna_detail"; // 질문 세부 정보를 보여주는 뷰의 이름을 반환합니다.
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