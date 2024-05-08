package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.domain.AdminQnaBoard;
import com.demo.service.AdminService;

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
        List<AdminQnaBoard> allQnA = adminBoardService.getAllQnaBoardList();
        model.addAttribute("qnaList", allQnA);
        return "qna/all";
    }
}

