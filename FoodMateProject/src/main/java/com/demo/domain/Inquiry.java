package com.demo.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.demo.dto.InquiryDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@DynamicInsert 
@DynamicUpdate
public class Inquiry {

	@Id
	@OneToOne
    @JoinColumn(name="seq", nullable=false)
	private Inquiry_Detail inquiry_detail; // 문의글 번호

	@OneToOne
	@JoinColumn(name="idx", nullable=false)
	private InquiryDTO subject; // 문의글 제목

}
