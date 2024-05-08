package com.demo.persistence;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceRepositoryImpl implements CustomerServiceRepository {
	private List<String> qnaList; // QnA 리스트를 저장하기 위한 변수
	private List<String> inquiryList; // 1:1 문의 리스트를 저장하기 위한 변수
	
	public CustomerServiceRepositoryImpl() {
        this.qnaList = new ArrayList<>();
        this.inquiryList = new ArrayList<>();
        // 초기 QnA 리스트를 설정하거나 데이터베이스에서 불러올 수 있습니다.
    }

	@Override
	public List<String> getQnAList() {
		// TODO Auto-generated method stub
		return qnaList;
	}

	@Override
	public void addInquiry(String inquiry) {
		inquiryList.add(inquiry);
	}

	@Override
	public List<String> getInquiryList() {
		// TODO Auto-generated method stub
		return inquiryList;
	}

}