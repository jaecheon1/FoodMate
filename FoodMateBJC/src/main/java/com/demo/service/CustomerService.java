package com.demo.service;

import java.util.List;

import com.demo.domain.AdminQnaBoard;
import com.demo.domain.askBoard;


public interface CustomerService {
    List<String> getQnAList();
    void addInquiry(askBoard inquiry);
    List<askBoard> getInquiryList();
    List<askBoard> getInquiriesBySubject(String subject);
    List<askBoard> getInquiriesBySubjectNamedQuery(String subject);
    List<AdminQnaBoard> getAllQnaBoards();
}
