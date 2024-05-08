package com.demo.domain;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inquiries")
@DynamicInsert
@DynamicUpdate
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inquiry_id;
    private String name;
    private String email;
    private String message;
    @ColumnDefault("sysdate")
    private Date created_at;
    private String subject;
    private String comments;
    
    // status 속성 추가
    private String status;

	public Long getInquiry_id() {
		return inquiry_id;
	}

	public void setInquiry_id(Long inquiry_id) {
		this.inquiry_id = inquiry_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Inquiry(Long inquiry_id, String name, String email, String message, Date created_at, String subject,
			String comments, String status) {
		super();
		this.inquiry_id = inquiry_id;
		this.name = name;
		this.email = email;
		this.message = message;
		this.created_at = created_at;
		this.subject = subject;
		this.comments = comments;
		this.status = status;
	}

	public Inquiry() {
		// TODO Auto-generated constructor stub
	}

    

    
}
