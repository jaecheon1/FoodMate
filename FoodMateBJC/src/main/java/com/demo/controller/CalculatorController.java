package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.demo.domain.MemberData;
import com.demo.dto.CalculationResult;
import com.demo.persistence.MemberDataRepository;
import com.demo.persistence.MemberRepository;
import com.demo.service.CalculatorServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class CalculatorController {

    private final CalculatorServiceImpl calculatorServiceImpl;
    private final MemberRepository memberRepository;

    @Autowired
    public CalculatorController(CalculatorServiceImpl calculatorServiceImpl, MemberRepository memberRepository) {
        this.calculatorServiceImpl = calculatorServiceImpl;
        this.memberRepository = memberRepository;
    }
    
    @GetMapping("/")
    public String homePage(Model model) {
        // 홈페이지에 필요한 데이터를 모델에 추가하고, 뷰 이름을 반환합니다.
    	model.addAttribute("welcomeMessage", "건강한 식단을 추천해드릴게요!");
        return "main"; // 여기서 "main"는 타임리프 템플릿 파일의 이름입니다.
    }

    @GetMapping("/UserChoice")
    public String showUserChoicePage(Model model, HttpSession session) {
        // 세션에서 사용자 정보를 가져옵니다.
        MemberData loggedInUser = (MemberData) session.getAttribute("loginUser");

        if (loggedInUser != null) {
            // 사용자 정보가 존재할 경우
        	MemberData member = memberRepository.findByLoginId(loggedInUser.getId());
        	CalculationResult result = calculatorServiceImpl.calculate(member);
            model.addAttribute("member", member);
            model.addAttribute("result", result);
            return "foodRecommend/UserChoice";
        } else {
            // 로그인 페이지로 리다이렉트합니다.
            return "redirect:/login"; 
        }
    }

}