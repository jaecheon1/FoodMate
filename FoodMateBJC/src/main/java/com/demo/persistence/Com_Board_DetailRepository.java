package com.demo.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.domain.Com_Board_Detail;

public interface Com_Board_DetailRepository extends JpaRepository<Com_Board_Detail, Integer> {

	// 상세페이지 정보 출력
	@Query(value="SELECT * FROM Com_Board_Detail c WHERE c.seq = ?1 ", nativeQuery=true)
	public Com_Board_Detail getCom_Board_Detail(int seq); 
	
	 //게시글 리스트 출력
	@Query(value="SELECT * FROM Com_Board_Detail ORDER BY seq DESC ", nativeQuery=true)
	public List<Com_Board_Detail> getBoard_List();
	
	// 제목으로 검색
	@Query(value="SELECT b.* FROM Com_Board_Detail b JOIN Com_Recipe r ON b.idx = r.idx WHERE r.rcp_nm LIKE %?1% ", nativeQuery=true)
	public Page<Com_Board_Detail> findCom_Board_DetailByTitleContainingOrderByTitle(String title, int seq, Pageable pageable);
	
	// 글쓴이 아이디로 검색
	@Query(value="SELECT b.* FROM Com_Board_Detail b JOIN Member_Data m ON b.no_data = m.no_data WHERE m.id = ?1 ", nativeQuery=true)
	public Page<Com_Board_Detail> findCom_Board_DetailByIdContainingOrderById(String id, int seq, Pageable pageable);
	
	//전체글 페이징처리 
	@Query(value="SELECT * FROM Com_Board_Detail ORDER BY seq DESC ", nativeQuery=true)
	public Page<Com_Board_Detail> findAllCom_Board_Detail(int seq, Pageable pageable); //전체글 페이징처리 
	
	//정렬 상품 조회
	@Query(value="SELECT b.* FROM Com_Board_Detail b JOIN Com_Recipe r ON b.idx = r.idx WHERE r.rcp_pat2 = ?1 ", nativeQuery=true)
	public Page<Com_Board_Detail> findCom_Board_DetailByKindContaining(String kind, int seq, Pageable pageable);
	
	//조회순 정렬
	@Query(value="SELECT * FROM Com_Board_Detail ORDER BY cnt DESC ", nativeQuery=true)
	public Page<Com_Board_Detail> findAllByOrderByCntDesc(int seq, Pageable pageable); 
	
	//추천순 정렬
	@Query(value="SELECT * FROM Com_Board_Detail ORDER BY goodpoint DESC ", nativeQuery=true)
	public Page<Com_Board_Detail> findAllByOrderByGoodpointDesc(int seq, Pageable pageable);


	
                                            
}