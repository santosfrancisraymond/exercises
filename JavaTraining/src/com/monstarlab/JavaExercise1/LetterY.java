package com.monstarlab.JavaExercise1;

public class LetterY extends BaseLetter implements LetterDrawable {

	public LetterY(Integer size) {
		this.setSize(size);
	}

	@Override
	public void draw() {

		super.draw();

		// Print the upper half
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

		// Print the lower half
		for (int row = (size / 2) + 1; row <= size; row++) {
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

}
