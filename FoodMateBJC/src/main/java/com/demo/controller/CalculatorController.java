package com.demo.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.demo.dto.MemberData;
import com.demo.service.CalculationResult;
import com.demo.service.CalculatorService;
import com.demo.persistence.MemberDataRepository;

@Controller
public class CalculatorController {

    private final CalculatorService calculatorService;
    private final MemberDataRepository memberDataRepository;

    public CalculatorController(CalculatorService calculatorService, MemberDataRepository memberDataRepository) {
        this.calculatorService = calculatorService;
        this.memberDataRepository = memberDataRepository;
    }

    @PostMapping("/calculate")
    public String calculate(Model model, String userId) {
        // 해당 userId로 회원 데이터 가져오기
        MemberData memberData = new MemberData();
        memberData.setId("gildong"); // gildong 회원의 ID를 설정합니다.
        // 기타 필요한 회원 정보도 설정합니다. (예: 나이, 키, 몸무게, 성별 등)

        // 회원 데이터를 기반으로 BMI, BMR, 하루 권장 칼로리 계산
        CalculationResult result = calculatorService.calculate(memberData);

        // 모델에 회원 데이터 추가
        model.addAttribute("MemberData", memberData);
        
        // 모델에 결과를 추가
        model.addAttribute("result", result);
        
        // 결과를 보여줄 뷰로 이동
        return "UserChoice";
    }




    @GetMapping("/UserChoice")
    public String showCalculatePage() {
        return "UserChoice";
    }
}