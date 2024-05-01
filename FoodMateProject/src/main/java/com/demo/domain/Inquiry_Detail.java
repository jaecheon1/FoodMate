package com.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Entity
public class Inquiry_Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inquiryId;

    private String name;
    private String email;
    private String subject;
    private String message;
    private Timestamp createdAt;
    private String comments;
    
	public Inquiry_Detail(Long inquiryId, String name, String email, String subject, String message,
			Timestamp createdAt, String comments) {
		super();
		this.inquiryId = inquiryId;
		this.name = name;
		this.email = email;
		this.subject = subject;
		this.message = message;
		this.createdAt = createdAt;
		this.comments = comments;
	}

}
