package com.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.domain.Inquiry;
import com.demo.dto.InquiryForm;
import com.demo.service.InquiryService;

@Controller
@RequestMapping("/inquiry")
public class InquiryProcessController {

    @Autowired
    private InquiryService inquiryService;
    
    @PostMapping("/processInquiry")
    public String processInquiry(@ModelAttribute("inquiryForm") InquiryForm inquiryForm) {
        Inquiry inquiry = new Inquiry();
        inquiry.setName(inquiryForm.getName());
        inquiry.setEmail(inquiryForm.getEmail());
        inquiry.setSubject(inquiryForm.getSubject());
        inquiry.setMessage(inquiryForm.getMessage());
        inquiry.setCreated_at(new Date()); // 현재 시간으로 설정
        inquiryService.saveInquiry(inquiry);
        return "redirect:/inquiry/inquiryList";
    }
}
