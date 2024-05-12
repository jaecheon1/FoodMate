package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.AdminQnaBoard;
import com.demo.domain.askBoard;
import com.demo.persistence.AdminQnaBoardRepository;
import com.demo.persistence.CustomerServiceRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	AdminQnaBoardRepository qnaBoardRepository;
	
	@Autowired
	CustomerServiceRepository customerServiceRepository;
    
    public CustomerServiceImpl(AdminQnaBoardRepository qnaBoardRepository) {
        this.qnaBoardRepository = qnaBoardRepository;
    }

    @Override
    public List<AdminQnaBoard> getAllQnaBoards() {
        return qnaBoardRepository.findAll();
    }

    @Override
    public List<String> getQnAList() {
        
        return null;
    }

    @Override
    public void addInquiry(askBoard inquiry) {
    	inquiry.setStatus("답변 대기");
    	customerServiceRepository.save(inquiry);
    }

    @Override
    public List<askBoard> getInquiryList() {
        
        return customerServiceRepository.getInquiryList();
    }

    @Override
    public List<askBoard> getInquiriesBySubject(String subject) {
        
        return null;
    }

    @Override
    public List<askBoard> getInquiriesBySubjectNamedQuery(String subject) {
        
        return null;
    }
}
