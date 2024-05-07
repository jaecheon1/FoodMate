package com.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.demo.domain.Com_Board_Detail;
import com.demo.dto.Com_Recipe;
import com.demo.service.Com_Board_DetailService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {

	@Autowired
	Com_Board_DetailService Board_DetailService;

	@Value("${com.demo.upload.path}")
	private String uploadPath;

	// 게시글 목록 조회
	@GetMapping("/board_list")
	public String getboard_list(@RequestParam(value = "seq", defaultValue = "1") int seq,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size, Model model) {
		Page<Com_Board_Detail> pageList = Board_DetailService.getAllCom_Board(seq, page, size);
		List<Com_Board_Detail> boardList = pageList.getContent();

		model.addAttribute("boardList", boardList);
		model.addAttribute("pageInfo", pageList);
		return "comboard/BoardList";
	}

	// 게시글 상세정보 조회
	@PostMapping("/com_board_detail")
	public String getCom_Board_DetailView(@RequestParam("seq") int seq, Com_Board_Detail vo, Model model) {
		Com_Board_Detail com_board = Board_DetailService.getCom_Board_Datail(seq);
		
		
	    if (com_board != null) {
	        com_board.setCnt(com_board.getCnt() + 1);  // 조회수 1 증가
	        Board_DetailService.updateBoard(com_board);  // DB에 업데이트
	    }
	    
		model.addAttribute("Com_Board_DetailVO", com_board);
		model.addAttribute("seq", com_board.getSeq());

		return "comboard/BoardDetail";
	}

	// 제목, 작성자로 검색
	@GetMapping("/board_search")
	public String getSearchByType(@RequestParam(value = "seq", defaultValue = "1") int seq,
			@RequestParam("searchType") String searchType, @RequestParam("searchKeyword") String keyword,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size, Model model) {

		Page<Com_Board_Detail> pageList;

		if ("title".equals(searchType)) {
			pageList = Board_DetailService.getCom_BoardByTitle(seq, page, size, keyword);
			List<Com_Board_Detail> searchResult = pageList.getContent();
			model.addAttribute("boardList", searchResult);
			model.addAttribute("pageInfo", pageList);

		} else {
			pageList = Board_DetailService.getCom_BoardByWriter(seq, page, size, keyword);
			List<Com_Board_Detail> searchResult = pageList.getContent();
			model.addAttribute("boardList", searchResult);
			model.addAttribute("pageInfo", pageList);
		}

		return "comboard/BoardList";
	}

	// 글쓰기 페이지로 이동
	@GetMapping("/board_write")
	public String getCom_Board_DetailWriteView(HttpSession session, Model model) {
		Com_Board_Detail loginUser = (Com_Board_Detail) session.getAttribute("loginUser");

//			if (loginUser == null) { 
//				return "member/login"; // 로그인페이지 넣기 (아직 안넣음!)
//			} else {
		String[] kindList = { "반찬", "국&찌개", "후식", "일품" }; // 카테고리

		model.addAttribute("kindList", kindList);
		return "comboard/BoardWrite";
//			}
	}
	
	
	// 글쓰기 등록
	@PostMapping("/board_write_t")
	public String insertCom_Board(Com_Board_Detail vo, HttpSession session,
			@RequestParam("title") String title,
	        @RequestParam("gredient") String gredient,
	        @RequestParam("maingredient") String maingredient,
	        @RequestParam("kind") String kind,
	        @RequestParam("manual01") String manual01,
	        @RequestParam("manual02") String manual02,
	        @RequestParam("manual03") String manual03,
	        @RequestParam("manual04") String manual04,
	        @RequestParam("manual05") String manual05,
	        @RequestParam("manual06") String manual06,
	        @RequestParam(value = "manual_img01", required = false) MultipartFile manualImg01,
	        @RequestParam(value = "manual_img02", required = false) MultipartFile manualImg02,
	        @RequestParam(value = "manual_img03", required = false) MultipartFile manualImg03,
	        @RequestParam(value = "manual_img04", required = false) MultipartFile manualImg04,
	        @RequestParam(value = "manual_img05", required = false) MultipartFile manualImg05,
	        @RequestParam(value = "manual_img06", required = false) MultipartFile manualImg06,
	        @RequestParam(value = "main_img", required = false) MultipartFile mainuploadFile) {

		// Com_Board_Detail loginUser = (Com_Board_Detail) session.getAttribute("loginUser");
		
		 // 이미지 파일 저장 및 DB 경로 설정
		saveUploadedFile(manualImg01, vo, 1);
	    saveUploadedFile(manualImg02, vo, 2);
	    saveUploadedFile(manualImg03, vo, 3);
	    saveUploadedFile(manualImg04, vo, 4);
	    saveUploadedFile(manualImg05, vo, 5);
	    saveUploadedFile(manualImg06, vo, 6);
	    saveUploadedFile(mainuploadFile, vo, 7);
	    
	    //레시피에 저장
	    Com_Recipe recipe = new Com_Recipe();  
	    recipe.setRcp_nm(title);
	    recipe.setHash_tag(maingredient);
	    recipe.setManual01(manual01);
	    recipe.setManual02(manual02);
	    recipe.setManual03(manual03);
	    recipe.setManual04(manual04);
	    recipe.setManual05(manual05);
	    recipe.setManual06(manual06);
	    recipe.setRcp_parts_dtls(gredient);
	    recipe.setRcp_pat2(kind);
	    
	    vo.setCom_recipe(recipe);
	    
	//	vo.getMember_data().setId(loginUser.getMember_data().getId());
	 // 로그인기능필요.
	    
	    
	    //vo.setMember_data();  
		Board_DetailService.insertBoard(vo);
		
		return "redirect:BoardList";

	}
	
	//이미지등록
		private void saveUploadedFile(MultipartFile file, Com_Board_Detail vo, int manualNumber) {
		    if (file != null && !file.isEmpty()) {
		        try {
		            String fileName = file.getOriginalFilename(); // 원본 파일 이름
		            String uuid = UUID.randomUUID().toString(); // 유니크 ID 생성
		            String saveName = uuid + "_" + fileName; // 저장될 파일명

		            File saveFile = new File(uploadPath, saveName); // 저장 경로와 파일명 설정
		            file.transferTo(saveFile); // 파일 저장
		            

		            // 각 조리법 이미지에 따라 경로 설정
		            switch (manualNumber) {
		                case 1:
		                    vo.getCom_recipe().setManual_img01("/images/" + saveName);
		                    break;
		                case 2:
		                    vo.getCom_recipe().setManual_img02("/images/" + saveName);
		                    break;
		                case 3:
		                    vo.getCom_recipe().setManual_img03("/images/" + saveName);
		                    break;
		                case 4:
		                    vo.getCom_recipe().setManual_img04("/images/" + saveName);
		                    break;
		                case 5:
		                    vo.getCom_recipe().setManual_img05("/images/" + saveName);
		                    break;
		                case 6:
		                    vo.getCom_recipe().setManual_img06("/images/" + saveName);
		                    break;
		                case 7:
		                    vo.getCom_recipe().setAtt_file_no_mk("/images/" + saveName);
		                    break;
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}

		
	// 글 수정
	@PostMapping("/board_update")
	public String updateCom_Board(Com_Board_Detail vo, HttpSession session) {
		Com_Board_Detail loginUser = (Com_Board_Detail) session.getAttribute("loginUser");

//		if (loginUser == null) { 
//			return "member/login"; // 로그인페이지 넣기 (아직 안넣음!)
//		}else if(loginUser.getMember_data().getId() == vo.getMember_data().getId()){
//			return "본인이 작성한 글만 수정가능합니다.";
//		}else {
		vo.setCnt(vo.getCnt());
		vo.setD_regdate(vo.getD_regdate());
		Board_DetailService.updateBoard(vo);
		return "redirect:BoardDetail";
//	}
	}

	// 글 삭제
	@PostMapping("/board_delete")
	public String deleteCom_Board(Com_Board_Detail vo, HttpSession session) {
		Com_Board_Detail loginUser = (Com_Board_Detail) session.getAttribute("loginUser");

//		if (loginUser == null) { 
//			return "member/login"; // 로그인페이지 넣기 (아직 안넣음!)
//		}else if(loginUser.getMember_data().getId() == vo.getMember_data().getId()){
//			return "본인이 작성한 글만 삭제가능합니다.";
//		}else {
		Board_DetailService.deleteBoard(vo);
		return "redirect:board_list";
//	}
	}
	
	//카테고리별 분류
	@GetMapping("/category")
	public String com_BoardKindAction(@RequestParam(value = "seq", defaultValue = "1") int seq,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size,
			Model model, @RequestParam(value = "", required = false)String category) {
		Page<Com_Board_Detail> pageList = Board_DetailService.getCom_Board_DetailByKind(seq, page, size, category);
		List<Com_Board_Detail> kindlist = pageList.getContent();
		
		model.addAttribute("boardKindList", kindlist);
		model.addAttribute("pageInfo", pageList);
		
		return "comboard/BoardKind";
	}
	

}