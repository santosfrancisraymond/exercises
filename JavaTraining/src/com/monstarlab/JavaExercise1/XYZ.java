package com.monstarlab.JavaExercise1;

import java.util.Scanner;

public class XYZ {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		String input = null;
		Boolean done = false;

		String[] inputs = null;

		String number = null;
		String text = null;

		while (!done) {

			System.out.println("Enter the number: ");
			input = sc.nextLine();

			inputs = input.split(" ");

			if (inputs.length == 2) {
				done = true;
			} else {
				done = false;
				System.out.println("Input the correct number of parameters.");

			}
		}

		number = inputs[0];

		text = inputs[1];

		done = false;

		int result = 0;
		while (!done) {
			String str = number; // read the input
			try {
				result = Integer.parseInt(str);
				done = true;
			} catch (NumberFormatException nfe) {
				// ask the user to try again
				done = false;
				System.out.println(result);
				// System.out.println(nfe.getMessage());
				System.out.println("The first string from the input isn't an integer");
				return;
			}

			if (result > 2) {
				done = true;
			} else {
				done = false;
				System.out.println("The integer isn't greater than 2");
				return;
			}
		}

		int theNumber = Integer.parseInt(number);

		for (int i = 0; i < text.length(); i++) {
			Character character = text.charAt(i);

			switch (Character.toLowerCase(character)) {

			case 'x':
				printX(theNumber);
				break;
			case 'y':
				printY(theNumber);
				break;
			case 'z':
				printZ(theNumber);
				break;

			}
		}

		sc.close();

	}

	private static void printX(Integer theNumber) {

		int size = theNumber;
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (row == col || row + col == size - 1) {
					System.out.print("X");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}

	}

	private static void printY(Integer theNumber) {

		int size = theNumber;
		for (int row = 0; row < size / 2; row++) {
			for (int col = 0; col < size; col++) {
				if (row == col || row + col == size - 1) {
					System.out.print("Y");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}

		for (int row = (size / 2) + 1; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (col == (size / 2)) {
					System.out.print("Y");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}

	}

	private static void printZ(Integer theNumber) {

		int size = theNumber;
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (row == 0 || row == size - 1 || theNumber - 1 - col == row) {
					System.out.print("Z");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}

	}
}
