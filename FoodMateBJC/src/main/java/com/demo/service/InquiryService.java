// 1:1 문의를 등록하는 서비스
package com.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.Inquiry;
import com.demo.dto.InquiryForm;
import com.demo.persistence.InquiryRepository;

@Service
public class InquiryService {
	
	@Autowired
    private InquiryRepository inquiryRepository; // InquiryRepository 인터페이스를 주입합니다.
	
	public void saveInquiry(InquiryForm inquiryForm) {
        // InquiryForm을 Inquiry 엔티티로 변환한 후, 저장합니다.
        Inquiry inquiry = new Inquiry();
        inquiry.setName(inquiryForm.getName());
        inquiry.setEmail(inquiryForm.getEmail());
        inquiry.setSubject(inquiryForm.getSubject());
        inquiry.setMessage(inquiryForm.getMessage());
        inquiry.setCreated_at(new Date());
        inquiryRepository.save(inquiry);
    }

	private List<Inquiry> inquiryList; // 1:1 문의 리스트를 저장하기 위한 변수

    public InquiryService() {
        this.inquiryList = new ArrayList<>();
    }

    

	public List<Inquiry> getAllInquiries() {
		// TODO Auto-generated method stub
		return inquiryRepository.findAll();
	}



	public void saveInquiry(Inquiry inquiry) {
		inquiryRepository.save(inquiry);
		
	}
}