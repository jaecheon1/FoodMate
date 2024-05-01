package com.demo.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Mypage {
	@Id
	private int bmi;	// bmi
	private double height;	// 회원 키(소수점이하1자리)
	private double weight;	// 회원 몸무게(소수점이하1자리)	
	private String goal;	// 회원 목표(input radio를 이용해서 하나의 선택지만 선택)
	
	@Column(columnDefinition = "char(1) default 'y'")
	private String food_preference;		// 음식선호도 : 'y'는 선호 | 'n'은 불호
	
	@Column(columnDefinition = "char(1) default 'n'")
	private String allergy_food;		// 알레르기 음식 : 'n'은 없음 | 'y'는 있음
	
	@ManyToOne
	@JoinColumn(name="id", nullable=false)
	private Member member;		// 회원테이블 (개인정보 보기 및 수정)
	
	@ManyToOne
	@JoinColumn(name = "seq", nullable = false)
	private Com_Board comBoard; // 커뮤니티게시글 (작성한 레시피목록 보기)

}
