package com.demo.persistence;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.dto.MemberData;

public interface MemberDataRepository extends JpaRepository<MemberData, String> {

	Optional<MemberData> findById(String id);
   
}
