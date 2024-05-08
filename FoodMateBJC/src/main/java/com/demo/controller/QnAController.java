package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.domain.AdminQnaBoard;
import com.demo.service.AdminService;

import org.springframework.data.domain.Page;


@Controller
@RequestMapping("/qna")
public class QnAController {

    private final AdminService adminBoardService;

    @Autowired
    public QnAController(AdminService adminBoardService) {
        this.adminBoardService = adminBoardService;
    }

    @GetMapping("/all")
    public String getAllQnA(Model model) {
        // 페이지 정보 생성
        Pageable pageable = PageRequest.of(0, 10); // 페이지 번호: 0, 페이지 크기: 10

        // 페이지 정보를 포함하여 호출
        Page<AdminQnaBoard> qnaPage = adminBoardService.getAllQnaBoardList(pageable);
        List<AdminQnaBoard> allQnA = qnaPage.getContent(); // 페이지의 내용을 가져옴
        model.addAttribute("qnaList", allQnA);
        return "qna/all";
    }
}

