package com.monstarlab.junit;

public class FrancisCalculator implements Calculator {

	public long add(long a, long b) {
		return a + b;
	}

	public long subtract(long a, long b) {
		return a - b;
	}

	public double multiply(double a, double b) {
		return a * b;
	}

	public double divide(double a, double b) {
		return a / b;
	}

}
