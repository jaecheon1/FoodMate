// 1:1 문의를 등록하는 컨트롤러
package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.domain.Inquiry;
import com.demo.dto.InquiryForm;
import com.demo.service.InquiryService;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {
    
    
    private InquiryService inquiryService;
    
    @Autowired
    public InquiryController(InquiryService inquiryService) {
    	this.inquiryService = inquiryService;
    }
    

    @GetMapping("/inquiryForm")
    public String showAddForm(Model model) {
    	model.addAttribute("inquiryForm", new InquiryForm()); // InquiryForm 객체를 모델에 추가
        return "inquiry/inquiryForm";
    }

    @PostMapping("/saveInquiry")
    public String saveInquiry(@ModelAttribute("inquiryForm") InquiryForm inquiryForm) {
        inquiryService.saveInquiry(inquiryForm);
        return "redirect:/inquiry/inquiryList"; // 목록 페이지로 리다이렉트합니다.
    }
    
    @GetMapping("/inquiryList")
    public String showInquiryList(Model model) {
        List<Inquiry> inquiries = inquiryService.getAllInquiries();
        model.addAttribute("inquiries", inquiries);
        return "inquiry/inquiryList"; // 문의 목록 페이지로 이동합니다.
    }

    @PostMapping("/inquiryList")
    public String submitInquiry(@ModelAttribute("inquiryForm") InquiryForm inquiryForm) {
        // 사용자가 제출한 문의를 저장하고 나중에 조회할 수 있도록 서비스를 호출합니다.
    	System.out.println("inquiryForm="+inquiryForm);
        inquiryService.saveInquiry(inquiryForm);
        
        return "redirect:/inquiry/inquiryList"; // 목록 페이지로 이동
    }
}