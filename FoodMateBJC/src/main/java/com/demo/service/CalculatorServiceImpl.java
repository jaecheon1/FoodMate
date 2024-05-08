package com.demo.service;

import org.springframework.stereotype.Service;

import com.demo.dto.MemberData;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public CalculationResult calculate(MemberData memberData) {
        // 여기서 BMI, BMR, 하루 권장 칼로리를 계산하고 CalculationResult 객체에 담아 반환.
        double bmi = calculateBMI(memberData);
        double bmr = calculateBMR(memberData);
        double dailyCalories = calculateDailyCalories(memberData);
        
        // 반올림하여 소숫점 둘째 자리까지 계산된 결과 반환
        return new CalculationResult(roundToTwoDecimalPlaces(bmi), roundToTwoDecimalPlaces(bmr), roundToTwoDecimalPlaces(dailyCalories));
    }
    
    // BMI 계산 메서드
    private double calculateBMI(MemberData memberData) {      
        int weight = memberData.getWeight();
        int height = memberData.getHeight();
        double height_m = height / 100.0; // 키를 미터 단위로 변환
        return weight / (height_m * height_m);
    }
    
    // BMR 계산 메서드
    private double calculateBMR(MemberData memberData) {
        // 성별에 따라 계산식이 다르게 적용.
        int weight = memberData.getWeight();
        int height = memberData.getHeight();
        int age = memberData.getAge();
        String gender = memberData.getGender();
        
        if (gender.equalsIgnoreCase("Male")) {
            return 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else {
            return 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        }
    }
    
    // 하루 권장 칼로리 계산 메서드
    private double calculateDailyCalories(MemberData memberData) {
        double height_m = memberData.getHeight() / 100.0;
        double weight_kg = memberData.getWeight();
        double bmi = weight_kg / (height_m * height_m);

        double bmr;
        if (memberData.getGender().equalsIgnoreCase("Male")) {
            bmr = 88.362 + (13.397 * weight_kg) + (4.799 * height_m) - (5.677 * memberData.getAge());
        } else {
            bmr = 447.593 + (9.247 * weight_kg) + (3.098 * height_m) - (4.330 * memberData.getAge());
        }

        double caloriesThreshold = bmr * 1.2; // 활동적일 시 1.2를 곱해줍니다.
        return caloriesThreshold;
    }

    // 소숫점 둘째 자리까지 반올림하는 메서드
    private double roundToTwoDecimalPlaces(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
