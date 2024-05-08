package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.demo.dto.MemberData;
import com.demo.service.CalculationResult;
import com.demo.service.CalculatorService;
import com.demo.service.CalculatorServiceImpl;
import com.demo.persistence.MemberDataRepository;
import java.util.Optional;

@Controller
public class CalculatorController {

    private final CalculatorServiceImpl calculatorServiceImpl;
    private final MemberDataRepository memberDataRepository;

    public CalculatorController(CalculatorServiceImpl calculatorServiceImpl, MemberDataRepository memberDataRepository) {
        this.calculatorServiceImpl = calculatorServiceImpl;
        this.memberDataRepository = memberDataRepository;
    }

    @PostMapping("/calculate")
    public String calculate(Model model, @RequestParam String Id) {
        // 해당 userId로 회원 데이터 가져오기
        Optional<MemberData> optionalMemberData = memberDataRepository.findById(Id);

        // 회원 데이터를 기반으로 BMI, BMR, 하루 권장 칼로리 계산
        if (optionalMemberData.isPresent()) {
            MemberData memberData = optionalMemberData.get();
            CalculationResult result = calculatorServiceImpl.calculate(memberData);

            // 모델에 회원 데이터 추가
            model.addAttribute("member", memberData);

            // 모델에 결과를 추가
            model.addAttribute("result", result);

            // 결과를 보여줄 뷰로 이동
            return "foodRecommend/UserChoice";
        } else {
            // 해당 Id에 해당하는 회원 데이터가 없을 경우 에러 메시지를 반환하거나 다른 처리를 수행.
            return "foodRecommend/ErrorPage"; // 에러 페이지로 이동하도록 설정
        }
    }

    @GetMapping("/UserChoice")
    public String showCalculatePage(Model model) {
        Optional<MemberData> optionalMemberData = memberDataRepository.findById(1);
        if (optionalMemberData.isPresent()) {
            MemberData memberData = optionalMemberData.get();
            
            // CalculatorServiceImpl을 사용하여 CalculationResult 계산
            CalculationResult result = calculatorServiceImpl.calculate(memberData);
            
            // 모델에 회원 데이터 추가
            model.addAttribute("member", memberData);
            
            // 모델에 결과를 추가
            model.addAttribute("result", result);
        } else {
            // 사용자 정보를 찾을 수 없는 경우 에러 처리
            return "foodRecommend/ErrorPage";
        }
        return "foodRecommend/UserChoice";
    }



}
