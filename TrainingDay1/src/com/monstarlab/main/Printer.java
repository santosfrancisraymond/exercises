package com.monstarlab.main;

public class Printer {

	public void printAll(String tempString, int length) {
		for (char x : tempString.toCharArray()) {
			printLetters(length, x);
		}
		printDiamond(length);
		printLowerHalf(length);
		printUpperHalf(length);
	}

	public void printLetters(int length, char x) {
		switch (x) {
		case 'x':
			printX(length);
			break;
		case 'y':
			printY(length);
			break;
		case 'z':
			printZ(length);
			break;
		}
	}

	public void printX(int length) {
		int mid = length / 2;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (i == j || length - 1 - j == i) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public void printY(int length) {
		int mid = length / 2;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if ((i == j || length - 1 - j == i) && (i < (mid) + 1) || i > (mid) && j == mid) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public void printZ(int length) {
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (i == 0 || i == length - 1 || length - 1 - j == i) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public void printDiamond(int input) {
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
		System.out.println();
	}

	public void printLowerHalf(int input) {
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

	public void printUpperHalf(int input) {
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
