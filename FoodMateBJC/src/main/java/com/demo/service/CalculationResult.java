package com.demo.service;

public class CalculationResult {
    private double bmi;
    private double bmr;
	private double dailyCalories;

    public CalculationResult(double bmi, double bmr, double dailyCalories) {
		this.bmi = bmi;
		this.bmr = bmr;
		this.dailyCalories = dailyCalories;
	}

	public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public double getBmr() {
        return bmr;
    }

    public void setBmr(double bmr) {
        this.bmr = bmr;
    }

	public double getDailyCalories() {
		return dailyCalories;
	}

	public void setDailyCalories(double dailyCalories) {
		this.dailyCalories = dailyCalories;
	}

	public void add(CalculationResult result) {
		// TODO Auto-generated method stub
		
	}

   
}