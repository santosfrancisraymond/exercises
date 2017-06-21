package com.monstarlab.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class XYZTest {

	private LetterX letterX;

	@BeforeClass
	public static void beforeClass() {
	}

	@Before
	public void init() {
		letterX = new LetterX(3);
		System.out.println(letterX.draw());
		
	}

	@Test
	public void testAdd() {

		
	}

	@Test
	public void testSubtract() {


	}
	
	

	@After
	public void cleanUp() {

	}

	@AfterClass
	public static void afterClass() {
	}
}
