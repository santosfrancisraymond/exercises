package com.monstarlab.junit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SalaryComputerTest {

	// @InjectMocks
	// private SalaryComputer salaryComputer = new SalaryComputer();
	private SalaryComputer salaryComputer;

	// @Mock
	private Calculator calculator;

	@Before
	public void init() {

		salaryComputer = new SalaryComputer();
		calculator = mock(Calculator.class);

		salaryComputer.setCalculator(calculator);
	}

	@Test
	public void testComputeTotalSalary() {

		//Mock first
		when(calculator.add(0000.0, 1000.0)).thenReturn(1000.0);
		when(calculator.add(1000.0, 2000.0)).thenReturn(3000.0);
		when(calculator.add(3000.0, 3000.0)).thenReturn(6000.0);

		double expected = 6000.0;
		double actual = salaryComputer.computeSalary(Arrays.asList(1000.0, 2000.00, 3000.0));

		System.out.println(actual);
		assertEquals(expected, actual, 0);

	}
}
