package com.demo.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    // QnA를 저장하는 리스트
    private List<AdminQnaBoard> qnaList;
    private List<askBoard> inquiryList;

    public CustomerService() {
        this.qnaList = new ArrayList<>();
        this.inquiryList = new ArrayList<>();
        
    }

   

    // 모든 QnA 리스트 반환
    public List<AdminQnaBoard> getAllQnA() {
        return qnaList;
    }
    
    // 모든 1:1 문의 리스트 반환
    public List<askBoard> getAllInquiries() {
        return inquiryList;
    }

    // 1:1 문의 추가
    public void addInquiry(askBoard inquiry) {
        inquiryList.add(inquiry);
    }

    
}
