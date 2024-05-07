package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import com.demo.domain.Member;
import com.demo.dto.MemberData;
import com.demo.service.MemberService;

@Controller
@SessionAttributes("loginUser")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // 약정화면 표시
    @GetMapping("/contract")
    public String contractView() {
        return "member/contract";
    }

    // 회원가입 화면 표시 (GET 요청)
    @GetMapping("/join_form")
    public String joinView() {
        return "member/join";
    }

    // 로그인 화면 표시
    @GetMapping("/login_form")
    public String loginView() {
        return "member/login";
    }

    // 사용자 인증(로그인)
    @PostMapping("/login")
    public String loginAction(MemberData vo, Model model) {
        String url = "";
        
        if (memberService.loginID(vo) == 1) { 
            model.addAttribute("loginUser", memberService.getMemberList(vo.getId()));
            url = "redirect:main";
        } else {
            url = "member/login_fail";
        }
        return url;
    }
    
    // 초기 로그인화면
    @GetMapping("/login")
    public String loginPage() {
        return "member/login";
    }

    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(SessionStatus status) {
        status.setComplete(); // 세션 완료 상태로 설정
        return "redirect:/login_form"; // 로그아웃 후 로그인 화면으로 리다이렉트
    }

    // ID 중복 확인 처리
    @GetMapping("/id_check_form")
    public String idCheckView(Member vo, Model model) {
        int result = memberService.confirmID(vo.getId());
        model.addAttribute("message", result);
        model.addAttribute("id", vo.getId());
        return "member/idcheck";
    }

 // 이메일 중복 확인 처리
    @GetMapping("/email_check_form")
    public String emailCheckView(Member vo, Model model) {
        int result = memberService.confirmEmail(vo.getEmail());
        model.addAttribute("message", result);
        model.addAttribute("email", vo.getEmail());
        return "member/emailcheck";
    }
    
    // 회원가입 처리 (POST 요청)
    @PostMapping("/join")
    public String joinAction(Member vo) {
        memberService.insertMember(vo);
        return "redirect:/main"; // 회원가입 후 메인 페이지로 리다이렉트
    }
    
    //로그인창에서 회원가입 버튼 누르면 약관 페이지로이동
    @GetMapping("/join")
    public String joinForm() {
        return "member/join";
    }  
}