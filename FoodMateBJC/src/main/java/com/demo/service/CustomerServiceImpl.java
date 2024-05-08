package com.demo.service;

import java.util.List;

import com.demo.domain.AdminQnaBoard;
import com.demo.domain.Inquiry;
import com.demo.persistence.AdminQnaBoardRepository;
import com.demo.persistence.InquiryRepository;

public class CustomerServiceImpl implements CustomerService {
	private final AdminQnaBoardRepository qnaBoardRepository;

    public CustomerServiceImpl(AdminQnaBoardRepository qnaBoardRepository) {
    	this.qnaBoardRepository = qnaBoardRepository;
    }

	public List<AdminQnaBoard> getAllQnaBoards() {
		// TODO Auto-generated method stub
		return qnaBoardRepository.findAll();
	}
	

}