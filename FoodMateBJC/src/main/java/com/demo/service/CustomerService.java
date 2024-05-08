package com.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.domain.AdminQnaBoard;
import com.demo.domain.Inquiry;

@Service
public interface CustomerService {
	List<AdminQnaBoard> getAllQnaBoards();
}