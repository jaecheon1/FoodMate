package com.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.domain.MemberData;

public interface MemberRepository extends JpaRepository<MemberData, Long> {

	@Query(value="SELECT * FROM member_data ORDER BY no_data DESC", nativeQuery = true)
	List<MemberData> getAllMember();
	
	@Query(value = "SELECT * FROM member_data WHERE name =:name AND email =:email", nativeQuery=true)
	MemberData findByNameAndEmail(String name, String email);

	@Query(value = "SELECT * FROM member_data WHERE id =:id", nativeQuery=true)
	MemberData findByLoginId(String id);
	
	@Query(value = "SELECT * FROM member_data WHERE email =:email", nativeQuery=true)
	MemberData findByEmail(String email);
	
	@Query(value = "SELECT * FROM member_data WHERE nickname =:nickname", nativeQuery=true)
	MemberData findByNickname(String nickname);
	
	@Query(value = "SELECT * FROM member_data WHERE id = :id AND name = :name AND email = :email", nativeQuery = true)
	MemberData findByIdAndNameAndEmail(String id, String name, String email);

	@Query(value = "SELECT * FROM member_data WHERE name LIKE %:name%", nativeQuery=true)
	List<MemberData> findByNameContaining(String name);
}