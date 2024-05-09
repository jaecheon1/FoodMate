package com.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.domain.AdminRecipeBoard;
import com.demo.domain.Member;

public interface AdminRecipeBoardRepository extends JpaRepository<AdminRecipeBoard, Integer> {
	
	@Query(value="SELECT * FROM member", nativeQuery = true)
	List<Member> getAllMember();
	
	@Query(value="SELECT * FROM (SELECT * FROM admin_recipe_board ORDER BY regdate DESC) WHERE ROWNUM <= 5", nativeQuery = true)
	List<AdminRecipeBoard> getAllRecipeListMain();
	
	@Query(value="SELECT * FROM admin_recipe_board WHERE recipe_boardnum = :boardnum ORDER BY regdate DESC", nativeQuery = true)
	AdminRecipeBoard findByRecipeBoardnum(int boardnum);
	
}
