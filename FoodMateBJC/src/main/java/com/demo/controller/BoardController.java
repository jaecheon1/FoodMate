package com.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import com.demo.domain.MemberData;
import com.demo.domain.Reply;
import com.demo.dto.Com_Recipe;
import com.demo.service.Com_Board_DetailService;

import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Controller
public class BoardController {

	@Autowired
	Com_Board_DetailService Board_DetailService;
	
	@Autowired
    private EntityManager entityManager;
	

	@Value("${com.demo.upload.path}")
	private String uploadPath;


	// 게시글 목록 조회
	@GetMapping(value= {"/board_list", "/board_list_main"})
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
	@GetMapping("/com_board_detail")
	public String getCom_Board_DetailView(@RequestParam("seq") int seq, Model model, HttpSession session) {
		Com_Board_Detail com_board = Board_DetailService.getCom_Board_Datail(seq);
		Set<Integer> viewed = (Set<Integer>) session.getAttribute("viewed");
		
		
		 if (viewed == null) { // 처음클릭시
		        viewed = new HashSet<>();
		        session.setAttribute("viewed", viewed);
		    }

		    if (!viewed.contains(seq)) {
		        if (com_board != null) {
		            com_board.setCnt(com_board.getCnt() + 1);  // 조회수 1 증가
		            Board_DetailService.updateBoard(com_board);  // DB에 업데이트
		        }
		        viewed.add(seq); //방문한 게시글번호 추가
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
		MemberData loginUser =  (MemberData)session.getAttribute("loginUser");

			if (loginUser == null) { 
				return "member/login"; 
			} else {
		String[] kindList = { "반찬", "국&찌개", "후식", "일품" }; // 카테고리

		model.addAttribute("kindList", kindList);
		return "comboard/BoardWrite";
			}
	}
    
	// 글쓰기 등록
	@Transactional
	@PostMapping("/board_write_t")
	public String insertCom_Board(Com_Board_Detail vo, HttpSession session, Model model,
			@RequestParam("title") String title,
	        @RequestParam("gredient") String gredient,
	        @RequestParam("maingredient") String maingredient,
	        @RequestParam("kind") int kind,
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

		MemberData loginUser =  (MemberData)session.getAttribute("loginUser");
		Page<Com_Board_Detail> pageInfo = (Page<Com_Board_Detail>)session.getAttribute("pageInfo");

        if (loginUser != null) {
        	loginUser = entityManager.merge(loginUser); 
            entityManager.persist(loginUser);
        }
        
        String[] kindList = { "반찬", "국&찌개", "후식", "일품" }; // 카테고리
        String kindName = kindList[kind];
        
	    //레시피에 저장
	    Com_Recipe recipe = new Com_Recipe();  
		
		

		 // 이미지 파일 저장 및 DB 경로 설정
		saveUploadedFile(manualImg01, recipe, 1);
	    saveUploadedFile(manualImg02, recipe, 2);
	    saveUploadedFile(manualImg03, recipe, 3);
	    saveUploadedFile(manualImg04, recipe, 4);
	    saveUploadedFile(manualImg05, recipe, 5);
	    saveUploadedFile(manualImg06, recipe, 6);
	    saveUploadedFile(mainuploadFile, recipe, 7);	    

	    
//	    if (recipe != null) { // idx가 번호를 자동으로 생성하게 했음에도 저장순서가 빨라서 null값으로 판정되어 에러가남. 수동으로 저장을 먼저해주기로함
//	    	recipe = entityManager.merge(recipe); 
//            entityManager.persist(recipe);
//        }
	    
	    recipe.setRcp_nm(title);
	    recipe.setHash_tag(maingredient);
	    recipe.setManual01(manual01);
	    recipe.setManual02(manual02);
	    recipe.setManual03(manual03);
	    recipe.setManual04(manual04);
	    recipe.setManual05(manual05);
	    recipe.setManual06(manual06);
	    recipe.setRcp_parts_dtls(gredient);
	    recipe.setRcp_pat2(kindName);
	    
	    
	    vo.setCom_recipe(recipe);
	    vo.setMember_data(loginUser);
	    
	    Board_DetailService.insertRecipe(recipe);
		Board_DetailService.insertBoard(vo);
		
		model.addAttribute("pageInfo", pageInfo );
		
		return "redirect:/board_list";

	}
	
	//이미지등록
	private void saveUploadedFile(MultipartFile file, Com_Recipe vo, int manualNumber) {
	    if (file != null && !file.isEmpty()) {
	        try {
	            String fileName = file.getOriginalFilename();
	            String fileExtension = fileName.substring(fileName.lastIndexOf("."));
	            String uuid = UUID.randomUUID().toString();
	            String saveName = uuid + fileExtension;

	            // 프로젝트의 작업 경로 확인
	            String workingDir = System.getProperty("user.dir");
	            File directory = new File(workingDir + "/uploads");
	            if (!directory.exists()) {
	                boolean created = directory.mkdirs();
	                System.out.println("Directory creation " + (created ? "succeeded" : "failed") + " at " + directory.getAbsolutePath());
	            }

	            File saveFile = new File(directory, saveName);
	            file.transferTo(saveFile);
	            System.out.println("File saved to " + saveFile.getAbsolutePath());

	            String filePath = "uploads/" + saveName; // 확장자를 포함한 전체 파일 경로를 저장
	            switch (manualNumber) {
	                case 1:
	                    vo.setManual_img01(filePath);
	                    break;
	                case 2:
	                    vo.setManual_img02(filePath);
	                    break;
	                case 3:
	                    vo.setManual_img03(filePath);
	                    break;
	                case 4:
	                    vo.setManual_img04(filePath);
	                    break;
	                case 5:
	                    vo.setManual_img05(filePath);
	                    break;
	                case 6:
	                    vo.setManual_img06(filePath);
	                    break;
	                case 7:
	                    vo.setAtt_file_no_mk(filePath);
	                    break;
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            System.err.println("Failed to save file: " + e.getMessage());
	            throw new RuntimeException("File upload failed", e);
	        }
	    }
	}
		
		// 글수정 페이지로 이동
		@GetMapping("/board_update")
		public String getCom_Board_DetailUpdateView(HttpSession session, Model model, @RequestParam("seq") int seq) {
			MemberData loginUser =  (MemberData)session.getAttribute("loginUser");
			Com_Board_Detail board = Board_DetailService.getCom_Board_Datail(seq);

				if (loginUser == null) { 
					return "member/login"; 
				} else {
					
					String[] kindList = { "반찬", "국&찌개", "후식", "일품" }; // 카테고리

					model.addAttribute("kindList", kindList);
					model.addAttribute("com_boardVO", board);
					
					return "comboard/Boardupdate";
				}
		}
		
	// 글 수정
	@PostMapping("/board_update_t")
	public String updateCom_Board(HttpSession session, @RequestParam("seq") int seq,
			@RequestParam("title") String title,
	        @RequestParam("gredient") String gredient,
	        @RequestParam("maingredient") String maingredient,
	        @RequestParam("kind") int kind,
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
		
		MemberData loginUser =  (MemberData)session.getAttribute("loginUser");
		Com_Board_Detail board = Board_DetailService.getCom_Board_Datail(seq);
        
		if (loginUser == null) { 
			return "member/login"; 
		}else if(!(loginUser.getId()).equals(board.getMember_data().getId())){
			return "본인이 작성한 글만 수정가능합니다.";
		}else {
			
			String[] kindList = { "반찬", "국&찌개", "후식", "일품" }; // 카테고리
	        String kindName = kindList[kind];
	        
		    //레시피에 저장
		    Com_Recipe recipe = new Com_Recipe();  
			
			
			 // 이미지 파일 저장 및 DB 경로 설정
			saveUploadedFile(manualImg01, recipe , 1);
		    saveUploadedFile(manualImg02, recipe, 2);
		    saveUploadedFile(manualImg03, recipe, 3);
		    saveUploadedFile(manualImg04, recipe, 4);
		    saveUploadedFile(manualImg05, recipe, 5);
		    saveUploadedFile(manualImg06, recipe, 6);
		    saveUploadedFile(mainuploadFile, recipe, 7);	
		    

		    recipe.setRcp_nm(title);
		    recipe.setHash_tag(maingredient);
		    recipe.setManual01(manual01);
		    recipe.setManual02(manual02);
		    recipe.setManual03(manual03);
		    recipe.setManual04(manual04);
		    recipe.setManual05(manual05);
		    recipe.setManual06(manual06);
		    recipe.setRcp_parts_dtls(gredient);
		    recipe.setRcp_pat2(kindName);
		    
		    
		    board.setCom_recipe(recipe);
		    
		Board_DetailService.updateBoard(board);
		Board_DetailService.updateRecipe(recipe);
		return "redirect:/com_board_detail?seq=" + seq;
	}
	}

	// 글 삭제
	@GetMapping("/board_delete")
	public String deleteCom_Board(@RequestParam(value = "seq") int seq, Com_Board_Detail vo, HttpSession session) {
		MemberData loginUser = (MemberData) session.getAttribute("loginUser");
		Com_Board_Detail board = Board_DetailService.getCom_Board_Datail(seq);

		if (loginUser == null) { 
			return "member/login"; 
		}else if(!(loginUser.getId()).equals(board.getMember_data().getId())){
			return "본인이 작성한 글만 삭제가능합니다.";
		}else {	
		Board_DetailService.deleteBoard(board);
		return "redirect:/board_list";
	}
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
	
	//추천수 관리
	@PostMapping("/goodpoint")
	public String goodPoint_Action(@RequestParam("seq") int seq, HttpSession session) {
		Com_Board_Detail com_board = Board_DetailService.getCom_Board_Datail(seq);
	    HashMap<Integer, String> goodPointStatusMap = (HashMap<Integer, String>) session.getAttribute("goodPointStatusMap");

	    if (goodPointStatusMap == null) {
	        goodPointStatusMap = new HashMap<>();
	        session.setAttribute("goodPointStatusMap", goodPointStatusMap);
	    }

	    String goodPointStatus = goodPointStatusMap.get(seq);

	    if (goodPointStatus == null || goodPointStatus.equals("off")) {
	        if (com_board != null) {
	            com_board.setGoodpoint(com_board.getGoodpoint() + 1);
	            Board_DetailService.updateBoard(com_board);
	            goodPointStatusMap.put(seq, "on");
	        }
	    } else if (goodPointStatus.equals("on")) {
	        if (com_board != null) {
	            com_board.setGoodpoint(com_board.getGoodpoint() - 1);
	            Board_DetailService.updateBoard(com_board);
	            goodPointStatusMap.put(seq, "off");
	        }
		    }
		    
		return "redirect:/com_board_detail?seq=" + seq;
	}
	
	
	//인기도순 정렬
		@GetMapping("/sorted_board_list")
		public String getSortedBoardList(@RequestParam(value = "seq", defaultValue = "1") int seq,
				@RequestParam(value = "page", defaultValue = "1") int page,
				@RequestParam(value = "size", defaultValue = "10") int size,
				@RequestParam("sort") String sort, Model model) {
		    
		    Page<Com_Board_Detail> pageList = null;

		    switch (sort) {
		        case "cnt_sort":
		            pageList = Board_DetailService.getCom_Board_DetailByCnt(seq, page, size);
		            break;
		        case "goodpoint_sort":
		            pageList = Board_DetailService.getCom_Board_DetailByGoodpoint(seq, page, size);
		            break;
		        case "date_sort":
		            pageList = Board_DetailService.getAllCom_Board(seq, page, size);
		            break;
		    }
		    List<Com_Board_Detail> boardList = pageList.getContent();
			model.addAttribute("boardList", boardList);
		    model.addAttribute("pageInfo", pageList);
		    
		    return "comboard/BoardList";
		}
		
		
		
		
		//댓글 출력
		@PostMapping("/reply_list")
		public String getReply_list(@RequestParam("seq") int seq, Model model) {
			List<Reply> ReplyList = Board_DetailService.getReplyBySeq(seq);
			Com_Board_Detail comBoardDetailVO = Board_DetailService.getCom_Board_Datail(seq);
			model.addAttribute("ReplyList", ReplyList);
			model.addAttribute("Com_Board_DetailVO", comBoardDetailVO);
			return "comboard/BoardDetail";
			}
		
		
		//댓글 등록
		@PostMapping("/reply_save")
		public String insertReply(@RequestParam("seq") int seq, HttpSession session,
				@RequestParam("reply_content") String reply_content, Reply vo) {
			
			MemberData loginUser = (MemberData) session.getAttribute("loginUser");
			Com_Board_Detail board = Board_DetailService.getCom_Board_Datail(seq);
			Reply reply = new Reply();
			
			if (loginUser == null) { 
				return "member/login"; 
			}else {
				reply.setCom_board_detail(board);
				reply.setMember_data(loginUser);
				reply.setContent(reply_content);
				reply.getMember_data().setNo_data(loginUser.getNo_data());
				reply.setCom_board_detail(Board_DetailService.getCom_Board_Datail(seq));
				
				Board_DetailService.insertReply(reply);
				return "redirect:/com_board_detail?seq=" + seq;
				}
		}
		
		//댓글 수정
		@PostMapping(value="/reply_update")
		public String updateReply(@RequestParam("replynum") int replynum,
				@RequestParam("seq") int seq, HttpSession session) {
			
			MemberData loginUser = (MemberData) session.getAttribute("loginUser");
			Reply reply = Board_DetailService.findReplyByreplynum(replynum);
			
			if (loginUser == null) { 
				return "member/login"; 
			}else if(!(loginUser.getId()).equals(reply.getMember_data().getId())){
				return "본인이 작성한 댓글만 수정가능합니다.";
			}else {
				
				Board_DetailService.updateReply(reply);			
				return "redirect:/com_board_detail?seq=" + seq;
		}
		}
		
		//댓글 삭제 
		@GetMapping("/reply_delete")
		public String deleteReply(@RequestParam("replynum") int replynum, 
				@RequestParam("seq") int seq, HttpSession session) {
			
			MemberData loginUser = (MemberData) session.getAttribute("loginUser");
			Reply reply = Board_DetailService.findReplyByreplynum(replynum);
			
			if (loginUser == null) { 
				return "member/login"; 
			}else if(!(loginUser.getId()).equals(reply.getMember_data().getId())){
				return "본인이 작성한 댓글만 삭제가능합니다.";
			}else {

				Board_DetailService.deleteReply(reply);			
				return "redirect:/com_board_detail?seq=" + seq;
		}
		}
		

		

}