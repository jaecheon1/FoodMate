package com.demo.service;

import org.springframework.stereotype.Service;

import com.demo.dto.MemberData;

@Service
public class CalculatorService {

	
	
    public CalculationResult calculate(MemberData memberData) {
        // 여기서 BMI, BMR, 하루 권장 칼로리를 계산하고 CalculationResult 객체에 담아 반환.
        // 예시 코드:
        double bmi = calculateBMI(memberData);
        double bmr = calculateBMR(memberData);
        double dailyCalories = calculateDailyCalories(memberData);
        
        return new CalculationResult(bmi, bmr, dailyCalories);
    }


    

    private double calculateBMI(MemberData memberData) {
        // BMI 계산
        int weight = memberData.getWeight();
        int height = memberData.getHeight();
        double height_m = height / 100.0; // 키를 미터 단위로 변환
        return weight / Math.pow(height_m, 2);
    }


	private double calculateBMR(MemberData memberData) {
	    // BMR 계산
	    // 성별에 따라 계산식이 다르게 적용.
	    int weight = memberData.getWeight();
	    int height = memberData.getHeight();
	    int age = memberData.getAge();
	    String gender = memberData.getGender();
	    
	    if (gender.equals("Male")) {
	        return 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
	    } else {
	        return 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
	    }
	}

	//하루 권장 칼로리 계산
	private double calculateDailyCalories(MemberData memberData) {
	    double height_m = memberData.getHeight() / 100.0;
	    double weight_kg = memberData.getWeight();
	    double bmi = weight_kg / Math.pow(height_m, 2);

	    double bmr;
	    if (memberData.getGender().equals("Male")) {
	        bmr = 88.362 + (13.397 * weight_kg) + (4.799 * height_m) - (5.677 * memberData.getAge());
	    } else {
	        bmr = 447.593 + (9.247 * weight_kg) + (3.098 * height_m) - (4.330 * memberData.getAge());
	    }

	    double caloriesThreshold = bmr * 1.2; // 활동적일 시 1.5정도 곱해줌
	    return caloriesThreshold;
	}

}