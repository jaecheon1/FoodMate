package com.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.KeywordData;
import com.demo.dto.RecommendData;

// HomeController는 Spring의 RestController로, 클라이언트로부터 HTTP POST 요청을 처리하고 JSON 응답을 반환하는 클래스
@RestController
public class HomeController {
	
	// STD_IN과 STD_ERR는 프로세스의 표준 입력과 표준 오류 스트림을 나타내는 상수
	private static final String STD_IN = "stdin";
	private static final String STD_ERR = "stderr";
	
	// HTTP POST 요청을 처리하는 메서드로, /processKeywords 경로로 들어오는 요청을 처리.
	@PostMapping("/processKeywords")
	public List<RecommendData> processKeywords(@RequestBody KeywordData keywordData) {
	    // 클라이언트로부터 받은 JSON 데이터를 KeywordData 객체로 매핑합니다.
	    String selectedKeywords = keywordData.getSelectedKeywords();
	    String excludeIngredients = keywordData.getExcludeIngredients();
	    String dietCalory = (keywordData.getDietCalory()).replaceAll(" ", ""); // 공백 제거
	    String nutrientChoice = keywordData.getNutrientChoice();

	    // 입력 데이터 확인을 위한 로그 출력
	    System.out.println("selectedKeywords=" + selectedKeywords);
	    System.out.println("excludeIngredients=" + excludeIngredients);
	    System.out.println("dietCalory=" + dietCalory);
	    System.out.println("nutrientChoice=" + nutrientChoice);

	    // 추천 음식 리스트를 담기 위한 ArrayList 생성
	    List<RecommendData> foodList = new ArrayList<>();
	    // 파이썬 프로그램 실행 결과를 문자열로 반환
	    String result = runningProcess(selectedKeywords, excludeIngredients, dietCalory, nutrientChoice);

	    // 파이썬 프로그램 실행 결과 확인을 위한 로그 출력
	    System.out.println("분석 결과=" + result);

	    // 결과 문자열을 줄 단위로 분리
	    String[] tmpArr = result.split("\n");
	    for (String line : tmpArr) {
	        // 각 줄을 '/'로 분리하여 배열로 저장
	        String[] items = line.split("/");
	        // 예상되는 아이템 수(4개)인지 확인
	        if (items.length >= 4) {
	            // RecommendData 객체를 생성하여 각 필드를 설정
	            RecommendData food = new RecommendData();
	            food.setIdx(items[0].trim());
	            food.setName(items[1].trim());
	            food.setCalories(items[2].replace(" kcal", "").trim());
	            food.setImages(items[3].split(", ")[0].trim());
	            // 리스트에 RecommendData 객체 추가
	            foodList.add(food);
	            // RecommendData 객체 추가 확인을 위한 로그 출력
	            System.out.println("food추가" + food);
	        } else {
	            // 예상된 아이템 수가 아닐 경우 해당 줄을 건너뜁니다.
	            System.out.println("Skipping line due to unexpected number of items: " + line);
	        }
	    }

	    // 추천 음식 리스트 반환
	    return foodList;
	}

	// 파이썬 스크립트를 실행하고 결과를 반환하는 메서드
	private static String runningProcess(String selectedKeywords, String excludeIngredients, String dietCalory, String nutrientChoice) {
	    Process process = null;
	    // 파이썬 스크립트가 있는 작업 디렉토리를 설정
	    File workingDirectory = new File("C:/Users/frenc/machinelearning");
	    // 실행할 파이썬 명령어를 생성
	    String cmd = "python C:/Users/frenc/machinelearning/recommend_food.py \"" + selectedKeywords + "\" \"" + excludeIngredients + "\" " + dietCalory + " \"" + nutrientChoice + "\"";
	    ProcessStream processInStream = null;
	    ProcessStream processErrStream = null;
	    String result = "";

	    try {
	        // 파이썬 스크립트 실행
	        process = Runtime.getRuntime().exec(cmd, null, workingDirectory);
	        // 프로세스의 입력 스트림과 오류 스트림을 처리할 ProcessStream 객체 생성
	        processInStream = new ProcessStream(STD_IN, process.getInputStream());
	        processErrStream = new ProcessStream(STD_ERR, process.getErrorStream());

	        // 입력 스트림으로부터 결과를 읽고, 오류 스트림을 별도로 처리
	        result = processInStream.start();
	        processErrStream.start();
	        // 프로세스의 출력 스트림을 닫음
	        process.getOutputStream().close();

	        // 프로세스가 종료될 때까지 대기
	        process.waitFor();

	    } catch (IOException | InterruptedException e) {
	        // 예외 발생 시 스택 트레이스를 출력
	        e.printStackTrace();
	    }

	    // 입력 스트림으로부터 읽은 결과를 반환
	    return processInStream.getResult();
	}
}
