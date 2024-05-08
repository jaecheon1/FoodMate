package com.demo.dto;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class InquiryForm {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inquiry_id;
    private String name;
    private String email;
    private String subject;
    private String message;
    private Timestamp createdAt;
    private String comments;
    private String status;
    
 // 기본 생성자
    public InquiryForm() {
    }

public InquiryForm(Long inquiry_id, String name, String email, String subject, String message, Timestamp createdAt,
		String comments, String status) {
	super();
	this.inquiry_id = inquiry_id;
	this.name = name;
	this.email = email;
	this.subject = subject;
	this.message = message;
	this.createdAt = createdAt;
	this.comments = comments;
	this.status = status;
}
    
    
}