package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(Model model) {
        // 홈페이지에 필요한 데이터를 모델에 추가하고, 뷰 이름을 반환합니다.
    	model.addAttribute("welcomeMessage", "건강한 식단을 추천해드릴게요!");
        return "main"; // 여기서 "main"는 타임리프 템플릿 파일의 이름입니다.
    }
}
