package com.demo.service;

import java.util.List;


import com.demo.domain.Member;
import com.demo.dto.MemberData;

public interface MemberService {
	
	// 회원정보 상세 조회
	public Member getMember(String id);
	
	// 회원 로그인
	public int loginID(MemberData vo);
	
	// 회원 인증
	public int confirmID(String id);
	
	// 이름과 이메일로 아이디 찾기
	public Member getIdByNameEmail(String name, String email);
	
	// 아이디와 이름과 이메일로 비밀번호 찾기
	public Member getPwdByIdNameEmail(String id, String name, String email);

	public List<MemberData> getMemberList(String name);

	public void insertMember(Member member);

	public int confirmEmail(String email);





}

