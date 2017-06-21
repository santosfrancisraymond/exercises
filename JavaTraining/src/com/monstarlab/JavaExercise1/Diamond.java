package com.monstarlab.JavaExercise1;

import java.util.Scanner;

public class Diamond {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the number: ");

		int input = sc.nextInt();

		if (input > 0) {

			System.out.println("DIAMOND");
			printDiamond(input);
			System.out.println();
			System.out.println("HOURGLASS");
			printLowerHalf(input);
			printUpperHalf(input);
		}
		sc.close();
	}

	private static void printDiamond(int input) {

		// Getting midRow of the diamond

		int midRow = input - 1;

		int row = 1;

		// Print upper half of the diamond

		for (int i = midRow; i > 0; i--) {
			// Print i spaces at the beginning of each row

			for (int j = 1; j <= i; j++) {
				System.out.print(" ");
			}

			// Print j *'s at the end of each row

			for (int j = 1; j <= row; j++) {
				System.out.print("* ");
			}

			System.out.println();

			row++;
		}

		// Print lower half of the diamond

		for (int i = 0; i <= midRow; i++) {
			// Print i spaces at the beginning of each row

			for (int j = 1; j <= i; j++) {
				System.out.print(" ");
			}

			// Print j *'s at the end of each row

			for (int j = row; j > 0; j--) {
				System.out.print("* ");
			}

			System.out.println();

			row--;
		}

	}

	private static void printLowerHalf(int input) {

		int midRow = input;

		int row = input;

		for (int i = 0; i < midRow; i++) {
			// Print i spaces at the beginning of each row

			for (int j = 1; j <= i; j++) {
				System.out.print(" ");
			}

			// Print j *'s at the end of each row

			for (int j = row; j > 0; j--) {
				System.out.print("* ");
			}

			System.out.println();

			row--;
		}

	}

	private static void printUpperHalf(int input) {

		int midRow = input;

		int row = 1;

		for (int i = midRow; i > 0; i--) {
			// Print i spaces at the beginning of each row

			for (int j = 1; j < i; j++) {
				System.out.print(" ");
			}

			// Print j *'s at the end of each row

			for (int j = 1; j <= row; j++) {
				System.out.print("* ");
			}

			System.out.println();

			row++;
		}

	}

}
