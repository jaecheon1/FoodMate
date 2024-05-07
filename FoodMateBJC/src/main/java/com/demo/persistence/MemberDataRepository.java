package com.demo.persistence;





import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.dto.MemberData;

public interface MemberDataRepository extends JpaRepository<MemberData, String> {

    
}