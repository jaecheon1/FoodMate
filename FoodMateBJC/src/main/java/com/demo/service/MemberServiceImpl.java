package com.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.Member;
import com.demo.dto.MemberData;
import com.demo.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepo;

    @Override
    public void insertMember(Member member) {
        memberRepo.save(member);
    }

    @Override
    public Member getMember(String id) {
        return memberRepo.findById(id).orElse(null);
    }

    @Override
    public int loginID(MemberData vo) {
        int result = -1;

        // Member_data 테이블에서 사용자 조회
        Optional<Member> member = memberRepo.findById(vo.getId());
        
        // 결과값 설정: 
        // 1: ID,PWD 일치, 0: 비밀번호 불일치, -1: ID가 존재하지 않음.
        if (member.isEmpty()) {
            result = -1;
        } else if(member.get().getPwd().equals(vo.getId())) {
            result = 1;
        } else {
            result = 0;    // 비밀번호 불일치
        }
        
        return result;
    }

    @Override
    public int confirmID(String id) {
        return memberRepo.existsById(id) ? 1 : -1;
    }

    @Override
    public Member getIdByNameEmail(String name, String email) {
        return memberRepo.findByNameAndEmail(name, email);
    }

    @Override
    public Member getPwdByIdNameEmail(String id, String name, String email) {
        return memberRepo.findByIdAndNameAndEmail(id, name, email);
    }

    @Override
    public List<MemberData> getMemberList(String name) {
        return memberRepo.findByNameContaining(name);
    }

	@Override
	public int confirmEmail(String email) {
		// 이메일이 이미 존재하는지 확인하여 결과를 반환합니다.
	    return memberRepo.existsById(email) ? 0 : 1;
	}
	}
