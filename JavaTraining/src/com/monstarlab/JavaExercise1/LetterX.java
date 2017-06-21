package com.monstarlab.JavaExercise1;

public class LetterX extends BaseLetter implements LetterDrawable {

	public LetterX(Integer size) {
		this.setSize(size);
	}

	@Override
	public void draw() {

		super.draw();

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

}
