package com.demo.dto;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class Com_Recipe {
	
	@Id
	int idx;
	String rcp_nm; // 이름
	String att_file_no_mk; //타이틀이미지
	String hash_tag; //주재료
	String manual01;	
	String manual02;
	String manual03;
	String manual04;
	String manual05;
	String manual06;
	String manual_img01;
	String manual_img02;
	String manual_img03;
	String manual_img04;
	String manual_img05;
	String manual_img06;
	String rcp_parts_dtls; //재료
	String rcp_pat2; // 카테고리
	
	
}
