package com.monstarlab.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FrancisCalculatorTest {

	private static final double DELTA = 1e-4;

	private FrancisCalculator calculator;

	@BeforeClass
	public static void beforeClass() {
	}

	@Before
	public void init() {
		calculator = new FrancisCalculator();
	}

	@Test
	public void testAdd() {
		double actual = calculator.add(0l, 0l);
	}

	@Test
	public void testSubtract() {
		double actual = calculator.subtract(0l, 0l);
	}

	@Test
	public void testDivide() {

		System.out.println("Test Divide");
		double unexpected = 1l;
		double actual = calculator.divide(4l, 2l);
		assertNotEquals(unexpected, actual);

		actual = calculator.divide(1l, 10000l);

		double expected = 0.0001;
		assertEquals(expected, actual, DELTA);

		expected = 0.001;
		assertNotEquals(expected, actual, DELTA);

	}

	@After
	public void cleanUp() {
		calculator = null;
	}

	@AfterClass
	public static void afterClass() {
	}
}
