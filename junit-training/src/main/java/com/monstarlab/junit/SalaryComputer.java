package com.monstarlab.junit;

import java.util.List;

public class SalaryComputer {

	private Calculator calculator;

	public double computeSalary(List<Double> salaries) {
		double total = 0.0;

		for (int i = 0; i < salaries.size(); i++) {
			total = calculator.add(total, salaries.get(i));
		}

//		System.out.println(total);
		return total;
	}

	public Calculator getCalculator() {
		return calculator;
	}

	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}

}
