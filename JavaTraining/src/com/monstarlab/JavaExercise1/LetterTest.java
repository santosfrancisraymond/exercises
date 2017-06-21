package com.monstarlab.JavaExercise1;

import java.util.Scanner;

public class LetterTest {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String input = null;
		Boolean done = false;

		String[] inputs = null;

		String number = null;
		String text = null;

		while (!done) {

			System.out.println("Enter the integer and the XYZ string: ");
			input = sc.nextLine();

			inputs = input.split(" ");

			if (inputs.length == 2) {
				done = true;
			} else {
				done = false;
				System.out.println("Input the correct number of parameters.");
				continue;
			}

			number = inputs[0];

			text = inputs[1];

			done = false;

			int result = 0;

			String str = number; 
			try {
				result = Integer.parseInt(str);
				done = true;
			} catch (NumberFormatException nfe) {
				// ask the user to try again
				done = false;
				System.out.println("The first string " + result + " from the input isn't an integer. Try again");
				continue;
			}

			if (result > 0) {
				done = true;
			} else {
				// ask the user to try again
				done = false;
				System.out.println("The integer isn't greater than 0. Try again");
				continue;
			}

			if (result % 2 != 0) {
				done = true;
			} else {
				// ask the user to try again
				done = false;
				System.out.println("The integer isn't odd. Try again");
				continue;
			}

		}

		int theNumber = Integer.parseInt(number);

		for (int i = 0; i < text.length(); i++) {
			Character character = text.charAt(i);

			switch (Character.toLowerCase(character)) {

			case 'x':
				LetterX letterX = new LetterX(theNumber);
				letterX.draw();
				break;
			case 'y':
				LetterY letterY = new LetterY(theNumber);
				letterY.draw();
				break;
			case 'z':
				LetterZ letterZ = new LetterZ(theNumber);
				letterZ.draw();
				break;

			}
		}

		sc.close();

	}

}
