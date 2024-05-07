package com.demo.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.demo.domain.Member;
import com.demo.dto.MemberData;

public interface MemberRepository extends JpaRepository<Member, String> {

    // 비밀번호 변경
    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.pwd = :password WHERE m.id = :id") 
    void changePassword(String id, String password);
    
    // ID를 사용하여 회원 조회
    Optional<Member> findById(String id);
    
    // 이름을 포함하는 회원 데이터 조회
    List<MemberData> findByNameContaining(String name);

    // 이름과 이메일을 사용하여 회원 조회
    Member findByNameAndEmail(String name, String email);

	Member findByIdAndNameAndEmail(String id, String name, String email);
}