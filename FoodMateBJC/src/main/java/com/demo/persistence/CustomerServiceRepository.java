package com.demo.persistence;

import java.util.List;

public interface CustomerServiceRepository {
	List<String> getQnAList(); // QnA 리스트를 가져오는 메서드
    void addInquiry(String inquiry); // 1:1 문의를 추가하는 메서드
    List<String> getInquiryList(); // 1:1 문의 리스트를 가져오는 메서드
}