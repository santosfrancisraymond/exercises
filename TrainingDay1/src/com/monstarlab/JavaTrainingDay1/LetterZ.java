package com.monstarlab.JavaTrainingDay1;

public class LetterZ extends BaseLetter implements LetterDrawable {

	public LetterZ(Integer size) {
		this.setSize(size);
	}

	@Override
	public void draw() {

		super.draw();

		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (row == 0 || row == size - 1 || size - 1 - col == row) {
					System.out.print("Z");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}

	}

}
